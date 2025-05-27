package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.tierno.daw.trasnferdaw.model.bbdd.Database;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDAO;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDBFactory;
import es.tierno.daw.trasnferdaw.model.entities.Traspaso;
import es.tierno.daw.trasnferdaw.model.exception.BBDDException;

@WebServlet("/TraspasoController")
public class TraspasoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if ("modificar".equalsIgnoreCase(accion)) {
                String idStr = request.getParameter("id_traspaso");
                if (idStr == null || idStr.isEmpty()) {
                    request.getSession().setAttribute("error", "No se ha seleccionado un traspaso para modificar");
                    response.sendRedirect("traspaso.jsp");
                    return;
                }

                int idTraspaso = Integer.parseInt(idStr);
                Traspaso traspaso = dao.visualizarTraspaso(idTraspaso);

                if (traspaso != null) {
                    request.setAttribute("traspaso", traspaso);
                    request.getRequestDispatcher("editar_traspaso.jsp").forward(request, response);
                } else {
                    request.getSession().setAttribute("error", "Traspaso no encontrado");
                    response.sendRedirect("traspaso.jsp");
                }

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Error interno del servidor");
            response.sendRedirect("traspaso.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if ("insertar".equalsIgnoreCase(accion)) {
                String nombreJugador = request.getParameter("jugadorId");
                String nombreEquipoOrigen = request.getParameter("equipoOrigenId");
                String nombreEquipoDestino = request.getParameter("equipoDestinoId");
                String nombreTemporada = request.getParameter("temporadaId");
                String cantidadStr = request.getParameter("cantidad");
                String fechaStr = request.getParameter("fechaTraspaso");
                String tipo = request.getParameter("tipo");

                int jugadorId, equipoOrigenId, equipoDestinoId, temporadaId;
                float cantidad;
                LocalDate fechaTraspaso;

                try {
                    jugadorId = dao.obtenerIdPorNombreJugador(nombreJugador);
                } catch (BBDDException e) {
                    request.getSession().setAttribute("error", "Jugador no encontrado");
                    response.sendRedirect("traspaso.jsp");
                    return;
                }

                try {
                    equipoOrigenId = dao.obtenerIdPorNombreEquipo(nombreEquipoOrigen);
                } catch (BBDDException e) {
                    request.getSession().setAttribute("error", "Equipo origen no encontrado");
                    response.sendRedirect("traspaso.jsp");
                    return;
                }

                try {
                    equipoDestinoId = dao.obtenerIdPorNombreEquipo(nombreEquipoDestino);
                } catch (BBDDException e) {
                    request.getSession().setAttribute("error", "Equipo destino no encontrado");
                    response.sendRedirect("traspaso.jsp");
                    return;
                }

                try {
                    temporadaId = dao.obtenerIdPorNombreTemporada(nombreTemporada);
                } catch (BBDDException e) {
                    request.getSession().setAttribute("error", "Temporada no encontrada");
                    response.sendRedirect("traspaso.jsp");
                    return;
                }

                try {
                    cantidad = Float.parseFloat(cantidadStr);
                    if (cantidad <= 0) {
                        request.getSession().setAttribute("error", "La cantidad debe ser mayor que cero");
                        response.sendRedirect("traspaso.jsp");
                        return;
                    }
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "Cantidad inválida");
                    response.sendRedirect("traspaso.jsp");
                    return;
                }

                try {
                    fechaTraspaso = LocalDate.parse(fechaStr);
                    if (fechaTraspaso.isAfter(LocalDate.now())) {
                        request.getSession().setAttribute("error", "La fecha no puede ser futura");
                        response.sendRedirect("traspaso.jsp");
                        return;
                    }
                } catch (Exception e) {
                    request.getSession().setAttribute("error", "Fecha inválida");
                    response.sendRedirect("traspaso.jsp");
                    return;
                }

                if (tipo == null || !(tipo.equalsIgnoreCase("compra") || tipo.equalsIgnoreCase("cesion")
                        || tipo.equalsIgnoreCase("libre") || tipo.equalsIgnoreCase("fin de contrato"))) {
                    request.getSession().setAttribute("error", "Tipo inválido");
                    response.sendRedirect("traspaso.jsp");
                    return;
                }

                Traspaso traspaso = new Traspaso(jugadorId, equipoOrigenId, equipoDestinoId, temporadaId,
                        fechaTraspaso, cantidad, tipo);
                dao.insertar(traspaso);

