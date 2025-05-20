<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Competicion" %>

<%@ include file="cabezera.jsp" %>

            <section class="col-md-9">
                <h2>Competiciones</h2>
                <form method="GET" action="CompeticionController" class="row g-3">
                    <div class="col-md-6">
                        <input type="text" name="nombre" class="form-control" placeholder="Nombre" required />
                    </div>
                    <div class="col-md-6">
                        <input type="text" name="pais" class="form-control" placeholder="País" required />
                    </div>
                    <div class="col-md-6">
                        <select name="tipo" class="form-select">
                            <option value="Liga">Liga</option>
                            <option value="Copa">Copa</option>
                            <option value="Internacional">Internacional</option>
                            <option value="Seleccion">Selección</option>
                            <option value="Amistoso">Amistoso</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <input type="number" name="numeroEquipos" class="form-control" placeholder="Número de Equipos" required />
                    </div>
                    <div class="col-md-6">
                        <input type="number" name="anioCreacion" class="form-control" placeholder="Año de Creación" required />
                    </div>
                    <div class="col-md-6 d-flex">
                        <input type="text" id="buscador" class="form-control me-2" placeholder="Buscar competición..." />
                        <% if (esAdmin) { %>
                        <button type="submit" name="accion" value="añadir" class="btn btn-success">Añadir</button>
                        <% } %>
                    </div>
                </form>

                <table class="table table-striped mt-4" id="datos">
                    <thead class="table-dark">
                        <tr>
                            <th>Nombre</th>
                            <th>País</th>
                            <th>Tipo</th>
                            <th>Nº Equipos</th>
                            <th>Año</th>
                            <% if (esAdmin) { %>
                            <th>Acción</th>
                            <% } %>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
                            List<Competicion> list = dao.listarCompeticiones();
                            for (Competicion competicion : list) {
                        %>
                            <tr>
                                <td><%= competicion.getNombre() %></td>
                                <td><%= competicion.getPais() %></td>
                                <td><%= competicion.getTipo() %></td>
                                <td><%= competicion.getNumeroEquipos() %></td>
                                <td><%= competicion.getAnioCreacion() %></td>
                                <td>
                                    <% if (esAdmin) { %>
                                    <form action="CompeticionController" method="GET" class="d-flex gap-1">
                                        <input type="hidden" name="id_competicion" value="<%= competicion.getIdCompeticion() %>" />
                                        <button type="submit" name="accion" value="eliminar" class="btn btn-danger btn-sm">Eliminar</button>
                                        <a href="CompeticionController?accion=modificar&id_competicion=<%= competicion.getIdCompeticion() %>" class="btn btn-warning btn-sm">Modificar</a>
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
