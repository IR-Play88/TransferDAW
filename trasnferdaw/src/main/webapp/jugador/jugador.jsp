<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Jugador" %>

<%@ include file="../importar/conf.jsp" %>
<!DOCTYPE html>
<html lang="es">
    <%@ include file="../importar/encabezado.jsp" %>

    <section class="col-md-9" id="content">
        <%@ include file="../importar/mensaje.jsp" %>
        <h2>Jugadores</h2>

        <form method="POST" action="JugadorController" class="row g-3">
            <div class="col-md-6">
                <input class="form-control" type="text" name="nombre" placeholder="Nombre" required>
            </div>
            <div class="col-md-6">
                <input class="form-control" type="text" name="alias" placeholder="Alias">
            </div>
            <div class="col-md-6">
                <input class="form-control" type="date" name="fecha_nacimiento" max="<%= java.time.LocalDate.now() %>" required>
            </div>
            <div class="col-md-6">
                <input class="form-control" type="text" name="nacionalidad" placeholder="Nacionalidad">
            </div>
            <div class="col-md-6">
                <input class="form-control" type="number" step="0.01" name="altura" placeholder="Altura (m)" required>
            </div>
            <div class="col-md-6">
                <input class="form-control" type="number" step="0.01" name="peso" placeholder="Peso (kg)" required>
            </div>
            <div class="col-md-6">
                <select class="form-select" name="pie_dominante" required>
                    <option value="derecho">Derecho</option>
                    <option value="izquierdo">Izquierdo</option>
                    <option value="ambidiestro">Ambidiestro</option>
                </select>
            </div>
            <div class="col-md-6">
                <input class="form-control" type="number" step="0.01" name="valor_mercado" placeholder="Valor mercado (€)" required>
            </div>
            <div class="col-md-6">
                <select class="form-select" name="posicion" required>
                    <option value="delantero">Delantero</option>
                    <option value="mediocampista">Mediocampista</option>
                    <option value="defensa">Defensa</option>
                    <option value="portero">Portero</option>
                </select>
            </div>
            <div class="col-md-6">
                <input class="form-control" type="text" name="representante" placeholder="Representante">
            </div>
            <div class="col-md-6">
                <input class="form-control" type="text" name="seleccion" placeholder="Selección">
            </div>
            <div class="col-md-6">
                <input type="text" id="buscador" class="form-control me-2" placeholder="Buscar jugador...">
            </div>
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
                    <th>Nombre</th>
                    <th>Alias</th>
                    <th>Fecha Nac.</th>
                    <th>Nacionalidad</th>
                    <th>Altura (m)</th>
                    <th>Peso (kg)</th>
                    <th>Pie</th>
                    <th>Valor (€)</th>
                    <th>Posición</th>
                    <th>Representante</th>
                    <th>Selección</th>
                    <% if (esAdmin) { %>
                        <th>Acción</th>
                    <% } %>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Jugador> list = (List<Jugador>) request.getAttribute("listaJugadores");
                    if (list == null || list.isEmpty()) {
                %>
                <tr>
                    <td colspan="<%= esAdmin ? 12 : 11 %>" class="text-center">No hay jugadores registrados.</td>
                </tr>
                <%
                    } else {
                        for (Jugador jugador : list) {
                %>
                <tr>
                    <td><%= jugador.getNombre() %></td>
                    <td><%= jugador.getAlias() %></td>
                    <td><%= jugador.getFechaNacimiento() %></td>
                    <td><%= jugador.getNacionalidad() %></td>
                    <td><%= jugador.getAltura() %></td>
                    <td><%= jugador.getPeso() %></td>
                    <td><%= jugador.getPieDominante() %></td>
                    <td><%= jugador.getValorMercado() %></td>
                    <td><%= jugador.getPosicion() %></td>
                    <td><%= jugador.getRepresentanteNombre() != null ? jugador.getRepresentanteNombre() : "" %></td>
                    <td><%= jugador.getSeleccionNombre() != null ? jugador.getSeleccionNombre() : "" %></td>
                    <% if (esAdmin) { %>
                        <td>
                            <div class="d-flex flex-column align-items-center gap-1">
                                <form action="JugadorController" method="POST" class="w-100">
                                    <input type="hidden" name="id_jugador" value="<%= jugador.getIdJugador() %>">
                                    <button type="submit" name="accion" value="eliminar" class="btn btn-danger btn-sm w-100" onclick="return confirm('¿Seguro que quieres eliminar este jugador?');">
                                        <img src="../images/eliminar.png" alt="Eliminar">
                                    </button>
                                </form>
                                <a href="JugadorController?accion=modificar&id_jugador=<%= jugador.getIdJugador() %>" class="btn btn-warning btn-sm w-100" onclick="return confirm('¿Seguro que quieres modificar este jugador?');">
                                    <img src="../images/editar.png" alt="Modificar">
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

    <%@ include file="../importar/script.jsp" %>
</body>
</html>
