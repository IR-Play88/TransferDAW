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
import es.tierno.daw.trasnferdaw.model.entities.ValorMercadoHistorial;
import es.tierno.daw.trasnferdaw.model.exception.BBDDException;

@WebServlet("/ValorMercadoController")
public class ValorMercadoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if ("modificar".equalsIgnoreCase(accion)) {
                String idStr = request.getParameter("id_historial");
                if (idStr == null || idStr.isEmpty()) {
                    request.getSession().setAttribute("error", "No se ha seleccionado un historial para modificar");
                    response.sendRedirect("valor_mercado.jsp");
                    return;
                }

                int id = Integer.parseInt(idStr);
                ValorMercadoHistorial vm = dao.visualizarValorMercadoHistorial(id);

                if (vm != null) {
                    request.setAttribute("valorMercado", vm);
                    request.getRequestDispatcher("editar_valor_mercado.jsp").forward(request, response);
                } else {
                    request.getSession().setAttribute("error", "Historial no encontrado");
                    response.sendRedirect("valor_mercado.jsp");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Error interno del servidor");
            response.sendRedirect("valor_mercado.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if ("insertar".equalsIgnoreCase(accion)) {
                String nombreJugador = request.getParameter("jugador");
                String fechaStr = request.getParameter("fecha");
                String valorStr = request.getParameter("valorMercado");
                String motivo = request.getParameter("motivo");

                int jugadorId;
                try {
                    jugadorId = dao.obtenerIdPorNombreJugador(nombreJugador);
                } catch (BBDDException e) {
                    request.getSession().setAttribute("error",
                            "Jugador no encontrado. Verifique que exista en la sección 'Jugadores'.");
                            response.sendRedirect("valor_mercado.jsp");
                    return;
                }

                LocalDate fecha;
                long valorMercado;

                // Validación de fecha
                try {
                    fecha = LocalDate.parse(fechaStr);
                    if (fecha.isAfter(LocalDate.now())) {
                        request.getSession().setAttribute("error", "La fecha no puede ser futura");
                        response.sendRedirect("valor_mercado.jsp");
                        return;
                    }
                } catch (Exception e) {
                    request.getSession().setAttribute("error", "Fecha inválida");
                    response.sendRedirect("valor_mercado.jsp");
                    return;
                }

                // Validación del valor de mercado
                try {
                    valorMercado = Long.parseLong(valorStr);
                    if (valorMercado <= 0) {
                        request.getSession().setAttribute("error", "El valor debe ser mayor a 0");
                        response.sendRedirect("valor_mercado.jsp");
                        return;
                    }
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "Valor de mercado inválido");
                    response.sendRedirect("valor_mercado.jsp");
                    return;
                }

                ValorMercadoHistorial vm = new ValorMercadoHistorial(jugadorId, fecha, valorMercado, motivo);
                dao.insertar(vm);

                request.getSession().setAttribute("mensaje", "Historial de valor insertado correctamente");
                response.sendRedirect("valor_mercado.jsp");

            } else if ("actualizar".equalsIgnoreCase(accion)) {
                int idHistorial = Integer.parseInt(request.getParameter("id_historial"));
                ValorMercadoHistorial valorMercadoHistorial = dao.visualizarValorMercadoHistorial(idHistorial);
                String nombreJugador = request.getParameter("jugador");
                String fechaStr = request.getParameter("fecha");
                String valorStr = request.getParameter("valorMercado");
                String motivo = request.getParameter("motivo");

                int jugadorId;
                try {
                    jugadorId = dao.obtenerIdPorNombreJugador(nombreJugador);
                } catch (Exception e) {
                    request.getSession().setAttribute("error",
                            "Jugador no encontrado. Verifique que exista en la sección 'Jugadores'.");
                    request.setAttribute("valorMercado", valorMercadoHistorial);
                    request.getRequestDispatcher("editar_valor_mercado.jsp").forward(request, response);
                    return;
                }

                LocalDate fecha;
                long valorMercado;

                // Validación de fecha
                try {
                    fecha = LocalDate.parse(fechaStr);
                    if (fecha.isAfter(LocalDate.now())) {
                        request.getSession().setAttribute("error", "La fecha no puede ser futura");
                        request.setAttribute("valorMercado", valorMercadoHistorial);
                        request.getRequestDispatcher("editar_valor_mercado.jsp").forward(request, response);
                        return;
                    }
                } catch (Exception e) {
                    request.getSession().setAttribute("error", "Fecha inválida");
                    request.setAttribute("valorMercado", valorMercadoHistorial);
                    request.getRequestDispatcher("editar_valor_mercado.jsp").forward(request, response);
                    return;
                }

                // Validación del valor de mercado
                try {
                    valorMercado = Long.parseLong(valorStr);
                    if (valorMercado <= 0) {
                        request.getSession().setAttribute("error", "El valor debe ser mayor a 0");
                        request.setAttribute("valorMercado", valorMercadoHistorial);
                        request.getRequestDispatcher("editar_valor_mercado.jsp").forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "Valor de mercado inválido");
                    request.setAttribute("valorMercado", valorMercadoHistorial);
                    request.getRequestDispatcher("editar_valor_mercado.jsp").forward(request, response);
                    return;
                }

                ValorMercadoHistorial vm = new ValorMercadoHistorial(idHistorial, jugadorId, fecha, valorMercado,
                        motivo);
                int actualizados = dao.modificar(vm);

                if (actualizados > 0) {
                    request.getSession().setAttribute("mensaje", "Historial actualizado correctamente");
                    response.sendRedirect("valor_mercado.jsp");
                } else {
                    request.getSession().setAttribute("error", "Historial no encontrado para actualizar");
                    response.sendRedirect("editar_valor_mercado.jsp");
                }

            } else if ("eliminar".equalsIgnoreCase(accion)) {
                String idStr = request.getParameter("id_historial");
                if (idStr == null || idStr.isEmpty()) {
                    request.getSession().setAttribute("error", "No se ha seleccionado un historial para eliminar");
                    response.sendRedirect("valor_mercado.jsp");
                    return;
                }
                int id = Integer.parseInt(idStr);

                int eliminados = dao.eliminarValorMercadoHistorial(id);

                if (eliminados > 0) {
                    request.getSession().setAttribute("mensaje", "Historial eliminado correctamente");
                } else {
                    request.getSession().setAttribute("error", "Historial no encontrado");
                }
                response.sendRedirect("valor_mercado.jsp");

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Error interno del servidor");
            response.sendRedirect("valor_mercado.jsp");
        }
    }
}
