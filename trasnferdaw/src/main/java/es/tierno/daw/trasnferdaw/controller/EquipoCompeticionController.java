package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB;
import es.tierno.daw.trasnferdaw.model.entities.EquipoCompeticion;

@WebServlet("/EquipoCompeticionController")
public class EquipoCompeticionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        if ("añadir".equalsIgnoreCase(accion)) {
            try {
                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();

                // Recoger los datos enviados en el formulario
                String nombreEquipo = request.getParameter("nombreEquipo");
                String nombreCompeticion = request.getParameter("nombreCompeticion");
                String nombreTemporada = request.getParameter("nombreTemporada");
                int rango = Integer.parseInt(request.getParameter("rango"));

                // Convertir los nombres a IDs
                int equipoId = dao.obtenerIdPorNombreEquipo(nombreEquipo);
                int competicionId = dao.obtenerIdPorNombreCompeticion(nombreCompeticion);
                int temporadaId = dao.obtenerIdPorNombreTemporada(nombreTemporada);

                // Crear un nuevo objeto EquipoCompeticion
                EquipoCompeticion ec = new EquipoCompeticion();
                ec.setEquipoId(equipoId);
                ec.setCompeticionId(competicionId);
                ec.setTemporadaId(temporadaId);
                ec.setRango(rango);

                // Insertar el equipo en la competición
                dao.insertar(ec);

                // Redirigir a la página de equipos
                response.sendRedirect("equipo_competicion.jsp");

            } catch (Exception e) {
                e.printStackTrace(); // Esto seguirá mostrando el error completo en consola/log
            
                // Mostrar el mensaje del error directamente en la respuesta al navegador
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Error al añadir equipo en competición: " + e.getMessage());
            }
            
        } else if ("eliminar".equalsIgnoreCase(accion)) {
            try {
                // Recoger los IDs para eliminar
                int equipoId = Integer.parseInt(request.getParameter("id_equipo"));
                int competicionId = Integer.parseInt(request.getParameter("id_competicion"));
                int temporadaId = Integer.parseInt(request.getParameter("id_temporada"));

                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
                
                // Eliminar la relación equipo-competición-temporada
                int eliminados = dao.eliminarEquipoCompeticion(equipoId, competicionId, temporadaId);

                // Redirigir según si se eliminó correctamente
                if (eliminados > 0) {
                    response.sendRedirect("equipo_competicion.jsp");
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Registro no encontrado");
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Error al eliminar equipo en competición");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // Vacío por ahora, se puede usar para futuras mejoras
    }
}
