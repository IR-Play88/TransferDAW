<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.ValorMercadoHistorial" %>

<%@ include file="cabezera.jsp" %>

            <section class="col-md-9">
                <h2>Historial Valor de Mercado</h2>

                <form method="GET" action="ValorMercadoController" class="row g-3 mb-4">
                    <div class="col-md-6">
                        <input class="form-control" type="text" name="jugador" placeholder="Jugador" required />
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" type="date" name="fecha" required />
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
                        <button type="submit" name="accion" value="añadir" class="btn btn-success">Añadir</button>
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
                            <td>
                                <% if (esAdmin) { %>
                                <form action="ValorMercadoController" method="GET" class="d-flex gap-1">
                                    <input type="hidden" name="id_historial" value="<%= vm.getIdHistorial() %>" />
                                    <button type="submit" name="accion" value="eliminar" class="btn btn-danger btn-sm">Eliminar</button>
                                    <a href="ValorMercadoController?accion=modificar&id_historial=<%= vm.getIdHistorial() %>" class="btn btn-warning btn-sm">Modificar</a>
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
