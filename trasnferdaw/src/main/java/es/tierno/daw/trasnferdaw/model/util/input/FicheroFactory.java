package es.tierno.daw.trasnferdaw.model.util.input;

public class FicheroFactory {
    public static Fichero obtener(FormatoFichero formato){
		if (formato == FormatoFichero.BINARIO ) {
			return new FicheroBinario();
		}else{
			return new FicheroMock();
		}
	}
}
