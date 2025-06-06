package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;
import java.util.List;

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
    private static final String ACCION= "accion";
    private static final String ERROR = "error";
    private static final String MSG= "mensaje";

    private static final String EQUIPO = "equipo";

    private static final String OPC_MODIFICAR= "modificar";
    private static final String OPC_ELIMINAR= "eliminar";
    private static final String OPC_ACTUALIZAR= "actualizar";
    private static final String OPC_INSERTAR= "insertar";

    private static final String LISTA_EQUIPOS= "listaEquipos";
    private static final String EQUIPO_JSP= "/equipo/equipo.jsp";
    private static final String EDITAR_EQUIPO = "/equipo/editar_equipo.jsp";
    private static final String EQUIPO_CONTROLLER= "EquipoController";

    private static final String EQUIPO_ID= "id_equipo";
    private static final String EQUIPO_NOMBRE= "nombre";
    private static final String EQUIPO_CIUDAD= "ciudad";
    private static final String EQUIPO_PAIS= "pais";
    private static final String EQUIPO_ENTRENADOR= "entrenador";
    private static final String EQUIPO_PROPIETARIO= "propietario";
    private static final String EQUIPO_ESTADIO= "estadio";
    private static final String EQUIPO_ANIO= "anio";
    private static final String EQUIPO_PRESUPUESTO= "presupuesto";

    private static final String ERROR_ACCION = "Acción no reconocida";
    private static final String ERROR_MODIFICAR= "No se ha seleccionado un equipo para modificar";
    private static final String ERROR_ELIMINAR = "Equipo no encontrado para eliminar";
    private static final String ERROR_INTERNO = "Error interno en el servidor: ";
    private static final String ERROR_ANIO= "El año de fundación debe ser mayor a 1800";
    private static final String ERROR_PRESUPUESTO= "El presupuesto debe ser mayor a 0";
  

    private static final String MSG_EQUIPO_INSERTADO= "Equipo insertado correctamente";
    private static final String MSG_EQUIPO_MODIFICADO= "Equipo modificado correctamente";
    private static final String MSG_EQUIPO_ELIMINADO= "Equipo eliminado correctamente";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter(ACCION);

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if (accion == null || !accion.equals(OPC_MODIFICAR)) {
                List<Equipo> list = dao.listarEquipos(); 
                request.setAttribute(LISTA_EQUIPOS, list);
                request.getRequestDispatcher(EQUIPO_JSP).forward(request, response);
            } else if(OPC_MODIFICAR.equalsIgnoreCase(accion)) {
                String idStr = request.getParameter(EQUIPO_ID);
                if (idStr == null || idStr.trim().isEmpty()) {
                    request.getSession().setAttribute(ERROR,ERROR_MODIFICAR );
                    response.sendRedirect(EQUIPO_CONTROLLER);
                    return;
                }

                int idEquipo = Integer.parseInt(idStr);
                Equipo equipo = dao.visualizarEquipo(idEquipo);

                if (equipo != null) {
                    request.setAttribute(EQUIPO, equipo);
                    request.getRequestDispatcher(EDITAR_EQUIPO).forward(request, response);
                } else {
                    request.getSession().setAttribute(ERROR, ERROR_MODIFICAR);
                    response.sendRedirect(EQUIPO_CONTROLLER);
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ERROR_ACCION);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute(ERROR, ERROR_INTERNO);
            response.sendRedirect(EQUIPO_CONTROLLER);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter(ACCION);

        try {
            TransferDAWDAO dao = TransferDAWDBFactory.obtener(Database.MARIADB);

            if (OPC_INSERTAR.equalsIgnoreCase(accion)) {

                String nombre = request.getParameter(EQUIPO_NOMBRE);
                String ciudad = request.getParameter(EQUIPO_CIUDAD);
                String pais = request.getParameter(EQUIPO_PAIS);
                String propietario = request.getParameter(EQUIPO_PROPIETARIO);
                String estadio = request.getParameter(EQUIPO_ESTADIO);
                String entrenador = request.getParameter(EQUIPO_ENTRENADOR);

                int anio = Integer.parseInt(request.getParameter(EQUIPO_ANIO));;
                
                float presupuesto =  Float.parseFloat(request.getParameter(EQUIPO_PRESUPUESTO));;

                if (anio <= 1800) {
                    request.getSession().setAttribute(ERROR, ERROR_ANIO);
                    response.sendRedirect(EQUIPO_CONTROLLER);
                    return;
                }

                if (presupuesto <= 0) {
                    request.getSession().setAttribute(ERROR, ERROR_PRESUPUESTO);
                    response.sendRedirect(EQUIPO_CONTROLLER);
                    return;
                }

                Equipo equipo = new Equipo(nombre, ciudad, pais, anio, presupuesto, propietario, estadio, entrenador);
                dao.insertar(equipo);

                request.getSession().setAttribute(MSG, MSG_EQUIPO_INSERTADO);
                response.sendRedirect(EQUIPO_CONTROLLER);

            } else if (OPC_ELIMINAR.equalsIgnoreCase(accion)) {

                int idEquipo = Integer.parseInt(request.getParameter(EQUIPO_ID));
                int filas = dao.eliminarEquipo(idEquipo);

                if (filas > 0) {
                    request.getSession().setAttribute(MSG, MSG_EQUIPO_ELIMINADO);
                } else {
                    request.getSession().setAttribute(ERROR, ERROR_ELIMINAR);
                }

                response.sendRedirect(EQUIPO_CONTROLLER);

            } else if (OPC_ACTUALIZAR.equalsIgnoreCase(accion)) {

                int idEquipo = Integer.parseInt(request.getParameter(EQUIPO_ID));
                Equipo equipo = dao.visualizarEquipo(idEquipo);

                if (equipo == null) {
                    request.getSession().setAttribute(ERROR, ERROR_MODIFICAR);
                    response.sendRedirect(EQUIPO_JSP);
                    return;
                }

                String nombre = request.getParameter(EQUIPO_NOMBRE);
                String ciudad = request.getParameter(EQUIPO_CIUDAD);
                String pais = request.getParameter(EQUIPO_PAIS);
                String propietario = request.getParameter(EQUIPO_PROPIETARIO);
                String estadio = request.getParameter(EQUIPO_ESTADIO);
                String entrenador = request.getParameter(EQUIPO_ENTRENADOR);

                int anio = Integer.parseInt(request.getParameter(EQUIPO_ANIO));
                float presupuesto = Float.parseFloat(request.getParameter(EQUIPO_PRESUPUESTO));

                if (anio <= 1800) {
                    request.getSession().setAttribute(ERROR, ERROR_ANIO);
                    request.setAttribute(EQUIPO, equipo);
                    request.getRequestDispatcher(EDITAR_EQUIPO).forward(request, response);
                    return;
                }

                if (presupuesto <= 0) {
                    request.getSession().setAttribute(ERROR, ERROR_PRESUPUESTO);
                    request.setAttribute(EQUIPO, equipo);
                    request.getRequestDispatcher(EDITAR_EQUIPO).forward(request, response);
                    return;
                }

                equipo = new Equipo(idEquipo, nombre, ciudad, pais, anio, presupuesto, propietario, estadio, entrenador);
                dao.modificar(equipo);

                request.getSession().setAttribute(MSG, MSG_EQUIPO_MODIFICADO);
                response.sendRedirect(EQUIPO_CONTROLLER);

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ERROR_ACCION);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ERROR_INTERNO + e.getMessage());
        }
    }
}
