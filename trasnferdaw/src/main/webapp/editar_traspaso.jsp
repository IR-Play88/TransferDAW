<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Traspaso" %>
<%
    String rol = (String) session.getAttribute("rol");
    if (rol == null || !rol.equals("admin")) {
        response.sendRedirect("index.jsp");
        return;
    }

    boolean esAdmin = true; // porque ya comprobaste que sí lo es
    String nombreUsuario = (String) session.getAttribute("usuario");
%>
<jsp:useBean id="traspaso" scope="request" class="es.tierno.daw.trasnferdaw.model.entities.Traspaso" />
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Traspaso</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/styles.css">
</head>
<body>
<div class="container-fluid">
    <header class="mb-4">
        <img src="../images/logo.png" alt="logo">
        <h1 class="mt-2">Editando Traspaso de <span class="text-warning"><%= traspaso.getNombreJugador() %></span></h1>
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
            <h2>Editar Traspaso</h2>

            <form action="TraspasoController" method="GET" class="row g-3">
                <input type="hidden" name="id_traspaso" value="<%= traspaso.getIdTraspaso() %>">

                <div class="col-md-6">
                    <label class="form-label">Jugador: <strong><%= traspaso.getNombreJugador() %></strong></label>
                    <input type="text" class="form-control" value="<%= traspaso.getNombreJugador() %>" disabled>
                    <input type="hidden" name="jugadorId" value="<%= traspaso.getJugadorId() %>">
                </div>

                <div class="col-md-6">
                    <label class="form-label">Equipo Origen: <strong><%= traspaso.getNombreEquipoOrigen() %></strong></label>
                    <input type="text" class="form-control" value="<%= traspaso.getNombreEquipoOrigen() %>" disabled>
                    <input type="hidden" name="equipoOrigenId" value="<%= traspaso.getEquipoOrigenId() != null ? traspaso.getEquipoOrigenId() : "" %>">
                </div>

                <div class="col-md-6">
                    <label class="form-label">Equipo Destino: <strong><%= traspaso.getNombreEquipoDestino() %></strong></label>
                    <input type="text" class="form-control" value="<%= traspaso.getNombreEquipoDestino() %>" disabled>
                    <input type="hidden" name="equipoDestinoId" value="<%= traspaso.getEquipoDestinoId() != null ? traspaso.getEquipoDestinoId() : "" %>">
                </div>

                <div class="col-md-6">
                    <label class="form-label">Temporada: <strong><%= traspaso.getNombreTemporada() %></strong></label>
                    <input type="text" class="form-control" value="<%= traspaso.getNombreTemporada() %>" disabled>
                    <input type="hidden" name="temporadaId" value="<%= traspaso.getTemporadaId() != null ? traspaso.getTemporadaId() : "" %>">
                </div>

                <div class="col-md-6">
                    <label for="fecha_traspaso" class="form-label">Fecha de Traspaso: <strong><%= traspaso.getFechaTraspaso() %></strong></label>
                    <input type="date" class="form-control" name="fechaTraspaso" id="fecha_traspaso" value="<%= traspaso.getFechaTraspaso() != null ? traspaso.getFechaTraspaso().toString() : "" %>">
                </div>

                <div class="col-md-6">
                    <label for="cantidad" class="form-label">Cantidad (€): <strong><%= traspaso.getCantidad() %></strong></label>
                    <input type="number" step="0.01" class="form-control" name="cantidad" id="cantidad" value="<%= traspaso.getCantidad() %>">
                </div>

                <div class="col-md-6">
                    <label for="tipo" class="form-label">Tipo: <strong><%= traspaso.getTipo() %></strong></label>
                    <select class="form-select" name="tipo" id="tipo">
                        <option value="compra" <%= "compra".equalsIgnoreCase(traspaso.getTipo()) ? "selected" : "" %>>Compra</option>
                        <option value="cesion" <%= "cesion".equalsIgnoreCase(traspaso.getTipo()) ? "selected" : "" %>>Cesión</option>
                        <option value="fin_cesion" <%= "fin_cesion".equalsIgnoreCase(traspaso.getTipo()) ? "selected" : "" %>>Fin de cesión</option>
                        <option value="libre" <%= "libre".equalsIgnoreCase(traspaso.getTipo()) ? "selected" : "" %>>Libre</option>
                    </select>
                </div>

                <div class="col-md-12 d-flex justify-content-between">
                    <a href="traspaso.jsp" class="btn btn-secondary">Cancelar</a>
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
