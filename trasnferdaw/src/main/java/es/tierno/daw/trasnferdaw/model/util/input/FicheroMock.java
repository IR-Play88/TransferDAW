package es.tierno.daw.trasnferdaw.model.util.input;

public class FicheroMock  implements Fichero{
    @Override
	public void escribir(String ruta, String texto) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'write'");
	}
	
	@Override
	public byte[] leer(String fichero) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'leer'");
	}
}
