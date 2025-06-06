package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.tierno.daw.trasnferdaw.model.bbdd.Database;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDAO;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDBFactory;
import es.tierno.daw.trasnferdaw.model.entities.EquipoCompeticion;
import es.tierno.daw.trasnferdaw.model.exception.BBDDException;

@WebServlet("/EquipoCompeticionController")
public class EquipoCompeticionController extends HttpServlet {
    private static final String ACCION= "accion";
    private static final String ERROR = "error";
    private static final String MSG= "mensaje";

    private static final String EQUIPO_COMPETICION = "equipoCompeticion";

    private static final String OPC_MODIFICAR= "modificar";
    private static final String OPC_ELIMINAR= "eliminar";
    private static final String OPC_ACTUALIZAR= "actualizar";
    private static final String OPC_INSERTAR= "insertar";

    private static final String LISTA_EQUIPO_COMPETICIONES= "listaEquipoCompeticiones";
    private static final String EQUIPO_COMPETICION_JSP= "/equipo_competicion/equipo_competicion.jsp";
    private static final String EDITAR_EQUIPO_COMPETICION = "/equipo_competicion/editar_equipo_competicion.jsp";
    private static final String EQUIPO_COMPETICION_CONTROLLER= "EquipoCompeticionController";

    private static final String EQUIPO_COMPETICION_EQUIPO_ID= "equipoId";
    private static final String EQUIPO_COMPETICION_COMPETICION_ID= "competicionId";
    private static final String EQUIPO_COMPETICION_TEMPORADA_ID= "temporadaId";
    private static final String EQUIPO_COMPETICION_NOMBRE_EQUIPO= "nombreEquipo";
    private static final String EQUIPO_COMPETICION_NOMBRE_COMPETICION= "nombreCompeticion";
    private static final String EQUIPO_COMPETICION_NOMBRE_TEMPORADA= "nombreTemporada";
    private static final String EQUIPO_COMPETICION_POSICION= "posicion";

    private static final String ERROR_ACCION = "Acción no reconocida";
    private static final String ERROR_MODIFICAR= "No se encontró un equipo en una competicion para modificar";
    private static final String ERROR_ELIMINAR= "No se encontró un equipo en una competicion para eliminar";
    private static final String ERROR_INTERNO = "Error interno en el servidor: ";
    private static final String ERROR_EQUIPO= "Equipo no encontrado. Verifique que exista en la sección 'Equipos'.";
    private static final String ERROR_COMPETICION= "Competicion no encontrado. Verifique que exista en la sección 'Competiciones'.";
    private static final String ERROR_TEMPORADA= "Temporada no encontrado. Verifique que exista en la sección 'Temporadas'.";

    private static final String MSG_EQUIPO_COMPETICION_INSERTADO= "Equipo en una competición añadido correctamente";
    private static final String MSG_EQUIPO_COMPETICION_MODIFICADO= "Equipo en una competición modificado correctamente";
    private static final String MSG_EQUIPO_COMPETICION_ELIMINADO= "Equipo en una competición eliminado correctamente";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter(ACCION);

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

