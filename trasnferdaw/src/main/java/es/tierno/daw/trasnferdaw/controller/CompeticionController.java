package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB;
import es.tierno.daw.trasnferdaw.model.entities.Competicion;

@WebServlet("/CompeticionController")
public class CompeticionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        if ("añadir".equalsIgnoreCase(accion)) {
            try {
                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();

                String nombre = request.getParameter("nombre");
                String pais = request.getParameter("pais");
                String tipo = request.getParameter("tipo");
                int numeroEquipos = Integer.parseInt(request.getParameter("numeroEquipos"));
                int anioCreacion = Integer.parseInt(request.getParameter("anioCreacion"));

                Competicion competicion = new Competicion();
                competicion.setNombre(nombre);
                competicion.setPais(pais);
                competicion.setTipo(tipo);
                competicion.setNumeroEquipos(numeroEquipos);
                competicion.setAnioCreacion(anioCreacion);

                dao.insertar(competicion); // Asegúrate de tener este método en tu DAO

                response.sendRedirect("competicion.jsp");

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al insertar competición");
            }

        } else if ("eliminar".equalsIgnoreCase(accion)) {
            try {
                int idCompeticion = Integer.parseInt(request.getParameter("id_competicion"));
                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();

                int registrosEliminados = dao.eliminarCompeticion(idCompeticion); // Asegúrate de tener este método

                if (registrosEliminados > 0) {
                    response.sendRedirect("competicion.jsp");
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Competición no encontrada");
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar competición");
            }
        } else if ("modificar".equalsIgnoreCase(accion)) {
            try {
                int idCompeticion = Integer.parseInt(request.getParameter("id_competicion"));
                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
                Competicion competicion = dao.visualizarCompeticion(idCompeticion);

                if (competicion != null) {
                    request.setAttribute("competicion", competicion);
                    request.getRequestDispatcher("editar_competicion.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Competición no encontrada para modificar");
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar competición para modificar");
            }

        } else if ("actualizar".equalsIgnoreCase(accion)) {
            try {
                int id = Integer.parseInt(request.getParameter("id_competicion"));
                String nombre = request.getParameter("nombre");
                String pais = request.getParameter("pais");
                String tipo = request.getParameter("tipo");
                int numeroEquipos = Integer.parseInt(request.getParameter("numeroEquipos"));
                int anioCreacion = Integer.parseInt(request.getParameter("anioCreacion"));

                Competicion c = new Competicion();
                c.setIdCompeticion(id);
                c.setNombre(nombre);
                c.setPais(pais);
                c.setTipo(tipo);
                c.setNumeroEquipos(numeroEquipos);
                c.setAnioCreacion(anioCreacion);

                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
                dao.modificar(c);

                response.sendRedirect("competicion.jsp");

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar competición");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // Puedes implementar aquí la lógica para modificar en POST si lo necesitas
    }
}
