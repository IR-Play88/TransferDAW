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
                        <li class="list-group-item">Cistiano Ronaldo</li>
                        <li class="list-group-item">Antony></li>
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
                    <p>¿Eres lector? ¡Explora todos los detalles!</p>
                    <p>Consulta el perfil completo de cada jugador.</p>
                    <p>¿Eres administrador? ¡Tú tienes el control!</p>
                    <p>Edita, elimina o agrega jugadores de forma rápida y sencilla.</p>
                    <a href="jugador.jsp" class="btn btn-outline-primary btn-sm">Ver más >></a>
                </article>

                <article class="mb-4">
                    <h3>Equipos</h3>
                    <p>¿Eres lector? ¡Explora todos los detalles!</p>
                    <p>Consulta el perfil completo de cada equipo.</p>
                    <p>¿Eres administrador? ¡Tú tienes el control!</p>
                    <p>Edita, elimina o agrega equipos de forma rápida y sencilla.</p>
                    <a href="equipo.jsp" class="btn btn-outline-primary btn-sm">Ver más >></a>
                </article>

                <article class="mb-4">
                    <h3>Competiciones</h3>
                    <p>¿Eres lector? ¡Explora todos los detalles!</p>
                    <p>Consulta los detalles completos de cada competición.</p>
                    <p>¿Eres administrador? ¡Tú tienes el control!</p>
                    <p>Edita, elimina o agrega competiciones de forma rápida y sencilla.</p>
                    <a href="competicion.jsp" class="btn btn-outline-primary btn-sm">Ver más >></a>
                </article>

                <article class="mb-4">
                    <h3>Equipo-Competición</h3>
                    <p>¿Eres lector? ¡Explora todos los detalles!</p>
                    <p>Consulta qué equipos están en cada competición.</p>
                    <p>¿Eres administrador? ¡Tú tienes el control!</p>
                    <p>Edita, elimina o agrega equipos a competiciones de forma rápida y sencilla.</p>
                    <a href="equipo_competicion.jsp" class="btn btn-outline-primary btn-sm">Ver más >></a>
                </article>

                <article class="mb-4">
                    <h3>Contratos de los jugadores</h3>
                    <p>¿Eres lector? ¡Explora todos los detalles!</p>
                    <p>Consulta los contratos completos de cada jugador.</p>
                    <p>¿Eres administrador? ¡Tú tienes el control!</p>
                    <p>Edita, elimina o agrega contratos para los jugadores de forma rápida y sencilla.</p>
                    <a href="contrato.jsp" class="btn btn-outline-primary btn-sm">Ver más >></a>
                </article>

                <article class="mb-4">
                    <h3>Valor de mercado de los jugadores</h3>
                    <p>¿Eres lector? ¡Explora todos los detalles!</p>
                    <p>Consulta el valor de mercado de cada jugador.</p>
                    <p>¿Eres administrador? ¡Tú tienes el control!</p>
                    <p>Edita, elimina o agrega los valores de mercado de cada jugador de forma rápida y sencilla.</p>
                    <a href="valor_mercado.jsp" class="btn btn-outline-primary btn-sm">Ver más >></a>
                </article>

                <article class="mb-4">
                    <h3>Estadísticas de los Jugadores</h3>
                    <p>¿Eres lector? ¡Explora todos los detalles!</p>
                    <p>Consulta las estadísticas completas de cada jugador.</p>
                    <p>¿Eres administrador? ¡Tú tienes el control!</p>
                    <p>Edita, elimina o agrega estadísticas para los jugadores de forma rápida y sencilla.</p>
                    <a href="estadisticas.jsp" class="btn btn-outline-primary btn-sm">Ver más >></a>
                </article>

                <article class="mb-4">
                    <h3>Traspasos</h3>
                    <p>¿Eres lector? ¡Explora todos los detalles!</p>
                    <p>Consulta los traspasos de cada jugador.</p>
                    <p>¿Eres administrador? ¡Tú tienes el control!</p>
                    <p>Edita, elimina o agrega traspasos de cada jugador de forma rápida y sencilla.</p>
                    <a href="traspaso.jsp" class="btn btn-outline-primary btn-sm">Ver más >></a>
                </article>

                <article class="mb-4">
                    <h3>Temporadas</h3>
                    <p>¿Eres lector? ¡Explora todos los detalles!</p>
                    <p>Consulta todas las temporadas.</p>
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
