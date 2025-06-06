package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import es.tierno.daw.trasnferdaw.model.bbdd.Database;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDAO;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDBFactory;
import es.tierno.daw.trasnferdaw.model.entities.Usuario;


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final String INDEX= "index.jsp";
    private static final String USUARIO= "usuario";
    private static final String PASSWORD= "password";
    private static final String ROL= "rol";
    private static final String ERROR= "error";
    private static final String ERROR_CREDENCIALES= "Credenciales incorrectas";
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter(USUARIO);
        String password = request.getParameter(PASSWORD);

        try{
        TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);
        Usuario user = dao.buscarUsuarioPorNombre(usuario);

      if (user != null && BCrypt.checkpw(password, user.getContrasena())) {
                HttpSession session = request.getSession();
                session.setAttribute(USUARIO, user.getNombre());
                session.setAttribute(ROL, user.getRol());
                response.sendRedirect(INDEX);
            } else {
                request.getSession().setAttribute(ERROR, ERROR_CREDENCIALES);
                response.sendRedirect(INDEX);
            }
    }catch (Exception e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ERROR + e.getMessage());
    }
    }
}

