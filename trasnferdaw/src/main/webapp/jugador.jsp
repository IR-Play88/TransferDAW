<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Jugador" %>

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
            <input class="form-control" type="number" step="0.01"  name="altura" placeholder="Altura (m)" required>
        </div>
        <div class="col-md-6">
            <input class="form-control" type="number" step="0.01"  name="peso" placeholder="Peso (kg)" required>
        </div>
        <div class="col-md-6">
            <select class="form-select" name="pie_dominante" required>
                <option value="derecho">Derecho</option>
                <option value="izquierdo">Izquierdo</option>
                <option value="ambidiestro">Ambidiestro</option>
            </select>
        </div>
        <div class="col-md-6">
            <input class="form-control" type="number" step="0.01" min="0" name="valor_mercado" placeholder="Valor mercado (€)" required>
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
        <div class="col-md-6 d-flex align-items-center gap-2">
            <input type="text" id="buscador" class="form-control me-2" placeholder="Buscar jugador...">
            <% if (esAdmin) { %>
                <input type="submit" class="btn btn-primary w-100" name="accion" value="insertar"/>
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
                TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
                List<Jugador> list = dao.listarJugadores();
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
                                    <button type="submit" name="accion" value="eliminar" class="btn btn-danger btn-sm w-100" onclick="return confirm('¿Seguro que quieres eliminar este jugador?');">Eliminar</button>
                                </form>
                                <a href="JugadorController?accion=modificar&id_jugador=<%= jugador.getIdJugador() %>" class="btn btn-warning btn-sm w-100" onclick="return confirm('¿Seguro que quieres modificar este jugador?');">Modificar</a>
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

<footer class="footer mt-5">
    <div class="container py-4 text-white">
        <div class="d-flex flex-column flex-md-row justify-content-between align-items-start gap-4">

            <!-- Columna izquierda -->
            <div class="footer-left">
                <h5>Legal</h5>
                <ul class="list-unstyled">
                    <li><a href="#" class="text-white">Aviso legal</a></li>
                    <li><a href="#" class="text-white">Política de privacidad</a></li>
                    <li><a href="#" class="text-white">Términos de uso</a></li>
                </ul>
            </div>

            <!-- Columna central -->
            <div class="footer-center text-center">
                <p class="mb-2">&copy; 2025 Iván Rafael Redondo</p>
                <p class="mb-1">Síguenos en redes sociales:</p>
                <div class="d-flex justify-content-center gap-3">
                    <a href="#"><img src="./images/insta.svg" alt="Instagram" width="40"></a>
                    <a href="#"><img src="./images/face.png" alt="Facebook" width="40"></a>
                    <a href="#"><img src="./images/x.jpeg" alt="Twitter/X" width="40"></a>
                    <a href="#"><img src="./images/tik.png" alt="TikTok" width="40"></a>
                </div>
            </div>

            <!-- Columna derecha -->
            <div class="footer-right">
                <h5>Opciones recomendadas</h5>
                <ul class="list-unstyled">
                    <li><a href="index.jsp" class="text-white">Inicio</a></li>
                    <li><a href="creearCuenta.jsp" class="text-white">Crear Cuenta</a></li>
                    <li><a href="contacto.jsp" class="text-white">Contacto</a></li>
                </ul>
            </div>
        </div>

        <div class="text-center mt-4 text-secondary small">
            Proyecto académico - IES Tierno Galván - DAW
        </div>
    </div>
</footer>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="./js/buscador.js"></script>
</body>
</html>
