package es.tierno.daw.trasnferdaw.model.bbdd;

import java.sql.Connection;

/**
* Clase abstracta que nos permite herecia, para que cada clase implemente los metodos a su manera.
* \author: Iván Rafael Redondo
*/
public  abstract class TransferDAWDAOImp implements TransferDAWDAO {
    protected Connection conn;
}
