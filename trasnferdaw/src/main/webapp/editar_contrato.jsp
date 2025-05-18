<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Contrato" %>
<%
    String rol = (String) session.getAttribute("rol");
    if (rol == null || !rol.equals("admin")) {
        response.sendRedirect("index.jsp");
        return;
    }

    boolean esAdmin = true; // porque ya comprobaste que sí lo es
    String nombreUsuario = (String) session.getAttribute("usuario");
%>
<jsp:useBean id="contrato" scope="request" class="es.tierno.daw.trasnferdaw.model.entities.Contrato" />
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Contrato</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="./css/styles.css" />
</head>
<body>
<div class="container-fluid">
    <header class="mb-4">
        <img src="../images/logo.png" alt="logo" />
        <h1 class="mt-2">Editando contrato de <span class="text-warning"><%= contrato.getNombreJugador() %></span></h1>
    </header>

    <nav class="mb-4">
        <ul class="nav justify-content-center">
            <li class="nav-item"><a class="nav-link" href="index.jsp">Inicio</a></li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#">Idiomas</a>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#">Español</a></li>
                    <li><a class="dropdown-item" href="#">Inglés</a></li>
                </ul>
            </li>
            <li class="nav-item"><a class="nav-link" href="#">Crear cuenta</a></li>
            <li class="nav-item"><a class="nav-link" href="./html/contacto.html">Contacto</a></li>
        </ul>
    </nav>

    <div class="row">
        <aside class="col-md-3">
            <div class="widget mb-3">
                <% if (nombreUsuario == null) { %>
                    <h3>Iniciar Sesión</h3>
                    <form method="POST" action="LoginServlet">
                        <input type="text" class="form-control mb-2" name="usuario" placeholder="usuario" />
                        <input type="password" class="form-control mb-2" name="password" placeholder="password" />
                        <input type="submit" class="btn btn-primary w-100" value="Enviar" />
                    </form>
                <% } else { %>
                    <h3>Bienvenido, <%= nombreUsuario %></h3>
                    <form method="POST" action="LogoutServlet">
                        <input type="submit" class="btn btn-danger w-100" value="Cerrar sesión" />
                    </form>
                <% } %> 
            </div>

            <div class="widget mb-3">
                <h3>Jugadores populares</h3>
                <ul class="list-group">
                    <li class="list-group-item">Marco Asensio</li>
                    <li class="list-group-item">Lucas Vázquez</li>
                    <li class="list-group-item">Isco</li>
                    <li class="list-group-item">Cristiano Ronaldo</li>
                    <li class="list-group-item">Antony</li>
                </ul>
            </div>

            <div class="widget">
                <h3>TransferDAW</h3>
                <blockquote class="blockquote">
                    Quieres ver todo lo relacionado con el mundo del fútbol, este es tu sitio. Si eres un apasionado, experto o quieres compartir tus conocimientos, inicia sesión. Podrás insertar, modificar, eliminar...
                </blockquote>
            </div>
        </aside>

        <section class="col-md-9">
            <h2>Editar Contrato</h2>

            <form action="ContratoController" method="GET" class="row g-3">
                <input type="hidden" name="id_contrato" value="<%= contrato.getIdContrato() %>" />

                <div class="col-md-6">
                    <label class="form-label">Jugador</label>
                    <input type="text" class="form-control" value="<%= contrato.getNombreJugador() %>" readonly />
                    <input type="hidden" name="jugadorId" value="<%= contrato.getJugadorId() %>" />
                </div>

                <div class="col-md-6">
                    <label class="form-label">Equipo</label>
                    <input type="text" class="form-control" value="<%= contrato.getNombreEquipo() != null ? contrato.getNombreEquipo() : "" %>" readonly />
                    <input type="hidden" name="equipoId" value="<%= contrato.getEquipoId() != null ? contrato.getEquipoId() : "" %>" />
                </div>

                <div class="col-md-6">
                    <label for="fechaInicio" class="form-label">Fecha de inicio</label>
                    <input type="date" class="form-control" id="fechaInicio" name="fechaInicio" value="<%= contrato.getFechaInicio() != null ? contrato.getFechaInicio().toString() : "" %>" required />
                </div>

                <div class="col-md-6">
                    <label for="fechaFin" class="form-label">Fecha de finalización</label>
                    <input type="date" class="form-control" id="fechaFin" name="fechaFin" value="<%= contrato.getFechaFin() != null ? contrato.getFechaFin().toString() : "" %>" required />
                </div>

                <div class="col-md-6">
                    <label for="salario" class="form-label">Salario (€)</label>
                    <input type="number" step="0.01" class="form-control" id="salario" name="salario" value="<%= contrato.getSalario() %>" required />
                </div>

                <div class="col-md-6">
                    <label for="tipoContrato" class="form-label">Tipo de contrato</label>
                    <select class="form-select" name="tipoContrato" id="tipoContrato">
                        <option value="cesion" <%= "cesion".equals(contrato.getTipoContrato()) ? "selected" : "" %>>Cesión</option>
                        <option value="nuevo" <%= "nuevo".equals(contrato.getTipoContrato()) ? "selected" : "" %>>Nuevo fichaje</option>
                        <option value="libre" <%= "libre".equals(contrato.getTipoContrato()) ? "selected" : "" %>>Libre</option>
                    </select>
                </div>

                <div class="col-md-12 d-flex justify-content-between">
                    <a href="contrato.jsp" class="btn btn-secondary">Cancelar</a>
                    <button type="submit" name="accion" value="actualizar" class="btn btn-success">Guardar cambios</button>
                </div>
            </form>
        </section>
    </div>

    <footer class="mt-5">
        <div>
            <a href="#" class="text-white text-decoration-none">&copy; Iván Rafael Redondo</a>
        </div>
        <div>
            Más información en <a href="#" class="text-white">Nuestras redes sociales</a>
        </div>
    </footer>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