             if (accion == null || !accion.equals(OPC_MODIFICAR)) {
                List<EquipoCompeticion> list = dao.listarEquiposCompeticion();  
                request.setAttribute(LISTA_EQUIPO_COMPETICIONES, list);
                request.getRequestDispatcher(EQUIPO_COMPETICION_JSP).forward(request, response);
            } else if (OPC_MODIFICAR.equalsIgnoreCase(accion)) {
                int equipoId = Integer.parseInt(request.getParameter(EQUIPO_COMPETICION_EQUIPO_ID));
                int competicionId = Integer.parseInt(request.getParameter(EQUIPO_COMPETICION_COMPETICION_ID));
                int temporadaId = Integer.parseInt(request.getParameter(EQUIPO_COMPETICION_TEMPORADA_ID));

                EquipoCompeticion ec = dao.visualizarEquipoCompeticion(equipoId, competicionId, temporadaId);

                if (ec != null) {
                    request.setAttribute(EQUIPO_COMPETICION, ec);
                    RequestDispatcher dispatcher = request.getRequestDispatcher(EDITAR_EQUIPO_COMPETICION);
                    dispatcher.forward(request, response);
                } else {
                    request.getSession().setAttribute(ERROR, ERROR_MODIFICAR);
                    response.sendRedirect(EQUIPO_COMPETICION_CONTROLLER);
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ERROR_ACCION);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute(ERROR, ERROR_INTERNO);
            response.sendRedirect(EQUIPO_COMPETICION_CONTROLLER);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter(ACCION);

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if (OPC_INSERTAR.equalsIgnoreCase(accion)) {
                String nombreEquipo = request.getParameter(EQUIPO_COMPETICION_NOMBRE_EQUIPO);
                String nombreCompeticion = request.getParameter(EQUIPO_COMPETICION_NOMBRE_COMPETICION);
                String nombreTemporada = request.getParameter(EQUIPO_COMPETICION_NOMBRE_TEMPORADA);
                String posicion = request.getParameter(EQUIPO_COMPETICION_POSICION);

                int equipoId;
                int competicionId;
                int temporadaId;

                try {
                    equipoId = dao.obtenerIdPorNombreEquipo(nombreEquipo);
                } catch (BBDDException e) {
                    request.getSession().setAttribute(ERROR, ERROR_EQUIPO);
                    response.sendRedirect(EQUIPO_COMPETICION_CONTROLLER);
                    return;
                }

                try {
                    competicionId = dao.obtenerIdPorNombreCompeticion(nombreCompeticion);
                } catch (BBDDException e) {
                    request.getSession().setAttribute(ERROR, ERROR_COMPETICION);
                    response.sendRedirect(EQUIPO_COMPETICION_CONTROLLER);
                    return;
                }

                try {
                    temporadaId = dao.obtenerIdPorNombreTemporada(nombreTemporada);
                } catch (BBDDException e) {
                    request.getSession().setAttribute(ERROR, ERROR_TEMPORADA);
                    response.sendRedirect(EQUIPO_COMPETICION_CONTROLLER);
                    return;
                }
                

                EquipoCompeticion ec = new EquipoCompeticion(equipoId, competicionId, temporadaId, posicion);
                dao.insertar(ec);

                request.getSession().setAttribute(MSG, MSG_EQUIPO_COMPETICION_INSERTADO);
                response.sendRedirect(EQUIPO_COMPETICION_CONTROLLER);

            } else if (OPC_ELIMINAR.equalsIgnoreCase(accion)) {
                int equipoId = Integer.parseInt(request.getParameter(EQUIPO_COMPETICION_EQUIPO_ID));
                int competicionId = Integer.parseInt(request.getParameter(EQUIPO_COMPETICION_COMPETICION_ID));
                int temporadaId = Integer.parseInt(request.getParameter(EQUIPO_COMPETICION_TEMPORADA_ID));

                int eliminados = dao.eliminarEquipoCompeticion(equipoId, competicionId, temporadaId);

                if (eliminados > 0) {
                    request.getSession().setAttribute(MSG, MSG_EQUIPO_COMPETICION_ELIMINADO);
                } else {
                    request.getSession().setAttribute(ERROR, ERROR_ELIMINAR);
                }
                response.sendRedirect(EQUIPO_COMPETICION_CONTROLLER);

            } else if (OPC_ACTUALIZAR.equalsIgnoreCase(accion)) {
                int equipoId = Integer.parseInt(request.getParameter(EQUIPO_COMPETICION_EQUIPO_ID));
                int competicionId = Integer.parseInt(request.getParameter(EQUIPO_COMPETICION_COMPETICION_ID));
                int temporadaId = Integer.parseInt(request.getParameter(EQUIPO_COMPETICION_TEMPORADA_ID));
                String posicion = request.getParameter(EQUIPO_COMPETICION_POSICION);
               

                EquipoCompeticion ec = new EquipoCompeticion(equipoId, competicionId, temporadaId, posicion);
                dao.modificar(ec);

                request.getSession().setAttribute(MSG, MSG_EQUIPO_COMPETICION_MODIFICADO);
                response.sendRedirect(EQUIPO_COMPETICION_CONTROLLER);

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ERROR_ACCION);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ERROR_INTERNO  + e.getMessage());
        }
    }
}
