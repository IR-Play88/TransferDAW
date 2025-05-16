<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.EquipoCompeticion" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>TransferDAW</title>
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
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Inicio</a>
                </li>
                <li class="nav-item dropdown position-static">
                    <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown" role="button" aria-expanded="false">
                        Idiomas
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Español</a></li>
                        <li><a class="dropdown-item" href="#">Inglés</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Crear cuenta</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contacto</a>
                </li>
            </ul>
        </nav>

        <div class="row">
            <aside class="col-md-3">
                <div class="widget mb-3">
                    <h3>Iniciar Sesión</h3>
                    <form>
                        <input class="form-control mb-2" type="text" name="usuario" placeholder="usuario" />
                        <input class="form-control mb-2" type="password" name="password" placeholder="password" />
                        <input class="btn btn-primary w-100" type="submit" value="Enviar" />
                    </form>
                </div>

                <div class="widget mb-3">
                    <h3>Jugadores populares</h3>
                    <ul class="list-group">
                        <li class="list-group-item"><a href="#">Marco Asensio</a></li>
                        <li class="list-group-item"><a href="#">Lucas Vázquez</a></li>
                        <li class="list-group-item"><a href="#">Isco</a></li>
                        <li class="list-group-item"><a href="#">Cristiano Ronaldo</a></li>
                        <li class="list-group-item"><a href="#">Antony</a></li>
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
                <h2>Equipo - Competición</h2>
                <form method="GET" action="EquipoCompeticionController" class="row g-3">
                    <div class="col-md-6">
                        <input class="form-control" type="text" name="nombreEquipo" placeholder="Nombre del Equipo" required />
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" type="text" name="nombreCompeticion" placeholder="Competición" required />
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" type="text" name="nombreTemporada" placeholder="Temporada" required />
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" type="number" name="rango" placeholder="Rango" required />
                    </div>
                    <div class="col-md-12 d-flex">
                        <input type="text" id="buscador" class="form-control me-2" placeholder="Buscar equipo en competición..." />
                        <button type="submit" name="accion" value="añadir" class="btn btn-success">Añadir</button>
                    </div>
                </form>

                <table class="table table-striped mt-4" id="datos">
                    <thead class="table-dark">
                        <tr>
                            <th>Equipo</th>
                            <th>Competición</th>
                            <th>Temporada</th>
                            <th>Rango</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
                            List<EquipoCompeticion> list = dao.listarEquiposCompeticion();
                            for (EquipoCompeticion equipoCompeticion : list) {
                        %>
                        <tr>
                            <td><%= equipoCompeticion.getNombreEquipo() %></td>
                            <td><%= equipoCompeticion.getNombreCompeticion() %></td>
                            <td><%= equipoCompeticion.getNombreTemporada() %></td>
                            <td><%= equipoCompeticion.getRango() %></td>
                            <td>
                                <form action="EquipoCompeticionController" method="GET" class="d-flex gap-1">
                                    <input type="hidden" name="id_equipo" value="<%= equipoCompeticion.getEquipoId() %>" />
                                    <input type="hidden" name="id_competicion" value="<%= equipoCompeticion.getCompeticionId() %>" />
                                    <input type="hidden" name="id_temporada" value="<%= equipoCompeticion.getTemporadaId() %>" />
                                    <button type="submit" name="accion" value="eliminar" class="btn btn-danger btn-sm">Eliminar</button>
                                    <button type="submit" name="accion" value="modificar" class="btn btn-warning btn-sm">Modificar</button>
                                </form>
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
