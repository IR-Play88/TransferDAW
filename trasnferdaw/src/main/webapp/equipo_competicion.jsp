<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.EquipoCompeticion" %>
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
            <h2>Equipos en Competiciones</h2>
            <form method="POST" action="EquipoCompeticionController" class="row g-3">
                <div class="col-md-6">
                    <input class="form-control" type="text" name="nombreEquipo" placeholder="Nombre del Equipo" required />
                </div>
                <div class="col-md-6">
                    <input class="form-control" type="text" name="nombreCompeticion" placeholder="Nombre de la Competición" required />
                </div>
                <div class="col-md-6">
                    <input class="form-control" type="text" name="nombreTemporada" placeholder="Temporada" required />
                </div>
                <div class="col-md-6">
                    <input class="form-control" type="number" name="rango" placeholder="Rango" required />
                </div>
                <div class="col-md-12 d-flex">
                    <input type="text" id="buscador" class="form-control me-2" placeholder="Buscar..." />
                    <% if (esAdmin) { %>
                        <input type="submit" class="btn btn-primary w-100" name="accion" value="insertar"/>
                    <% } %>
                </div>
            </form>

            <table class="table table-striped mt-4" id="datos">
                <thead class="table-dark">
                <tr>
                    <th>Equipo</th>
                    <th>Competición</th>
                    <th>Temporada</th>
                    <th>Rango</th>
                    <% if (esAdmin) { %>
                    <th>Acción</th>
                    <% } %>
                </tr>
                </thead>
                <tbody>
                <%
                    TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
                    List<EquipoCompeticion> lista = dao.listarEquiposCompeticion();
                    for (EquipoCompeticion ec : lista) {
                %>
                <tr>
                    <td><%= ec.getNombreEquipo() %></td>
                    <td><%= ec.getNombreCompeticion() %></td>
                    <td><%= ec.getNombreTemporada() %></td>
                    <td><%= ec.getRango() %></td>
                    <td>
                        <% if (esAdmin) { %>
                            <div class="d-flex flex-column gap-1">
                                <form method="POST" action="EquipoCompeticionController" class="w-100">
                                    <input type="hidden" name="equipoId" value="<%= ec.getEquipoId() %>" />
                                    <input type="hidden" name="competicionId" value="<%= ec.getCompeticionId() %>" />
                                    <input type="hidden" name="temporadaId" value="<%= ec.getTemporadaId() %>" />
                                    <button type="submit" name="accion" value="eliminar" class="btn btn-danger btn-sm w-100" onclick="return confirm('¿Seguro que quieres eliminar esta competición para el equipo?');">
                                        Eliminar
                                    </button>
                                </form>
                                <a href="EquipoCompeticionController?accion=modificar&equipoId=<%= ec.getEquipoId() %>&competicionId=<%= ec.getCompeticionId() %>&temporadaId=<%= ec.getTemporadaId() %>" class="btn btn-warning btn-sm w-100" onclick="return confirm('¿Seguro que quieres modificar esta competición para el equipo?');">
                                    Modificar
                                </a>
                            </div>
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
