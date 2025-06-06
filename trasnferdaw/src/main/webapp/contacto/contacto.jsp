<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Jugador" %>

<%@ include file="../importar/conf.jsp" %>
<!DOCTYPE html>
<html lang="es">
    <%@ include file="../importar/encabezado.jsp" %>

    <section class="col-md-9">
        <h2>Contacto</h2>
        <p>Ha tenido algún inconveniente, problema, error o lo que sea. No se preocupe, introduzca su usuario, su correo con su queja y nosotros nos pondremos de inmediato con ello.</p>

        <%@ include file="../importar/mensaje.jsp" %>

        <form action="ContactoController" method="post" class="row g-3">
            <div class="col-md-6">
                <input class="form-control" type="text" name="usuario" placeholder="Usuario" required>
            </div>
            <div class="col-md-6">
                <input class="form-control" type="email" name="email" placeholder="Email" required>
            </div>
            <div class="col-md-6">
                <input class="form-control" type="password" name="pass" placeholder="Pass" required>
            </div>
            <div class="col-md-6">
                <input class="form-control" type="text" name="queja" placeholder="Problema" required>
            </div>
            <div class="col-md-6">
                <button type="submit" name="accion" value="enviar" class="btn btn-primary">
                    <img src="images/enviar.png" alt="enviar">
                </button>
            </div>
        </form>
    </section>

</div>

<%@ include file="../importar/footer.jsp" %>
</div>
</body>
</html>
