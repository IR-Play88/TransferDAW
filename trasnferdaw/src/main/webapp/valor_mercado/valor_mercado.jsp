<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.ValorMercadoHistorial" %>
<%@ include file="../importar/conf.jsp" %>
<!DOCTYPE html>
<html lang="es">

<%@ include file="../importar/encabezado.jsp" %>

<section class="col-md-9">
    <%@ include file="../importar/mensaje.jsp" %>
    <h2>Valor de Mercado de los jugadores</h2>

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
        <div class="col-md-6">
            <input type="text" id="buscador" class="form-control me-2" placeholder="Buscar valor de mercado..." />
        </div>
        <div class="w-100"></div>
        <div class="col-md-6">
            <% if (esAdmin) { %>
                <button type="submit" name="accion" value="insertar" class="btn btn-success">
                    <img src="../images/insertar.png" alt="Insertar" />
                </button>
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
                List<ValorMercadoHistorial> list = (List<ValorMercadoHistorial>) request.getAttribute("listaValores");
                if (list == null || list.isEmpty()) {
            %>
                <tr>
                    <td colspan="<%= esAdmin ? 5 : 4 %>" class="text-center">No hay valores de los jugadores registrados.</td>
                </tr>
            <%
                } else {
                    for (ValorMercadoHistorial vm : list) {
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
                                    <input type="hidden" name="id_historial" value="<%= vm.getIdHistorial() %>" />
                                    <button type="submit" name="accion" value="eliminar" class="btn btn-danger btn-sm w-100"
                                        onclick="return confirm('¿Seguro que quieres eliminar este valor de mercado?');">
                                        <img src="../images/eliminar.png" alt="Eliminar" />
                                    </button>
                                </form>
                                <a href="ValorMercadoController?accion=modificar&id_historial=<%= vm.getIdHistorial() %>"
                                    class="btn btn-warning btn-sm w-100"
                                    onclick="return confirm('¿Seguro que quieres modificar este valor de mercado?');">
                                    <img src="../images/editar.png" alt="Modificar" />
                                </a>
                            </div>
                        </td>
                    <% } %>
                </tr>
            <%   } } %>
        </tbody>
    </table>
</section>

<%@ include file="../importar/footer.jsp" %>

<%@ include file="../importar/script.jsp" %>
</body>

</html>
