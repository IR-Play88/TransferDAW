package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB;
import es.tierno.daw.trasnferdaw.model.entities.ValorMercadoHistorial;

@WebServlet("/ValorMercadoController")
public class ValorMercadoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        if ("añadir".equalsIgnoreCase(accion)) {
            try {
                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();

                // Recoger nombre del jugador (en vez de ID directamente)
                String nombreJugador = request.getParameter("jugador");
                int jugadorId = dao.obtenerIdPorNombreJugador(nombreJugador);

                LocalDate fecha = LocalDate.parse(request.getParameter("fecha"));
                float valorMercado = Float.parseFloat(request.getParameter("valorMercado"));
                String motivo = request.getParameter("motivo");

                ValorMercadoHistorial vm = new ValorMercadoHistorial();
                vm.setJugadorId(jugadorId);
                vm.setFecha(fecha);
                vm.setValorMercado(valorMercado);
                vm.setMotivo(motivo != null && !motivo.trim().isEmpty() ? motivo : null);

                dao.insertar(vm);
                response.sendRedirect("valor_mercado.jsp");

            }  catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Error al añadir contrato: " + e.getMessage());
            }

        } else if ("eliminar".equalsIgnoreCase(accion)) {
            try {
                int idHistorial = Integer.parseInt(request.getParameter("id_historial"));
                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();

                int registrosEliminados = dao.eliminarValorMercadoHistorial(idHistorial);

                if (registrosEliminados > 0) {
                    response.sendRedirect("valor_mercado.jsp");
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Historial no encontrado");
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar historial");
            }
        }else if ("modificar".equalsIgnoreCase(accion)) {
            try {
                int idHistorial = Integer.parseInt(request.getParameter("id_historial"));
                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();

                ValorMercadoHistorial vm = dao.visualizarValorMercadoHistorial(idHistorial);
                if (vm != null) {
                    request.setAttribute("valorMercado", vm);
                    request.getRequestDispatcher("editar_valor_mercado.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Registro no encontrado para modificar");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar registro para modificar");
            }

        } else if ("actualizar".equalsIgnoreCase(accion)) {
            try {
                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
        
                int idHistorial = Integer.parseInt(request.getParameter("id_historial"));
                String nombreJugador = request.getParameter("jugador");
                int jugadorId = dao.obtenerIdPorNombreJugador(nombreJugador);
        
                LocalDate fecha = LocalDate.parse(request.getParameter("fecha"));
                float valorMercado = Float.parseFloat(request.getParameter("valorMercado"));
                String motivo = request.getParameter("motivo");
        
                ValorMercadoHistorial vm = new ValorMercadoHistorial();
                vm.setIdHistorial(idHistorial);
                vm.setJugadorId(jugadorId);
                vm.setFecha(fecha);
                vm.setValorMercado(valorMercado);
                vm.setMotivo(motivo != null && !motivo.trim().isEmpty() ? motivo : null);
        
                int registrosActualizados = dao.modificar(vm);
        
                if (registrosActualizados > 0) {
                    response.sendRedirect("valor_mercado.jsp");
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Registro no encontrado para actualizar");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Error al actualizar registro: " + e.getMessage());
            }

        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // No utilizado
    }
}
