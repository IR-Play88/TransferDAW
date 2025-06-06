<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../importar/conf.jsp" %>
<!DOCTYPE html>
<html lang="es">

<%@ include file="../importar/encabezado.jsp" %>

<section class="col-md-9" id="content">
    <%@ include file="../importar/mensaje.jsp" %>
    <h2>Crea tu cuenta</h2>

    <form method="POST" action="CuentaController">
        <input type="text" class="form-control mb-2" name="usuario" placeholder="usuario" required />
        <input type="email" class="form-control mb-2" name="email" placeholder="email" required />
        <input type="password" class="form-control mb-2" name="pass" placeholder="password" required />
        <button type="submit" name="accion" value="crear" class="btn btn-success">
            <img src="images/insertar.png" alt="crear">
        </button>
    </form>
</section>

<%@ include file="../importar/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
