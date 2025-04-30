package es.tierno.daw.trasnferdaw.model.bbdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.time.LocalDate;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.tierno.daw.trasnferdaw.model.entities.EstadisticasTemporada;
import es.tierno.daw.trasnferdaw.model.entities.Jugador;

/**
 * Clase de pruebas unitarias para comprobar el correcto funcionamiento de la clase TransferDAOImpMariaDB.
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
     * @throws SQLException si ocurre un error al verificar la validez de la conexión.
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

    /**
     * Prueba para buscar un jugador por ID
     * \author: Iván Rafael Redondo
     * 
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
    @Test
    public void testObtenerJugadorPorID() throws SQLException {
        int idJugador = 1;

        Jugador jugador = dao.buscarPorId(idJugador);

        assertNotNull(jugador, "El jugador no debería ser nulo");

        assertEquals(idJugador, jugador.getIdJugador(), "El ID del jugador debería ser 1");
        assertEquals("Marco Asensio Willisem", jugador.getNombre(), "El nombre del jugador debería ser 'Marco Asensio Willisem'");
        assertEquals("La perla mallorquina", jugador.getAlias(), "El alias del jugador debería ser 'La perla mallorquina'");
        assertNull(jugador.getFotoUrl(), "La URL de la foto del jugador debería ser 'url_foto.jpg'");
        assertEquals(LocalDate.of(1996, 1, 21), jugador.getFechaNacimiento(), "La fecha de nacimiento del jugador debería ser '1996-01-21'");
        assertEquals("España", jugador.getNacionalidad(), "La nacionalidad del jugador debería ser 'Española'");
        assertEquals(1.82f, jugador.getAltura(), 0.01f, "La altura del jugador debería ser 1.82f");
        assertEquals(76.0f, jugador.getPeso(), 0.01f, "El peso del jugador debería ser 76.0f");
        assertEquals("izquierdo", jugador.getPieDominante(), "El pie dominante del jugador debería ser 'izquierdo'");
        assertEquals(20f, jugador.getValorMercado(), 0.01f, "El valor de mercado del jugador debería ser 200000000f");
        assertEquals(1, jugador.getRepresentanteId(), "El ID del representante del jugador debería ser 1");
        assertEquals(1, jugador.getSeleccionId(), "El ID de la selección del jugador debería ser 1");
    }

   
    @Test
    public void testListarTodos() throws Exception {
        List<EstadisticasTemporada> lista = dao.listarTodos();

        assertNotNull(lista, "La lista no debería ser null");
        assertTrue(lista.size() > 0, "La lista debería contener elementos");
    }

    @Test
    public void testBuscarPorJugador() throws Exception {
        int jugadorId = 1; // Usa un ID válido que tengas en tu BBDD
        List<EstadisticasTemporada> resultados = dao.buscarPorJugador(jugadorId);
    
        assertNotNull(resultados, "La lista de resultados no debería ser null");
        assertFalse(resultados.isEmpty(), "Debería devolver estadísticas para ese jugador");
    
        // Imprimir estadísticas por consola
        for (EstadisticasTemporada est : resultados) {
            System.out.println(est);
        }
    }
    
    
    
}
