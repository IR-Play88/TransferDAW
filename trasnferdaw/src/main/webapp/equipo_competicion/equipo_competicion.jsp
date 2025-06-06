<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.EquipoCompeticion" %>
<%@ include file="../importar/conf.jsp" %>
<!DOCTYPE html>
<html lang="es">

<%@ include file="../importar/encabezado.jsp" %>

<section class="col-md-9">
    <%@ include file="../importar/mensaje.jsp" %>
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
            <input class="form-control" type="text" name="posicion" placeholder="Posicion" required />
        </div>
        <div class="col-md-6">
            <input type="text" id="buscador" class="form-control me-2" placeholder="Buscar equipo en competicion...">
        </div>
        <div class="w-100"></div>
        <div class="col-md-6">
            <% if (esAdmin) { %>
                <button type="submit" name="accion" value="insertar" class="btn btn-success">
                    <img src="../images/insertar.png" alt="Insertar">
                </button>
            <% } %>
        </div>
    </form>

    <table class="table table-striped mt-4" id="datos">
        <thead class="table-dark">
            <tr>
                <th>Equipo</th>
                <th>Competición</th>
                <th>Temporada</th>
                <th>Posición</th>
                <% if (esAdmin) { %>
                    <th>Acción</th>
                <% } %>
            </tr>
        </thead>
        <tbody>
            <%
                List<EquipoCompeticion> list = (List<EquipoCompeticion>) request.getAttribute("listaEquipoCompeticiones");
                if (list == null || list.isEmpty()) {
            %>
                <tr>
                    <td colspan="<%= esAdmin ? 12 : 11 %>" class="text-center">No hay equipos en competiciones registrados.</td>
                </tr>
            <%
                } else {
                    for (EquipoCompeticion ec : list) {
            %>
                <tr>
                    <td><%= ec.getNombreEquipo() %></td>
                    <td><%= ec.getNombreCompeticion() %></td>
                    <td><%= ec.getNombreTemporada() %></td>
                    <td><%= ec.getPosicion() %></td>
                    <% if (esAdmin) { %>
                        <td>
                            <div class="d-flex flex-column gap-1">
                                <form method="POST" action="EquipoCompeticionController" class="w-100">
                                    <input type="hidden" name="equipoId" value="<%= ec.getEquipoId() %>" />
                                    <input type="hidden" name="competicionId" value="<%= ec.getCompeticionId() %>" />
                                    <input type="hidden" name="temporadaId" value="<%= ec.getTemporadaId() %>" />
                                    <button type="submit" name="accion" value="eliminar" class="btn btn-danger btn-sm w-100"
                                        onclick="return confirm('¿Seguro que quieres eliminar esta competición para el equipo?');">
                                        <img src="images/eliminar.png" alt="Eliminar">
                                    </button>
                                </form>
                                <a href="EquipoCompeticionController?accion=modificar&equipoId=<%= ec.getEquipoId() %>&competicionId=<%= ec.getCompeticionId() %>&temporadaId=<%= ec.getTemporadaId() %>" 
                                    class="btn btn-warning btn-sm w-100"
                                    onclick="return confirm('¿Seguro que quieres modificar esta competición para el equipo?');">
                                    <img src="images/editar.png" alt="Modificar">
                                </a>
                            </div>
                        </td>
                    <% } %>
                </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</section>
</div>

<%@ include file="../importar/footer.jsp" %>
</div>

<%@ include file="../importar/script.jsp" %>
</body>

</html>
