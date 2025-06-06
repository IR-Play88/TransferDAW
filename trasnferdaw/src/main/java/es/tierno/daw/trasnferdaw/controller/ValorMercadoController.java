package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.tierno.daw.trasnferdaw.model.bbdd.Database;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDAO;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDBFactory;
import es.tierno.daw.trasnferdaw.model.entities.ValorMercadoHistorial;
import es.tierno.daw.trasnferdaw.model.exception.BBDDException;

@WebServlet("/ValorMercadoController")
public class ValorMercadoController extends HttpServlet {
    private static final String ACCION= "accion";
    private static final String ERROR = "error";
    private static final String MSG= "mensaje";

    private static final String VALOR_MERCADO = "valorMercado";

    private static final String OPC_MODIFICAR= "modificar";
    private static final String OPC_ELIMINAR= "eliminar";
    private static final String OPC_ACTUALIZAR= "actualizar";
    private static final String OPC_INSERTAR= "insertar";

    private static final String LISTA_VALORES= "listaValores";
    private static final String VALOR_MERCADO_JSP= "/valor_mercado/valor_mercado.jsp";
    private static final String EDITAR_VALOR_MERCADO = "/valor_mercado/editar_valor_mercado.jsp";
    private static final String VALOR_MERCADO_CONTROLLER= "ValorMercadoController";

    private static final String VALOR_MERCADO_ID= "id_historial";
    private static final String VALOR_MERCADO_JUGADOR= "jugador";
    private static final String VALOR_MERCADO_FECHA= "fecha";
    private static final String VALOR_MERCADO_MOTIVO= "motivo";
    private static final String VALOR_MERCADO_VALOR_MERCADO= "valorMercado";

    private static final String ERROR_ACCION = "Acción no reconocida";
    private static final String ERROR_MODIFICAR="No se ha seleccionado un historial para modificar";
    private static final String ERROR_ELIMINAR="No se ha seleccionado un historial para eliminar";
    private static final String ERROR_INTERNO = "Error interno en el servidor: ";
    private static final String ERROR_JUGADOR=  "Jugador no encontrado. Verifique que exista en la sección 'Jugadores'.";   
    private static final String ERROR_VALOR_MERCADO= "Valor mercado debe ser mayor a 0";
    private static final String ERROR_VALOR_MERCADO_INVALIDO= "Valor mercado invalido";
    private static final String ERROR_FECHA_INVALIDA= "Fecha inválida";
    private static final String ERROR_FECHA_FUTUTRA= "La fecha no puede ser futura";

