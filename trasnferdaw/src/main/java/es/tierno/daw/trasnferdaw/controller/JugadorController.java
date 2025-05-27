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
import es.tierno.daw.trasnferdaw.model.entities.Jugador;

@WebServlet("/JugadorController")
public class JugadorController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if ("modificar".equalsIgnoreCase(accion)) {
                String idStr = request.getParameter("id_jugador");
                if (idStr == null || idStr.trim().isEmpty()) {
                    request.getSession().setAttribute("error", "No se ha seleccionado un jugador para modificar");
                    response.sendRedirect("jugador.jsp");
                    return;
                }

                int idJugador = Integer.parseInt(idStr);
                Jugador jugador = dao.visualizarJugador(idJugador);

                if (jugador != null) {
                    request.setAttribute("jugador", jugador);
                    request.getRequestDispatcher("editar_jugador.jsp").forward(request, response);
                } else {
                    request.getSession().setAttribute("error", "No se encontró un jugador para modificar");
                    response.sendRedirect("jugador.jsp");
                }

            }else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Error interno del servidor");
            response.sendRedirect("jugador.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");
    
        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);
    
            if ("insertar".equalsIgnoreCase(accion)) {
                // Extraer parámetros comunes
                String nombre = request.getParameter("nombre");
                String alias = request.getParameter("alias");
                String fechaNacimientoStr = request.getParameter("fecha_nacimiento");
                String nacionalidad = request.getParameter("nacionalidad");
                String pieDominante = request.getParameter("pie_dominante");
                String posicion = request.getParameter("posicion");
                String representante = request.getParameter("representante");
                String seleccion = request.getParameter("seleccion");
    
                float altura, peso, valorMercado;
                try {
                    altura = Float.parseFloat(request.getParameter("altura"));
                    peso = Float.parseFloat(request.getParameter("peso"));
                    valorMercado = Float.parseFloat(request.getParameter("valor_mercado"));
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "Valor numérico inválido en peso, altura o mercado");
                    response.sendRedirect("jugador.jsp");
                    return;
                }
    
                if (altura <= 0) {
                    request.getSession().setAttribute("error", "Altura debe ser mayor a 0");
                    response.sendRedirect("jugador.jsp");
                    return;
                }

                if (peso <= 0) {
                    request.getSession().setAttribute("error", "Peso debe ser mayor a 0");
                    response.sendRedirect("jugador.jsp");
                    return;
                }

                if (valorMercado <= 0) {
                    request.getSession().setAttribute("error", "Valor de mercado debe ser mayor a 0");
                    response.sendRedirect("jugador.jsp");
                    return;
                }
    
                LocalDate fechaNacimiento;
                try {
                    fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
                    if (fechaNacimiento.isAfter(LocalDate.now())) {
                        request.getSession().setAttribute("error", "La fecha de nacimiento no puede ser futura");
                        response.sendRedirect("jugador.jsp");
                        return;
                    }
                } catch (Exception e) {
                    request.getSession().setAttribute("error", "Fecha de nacimiento inválida");
                    response.sendRedirect("jugador.jsp");
                    return;
                }
                if (pieDominante == null || !(pieDominante.equalsIgnoreCase("derecho")
                        || pieDominante.equalsIgnoreCase("izquierdo")
                        || pieDominante.equalsIgnoreCase("ambidiestro"))) {
                    request.getSession().setAttribute("error",
                            "Pie invádilo. Solo puede ser derecho, izquierdo o ambidiestro.");
                    response.sendRedirect("jugador.jsp");
                    return;
                }
    
                if (posicion == null || !(posicion.equalsIgnoreCase("delantero")
                        || posicion.equalsIgnoreCase("mediocampista")
                        || posicion.equalsIgnoreCase("defensa")
                        || posicion.equalsIgnoreCase("portero"))) {
                    request.getSession().setAttribute("error",
                            "Posición inválida. Solo puede ser delantero, mediocampista, defensa o portero.");
                    response.sendRedirect("jugador.jsp");
                    return;
                }
    
                Jugador jugador = new Jugador(nombre, alias, fechaNacimiento, nacionalidad, altura, peso, pieDominante,
                        valorMercado, posicion, representante, seleccion);
                dao.insertar(jugador);
    
                request.getSession().setAttribute("mensaje", "Jugador insertado correctamente");
                response.sendRedirect("jugador.jsp");
    
            } else if ("eliminar".equalsIgnoreCase(accion)) {
                int idJugador = Integer.parseInt(request.getParameter("id_jugador"));
                int registrosEliminados = dao.eliminarJugador(idJugador);
    
                if (registrosEliminados > 0) {
                    request.getSession().setAttribute("mensaje", "Jugador eliminado correctamente");
                } else {
                    request.getSession().setAttribute("error", "Jugador no encontrado");
                }
                response.sendRedirect("jugador.jsp");
    
            } else if ("actualizar".equalsIgnoreCase(accion)) {
                int idJugador = Integer.parseInt(request.getParameter("id_jugador"));
                Jugador jugador = dao.visualizarJugador(idJugador);
                String nombre = request.getParameter("nombre");
                String alias = request.getParameter("alias");
                String fechaNacimientoStr = request.getParameter("fecha_nacimiento");
                String nacionalidad = request.getParameter("nacionalidad");
                float altura, peso, valorMercado;
                try {
                    altura = Float.parseFloat(request.getParameter("altura"));
                    peso = Float.parseFloat(request.getParameter("peso"));
                    valorMercado = Float.parseFloat(request.getParameter("valor_mercado"));
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "Valor numérico inválido");
                    request.setAttribute("jugador", jugador);
                    request.getRequestDispatcher("editar_jugador.jsp").forward(request, response);
                    return;
                }
    
                String pieDominante = request.getParameter("pie_dominante");
                String posicion = request.getParameter("posicion");
                String representante = request.getParameter("representante");
                String seleccion = request.getParameter("seleccion");
    
                if (altura <= 0) {
                    request.getSession().setAttribute("error", "Altura debe ser mayor a 0");
                    request.setAttribute("jugador", jugador);
                    request.getRequestDispatcher("editar_jugador.jsp").forward(request, response);
                    return;
                }

                if (peso <= 0) {
                    request.getSession().setAttribute("error", "Peso debe ser mayor a 0");
                    request.setAttribute("jugador", jugador);
                    request.getRequestDispatcher("editar_jugador.jsp").forward(request, response);
                    return;
                }

                if (valorMercado <= 0) {
                    request.getSession().setAttribute("error", "Valor de mercado debe ser mayor a 0");
                    request.setAttribute("jugador", jugador);
                    request.getRequestDispatcher("editar_jugador.jsp").forward(request, response);
                    return;
                }
    
                LocalDate fechaNacimiento;
                try {
                    fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
                    if (fechaNacimiento.isAfter(LocalDate.now())) {
                        request.getSession().setAttribute("error", "La fecha de nacimiento no puede ser futura");
                        request.setAttribute("jugador", jugador);
                        request.getRequestDispatcher("editar_jugador.jsp").forward(request, response);
                        return;
                    }
                } catch (Exception e) {
                    request.getSession().setAttribute("error", "Fecha de nacimiento inválida");
                    request.setAttribute("jugador", jugador);
                    request.getRequestDispatcher("editar_jugador.jsp").forward(request, response);
                    return;
                }

                if (pieDominante == null || !(pieDominante.equalsIgnoreCase("derecho")
                        || pieDominante.equalsIgnoreCase("izquierdo")
                        || pieDominante.equalsIgnoreCase("ambidiestro"))) {
                    request.getSession().setAttribute("error",
                            "Pie invádilo. Solo puede ser derecho, izquierdo o ambidiestro.");
                            request.setAttribute("jugador", jugador);
                            request.getRequestDispatcher("editar_jugador.jsp").forward(request, response);
                    return;
                }
    
                if (posicion == null || !(posicion.equalsIgnoreCase("delantero")
                        || posicion.equalsIgnoreCase("mediocampista")
                        || posicion.equalsIgnoreCase("defensa")
                        || posicion.equalsIgnoreCase("portero"))) {
                    request.getSession().setAttribute("error",
                            "Posición inválida. Solo puede ser delantero, mediocampista, defensa o portero.");
                    request.setAttribute("jugador", jugador);
                    request.getRequestDispatcher("editar_jugador.jsp").forward(request, response);
                    return;
                }
    
                jugador = new Jugador(idJugador, nombre, alias, fechaNacimiento, nacionalidad, altura, peso,
                        pieDominante, valorMercado, posicion, representante, seleccion);
    
                dao.modificar(jugador);
    
                request.getSession().setAttribute("mensaje", "Jugador actualizado correctamente");
                response.sendRedirect("jugador.jsp");
    
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida");
            }
    
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Error interno del servidor: " + e.getMessage());
        }
    }
    
}