package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.tierno.daw.trasnferdaw.model.bbdd.Database;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDAO;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDBFactory;
import es.tierno.daw.trasnferdaw.model.entities.Equipo;

@WebServlet("/EquipoController")
public class EquipoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if ("modificar".equalsIgnoreCase(accion)) {
                String idStr = request.getParameter("id_equipo");
                if (idStr == null || idStr.trim().isEmpty()) {
                    request.getSession().setAttribute("error", "No se ha seleccionado un equipo para modificar");
                    response.sendRedirect("equipo.jsp");
                    return;
                }

                int idEquipo = Integer.parseInt(idStr);
                Equipo equipo = dao.visualizarEquipo(idEquipo);

                if (equipo != null) {
                    request.setAttribute("equipo", equipo);
                    request.getRequestDispatcher("editar_equipo.jsp").forward(request, response);
                } else {
                    request.getSession().setAttribute("error", "No se encontró el equipo");
                    response.sendRedirect("equipo.jsp");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Error interno del servidor");
            response.sendRedirect("equipo.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if ("insertar".equalsIgnoreCase(accion)) {

                String nombre = request.getParameter("nombre");
                String ciudad = request.getParameter("ciudad");
                String pais = request.getParameter("pais");
                String propietario = request.getParameter("propietario");
                String estadio = request.getParameter("estadio");
                String entrenador = request.getParameter("entrenador");

                int anio;
                float presupuesto;

                try {
                    anio = Integer.parseInt(request.getParameter("anio"));
                    presupuesto = Float.parseFloat(request.getParameter("presupuesto"));
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "Formato inválido en año o presupuesto");
                    response.sendRedirect("equipo.jsp");
                    return;
                }

                if (anio <= 1800) {
                    request.getSession().setAttribute("error", "El año de fundación debe ser mayor a 1800");
                    response.sendRedirect("equipo.jsp");
                    return;
                }

                if (presupuesto <= 0) {
                    request.getSession().setAttribute("error", "El presupuesto debe ser mayor a 0");
                    response.sendRedirect("equipo.jsp");
                    return;
                }

                Equipo equipo = new Equipo(nombre, ciudad, pais, anio, presupuesto, propietario, estadio, entrenador);
                dao.insertar(equipo);

                request.getSession().setAttribute("mensaje", "Equipo insertado correctamente");
                response.sendRedirect("equipo.jsp");

            } else if ("eliminar".equalsIgnoreCase(accion)) {

                int idEquipo = Integer.parseInt(request.getParameter("id_equipo"));
                int filas = dao.eliminarEquipo(idEquipo);

                if (filas > 0) {
                    request.getSession().setAttribute("mensaje", "Equipo eliminado correctamente");
                } else {
                    request.getSession().setAttribute("error", "Equipo no encontrado");
                }

                response.sendRedirect("equipo.jsp");

            } else if ("actualizar".equalsIgnoreCase(accion)) {

                int idEquipo = Integer.parseInt(request.getParameter("id_equipo"));
                Equipo equipo = dao.visualizarEquipo(idEquipo);

                if (equipo == null) {
                    request.getSession().setAttribute("error", "Equipo no encontrado");
                    response.sendRedirect("equipo.jsp");
                    return;
                }

                String nombre = request.getParameter("nombre");
                String ciudad = request.getParameter("ciudad");
                String pais = request.getParameter("pais");
                String propietario = request.getParameter("propietario");
                String estadio = request.getParameter("estadio");
                String entrenador = request.getParameter("entrenador");

                int anio;
                float presupuesto;

                try {
                    anio = Integer.parseInt(request.getParameter("anio"));
                    presupuesto = Float.parseFloat(request.getParameter("presupuesto"));
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute("error", "Formato inválido en año o presupuesto");
                    request.setAttribute("equipo", equipo);
                    request.getRequestDispatcher("editar_equipo.jsp").forward(request, response);
                    return;
                }

                if (anio <= 1800) {
                    request.getSession().setAttribute("error", "El año de fundación debe ser mayor a 1800");
                    request.setAttribute("equipo", equipo);
                    request.getRequestDispatcher("editar_equipo.jsp").forward(request, response);
                    return;
                }

                if (presupuesto <= 0) {
                    request.getSession().setAttribute("error", "El presupuesto debe ser mayor a 0");
                    request.setAttribute("equipo", equipo);
                    request.getRequestDispatcher("editar_equipo.jsp").forward(request, response);
                    return;
                }

                equipo = new Equipo(idEquipo, nombre, ciudad, pais, anio, presupuesto, propietario, estadio, entrenador);
                dao.modificar(equipo);

                request.getSession().setAttribute("mensaje", "Equipo actualizado correctamente");
                response.sendRedirect("equipo.jsp");

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Error interno al procesar la solicitud");
            response.sendRedirect("equipo.jsp");
        }
    }
}
