package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.tierno.daw.trasnferdaw.model.bbdd.Database;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDAO;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDBFactory;
import es.tierno.daw.trasnferdaw.model.entities.Competicion;

@WebServlet("/CompeticionController")
public class CompeticionController extends HttpServlet {
    private static final String ACCION= "accion";
    private static final String ERROR = "error";
    private static final String MSG= "mensaje";

    private static final String COMPETICION = "competicion";

    private static final String OPC_MODIFICAR= "modificar";
    private static final String OPC_ELIMINAR= "eliminar";
    private static final String OPC_ACTUALIZAR= "actualizar";
    private static final String OPC_INSERTAR= "insertar";

    private static final String LISTA_COMPETICIONES= "listaCompeticiones";
    private static final String COMPETICION_JSP= "/competicion/competicion.jsp";
    private static final String EDITAR_COMPETICION = "/competicion/editar_competicion.jsp";
    private static final String COMPETICION_CONTROLLER= "CompeticionController";

    private static final String COMPETICION_ID= "id_competicion";
    private static final String COMPETICION_NOMBRE= "nombre";
    private static final String COMPETICION_PAIS= "pais";
    private static final String COMPETICION_TIPO= "tipo";
    private static final String COMPETICION_ANIO= "anioCreacion";
    private static final String COMPETICION_NUM_EQUIPO= "numeroEquipos";


    private static final String ERROR_ACCION = "Acción no reconocida";
    private static final String ERROR_MODIFICAR= "No se encontró una competicion para modificar";
    private static final String ERROR_INTERNO = "Error interno en el servidor: ";
    private static final String ERROR_NUM_EQUIPO= "El numero de equipos debe ser mayor a 0";
    private static final String ERROR_ANIO_CREACION= "El año de creación debe ser mayor a 1800";
    private static final String ERROR_TIPO= "Tipo invádilo. Solo puede ser liga, copa, internacional, seleccion o amistoso.";
    private static final String ERROR_COMPETICION_ELIMINADO= "Jugador no encontrado para modificar";


    private static final String COMPETICION_LIGA= "Liga";
    private static final String COMPETICION_COPA= "Copa";
    private static final String COMPETICION_INTERNACIONAL= "Internacional";
    private static final String COMPETICION_SELECCION= "selecciones";
    private static final String COMPETICION_AMISTOSO= "Amistoso";

