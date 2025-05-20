<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String nombreUsuario = (String) session.getAttribute("usuario");
%>

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
                    <a class="nav-link dropdown-toggle" href="#" id="idiomasDropdown" role="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        Idiomas
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="idiomasDropdown">
                        <li><a class="dropdown-item" href="#">Español</a></li>
                        <li><a class="dropdown-item" href="#">Inglés</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="creearCuenta.jsp">Crear cuenta</a>
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
                        <li class="list-group-item">Cistiano Ronaldo</li>
                        <li class="list-group-item">Antony</li>
                    </ul>
                </div>

                <div class="widget">
                    <h3>TransferDAW</h3>
                    <blockquote class="blockquote">
                        Quieres ver todo lo relacionado con el mundo del fútbol, este es tu sitio, y si eres un apasionado, un
                        experto o quieres compartir tus conocimientos, no dudes en iniciar sesión, así dejarás de ser un simple lector
                        y pasarás a ser un administrador, donde podrás insertar, modificar, eliminar... No lo dudes.
                    </blockquote>
                </div>
            </aside>

            <section class="col-md-9" id="content">
                <h2>Todas nuestras opciones</h2>

                <article class="mb-4">
                    <h3>Jugadores</h3>
                    <% if (nombreUsuario == null) { %>
                    <p>Como eres lector ¡Puedes ver los jugadores disponibles y buscar tu jugador favorito!</p>
                    <% } else { %>
                    <p><%= nombreUsuario %> eres administrador ¡Tú tienes el control, inserta, modifica, elimina, busca un jugador en concreto o visualizados a todos!</p>
                    <% } %> 
                    <a href="jugador.jsp" class="btn btn-outline-primary btn-sm">Ver más >></a>
                </article>

                <article class="mb-4">
                    <h3>Equipos</h3>
                    <% if (nombreUsuario == null) { %>
                    <p>Como eres lector ¡Puedes ver los equipos disponibles y buscar tu equipo favorito!</p>
                    <% } else { %>
                    <p><%= nombreUsuario %> eres administrador ¡Tú tienes el control, inserta, modifica, elimina, busca un equipo en concreto o visualizados a todos!</p>
                    <% } %> 
                    <a href="equipo.jsp" class="btn btn-outline-primary btn-sm">Ver más >></a>
                </article>

                <article class="mb-4">
                    <h3>Competiciones</h3>
                    <% if (nombreUsuario == null) { %>
                    <p>Como eres lector ¡Puedes ver las competiciones disponibles!</p>
                    <% } else { %>
                    <p><%= nombreUsuario %> eres administrador ¡Tú tienes el control, inserta, modifica, elimina, busca una competición en concreto o visualizalas todas!</p>
                    <% } %> 
                    <a href="competicion.jsp" class="btn btn-outline-primary btn-sm">Ver más >></a>
                </article>

                <article class="mb-4">
                    <h3>Equipo-Competición</h3>
                    <% if (nombreUsuario == null) { %>
                        <p>Como eres lector ¡Puedes ver las  relaciones entre equipos y competiciones disponibles!</p>
                    <% } else { %>
                        <p><%= nombreUsuario %> eres administrador ¡Tú tienes el control, inserta, modifica, elimina, busca en que competición esta tu equipo o visualizalo todos!</p>
                    <% } %> 
                    <a href="equipo_competicion.jsp" class="btn btn-outline-primary btn-sm">Ver más >></a>
                </article>

                <article class="mb-4">
                    <h3>Contratos de los jugadores</h3>
                    <% if (nombreUsuario == null) { %>
                        <p>Como eres lector ¡Puedes ver los contratos disponibles!</p>
                    <% } else { %>
                        <p><%= nombreUsuario %> eres administrador ¡Tú tienes el control, inserta, modifica, elimina, busca una contrato en concreto o visualizalos todos!</p>
                    <% } %> 
                    <a href="contrato.jsp" class="btn btn-outline-primary btn-sm">Ver más >></a>
                </article>

                <article class="mb-4">
                    <h3>Valor de mercado de los jugadores</h3>
                    <% if (nombreUsuario == null) { %>
                        <p>Como eres lector ¡Puedes ver las competiciones disponibles!</p>
                    <% } else { %>
                        <p><%= nombreUsuario %> eres administrador ¡Tú tienes el control, inserta, modifica, elimina, busca un valor de mercado de un jugador en concreto 
                            o visualizalos todos!</p>
                    <% } %> 
                    <a href="valor_mercado.jsp" class="btn btn-outline-primary btn-sm">Ver más >></a>
                </article>

                <article class="mb-4">
                    <h3>Estadísticas de los Jugadores</h3>
                    <% if (nombreUsuario == null) { %>
                        <p>Como eres lector ¡Puedes ver las estadisticas de los jugadores disponibles!</p>
                    <% } else { %>
                        <p><%= nombreUsuario %> eres administrador ¡Tú tienes el control, inserta, modifica, elimina, busca una estadistica en concreto o visualizalas todas!</p>
                     <% } %> 
                    <a href="estadisticas.jsp" class="btn btn-outline-primary btn-sm">Ver más >></a>
                </article>

                <article class="mb-4">
                    <h3>Traspasos</h3>
                    <% if (nombreUsuario == null) { %>
                        <p>Como eres lector ¡Puedes ver las competiciones disponibles!</p>
                    <% } else { %>
                        <p><%= nombreUsuario %> eres administrador ¡Tú tienes el control, inserta, modifica, elimina, busca un traspaso en concreto o visualizalos todos!</p>
                    <% } %> 
                    <a href="traspaso.jsp" class="btn btn-outline-primary btn-sm">Ver más >></a>
                </article>

                <article class="mb-4">
                    <h3>Temporadas</h3>
                        <p>¡Puedes ver las temporadas disponibles!</p>
                    <a href="temporada.jsp" class="btn btn-outline-primary btn-sm">Ver más >></a>
                </article>
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
