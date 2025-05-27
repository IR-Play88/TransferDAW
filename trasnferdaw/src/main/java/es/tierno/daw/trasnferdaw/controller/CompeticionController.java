package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if ("modificar".equalsIgnoreCase(accion)) {
                String idStr = request.getParameter("id_competicion");
                if (idStr == null || idStr.trim().isEmpty()) {
                    request.getSession().setAttribute("error", "No se ha seleccionado una competición para modificar");
                    response.sendRedirect("competicion.jsp");
                    return;
                }

                int idCompeticion = Integer.parseInt(idStr);
                Competicion competicion = dao.visualizarCompeticion(idCompeticion);

                if (competicion != null) {
                    request.setAttribute("competicion", competicion);
                    request.getRequestDispatcher("editar_competicion.jsp").forward(request, response);
                } else {
                    request.getSession().setAttribute("error", "No se encontró una competición para modificar");
                    response.sendRedirect("competicion.jsp");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Error interno del servidor");
            response.sendRedirect("competicion.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if ("insertar".equalsIgnoreCase(accion)) {
                String nombre = request.getParameter("nombre");
                String pais = request.getParameter("pais");
                String tipo = request.getParameter("tipo");

                int numeroEquipos, anioCreacion;
                try {
                    numeroEquipos = Integer.parseInt(request.getParameter("numeroEquipos"));
                    anioCreacion = Integer.parseInt(request.getParameter("anioCreacion"));
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "Número de equipos o año de creación inválidos");
                    response.sendRedirect("competicion.jsp");
                    return;
                }

                if (numeroEquipos <= 0) {
                    request.getSession().setAttribute("error", "El numero de equipos debe ser mínimo 0");
                    response.sendRedirect("competicion.jsp");
                    return;
                }

                if (anioCreacion <= 1800) {
                    request.getSession().setAttribute("error", "El año de creación debe ser mayor a 1800");
                    response.sendRedirect("competicion.jsp");
                    return;
                }

                if (tipo == null || !(tipo.equalsIgnoreCase("liga")
                        || tipo.equalsIgnoreCase("copa")
                        || tipo.equalsIgnoreCase("internacional")
                        || tipo.equalsIgnoreCase("seleccion")
                        || tipo.equalsIgnoreCase("amistoso"))) {
                    request.getSession().setAttribute("error",
                            "Tipo invádilo. Solo puede ser liga, copa, internacional, seleccion o amistoso.");
                    response.sendRedirect("competicion.jsp");
                    return;
                }

                Competicion competicion = new Competicion(nombre, pais, tipo, numeroEquipos, anioCreacion);
                dao.insertar(competicion);

                request.getSession().setAttribute("mensaje", "Competición insertada correctamente");
                response.sendRedirect("competicion.jsp");

            } else if ("eliminar".equalsIgnoreCase(accion)) {
                int idCompeticion = Integer.parseInt(request.getParameter("id_competicion"));
                int registrosEliminados = dao.eliminarCompeticion(idCompeticion);

                if (registrosEliminados > 0) {
                    request.getSession().setAttribute("mensaje", "Competición eliminada correctamente");
                } else {
                    request.getSession().setAttribute("error", "Competición no encontrada");
                }
                response.sendRedirect("competicion.jsp");

            } else if ("actualizar".equalsIgnoreCase(accion)) {
                int idCompeticion = Integer.parseInt(request.getParameter("id_competicion"));
                String nombre = request.getParameter("nombre");
                String pais = request.getParameter("pais");
                String tipo = request.getParameter("tipo");

                int numeroEquipos, anioCreacion;
                try {
                    numeroEquipos = Integer.parseInt(request.getParameter("numeroEquipos"));
                    anioCreacion = Integer.parseInt(request.getParameter("anioCreacion"));
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "Número de equipos o año de creación inválidos");
                    Competicion competicion = dao.visualizarCompeticion(idCompeticion);
                    request.setAttribute("competicion", competicion);
                    request.getRequestDispatcher("editar_competicion.jsp").forward(request, response);
                    return;
                }

                if (numeroEquipos <= 0) {
                    request.getSession().setAttribute("error", "El numero de equipos debe ser mínimo 0");
                    Competicion competicion = dao.visualizarCompeticion(idCompeticion);
                    request.setAttribute("competicion", competicion);
                    request.getRequestDispatcher("editar_competicion.jsp").forward(request, response);
                    return;
                }

                if (tipo == null || !(tipo.equalsIgnoreCase("liga")
                        || tipo.equalsIgnoreCase("copa")
                        || tipo.equalsIgnoreCase("internacional")
                        || tipo.equalsIgnoreCase("seleccion")
                        || tipo.equalsIgnoreCase("amistoso"))) {
                    request.getSession().setAttribute("error",
                            "Tipo invádilo. Solo puede ser liga, copa, internacional, seleccion o amistoso.");
                    response.sendRedirect("editar_competicion.jsp");
                    return;
                }

                if (anioCreacion <= 1800) {
                    request.getSession().setAttribute("error", "El año de creación debe ser mayor a 1800");
                    Competicion competicion = dao.visualizarCompeticion(idCompeticion);
                    request.setAttribute("competicion", competicion);
                    request.getRequestDispatcher("editar_competicion.jsp").forward(request, response);
                    return;
                }

                Competicion competicion = new Competicion(idCompeticion, nombre, pais, tipo, numeroEquipos,
                        anioCreacion);
                dao.modificar(competicion);

                request.getSession().setAttribute("mensaje", "Competición actualizada correctamente");
                response.sendRedirect("competicion.jsp");

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno del servidor");
        }
    }
}
