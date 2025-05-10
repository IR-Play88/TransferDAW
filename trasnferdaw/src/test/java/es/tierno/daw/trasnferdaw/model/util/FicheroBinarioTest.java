package es.tierno.daw.trasnferdaw.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import es.tierno.daw.trasnferdaw.model.bbdd.TransferDAOImpMariaDB;
import es.tierno.daw.trasnferdaw.model.entities.Jugador;
import es.tierno.daw.trasnferdaw.model.util.input.Fichero;
import es.tierno.daw.trasnferdaw.model.util.input.FicheroFactory;
import es.tierno.daw.trasnferdaw.model.util.input.FormatoFichero;

/**
 * Clase de pruebas para validar la funcionalidad de escritura y lectura
 * \author: Iván Rafael Redondo
 */
public class FicheroBinarioTest {

    /**
     * Prueba unitaria para verificar que los datos de un jugador pueden escribirse correctamente en un fichero binario y leerse sin pérdida de información.
     * \author: Iván Rafael Redondo
     * @throws IOException si ocurre un error durante la escritura o lectura del fichero.
     */
    @Test
    public void testEscribirJugadorEnFichero() throws IOException {
        // Crear un jugador de prueba
        Jugador jugador = new Jugador(1,"Marco Asensio Willisem",
                "La perla mallorquina",
                LocalDate.of(1996, 1, 21),
                "Española",
                1.82f,
                76.0f,
                "izquierdo",
                200_000_000f,
                "mediocampista",
                "Gesfusito",
                1);

        String contenidoEsperado = jugador.toString();
        String ruta = "test_jugador.dat";

        // Escribir en fichero usando FicheroBinario a través de FicheroFactory
        Fichero fichero = FicheroFactory.obtener(FormatoFichero.BINARIO);
        fichero.escribir(ruta, contenidoEsperado);

        // Leer el contenido del fichero
        byte[] datosLeidos = fichero.leer(ruta);
        String contenidoLeido = new String(datosLeidos);

        // Asegurar que el contenido es el mismo que el esperado
        assertEquals(contenidoEsperado, contenidoLeido);

        // Borrar fichero después del test
        // new File(ruta).delete();
    }

    /**
     * Prueba unitaria para verificar que los datos de un jugador  de la BBDD pueden escribirse correctamente en un fichero binario y leerse sin pérdida de información.
     * \author: Iván Rafael Redondo
     * @throws Exception si ocurre un error al acceder a la base de datos o al manejar el fichero.
     */
    @Test
    public void testEscribirJugadorDesdeBBDDEnFichero() throws Exception {
        // Obtener el jugador desde la base de datos
        TransferDAOImpMariaDB dao = new TransferDAOImpMariaDB();
        int idJugador = 1;
        Jugador jugador = dao.visualizarJugador(idJugador);

        // Verifica que no sea null
        assertNotNull(jugador, "El jugador obtenido de la BBDD no debería ser null");

        // Convertir el jugador a string
        String contenidoEsperado = jugador.toString();
        String ruta = "test_jugadorBBDD.dat";

        // Escribir en fichero usando FicheroBinario a través de FicheroFactory
        Fichero fichero = FicheroFactory.obtener(FormatoFichero.BINARIO);
        fichero.escribir(ruta, contenidoEsperado);

        // Leer el contenido del fichero
        byte[] datosLeidos = fichero.leer(ruta);
        String contenidoLeido = new String(datosLeidos);

        // Asegurar que el contenido es el mismo que el esperado
        assertEquals(contenidoEsperado, contenidoLeido);

        // Borrar el fichero después del test (opcional)
        // new File(ruta).delete();
    }
}
