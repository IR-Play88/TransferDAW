<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Traspaso" %>

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
            
                <h2>Traspasos</h2>

                <form method="POST" action="TraspasoController" class="row g-3 mb-4">
                    <div class="col-md-6">
                        <input class="form-control" type="text" name="jugadorId" placeholder="Jugador" required />
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" type="text" name="equipoOrigenId" placeholder="Equipo Origen" />
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" type="text" name="equipoDestinoId" placeholder="Equipo Destino" />
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" type="text" name="temporadaId" placeholder="Temporada" />
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" type="date" name="fechaTraspaso" placeholder="Fecha Traspaso"  max="<%= java.time.LocalDate.now() %>" required />
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" type="number" step="0.01" name="cantidad" placeholder="Cantidad" />
                    </div>
                    <div class="col-md-6">
                        <select class="form-select" name="tipo">
                            <option value="compra">Compra</option>
                            <option value="cesion">Cesión</option>
                            <option value="libre">Libre</option>
                            <option value="fin de contrato">Fin de contrato</option>
                        </select>
                    </div>
                    <div class="col-md-12 d-flex">
                        <input type="text" id="buscador" class="form-control me-2" placeholder="Buscar jugador..." />
                        <% if (esAdmin) { %>
                            <input type="submit" class="btn btn-primary w-100" name="accion" value="insertar"/>
                        <% } %>
                    </div>
                </form>

                <table class="table table-striped" id="datos">
                    <thead class="table-dark">
                        <tr>
                            <th>Jugador</th>
                            <th>Origen</th>
                            <th>Destino</th>
                            <th>Temporada</th>
                            <th>Fecha</th>
                            <th>Cantidad</th>
                            <th>Tipo</th>
                            <% if (esAdmin) { %>
                            <th>Acción</th>
                            <% } %>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Traspaso> lista = new TransferDAOImpMariaDB().listarTraspasos();
                            for (Traspaso t : lista) {
                        %>
                        <tr>
                            <td><%= t.getNombreJugador() %></td>
                            <td><%= t.getNombreEquipoOrigen() %></td>
                            <td><%= t.getNombreEquipoDestino() %></td>
                            <td><%= t.getNombreTemporada() %></td>
                            <td><%= t.getFechaTraspaso() %></td>
                            <td><%= t.getCantidad() %></td>
                            <td><%= t.getTipo() %></td>
                                <% if (esAdmin) { %>
                                    <td>
                                        <div class="d-flex flex-column align-items-center gap-1">
                                            <form action="TraspasoController" method="POST" class="w-100">
                                                <input type="hidden" name="id_traspaso" value="<%= t.getIdTraspaso() %>" />
                                                <input type="hidden" name="jugadorId" value="<%= t.getJugadorId() %>" />
                                                <input type="hidden" name="temporadaId" value="<%= t.getTemporadaId() %>" />
                                                <input type="hidden" name="equipoOrigenId" value="<%= t.getEquipoOrigenId() %>" />
                                                <input type="hidden" name="equipoDestinoId" value="<%= t.getEquipoDestinoId() %>" />
                                                <button type="submit" name="accion" value="eliminar" class="btn btn-danger btn-sm w-100" onclick="return confirm('¿Seguro que quieres eliminar este traspaso?');">Eliminar</button>
                                            </form>
                                            <a href="TraspasoController?accion=modificar&id_traspaso=<%= t.getIdTraspaso() %>" class="btn btn-warning btn-sm w-100" onclick="return confirm('¿Seguro que quieres modificar este traspaso?');">Modificar</a>
                                        </div>
                                    </td>
                                <% } %>
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
