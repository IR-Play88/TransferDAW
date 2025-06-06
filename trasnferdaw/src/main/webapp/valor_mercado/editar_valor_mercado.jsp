<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.ValorMercadoHistorial" %>

<%
    ValorMercadoHistorial valorMercado = (ValorMercadoHistorial) request.getAttribute("valorMercado");
    if (valorMercado == null) {
        response.sendRedirect("valor_mercado.jsp");
        return;
    }
%>
<%@ include file="../importar/conf_editar.jsp" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Editar Valor de Mercado</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="./css/styles.css" />
</head>

<body>
    <div class="container-fluid">
        <header class="mb-4">
            <img src="../images/logo.png" alt="logo">
            <h1 class="mt-2">Editar Valor de Mercado de <span class="text-warning"><%= valorMercado.getNombreJugador() %></span></h1>
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
                <h2>Editar Información de Valor de Mercado</h2>

                <form action="ValorMercadoController" method="POST" class="row g-3">
                    <input type="hidden" name="id_historial" value="<%= valorMercado.getIdHistorial() %>" />
                    <input type="hidden" name="jugador" value="<%= valorMercado.getNombreJugador() %>" />

                    <div class="col-md-6">
                        <label for="jugador" class="form-label">Jugador</label>
                        <input type="text" class="form-control" id="jugador" name="jugador"
                               value="<%= valorMercado.getNombreJugador() %>" required disabled />
                    </div>

                    <div class="col-md-6">
                        <label for="fecha" class="form-label">Fecha</label>
                        <input type="date" class="form-control" id="fecha" name="fecha"
                               max="<%= java.time.LocalDate.now() %>"
                               value="<%= valorMercado.getFecha() != null ? valorMercado.getFecha().toString() : "" %>" required />
                    </div>

                    <div class="col-md-6">
                        <label for="valorMercado" class="form-label">Valor (€)</label>
                        <input type="number" step="0.01" class="form-control" id="valorMercado" name="valorMercado"
                               value="<%= valorMercado.getValorMercado() %>" required />
                    </div>

                    <div class="col-md-12">
                        <label for="motivo" class="form-label">Motivo (opcional)</label>
                        <textarea class="form-control" id="motivo" name="motivo" rows="3"><%= valorMercado.getMotivo() != null ? valorMercado.getMotivo() : "" %></textarea>
                    </div>

                    <div class="col-md-12 d-flex justify-content-between">
                        <a href="ValorMercadoController">
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
