<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Jugador" %>

<%@ include file="cabezera.jsp" %>

<section class="col-md-9">
    <h2>Contacto</h2>
    <p>Ha tenido algún inconveniente, problema, error o lo que sea. No se preocupe, introduzca su usuario, su correo con su queja y nosotros nos pondremos de inmediato con ello.</p>

    <%-- Mostrar mensaje si se ha enviado el formulario --%>
    <%
        String accion = request.getParameter("accion");
        if ("enviar".equals(accion)) {
    %>
        <div class="alert alert-success" role="alert">
            ¡Mensaje enviado correctamente!
        </div>
    <%
        }
    %>

    <form method="GET" action="contacto.jsp" class="row g-3">
        <div class="col-md-6">
            <input class="form-control" type="text" name="nombre" placeholder="Nombre" required>
        </div>
        <div class="col-md-6">
            <input class="form-control" type="email" name="email" placeholder="Email" required>
        </div>
        <div class="col-md-6">
            <input class="form-control" type="text" name="queja" placeholder="Problema" required>
        </div>
        <button type="submit" name="accion" value="enviar" class="btn btn-success">Enviar</button>
    </form>
</section>

</div>

<%@ include file="footer.jsp" %>
</div>
</body>
</html>
