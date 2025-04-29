package es.tierno.daw.trasnferdaw.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import es.tierno.daw.trasnferdaw.model.entities.Jugador;
import es.tierno.daw.trasnferdaw.model.util.input.Fichero;
import es.tierno.daw.trasnferdaw.model.util.input.FicheroFactory;
import es.tierno.daw.trasnferdaw.model.util.input.FormatoFichero;

public class FicheroBinarioTest {
     @Test
    public void testEscribirJugadorEnFichero() throws IOException {
        // Crear un jugador de prueba
        Jugador jugador = new Jugador(
            1,
            "Marco Asensio Willisem",
            "La perla mallorquina",
            "No disponible de momento",
            LocalDate.of(1996, 1, 21),
            "Española",
            1.82f,
            76.0f,
            "izquierdo",
            200_000_000f,
            null,
            null
        );

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
}
