<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Competicion" %>

<%@ include file="../importar/conf.jsp" %>
<!DOCTYPE html>
<html lang="es">
    <%@ include file="../importar/encabezado.jsp" %>

<section class="col-md-9">
    <%@ include file="../importar/mensaje.jsp" %>

    <h2>Competiciones</h2>

    <form method="POST" action="CompeticionController" class="row g-3">
        <div class="col-md-6">
            <input type="text" name="nombre" class="form-control" placeholder="Nombre" required />
        </div>
        <div class="col-md-6">
            <input type="text" name="pais" class="form-control" placeholder="País" required />
        </div>
        <div class="col-md-6">
            <select name="tipo" class="form-select" required>
                <option value="Liga">Liga</option>
                <option value="Copa">Copa</option>
                <option value="Internacional">Internacional</option>
                <option value="selecciones">Selección</option>
                <option value="Amistoso">Amistoso</option>
            </select>
        </div>
        <div class="col-md-6">
            <input type="number" name="numeroEquipos" class="form-control" placeholder="Número de Equipos" required />
        </div>
        <div class="col-md-6">
            <input type="number" name="anioCreacion" class="form-control" placeholder="Año de Creación" required />
        </div>
        <div class="col-md-6">
            <input type="text" id="buscador" class="form-control me-2" placeholder="Buscar competición...">
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
            List<Competicion> list = (List<Competicion>) request.getAttribute("listaCompeticiones");
            if (list == null || list.isEmpty()) {
        %>
            <tr>
                <td colspan="<%= esAdmin ? 12 : 11 %>" class="text-center">No hay competiciones registrados.</td>
            </tr>
        <%
            } else {
                for (Competicion competicion : list) {
        %>
                <tr>
                    <td><%= competicion.getNombre() %></td>
                    <td><%= competicion.getPais() %></td>
                    <td><%= competicion.getTipo() %></td>
                    <td><%= competicion.getNumeroEquipos() %></td>
                    <td><%= competicion.getAnioCreacion() %></td>
                    <% if (esAdmin) { %>
                    <td>
                        <div class="d-flex flex-column align-items-center gap-1">
                            <form action="CompeticionController" method="POST" class="w-100">
                                <input type="hidden" name="id_competicion" value="<%= competicion.getIdCompeticion() %>" />
                                <button type="submit" name="accion" value="eliminar" 
                                class="btn btn-danger btn-sm w-100" 
                                onclick="return confirm('¿Seguro que quieres eliminar esta competición?');">
                                <img src="images/eliminar.png" alt="Eliminar">
                            </button>
                            </form>
                            <a href="CompeticionController?accion=modificar&id_competicion=<%= competicion.getIdCompeticion() %>" 
                                class="btn btn-warning btn-sm w-100" 
                                onclick="return confirm('¿Modificar esta competición?');">
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
<%@ include file="../importar/script.jsp" %>
</body>
</html>
