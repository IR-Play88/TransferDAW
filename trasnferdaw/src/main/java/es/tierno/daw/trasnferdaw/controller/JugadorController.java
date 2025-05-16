package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB;
import es.tierno.daw.trasnferdaw.model.entities.Jugador;

@WebServlet("/JugadorController")
public class JugadorController extends HttpServlet {
    int id;

    String lista = "jugador.jsp";
    String edita = "jugador.jsp";
    Jugador jugador = new Jugador();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        if ("añadir".equalsIgnoreCase(accion)) {
            try {
                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB(); // ✔️ Se instancia dentro de try-catch

                String nombre = request.getParameter("nombre");
                String alias = request.getParameter("alias");
                String fechaNacimientoStr = request.getParameter("fecha_nacimiento");
                String nacionalidad = request.getParameter("nacionalidad");
                float altura = Float.parseFloat(request.getParameter("altura"));
                float peso = Float.parseFloat(request.getParameter("peso"));
                String pieDominante = request.getParameter("pie_dominante");
                float valorMercado = Float.parseFloat(request.getParameter("valor_mercado"));
                String posicion = request.getParameter("posicion");
                String representante = request.getParameter("representante");
                String seleccion = request.getParameter("seleccion");

                Jugador jugador = new Jugador();
                jugador.setNombre(nombre);
                jugador.setAlias(alias);
                jugador.setFechaNacimiento(LocalDate.parse(fechaNacimientoStr));
                jugador.setNacionalidad(nacionalidad);
                jugador.setAltura(altura);
                jugador.setPeso(peso);
                jugador.setPieDominante(pieDominante);
                jugador.setValorMercado(valorMercado);
                jugador.setPosicion(posicion);
                jugador.setRepresentanteNombre(representante);
                jugador.setSeleccionNombre(seleccion);

                dao.insertar(jugador);
                response.sendRedirect("jugador.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al insertar jugador");
            }
        } else if ("eliminar".equalsIgnoreCase(accion)) {
            try {
                int idJugador = Integer.parseInt(request.getParameter("id_jugador"));
                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
        
                // Llamada al método eliminarJugador del DAO
                int registrosEliminados = dao.eliminarJugador(idJugador);
        
                if (registrosEliminados > 0) {
                    response.sendRedirect("jugador.jsp"); // Redirige a la lista de jugadores después de la eliminación
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Jugador no encontrado");
                }
        
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar jugador");
            }
        }else if ("modificar".equalsIgnoreCase(accion)) {
            try {
                int idJugador = Integer.parseInt(request.getParameter("id_jugador"));
                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
                Jugador jugador = dao.visualizarJugador(idJugador);
        
                if (jugador != null) {
                    request.setAttribute("jugador", jugador);
                    request.getRequestDispatcher("editar_jugador.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Jugador no encontrado para modificar");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar jugador para modificar");
            }
        }else  if ("actualizar".equals(accion)) {
            try {
                int idJugador = Integer.parseInt(request.getParameter("id_jugador"));
                String nombre = request.getParameter("nombre");
                String alias = request.getParameter("alias");
                String fechaNacimientoStr = request.getParameter("fecha_nacimiento");
                String nacionalidad = request.getParameter("nacionalidad");
                float altura = Float.parseFloat(request.getParameter("altura"));
                float peso = Float.parseFloat(request.getParameter("peso"));
                String pieDominante = request.getParameter("pie_dominante");
                float valorMercado = Float.parseFloat(request.getParameter("valor_mercado"));
                String posicion = request.getParameter("posicion");
                String representante = request.getParameter("representante");
                String seleccion = request.getParameter("seleccion");

                LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);

                Jugador jugador = new Jugador();
                jugador.setIdJugador(idJugador);
                jugador.setNombre(nombre);
                jugador.setAlias(alias);
                jugador.setFechaNacimiento(fechaNacimiento);
                jugador.setNacionalidad(nacionalidad);
                jugador.setAltura(altura);
                jugador.setPeso(peso);
                jugador.setPieDominante(pieDominante);
                jugador.setValorMercado(valorMercado);
                jugador.setPosicion(posicion);
                jugador.setRepresentanteNombre(representante);
                jugador.setSeleccionNombre(seleccion);

                // Actualizar en base de datos
                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB(); 
                dao.modificar(jugador);

                // Redirigir a la página de lista o detalle
                response.sendRedirect("jugador.jsp");

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar el jugador");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida");
        }
        

        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

}
