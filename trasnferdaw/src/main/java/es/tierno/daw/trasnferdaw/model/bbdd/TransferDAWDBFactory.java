package es.tierno.daw.trasnferdaw.model.bbdd;

public class TransferDAWDBFactory {
    public static TransferDAWDAO obtener(Database tipo) throws Exception {
        if (tipo == Database.MARIADB) {
            return new TransferDAOImpMariaDB();
        } else {
            return new TransferDAOImpMock();
        }
    }
}
