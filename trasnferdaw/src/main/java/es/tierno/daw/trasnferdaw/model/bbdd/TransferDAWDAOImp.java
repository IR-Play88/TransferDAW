package es.tierno.daw.trasnferdaw.model.bbdd;

import java.sql.Connection;

/**
 * La clase TransferDAWDAOImp servirá para futuras actualizaciones.
 * \author Iván Rafael Redondo.
 */
public  abstract class TransferDAWDAOImp implements TransferDAWDAO {
    protected Connection conn;
}
