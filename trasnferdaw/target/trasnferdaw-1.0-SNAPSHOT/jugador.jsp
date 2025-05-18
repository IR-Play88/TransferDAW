<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Jugador" %>
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
                    <h3>Iniciar Sesión</h3>
                    <form>
                        <input class="form-control mb-2" type="text" name="usuario" placeholder="usuario">
                        <input class="form-control mb-2" type="password" name="password" placeholder="password">
                        <input class="btn btn-primary w-100" type="submit" value="Enviar">
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
                <h2>Jugadores</h2>
                <form method="GET" action="JugadorController" class="row g-3">
                    <div class="col-md-6"><input class="form-control" type="text" name="nombre" placeholder="Nombre" required></div>
                    <div class="col-md-6"><input class="form-control" type="text" name="alias" placeholder="Alias"></div>
                    <div class="col-md-6"><input class="form-control" type="date" name="fecha_nacimiento"></div>
                    <div class="col-md-6"><input class="form-control" type="text" name="nacionalidad" placeholder="Nacionalidad"></div>
                    <div class="col-md-6"><input class="form-control" type="number" step="0.01" name="altura" placeholder="Altura"></div>
                    <div class="col-md-6"><input class="form-control" type="number" step="0.01" name="peso" placeholder="Peso"></div>
                    <div class="col-md-6">
                        <select class="form-select" name="pie_dominante">
                            <option value="derecho">Derecho</option>
                            <option value="izquierdo">Izquierdo</option>
                            <option value="ambidiestro">Ambidiestro</option>
                        </select>
                    </div>
                    <div class="col-md-6"><input class="form-control" type="number" name="valor_mercado" placeholder="Valor mercado"></div>
                    <div class="col-md-6">
                        <select class="form-select" name="posicion">
                            <option value="delantero">Delantero</option>
                            <option value="mediocampista">Mediocampista</option>
                            <option value="defensa">Defensa</option>
                            <option value="portero">Portero</option>
                        </select>
                    </div>
                    <div class="col-md-6"><input class="form-control" type="text" name="representante" placeholder="Representante"></div>
                    <div class="col-md-6"><input class="form-control" type="text" name="seleccion" placeholder="Selección"></div>
                    <div class="col-md-6 d-flex">
                        <input type="text" id="buscador" class="form-control me-2" placeholder="Buscar jugador...">
                        <button type="submit" name="accion" value="añadir" class="btn btn-success">Añadir</button>
                    </div>
                </form>

                <table class="table table-striped mt-4" id="datos">
                    <thead class="table-dark">
                        <tr>
                            <th>Nombre</th>
                            <th>Alias</th>
                            <th>Fecha Nac.</th>
                            <th>Nacionalidad</th>
                            <th>Altura</th>
                            <th>Peso</th>
                            <th>Pie</th>
                            <th>Valor</th>
                            <th>Posición</th>
                            <th>Representante</th>
                            <th>Selección</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                        TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
                        List<Jugador> list = dao.listarJugadores();
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
                                <td><%= jugador.getRepresentanteNombre() %></td>
                                <td><%= jugador.getSeleccionNombre() %></td>
                                <td>
                                    <form action="JugadorController" method="GET" class="d-flex gap-1">
                                        <input type="hidden" name="id_jugador" value="<%= jugador.getIdJugador() %>">
                                        <button type="submit" name="accion" value="eliminar" class="btn btn-danger btn-sm">Eliminar</button>
                                        <a href="JugadorController?accion=modificar&id_jugador=<%= jugador.getIdJugador() %>" class="btn btn-warning btn-sm">Modificar</a>

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