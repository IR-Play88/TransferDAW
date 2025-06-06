package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.tierno.daw.trasnferdaw.model.bbdd.Database;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDAO;
import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAWDBFactory;
import es.tierno.daw.trasnferdaw.model.entities.Temporada;

@WebServlet("/TemporadaController")
public class TemporadaController extends HttpServlet {
    
    private static final String ACCION= "accion";
    private static final String ERROR = "error";

    private static final String OPC_MODIFICAR= "modificar";


    private static final String LISTA_TEMPORADAS= "listaTemporadas";
    private static final String TEMPORADA_JSP= "/temporada/temporada.jsp";

    private static final String TEMPORADA_CONTROLLER= "TemporadaController";


    private static final String ERROR_INTERNO = "Error interno en el servidor: ";
    

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
        String accion = request.getParameter(ACCION);

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if (accion == null || !accion.equals(OPC_MODIFICAR)) {
                List<Temporada> list = dao.listarTemporadas(); 
                request.setAttribute(LISTA_TEMPORADAS, list);
                request.getRequestDispatcher(TEMPORADA_JSP).forward(request, response);
            } 
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute(ERROR, ERROR_INTERNO);
            response.sendRedirect(TEMPORADA_CONTROLLER);
        }
    }

    
}
