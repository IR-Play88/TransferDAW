package es.tierno.daw.trasnferdaw.model.bbdd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.tierno.daw.trasnferdaw.model.entities.Jugador;

public class BBDDTest {
    private static TransferDAOImpMariaDB dao;  // Usamos static porque @BeforeAll es estático

    @BeforeAll
    public static void setUp() throws Exception {
        // Crear instancia de TransferDAOImpMariaDB para establecer la conexión
        dao = new TransferDAOImpMariaDB();
    }

    @Test
    public void testConnection() throws SQLException {
        // Verificar que la conexión no sea nula y que sea válida
        var connection = dao.conn;  // Usamos var para inferir el tipo de conexión

        // Verificación con un bloque de control de excepciones
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
}
