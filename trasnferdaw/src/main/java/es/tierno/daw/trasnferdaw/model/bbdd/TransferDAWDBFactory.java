package es.tierno.daw.trasnferdaw.model.bbdd;

import es.tierno.daw.trasnferdaw.model.exception.BBDDException;

public class TransferDAWDBFactory {
    public static TransferDAWDAO obtener(Database tipo) throws BBDDException{
        
        if (tipo == Database.MARIADB) {
            try {
                return new TransferDAOImpMariaDB();
            } catch (Exception e) {
                throw new BBDDException();
            }
            
        }
        
        return new TransferDAOImpMock();
    }
}
