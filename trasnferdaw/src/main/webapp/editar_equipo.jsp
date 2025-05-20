<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Equipo" %>
<%
    Equipo equipo = (Equipo) request.getAttribute("equipo");
%>
<%
    String rol = (String) session.getAttribute("rol");
    if (rol == null || !rol.equals("admin")) {
        response.sendRedirect("index.jsp");
        return;
    }

    boolean esAdmin = true; // porque ya comprobaste que sí lo es
    String nombreUsuario = (String) session.getAttribute("usuario");
%>
%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Editar Equipo</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/styles.css">
</head>

<body>
<div class="container-fluid">
    <header class="mb-4">
        <img src="../images/logo.png" alt="logo">
        <h1 class="mt-2">Editando equipo: <span class="text-warning"><%= equipo.getNombre() %></span></h1>
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
                    Gestiona los mejores equipos de fútbol del mundo. Edita, consulta y organiza la información de los clubes.
                </blockquote>
            </div>
        </aside>

        <section class="col-md-9">
            <h2>Editar Equipo</h2>

            <form method="GET" action="EquipoController" class="row g-3">
                <input type="hidden" name="id_equipo" value="<%= equipo.getIdEquipo() %>">
            
                <div class="col-md-6">
                    <label for="nombre" class="form-label">Nombre actual: <strong><%= equipo.getNombre() %></strong></label>
                    <input type="text" name="nombre" id="nombre" class="form-control" value="<%= equipo.getNombre() %>" required>
                </div>
            
                <div class="col-md-6">
                    <label for="ciudad" class="form-label">Ciudad actual: <strong><%= equipo.getCiudad() %></strong></label>
                    <input type="text" name="ciudad" id="ciudad" class="form-control" value="<%= equipo.getCiudad() %>" required>
                </div>
            
                <div class="col-md-6">
                    <label for="pais" class="form-label">País actual: <strong><%= equipo.getPais() %></strong></label>
                    <input type="text" name="pais" id="pais" class="form-control" value="<%= equipo.getPais() %>" required>
                </div>
            
                <div class="col-md-6">
                    <label for="anio" class="form-label">Año de fundación: <strong><%= equipo.getAnioFundacion() %></strong></label>
                    <input type="number" name="anio" id="anio" class="form-control" value="<%= equipo.getAnioFundacion() %>" required>
                </div>
            
                <div class="col-md-6">
                    <label for="presupuesto" class="form-label">Presupuesto actual: <strong><%= equipo.getPresupuesto() %></strong></label>
                    <input type="number" step="0.01" name="presupuesto" id="presupuesto" class="form-control" value="<%= equipo.getPresupuesto() %>" required>
                </div>
            
                <div class="col-md-6">
                    <label for="propietario" class="form-label">Propietario actual: <strong><%= equipo.getPropietario() %></strong></label>
                    <input type="text" name="propietario" id="propietario" class="form-control" value="<%= equipo.getPropietario() %>" required>
                </div>
            
                <div class="col-md-6">
                    <label for="estadio" class="form-label">Estadio actual: <strong><%= equipo.getEstadioNombre() %></strong></label>
                    <input type="text" name="estadio" id="estadio" class="form-control" value="<%= equipo.getEstadioNombre() %>" required>
                </div>
            
                <div class="col-md-6">
                    <label for="entrenador" class="form-label">Entrenador actual: <strong><%= equipo.getEntrenadorNombre() %></strong></label>
                    <input type="text" name="entrenador" id="entrenador" class="form-control" value="<%= equipo.getEntrenadorNombre() %>" required>
                </div>
            
                <div class="col-md-12 d-flex justify-content-between">
                    <a href="equipo.jsp" class="btn btn-secondary">Cancelar</a>
                    <button type="submit" name="accion" value="actualizar" class="btn btn-success">Guardar cambios</button>
                </div>
            </form>
            
        </section>
    </div>

    <%@ include file="footer.jsp" %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