    private static final String MSG_COMPETICION_INSERTADO= "Competición insertada correctamente";
    private static final String MSG_COMPETICION_MODIFICADO= "Competición modificada correctamente";
    private static final String MSG_COMPETICION_ELIMINADO= "Competición eliminada correctamente";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter(ACCION);

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);
            if (accion == null || !accion.equals(OPC_MODIFICAR)) {
                List<Competicion> list = dao.listarCompeticiones(); 
                request.setAttribute(LISTA_COMPETICIONES, list);
                request.getRequestDispatcher(COMPETICION_JSP).forward(request, response);
            } else if (OPC_MODIFICAR.equalsIgnoreCase(accion)) {
                String idStr = request.getParameter(COMPETICION_ID);
                if (idStr == null || idStr.trim().isEmpty()) {
                    request.getSession().setAttribute(ERROR, ERROR_MODIFICAR);
                    response.sendRedirect(COMPETICION_CONTROLLER);
                    return;
                }

                int idCompeticion = Integer.parseInt(idStr);
                Competicion competicion = dao.visualizarCompeticion(idCompeticion);

                if (competicion != null) {
                    request.setAttribute(COMPETICION, competicion);
                    request.getRequestDispatcher(EDITAR_COMPETICION).forward(request, response);
                } else {
                    request.getSession().setAttribute(ERROR, ERROR_MODIFICAR);
                    response.sendRedirect(COMPETICION_CONTROLLER);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute(ERROR, ERROR_INTERNO);
            response.sendRedirect(COMPETICION_CONTROLLER);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter(ACCION);

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if (OPC_INSERTAR.equalsIgnoreCase(accion)) {
                String nombre = request.getParameter(COMPETICION_NOMBRE);
                String pais = request.getParameter(COMPETICION_PAIS);
                String tipo = request.getParameter(COMPETICION_TIPO);

                int numeroEquipos = Integer.parseInt(request.getParameter(COMPETICION_NUM_EQUIPO));
                int anioCreacion = Integer.parseInt(request.getParameter(COMPETICION_ANIO));;

                if (numeroEquipos <= 0) {
                    request.getSession().setAttribute(ERROR, ERROR_NUM_EQUIPO);
                    response.sendRedirect(COMPETICION_CONTROLLER);
                    return;
                }

                if (anioCreacion <= 1800) {
                    request.getSession().setAttribute(ERROR, ERROR_ANIO_CREACION);
                    response.sendRedirect(COMPETICION_CONTROLLER);
                    return;
                }

                if (tipo == null || !(tipo.equalsIgnoreCase(COMPETICION_LIGA)
                        || tipo.equalsIgnoreCase(COMPETICION_COPA)
                        || tipo.equalsIgnoreCase(COMPETICION_INTERNACIONAL)
                        || tipo.equalsIgnoreCase(COMPETICION_SELECCION)
                        || tipo.equalsIgnoreCase(COMPETICION_AMISTOSO))) {
                    request.getSession().setAttribute(ERROR,ERROR_TIPO);
                    response.sendRedirect(COMPETICION_CONTROLLER);
                    return;
                }

                Competicion competicion = new Competicion(nombre, pais, tipo, numeroEquipos, anioCreacion);
                dao.insertar(competicion);

                request.getSession().setAttribute(MSG, MSG_COMPETICION_INSERTADO);
                response.sendRedirect(COMPETICION_CONTROLLER);

            } else if (OPC_ELIMINAR.equalsIgnoreCase(accion)) {
                int idCompeticion = Integer.parseInt(request.getParameter(COMPETICION_ID));
                int registrosEliminados = dao.eliminarCompeticion(idCompeticion);

                if (registrosEliminados > 0) {
                    request.getSession().setAttribute(MSG, MSG_COMPETICION_ELIMINADO);
                } else {
                    request.getSession().setAttribute(ERROR, ERROR_COMPETICION_ELIMINADO);
                }
                response.sendRedirect(COMPETICION_CONTROLLER);

            } else if (OPC_ACTUALIZAR.equalsIgnoreCase(accion)) {
                int idCompeticion = Integer.parseInt(request.getParameter(COMPETICION_ID));
                String nombre = request.getParameter(COMPETICION_NOMBRE);
                String pais = request.getParameter(COMPETICION_PAIS);
                String tipo = request.getParameter(COMPETICION_TIPO);

                int numeroEquipos = Integer.parseInt(request.getParameter(COMPETICION_NUM_EQUIPO));
                int anioCreacion = Integer.parseInt(request.getParameter(COMPETICION_ANIO));
                

                if (numeroEquipos <= 0) {
                    request.getSession().setAttribute(ERROR, ERROR_NUM_EQUIPO);
                    Competicion competicion = dao.visualizarCompeticion(idCompeticion);
                    request.setAttribute(COMPETICION, competicion);
                    request.getRequestDispatcher(EDITAR_COMPETICION).forward(request, response);
                    return;
                }

                if (tipo == null || !(tipo.equalsIgnoreCase(COMPETICION_LIGA)
                        || tipo.equalsIgnoreCase(COMPETICION_COPA)
                        || tipo.equalsIgnoreCase(COMPETICION_INTERNACIONAL)
                        || tipo.equalsIgnoreCase(COMPETICION_SELECCION)
                        || tipo.equalsIgnoreCase(COMPETICION_AMISTOSO))) {
                            request.getSession().setAttribute(ERROR, ERROR_TIPO);

                            Competicion competicion = dao.visualizarCompeticion(idCompeticion);
                            request.setAttribute(COMPETICION, competicion);
                            request.getRequestDispatcher(EDITAR_COMPETICION).forward(request, response);
                            return;
                }

                if (anioCreacion <= 1800) {
                    request.getSession().setAttribute(ERROR, ERROR_ANIO_CREACION);
                    Competicion competicion = dao.visualizarCompeticion(idCompeticion);
                    request.setAttribute(COMPETICION, competicion);
                    request.getRequestDispatcher(EDITAR_COMPETICION).forward(request, response);
                    return;
                }

                Competicion competicion = new Competicion(idCompeticion, nombre, pais, tipo, numeroEquipos,
                        anioCreacion);
                dao.modificar(competicion);

                request.getSession().setAttribute(MSG, MSG_COMPETICION_MODIFICADO);
                response.sendRedirect(COMPETICION_CONTROLLER);

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ERROR_ACCION);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ERROR_INTERNO + e.getMessage());
        }
    }
}
