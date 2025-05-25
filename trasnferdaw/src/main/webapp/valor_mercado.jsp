<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.ValorMercadoHistorial" %>

<%@ include file="cabezera.jsp" %>

<section class="col-md-9">
    <%
    String error = (String) session.getAttribute("error");
    if (error != null) {
    %>
    <div class="alert alert-danger"><%= error %></div>
    <%
        session.removeAttribute("error");
    }

    String mensaje = (String) session.getAttribute("mensaje");
    if (mensaje != null) {
    %>
    <div class="alert alert-success"><%= mensaje %></div>
    <%
        session.removeAttribute("mensaje");
    }
    %>
    <h2>Historial Valor de Mercado</h2>

    <form method="POST" action="ValorMercadoController" class="row g-3 mb-4">
        <div class="col-md-6">
            <input class="form-control" type="text" name="jugador" placeholder="Jugador" required />
        </div>
        <div class="col-md-6">
            <input class="form-control" type="date" name="fecha" max="<%= java.time.LocalDate.now() %>" required />
        </div>
        <div class="col-md-6">
            <input class="form-control" type="number" step="0.01" name="valorMercado" placeholder="Valor (€)" required />
        </div>
        <div class="col-md-6">
            <textarea class="form-control" name="motivo" placeholder="Motivo (opcional)" rows="2"></textarea>
        </div>
        <div class="col-md-12 d-flex">
            <input type="text" id="buscador" class="form-control me-2" placeholder="Buscar valor de mercado..." />
            <% if (esAdmin) { %>
                <input type="submit" class="btn btn-primary w-100" name="accion" value="insertar" />
            <% } %>
        </div>
    </form>

    <table class="table table-striped" id="datos">
        <thead class="table-dark">
            <tr>
                <th>Jugador</th>
                <th>Fecha</th>
                <th>Valor (€)</th>
                <th>Motivo</th>
                <% if (esAdmin) { %>
                <th>Acción</th>
                <% } %>
            </tr>
        </thead>
        <tbody>
            <%
                List<ValorMercadoHistorial> lista = new TransferDAOImpMariaDB().listarValorMercadoHistorial();
                for (ValorMercadoHistorial vm : lista) {
            %>
            <tr>
                <td><%= vm.getNombreJugador() %></td>
                <td><%= vm.getFecha() %></td>
                <td><%= vm.getValorMercado() %></td>
                <td><%= vm.getMotivo() != null ? vm.getMotivo() : "" %></td>
                <% if (esAdmin) { %>
                <td>
                    <div class="d-flex flex-column align-items-center gap-1">
                        <form action="ValorMercadoController" method="POST" class="w-100">
                            <input type="hidden" name="id_historial" value="<%= vm.getIdHistorial() %>">
                            <button type="submit" name="accion" value="eliminar" class="btn btn-danger btn-sm w-100"
                                onclick="return confirm('¿Seguro que quieres eliminar este valor de mercado?');">Eliminar</button>
                        </form>
                        <a href="ValorMercadoController?accion=modificar&id_historial=<%= vm.getIdHistorial() %>"
                            class="btn btn-warning btn-sm w-100"
                            onclick="return confirm('¿Seguro que quieres modificar este valor de mercado?');">Modificar</a>
                    </div>
                </td>
                <% } else { %>
                <td></td>
                <% } %>
            </tr>
            <% } %>
        </tbody>
    </table>
</section>

<%@ include file="footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="./js/buscador.js"></script>
</body>

</html>
