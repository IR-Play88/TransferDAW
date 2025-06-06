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
import es.tierno.daw.trasnferdaw.model.entities.Traspaso;
import es.tierno.daw.trasnferdaw.model.exception.BBDDException;

@WebServlet("/TraspasoController")
public class TraspasoController extends HttpServlet {
    private static final String ACCION= "accion";
    private static final String ERROR = "error";
    private static final String MSG= "mensaje";

    private static final String TRASPASO = "traspaso";

    private static final String OPC_MODIFICAR= "modificar";
    private static final String OPC_ELIMINAR= "eliminar";
    private static final String OPC_ACTUALIZAR= "actualizar";
    private static final String OPC_INSERTAR= "insertar";

    private static final String LISTA_TRASPASOS= "listaTraspasos";
    private static final String TRASPASO_JSP= "/traspaso/traspaso.jsp";
    private static final String EDITAR_TRASPASO = "/traspaso/editar_traspaso.jsp";
    private static final String TRASPASO_CONTROLLER= "TraspasoController";

    private static final String TRASPASO_ID= "id_traspaso";
    private static final String TRASPASO_JUGADOR_ID= "jugadorId";
    private static final String TRASPASO_EQUIPO_ORIGEN_ID= "equipoOrigenId";
    private static final String TRASPASO_EQUIPO_DESTINO_ID= "equipoDestinoId";
    private static final String TRASPASO_TEMPORADA_ID= "temporadaId";
    private static final String TRASPASO_CANTIDAD= "cantidad";
    private static final String TRASPASO_FECHA_TRASPASO= "fechaTraspaso";
    private static final String TRASPASO_TIPO= "tipo";
    private static final String TRASPASO_TIPO_COMPRA= "compra";
    private static final String TRASPASO_TIPO_LIBRE= "libre";
    private static final String TRASPASO_TIPO_CESION= "cesion";
    private static final String TRASPASO_TIPO_FIN= "fin de contrato";
   
    private static final String ERROR_ACCION = "Acción no reconocida";
    private static final String ERROR_MODIFICAR= "No se encontró un traspaso para modificar";
    private static final String ERROR_ELIMINAR= "No se encontró un traspaso para eliminar";
    private static final String ERROR_JUGADOR= "Jugador no encontrado. Verifique que exista en la sección 'Jugadores'.";
    private static final String ERROR_EQUIPO_ORIGEN= "Equipo origen no encontrado. Verifique que exista en la sección 'Equipos'.";
    private static final String ERROR_EQUIPO_DESTINO= "Equipo destino no encontrado. Verifique que exista en la sección 'Equipos'.";
    private static final String ERROR_TEMPORADA= "Temporada no encontrado. Verifique que exista en la sección 'Temporadas'.";
    private static final String ERROR_INTERNO = "Error interno en el servidor: ";
    private static final String ERROR_CANTIDAD= "La cantidad debe ser mayor que cero";
    private static final String ERROR_CANTIDAD_INVALIDA= "Cantidad invalida";
    private static final String ERROR_FECHA_INVALIDA= "Fecha de nacimiento inválida";
    private static final String ERROR_FECHA_FUTUTRA= "La fecha de traspaso no puede ser futura";
    private static final String ERROR_TIPO=  "Tipo inválida. Solo puede ser compra, libre, cesión o fin de contrato.";

    private static final String MSG_TRASPASO_INSERTADO= "Traspaso insertado correctamnete";
    private static final String MSG_TRASPASO_MODIFICADO= "Traspaso modificado correctamnete";
    private static final String MSG_TRASPASO_ELIMINADO= "Traspaso eliminado correctamnete";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter(ACCION);

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

