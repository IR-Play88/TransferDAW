<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="cabezera.jsp" %>

<section class="col-md-9" id="content">
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
    <h2>Crea tu cuenta</h2>

    <form method="POST" action="CuentaServlet">
        <input type="text" class="form-control mb-2" name="usuario" placeholder="usuario" required />
        <input type="email" class="form-control mb-2" name="email" placeholder="email" required />
        <input type="password" class="form-control mb-2" name="pass" placeholder="password" required />
        <input type="submit" class="btn btn-primary w-100" value="enviar" />
    </form>
</section>

<%@ include file="footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
