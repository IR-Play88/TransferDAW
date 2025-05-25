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

@WebServlet("/CuentaServlet")
public class CuentaServlet extends HttpServlet {

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
                response.sendRedirect("creearCuenta.jsp");
                return;
            }

            // Validar si ya existe el nombre de usuario
            if (dao.buscarUsuarioPorNombre(nombre) != null) {
                session.setAttribute("error", "El nombre de usuario ya está registrado.");
                response.sendRedirect("creearCuenta.jsp");
                return;
            }

            // Validar si ya existe el correo
            if (dao.buscarUsuarioPorEmail(email) != null) {
                session.setAttribute("error", "El correo electrónico ya está registrado.");
                response.sendRedirect("creearCuenta.jsp");
                return;
            }

            // Crear nuevo usuario
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setContrasena(pass);
            usuario.setRol("admin");

            dao.insertar(usuario);

            session.setAttribute("mensaje", "Cuenta creada correctamente.");
            response.sendRedirect("creearCuenta.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al crear cuenta: " + e.getMessage());
        }
    }
}
