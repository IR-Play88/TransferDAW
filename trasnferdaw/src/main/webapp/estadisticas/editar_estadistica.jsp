<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.EstadisticasTemporada" %>
<%
    EstadisticasTemporada est = (EstadisticasTemporada) request.getAttribute("estadistica");
%>
<%@ include file="../importar/conf_editar.jsp" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Estadística</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="./css/styles.css">
</head>
<body>
    <div class="container-fluid">
        <header class="mb-4">
            <img src="../images/logo.png" alt="logo">
            <h1 class="mt-2">Editando estadísticas de <span class="text-warning"><%= est.getNombreJugador() %></span></h1>
        </header>

        <nav class="mb-4">
            <ul class="nav justify-content-center">
                <li class="nav-item">
                    <a class="nav-link" href="../index.jsp">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="CuentaController">Crear cuenta</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ContactoController">Contacto</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="EliminarController">Eliminar cuenta</a>
                </li>
            </ul>
        </nav>

        <div class="row">
            <aside class="col-md-3">
                <div class="widget mb-3">
                    <% if (nombreUsuario == null) { %>
                        <h3>Iniciar Sesión</h3>
                        <form method="POST" action="LoginController">
                            <input type="text" class="form-control mb-2" name="usuario" placeholder="usuario" />
                            <input type="password" class="form-control mb-2" name="password" placeholder="password" />
                            <input type="submit" class="btn btn-primary w-100" value="Enviar" />
                        </form>
                    <% } else { %>
                        <h3>Bienvenido, <%= nombreUsuario %></h3>
                        <form method="POST" action="LogoutController">
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
                <%@ include file="../importar/mensaje.jsp" %>
                <h2>Editar Estadísticas del Jugador</h2>

                <form method="POST" action="EstadisticasTemporadaController" class="row g-3">
                    <input type="hidden" name="accion" value="actualizar" />
                    <input type="hidden" name="jugadorId" value="<%= est.getJugadorId() %>" />
                    <input type="hidden" name="temporadaId" value="<%= est.getTemporadaId() %>" />
                    <input type="hidden" name="competicionId" value="<%= est.getCompeticionId() %>" />
                    <input type="hidden" name="equipoId" value="<%= est.getEquipoId() %>" />

                    <div class="col-md-6">
                        <label class="form-label">Jugador</label>
                        <input type="text" class="form-control" value="<%= est.getNombreJugador() %>" readonly disabled/>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Temporada</label>
                        <input type="text" class="form-control" value="<%= est.getNombreTemporada() %>" readonly disabled/>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Competición</label>
                        <input type="text" class="form-control" value="<%= est.getNombreCompeticion() %>" readonly disabled/>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Equipo</label>
                        <input type="text" class="form-control" value="<%= est.getNombreEquipo() %>" readonly disabled/>
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Partidos Jugados</label>
                        <input type="number" class="form-control" name="partidosJugados" value="<%= est.getPartidosJugados() %>" required />
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Goles</label>
                        <input type="number" class="form-control" name="goles" value="<%= est.getGoles() %>" required />
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Asistencias</label>
                        <input type="number" class="form-control" name="asistencias" value="<%= est.getAsistencias() %>" required />
                    </div>

                    <div class="col-md-12 d-flex justify-content-between">
                        <a href="EstadisticasTemporadaController">
                            <img src="images/atras.png" alt="Cancelar">
                        </a>                      
                        <button type="submit" name="accion" value="actualizar" class="btn btn-primary btn-sm">
                            <img src="images/guardar.png" alt="Guardar cambios">
                        </button>
                    </div>
                </form>
            </section>
        </div>

        <%@ include file="../importar/footer.jsp" %>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
