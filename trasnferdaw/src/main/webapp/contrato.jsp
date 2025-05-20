<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Contrato" %>

<%@ include file="cabezera.jsp" %>

            <section class="col-md-9">
                <h2>Contratos</h2>

                <form method="GET" action="ContratoController" class="row g-3 mb-3">
                    <div class="col-md-6">
                        <input class="form-control" type="text" name="jugadorId" placeholder="Jugador" required />
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" type="text" name="equipoId" placeholder="Equipo" required />
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" type="date" name="fechaInicio" required />
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" type="date" name="fechaFin" required />
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" type="number" step="0.01" name="salario" placeholder="Salario" required />
                    </div>
                    <div class="col-md-6">
                        <select class="form-select" name="tipoContrato">
                            <option value="cesion">Cesión</option>
                            <option value="nuevo">Nuevo fichaje</option>
                            <option value="libre">Libre</option>
                        </select>
                    </div>
                    <div class="col-md-12 d-flex">
                        <input type="text" id="buscador" class="form-control me-2" placeholder="Buscar contrato..." />
                        <% if (esAdmin) { %>
                        <button type="submit" name="accion" value="añadir" class="btn btn-success">Añadir</button>
                        <% } %>
                    </div>
                </form>

                <table class="table table-striped mt-4" id="datos">
                    <thead class="table-dark">
                        <tr>
                            <th>Jugador</th>
                            <th>Equipo</th>
                            <th>Fecha Inicio</th>
                            <th>Fecha Fin</th>
                            <th>Salario</th>
                            <th>Tipo</th>
                            <% if (esAdmin) { %>
                            <th>Acción</th>
                            <% } %>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
                            List<Contrato> lista = dao.listarContratos();
                            for (Contrato c : lista) {
                        %>
                        <tr>
                            <td><%= c.getNombreJugador() %></td>
                            <td><%= c.getNombreEquipo() %></td>
                            <td><%= c.getFechaInicio() %></td>
                            <td><%= c.getFechaFin() %></td>
                            <td><%= c.getSalario() %></td>
                            <td><%= c.getTipoContrato() %></td>
                            <td>
                                <% if (esAdmin) { %>
                                <form action="ContratoController" method="GET" class="d-flex gap-1">
                                    <input type="hidden" name="id_contrato" value="<%= c.getIdContrato() %>" />
                                    <button type="submit" name="accion" value="eliminar" class="btn btn-danger btn-sm">Eliminar</button>
                                    <a href="ContratoController?accion=modificar&id_contrato=<%= c.getIdContrato() %>" class="btn btn-warning btn-sm">Modificar</a>
                                </form>
                                <% } %>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </section>
        </div>

        <%@ include file="footer.jsp" %>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="./js/buscador.js"></script>
</body>

</html>
