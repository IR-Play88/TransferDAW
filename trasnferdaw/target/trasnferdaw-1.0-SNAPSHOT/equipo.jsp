<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Equipo" %>
<%
    String rol = (String) session.getAttribute("rol");
    boolean esAdmin = rol != null && rol.equals("admin");
%>
<%
    String nombreUsuario = (String) session.getAttribute("usuario");
%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TransferDAW</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/styles.css">
</head>

<body>
    <div class="container-fluid">
        <header class="mb-4">
            <img src="../images/logo.png" alt="logo">
            <h1 class="mt-2">Bienvenido <span class="text-warning">amante</span> del fútbol</h1>
        </header>

        <nav class="mb-4">
            <ul class="nav justify-content-center">
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Inicio</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#">Idiomas</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Español</a></li>
                        <li><a class="dropdown-item" href="#">Inglés</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Crear cuenta</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./html/contacto.html">Contacto</a>
                </li>
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
                <h2>Equipos</h2>

                <form method="GET" action="EquipoController" class="row g-3 mb-4">
                    <div class="col-md-6"><input class="form-control" type="text" name="nombre" placeholder="Nombre" required></div>
                    <div class="col-md-6"><input class="form-control" type="text" name="ciudad" placeholder="Ciudad"></div>
                    <div class="col-md-6"><input class="form-control" type="text" name="pais" placeholder="País"></div>
                    <div class="col-md-6"><input class="form-control" type="number" name="anio" placeholder="Año de fundación"></div>
                    <div class="col-md-6"><input class="form-control" type="number" step="0.01" name="presupuesto" placeholder="Presupuesto"></div>
                    <div class="col-md-6"><input class="form-control" type="text" name="propietario" placeholder="Propietario"></div>
                    <div class="col-md-6"><input class="form-control" type="text" name="estadio" placeholder="Estadio"></div>
                    <div class="col-md-6"><input class="form-control" type="text" name="entrenador" placeholder="Entrenador"></div>
                    <div class="col-md-12 d-flex">
                        <input type="text" id="buscador" class="form-control me-2" placeholder="Buscar equipo...">
                        <% if (esAdmin) { %>
                        <button type="submit" name="accion" value="añadir" class="btn btn-success">Añadir</button>
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
                        TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
                        List<Equipo> list = dao.listarEquipos();
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
                                <td>
                                    <% if (esAdmin) { %>
                                    <form action="EquipoController" method="GET" class="d-flex gap-1">
                                        <input type="hidden" name="id_equipo" value="<%= equipo.getIdEquipo() %>">
                                        <button type="submit" name="accion" value="eliminar" class="btn btn-danger btn-sm">Eliminar</button>
                                        <a href="EquipoController?accion=modificar&id_equipo=<%= equipo.getIdEquipo() %>" class="btn btn-warning btn-sm">Modificar</a>
                                    </form>
                                    <% } %>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="./js/buscador.js"></script>
</body>

</html>
