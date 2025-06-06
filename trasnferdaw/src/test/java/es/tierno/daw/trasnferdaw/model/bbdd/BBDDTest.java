package es.tierno.daw.trasnferdaw.model.bbdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.time.LocalDate;

import java.util.List;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.tierno.daw.trasnferdaw.model.entities.Competicion;
import es.tierno.daw.trasnferdaw.model.entities.Equipo;
import es.tierno.daw.trasnferdaw.model.entities.EquipoCompeticion;
import es.tierno.daw.trasnferdaw.model.entities.EstadisticasTemporada;
import es.tierno.daw.trasnferdaw.model.entities.Jugador;
import es.tierno.daw.trasnferdaw.model.entities.Temporada;
import es.tierno.daw.trasnferdaw.model.entities.Traspaso;
import es.tierno.daw.trasnferdaw.model.entities.Usuario;
import es.tierno.daw.trasnferdaw.model.entities.ValorMercadoHistorial;
import es.tierno.daw.trasnferdaw.model.exception.BBDDException;

/**
 * Clase de pruebas unitarias para comprobar el correcto funcionamiento de la
 * clase TransferDAOImpMariaDB.
 * \author: Iván Rafael Redondo
 */
public class BBDDTest {

    /**
     * Instancia estática de la clase DAO que se usará para las pruebas.
     * \author: Iván Rafael Redondo
     */
    private static TransferDAOImpMariaDB dao;

    /**
     * Método que se ejecuta una vez antes de todos los tests.
     * \author: Iván Rafael Redondo
     * 
     * @throws Exception 
     */
    @BeforeAll
    public static void setUp() throws Exception {
        dao = new TransferDAOImpMariaDB();
    }

    /**
     * Prueba que verifica que la conexión a la base de datos no sea nula
     * y\author: Iván Rafael Redondo
     * 
     * @throws SQLException 
     */
    @Test
    public void testConnection() throws SQLException {
        var connection = dao.conn;

        try {
            if (connection == null) {
                fail("La conexión no debería ser nula");
            }

            if (!connection.isValid(2)) {
                fail("La conexión debería ser válida");
            }

        } catch (SQLException e) {
            fail("Error al comprobar la validez de la conexión: " + e.getMessage());
        }
    }

    @Test
    public void insertarJugadorTest() throws BBDDException {
        Jugador jugador = new Jugador(0, "Isco", "Alarcon", LocalDate.of(1986, 3, 30), "España", 1.84, 82, "derecho",
                10, "mediocampista", "Prueba", "España");

        int real = dao.insertar(jugador);
        assertEquals(1, real);
    }