    private static final String MSG_VALOR_MERCADO_INSERTADO= "Valor de mercado insertado correctamente";
    private static final String MSG_VALOR_MERCADO_MODIFICADO= "Valor de mercado modificado correctamnete";
    private static final String MSG_VALOR_MERCADO_ELIMINADO= "Valor de mercado eliminado correctamnete";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter(ACCION);

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if (accion == null || !accion.equals(OPC_MODIFICAR)) {
                List<ValorMercadoHistorial> list = dao.listarValorMercadoHistorial(); 
                request.setAttribute(LISTA_VALORES, list);
                request.getRequestDispatcher(VALOR_MERCADO_JSP).forward(request, response);
            } else if (OPC_MODIFICAR.equalsIgnoreCase(accion)) {
                String idStr = request.getParameter(VALOR_MERCADO_ID);
                if (idStr == null || idStr.isEmpty()) {
                    request.getSession().setAttribute(ERROR, ERROR_MODIFICAR);
                    response.sendRedirect(VALOR_MERCADO_CONTROLLER);
                    return;
                }

                int id = Integer.parseInt(idStr);
                ValorMercadoHistorial vm = dao.visualizarValorMercadoHistorial(id);

                if (vm != null) {
                    request.setAttribute(VALOR_MERCADO, vm);
                    request.getRequestDispatcher(EDITAR_VALOR_MERCADO).forward(request, response);
                } else {
                    request.getSession().setAttribute(ERROR, ERROR_MODIFICAR);
                    response.sendRedirect(VALOR_MERCADO_CONTROLLER);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute(ERROR, ERROR_INTERNO);
            response.sendRedirect(VALOR_MERCADO_CONTROLLER);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter(ACCION);

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if (OPC_INSERTAR.equalsIgnoreCase(accion)) {
                String nombreJugador = request.getParameter(VALOR_MERCADO_JUGADOR);
                String fechaStr = request.getParameter(VALOR_MERCADO_FECHA);
                String valorStr = request.getParameter(VALOR_MERCADO_VALOR_MERCADO);
                String motivo = request.getParameter(VALOR_MERCADO_MOTIVO);

                LocalDate fecha;

                long valorMercado;

                int jugadorId;

                try {
                    jugadorId = dao.obtenerIdPorNombreJugador(nombreJugador);
                } catch (BBDDException e) {
                    request.getSession().setAttribute(ERROR, ERROR_JUGADOR);
                            response.sendRedirect(VALOR_MERCADO_CONTROLLER);
                    return;
                }

                try {
                    fecha = LocalDate.parse(fechaStr);
                    if (fecha.isAfter(LocalDate.now())) {
                        request.getSession().setAttribute(ERROR, ERROR_FECHA_FUTUTRA);
                        response.sendRedirect(VALOR_MERCADO_CONTROLLER);
                        return;
                    }
                } catch (Exception e) {
                    request.getSession().setAttribute(ERROR, ERROR_FECHA_INVALIDA);
                    response.sendRedirect(VALOR_MERCADO_CONTROLLER);
                    return;
                }

                try {
                    valorMercado = Long.parseLong(valorStr);
                    if (valorMercado <= 0) {
                        request.getSession().setAttribute(ERROR, ERROR_VALOR_MERCADO);
                        response.sendRedirect(VALOR_MERCADO_CONTROLLER);
                        return;
                    }
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute(ERROR, ERROR_VALOR_MERCADO_INVALIDO);
                    response.sendRedirect(VALOR_MERCADO_CONTROLLER);
                    return;
                }

                ValorMercadoHistorial vm = new ValorMercadoHistorial(jugadorId, fecha, valorMercado, motivo);
                dao.insertar(vm);

                request.getSession().setAttribute(MSG, MSG_VALOR_MERCADO_INSERTADO);
                response.sendRedirect(VALOR_MERCADO_CONTROLLER);

            } else if (OPC_ACTUALIZAR.equalsIgnoreCase(accion)) {
                int idHistorial = Integer.parseInt(request.getParameter(VALOR_MERCADO_ID));
                ValorMercadoHistorial valorMercadoHistorial = dao.visualizarValorMercadoHistorial(idHistorial);
                String nombreJugador = request.getParameter(VALOR_MERCADO_JUGADOR);
                String fechaStr = request.getParameter(VALOR_MERCADO_FECHA);
                String valorStr = request.getParameter(VALOR_MERCADO_VALOR_MERCADO);
                String motivo = request.getParameter(VALOR_MERCADO_MOTIVO);

                LocalDate fecha;

                long valorMercado;
                
                int jugadorId;

                try {
                    jugadorId = dao.obtenerIdPorNombreJugador(nombreJugador);
                } catch (Exception e) {
                    request.getSession().setAttribute(ERROR, ERROR_JUGADOR);
                    request.setAttribute(VALOR_MERCADO, valorMercadoHistorial);
                    request.getRequestDispatcher(EDITAR_VALOR_MERCADO).forward(request, response);
                    return;
                }

                try {
                    fecha = LocalDate.parse(fechaStr);
                    if (fecha.isAfter(LocalDate.now())) {
                        request.getSession().setAttribute(ERROR, ERROR_FECHA_FUTUTRA);
                        request.setAttribute(VALOR_MERCADO, valorMercadoHistorial);
                        request.getRequestDispatcher(EDITAR_VALOR_MERCADO).forward(request, response);
                        return;
                    }
                } catch (Exception e) {
                    request.getSession().setAttribute(ERROR, ERROR_FECHA_INVALIDA);
                    request.setAttribute(VALOR_MERCADO, valorMercadoHistorial);
                    request.getRequestDispatcher(EDITAR_VALOR_MERCADO).forward(request, response);
                    return;
                }

                try {
                    valorMercado = Long.parseLong(valorStr);
                    if (valorMercado <= 0) {
                        request.getSession().setAttribute(ERROR, ERROR_VALOR_MERCADO);
                        request.setAttribute(VALOR_MERCADO, valorMercadoHistorial);
                        request.getRequestDispatcher(EDITAR_VALOR_MERCADO).forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute(ERROR, ERROR_VALOR_MERCADO_INVALIDO);
                    request.setAttribute(VALOR_MERCADO, valorMercadoHistorial);
                    request.getRequestDispatcher(EDITAR_VALOR_MERCADO).forward(request, response);
                    return;
                }

                ValorMercadoHistorial vm = new ValorMercadoHistorial(idHistorial, jugadorId, fecha, valorMercado,
                        motivo);
                int actualizados = dao.modificar(vm);

                if (actualizados > 0) {
                    request.getSession().setAttribute(MSG, MSG_VALOR_MERCADO_MODIFICADO);
                    response.sendRedirect(VALOR_MERCADO_CONTROLLER);
                } else {
                    request.getSession().setAttribute(ERROR , ERROR_MODIFICAR);
                    response.sendRedirect(EDITAR_VALOR_MERCADO);
                }

            } else if (OPC_ELIMINAR.equalsIgnoreCase(accion)) {
                String idStr = request.getParameter(VALOR_MERCADO_ID);
                if (idStr == null || idStr.isEmpty()) {
                    request.getSession().setAttribute(ERROR, ERROR_ELIMINAR);
                    response.sendRedirect(VALOR_MERCADO_CONTROLLER);
                    return;
                }
                int id = Integer.parseInt(idStr);

                int eliminados = dao.eliminarValorMercadoHistorial(id);

                if (eliminados > 0) {
                    request.getSession().setAttribute(MSG, MSG_VALOR_MERCADO_ELIMINADO);
                } else {
                    request.getSession().setAttribute(ERROR, ERROR_ELIMINAR);
                }
                response.sendRedirect(VALOR_MERCADO_CONTROLLER);

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ERROR_ACCION);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ERROR_INTERNO + e.getMessage());
        }
    }
}
