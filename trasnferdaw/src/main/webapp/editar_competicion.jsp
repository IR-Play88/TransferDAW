<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.tierno.daw.trasnferdaw.model.entities.Competicion" %>
<%
    Competicion competicion = (Competicion) request.getAttribute("competicion");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Editar Competición</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Editar Competición</h2>

    <form method="GET" action="CompeticionController" class="row g-3">
        <input type="hidden" name="id_competicion" value="<%= competicion.getIdCompeticion() %>">

        <div class="col-md-6">
            <label for="nombre" class="form-label">Nombre actual: <strong><%= competicion.getNombre() %></strong></label>
            <input type="text" name="nombre" id="nombre" class="form-control" value="<%= competicion.getNombre() %>" required>
        </div>

        <div class="col-md-6">
            <label for="pais" class="form-label">País actual: <strong><%= competicion.getPais() %></strong></label>
            <input type="text" name="pais" id="pais" class="form-control" value="<%= competicion.getPais() %>" required>
        </div>

        <div class="col-md-6">
            <label for="tipo" class="form-label">Tipo actual: <strong><%= competicion.getTipo() %></strong></label>
            <select name="tipo" id="tipo" class="form-select" required>
                <option value="Liga" <%= "Liga".equals(competicion.getTipo()) ? "selected" : "" %>>Liga</option>
                <option value="Copa" <%= "Copa".equals(competicion.getTipo()) ? "selected" : "" %>>Copa</option>
                <option value="Internacional" <%= "Internacional".equals(competicion.getTipo()) ? "selected" : "" %>>Internacional</option>
                <option value="Seleccion" <%= "Seleccion".equals(competicion.getTipo()) ? "selected" : "" %>>Selección</option>
                <option value="Amistoso" <%= "Amistoso".equals(competicion.getTipo()) ? "selected" : "" %>>Amistoso</option>
            </select>
        </div>

        <div class="col-md-6">
            <label for="numeroEquipos" class="form-label">Número de equipos actual: <strong><%= competicion.getNumeroEquipos() %></strong></label>
            <input type="number" name="numeroEquipos" id="numeroEquipos" class="form-control" value="<%= competicion.getNumeroEquipos() %>" required>
        </div>

        <div class="col-md-6">
            <label for="anioCreacion" class="form-label">Año de creación actual: <strong><%= competicion.getAnioCreacion() %></strong></label>
            <input type="number" name="anioCreacion" id="anioCreacion" class="form-control" value="<%= competicion.getAnioCreacion() %>" required>
        </div>

        <div class="col-md-12 d-flex justify-content-between">
            <a href="competicion.jsp" class="btn btn-secondary">Cancelar</a>
            <button type="submit" name="accion" value="actualizar" class="btn btn-success">Guardar cambios</button>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
