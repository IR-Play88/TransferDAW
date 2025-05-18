<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Contrato" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>TransferDAW</title>
    <link
        rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
    />
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        header {
            background-color: #343a40;
            color: white;
            padding: 1rem;
            text-align: center;
        }
        header img {
            height: 50px;
        }
        nav ul {
            list-style: none;
            padding: 0;
        }
        nav ul li {
            display: inline-block;
            margin-right: 1rem;
        }
        aside {
            background-color: #e9ecef;
            padding: 1rem;
            border-radius: 0.5rem;
        }
        .widget h3 {
            margin-top: 0;
        }
        table th,
        table td {
            vertical-align: middle;
        }
        footer {
            background-color: #343a40;
            color: white;
            padding: 1rem;
            text-align: center;
            margin-top: 2rem;
        }
    </style>
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
                <li class="nav-item dropdown">
                    <a
                        class="nav-link dropdown-toggle"
                        data-bs-toggle="dropdown"
                        href="#"
                        role="button"
                        aria-expanded="false"
                        >Idiomas</a
                    >
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
                    <h3>Iniciar Sesión</h3>
                    <form>
                        <input
                            class="form-control mb-2"
                            type="text"
                            name="usuario"
                            placeholder="usuario"
                        />
                        <input
                            class="form-control mb-2"
                            type="password"
                            name="password"
                            placeholder="password"
                        />
                        <input class="btn btn-primary w-100" type="submit" value="Enviar" />
                    </form>
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
                <h2>Contratos</h2>

                <form method="GET" action="ContratoController" class="row g-3 mb-3">
                    <div class="col-md-6">
                        <input
                            class="form-control"
                            type="text"
                            name="jugador"
                            placeholder="Jugador"
                            required
                        />
                    </div>
                    <div class="col-md-6">
                        <input class="form-control" type="text" name="equipo" placeholder="Equipo" />
                    </div>
                    <div class="col-md-6">
                        <input
                            class="form-control"
                            type="date"
                            name="fechaInicio"
                            placeholder="Fecha Inicio"
                            required
                        />
                    </div>
                    <div class="col-md-6">
                        <input
                            class="form-control"
                            type="date"
                            name="fechaFin"
                            placeholder="Fecha Fin"
                            required
                        />
                    </div>
                    <div class="col-md-6">
                        <input
                            class="form-control"
                            type="number"
                            step="0.01"
                            name="salario"
                            placeholder="Salario"
                            required
                        />
                    </div>
                    <div class="col-md-6">
                        <select class="form-select" name="tipoContrato">
                            <option value="cesion">Cesión</option>
                            <option value="nuevo">Nuevo fichaje</option>
                            <option value="libre">Libre</option>
                        </select>
                    </div>
                    <div class="col-md-12 d-flex">
                        <input
                            type="text"
                            id="buscador"
                            class="form-control me-2"
                            placeholder="Buscar contrato..."
                        />
                        <button type="submit" name="accion" value="añadir" class="btn btn-success">
                            Añadir
                        </button>
                    </div>
                </form>

                <table class="table table-striped" id="datos">
                    <thead class="table-dark">
                        <tr>
                            <th>Jugador</th>
                            <th>Equipo</th>
                            <th>Fecha Inicio</th>
                            <th>Fecha Fin</th>
                            <th>Salario</th>
                            <th>Tipo</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
                            List<Contrato> lista = dao.listarContratos();
                            for (Contrato c : lista) {
                        %>
                        <tr>
                            <td><%= c.getNombreJugador() %></td>
                            <td><%= c.getNombreEquipo() %></td>
                            <td><%= c.getFechaInicio() %></td>
                            <td><%= c.getFechaFin() %></td>
                            <td><%= c.getSalario() %></td>
                            <td><%= c.getTipoContrato() %></td>
                            <td>
                                <form action="ContratoController" method="GET" class="d-flex gap-1">
                                    <input type="hidden" name="id_contrato" value="<%= c.getIdContrato() %>" />
                                    <button
                                        type="submit"
                                        name="accion"
                                        value="eliminar"
                                        class="btn btn-danger btn-sm"
                                    >
                                        Eliminar
                                    </button>
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
                Más información en
                <a href="#" class="text-white">Nuestras redes sociales</a>
            </div>
        </footer>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="./js/buscador.js"></script>
</body>

</html>