                       if (accion == null || !accion.equals(OPC_MODIFICAR)) {
                List<Traspaso> list = dao.listarTraspasos();  
                request.setAttribute(LISTA_TRASPASOS, list);
                request.getRequestDispatcher(TRASPASO_JSP).forward(request, response);
            } else if(OPC_MODIFICAR.equalsIgnoreCase(accion)) {
                String idStr = request.getParameter(TRASPASO_ID);
                if (idStr == null || idStr.isEmpty()) {
                    request.getSession().setAttribute(ERROR, ERROR_MODIFICAR);
                    response.sendRedirect(TRASPASO_CONTROLLER);
                    return;
                }

                int idTraspaso = Integer.parseInt(idStr);
                Traspaso traspaso = dao.visualizarTraspaso(idTraspaso);

                if (traspaso != null) {
                    request.setAttribute(TRASPASO, traspaso);
                    request.getRequestDispatcher(EDITAR_TRASPASO).forward(request, response);
                } else {
                    request.getSession().setAttribute(ERROR, ERROR_MODIFICAR);
                    response.sendRedirect(TRASPASO_CONTROLLER);
                }

            } 
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute(ERROR, ERROR_INTERNO);
            response.sendRedirect(TRASPASO_CONTROLLER);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter(ACCION);

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if (OPC_INSERTAR.equalsIgnoreCase(accion)) {
                String nombreJugador = request.getParameter(TRASPASO_JUGADOR_ID);
                String nombreEquipoOrigen = request.getParameter(TRASPASO_EQUIPO_ORIGEN_ID);
                String nombreEquipoDestino = request.getParameter(TRASPASO_EQUIPO_DESTINO_ID);
                String nombreTemporada = request.getParameter(TRASPASO_TEMPORADA_ID);
                String cantidadStr = request.getParameter(TRASPASO_CANTIDAD);
                String fechaStr = request.getParameter(TRASPASO_FECHA_TRASPASO);
                String tipo = request.getParameter(TRASPASO_TIPO);

                int jugadorId;
                int equipoOrigenId;
                int equipoDestinoId;
                int temporadaId;
                float cantidad;
                LocalDate fechaTraspaso;

                try {
                    jugadorId = dao.obtenerIdPorNombreJugador(nombreJugador);
                } catch (BBDDException e) {
                    request.getSession().setAttribute(ERROR, ERROR_JUGADOR);
                    response.sendRedirect(TRASPASO_CONTROLLER);
                    return;
                }

                try {
                    equipoOrigenId = dao.obtenerIdPorNombreEquipo(nombreEquipoOrigen);
                } catch (BBDDException e) {
                    request.getSession().setAttribute(ERROR, ERROR_EQUIPO_ORIGEN);
                    response.sendRedirect(TRASPASO_CONTROLLER);
                    return;
                }

                try {
                    equipoDestinoId = dao.obtenerIdPorNombreEquipo(nombreEquipoDestino);
                } catch (BBDDException e) {
                    request.getSession().setAttribute(ERROR, ERROR_EQUIPO_DESTINO);
                    response.sendRedirect(TRASPASO_CONTROLLER);
                    return;
                }

                try {
                    temporadaId = dao.obtenerIdPorNombreTemporada(nombreTemporada);
                } catch (BBDDException e) {
                    request.getSession().setAttribute(ERROR, ERROR_TEMPORADA);
                    response.sendRedirect(TRASPASO_CONTROLLER);
                    return;
                }

                try {
                    cantidad = Float.parseFloat(cantidadStr);
                    if (cantidad <= 0) {
                        request.getSession().setAttribute(ERROR, ERROR_CANTIDAD);
                        response.sendRedirect(TRASPASO_CONTROLLER);
                        return;
                    }
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute(ERROR, ERROR_CANTIDAD_INVALIDA);
                    response.sendRedirect(TRASPASO_CONTROLLER);
                    return;
                }

                try {
                    fechaTraspaso = LocalDate.parse(fechaStr);
                    if (fechaTraspaso.isAfter(LocalDate.now())) {
                        request.getSession().setAttribute(ERROR, ERROR_FECHA_FUTUTRA);
                        response.sendRedirect(TRASPASO_CONTROLLER);
                        return;
                    }
                } catch (Exception e) {
                    request.getSession().setAttribute(ERROR , ERROR_FECHA_INVALIDA);
                    response.sendRedirect(TRASPASO_CONTROLLER);
                    return;
                }

                if (tipo == null || !(tipo.equalsIgnoreCase(TRASPASO_TIPO_COMPRA) || tipo.equalsIgnoreCase(TRASPASO_TIPO_CESION)
                        || tipo.equalsIgnoreCase(TRASPASO_TIPO_LIBRE) || tipo.equalsIgnoreCase(TRASPASO_TIPO_FIN))) {
                    request.getSession().setAttribute(ERROR, ERROR_TIPO);
                    response.sendRedirect(TRASPASO_CONTROLLER);
                    return;
                }

                Traspaso traspaso = new Traspaso(jugadorId, equipoOrigenId, equipoDestinoId, temporadaId,
                        fechaTraspaso, cantidad, tipo);
                dao.insertar(traspaso);

                request.getSession().setAttribute(MSG, MSG_TRASPASO_INSERTADO);
                response.sendRedirect(TRASPASO_CONTROLLER);

            } else if (OPC_ACTUALIZAR.equalsIgnoreCase(accion)) {
                int idTraspaso = Integer.parseInt(request.getParameter(TRASPASO_ID));
                Traspaso traspasoActual = dao.visualizarTraspaso(idTraspaso);

                String nombreJugador = request.getParameter(TRASPASO_JUGADOR_ID);
                String nombreEquipoOrigen = request.getParameter(TRASPASO_EQUIPO_ORIGEN_ID);
                String nombreEquipoDestino = request.getParameter(TRASPASO_EQUIPO_DESTINO_ID);
                String nombreTemporada = request.getParameter(TRASPASO_TEMPORADA_ID);
                String cantidadStr = request.getParameter(TRASPASO_CANTIDAD);
                String fechaStr = request.getParameter(TRASPASO_FECHA_TRASPASO);
                String tipo = request.getParameter(TRASPASO_TIPO);

                int jugadorId;
                int equipoOrigenId;
                int equipoDestinoId;
                int temporadaId;

                float cantidad;

                LocalDate fechaTraspaso;

                try {
                    jugadorId = dao.obtenerIdPorNombreJugador(nombreJugador);
                } catch (BBDDException e) {
                    request.getSession().setAttribute(ERROR, ERROR_JUGADOR);
                    request.setAttribute(TRASPASO, traspasoActual);
                    request.getRequestDispatcher(EDITAR_TRASPASO).forward(request, response);
                    return;
                }

                try {
                    equipoOrigenId = dao.obtenerIdPorNombreEquipo(nombreEquipoOrigen);
                } catch (BBDDException e) {
                    request.getSession().setAttribute(ERROR, ERROR_EQUIPO_ORIGEN);
                    request.setAttribute(TRASPASO, traspasoActual);
                    request.getRequestDispatcher(EDITAR_TRASPASO).forward(request, response);
                    return;
                }

                try {
                    equipoDestinoId = dao.obtenerIdPorNombreEquipo(nombreEquipoDestino);
                } catch (BBDDException e) {
                    request.getSession().setAttribute(ERROR, ERROR_EQUIPO_DESTINO);
                    request.setAttribute(TRASPASO, traspasoActual);
                    request.getRequestDispatcher(EDITAR_TRASPASO).forward(request, response);
                    return;
                }

                try {
                    temporadaId = dao.obtenerIdPorNombreTemporada(nombreTemporada);
                } catch (BBDDException e) {
                    request.getSession().setAttribute(ERROR, ERROR_TEMPORADA);
                    request.setAttribute(TRASPASO, traspasoActual);
                    request.getRequestDispatcher(EDITAR_TRASPASO).forward(request, response);
                    return;
                }

                try {
                    cantidad = Float.parseFloat(cantidadStr);
                    if (cantidad <= 0) {
                        request.getSession().setAttribute(ERROR, ERROR_CANTIDAD);
                        request.setAttribute(TRASPASO , traspasoActual);
                        request.getRequestDispatcher(EDITAR_TRASPASO).forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute(ERROR, ERROR_CANTIDAD_INVALIDA);
                    request.setAttribute(TRASPASO, traspasoActual);
                    request.getRequestDispatcher(EDITAR_TRASPASO).forward(request, response);
                    return;
                }

                try {
                    fechaTraspaso = LocalDate.parse(fechaStr);
                    if (fechaTraspaso.isAfter(LocalDate.now())) {
                        request.getSession().setAttribute(ERROR, ERROR_FECHA_FUTUTRA);
                        request.setAttribute(TRASPASO, traspasoActual);
                        request.getRequestDispatcher(EDITAR_TRASPASO).forward(request, response);
                        return;
                    }
                } catch (Exception e) {
                    request.getSession().setAttribute(ERROR, ERROR_FECHA_INVALIDA);
                    request.setAttribute(TRASPASO, traspasoActual);
                    request.getRequestDispatcher(EDITAR_TRASPASO).forward(request, response);
                    return;
                }

                if (tipo == null || !(tipo.equalsIgnoreCase(TRASPASO_TIPO_COMPRA) || tipo.equalsIgnoreCase(TRASPASO_TIPO_CESION)
                        || tipo.equalsIgnoreCase(TRASPASO_TIPO_LIBRE) || tipo.equalsIgnoreCase(TRASPASO_TIPO_FIN))) {
                    request.getSession().setAttribute(ERROR, ERROR_TIPO);
                    request.setAttribute(TRASPASO, traspasoActual);
                    request.getRequestDispatcher(EDITAR_TRASPASO).forward(request, response);
                    return;
                }

                Traspaso nuevoTraspaso = new Traspaso(idTraspaso, jugadorId, equipoOrigenId, equipoDestinoId,
                        temporadaId, fechaTraspaso, cantidad, tipo);
                int actualizados = dao.modificar(nuevoTraspaso);

                if (actualizados > 0) {
                    request.getSession().setAttribute(MSG, MSG_TRASPASO_MODIFICADO);
                    response.sendRedirect(TRASPASO_CONTROLLER);
                } else {
                    request.getSession().setAttribute(ERROR, ERROR_MODIFICAR);
                    response.sendRedirect(EDITAR_TRASPASO);
                }

            } else if (OPC_ELIMINAR.equalsIgnoreCase(accion)) {
                String idStr = request.getParameter(TRASPASO_ID);
                if (idStr == null || idStr.isEmpty()) {
                    request.getSession().setAttribute(ERROR, ERROR_ELIMINAR);
                    response.sendRedirect(TRASPASO_CONTROLLER);
                    return;
                }

                int id = Integer.parseInt(idStr);
                int eliminados = dao.eliminarTraspaso(id);

                if (eliminados > 0) {
                    request.getSession().setAttribute(MSG, MSG_TRASPASO_ELIMINADO);
                } else {
                    request.getSession().setAttribute(ERROR, ERROR_ELIMINAR);
                }
                response.sendRedirect(TRASPASO_CONTROLLER);

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ERROR_ACCION);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ERROR_INTERNO + e.getMessage());
        }
    }
}
