<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Temporada" %>
<%@ include file="../importar/conf.jsp" %>
<!DOCTYPE html>
<html lang="es">

<%@ include file="../importar/encabezado.jsp" %>

<section class="col-md-9" id="content">
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
                List<Temporada> list = (List<Temporada>) request.getAttribute("listaTemporadas");
                if (list == null || list.isEmpty()) {
            %>
            <tr>
                <td colspan="3" class="text-center">No hay temporadas registradas.</td>
            </tr>
            <%
                } else {
                    for (Temporada temporada : list) {
            %>
            <tr>
                <td><%= temporada.getNombre() %></td>
                <td><%= temporada.getFechaInicio() %></td>
                <td><%= temporada.getFechaFin() %></td>
            </tr>
            <%  } } %>
        </tbody>
    </table>
</section>

<%@ include file="../importar/footer.jsp" %>

<%@ include file="../importar/script.jsp" %>
</body>
</html>
