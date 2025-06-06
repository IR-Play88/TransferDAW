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
