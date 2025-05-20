<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Equipo" %>
<%@ include file="cabezera.jsp" %>

            <section class="col-md-9">
                <h2>Equipos</h2>

                <form method="GET" action="EquipoController" class="row g-3 mb-4">
                    <div class="col-md-6"><input class="form-control" type="text" name="nombre" placeholder="Nombre" required></div>
                    <div class="col-md-6"><input class="form-control" type="text" name="ciudad" placeholder="Ciudad"></div>
                    <div class="col-md-6"><input class="form-control" type="text" name="pais" placeholder="País"></div>
                    <div class="col-md-6"><input class="form-control" type="number" name="anio" placeholder="Año de fundación"></div>
                    <div class="col-md-6"><input class="form-control" type="number" step="0.01" name="presupuesto" placeholder="Presupuesto"></div>
                    <div class="col-md-6"><input class="form-control" type="text" name="propietario" placeholder="Propietario"></div>
                    <div class="col-md-6"><input class="form-control" type="text" name="estadio" placeholder="Estadio"></div>
                    <div class="col-md-6"><input class="form-control" type="text" name="entrenador" placeholder="Entrenador"></div>
                    <div class="col-md-12 d-flex">
                        <input type="text" id="buscador" class="form-control me-2" placeholder="Buscar equipo...">
                        <% if (esAdmin) { %>
                        <button type="submit" name="accion" value="añadir" class="btn btn-success">Añadir</button>
                        <% } %>
                    </div>
                </form>

                <table class="table table-striped" id="datos">
                    <thead class="table-dark">
                        <tr>
                            <th>Nombre</th>
                            <th>Ciudad</th>
                            <th>País</th>
                            <th>Año de fundación</th>
                            <th>Presupuesto</th>
                            <th>Propietario</th>
                            <th>Estadio</th>
                            <th>Entrenador</th>
                            <% if (esAdmin) { %>
                            <th>Acción</th>
                            <% } %>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
                        List<Equipo> list = dao.listarEquipos();
                        for (Equipo equipo : list) {
                        %>
                            <tr>
                                <td><%= equipo.getNombre() %></td>
                                <td><%= equipo.getCiudad() %></td>
                                <td><%= equipo.getPais() %></td>
                                <td><%= equipo.getAnioFundacion() %></td>
                                <td><%= equipo.getPresupuesto() %></td>
                                <td><%= equipo.getPropietario() %></td>
                                <td><%= equipo.getEstadioNombre() %></td>
                                <td><%= equipo.getEntrenadorNombre() %></td>
                                <td>
                                    <% if (esAdmin) { %>
                                    <form action="EquipoController" method="GET" class="d-flex gap-1">
                                        <input type="hidden" name="id_equipo" value="<%= equipo.getIdEquipo() %>">
                                        <button type="submit" name="accion" value="eliminar" class="btn btn-danger btn-sm">Eliminar</button>
                                        <a href="EquipoController?accion=modificar&id_equipo=<%= equipo.getIdEquipo() %>" class="btn btn-warning btn-sm">Modificar</a>
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
