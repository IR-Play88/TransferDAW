package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.tierno.daw.trasnferdaw.model.bbdd.Database;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDAO;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDBFactory;
import es.tierno.daw.trasnferdaw.model.entities.Jugador;

@WebServlet("/JugadorController")
public class JugadorController extends HttpServlet {
    
    private static final String ACCION= "accion";
    private static final String ERROR = "error";
    private static final String MSG= "mensaje";

    private static final String JUGADOR = "jugador";

    private static final String OPC_MODIFICAR= "modificar";
    private static final String OPC_ELIMINAR= "eliminar";
    private static final String OPC_ACTUALIZAR= "actualizar";
    private static final String OPC_INSERTAR= "insertar";

    private static final String LISTA_JUGADORES= "listaJugadores";
    private static final String JUGADOR_JSP= "/jugador/jugador.jsp";
    private static final String EDITAR_JUGADOR = "/jugador/editar_jugador.jsp";
    private static final String JUGADOR_CONTROLLER= "JugadorController";

    private static final String JUG_ID= "id_jugador";
    private static final String JUG_NOMBRE= "nombre";
    private static final String JUG_ALIAS= "alias";
    private static final String JUG_PESO= "peso";
    private static final String JUG_ALTURA= "altura";
    private static final String JUG_NACIONALIDAD= "nacionalidad";
    private static final String JUG_VALOR_MERCADO= "valor_mercado";
    private static final String JUG_REPRESENTANTE= "representante";
    private static final String JUG_POSICION= "posicion";
    private static final String JUG_SELECCION= "seleccion";
    private static final String JUG_PIE_DOMINANTE= "pie_dominante";
    private static final String JUG_FECHA_NACIMIENTO= "fecha_nacimiento";

    private static final String ERROR_ACCION = "Acción no reconocida";
    private static final String ERROR_MODIFICAR= "No se encontró un jugador para modificar";
    private static final String ERROR_INTERNO = "Error interno en el servidor: ";
    private static final String ERROR_ALTURA= "Altura debe ser mayor a 0";
    private static final String ERROR_PESO= "Peso debe ser mayor a 0";
    private static final String ERROR_VALOR_MERCADO= "Valor mercado debe ser mayor a 0";
    private static final String ERROR_FECHA_NACIMIENTO_INVALIDA= "Fecha de nacimiento inválida";
    private static final String ERROR_FECHA_NACIMIENTO_FUTUTRA= "La fecha de nacimiento no puede ser futura";
    private static final String ERROR_JUG_ELIMINADO= "Jugador no encontrado para modificar";
    private static final String ERROR_PIE= "Pie invádilo. Solo puede ser derecho, izquierdo o ambidiestro.";
    private static final String ERROR_POSICION=  "Posición inválida. Solo puede ser delantero, mediocampista, defensa o portero.";

    private static final String POS_DELANTERO= "delantero";
    private static final String POS_MEDIOCAMPISTA= "mediocampista";
    private static final String POS_DEFENSA= "defensa";
    private static final String POS_PORTERO= "portero";

    private static final String PIE_DERECHO= "derecho";
    private static final String PIE_IZQUIERDO= "izquierdo";
    private static final String PIE_AMBIDIESTRO= "ambidiestro";

