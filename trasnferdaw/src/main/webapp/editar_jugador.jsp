<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Jugador" %>
<%
    String rol = (String) session.getAttribute("rol");
    if (rol == null || !rol.equals("admin")) {
        response.sendRedirect("index.jsp");
        return;
    }

    boolean esAdmin = true; // porque ya comprobaste que sí lo es
    String nombreUsuario = (String) session.getAttribute("usuario");
%>

<jsp:useBean id="jugador" scope="request" class="es.tierno.daw.trasnferdaw.model.entities.Jugador" />
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Editar Jugador</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/styles.css">
</head>

<body>
<div class="container-fluid">
    <header class="mb-4">
        <img src="../images/logo.png" alt="logo">
        <h1 class="mt-2">Editando a <span class="text-warning"><%= jugador.getNombre() %></span></h1>
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
            <%
            String error = (String) session.getAttribute("error");
            if (error != null) {
        %>
            <div class="alert alert-danger"><%= error %></div>
        <%
                session.removeAttribute("error");
            }
        
            String mensaje = (String) session.getAttribute("mensaje");
            if (mensaje != null) {
        %>
            <div class="alert alert-success"><%= mensaje %></div>
        <%
                session.removeAttribute("mensaje");
            }
        %>
            <h2>Editar Jugador</h2>

            <form action="JugadorController" method="POST" class="row g-3">
                <input type="hidden" name="id_jugador" value="<%= jugador.getIdJugador() %>">
                
                <label for="nombre" class="form-label">Editando a: <strong><%= jugador.getNombre() %></strong></label>
    
              
            
                <div class="col-md-6">
                    <label for="nombre" class="form-label">Nombre: <strong><%= jugador.getNombre() %></strong></label>
                    <input class="form-control" type="text" name="nombre" id="nombre" value="<%= jugador.getNombre() %>" required placeholder="Nombre">
                </div>
            
                <div class="col-md-6">
                    <label for="alias" class="form-label">Alias: <strong><%= jugador.getAlias() %></strong></label>
                    <input class="form-control" type="text" name="alias" id="alias" value="<%= jugador.getAlias() %>" placeholder="Alias">
                </div>
            
                <div class="col-md-6">
                    <label for="fecha_nacimiento" class="form-label">Fecha de nacimiento: <strong><%= jugador.getFechaNacimiento() %></strong></label>
                    <input class="form-control" type="date" name="fecha_nacimiento" id="fecha_nacimiento" value="<%= jugador.getFechaNacimiento() %>">
                </div>
            
                <div class="col-md-6">
                    <label for="nacionalidad" class="form-label">Nacionalidad: <strong><%= jugador.getNacionalidad() %></strong></label>
                    <input class="form-control" type="text" name="nacionalidad" id="nacionalidad" value="<%= jugador.getNacionalidad() %>" placeholder="Nacionalidad">
                </div>
            
                <div class="col-md-6">
                    <label for="altura" class="form-label">Altura: <strong><%= jugador.getAltura() %> m</strong></label>
                    <input class="form-control" type="number" step="0.01" name="altura" id="altura" value="<%= jugador.getAltura() %>" placeholder="Altura (m)">
                </div>
            
                <div class="col-md-6">
                    <label for="peso" class="form-label">Peso: <strong><%= jugador.getPeso() %> kg</strong></label>
                    <input class="form-control" type="number" step="0.01" name="peso" id="peso" value="<%= jugador.getPeso() %>" placeholder="Peso (kg)">
                </div>
            
                <div class="col-md-6">
                    <label for="pie_dominante" class="form-label">Pie dominante: <strong><%= jugador.getPieDominante() %></strong></label>
                    <select class="form-select" name="pie_dominante" id="pie_dominante">
                        <option value="derecho" <%= "derecho".equals(jugador.getPieDominante()) ? "selected" : "" %>>Derecho</option>
                        <option value="izquierdo" <%= "izquierdo".equals(jugador.getPieDominante()) ? "selected" : "" %>>Izquierdo</option>
                        <option value="ambidiestro" <%= "ambidiestro".equals(jugador.getPieDominante()) ? "selected" : "" %>>Ambidiestro</option>
                    </select>
                </div>
            
                <div class="col-md-6">
                    <label for="valor_mercado" class="form-label">Valor de mercado actual: <strong><%= jugador.getValorMercado() %> €</strong></label>
                    <input class="form-control" type="number" step="0.01" name="valor_mercado" id="valor_mercado" value="<%= jugador.getValorMercado() %>" placeholder="Valor mercado">
                </div>
            
                <div class="col-md-6">
                    <label for="posicion" class="form-label">Posición: <strong><%= jugador.getPosicion() %></strong></label>
                    <select class="form-select" name="posicion" id="posicion">
                        <option value="delantero" <%= "delantero".equals(jugador.getPosicion()) ? "selected" : "" %>>Delantero</option>
                        <option value="mediocampista" <%= "mediocampista".equals(jugador.getPosicion()) ? "selected" : "" %>>Mediocampista</option>
                        <option value="defensa" <%= "defensa".equals(jugador.getPosicion()) ? "selected" : "" %>>Defensa</option>
                        <option value="portero" <%= "portero".equals(jugador.getPosicion()) ? "selected" : "" %>>Portero</option>
                    </select>
                </div>
            
                <div class="col-md-6">
                    <label for="representante" class="form-label">Representante: <strong><%= jugador.getRepresentanteNombre() %></strong></label>
                    <input class="form-control" type="text" name="representante" id="representante" value="<%= jugador.getRepresentanteNombre() %>" placeholder="Representante">
                </div>
            
                <div class="col-md-6">
                    <label for="seleccion" class="form-label">Selección: <strong><%= jugador.getSeleccionNombre() %></strong></label>
                    <input class="form-control" type="text" name="seleccion" id="seleccion" value="<%= jugador.getSeleccionNombre() %>" placeholder="Selección">
                </div>
            
                <div class="col-md-12 d-flex justify-content-between">
                    <a href="jugador.jsp" class="btn btn-secondary">Cancelar</a>
                    <button type="submit" name="accion" value="actualizar" class="btn btn-success">Guardar cambios</button>
                </div>
            </form>
            
        </section>
    </div>

    <%@ include file="footer.jsp" %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
