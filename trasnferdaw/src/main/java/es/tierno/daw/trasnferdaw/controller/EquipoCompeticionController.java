package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if ("modificar".equalsIgnoreCase(accion)) {
                int equipoId = Integer.parseInt(request.getParameter("equipoId"));
                int competicionId = Integer.parseInt(request.getParameter("competicionId"));
                int temporadaId = Integer.parseInt(request.getParameter("temporadaId"));

                EquipoCompeticion ec = dao.visualizarEquipoCompeticion(equipoId, competicionId, temporadaId);

                if (ec != null) {
                    request.setAttribute("equipoCompeticion", ec);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("editar_equipo_competicion.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.getSession().setAttribute("error", "Registro no encontrado para modificar");
                    response.sendRedirect("equipo_competicion.jsp");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Error interno del servidor");
            response.sendRedirect("equipo_competicion.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if ("insertar".equalsIgnoreCase(accion)) {
                // Validar nombres: equipo, competición, temporada existen
                String nombreEquipo = request.getParameter("nombreEquipo");
                String nombreCompeticion = request.getParameter("nombreCompeticion");
                String nombreTemporada = request.getParameter("nombreTemporada");

                int equipoId, competicionId, temporadaId;

                try {
                    equipoId = dao.obtenerIdPorNombreEquipo(nombreEquipo);
                } catch (BBDDException e) {
                    request.getSession().setAttribute("error", "Equipo no encontrado");
                    response.sendRedirect("equipo_competicion.jsp");
                    return;
                }

                try {
                    competicionId = dao.obtenerIdPorNombreCompeticion(nombreCompeticion);
                } catch (BBDDException e) {
                    request.getSession().setAttribute("error", "Competición no encontrada");
                    response.sendRedirect("equipo_competicion.jsp");
                    return;
                }

                try {
                    temporadaId = dao.obtenerIdPorNombreTemporada(nombreTemporada);
                } catch (BBDDException e) {
                    request.getSession().setAttribute("error", "Temporada no encontrada");
                    response.sendRedirect("equipo_competicion.jsp");
                    return;
                }

                // Validar rango >= 0
                int rango;
                try {
                    rango = Integer.parseInt(request.getParameter("rango"));
                    if (rango < 0) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "El rango debe ser mayor o igual a cero");
                    response.sendRedirect("equipo_competicion.jsp");
                    return;
                }

                EquipoCompeticion ec = new EquipoCompeticion(equipoId, competicionId, temporadaId, rango);
                dao.insertar(ec);

                request.getSession().setAttribute("mensaje", "Equipo añadido a competición correctamente");
                response.sendRedirect("equipo_competicion.jsp");

            } else if ("eliminar".equalsIgnoreCase(accion)) {
                int equipoId = Integer.parseInt(request.getParameter("equipoId"));
                int competicionId = Integer.parseInt(request.getParameter("competicionId"));
                int temporadaId = Integer.parseInt(request.getParameter("temporadaId"));

                int eliminados = dao.eliminarEquipoCompeticion(equipoId, competicionId, temporadaId);

                if (eliminados > 0) {
                    request.getSession().setAttribute("mensaje", "Registro eliminado correctamente");
                } else {
                    request.getSession().setAttribute("error", "Registro no encontrado");
                }
                response.sendRedirect("equipo_competicion.jsp");

            } else if ("actualizar".equalsIgnoreCase(accion)) {
                int equipoId = Integer.parseInt(request.getParameter("equipoId"));
                int competicionId = Integer.parseInt(request.getParameter("competicionId"));
                int temporadaId = Integer.parseInt(request.getParameter("temporadaId"));

                int rango;
                try {
                    rango = Integer.parseInt(request.getParameter("rango"));
                    if (rango < 0) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "El rango debe ser mayor o igual a cero");
                    response.sendRedirect("EquipoCompeticionController?accion=modificar&equipoId=" + equipoId
                            + "&competicionId=" + competicionId
                            + "&temporadaId=" + temporadaId);
                    return;
                }

                EquipoCompeticion ec = new EquipoCompeticion(equipoId, competicionId, temporadaId, rango);
                dao.modificar(ec);

                request.getSession().setAttribute("mensaje", "Registro actualizado correctamente");
                response.sendRedirect("equipo_competicion.jsp");

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Error interno del servidor");
            response.sendRedirect("equipo_competicion.jsp");
        }
    }
}
