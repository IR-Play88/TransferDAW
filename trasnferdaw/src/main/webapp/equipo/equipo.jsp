<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Equipo" %>

<%@ include file="../importar/conf.jsp" %>
<!DOCTYPE html>
<html lang="es">

<%@ include file="../importar/encabezado.jsp" %>

<section class="col-md-9">
    <%@ include file="../importar/mensaje.jsp" %>
    <h2>Equipos</h2>

    <form method="POST" action="EquipoController" class="row g-3 mb-4">
        <div class="col-md-6">
            <input class="form-control" type="text" name="nombre" placeholder="Nombre" required>
        </div>
        <div class="col-md-6">
            <input class="form-control" type="text" name="ciudad" placeholder="Ciudad">
        </div>
        <div class="col-md-6">
            <input class="form-control" type="text" name="pais" placeholder="País">
        </div>
        <div class="col-md-6">
            <input class="form-control" type="number" name="anio" placeholder="Año de fundación">
        </div>
        <div class="col-md-6">
            <input class="form-control" type="number" step="0.01" name="presupuesto" placeholder="Presupuesto">
        </div>
        <div class="col-md-6">
            <input class="form-control" type="text" name="propietario" placeholder="Propietario">
        </div>
        <div class="col-md-6">
            <input class="form-control" type="text" name="estadio" placeholder="Estadio">
        </div>
        <div class="col-md-6">
            <input class="form-control" type="text" name="entrenador" placeholder="Entrenador">
        </div>
        <div class="col-md-6">
            <input type="text" id="buscador" class="form-control me-2" placeholder="Buscar equipo...">
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
                List<Equipo> list = (List<Equipo>) request.getAttribute("listaEquipos");
                if (list == null || list.isEmpty()) {
            %>
                <tr>
                    <td colspan="<%= esAdmin ? 12 : 11 %>" class="text-center">No hay equipos registrados.</td>
                </tr>
            <%
                } else {
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
                    <% if (esAdmin) { %>
                        <td>
                            <div class="d-flex flex-column align-items-center gap-1">
                                <form action="EquipoController" method="POST" class="w-100">
                                    <input type="hidden" name="id_equipo" value="<%= equipo.getIdEquipo() %>">
                                    <button type="submit" name="accion" value="eliminar" 
                                        class="btn btn-danger btn-sm w-100"
                                        onclick="return confirm('¿Seguro que quieres eliminar este equipo?');">
                                        <img src="images/eliminar.png" alt="Eliminar">
                                    </button>
                                </form>
                                <a href="EquipoController?accion=modificar&id_equipo=<%= equipo.getIdEquipo() %>"
                                    class="btn btn-warning btn-sm w-100"
                                    onclick="return confirm('¿Seguro que quieres modificar este equipo?');">
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

<%@ include file="../importar/footer.jsp" %>
</div>

<%@ include file="../importar/script.jsp" %>
</body>

</html>
