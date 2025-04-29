package es.tierno.daw.trasnferdaw.model.bbdd;

import java.util.List;

import es.tierno.daw.trasnferdaw.model.entities.Jugador;

public class TransferDAOImpMock  implements TransferDAWDAO{

    @Override
    public void insertar(Jugador jugador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public void modificar(Jugador jugador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public void eliminar(int idJugador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    @Override
    public List<Jugador> listarJugadores() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarJugadores'");
    }

    @Override
    public Jugador buscarPorId(int idJugador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }
    
}
