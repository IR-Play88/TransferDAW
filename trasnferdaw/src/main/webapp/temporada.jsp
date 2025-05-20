<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Temporada" %>

<%@ include file="cabezera.jsp" %>

            <section class="col-md-9">
                <h2>Temporadas</h2>

                <div class="mb-3">
                    <input type="text" id="buscador" class="form-control" placeholder="Buscar temporada..." />
                </div>

                <table class="table table-striped" id="datos">
                    <thead class="table-dark">
                        <tr>
                            <th>Nombre</th>
                            <th>Fecha inicio</th>
                            <th>Fecha fin</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
                            List<Temporada> list = dao.listarTemporadas();
                            for (Temporada temporada : list) {
                        %>
                        <tr>
                            <td><%= temporada.getNombre() %></td>
                            <td><%= temporada.getFechaInicio() %></td>
                            <td><%= temporada.getFechaFin() %></td>
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
