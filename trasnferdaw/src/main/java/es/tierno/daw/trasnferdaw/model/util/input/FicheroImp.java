package es.tierno.daw.trasnferdaw.model.util.input;

/**
 * La clase abstracta FicheroImp utiliza Fichero, pero no las implementa, sino que permite la herencia
 * para que cada clase los pueda implementar como deba.
 * \author Iván Rafael Redondo.
 */
public abstract class FicheroImp implements Fichero {
    // Método abstracto para escribir en el fichero
    public abstract void escribir(String ruta, String texto);

    // Método abstracto para leer del fichero
    public abstract byte[] leer(String fichero);
}
