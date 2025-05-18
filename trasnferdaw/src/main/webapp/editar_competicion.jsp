<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Competicion" %>
<%
    Competicion competicion = (Competicion) request.getAttribute("competicion");
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
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Editar Competición</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/styles.css">
</head>

<body>
<div class="container-fluid">
    <header class="mb-4">
        <img src="../images/logo.png" alt="logo">
        <h1 class="mt-2">Editando competición: <span class="text-warning"><%= competicion.getNombre() %></span></h1>
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
                    Todo sobre las competiciones más importantes del mundo del fútbol. Edita, consulta y administra competiciones a tu gusto.
                </blockquote>
            </div>
        </aside>

        <section class="col-md-9">
            <h2>Editar Competición</h2>

            <form method="GET" action="CompeticionController" class="row g-3">
                <input type="hidden" name="id_competicion" value="<%= competicion.getIdCompeticion() %>">

                <div class="col-md-6">
                    <label for="nombre" class="form-label">Nombre actual: <strong><%= competicion.getNombre() %></strong></label>
                    <input type="text" name="nombre" id="nombre" class="form-control" value="<%= competicion.getNombre() %>" required>
                </div>

                <div class="col-md-6">
                    <label for="pais" class="form-label">País actual: <strong><%= competicion.getPais() %></strong></label>
                    <input type="text" name="pais" id="pais" class="form-control" value="<%= competicion.getPais() %>" required>
                </div>

                <div class="col-md-6">
                    <label for="tipo" class="form-label">Tipo actual: <strong><%= competicion.getTipo() %></strong></label>
                    <select name="tipo" id="tipo" class="form-select" required>
                        <option value="Liga" <%= "Liga".equals(competicion.getTipo()) ? "selected" : "" %>>Liga</option>
                        <option value="Copa" <%= "Copa".equals(competicion.getTipo()) ? "selected" : "" %>>Copa</option>
                        <option value="Internacional" <%= "Internacional".equals(competicion.getTipo()) ? "selected" : "" %>>Internacional</option>
                        <option value="Seleccion" <%= "Seleccion".equals(competicion.getTipo()) ? "selected" : "" %>>Selección</option>
                        <option value="Amistoso" <%= "Amistoso".equals(competicion.getTipo()) ? "selected" : "" %>>Amistoso</option>
                    </select>
                </div>

                <div class="col-md-6">
                    <label for="numeroEquipos" class="form-label">Número de equipos actual: <strong><%= competicion.getNumeroEquipos() %></strong></label>
                    <input type="number" name="numeroEquipos" id="numeroEquipos" class="form-control" value="<%= competicion.getNumeroEquipos() %>" required>
                </div>

                <div class="col-md-6">
                    <label for="anioCreacion" class="form-label">Año de creación actual: <strong><%= competicion.getAnioCreacion() %></strong></label>
                    <input type="number" name="anioCreacion" id="anioCreacion" class="form-control" value="<%= competicion.getAnioCreacion() %>" required>
                </div>

                <div class="col-md-12 d-flex justify-content-between">
                    <a href="competicion.jsp" class="btn btn-secondary">Cancelar</a>
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
