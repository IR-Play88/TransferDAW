package es.tierno.daw.trasnferdaw.model.exception;

public class BBDDException extends TransferDAWException {

    // Constructor con un mensaje específico
    public BBDDException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor solo con mensaje
    public BBDDException(String message) {
        super(message, null);
    }

}
