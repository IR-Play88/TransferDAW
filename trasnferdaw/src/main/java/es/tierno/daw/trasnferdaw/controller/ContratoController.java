package es.tierno.daw.trasnferdaw.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB;
import es.tierno.daw.trasnferdaw.model.entities.Contrato;

@WebServlet("/ContratoController")
public class ContratoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");

        if ("añadir".equalsIgnoreCase(accion)) {
            try {
                int jugadorId = Integer.parseInt(request.getParameter("jugadorId"));
                Integer equipoId = request.getParameter("equipoId").isEmpty() ? null : Integer.parseInt(request.getParameter("equipoId"));
                LocalDate fechaInicio = LocalDate.parse(request.getParameter("fechaInicio"));
                LocalDate fechaFin = LocalDate.parse(request.getParameter("fechaFin"));
                float salario = Float.parseFloat(request.getParameter("salario"));
                String tipoContrato = request.getParameter("tipoContrato");

                Contrato contrato = new Contrato();
                contrato.setJugadorId(jugadorId);
                contrato.setEquipoId(equipoId);
                contrato.setFechaInicio(fechaInicio);
                contrato.setFechaFin(fechaFin);
                contrato.setSalario(salario);
                contrato.setTipoContrato(tipoContrato);

                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
                dao.insertar(contrato);

                response.sendRedirect("contrato.jsp");

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al insertar contrato");
            }

        } else if ("eliminar".equalsIgnoreCase(accion)) {
            try {
                int idContrato = Integer.parseInt(request.getParameter("id_contrato"));

                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
                int filas = dao.eliminarContrato(idContrato);

                if (filas > 0) {
                    response.sendRedirect("contrato.jsp");
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Contrato no encontrado");
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar contrato");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // No implementado
    }
}
