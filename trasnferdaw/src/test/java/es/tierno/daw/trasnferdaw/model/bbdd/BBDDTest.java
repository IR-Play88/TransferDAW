package es.tierno.daw.trasnferdaw.model.bbdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.time.LocalDate;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.tierno.daw.trasnferdaw.model.entities.Jugador;
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
                j.setValorMercado(400000f); // modificamos algo
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
}