    @Test
    public void actualizarJugadorTest() throws BBDDException {
        List<Jugador> jugadores = dao.listarJugadores();
        for (Jugador j : jugadores) {
            if (j.getNombre().equals("Isco")) {
                j.setValorMercado(7000000); 
                int actualizados = dao.modificar(j);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarJugadorTest() throws BBDDException {
        List<Jugador> jugadores = dao.listarJugadores();
        int idEliminar = -1;
        for (Jugador j : jugadores) {
            if (j.getNombre().equals("Isco")) {
                idEliminar = j.getIdJugador();
                break;
            }
        }

        int real = dao.eliminarJugador(idEliminar);
        assertEquals(1, real);

    }

    @Test
    public void listarJugadorTest() throws BBDDException {
        List<Jugador> jugadores = dao.listarJugadores();
        assertEquals(2, jugadores.size());
    }

    /**
     * Prueba para buscar un jugador por ID
     * \author: Iván Rafael Redondo
     * 
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
    @Test
    public void testViusalizarJugador() throws BBDDException {
        int idJugador = 1;

        Jugador jugador = dao.visualizarJugador(idJugador);

        assertNotNull(jugador, "El jugador no debería ser nulo");

        assertEquals(idJugador, jugador.getIdJugador(), "El ID del jugador debería ser 1");
        assertEquals("Marco Asensio Willemsen", jugador.getNombre(),
                "El nombre del jugador debería ser 'Marco Asensio Willemsen'");
        assertEquals("La perla mallorquina", jugador.getAlias(),
                "El alias del jugador debería ser 'La perla mallorquina'");
        assertEquals(LocalDate.of(1996, 1, 21), jugador.getFechaNacimiento(),
                "La fecha de nacimiento del jugador debería ser '1996-01-21'");
        assertEquals("España", jugador.getNacionalidad(), "La nacionalidad del jugador debería ser 'Española'");
        assertEquals(1.82, jugador.getAltura(), 0.01f, "La altura del jugador debería ser 1.82f");
        assertEquals(76.0, jugador.getPeso(), 0.01f, "El peso del jugador debería ser 76.0f");
        assertEquals("izquierdo", jugador.getPieDominante(), "El pie dominante del jugador debería ser 'izquierdo'");
        assertEquals(20, jugador.getValorMercado(), 0.01f, "El valor de mercado del jugador debería ser 200000000f");
        assertEquals("mediocampista", jugador.getPosicion(), "La posición del jugador debería ser mediocampista");
        assertEquals("Gestifute", jugador.getRepresentanteNombre(),
                "El ID del representante del jugador debería ser 1");
        assertEquals("España", jugador.getSeleccionNombre(), "El nombre de la selección del jugador debería ser España");
    }

    @Test
    public void listarTemporadaTest() throws BBDDException {
        List<Temporada> temporadas = dao.listarTemporadas();
        assertEquals(56, temporadas.size());
    }

    @Test
    public void insertarEquipoTest() throws BBDDException {
        Equipo equipo = new Equipo(0, "equipo_prueba", "ciudad_prueba", "pais_prueba", 1999, 100, "propietario prueba",
                "estadio prueba", "entrenador prueba");

        int real = dao.insertar(equipo);
        assertEquals(1, real);
    }

    @Test
    public void actualizarEquipoTTest() throws BBDDException {
        List<Equipo> equipos = dao.listarEquipos();
        for (Equipo e : equipos) {
            if (e.getNombre().equals("equipo_prueba")) {
                e.setPresupuesto(500);
                int actualizados = dao.modificar(e);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarEquipoTTest() throws BBDDException {
        List<Equipo> equipos = dao.listarEquipos();
        int idEliminar = -1;
        for (Equipo e : equipos) {
            if (e.getNombre().equals("equipo_prueba")) {
                idEliminar = e.getIdEquipo();
                break;
            }
        }

        int real = dao.eliminarEquipo(idEliminar);
        assertEquals(1, real);

    }

    @Test
    public void listarEquipoTTest() throws BBDDException {
        List<Equipo> equipos = dao.listarEquipos();
        assertEquals(8, equipos.size());
    }

    @Test
    public void testVisualizarEquipo() throws BBDDException {
        int idEquipo = 1;

        Equipo equipo = dao.visualizarEquipo(idEquipo);

        assertNotNull(equipo, "El equipo no debería ser nulo");
        assertEquals(idEquipo, equipo.getIdEquipo(), "El ID del equipo debería ser 1");
        assertEquals("Real Madrid CF", equipo.getNombre(), "El nombre del equipo debería ser 'Real Madrid'");
        assertEquals("España", equipo.getPais(), "El país del equipo debería ser 'España'");
        assertEquals("Santiago Bernabeu", equipo.getEstadioNombre(),
                "El estadio del equipo debería ser 'Santiago Bernabeu'");
        assertEquals(1902, equipo.getAnioFundacion(), "El año de fundación debería ser 1902");
    }

    @Test
    public void insertarCompeticionTest() throws BBDDException {
        Competicion competicion = new Competicion(0, "competicion_prueba", "pais_prueba", "liga", 20, 1900);

        int real = dao.insertar(competicion);
        assertEquals(1, real);
    }

    @Test
    public void actualizarCompeticionTest() throws BBDDException {
        List<Competicion> competiciones = dao.listarCompeticiones();
        for (Competicion c : competiciones) {
            if (c.getNombre().equals("competicion_prueba")) {
                c.setAnioCreacion(2000);
                int actualizados = dao.modificar(c);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarCompeticionTest() throws BBDDException {
        List<Competicion> competiciones = dao.listarCompeticiones();
        int idEliminar = -1;
        for (Competicion c : competiciones) {
            if (c.getNombre().equals("competicion_prueba")) {
                idEliminar = c.getIdCompeticion();
                break;
            }
        }

        int real = dao.eliminarCompeticion(idEliminar);
        assertEquals(1, real);

    }

    @Test
    public void listarCompeticionTest() throws BBDDException {
        List<Competicion> competiciones = dao.listarCompeticiones();
        assertEquals(12, competiciones.size());
    }

    @Test
    public void testVisualizarCompeticion() throws BBDDException {
        int idCompeticion = 1;

        Competicion competicion = dao.visualizarCompeticion(idCompeticion);

        assertNotNull(competicion, "La competición no debería ser nula");
        assertEquals(idCompeticion, competicion.getIdCompeticion(), "El ID de la competición debería ser 1");
        assertEquals("La Liga", competicion.getNombre(), "El nombre de la competición debería ser 'LaLiga'");
        assertEquals("España", competicion.getPais(), "El país de la competición debería ser 'España'");
        assertEquals("liga", competicion.getTipo(), "El tipo de competición debería ser 'liga'");
        assertEquals(20, competicion.getNumeroEquipos(), "El numero de equipos debe ser 20");
        assertEquals(1929, competicion.getAnioCreacion(), "El año de creacion deberia ser 1929");
    }

    @Test
    public void insertarEquipoCompeticionTest() throws BBDDException {
        EquipoCompeticion equipoCompeticion = new EquipoCompeticion(8, 9, 9, "pruebasss");
        int real = dao.insertar(equipoCompeticion);
        assertEquals(1, real);
    }

    @Test
    public void actualizarEquipoCompeticionTest() throws BBDDException {
        List<EquipoCompeticion> equipoCompeticiones = dao.listarEquiposCompeticion();
        for (EquipoCompeticion eqs : equipoCompeticiones) {
            if (eqs.getEquipoId() == 8 && eqs.getCompeticionId() == 9 && eqs.getTemporadaId() == 9) {
                eqs.setPosicion("prueba");
                int actualizados = dao.modificar(eqs);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarEquipoCompeticionTest() throws BBDDException {
        List<EquipoCompeticion> equipoCompeticiones = dao.listarEquiposCompeticion();

        int equipoId = -1;
        int competicionId = -1;
        int temporadaId = -1;

        for (EquipoCompeticion eqs : equipoCompeticiones) {
            if (eqs.getPosicion().equals("prueba")) { 
                equipoId = eqs.getEquipoId();
                competicionId = eqs.getCompeticionId();
                temporadaId = eqs.getTemporadaId();
                break;
            }
        }

        assertNotEquals(-1, equipoId, "No se encontró un registro con posicion prueba");

        int real = dao.eliminarEquipoCompeticion(equipoId, competicionId, temporadaId);
        assertEquals(1, real);
    }

    @Test
    public void listarEquipoCompeticionTest() throws BBDDException {
        List<EquipoCompeticion> equipoCompeticiones = dao.listarEquiposCompeticion();
        assertEquals(47, equipoCompeticiones.size());
    }

    @Test
    public void testVisualizarEquipoCompeticionPorIds() throws BBDDException {
        int equipoId = 1;
        int competicionId = 1;
        int temporadaId = 48;

        EquipoCompeticion equipoCompeticion = dao.visualizarEquipoCompeticion(equipoId, competicionId, temporadaId);

        assertNotNull(equipoCompeticion, "El objeto EquipoCompeticion no debería ser nulo");

        assertEquals(equipoId, equipoCompeticion.getEquipoId(), "El ID del equipo debería ser 1");
        assertEquals(competicionId, equipoCompeticion.getCompeticionId(), "El ID de la competición debería ser 1");
        assertEquals(temporadaId, equipoCompeticion.getTemporadaId(), "El ID de la temporada debería ser 48");
        assertEquals("primero", equipoCompeticion.getPosicion(), "El rango debería ser primero"); 
    }

    @Test
    public void insertarEstadisticasTemporadaTest() throws BBDDException {
        EstadisticasTemporada estadisticas = new EstadisticasTemporada(2, 56, 7, 7, 0, 0, 0);
        int real = dao.insertar(estadisticas);
        assertEquals(1, real);
    }

    @Test
    public void modificarEstadisticasTemporadaTest() throws BBDDException {
        List<EstadisticasTemporada> estadisticas = dao.listarEstadisticasTemporada();

        for (EstadisticasTemporada est : estadisticas) {
            if (est.getJugadorId() == 2 && est.getTemporadaId() == 56 && est.getCompeticionId() == 7
                    && est.getEquipoId() == 7) {
                est.setGoles(99);
                int actualizados = dao.modificar(est);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarEstadisticasTemporadaTest() throws BBDDException {
        List<EstadisticasTemporada> lista = dao.listarEstadisticasTemporada();

        int jugadorId = -1, temporadaId = -1, competicionId = -1, equipo_id = -1;

        for (EstadisticasTemporada est : lista) {
            if (est.getGoles() == 99) {
                jugadorId = est.getJugadorId();
                temporadaId = est.getTemporadaId();
                competicionId = est.getCompeticionId();
                equipo_id = est.getEquipoId();
                break;
            }
        }

        assertNotEquals(-1, jugadorId, "No se encontró una estadística válida para eliminar");

        int real = dao.eliminarEstadisticasTemporada(jugadorId, temporadaId, competicionId, equipo_id);
        assertEquals(1, real);
    }

    @Test
    public void listarEstadisticasTemporadaTest() throws BBDDException {
        List<EstadisticasTemporada> lista = dao.listarEstadisticasTemporada();
        assertEquals(43, lista.size()); 
    }

    @Test
    public void testBuscarEstadisticasTotalesPorJugador() throws BBDDException {
        int jugadorId = 1;

        EstadisticasTemporada totales = dao.buscarEstadisticasTotalesPorJugador(jugadorId);

        assertNotNull(totales, "Las estadísticas totales no deben ser nulas");

        assertEquals(447, totales.getPartidosJugados(), "Total de partidos jugados incorrecto");
        assertEquals(87, totales.getGoles(), "Total de goles incorrecto");
        assertEquals(68, totales.getAsistencias(), "Total de asistencias incorrecto");

    }

    @Test
    public void testBuscarEstadisticasPorTemporada() throws BBDDException {
        int jugadorId = 1;
        int temporadaId = 53;

        EstadisticasTemporada temporada = dao.buscarEstadisticasPorTemporada(jugadorId, temporadaId);

        assertNotNull(temporada, "Las estadísticas de la temporada no deben ser nulas");

        assertEquals(jugadorId, temporada.getJugadorId(), "ID del jugador incorrecto");
        assertEquals(temporadaId, temporada.getTemporadaId(), "ID de temporada incorrecto");
        assertEquals(42, temporada.getPartidosJugados(), "Total de partidos jugados en temporada 56 incorrecto");
        assertEquals(12, temporada.getGoles(), "Total de goles en temporada 56 incorrecto");
        assertEquals(2, temporada.getAsistencias(), "Total de asistencias en temporada 56 incorrecto");

    }

    @Test
    public void anadirTraspaso() throws BBDDException {
        Traspaso traspaso = new Traspaso(0, 2, 8, 8, 56, LocalDate.of(1986, 3, 30), 0, "cesion");
        int real = dao.insertar(traspaso);
        assertEquals(1, real);
    }

    @Test
    public void modificarTraspasoTest() throws BBDDException {
        List<Traspaso> traspasos = dao.listarTraspasos();
        for (Traspaso t : traspasos) {
            if (t.getJugadorId() == 2 && t.getTemporadaId() == 56 && t.getEquipoOrigenId() == 8) {
                t.setCantidad(2000000.0); 
                int actualizados = dao.modificar(t);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarTraspasoTest() throws BBDDException {
        List<Traspaso> traspasos = dao.listarTraspasos();
        int idTraspaso = -1;

        for (Traspaso t : traspasos) {
            if (t.getCantidad() == 2000000.0) {
                idTraspaso = t.getIdTraspaso();
                break;
            }
        }

        assertNotEquals(-1, idTraspaso, "No se encontró un traspaso con la cantidad esperada");

        int real = dao.eliminarTraspaso(idTraspaso);
        assertEquals(1, real);
    }

    @Test
    public void listarTraspasosTest() throws BBDDException {
        List<Traspaso> traspasos = dao.listarTraspasos();
        assertEquals(4, traspasos.size());
    }

    @Test
    public void testVisualizarTraspaso() throws BBDDException {
        int idTraspaso = 1; 

        Traspaso traspaso = dao.visualizarTraspaso(idTraspaso);

        assertNotNull(traspaso, "El traspaso no debería ser nulo");

        assertEquals(idTraspaso, traspaso.getIdTraspaso(), "El ID del traspaso debería ser 1");
        assertEquals(1, traspaso.getJugadorId(), "El ID del jugador debería ser 1");
        assertEquals(4, traspaso.getEquipoOrigenId(), "El ID del equipo origen debería ser 4");
        assertEquals(1, traspaso.getEquipoDestinoId(), "El ID del equipo destino debería ser 1");
        assertEquals(46, traspaso.getTemporadaId(), "El ID de la temporada debería ser 46");
        assertEquals(LocalDate.of(2014, 7, 1), traspaso.getFechaTraspaso(),
                "La fecha del traspaso debería ser '2014-07-01'");
        assertEquals(3.7f, traspaso.getCantidad(), 0.01f, "La cantidad del traspaso debería ser 3.7f");
        assertEquals("compra", traspaso.getTipo(), "El tipo de traspaso debería ser 'compra'");
    }

    @Test
    public void insertarValorMercadoHistoriaTest() throws BBDDException {
        ValorMercadoHistorial historia = new ValorMercadoHistorial(0, 1, LocalDate.of(2025, 7, 1), 999_000_000,
                "Sube por buena temporada");
        int real = dao.insertar(historia);
        assertEquals(1, real);
    }

    @Test
    public void modificarValorMercadoHistoriaTest() throws BBDDException {
        List<ValorMercadoHistorial> historiales = dao.listarValorMercadoHistorial();
        for (ValorMercadoHistorial h : historiales) {
            if (h.getValorMercado() == 99_000_000 && h.getIdHistorial() == 12) {
                h.setValorMercado(111_000_000);
                int actualizados = dao.modificar(h);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarValorMercadoHistoriaTest() throws BBDDException {
        List<ValorMercadoHistorial> historiales = dao.listarValorMercadoHistorial();
        int id = -1;
        for (ValorMercadoHistorial h : historiales) {
            if (h.getValorMercado() == 99_000_000) {
                id = h.getIdHistorial();
                break;
            }
        }

        assertNotEquals(-1, id, "No se encontró historial con valor esperado");
        int real = dao.eliminarValorMercadoHistorial(id);
        assertEquals(1, real);
    }

    @Test
    public void listarValorMercadoHistoriaTest() throws BBDDException {
        List<ValorMercadoHistorial> historiales = dao.listarValorMercadoHistorial();
        assertEquals(11, historiales.size());
    }

    @Test
    public void testVisualizarValorMercadoHistorial() throws BBDDException {
        int idHistorial = 1;

        ValorMercadoHistorial historial = dao.visualizarValorMercadoHistorial(idHistorial);

        assertNotNull(historial, "El historial no debería ser nulo");
        assertEquals(idHistorial, historial.getIdHistorial(), "ID incorrecto");
        assertEquals(1, historial.getJugadorId(), "ID del jugador incorrecto");
        assertEquals(LocalDate.of(2014, 7, 1), historial.getFecha(), "Fecha incorrecta");
        assertEquals(1000000, historial.getValorMercado(), "Valor de mercado incorrecto");
        assertEquals("Marco Asensio inicia en 2º división con el mallorca", historial.getMotivo(), "Motivo incorrecto");
    }

    @Test
    public void testInsertarUsuario() throws BBDDException {
        Usuario usuario = new Usuario("prueba", "ninguno@gmail.com", "123456789", "admin");
        int real = dao.insertar(usuario);
        assertEquals(1, real);
    }

    @Test
    public void testEliminarUsuario() throws BBDDException {
        Usuario usuario = new Usuario();
        usuario.setNombre("prueba");
        int real = dao.eliminar(usuario);
        assertEquals(1, real);
    }

    @Test
    public void testObtenerIdPorNombreJugador() throws BBDDException {
        String nombre = "Marco Asensio Willemsen";

        int id = dao.obtenerIdPorNombreJugador(nombre);
        assertEquals(1, id);

    }

    @Test
    public void testtestObtenerIdPorNombreEquipo() throws BBDDException {
        String nombre = "Real Madrid CF";

        int id = dao.obtenerIdPorNombreEquipo(nombre);
        assertEquals(1, id);
    }

    @Test
    public void testtestObtenerIdPorNombreCompeticion() throws BBDDException {
        String nombre = "Champions League";

        int id = dao.obtenerIdPorNombreCompeticion(nombre);
        assertEquals(4, id);
    }

    @Test
    public void testtestObtenerIdPorNombreTemporada() throws BBDDException {
        String nombre = "Temporada 2021/2022";

        int id = dao.obtenerIdPorNombreTemporada(nombre);
        assertEquals(53, id);
    }

    @Test
    public void testObtenerEstadisticasPorId() throws BBDDException {
        int jugadorId = 1;
        int equipoId = 1;
        int competicionId = 4;
        int temporadaId = 53;

        EstadisticasTemporada est = dao.obtenerEstadisticaPorId(jugadorId, temporadaId, competicionId, equipoId);

        assertEquals(jugadorId, est.getJugadorId());
        assertEquals(temporadaId, est.getTemporadaId());
        assertEquals(competicionId, est.getCompeticionId());
        assertEquals(equipoId, est.getEquipoId());

        assertEquals(8, est.getPartidosJugados());
        assertEquals(1, est.getGoles());
        assertEquals(2, est.getAsistencias());

        assertEquals("Marco Asensio Willemsen", est.getNombreJugador());
        assertEquals("Temporada 2021/2022", est.getNombreTemporada());
        assertEquals("Champions League", est.getNombreCompeticion());
        assertEquals("Real Madrid CF", est.getNombreEquipo());
    }

    @Test
    public void testBuscarUusarioPorNombre() throws BBDDException {
        String nombre = "irr";

        Usuario usuario = dao.buscarUsuarioPorNombre(nombre);
        assertEquals(nombre, usuario.getNombre());
    }

    @Test
    public void testBuscarUsuarioPorEmail() throws BBDDException {
        String email = "irr@gmail.com";

        Usuario usuario = dao.buscarUsuarioPorEmail(email);
        assertEquals(email, usuario.getEmail());
    }

    

}
