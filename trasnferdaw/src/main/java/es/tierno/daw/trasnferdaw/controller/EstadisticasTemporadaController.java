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
import es.tierno.daw.trasnferdaw.model.entities.EstadisticasTemporada;
import es.tierno.daw.trasnferdaw.model.exception.BBDDException;

@WebServlet("/EstadisticasTemporadaController")
public class EstadisticasTemporadaController extends HttpServlet {

    private static final String ACCION = "accion";
    private static final String ERROR = "error";
    private static final String MSG = "mensaje";

    private static final String ESTADISTICA = "estadistica";

    private static final String OPC_MODIFICAR = "modificar";
    private static final String OPC_ELIMINAR = "eliminar";
    private static final String OPC_ACTUALIZAR = "actualizar";
    private static final String OPC_INSERTAR = "insertar";
    private static final String OPC_DESCARGAR_TOTALES = "descargarTotales";
    private static final String OPC_DESCARGAR_TEMPORADA = "descargarTemporada";

    private static final String LISTA_ESTADISTICAS = "listaEstadisticaTemporadas";
    private static final String ESTADISTICAS_JSP = "/estadisticas/estadisticas.jsp";
    private static final String EDITAR_ESTADISTICAS = "/estadisticas/editar_estadistica.jsp";
    private static final String ESTADISTICAS_CONTROLLER = "EstadisticasTemporadaController";
    private static final String URL_REDIRECCION_MODIFICAR = "EstadisticasTemporadaController?accion=modificar";
    private static final String URL_ESTADISTICAS_JUGADOR = "&jugadorId=";
    private static final String URL_STADISTICAS_TEMPORADA = "&temporadaId=";
    private static final String URL_ESTADISTICAS_EQUIPO = "&equipoId=";
    private static final String URL_ESTADISTICAS_COMPETICION = "&competicionId=";

    private static final String ESTADISTICAS_JUGADOR_ID = "jugadorId";
    private static final String ESTADISTICAS_TEMPORADA_ID = "temporadaId";
    private static final String ESTADISTICAS_EQUIPO_ID = "equipoId";
    private static final String ESTADISTICAS_COMPETICION_ID = "competicionId";
    private static final String ESTADISTICAS_JUGADOR = "jugador";
    private static final String ESTADISTICAS_TEMPORADA = "temporada";
    private static final String ESTADISTICAS_EQUIPO = "equipo";
    private static final String ESTADISTICAS_COMPETICION = "competicion";
    private static final String ESTADISTICAS_PARTIDOS_JUGADOS = "partidosJugados";
    private static final String ESTADISTICAS_GOLES = "goles";
    private static final String ESTADISTICAS_ASISTENCIAS = "asistencias";
    private static final String JUGADOR = "Jugador ->";
    private static final String TEMPORADA = "Temporada ->";
    private static final String PARTIDOS = "Partidos ->";
    private static final String GOLES = "goles ->";
    private static final String ASISTENCIAS = "asistencias ->";
    private static final String TEXT_PLAIN = "text/plain";
    private static final String ESTADISTICAS = "estadisticas_";
    private static final String LETRAS_NUMERO = "[^a-zA-Z0-9]";
    private static final String SALTO = "\n";
    private static final String GUION_BAJO= "_";
    private static final String EXTENSION = ".txt"; 
    private static final String CONETNT = "Content-Disposition"; 
    private static final String ATTACHMENT = "attachment; filename=\""; 
    private static final String BARRA = "\"";

   
    private static final String ERROR_ACCION = "Acción no reconocida";
    private static final String ERROR_MODIFICAR = "No se encontró una estadistica para modificar";
    private static final String ERROR_ELIMINAR = "No se encontró una estadistica para eliminar";
    private static final String ERROR_DESCARGAR = "No se encontró una estadistica para descargar";
    private static final String ERROR_RELACION = "No se encontró una relacion entre las estadistica para descargar";
    private static final String ERROR_JUGADOR = "Jugador no encontrado. Verifique que exista en la sección 'Jugadores'.";
    private static final String ERROR_EQUIPO = "Equipo no encontrado. Verifique que exista en la sección 'Equipos'.";
    private static final String ERROR_COMPETICION = "Competicion no encontrada. Verifique que exista en la sección 'Competiciones'.";
    private static final String ERROR_TEMPORADA = "Temporada no encontrado. Verifique que exista en la sección 'Temporadas'.";
    private static final String ERROR_INTERNO = "Error interno en el servidor: ";
    private static final String ERROR_PARTIDOS_JUGADOS = "Los partidos jugados no pueden ser negativos";
    private static final String ERROR_GOLES = "Los goles no pueden ser negativos";
    private static final String ERROR_ASISTENCIAS = "Las asistencias no pueden ser negativas";
   
