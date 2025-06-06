package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import es.tierno.daw.trasnferdaw.model.bbdd.Database;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDAO;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDBFactory;
import es.tierno.daw.trasnferdaw.model.entities.Usuario;

@WebServlet("/ContactoController")
public class ContactoController extends HttpServlet {

    private static final String ENVIAR = "enviar";
    private static final String CONTACTO__JSP = "/contacto/contacto.jsp";
    private static final String ERROR_ACCION = "Acción no reconocida";
    private static final String ACCION = "Acción";
    private static final String USUARIO = "usuario";
    private static final String EMAIL = "email";
    private static final String PASS = "pass";
    private static final String ERROR = "error";
    private static final String ERROR_PASS = "La contraseña debe tener al menos 8 caracteres.";
    private static final String CONTACTO_CONTROLLER = "ContactoController";
    private static final String ERROR_USUARIO = "El nombre de usuario no existe.";
    private static final String ERROR_CORREO = "El correo electrónico no existe.";
    private static final String MSG = "mensaje";
    private static final String MSG_INCIDENTE = "Incidente enviado correctamente.";
    private static final String ERROR_INCIDENTE = "Error al enviar el indicente";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter(ACCION);

        if (accion == null || ENVIAR.equals(accion)) {
            request.getRequestDispatcher(CONTACTO__JSP).forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, ERROR_ACCION);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            String nombre = request.getParameter(USUARIO);
            String email = request.getParameter(EMAIL);
            String pass = request.getParameter(PASS);

            HttpSession session = request.getSession();

            // Validación de longitud de contraseña
            if (pass == null || pass.length() < 8) {
                session.setAttribute(ERROR, ERROR_PASS);
                response.sendRedirect(CONTACTO_CONTROLLER);
                return;
            }

            // Validar si NO existe el nombre de usuario
            Usuario usuarioExistente = dao.buscarUsuarioPorNombre(nombre);
            if (usuarioExistente == null) {
                session.setAttribute(ERROR, ERROR_USUARIO);
                response.sendRedirect(CONTACTO_CONTROLLER);
                return;
            }

            // Validar si NO existe el correo
            if (!usuarioExistente.getEmail().equals(email)) {
                session.setAttribute(ERROR, ERROR_CORREO);
                response.sendRedirect(CONTACTO_CONTROLLER);
                return;
            }

            // Validar contraseña usando bcrypt
            if (!BCrypt.checkpw(pass, usuarioExistente.getContrasena())) {
                session.setAttribute(ERROR, ERROR_PASS);
                response.sendRedirect(CONTACTO_CONTROLLER);
                return;
            }

            // Si todo está correcto
            session.setAttribute(MSG, MSG_INCIDENTE);
            response.sendRedirect(CONTACTO_CONTROLLER);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    ERROR_INCIDENTE + e.getMessage());
        }
    }
}
