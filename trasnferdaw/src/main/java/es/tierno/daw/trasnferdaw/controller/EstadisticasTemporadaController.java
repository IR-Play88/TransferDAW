package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB;
import es.tierno.daw.trasnferdaw.model.entities.EstadisticasTemporada;

@WebServlet("/EstadisticasTemporadaController")
public class EstadisticasTemporadaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        if ("añadir".equalsIgnoreCase(accion)) {
            try {
                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
        
                int jugadorId = dao.obtenerIdPorNombreJugador(request.getParameter("jugador"));
                int temporadaId = dao.obtenerIdPorNombreTemporada(request.getParameter("temporada"));
                int competicionId = dao.obtenerIdPorNombreCompeticion(request.getParameter("competicion"));
                int equipoId = dao.obtenerIdPorNombreEquipo(request.getParameter("equipo"));
        
                int partidosJugados = 0;
                int goles = 0;
                int asistencias = 0;
        
                if (request.getParameter("partidosJugados") != null && !request.getParameter("partidosJugados").isEmpty()) {
                    partidosJugados = Integer.parseInt(request.getParameter("partidosJugados"));
                }
                if (request.getParameter("goles") != null && !request.getParameter("goles").isEmpty()) {
                    goles = Integer.parseInt(request.getParameter("goles"));
                }
                if (request.getParameter("asistencias") != null && !request.getParameter("asistencias").isEmpty()) {
                    asistencias = Integer.parseInt(request.getParameter("asistencias"));
                }
        
                EstadisticasTemporada est = new EstadisticasTemporada();
                est.setJugadorId(jugadorId);
                est.setTemporadaId(temporadaId);
                est.setCompeticionId(competicionId);
                est.setEquipoId(equipoId);
                est.setPartidosJugados(partidosJugados);
                est.setGoles(goles);
                est.setAsistencias(asistencias);
        
                dao.insertar(est);
                response.sendRedirect("estadisticas.jsp");
        
            }  catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Error al añadir estadisticas: " + e.getMessage());
            }
        } else if ("eliminar".equalsIgnoreCase(accion)) {
            try {
                int jugadorId = Integer.parseInt(request.getParameter("jugadorId"));
                int temporadaId = Integer.parseInt(request.getParameter("temporadaId"));
                int competicionId = Integer.parseInt(request.getParameter("competicionId"));
                int equipoId = Integer.parseInt(request.getParameter("equipoId"));

                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
                int registrosEliminados = dao.eliminarEstadisticasTemporada(jugadorId, temporadaId, competicionId, equipoId);

                if (registrosEliminados > 0) {
                    response.sendRedirect("estadisticas.jsp");
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Estadística no encontrada");
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar estadística");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // No se usa
    }
}
