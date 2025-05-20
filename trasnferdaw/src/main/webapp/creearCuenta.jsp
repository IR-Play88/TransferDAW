<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="cabezera.jsp" %>

<section class="col-md-9" id="content">
    <h2>Crea tu cuenta</h2>

    <form method="POST" action="CuentaServlet">
        <input type="text" class="form-control mb-2" name="usuario" placeholder="usuario" required />
        <input type="email" class="form-control mb-2" name="email" placeholder="email" required />
        <input type="password" class="form-control mb-2" name="pass" placeholder="password" required />
        <input type="submit" class="btn btn-primary w-100" value="enviar" />
    </form>

    <% 
        String accion = request.getParameter("accion");
        if ("enviar".equals(accion)) {
    %>
        <div class="alert alert-success mt-3" role="alert">
            ¡Cuenta creada correctamente!
        </div>
    <% 
        }
    %>
</section>

<%@ include file="footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
