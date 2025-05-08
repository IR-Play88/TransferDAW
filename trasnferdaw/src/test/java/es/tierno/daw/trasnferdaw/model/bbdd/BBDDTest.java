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
import es.tierno.daw.trasnferdaw.model.entities.Contrato;
import es.tierno.daw.trasnferdaw.model.entities.Equipo;
import es.tierno.daw.trasnferdaw.model.entities.EquipoCompeticion;
import es.tierno.daw.trasnferdaw.model.entities.EstadisticasTemporada;
import es.tierno.daw.trasnferdaw.model.entities.Jugador;
import es.tierno.daw.trasnferdaw.model.entities.Seleccion;
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
     * @throws Exception si ocurre un error al crear la instancia del DAO.
     */
    @BeforeAll
    public static void setUp() throws Exception {
        dao = new TransferDAOImpMariaDB();
    }

    /**
     * Prueba que verifica que la conexión a la base de datos no sea nula
     * y\author: Iván Rafael Redondo
     * 
     * @throws SQLException si ocurre un error al verificar la validez de la
     *                      conexión.
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
    public void insertarJugadorTest() throws Exception {
        Jugador jugador = new Jugador(0, "Isco", "Alarcon", LocalDate.of(1986, 3, 30), "España", 1.84f, 82f, "derecho", 10f, "mediocampista", "Prueba", 1);

        int real = dao.insertar(jugador);
        assertEquals(1, real);
    }

    @Test
    public void actualizarJugadorTest() throws Exception {
        // Recuperamos el jugador para actualizarlo
        List<Jugador> jugadores = dao.listarJugadores();
        for (Jugador j : jugadores) {
            if (j.getNombre().equals("Isco")) {
                j.setValorMercado(7000000f); // modificamos algo
                int actualizados = dao.modificar(j);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarJugadorTest() throws Exception {
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
    public void listarJugadorTest() throws Exception {
        List<Jugador> jugadores = dao.listarJugadores();
        assertEquals(4, jugadores.size());// o puedes comprobar exacto si conoces el número
    }

    /**
     * Prueba para buscar un jugador por ID
     * \author: Iván Rafael Redondo
     * 
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
    @Test
    public void testObtenerJugadorPorID() throws BBDDException {
        int idJugador = 1;

        Jugador jugador = dao.buscarPorId(idJugador);

        assertNotNull(jugador, "El jugador no debería ser nulo");

        assertEquals(idJugador, jugador.getIdJugador(), "El ID del jugador debería ser 1");
        assertEquals("Marco Asensio Willisem", jugador.getNombre(),
                "El nombre del jugador debería ser 'Marco Asensio Willisem'");
        assertEquals("La perla mallorquina", jugador.getAlias(),
                "El alias del jugador debería ser 'La perla mallorquina'");
        assertEquals(LocalDate.of(1996, 1, 21), jugador.getFechaNacimiento(),
                "La fecha de nacimiento del jugador debería ser '1996-01-21'");
        assertEquals("España", jugador.getNacionalidad(), "La nacionalidad del jugador debería ser 'Española'");
        assertEquals(1.82f, jugador.getAltura(), 0.01f, "La altura del jugador debería ser 1.82f");
        assertEquals(76.0f, jugador.getPeso(), 0.01f, "El peso del jugador debería ser 76.0f");
        assertEquals("izquierdo", jugador.getPieDominante(), "El pie dominante del jugador debería ser 'izquierdo'");
        assertEquals(20f, jugador.getValorMercado(), 0.01f, "El valor de mercado del jugador debería ser 200000000f");
        assertEquals("delantero", jugador.getPosicion(), "El ID del representante del jugador debería ser 1");
        assertEquals("Gestifute", jugador.getRepresentanteNombre(), "El ID del representante del jugador debería ser 1");
        assertEquals(1, jugador.getSeleccionNombre(), "El ID de la selección del jugador debería ser 1");
    }

    @Test
    public void listarTemporadaTest() throws Exception {
        List<Temporada> temporadas = dao.listarTemporadas();
        assertEquals(56, temporadas.size());// o puedes comprobar exacto si conoces el número
    }

    @Test
    public void insertarSelecionTest() throws Exception {
        Seleccion seleccion = new Seleccion(0, "pais_prueba", "españa_prueba", "federacion_prueba",1986, 11, null, null);

        int real = dao.insertar(seleccion);
        assertEquals(1, real);
    }

    @Test
    public void actualizarSelecionTest() throws Exception {
        // Recuperamos el jugador para actualizarlo
        List<Seleccion> selecciones = dao.listarSelecciones();
        for (Seleccion s : selecciones) {
            if (s.getNombre().equals("pais_prueba")) {
                s.setRanking(20);
                ; // modificamos algo
                int actualizados = dao.modificar(s);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarSelecionTest() throws Exception {
        List<Seleccion> selecciones = dao.listarSelecciones();
        int idEliminar = -1;
        for (Seleccion s : selecciones) {
            if (s.getNombre().equals("pais_prueba")) {
                idEliminar = s.getIdSeleccion();
                break;
            }
        }

        int real = dao.eliminarSeleccion(idEliminar);
        assertEquals(1, real);

    }

    @Test
    public void listarSelecionTest() throws Exception {
        List<Seleccion> selecciones = dao.listarSelecciones();
        assertEquals(2, selecciones.size());
    }

    @Test
    public void insertarEquipoTest() throws Exception {
        Equipo equipo = new Equipo(0, "equipo_prueba", "ciudad_prueba", "pais_prueba", 1999,100, "propietario prueba", "estadio prueba", "entrenador prueba");

        int real = dao.insertar(equipo);
        assertEquals(1, real);
    }

    @Test
    public void actualizarEquipoTTest() throws Exception {
        // Recuperamos el jugador para actualizarlo
        List<Equipo> equipos = dao.listarEquipos();
        for (Equipo e : equipos) {
            if (e.getNombre().equals("equipo_prueba")) {
                e.setPresupuesto(500);
                ; // modificamos algo
                int actualizados = dao.modificar(e);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarEquipoTTest() throws Exception {
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
    public void listarEquipoTTest() throws Exception {
        List<Equipo> equipos = dao.listarEquipos();
        assertEquals(9, equipos.size());
    }

    @Test
    public void insertarCompeticionTest() throws Exception {
        Competicion competicion = new Competicion(0, "competicion_prueba", "pais_prueba", "liga",  20, 1900);

        int real = dao.insertar(competicion);
        assertEquals(1, real);
    }

    @Test
    public void actualizarCompeticionTest() throws Exception {
        // Recuperamos el jugador para actualizarlo
        List<Competicion> competiciones = dao.listarCompeticiones();
        for (Competicion c : competiciones) {
            if (c.getNombre().equals("competicion_prueba")) {
                c.setAnioCreacion(2000);
                ; // modificamos algo
                int actualizados = dao.modificar(c);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarCompeticionTest() throws Exception {
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
    public void listarCompeticionTest() throws Exception {
        List<Competicion> competiciones = dao.listarCompeticiones();
        assertEquals(12, competiciones.size());
    }

    @Test
    public void insertarEquipoCompeticionTest() throws Exception {
        EquipoCompeticion equipoCompeticion = new EquipoCompeticion(9, 9, 9, 11);
        int real = dao.insertar(equipoCompeticion);
        assertEquals(1, real);
    }

    @Test
    public void actualizarEquipoCompeticionTest() throws Exception {
        // Recuperamos el jugador para actualizarlo
        List<EquipoCompeticion> equipoCompeticiones = dao.listarEquiposCompeticion();
        for (EquipoCompeticion eqs : equipoCompeticiones) {
            if (eqs.getEquipoId() == 9 && eqs.getCompeticionId() == 9 && eqs.getTemporadaId() == 9) {
                eqs.setRango(20);
                ; // modificamos algo
                int actualizados = dao.modificar(eqs);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarEquipoCompeticionTest() throws Exception {
        List<EquipoCompeticion> equipoCompeticiones = dao.listarEquiposCompeticion();

        int equipoId = -1;
        int competicionId = -1;
        int temporadaId = -1;

        for (EquipoCompeticion eqs : equipoCompeticiones) {
            if (eqs.getRango() == 20) { // puedes cambiar el criterio si hace falta
                equipoId = eqs.getEquipoId();
                competicionId = eqs.getCompeticionId();
                temporadaId = eqs.getTemporadaId();
                break;
            }
        }

        // Validación defensiva por si no encuentra nada
        assertNotEquals(-1, equipoId, "No se encontró un registro con rango 11");

        // Llama al método que elimina usando las tres claves
        int real = dao.eliminarEquipoCompeticion(equipoId, competicionId, temporadaId);
        assertEquals(1, real);
    }

    @Test
    public void listarEquipoCompeticionTest() throws Exception {
        List<EquipoCompeticion> equipoCompeticiones = dao.listarEquiposCompeticion();
        assertEquals(47, equipoCompeticiones.size());// o puedes comprobar exacto si conoces el número
    }

    @Test
    public void insertarContratoTest() throws Exception {
        Contrato contrato = new Contrato(0, 2, 2, LocalDate.of(2025, 1, 1), LocalDate.of(2026, 1, 1), 5000000.0f, "cesion");
        int real = dao.insertar(contrato);
        assertEquals(1, real);
    }

    @Test
    public void actualizarContratoTest() throws Exception {
        List<Contrato> contratos = dao.listarContratos();
        for (Contrato contrato : contratos) {
            if (contrato.getJugadorId() == 2 && contrato.getEquipoId() == 2) {
                contrato.setSalario(7500000.0f); 
                int actualizados = dao.modificar(contrato);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarContratoTest() throws Exception {
        List<Contrato> contratos = dao.listarContratos();

        int idContratoAEliminar = -1;
        for (Contrato contrato : contratos) {
            if (contrato.getSalario() == 7500000.0f) {
                idContratoAEliminar = contrato.getIdContrato();
                break;
            }
        }

        // Validación defensiva
        assertNotEquals(-1, idContratoAEliminar, "No se encontró un contrato con salario 7500000");

        int real = dao.eliminarContrato(idContratoAEliminar);
        assertEquals(1, real);
    }

    @Test
    public void listarContratosTest() throws Exception {
        List<Contrato> contratos = dao.listarContratos();
        assertEquals(5, contratos.size()); 
    }

    @Test
    public void insertarEstadisticasTemporadaTest() throws Exception {
        EstadisticasTemporada estadisticas = new EstadisticasTemporada(2, 56, 7, 7, 0, 0, 0);
        int real = dao.insertar(estadisticas);
        assertEquals(1, real);
    }

    @Test
    public void modificarEstadisticasTemporadaTest() throws Exception {
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
    public void eliminarEstadisticasTemporadaTest() throws Exception {
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
    public void listarEstadisticasTemporadaTest() throws Exception {
        List<EstadisticasTemporada> lista = dao.listarEstadisticasTemporada();
        assertEquals(42, lista.size()); // o usa assertEquals(n, size) si sabes el número exacto
    }

    @Test
    public void testBuscarEstadisticasTotalesPorJugador() throws BBDDException {
        int jugadorId = 1;

        EstadisticasTemporada totales = dao.buscarEstadisticasTotalesPorJugador(jugadorId);

        assertNotNull(totales, "Las estadísticas totales no deben ser nulas");

        // Sumas verificadas manualmente de la tabla (puedes cambiarlas si varían los
        // datos)
        assertEquals(444, totales.getPartidosJugados(), "Total de partidos jugados incorrecto");
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

        // Datos sumados a mano de la tabla para temporada 56
        assertEquals(42, temporada.getPartidosJugados(), "Total de partidos jugados en temporada 56 incorrecto");
        assertEquals(12, temporada.getGoles(), "Total de goles en temporada 56 incorrecto");
        assertEquals(2, temporada.getAsistencias(), "Total de asistencias en temporada 56 incorrecto");
        
       
    }

    @Test
    public void anadirTraspaso() throws BBDDException{
        Traspaso traspaso = new Traspaso(0, 2, 9, 9, 56, LocalDate.of(1986, 3, 30), 0, "cesion");
        int real = dao.insertar(traspaso);
        assertEquals(1, real);
    }

    @Test
    public void modificarTraspasoTest() throws Exception {
        List<Traspaso> traspasos = dao.listarTraspasos();
        for (Traspaso t : traspasos) {
            if (t.getJugadorId() == 2 && t.getTemporadaId() == 56 && t.getEquipoOrigenId() == 9) {
                t.setCantidad(2000000.0f); // modificamos algo
                int actualizados = dao.modificar(t);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarTraspasoTest() throws Exception {
        List<Traspaso> traspasos = dao.listarTraspasos();
        int idTraspaso = -1;

        for (Traspaso t : traspasos) {
            if (t.getCantidad() == 2000000.0f) {
                idTraspaso = t.getIdTraspaso();
                break;
            }
        }

        assertNotEquals(-1, idTraspaso, "No se encontró un traspaso con la cantidad esperada");

        int real = dao.eliminarTraspaso(idTraspaso);
        assertEquals(1, real);
    }

    @Test
    public void listarTraspasosTest() throws Exception {
        List<Traspaso> traspasos = dao.listarTraspasos();
        assertEquals(4, traspasos.size()); // o usa assertEquals(n, size) si sabes el número exacto
    }

    @Test
    public void insertarValorMercadoHistoriaTest() throws Exception {
        ValorMercadoHistorial historia = new ValorMercadoHistorial(0, 1, LocalDate.of(2025, 7, 1), 999_000_000,
                "Sube por buena temporada");
        int real = dao.insertar(historia);
        assertEquals(1, real);
    }

    @Test
    public void modificarValorMercadoHistoriaTest() throws Exception {
        List<ValorMercadoHistorial> historiales = dao.listarValorMercadoHistorial();
        for (ValorMercadoHistorial h : historiales) {
            if (h.getValorMercado() == 999_000_000 && h.getJugadorId() == 1) {
                h.setValorMercado(1_000_000);
                int actualizados = dao.modificar(h);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarValorMercadoHistoriaTest() throws Exception {
        List<ValorMercadoHistorial> historiales = dao.listarValorMercadoHistorial();
        int id = -1;
        for (ValorMercadoHistorial h : historiales) {
            if (h.getValorMercado() == 1_000_000) {
                id = h.getIdHistorial();
                break;
            }
        }

        assertNotEquals(-1, id, "No se encontró historial con valor esperado");
        int real = dao.eliminarValorMercadoHistorial(id);
        assertEquals(1, real);
    }

    @Test
    public void listarValorMercadoHistoriaTest() throws Exception {
        List<ValorMercadoHistorial> historiales = dao.listarValorMercadoHistorial();
        assertEquals(11, historiales.size());
    }

    @Test
    public void insertarUsuarioTest() throws Exception {
        Usuario usuario = new Usuario(0, "Test User", "test@example.com", "1234567899", "lector");
        int real = dao.insertar(usuario);
        assertEquals(1, real);
    }

    @Test
    public void modificarUsuarioTest() throws Exception {
        List<Usuario> usuarios = dao.listarUsuarios();
        for (Usuario u : usuarios) {
            if ("test@example.com".equals(u.getEmail())) {
                u.setNombre("Usuario Modificado");
                int actualizados = dao.modificar(u);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarUsuarioTest() throws Exception {
        List<Usuario> usuarios = dao.listarUsuarios();
        int id = -1;
        for (Usuario u : usuarios) {
            if ("Usuario Modificado".equals(u.getNombre())) {
                id = u.getIdUsuario();
                break;
            }
        }

        assertNotEquals(-1, id, "No se encontró el usuario a eliminar");
        int real = dao.eliminarUsuario(id);
        assertEquals(1, real);
    }

    @Test
    public void listarUsuariosTest() throws Exception {
        List<Usuario> usuarios = dao.listarUsuarios();
        assertEquals(2, usuarios.size());
    }

}
