package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import es.tierno.daw.trasnferdaw.model.bbdd.Database;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDAO;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDBFactory;
import es.tierno.daw.trasnferdaw.model.entities.Usuario;

@WebServlet("/CuentaController")
public class CuentaController extends HttpServlet {
    private static final String CREAR = "crear";
    private static final String CREAR_CUENTA_JSP = "/cuenta/crear_cuenta.jsp";
    private static final String ERROR_ACCION = "Acción no reconocida";
    private static final String ACCION = "Acción";
    private static final String USUARIO = "usuario";
    private static final String EMAIL = "email";
    private static final String PASS = "pass";
    private static final String ERROR = "error";
    private static final String ERROR_PASS = "La contraseña debe tener al menos 8 caracteres.";
    private static final String CREAR_CUENTA = "CuentaController";
    private static final String ERROR_USUARIO = "El nombre de usuario ya está registrado.";
    private static final String ERROR_CORREO = "El correo electrónico ya está registrado.";
    private static final String ADMIN = "admin";
    private static final String MSG = "mensaje";
    private static final String MSG_CUENTA = "Cuenta creada correctamente.";
    private static final String ERROR_CUENTA = "Error al crear cuenta";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter(ACCION);

        if (accion == null || CREAR.equals(accion)) {
            request.getRequestDispatcher(CREAR_CUENTA_JSP).forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, ERROR_ACCION);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            String nombre = request.getParameter(USUARIO);
            String email = request.getParameter(EMAIL);
            String pass = request.getParameter(PASS);

            HttpSession session = request.getSession();

            // Validación de longitud de contraseña
            if (pass == null || pass.length() < 8) {
                session.setAttribute(ERROR, ERROR_PASS);
                response.sendRedirect(CREAR_CUENTA);
                return;
            }

            // Validar si ya existe el nombre de usuario
            if (dao.buscarUsuarioPorNombre(nombre) != null) {
                session.setAttribute(ERROR, ERROR_USUARIO);
                response.sendRedirect(CREAR_CUENTA);
                return;
            }

            // Validar si ya existe el correo
            if (dao.buscarUsuarioPorEmail(email) != null) {
                session.setAttribute(ERROR, ERROR_CORREO);
                response.sendRedirect(CREAR_CUENTA);
                return;
            }

            // Crear nuevo usuario
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setContrasena(pass); 
            usuario.setRol(ADMIN);

            dao.insertar(usuario);

            session.setAttribute(MSG, MSG_CUENTA);
            response.sendRedirect(CREAR_CUENTA);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ERROR_CUENTA + e.getMessage());
        }
    }
}
