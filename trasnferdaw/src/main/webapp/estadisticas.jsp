<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.EstadisticasTemporada" %>
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
                <h2>Estadísticas Temporada</h2>

                <form method="POST" action="EstadisticasTemporadaController" class="row g-3 mb-4">
                    <div class="col-md-6">
                        <input class="form-control" type="text" name="jugador" placeholder="Jugador" required />
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" type="text" name="temporada" placeholder="Temporada" required />
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" type="text" name="competicion" placeholder="Competición" required />
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" type="text" name="equipo" placeholder="Equipo" required />
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" type="number" name="partidosJugados" placeholder="Partidos Jugados" />
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" type="number" name="goles" placeholder="Goles" />
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" type="number" name="asistencias" placeholder="Asistencias" />
                    </div>
                    <div class="col-md-12 d-flex">
                        <input type="text" id="buscador" class="form-control me-2" placeholder="Buscar estadisticas..." />
                        <% if (esAdmin) { %>
                            <input type="submit" class="btn btn-primary w-100" name="accion" value="insertar"/>
                        <% } %>
                    </div>
                </form>

                <table class="table table-striped" id="datos">
                    <thead class="table-dark">
                        <tr>
                            <th>Jugador</th>
                            <th>Temporada</th>
                            <th>Competición</th>
                            <th>Equipo</th>
                            <th>Partidos</th>
                            <th>Goles</th>
                            <th>Asistencias</th>
                            <% if (esAdmin) { %>
                            <th>Acción</th>
                            <% } %>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<EstadisticasTemporada> lista = new TransferDAOImpMariaDB().listarEstadisticasTemporada();
                            for (EstadisticasTemporada est : lista) {
                        %>
                        <tr>
                            <td><%= est.getNombreJugador() %></td>
                            <td><%= est.getNombreTemporada() %></td>
                            <td><%= est.getNombreCompeticion() %></td>
                            <td><%= est.getNombreEquipo() %></td>
                            <td><%= est.getPartidosJugados() %></td>
                            <td><%= est.getGoles() %></td>
                            <td><%= est.getAsistencias() %></td>
                            <td>
                                <% if (esAdmin) { %>
                                <div class="d-flex flex-column align-items-center gap-1">
                                    <form action="EstadisticasTemporadaController" method="POST" class="w-100">
                                        <input type="hidden" name="jugadorId" value="<%= est.getJugadorId() %>" />
                                        <input type="hidden" name="temporadaId" value="<%= est.getTemporadaId() %>" />
                                        <input type="hidden" name="competicionId" value="<%= est.getCompeticionId() %>" />
                                        <input type="hidden" name="equipoId" value="<%= est.getEquipoId() %>" />
                                        <button type="submit" name="accion" value="eliminar" class="btn btn-danger btn-sm w-100" onclick="return confirm('¿Seguro que quieres eliminar esta estadística?');">
                                            Eliminar
                                        </button>
                                    </form>
                                    <a href="EstadisticasTemporadaController?accion=modificar&jugadorId=<%= est.getJugadorId() %>&temporadaId=<%= est.getTemporadaId() %>&competicionId=<%= est.getCompeticionId() %>&equipoId=<%= est.getEquipoId() %>" class="btn btn-warning btn-sm w-100" onclick="return confirm('¿Seguro que quieres modificar esta estadística?');">
                                        Modificar
                                    </a>
                                </div>
                                <% } %>
                            </td>
                            
                        </tr>
                        <% } %>
                    </tbody>
                </table>

               <% if (esAdmin) { %>
                <h2>Descargar las estadisticas de tu jugador favorito</h2>

                <form action="EstadisticasTemporadaController" method="POST">
                    <table class="table table-striped">
                        <thead class="table-dark">
                        <tr>
                            <th>Nombre del jugador</th>
                            <th>Temporada(Opcional)</th>
                            <th>accion</th>
                        </tr>
                    </thead>
                        <tr>
                            <td><input type="text" name="jugador"></td>
                            <td><input type="text" name="temporada"></td>
                            <td>
                                <button type="submit" name="accion" value="descargarTotales">Descargar totales</button>
                                <button type="submit" name="accion" value="descargarTemporada">Descargar por temporada</button>
                            </td>
                        </tr>
                    </table>
                </form>
               <% } %>
            </section>
        </div>
        <%@ include file="footer.jsp" %>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="./js/buscador.js"></script>
</body>

</html>
