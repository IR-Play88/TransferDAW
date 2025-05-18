package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB;
import es.tierno.daw.trasnferdaw.model.entities.Usuario;


@WebServlet("/LoginServlet")
public class LoginControlador extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        try{
        TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
        Usuario user = dao.buscarUsuarioPorNombreYPassword(usuario, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", user.getNombre());
            session.setAttribute("rol", user.getRol());
            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("error", "Credenciales incorrectas");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }catch (Exception e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error: " + e.getMessage());
    }
    }
}

