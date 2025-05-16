package es.tierno.daw.trasnferdaw.model.bbdd;

import java.util.List;

import es.tierno.daw.trasnferdaw.model.entities.Competicion;
import es.tierno.daw.trasnferdaw.model.entities.Contrato;
import es.tierno.daw.trasnferdaw.model.entities.Equipo;
import es.tierno.daw.trasnferdaw.model.entities.EquipoCompeticion;
import es.tierno.daw.trasnferdaw.model.entities.EstadisticasTemporada;
import es.tierno.daw.trasnferdaw.model.entities.Jugador;
import es.tierno.daw.trasnferdaw.model.entities.Temporada;
import es.tierno.daw.trasnferdaw.model.entities.Traspaso;
import es.tierno.daw.trasnferdaw.model.entities.ValorMercadoHistorial;
import es.tierno.daw.trasnferdaw.model.exception.BBDDException;

public class TransferDAOImpMock implements TransferDAWDAO {

    @Override
    public List<Temporada> listarTemporadas() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTemporadas'");
    }

    @Override
    public int insertar(Jugador jugador) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int modificar(Jugador jugador) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public int eliminarJugador(int idJugador) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarJugador'");
    }

    @Override
    public List<Jugador> listarJugadores() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarJugadores'");
    }

    @Override
    public Jugador visualizarJugador(int idJugador) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visualizarJugador'");
    }

    @Override
    public int insertar(Equipo equipo) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int modificar(Equipo equipo) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public int eliminarEquipo(int idEquipo) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarEquipo'");
    }

    @Override
    public List<Equipo> listarEquipos() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarEquipos'");
    }

    @Override
    public Equipo visualizarEquipo(int idEquipo) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visualizarEquipo'");
    }

    @Override
    public int insertar(Competicion competicion) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int modificar(Competicion competicion) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public int eliminarCompeticion(int idCompeticion) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarCompeticion'");
    }

    @Override
    public List<Competicion> listarCompeticiones() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarCompeticiones'");
    }

    @Override
    public Competicion visualizarCompeticion(int idCompeticion) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visualizarCompeticion'");
    }

    @Override
    public int insertar(EquipoCompeticion equipoCompeticion) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int modificar(EquipoCompeticion equipoCompeticion) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public int eliminarEquipoCompeticion(int equipoId, int competicionId, int temporadaId) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarEquipoCompeticion'");
    }

    @Override
    public List<EquipoCompeticion> listarEquiposCompeticion() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarEquiposCompeticion'");
    }

    @Override
    public EquipoCompeticion visualizarEquipoCompeticion(int equipoId, int competicionId, int temporadaId)
            throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visualizarEquipoCompeticion'");
    }

    @Override
    public int insertar(Contrato contrato) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int modificar(Contrato contrato) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public int eliminarContrato(int idContrato) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarContrato'");
    }

    @Override
    public List<Contrato> listarContratos() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarContratos'");
    }

    @Override
    public Contrato visualizarContrato(int idContrato) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visualizarContrato'");
    }

    @Override
    public int insertar(EstadisticasTemporada estadisticasTemporada) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int modificar(EstadisticasTemporada estadisticasTemporada) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public int eliminarEstadisticasTemporada(int jugadorId, int temporadaId, int competicionId, int equipoId)
            throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarEstadisticasTemporada'");
    }

    @Override
    public List<EstadisticasTemporada> listarEstadisticasTemporada() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarEstadisticasTemporada'");
    }

    @Override
    public EstadisticasTemporada buscarEstadisticasTotalesPorJugador(int jugadorId) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarEstadisticasTotalesPorJugador'");
    }

    @Override
    public EstadisticasTemporada buscarEstadisticasPorTemporada(int jugadorId, int temporadaId) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarEstadisticasPorTemporada'");
    }

    @Override
    public Temporada visualizarTemporada(int idTemporada) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visualizarTemporada'");
    }

    @Override
    public int insertar(Traspaso traspaso) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int modificar(Traspaso traspaso) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public int eliminarTraspaso(int idTraspaso) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarTraspaso'");
    }

    @Override
    public List<Traspaso> listarTraspasos() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTraspasos'");
    }

    @Override
    public Traspaso visualizarTraspaso(int idTraspaso) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visualizarTraspaso'");
    }

    @Override
    public int insertar(ValorMercadoHistorial valorMercadoHistorial) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int modificar(ValorMercadoHistorial valorMercadoHistorial) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public int eliminarValorMercadoHistorial(int idHistorial) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarValorMercadoHistorial'");
    }

    @Override
    public List<ValorMercadoHistorial> listarValorMercadoHistorial() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarValorMercadoHistorial'");
    }

    @Override
    public ValorMercadoHistorial visualizarValorMercadoHistorial(int idHistorial) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visualizarValorMercadoHistorial'");
    }

   
   
}
