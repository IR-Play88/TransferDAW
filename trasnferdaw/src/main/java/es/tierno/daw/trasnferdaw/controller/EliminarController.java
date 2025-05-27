package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import es.tierno.daw.trasnferdaw.model.bbdd.Database;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDAO;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDBFactory;
import es.tierno.daw.trasnferdaw.model.entities.Usuario;

@WebServlet("/EliminarServlet")
public class EliminarController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            String nombre = request.getParameter("usuario");
            String email = request.getParameter("email");
            String pass = request.getParameter("pass");

            HttpSession session = request.getSession();

            // Validación de longitud de contraseña
            if (pass == null || pass.length() < 8) {
                session.setAttribute("error", "La contraseña debe tener al menos 8 caracteres.");
                response.sendRedirect("eliminar_cuenta.jsp");
                return;
            }

            // Validar si NO existe el nombre de usuario
            Usuario usuarioExistente = dao.buscarUsuarioPorNombre(nombre);
            if (usuarioExistente == null) {
                session.setAttribute("error", "El nombre de usuario no existe.");
                response.sendRedirect("eliminar_cuenta.jsp");
                return;
            }

            // Validar si NO existe el correo
            if (!usuarioExistente.getEmail().equals(email)) {
                session.setAttribute("error", "El correo electrónico no coincide.");
                response.sendRedirect("eliminar_cuenta.jsp");
                return;
            }

            // Validar contraseña
            if (!usuarioExistente.getContrasena().equals(pass)) {
                session.setAttribute("error", "Contraseña incorrecta.");
                response.sendRedirect("eliminar_cuenta.jsp");
                return;
            }

            // Eliminar usuario
            dao.eliminar(usuarioExistente);
            // Cerrar sesión solo si el usuario que se elimina es el mismo que está logueado
            String usuarioActual = (String) session.getAttribute("usuario");
            if (usuarioActual != null && usuarioActual.equals(nombre)) {
                session.invalidate();
                response.sendRedirect("index.jsp");
            } else {
                session.setAttribute("mensaje", "Cuenta eliminada correctamente.");
                response.sendRedirect("eliminar_cuenta.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Error al eliminar cuenta: " + e.getMessage());
        }
    }
}