    private static final String MSG_JUG_INSERTADO= "Jugador insertado correctamnete";
    private static final String MSG_JUG_MODIFICADO= "Jugador modificado correctamnete";
    private static final String MSG_JUG_ELIMINADO= "Jugador eliminado correctamnete";

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter(ACCION);

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if (accion == null || !accion.equals(OPC_MODIFICAR)) {
                List<Jugador> list = dao.listarJugadores();  
                request.setAttribute(LISTA_JUGADORES, list);
                request.getRequestDispatcher(JUGADOR_JSP).forward(request, response);
            } else if(OPC_MODIFICAR.equalsIgnoreCase(accion)) {
                String idStr = request.getParameter(JUG_ID);
                if (idStr == null || idStr.trim().isEmpty()) {
                    request.getSession().setAttribute(ERROR, ERROR_MODIFICAR);
                    response.sendRedirect(JUGADOR_CONTROLLER);
                    return;
                }

                int idJugador = Integer.parseInt(idStr);
                Jugador jugador = dao.visualizarJugador(idJugador);

                if (jugador != null) {
                    request.setAttribute(JUGADOR, jugador);
                    request.getRequestDispatcher(EDITAR_JUGADOR).forward(request, response);
                } else {
                    request.getSession().setAttribute(ERROR, ERROR_MODIFICAR);
                    response.sendRedirect(JUGADOR_CONTROLLER);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute(ERROR, ERROR_INTERNO);
            response.sendRedirect(JUGADOR_CONTROLLER);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter(ACCION);
    
        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);
    
            if (OPC_INSERTAR.equalsIgnoreCase(accion)) {
                String nombre = request.getParameter(JUG_NOMBRE);
                String alias = request.getParameter(JUG_ALIAS);
                String fechaNacimientoStr = request.getParameter(JUG_FECHA_NACIMIENTO);
                String nacionalidad = request.getParameter(JUG_NACIONALIDAD);
                String pieDominante = request.getParameter(JUG_PIE_DOMINANTE);
                String posicion = request.getParameter(JUG_POSICION);
                String representante = request.getParameter(JUG_REPRESENTANTE);
                String seleccion = request.getParameter(JUG_SELECCION);
                float altura = Float.parseFloat(request.getParameter(JUG_ALTURA));
                float peso = Float.parseFloat(request.getParameter(JUG_PESO));
                float valorMercado = Float.parseFloat(request.getParameter(JUG_VALOR_MERCADO));
                LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
    
                if (altura <= 0) {
                    request.getSession().setAttribute(ERROR, ERROR_ALTURA);
                    response.sendRedirect(JUGADOR_CONTROLLER);
                    return;
                }

                if (peso <= 0) {
                    request.getSession().setAttribute(ERROR, ERROR_PESO);
                    response.sendRedirect(JUGADOR_CONTROLLER);
                    return;
                }

                if (valorMercado <= 0) {
                    request.getSession().setAttribute(ERROR, ERROR_VALOR_MERCADO);
                    response.sendRedirect(JUGADOR_CONTROLLER);
                    return;
                }
    
                try {
                    if (fechaNacimiento.isAfter(LocalDate.now())) {
                        request.getSession().setAttribute(ERROR, ERROR_FECHA_NACIMIENTO_FUTUTRA);
                        response.sendRedirect(JUGADOR_CONTROLLER);
                        return;
                    }
                } catch (Exception e) {
                    request.getSession().setAttribute(ERROR, ERROR_FECHA_NACIMIENTO_INVALIDA);
                    response.sendRedirect(JUGADOR_CONTROLLER);
                    return;
                }
                if (pieDominante == null || !(pieDominante.equalsIgnoreCase(PIE_DERECHO)
                        || pieDominante.equalsIgnoreCase(PIE_IZQUIERDO)
                        || pieDominante.equalsIgnoreCase(PIE_AMBIDIESTRO))) {
                    request.getSession().setAttribute(ERROR, ERROR_PIE);
                    response.sendRedirect(JUGADOR_CONTROLLER);
                    return;
                }
    
                if (posicion == null || !(posicion.equalsIgnoreCase(POS_DELANTERO)
                        || posicion.equalsIgnoreCase(POS_MEDIOCAMPISTA)
                        || posicion.equalsIgnoreCase(POS_DEFENSA)
                        || posicion.equalsIgnoreCase(POS_PORTERO))) {
                    request.getSession().setAttribute(ERROR, ERROR_POSICION);
                    response.sendRedirect(JUGADOR_CONTROLLER);
                    return;
                }
    
                Jugador jugador = new Jugador(nombre, alias, fechaNacimiento, nacionalidad, altura, peso, pieDominante,
                        valorMercado, posicion, representante, seleccion);
                dao.insertar(jugador);
    
                request.getSession().setAttribute(MSG, MSG_JUG_INSERTADO);
                response.sendRedirect(JUGADOR_CONTROLLER);
    
            } else if (OPC_ELIMINAR.equalsIgnoreCase(accion)) {
                int idJugador = Integer.parseInt(request.getParameter(JUG_ID));
                int registrosEliminados = dao.eliminarJugador(idJugador);
    
                if (registrosEliminados > 0) {
                    request.getSession().setAttribute(MSG, MSG_JUG_ELIMINADO);
                } else {
                    request.getSession().setAttribute(ERROR, ERROR_JUG_ELIMINADO);
                }
                response.sendRedirect(JUGADOR_CONTROLLER);
    
            } else if (OPC_ACTUALIZAR.equalsIgnoreCase(accion)) {
                int idJugador = Integer.parseInt(request.getParameter(JUG_ID));
                Jugador jugador = dao.visualizarJugador(idJugador);
                String nombre = request.getParameter(JUG_NOMBRE);
                String alias = request.getParameter(JUG_ALIAS);
                String fechaNacimientoStr = request.getParameter(JUG_FECHA_NACIMIENTO);
                String nacionalidad = request.getParameter(JUG_NACIONALIDAD);
                float altura = Float.parseFloat(request.getParameter(JUG_ALTURA));
                float peso = Float.parseFloat(request.getParameter(JUG_PESO));
                float valorMercado = Float.parseFloat(request.getParameter(JUG_VALOR_MERCADO));
                LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
                String pieDominante = request.getParameter(JUG_PIE_DOMINANTE);
                String posicion = request.getParameter(JUG_POSICION);
                String representante = request.getParameter(JUG_REPRESENTANTE);
                String seleccion = request.getParameter(JUG_SELECCION);
    
                if (altura <= 0) {
                    request.getSession().setAttribute(ERROR, ERROR_ALTURA);
                    request.setAttribute(JUGADOR, jugador);
                    request.getRequestDispatcher(EDITAR_JUGADOR).forward(request, response);
                    return;
                }

                if (peso <= 0) {
                    request.getSession().setAttribute(ERROR, ERROR_PESO);
                    request.setAttribute(JUGADOR, jugador);
                    request.getRequestDispatcher(EDITAR_JUGADOR).forward(request, response);
                    return;
                }

                if (valorMercado <= 0) {
                    request.getSession().setAttribute(ERROR, ERROR_VALOR_MERCADO);
                    request.setAttribute(JUGADOR, jugador);
                    request.getRequestDispatcher(EDITAR_JUGADOR).forward(request, response);
                    return;
                }

                try {
                    if (fechaNacimiento.isAfter(LocalDate.now())) {
                        request.getSession().setAttribute(ERROR, ERROR_FECHA_NACIMIENTO_FUTUTRA);
                        request.setAttribute(JUGADOR, jugador);
                        request.getRequestDispatcher(EDITAR_JUGADOR).forward(request, response);
                        return;
                    }
                } catch (Exception e) {
                    request.getSession().setAttribute(ERROR, ERROR_FECHA_NACIMIENTO_INVALIDA);
                    request.setAttribute(JUGADOR, jugador);
                    request.getRequestDispatcher(EDITAR_JUGADOR).forward(request, response);
                    return;
                }

                if (pieDominante == null || !(pieDominante.equalsIgnoreCase(PIE_DERECHO)
                        || pieDominante.equalsIgnoreCase(PIE_IZQUIERDO)
                        || pieDominante.equalsIgnoreCase(PIE_AMBIDIESTRO))) {
                    request.getSession().setAttribute(ERROR, ERROR_PIE);
                            request.setAttribute(JUGADOR, jugador);
                            request.getRequestDispatcher(EDITAR_JUGADOR).forward(request, response);
                    return;
                }
    
                if (posicion == null || !(posicion.equalsIgnoreCase(POS_DELANTERO)
                        || posicion.equalsIgnoreCase(POS_MEDIOCAMPISTA)
                        || posicion.equalsIgnoreCase(POS_DEFENSA)
                        || posicion.equalsIgnoreCase(POS_PORTERO))) {
                    request.getSession().setAttribute(ERROR, ERROR_POSICION);
                    request.setAttribute(JUGADOR, jugador);
                    request.getRequestDispatcher(EDITAR_JUGADOR).forward(request, response);
                    return;
                }
    
                jugador = new Jugador(idJugador, nombre, alias, fechaNacimiento, nacionalidad, altura, peso,
                        pieDominante, valorMercado, posicion, representante, seleccion);
    
                dao.modificar(jugador);
    
                request.getSession().setAttribute(MSG, MSG_JUG_MODIFICADO);
                response.sendRedirect(JUGADOR_CONTROLLER);
    
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ERROR_ACCION);
            }
    
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ERROR_INTERNO + e.getMessage());
        }
    }
    
}
