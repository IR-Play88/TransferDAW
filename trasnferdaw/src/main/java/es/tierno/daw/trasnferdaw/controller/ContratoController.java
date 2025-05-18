package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB;
import es.tierno.daw.trasnferdaw.model.entities.Contrato;

@WebServlet("/ContratoController")
public class ContratoController extends HttpServlet {

    String vista = "contrato.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        try {
            TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();

            if ("añadir".equalsIgnoreCase(accion)) {
                // Recoger parámetros del formulario (los nombres son los de tu JSP)
                String nombreJugador = request.getParameter("jugadorId");
                String nombreEquipo = request.getParameter("equipoId");
                String fechaInicioStr = request.getParameter("fechaInicio");
                String fechaFinStr = request.getParameter("fechaFin");
                String salarioStr = request.getParameter("salario");
                String tipoContrato = request.getParameter("tipoContrato");

                // Obtener IDs desde nombres si fuera necesario, si no, puedes adaptarlo
                int jugadorId = dao.obtenerIdPorNombreJugador(nombreJugador);
                int equipoId = dao.obtenerIdPorNombreEquipo(nombreEquipo);

                LocalDate fechaInicio = LocalDate.parse(fechaInicioStr);
                LocalDate fechaFin = LocalDate.parse(fechaFinStr);
                float salario = Float.parseFloat(salarioStr);

                Contrato contrato = new Contrato();
                contrato.setJugadorId(jugadorId);
                contrato.setEquipoId(equipoId);
                contrato.setFechaInicio(fechaInicio);
                contrato.setFechaFin(fechaFin);
                contrato.setSalario(salario);
                contrato.setTipoContrato(tipoContrato);

                dao.insertar(contrato);

                response.sendRedirect(vista);

            } else if ("eliminar".equalsIgnoreCase(accion)) {
                int idContrato = Integer.parseInt(request.getParameter("id_contrato"));
                int registrosEliminados = dao.eliminarContrato(idContrato);

                if (registrosEliminados > 0) {
                    response.sendRedirect(vista);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Contrato no encontrado");
                }

            } else if ("modificar".equalsIgnoreCase(accion)) {
                int idContrato = Integer.parseInt(request.getParameter("id_contrato"));
                Contrato contrato = dao.visualizarContrato(idContrato);

                if (contrato != null) {
                    request.setAttribute("contrato", contrato);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("editar_contrato.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Contrato no encontrado");
                }

            } else if ("actualizar".equalsIgnoreCase(accion)) {
                int idContrato = Integer.parseInt(request.getParameter("id_contrato"));
                int jugadorId = Integer.parseInt(request.getParameter("jugadorId"));
                int equipoId = Integer.parseInt(request.getParameter("equipoId"));
                LocalDate fechaInicio = LocalDate.parse(request.getParameter("fechaInicio"));
                LocalDate fechaFin = LocalDate.parse(request.getParameter("fechaFin"));
                float salario = Float.parseFloat(request.getParameter("salario"));
                String tipoContrato = request.getParameter("tipoContrato");

                Contrato contrato = new Contrato();
                contrato.setIdContrato(idContrato);
                contrato.setJugadorId(jugadorId);
                contrato.setEquipoId(equipoId);
                contrato.setFechaInicio(fechaInicio);
                contrato.setFechaFin(fechaFin);
                contrato.setSalario(salario);
                contrato.setTipoContrato(tipoContrato);

                dao.modificar(contrato);

                response.sendRedirect(vista);

            } else {
                // Por defecto redirigir a la vista principal
                response.sendRedirect(vista);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // En este caso, puedes redirigir al doGet para simplificar el manejo
        doGet(request, response);
    }
}
