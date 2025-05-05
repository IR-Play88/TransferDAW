package es.tierno.daw.trasnferdaw.model.bbdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.time.LocalDate;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.tierno.daw.trasnferdaw.model.entities.CategoriaPosicion;
import es.tierno.daw.trasnferdaw.model.entities.Competicion;
import es.tierno.daw.trasnferdaw.model.entities.Contrato;
import es.tierno.daw.trasnferdaw.model.entities.Entrenador;
import es.tierno.daw.trasnferdaw.model.entities.Equipo;
import es.tierno.daw.trasnferdaw.model.entities.EquipoCompeticion;
import es.tierno.daw.trasnferdaw.model.entities.EquipoEntrenador;
import es.tierno.daw.trasnferdaw.model.entities.EquipoEstadio;
import es.tierno.daw.trasnferdaw.model.entities.Estadio;
import es.tierno.daw.trasnferdaw.model.entities.EstadisticasTemporada;
import es.tierno.daw.trasnferdaw.model.entities.Jugador;
import es.tierno.daw.trasnferdaw.model.entities.JugadorPosicion;
import es.tierno.daw.trasnferdaw.model.entities.Noticia;
import es.tierno.daw.trasnferdaw.model.entities.Posicion;
import es.tierno.daw.trasnferdaw.model.entities.Representante;
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
        Jugador jugador = new Jugador(0, "Isco", "Alarcon", null,
                LocalDate.of(1986, 3, 30), "España", 1.84f, 82f,
                "derecho", 10f, null, null);

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
        assertNull(jugador.getFotoUrl(), "La URL de la foto del jugador debería ser 'url_foto.jpg'");
        assertEquals(LocalDate.of(1996, 1, 21), jugador.getFechaNacimiento(),
                "La fecha de nacimiento del jugador debería ser '1996-01-21'");
        assertEquals("España", jugador.getNacionalidad(), "La nacionalidad del jugador debería ser 'Española'");
        assertEquals(1.82f, jugador.getAltura(), 0.01f, "La altura del jugador debería ser 1.82f");
        assertEquals(76.0f, jugador.getPeso(), 0.01f, "El peso del jugador debería ser 76.0f");
        assertEquals("izquierdo", jugador.getPieDominante(), "El pie dominante del jugador debería ser 'izquierdo'");
        assertEquals(20f, jugador.getValorMercado(), 0.01f, "El valor de mercado del jugador debería ser 200000000f");
        assertEquals(1, jugador.getRepresentanteId(), "El ID del representante del jugador debería ser 1");
        assertEquals(1, jugador.getSeleccionId(), "El ID de la selección del jugador debería ser 1");
    }

    @Test
    public void insertarRepresentanteTest() throws Exception {
        Representante representante = new Representante(0, null, "prueba", "123456789", "prueba@gmail.com",
                "prueba hoy");

        int real = dao.insertar(representante);
        assertEquals(1, real);
    }

    @Test
    public void actualizarRepresentanteTest() throws Exception {
        // Recuperamos el jugador para actualizarlo
        List<Representante> representantes = dao.listarRepresentantes();
        for (Representante r : representantes) {
            if (r.getNombre().equals("prueba")) {
                r.setDireccion("prueba actualizada"); // modificamos algo
                int actualizados = dao.modificar(r);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarRepresentanteTest() throws Exception {
        List<Representante> representantes = dao.listarRepresentantes();
        int idEliminar = -1;
        for (Representante r : representantes) {
            if (r.getNombre().equals("prueba")) {
                idEliminar = r.getId_representante();
                break;
            }
        }

        int real = dao.eliminarRepresentante(idEliminar);
        assertEquals(1, real);

    }

    @Test
    public void listarRepresentanteTest() throws Exception {
        List<Representante> representantes = dao.listarRepresentantes();
        assertEquals(4, representantes.size());// o puedes comprobar exacto si conoces el número
    }

    @Test
    public void insertarEntrenadorTest() throws Exception {
        Entrenador entrenador = new Entrenador(0, null, "entrenador_prueba", LocalDate.of(1986, 3, 30),
                "española_prueba", "experiencia_prueba");

        int real = dao.insertar(entrenador);
        assertEquals(1, real);
    }

    @Test
    public void actualizarEntrenadorTest() throws Exception {
        // Recuperamos el jugador para actualizarlo
        List<Entrenador> entrenadores = dao.listarEntrenadores();
        for (Entrenador e : entrenadores) {
            if (e.getNombre().equals("entrenador_prueba")) {
                e.setExperiencia("experiencia_2_prueba");
                ; // modificamos algo
                int actualizados = dao.modificar(e);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarEntrenadorTest() throws Exception {
        List<Entrenador> entrenadores = dao.listarEntrenadores();
        int idEliminar = -1;
        for (Entrenador e : entrenadores) {
            if (e.getNombre().equals("entrenador_prueba")) {
                idEliminar = e.getId_entrenador();
                break;
            }
        }

        int real = dao.eliminarEntrenador(idEliminar);
        assertEquals(1, real);

    }

    @Test
    public void listarEntrenadorTest() throws Exception {
        List<Entrenador> entrenadores = dao.listarEntrenadores();
        assertEquals(6, entrenadores.size());// o puedes comprobar exacto si conoces el número
    }

    @Test
    public void listarCategoriaPosicionrTest() throws Exception {
        List<CategoriaPosicion> categoriaPosiciones = dao.listarCategoriasPosicion();
        assertEquals(4, categoriaPosiciones.size());// o puedes comprobar exacto si conoces el número
    }

    @Test
    public void listarPosicionTest() throws Exception {
        List<Posicion> posiciones = dao.listarPosiciones();
        assertEquals(13, posiciones.size());// o puedes comprobar exacto si conoces el número
    }

    @Test
    public void listarTemporadaTest() throws Exception {
        List<Temporada> temporadas = dao.listarTemporadas();
        assertEquals(56, temporadas.size());// o puedes comprobar exacto si conoces el número
    }

    @Test
    public void insertarSelecionTest() throws Exception {
        Seleccion seleccion = new Seleccion(0, null, "pais_prueba", "españa_prueba", "federacion_prueba",
                LocalDate.of(1986, 3, 30), 11, null, null);

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
        assertEquals(2, selecciones.size());// o puedes comprobar exacto si conoces el número
    }

    @Test
    public void insertarEquipoTest() throws Exception {
        Equipo equipo = new Equipo(0, "equipo_prueba", "ciudad_prueba", "pais_prueba", null, "descripcion_prueba", 1999,
                100, null, null);

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
        assertEquals(9, equipos.size());// o puedes comprobar exacto si conoces el número
    }

    @Test
    public void insertarCompeticionTest() throws Exception {
        Competicion competicion = new Competicion(0, "competicion_prueba", "pais_prueba", "liga", null, 20, 1900);

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
        assertEquals(12, competiciones.size());// o puedes comprobar exacto si conoces el número
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
        assertEquals(45, equipoCompeticiones.size());// o puedes comprobar exacto si conoces el número
    }

    @Test
    public void insertarContratoTest() throws Exception {
        Contrato contrato = new Contrato(0, 1, 1, LocalDate.of(2025, 1, 1), LocalDate.of(2026, 1, 1), 5000000.0f,
                560000.0f, "cesion", 1000);
        int real = dao.insertar(contrato);
        assertEquals(1, real);
    }

    @Test
    public void actualizarContratoTest() throws Exception {
        List<Contrato> contratos = dao.listarContratos();
        for (Contrato contrato : contratos) {
            if (contrato.getJugadorId() == 9 && contrato.getEquipoId() == 9) {
                contrato.setSalario(7500000.0f); // modificamos algo
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
            if (contrato.getSalario() == 7500000.0f) { // criterio de prueba
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
        assertEquals(5, contratos.size()); // o usa assertEquals si conoces el número exacto
    }

    @Test
    public void insertarEstadioTest() throws Exception {
        Estadio estadio = new Estadio(0, null, "Nuevo Estadio", 35000, "Sevilla, España", 2025);
        int real = dao.insertar(estadio);
        assertEquals(1, real);
    }

    @Test
    public void actualizarEstadioTest() throws Exception {
        List<Estadio> estadios = dao.listarEstadios();
        for (Estadio estadio : estadios) {
            if ("Nuevo Estadio".equals(estadio.getNombre())) {
                estadio.setCapacidad(40000); // modificamos algo
                int actualizados = dao.modificar(estadio);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarEstadioTest() throws Exception {
        List<Estadio> estadios = dao.listarEstadios();
        int idEstadio = -1;
        for (Estadio estadio : estadios) {
            if (estadio.getCapacidad() == 40000) {
                idEstadio = estadio.getIdEstadio();
                break;
            }
        }

        assertNotEquals(-1, idEstadio, "No se encontró un estadio con capacidad 40000");

        int real = dao.eliminarEstadio(idEstadio);
        assertEquals(1, real);
    }

    @Test
    public void listarEstadiosTest() throws Exception {
        List<Estadio> estadios = dao.listarEstadios();
        assertEquals(5, estadios.size()); // ajusta el número si cambia el total real
    }

    @Test
    public void insertarEquipoEstadioTest() throws Exception {
        EquipoEstadio ee = new EquipoEstadio(9, 3, LocalDate.of(2024, 1, 1), null);
        int real = dao.insertar(ee);
        assertEquals(1, real);
    }

    @Test
    public void actualizarEquipoEstadioTest() throws Exception {
        List<EquipoEstadio> relaciones = dao.listarEquiposEstadio();
        for (EquipoEstadio ee : relaciones) {
            if (ee.getEquipoId() == 9 && ee.getEstadioId() == 3
                    && ee.getFechaInicio().equals(LocalDate.of(2024, 1, 1))) {
                ee.setFechaFin(LocalDate.of(2025, 1, 1));
                int actualizados = dao.modificar(ee);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarEquipoEstadioTest() throws Exception {
        List<EquipoEstadio> relaciones = dao.listarEquiposEstadio();
        int equipoId = -1, estadioId = -1;
        LocalDate fechaInicio = null;

        for (EquipoEstadio ee : relaciones) {
            if (ee.getFechaFin() != null && ee.getFechaFin().equals(LocalDate.of(2025, 1, 1))) {
                equipoId = ee.getEquipoId();
                estadioId = ee.getEstadioId();
                fechaInicio = ee.getFechaInicio();
                break;
            }
        }

        assertNotEquals(-1, equipoId, "No se encontró relación con fecha_fin 2025-01-01");

        int real = dao.eliminarEquipoEstadio(equipoId, estadioId);
        assertEquals(1, real);
    }

    @Test
    public void listarEquipoEstadioTest() throws Exception {
        List<EquipoEstadio> relaciones = dao.listarEquiposEstadio();
        assertEquals(5, relaciones.size()); // actualiza según tu dataset
    }

    @Test
    public void insertarJugadorPosicionTest() throws Exception {
        JugadorPosicion jp = new JugadorPosicion(2, 12, 50, true); // datos de prueba
        int real = dao.insertar(jp);
        assertEquals(1, real);
    }

    @Test
    public void modificarJugadorPosicionTest() throws Exception {
        List<JugadorPosicion> posiciones = dao.listarJugadoresPosicion();
        for (JugadorPosicion jp : posiciones) {
            if (jp.getJugadorId() == 2 && jp.getPosicionId() == 12 && jp.getTemporadaId() == 50) {
                jp.setEsPrincipal(false); // cambiar valor
                int actualizados = dao.modificar(jp);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarJugadorPosicionTest() throws Exception {
        List<JugadorPosicion> posiciones = dao.listarJugadoresPosicion();

        int jugadorId = -1, posicionId = -1, temporadaId = -1;

        for (JugadorPosicion jp : posiciones) {
            if (jp.getPosicionId() == 12 && jp.getTemporadaId() == 50) { // criterio usado antes
                jugadorId = jp.getJugadorId();
                posicionId = jp.getPosicionId();
                temporadaId = jp.getTemporadaId();
                break;
            }
        }

        assertNotEquals(-1, jugadorId, "No se encontró una posición de jugador válida para eliminar");

        int real = dao.eliminarJugadorPosicion(jugadorId, posicionId, temporadaId);
        assertEquals(1, real);
    }

    @Test
    public void listarJugadorPosicionTest() throws Exception {
        List<JugadorPosicion> posiciones = dao.listarJugadoresPosicion();
        assertEquals(12, posiciones.size()); // o assertEquals(x, size) si sabes el número exacto
    }

    @Test
    public void insertarEstadisticasTemporadaTest() throws Exception {
        EstadisticasTemporada estadisticas = new EstadisticasTemporada(2, 56, 7, 7, 0, 0, 0, 0, 0, 0, 0);
        int real = dao.insertar(estadisticas);
        assertEquals(1, real);
    }

    @Test
    public void modificarEstadisticasTemporadaTest() throws Exception {
        List<EstadisticasTemporada> estadisticas = dao.listarEstadisticasTemporada();

        for (EstadisticasTemporada est : estadisticas) {
            if (est.getJugadorId() == 2 && est.getTemporadaId() == 56 && est.getCompeticionId() == 7
                    && est.getEquipoId() == 7) {
                est.setGoles(99); // ejemplo de modificación
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
            if (est.getGoles() == 99) { // Usa un criterio de prueba reconocible
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
        assertEquals(47, lista.size()); // o usa assertEquals(n, size) si sabes el número exacto
    }

    @Test
    public void testBuscarEstadisticasTotalesPorJugador() throws BBDDException {
        int jugadorId = 1;

        EstadisticasTemporada totales = dao.buscarEstadisticasTotalesPorJugador(jugadorId);

        assertNotNull(totales, "Las estadísticas totales no deben ser nulas");

        // Sumas verificadas manualmente de la tabla (puedes cambiarlas si varían los
        // datos)
        assertEquals(94, totales.getGoles(), "Total de goles incorrecto");
        assertEquals(72, totales.getAsistencias(), "Total de asistencias incorrecto");
        assertEquals(24765, totales.getMinutos(), "Total de minutos incorrecto");
        assertEquals(468, totales.getPartidosJugados(), "Total de partidos jugados incorrecto");
        assertEquals(23, totales.getAmarillas(), "Total de amarillas incorrecto");
        assertEquals(12, totales.getRojas(), "Total de rojas incorrecto");
    }

    @Test
    public void testBuscarEstadisticasPorTemporada() throws BBDDException {
        int jugadorId = 1;
        int temporadaId = 56;

        EstadisticasTemporada temporada = dao.buscarEstadisticasPorTemporada(jugadorId, temporadaId);

        assertNotNull(temporada, "Las estadísticas de la temporada no deben ser nulas");

        assertEquals(jugadorId, temporada.getJugadorId(), "ID del jugador incorrecto");
        assertEquals(temporadaId, temporada.getTemporadaId(), "ID de temporada incorrecto");

        // Datos sumados a mano de la tabla para temporada 56
        assertEquals(17, temporada.getGoles(), "Total de goles en temporada 56 incorrecto");
        assertEquals(8, temporada.getAsistencias(), "Total de asistencias en temporada 56 incorrecto");
        assertEquals(2904, temporada.getMinutos(), "Total de minutos en temporada 56 incorrecto");
        assertEquals(53, temporada.getPartidosJugados(), "Total de partidos jugados en temporada 56 incorrecto");
        assertEquals(6, temporada.getAmarillas(), "Total de amarillas en temporada 56 incorrecto");
        assertEquals(3, temporada.getRojas(), "Total de rojas en temporada 56 incorrecto");
    }

    @Test
    public void modificarTraspasoTest() throws Exception {
        List<Traspaso> traspasos = dao.listarTraspasos();
        for (Traspaso t : traspasos) {
            if (t.getJugadorId() == 2 && t.getTemporadaId() == 50 && t.getEquipoOrigenId() == 3) {
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
    public void insertarNoticiaTest() throws Exception {
        Noticia noticia = new Noticia(0, 1, 1, 1, "Título de prueba", "Contenido de prueba", "", "Deportes",
                LocalDate.of(2025, 5, 5));
        int real = dao.insertar(noticia);
        assertEquals(1, real);
    }

    @Test
    public void modificarNoticiaTest() throws Exception {
        List<Noticia> noticias = dao.listarNoticias();
        for (Noticia n : noticias) {
            if ("Título de prueba".equals(n.getTitulo())) {
                n.setTitulo("Título actualizado");
                int actualizados = dao.modificar(n);
                assertEquals(1, actualizados);
                break;
            }
        }
    }

    @Test
    public void eliminarNoticiaTest() throws Exception {
        List<Noticia> noticias = dao.listarNoticias();
        int id = -1;
        for (Noticia n : noticias) {
            if ("Título actualizado".equals(n.getTitulo())) {
                id = n.getIdNoticia();
                break;
            }
        }

        assertNotEquals(-1, id, "No se encontró la noticia esperada");
        int real = dao.eliminarNoticia(id);
        assertEquals(1, real);
    }

    @Test
    public void listarNoticiasTest() throws Exception {
        List<Noticia> noticias = dao.listarNoticias();
        assertEquals(1, noticias.size());
    }

    @Test
    public void insertarUsuarioTest() throws Exception {
        Usuario usuario = new Usuario(0, "Test User", "test@example.com", "1234567899", "lector", LocalDate.now());
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
