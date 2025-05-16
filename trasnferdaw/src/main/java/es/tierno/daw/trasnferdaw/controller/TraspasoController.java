package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB;
import es.tierno.daw.trasnferdaw.model.entities.Traspaso;

@WebServlet("/TraspasoController")
public class TraspasoController extends HttpServlet {

    String vista = "traspaso.jsp";
    Traspaso traspaso = new Traspaso();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        if ("añadir".equalsIgnoreCase(accion)) {
            try {
                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
    
                // Recoger nombres (no IDs)
                String nombreJugador = request.getParameter("jugadorId");
                String nombreEquipoOrigen = request.getParameter("equipoOrigenId");
                String nombreEquipoDestino = request.getParameter("equipoDestinoId");
                String nombreTemporada = request.getParameter("temporadaId");
    
                // Obtener IDs desde nombres
                int jugadorId = dao.obtenerIdPorNombreJugador(nombreJugador);
                int equipoOrigenId = dao.obtenerIdPorNombreEquipo(nombreEquipoOrigen);
                int equipoDestinoId = dao.obtenerIdPorNombreEquipo(nombreEquipoDestino);
                int temporadaId = dao.obtenerIdPorNombreTemporada(nombreTemporada);
    
                // Otros campos
                String fechaTraspaso = request.getParameter("fechaTraspaso");
                float cantidad = 0f;
                String cantidadParam = request.getParameter("cantidad");
                if (cantidadParam != null && !cantidadParam.isEmpty()) {
                    cantidad = Float.parseFloat(cantidadParam);
                }
                String tipo = request.getParameter("tipo");
    
                Traspaso traspaso = new Traspaso();
                traspaso.setJugadorId(jugadorId);
                traspaso.setEquipoOrigenId(equipoOrigenId);
                traspaso.setEquipoDestinoId(equipoDestinoId);
                traspaso.setTemporadaId(temporadaId);
                traspaso.setFechaTraspaso(LocalDate.parse(fechaTraspaso));
                traspaso.setCantidad(cantidad);
                traspaso.setTipo(tipo);
    
                dao.insertar(traspaso);
    
                response.sendRedirect("traspaso.jsp");
    
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al añadir traspaso: " + e.getMessage());
            }

        } else if ("eliminar".equalsIgnoreCase(accion)) {
            try {
                int idTraspaso = Integer.parseInt(request.getParameter("id_traspaso"));
                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();

                int registrosEliminados = dao.eliminarTraspaso(idTraspaso);

                if (registrosEliminados > 0) {
                    response.sendRedirect("traspaso.jsp");
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Traspaso no encontrado");
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar traspaso");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // No se utiliza en este caso
    }
}
