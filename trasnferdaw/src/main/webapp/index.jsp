<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../importar/conf.jsp" %>
<!DOCTYPE html>
<html lang="es">
    <%@ include file="../importar/encabezado.jsp" %>
            <section class="col-md-9" id="content">
                <%@ include file="../importar/mensaje.jsp" %>

                <h2>Todas nuestras opciones</h2>

                <article class="mb-4">
                    <h3>Jugadores</h3>
                    <% if (nombreUsuario == null) { %>
                        <p>Como eres lector ¡Puedes ver los jugadores disponibles y buscar tu jugador favorito!</p>
                    <% } else { %>
                        <p><%= nombreUsuario %> eres administrador ¡Tú tienes el control, inserta, modifica, elimina, busca un jugador en concreto o visualizados a todos!</p>
                    <% } %> 
                    <a href="JugadorController">
                        <img src="images/mas.png" alt="Ver más">
                    </a>
                      
                </article>

                <article class="mb-4">
                    <h3>Equipos</h3>
                    <% if (nombreUsuario == null) { %>
                        <p>Como eres lector ¡Puedes ver los equipos disponibles y buscar tu equipo favorito!</p>
                    <% } else { %>
                        <p><%= nombreUsuario %> eres administrador ¡Tú tienes el control, inserta, modifica, elimina, busca un equipo en concreto o visualizados a todos!</p>
                    <% } %> 
                    <a href="EquipoController">
                        <img src="images/mas.png" alt="Ver más">
                    </a>
                </article>

                <article class="mb-4">
                    <h3>Competiciones</h3>
                    <% if (nombreUsuario == null) { %>
                        <p>Como eres lector ¡Puedes ver las competiciones disponibles!</p>
                    <% } else { %>
                        <p><%= nombreUsuario %> eres administrador ¡Tú tienes el control, inserta, modifica, elimina, busca una competición en concreto o visualizalas todas!</p>
                    <% } %> 
                    <a href="CompeticionController">
                        <img src="images/mas.png" alt="Ver más">
                    </a>
                </article>

                <article class="mb-4">
                    <h3>Equipo-Competición</h3>
                    <% if (nombreUsuario == null) { %>
                        <p>Como eres lector ¡Puedes ver las  relaciones entre equipos y competiciones disponibles!</p>
                    <% } else { %>
                        <p><%= nombreUsuario %> eres administrador ¡Tú tienes el control, inserta, modifica, elimina, busca en que competición esta tu equipo o visualizalo todos!</p>
                    <% } %> 
                    <a href="EquipoCompeticionController">
                        <img src="images/mas.png" alt="Ver más">
                    </a>
                </article>

                <article class="mb-4">
                    <h3>Valor de mercado de los jugadores</h3>
                    <% if (nombreUsuario == null) { %>
                        <p>Como eres lector ¡Puedes ver las competiciones disponibles!</p>
                    <% } else { %>
                        <p><%= nombreUsuario %> eres administrador ¡Tú tienes el control, inserta, modifica, elimina, busca un valor de mercado de un jugador en concreto 
                            o visualizalos todos!</p>
                    <% } %> 
                    <a href="ValorMercadoController">
                        <img src="images/mas.png" alt="Ver más">
                    </a>
                </article>

                <article class="mb-4">
                    <h3>Estadísticas de los Jugadores</h3>
                    <% if (nombreUsuario == null) { %>
                        <p>Como eres lector ¡Puedes ver las estadisticas de los jugadores disponibles!</p>
                    <% } else { %>
                        <p><%= nombreUsuario %> eres administrador ¡Tú tienes el control, inserta, modifica, elimina, busca una estadistica en concreto o visualizalas todas!</p>
                     <% } %> 
                     <a href="EstadisticasTemporadaController">
                        <img src="images/mas.png" alt="Ver más">
                    </a>
                </article>

                <article class="mb-4">
                    <h3>Traspaso de los Jugadores</h3>
                    <% if (nombreUsuario == null) { %>
                        <p>Como eres lector ¡Puedes ver los traspasos de los jugadores disponibles!</p>
                    <% } else { %>
                        <p><%= nombreUsuario %> eres administrador ¡Tú tienes el control, inserta, modifica, elimina, busca un traspasoconcreto o visualizalas todas!</p>
                     <% } %> 
                     <a href="TraspasoController">
                        <img src="images/mas.png" alt="Ver más">
                    </a>
                </article>

                <article class="mb-4">
                    <h3>Temporadas</h3>
                    <p>¡Puedes ver las temporadas disponibles!</p>
                    <a href="TemporadaController">
                        <img src="images/mas.png" alt="Ver más">
                    </a>
                </article>
            </section>
        </div>
        <%@ include file="../importar/footer.jsp" %>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
