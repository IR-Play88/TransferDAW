package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB;
import es.tierno.daw.trasnferdaw.model.entities.Equipo;

@WebServlet("/EquipoController")
public class EquipoController extends HttpServlet {
    int id;

    String lista = "equipo.jsp";
    String edita = "equipo.jsp";
    Equipo equipo = new Equipo();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        if ("añadir".equalsIgnoreCase(accion)) {
            try {
                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB(); // ✔️ Se instancia dentro de try-catch

                String nombre = request.getParameter("nombre");
                String ciudad = request.getParameter("ciudad");
                String pais = request.getParameter("pais");
                int anio = Integer.parseInt(request.getParameter("anio"));
                float presupuesto = Float.parseFloat(request.getParameter("presupuesto"));
                String propietario = request.getParameter("propietario");
                String estadio = request.getParameter("estadio");
                String entrenador = request.getParameter("entrenador");

                Equipo equipo = new Equipo();
                equipo.setNombre(nombre);
                equipo.setCiudad(ciudad);
                equipo.setPais(pais);
                equipo.setAnioFundacion(anio);
                equipo.setPresupuesto(presupuesto);
                equipo.setPropietario(propietario);
                equipo.setEstadioNombre(estadio);
                equipo.setEntrenadorNombre(entrenador);

                dao.insertar(equipo);
                response.sendRedirect("equipo.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al insertar jugador");
            }
        } else if ("eliminar".equalsIgnoreCase(accion)) {
            try {
                int idEquipo = Integer.parseInt(request.getParameter("id_equipo"));
                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
        
                // Llamada al método eliminarJugador del DAO
                int registrosEliminados = dao.eliminarEquipo(idEquipo);
        
                if (registrosEliminados > 0) {
                    response.sendRedirect("equipo.jsp"); // Redirige a la lista de jugadores después de la eliminación
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Equipo no encontrado");
                }
        
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar equipo");
            }
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

}