    private static final String MSG_ESTADISTICA_INSERTADO = "Estadistica insertada correctamnete";
    private static final String MSG_ESTADISTICA_MODIFICADO = "Estadistica modificada correctamnete";
    private static final String MSG_ESTADISTICA_ELIMINADO = "Estadistica eliminada correctamnete";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter(ACCION);

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if (accion == null || !accion.equals(OPC_MODIFICAR)) {
                List<EstadisticasTemporada> list = dao.listarEstadisticasTemporada();
                request.setAttribute(LISTA_ESTADISTICAS, list);
                request.getRequestDispatcher(ESTADISTICAS_JSP).forward(request, response);
            } else if (OPC_MODIFICAR.equalsIgnoreCase(accion)) {
                int jugadorId = Integer.parseInt(request.getParameter(ESTADISTICAS_JUGADOR_ID));
                int temporadaId = Integer.parseInt(request.getParameter(ESTADISTICAS_TEMPORADA_ID));
                int competicionId = Integer.parseInt(request.getParameter(ESTADISTICAS_COMPETICION_ID));
                int equipoId = Integer.parseInt(request.getParameter(ESTADISTICAS_EQUIPO_ID));

                EstadisticasTemporada est = dao.obtenerEstadisticaPorId(jugadorId, temporadaId, competicionId,
                        equipoId);

                if (est != null) {
                    request.setAttribute(ESTADISTICA, est);
                    request.getRequestDispatcher(EDITAR_ESTADISTICAS).forward(request, response);
                } else {
                    request.getSession().setAttribute(ERROR, ERROR_MODIFICAR);
                    response.sendRedirect(ESTADISTICAS_CONTROLLER);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute(ERROR, ERROR_INTERNO);
            response.sendRedirect(ESTADISTICAS_CONTROLLER);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter(ACCION);

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if (OPC_INSERTAR.equalsIgnoreCase(accion)) {
                // Validación de nombres
                String nombreJugador = request.getParameter(ESTADISTICAS_JUGADOR);
                String nombreTemporada = request.getParameter(ESTADISTICAS_TEMPORADA);
                String nombreCompeticion = request.getParameter(ESTADISTICAS_COMPETICION);
                String nombreEquipo = request.getParameter(ESTADISTICAS_EQUIPO);

                int jugadorId;
                int temporadaId;
                int competicionId;
                int equipoId;
                int partidosJugados;
                int goles;
                int asistencias;

                try {
                    jugadorId = dao.obtenerIdPorNombreJugador(nombreJugador);
                } catch (BBDDException e) {
                    request.getSession().setAttribute(ERROR, ERROR_JUGADOR);
                    response.sendRedirect(ESTADISTICAS_CONTROLLER);
                    return;
                }

                
                try {
                    temporadaId = dao.obtenerIdPorNombreTemporada(nombreTemporada);
                } catch (BBDDException e) {
                    request.getSession().setAttribute(ERROR, ERROR_TEMPORADA);
                    response.sendRedirect(ESTADISTICAS_CONTROLLER);
                    return;
                }

                try {
                    competicionId = dao.obtenerIdPorNombreCompeticion(nombreCompeticion);
                } catch (BBDDException e) {
                    request.getSession().setAttribute(ERROR, ERROR_COMPETICION);
                    response.sendRedirect(ESTADISTICAS_CONTROLLER);
                    return;
                }

                try {
                    equipoId = dao.obtenerIdPorNombreEquipo(nombreEquipo);
                } catch (BBDDException e) {
                    request.getSession().setAttribute(ERROR, ERROR_EQUIPO);
                    response.sendRedirect(ESTADISTICAS_CONTROLLER);
                    return;
                }

               
                try {
                    partidosJugados = Integer.parseInt(request.getParameter(ESTADISTICAS_PARTIDOS_JUGADOS));
                    if (partidosJugados < 0)
                        throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute(ERROR, ERROR_PARTIDOS_JUGADOS);
                    response.sendRedirect(ESTADISTICAS_CONTROLLER);
                    return;
                }

                try {
                    goles = Integer.parseInt(request.getParameter(ESTADISTICAS_GOLES));
                    if (goles < 0)
                        throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute(ERROR, ERROR_GOLES);
                    response.sendRedirect(ESTADISTICAS_CONTROLLER);
                    return;
                }

                try {
                    asistencias = Integer.parseInt(request.getParameter(ESTADISTICAS_ASISTENCIAS));
                    if (asistencias < 0)
                        throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute(ERROR, ERROR_ASISTENCIAS);
                    response.sendRedirect(ESTADISTICAS_CONTROLLER);
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
                request.getSession().setAttribute(MSG, MSG_ESTADISTICA_INSERTADO);
                response.sendRedirect(ESTADISTICAS_CONTROLLER);

            } else if (OPC_ELIMINAR.equalsIgnoreCase(accion)) {
                int jugadorId = Integer.parseInt(request.getParameter(ESTADISTICAS_JUGADOR_ID));
                int temporadaId = Integer.parseInt(request.getParameter(ESTADISTICAS_TEMPORADA_ID));
                int competicionId = Integer.parseInt(request.getParameter(ESTADISTICAS_COMPETICION_ID));
                int equipoId = Integer.parseInt(request.getParameter(ESTADISTICAS_EQUIPO_ID));

                int eliminados = dao.eliminarEstadisticasTemporada(jugadorId, temporadaId, competicionId, equipoId);

                if (eliminados > 0) {
                    request.getSession().setAttribute(MSG, MSG_ESTADISTICA_ELIMINADO);
                } else {
                    request.getSession().setAttribute(ERROR, ERROR_ELIMINAR);
                }
                response.sendRedirect(ESTADISTICAS_CONTROLLER);

            } else if (OPC_ACTUALIZAR.equalsIgnoreCase(accion)) {
                int jugadorId = Integer.parseInt(request.getParameter(ESTADISTICAS_JUGADOR_ID));
                int temporadaId = Integer.parseInt(request.getParameter(ESTADISTICAS_TEMPORADA_ID));
                int competicionId = Integer.parseInt(request.getParameter(ESTADISTICAS_COMPETICION_ID));
                int equipoId = Integer.parseInt(request.getParameter(ESTADISTICAS_EQUIPO_ID));

                int partidosJugados;
                int goles;
                int asistencias;

                try {
                    partidosJugados = Integer.parseInt(request.getParameter(ESTADISTICAS_PARTIDOS_JUGADOS));
                    if (partidosJugados < 0)
                        throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute(ERROR, ERROR_PARTIDOS_JUGADOS);
                    response.sendRedirect(URL_REDIRECCION_MODIFICAR + URL_ESTADISTICAS_JUGADOR + jugadorId
                            + URL_STADISTICAS_TEMPORADA + temporadaId
                            + URL_ESTADISTICAS_COMPETICION + competicionId
                            + URL_ESTADISTICAS_EQUIPO + equipoId);
                    return;
                }

                try {
                    goles = Integer.parseInt(request.getParameter(ESTADISTICAS_GOLES));
                    if (goles < 0)
                        throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute(ERROR, ERROR_GOLES);
                    response.sendRedirect(URL_REDIRECCION_MODIFICAR + URL_ESTADISTICAS_JUGADOR + jugadorId
                            + URL_STADISTICAS_TEMPORADA + temporadaId
                            + URL_ESTADISTICAS_COMPETICION + competicionId
                            + URL_ESTADISTICAS_EQUIPO + equipoId);
                    return;
                }

                try {
                    asistencias = Integer.parseInt(request.getParameter(ESTADISTICAS_ASISTENCIAS));
                    if (asistencias < 0)
                        throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute(ERROR, ERROR_ASISTENCIAS);
                    response.sendRedirect(URL_REDIRECCION_MODIFICAR + URL_ESTADISTICAS_JUGADOR + jugadorId
                    + URL_STADISTICAS_TEMPORADA + temporadaId
                    + URL_ESTADISTICAS_COMPETICION + competicionId
                    + URL_ESTADISTICAS_EQUIPO + equipoId);
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
                request.getSession().setAttribute(MSG, MSG_ESTADISTICA_MODIFICADO);
                response.sendRedirect(ESTADISTICAS_CONTROLLER);

            } else if (OPC_DESCARGAR_TOTALES.equalsIgnoreCase(accion)) {

                String nombreJugador = request.getParameter(ESTADISTICAS_JUGADOR);
                int jugadorId;
                try {
                    jugadorId = dao.obtenerIdPorNombreJugador(nombreJugador);
                } catch (Exception e) {
                    request.getSession().setAttribute(ERROR, ERROR_JUGADOR);
                    response.sendRedirect(ESTADISTICAS_CONTROLLER);
                    return;
                }

                EstadisticasTemporada est = dao.buscarEstadisticasTotalesPorJugador(jugadorId);

                try {
                    if (est != null) {
                        response.setContentType(TEXT_PLAIN);
                        String nombreArchivo = ESTADISTICAS + est.getNombreJugador().replaceAll(LETRAS_NUMERO, GUION_BAJO)
                                + EXTENSION;
                        response.setHeader(CONETNT, ATTACHMENT + nombreArchivo + BARRA);

                        StringBuilder texto = new StringBuilder();
                        texto.append(JUGADOR).append(est.getNombreJugador()).append(SALTO);
                        texto.append(PARTIDOS).append(est.getPartidosJugados()).append(SALTO);
                        texto.append(GOLES).append(est.getGoles()).append(SALTO);
                        texto.append(ASISTENCIAS).append(est.getAsistencias()).append(SALTO);

                        response.getWriter().write(texto.toString());
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, ERROR_DESCARGAR);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ERROR_DESCARGAR);
                }
            } else if (OPC_DESCARGAR_TEMPORADA.equalsIgnoreCase(accion)) {
                try {
                    String nombreJugador = request.getParameter(ESTADISTICAS_JUGADOR);
                    String nombreTemporada = request.getParameter(ESTADISTICAS_TEMPORADA);
                    int jugadorId;
                    int temporadaId;
                    try {
                        jugadorId = dao.obtenerIdPorNombreJugador(nombreJugador);
                    } catch (Exception e) {
                        request.getSession().setAttribute(ERROR, ERROR_JUGADOR);
                        response.sendRedirect(ESTADISTICAS_CONTROLLER);
                        return;
                    }

                    try {
                        temporadaId = dao.obtenerIdPorNombreTemporada(nombreTemporada);
                    } catch (Exception e) {
                        request.getSession().setAttribute(ERROR, ERROR_TEMPORADA);
                        response.sendRedirect(ESTADISTICAS_CONTROLLER);
                        return;
                    }

                    EstadisticasTemporada est = dao.buscarEstadisticasPorTemporada(jugadorId, temporadaId);

                    if (est != null) {
                        StringBuilder texto = new StringBuilder();
                        texto.append(JUGADOR).append(est.getNombreJugador()).append(SALTO);
                        texto.append(TEMPORADA).append(est.getNombreTemporada()).append(SALTO);
                        texto.append(PARTIDOS).append(est.getPartidosJugados()).append(SALTO);
                        texto.append(GOLES).append(est.getGoles()).append(SALTO);
                        texto.append(ASISTENCIAS).append(est.getAsistencias()).append(SALTO);

                        response.setContentType(TEXT_PLAIN);
                        String nombreArchivo = ESTADISTICAS +
                                est.getNombreJugador().replaceAll(LETRAS_NUMERO, GUION_BAJO) + GUION_BAJO +
                                est.getNombreTemporada().replaceAll(LETRAS_NUMERO, GUION_BAJO) + EXTENSION;

                        response.setHeader(CONETNT, ATTACHMENT + nombreArchivo + BARRA);

                        response.getWriter().write(texto.toString());

                    } else {
                        request.getSession().setAttribute(ERROR, ERROR_RELACION);
                        response.sendRedirect(ESTADISTICAS_CONTROLLER);
                    }
                    ;
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ERROR_DESCARGAR);
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ERROR_ACCION);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    ERROR_INTERNO + e.getMessage());
        }
    }
}