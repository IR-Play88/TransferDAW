<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.EquipoCompeticion" %>
<%
    String rol = (String) session.getAttribute("rol");
    if (rol == null || !rol.equals("admin")) {
        response.sendRedirect("index.jsp");
        return;
    }

    boolean esAdmin = true; // porque ya comprobaste que sí lo es
    String nombreUsuario = (String) session.getAttribute("usuario");
%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Editar Equipo en Competición</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="./css/styles.css">
</head>

<body>
<div class="container-fluid">
    <header class="mb-4">
        <img src="../images/logo.png" alt="logo" />
        <h1 class="mt-2">Bienvenido <span class="text-warning">amante</span> del fútbol</h1>
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
            <li class="nav-item"><a class="nav-link" href="#">Contacto</a></li>
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
            <h2>Editar Equipo en Competición</h2>

            <%
                EquipoCompeticion ec = (EquipoCompeticion) request.getAttribute("equipoCompeticion");
                if (ec == null) {
            %>
            <div class="alert alert-danger">No se encontró el equipo en competición para editar.</div>
            <%
                } else {
            %>

            <form method="POST" action="EquipoCompeticionController" class="row g-3">
                <input type="hidden" name="accion" value="actualizar" />
                <input type="hidden" name="equipoId" value="<%= ec.getEquipoId() %>" />
                <input type="hidden" name="competicionId" value="<%= ec.getCompeticionId() %>" />
                <input type="hidden" name="temporadaId" value="<%= ec.getTemporadaId() %>" />

                <div class="col-md-6">
                    <label for="nombreEquipo" class="form-label">Nombre del Equipo</label>
                    <input type="text" class="form-control" id="nombreEquipo" name="nombreEquipo"
                           value="<%= ec.getNombreEquipo() %>" required disabled/>
                </div>

                <div class="col-md-6">
                    <label for="nombreCompeticion" class="form-label">Nombre de la Competición</label>
                    <input type="text" class="form-control" id="nombreCompeticion" name="nombreCompeticion"
                           value="<%= ec.getNombreCompeticion() %>" required disabled/>
                </div>

                <div class="col-md-6">
                    <label for="nombreTemporada" class="form-label">Temporada</label>
                    <input type="text" class="form-control" id="nombreTemporada" name="nombreTemporada"
                           value="<%= ec.getNombreTemporada() %>" required disabled/>
                </div>

                <div class="col-md-6">
                    <label for="rango" class="form-label">Rango</label>
                    <input type="number" class="form-control" id="rango" name="rango" 
                           value="<%= ec.getRango() %>" required />
                </div>
                <div class="col-md-12 d-flex justify-content-between">
                    <a href="equipo_competicion.jsp" class="btn btn-secondary">Cancelar</a>
                    <button type="submit" name="accion" value="actualizar" class="btn btn-success">Guardar cambios</button>
                </div>
            </form>

            <%
                }
            %>
        </section>
    </div>

    <%@ include file="footer.jsp" %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
