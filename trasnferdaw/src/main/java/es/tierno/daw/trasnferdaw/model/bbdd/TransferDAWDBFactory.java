package es.tierno.daw.trasnferdaw.model.bbdd;

/**
 * La clase TransferDAWDBFactory crea las posibles BD disponibles.
 * \author Iván Rafael Redondo.
 */
public class TransferDAWDBFactory {
    public static TransferDAWDAO obtener(Database tipo) throws Exception {
            return new TransferDAOImpMariaDB();
    }
}
