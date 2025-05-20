<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String rol = (String) session.getAttribute("rol");
    boolean esAdmin = rol != null && rol.equals("admin");
%>
<%
    String nombreUsuario = (String) session.getAttribute("usuario");
%>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0); // Proxies
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
                        <li><a class="dropdown-item" href="#">Portugues</a></li>
                        <li><a class="dropdown-item" href="#">Frances</a></li>
                        <li><a class="dropdown-item" href="#">Italiano</a></li>
                        <li><a class="dropdown-item" href="#">Argentino</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="creearCuenta.jsp">Crear cuenta</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="contacto.jsp">Contacto</a>
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