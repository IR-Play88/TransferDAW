package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.tierno.daw.trasnferdaw.model.bbdd.Database;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDAO;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDBFactory;
import es.tierno.daw.trasnferdaw.model.entities.EstadisticasTemporada;
import es.tierno.daw.trasnferdaw.model.exception.BBDDException;

@WebServlet("/EstadisticasTemporadaController")
public class EstadisticasTemporadaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if ("modificar".equalsIgnoreCase(accion)) {
                int jugadorId = Integer.parseInt(request.getParameter("jugadorId"));
                int temporadaId = Integer.parseInt(request.getParameter("temporadaId"));
                int competicionId = Integer.parseInt(request.getParameter("competicionId"));
                int equipoId = Integer.parseInt(request.getParameter("equipoId"));

                EstadisticasTemporada est = dao.obtenerEstadisticaPorId(jugadorId, temporadaId, competicionId, equipoId);

                if (est != null) {
                    request.setAttribute("estadistica", est);
                    request.getRequestDispatcher("editar_estadistica.jsp").forward(request, response);
                } else {
                    request.getSession().setAttribute("error", "Estadística no encontrada");
                    response.sendRedirect("estadisticas.jsp");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Error interno del servidor");
            response.sendRedirect("estadisticas.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if ("insertar".equalsIgnoreCase(accion)) {
                // Validación de nombres
                String nombreJugador = request.getParameter("jugador");
                String nombreTemporada = request.getParameter("temporada");
                String nombreCompeticion = request.getParameter("competicion");
                String nombreEquipo = request.getParameter("equipo");

                int jugadorId;
                try {
                    jugadorId = dao.obtenerIdPorNombreJugador(nombreJugador);
                } catch (BBDDException e) {
                    request.getSession().setAttribute("error", "Jugador no encontrado");
                    response.sendRedirect("estadisticas.jsp");
                    return;
                }

                int temporadaId;
                try {
                    temporadaId = dao.obtenerIdPorNombreTemporada(nombreTemporada);
                } catch (BBDDException e) {
                    request.getSession().setAttribute("error", "Temporada no encontrada");
                    response.sendRedirect("estadisticas.jsp");
                    return;
                }

                int competicionId;
                try {
                    competicionId = dao.obtenerIdPorNombreCompeticion(nombreCompeticion);
                } catch (BBDDException e) {
                    request.getSession().setAttribute("error", "Competición no encontrada");
                    response.sendRedirect("estadisticas.jsp");
                    return;
                }

                int equipoId;
                try {
                    equipoId = dao.obtenerIdPorNombreEquipo(nombreEquipo);
                } catch (BBDDException e) {
                    request.getSession().setAttribute("error", "Equipo no encontrado");
                    response.sendRedirect("estadisticas.jsp");
                    return;
                }

                // Validaciones numéricas
                int partidosJugados, goles, asistencias;
                try {
                    partidosJugados = Integer.parseInt(request.getParameter("partidosJugados"));
                    if (partidosJugados < 0) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "Los partidos jugados no pueden ser negativos");
                    response.sendRedirect("estadisticas.jsp");
                    return;
                }
                
                try {
                    goles = Integer.parseInt(request.getParameter("goles"));
                    if (goles < 0) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "Los goles no pueden ser negativos");
                    response.sendRedirect("estadisticas.jsp");
                    return;
                }
                
                try {
                    asistencias = Integer.parseInt(request.getParameter("asistencias"));
                    if (asistencias < 0) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "Las asistencias no pueden ser negativas");
                    response.sendRedirect("estadisticas.jsp");
                    return;
                }
                

                // Crear y guardar
                EstadisticasTemporada est = new EstadisticasTemporada();
                est.setJugadorId(jugadorId);
                est.setTemporadaId(temporadaId);
                est.setCompeticionId(competicionId);
                est.setEquipoId(equipoId);
                est.setPartidosJugados(partidosJugados);
                est.setGoles(goles);
                est.setAsistencias(asistencias);

                dao.insertar(est);
                request.getSession().setAttribute("mensaje", "Estadística insertada correctamente");
                response.sendRedirect("estadisticas.jsp");

            } else if ("eliminar".equalsIgnoreCase(accion)) {
                int jugadorId = Integer.parseInt(request.getParameter("jugadorId"));
                int temporadaId = Integer.parseInt(request.getParameter("temporadaId"));
                int competicionId = Integer.parseInt(request.getParameter("competicionId"));
                int equipoId = Integer.parseInt(request.getParameter("equipoId"));

                int eliminados = dao.eliminarEstadisticasTemporada(jugadorId, temporadaId, competicionId, equipoId);

                if (eliminados > 0) {
                    request.getSession().setAttribute("mensaje", "Estadística eliminada correctamente");
                } else {
                    request.getSession().setAttribute("error", "Estadística no encontrada");
                }
                response.sendRedirect("estadisticas.jsp");

            } else if ("actualizar".equalsIgnoreCase(accion)) {
                int jugadorId = Integer.parseInt(request.getParameter("jugadorId"));
                int temporadaId = Integer.parseInt(request.getParameter("temporadaId"));
                int competicionId = Integer.parseInt(request.getParameter("competicionId"));
                int equipoId = Integer.parseInt(request.getParameter("equipoId"));
            
                int partidosJugados, goles, asistencias;
            
                try {
                    partidosJugados = Integer.parseInt(request.getParameter("partidosJugados"));
                    if (partidosJugados < 0) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "Los partidos jugados no pueden ser negativos");
                    response.sendRedirect("EstadisticasTemporadaController?accion=modificar&jugadorId=" + jugadorId
                        + "&temporadaId=" + temporadaId
                        + "&competicionId=" + competicionId
                        + "&equipoId=" + equipoId);
                    return;
                }
            
                try {
                    goles = Integer.parseInt(request.getParameter("goles"));
                    if (goles < 0) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "Los goles no pueden ser negativos");
                    response.sendRedirect("EstadisticasTemporadaController?accion=modificar&jugadorId=" + jugadorId
                        + "&temporadaId=" + temporadaId
                        + "&competicionId=" + competicionId
                        + "&equipoId=" + equipoId);
                    return;
                }
            
                try {
                    asistencias = Integer.parseInt(request.getParameter("asistencias"));
                    if (asistencias < 0) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "Las asistencias no pueden ser negativas");
                    response.sendRedirect("EstadisticasTemporadaController?accion=modificar&jugadorId=" + jugadorId
                        + "&temporadaId=" + temporadaId
                        + "&competicionId=" + competicionId
                        + "&equipoId=" + equipoId);
                    return;
                }
            
                EstadisticasTemporada est = new EstadisticasTemporada();
                est.setJugadorId(jugadorId);
                est.setTemporadaId(temporadaId);
                est.setCompeticionId(competicionId);
                est.setEquipoId(equipoId);
                est.setPartidosJugados(partidosJugados);
                est.setGoles(goles);
                est.setAsistencias(asistencias);
            
                dao.modificar(est);
                request.getSession().setAttribute("mensaje", "Estadística actualizada correctamente");
                response.sendRedirect("estadisticas.jsp");

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Error interno del servidor");
            response.sendRedirect("estadisticas.jsp");
        }
    }
}
