<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../importar/conf.jsp" %>
<!DOCTYPE html>
<html lang="es">

<%@ include file="../importar/encabezado.jsp" %>

<section class="col-md-9" id="content">
    <%@ include file="../importar/mensaje.jsp" %>
    <h2>Elimina tu cuenta</h2>

    <form action="EliminarController" method="POST">
        <input type="text" class="form-control mb-2" name="usuario" placeholder="usuario" required />
        <input type="email" class="form-control mb-2" name="email" placeholder="email" required />
        <input type="password" class="form-control mb-2" name="pass" placeholder="password" required />
        <button type="submit" name="accion" value="eliminar" class="btn btn-danger btn-sm">
            <img src="images/eliminar.png" alt="eliminar">
        </button>
    </form>
</section>

<%@ include file="../importar/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