                request.getSession().setAttribute("mensaje", "Traspaso insertado correctamente");
                response.sendRedirect("traspaso.jsp");

            } else if ("actualizar".equalsIgnoreCase(accion)) {
                int idTraspaso = Integer.parseInt(request.getParameter("id_traspaso"));
                Traspaso traspasoActual = dao.visualizarTraspaso(idTraspaso);

                String nombreJugador = request.getParameter("jugadorId");
                String nombreEquipoOrigen = request.getParameter("equipoOrigenId");
                String nombreEquipoDestino = request.getParameter("equipoDestinoId");
                String nombreTemporada = request.getParameter("temporadaId");
                String cantidadStr = request.getParameter("cantidad");
                String fechaStr = request.getParameter("fechaTraspaso");
                String tipo = request.getParameter("tipo");

                int jugadorId, equipoOrigenId, equipoDestinoId, temporadaId;
                float cantidad;
                LocalDate fechaTraspaso;

                try {
                    jugadorId = dao.obtenerIdPorNombreJugador(nombreJugador);
                } catch (BBDDException e) {
                    request.getSession().setAttribute("error", "Jugador no encontrado");
                    request.setAttribute("traspaso", traspasoActual);
                    request.getRequestDispatcher("editar_traspaso.jsp").forward(request, response);
                    return;
                }

                try {
                    equipoOrigenId = dao.obtenerIdPorNombreEquipo(nombreEquipoOrigen);
                } catch (BBDDException e) {
                    request.getSession().setAttribute("error", "Equipo origen no encontrado");
                    request.setAttribute("traspaso", traspasoActual);
                    request.getRequestDispatcher("editar_traspaso.jsp").forward(request, response);
                    return;
                }

                try {
                    equipoDestinoId = dao.obtenerIdPorNombreEquipo(nombreEquipoDestino);
                } catch (BBDDException e) {
                    request.getSession().setAttribute("error", "Equipo destino no encontrado");
                    request.setAttribute("traspaso", traspasoActual);
                    request.getRequestDispatcher("editar_traspaso.jsp").forward(request, response);
                    return;
                }

                try {
                    temporadaId = dao.obtenerIdPorNombreTemporada(nombreTemporada);
                } catch (BBDDException e) {
                    request.getSession().setAttribute("error", "Temporada no encontrada");
                    request.setAttribute("traspaso", traspasoActual);
                    request.getRequestDispatcher("editar_traspaso.jsp").forward(request, response);
                    return;
                }

                try {
                    cantidad = Float.parseFloat(cantidadStr);
                    if (cantidad <= 0) {
                        request.getSession().setAttribute("error", "La cantidad debe ser mayor que cero");
                        request.setAttribute("traspaso", traspasoActual);
                        request.getRequestDispatcher("editar_traspaso.jsp").forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "Cantidad inválida");
                    request.setAttribute("traspaso", traspasoActual);
                    request.getRequestDispatcher("editar_traspaso.jsp").forward(request, response);
                    return;
                }

                try {
                    fechaTraspaso = LocalDate.parse(fechaStr);
                    if (fechaTraspaso.isAfter(LocalDate.now())) {
                        request.getSession().setAttribute("error", "La fecha no puede ser futura");
                        request.setAttribute("traspaso", traspasoActual);
                        request.getRequestDispatcher("editar_traspaso.jsp").forward(request, response);
                        return;
                    }
                } catch (Exception e) {
                    request.getSession().setAttribute("error", "Fecha inválida");
                    request.setAttribute("traspaso", traspasoActual);
                    request.getRequestDispatcher("editar_traspaso.jsp").forward(request, response);
                    return;
                }

                if (tipo == null || !(tipo.equalsIgnoreCase("compra") || tipo.equalsIgnoreCase("cesion")
                        || tipo.equalsIgnoreCase("libre") || tipo.equalsIgnoreCase("fin de contrato"))) {
                    request.getSession().setAttribute("error", "Tipo inválido");
                    request.setAttribute("traspaso", traspasoActual);
                    request.getRequestDispatcher("editar_traspaso.jsp").forward(request, response);
                    return;
                }

                Traspaso nuevoTraspaso = new Traspaso(idTraspaso, jugadorId, equipoOrigenId, equipoDestinoId,
                        temporadaId, fechaTraspaso, cantidad, tipo);
                int actualizados = dao.modificar(nuevoTraspaso);

                if (actualizados > 0) {
                    request.getSession().setAttribute("mensaje", "Traspaso actualizado correctamente");
                    response.sendRedirect("traspaso.jsp");
                } else {
                    request.getSession().setAttribute("error", "Traspaso no encontrado para actualizar");
                    response.sendRedirect("editar_traspaso.jsp");
                }

            } else if ("eliminar".equalsIgnoreCase(accion)) {
                String idStr = request.getParameter("id_traspaso");
                if (idStr == null || idStr.isEmpty()) {
                    request.getSession().setAttribute("error", "No se ha seleccionado un traspaso para eliminar");
                    response.sendRedirect("traspaso.jsp");
                    return;
                }

                int id = Integer.parseInt(idStr);
                int eliminados = dao.eliminarTraspaso(id);

                if (eliminados > 0) {
                    request.getSession().setAttribute("mensaje", "Traspaso eliminado correctamente");
                } else {
                    request.getSession().setAttribute("error", "Traspaso no encontrado");
                }
                response.sendRedirect("traspaso.jsp");

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Error interno del servidor");
            response.sendRedirect("traspaso.jsp");
        }
    }
}
