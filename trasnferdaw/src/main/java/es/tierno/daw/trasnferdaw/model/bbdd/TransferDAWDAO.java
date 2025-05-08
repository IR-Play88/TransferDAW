package es.tierno.daw.trasnferdaw.model.bbdd;

import java.util.List;

import es.tierno.daw.trasnferdaw.model.entities.Competicion;
import es.tierno.daw.trasnferdaw.model.entities.Contrato;
import es.tierno.daw.trasnferdaw.model.entities.Equipo;
import es.tierno.daw.trasnferdaw.model.entities.EquipoCompeticion;
import es.tierno.daw.trasnferdaw.model.entities.EstadisticasTemporada;
import es.tierno.daw.trasnferdaw.model.entities.Jugador;
import es.tierno.daw.trasnferdaw.model.entities.Seleccion;
import es.tierno.daw.trasnferdaw.model.entities.Temporada;
import es.tierno.daw.trasnferdaw.model.entities.Traspaso;
import es.tierno.daw.trasnferdaw.model.entities.Usuario;
import es.tierno.daw.trasnferdaw.model.entities.ValorMercadoHistorial;
import es.tierno.daw.trasnferdaw.model.exception.BBDDException;

/**
 * Esta interface nos permite Visualizar, añadir, modificar, eliminar por cada
 * entidad.
 * \author: Iván Rafael Redondo
 */
public interface TransferDAWDAO {
    // Temporada
    public List<Temporada> listarTemporadas() throws BBDDException;


    // Jugador
    public int insertar(Jugador jugador) throws BBDDException;

    public int modificar(Jugador jugador) throws BBDDException;

    public int eliminarJugador(int idJugador) throws BBDDException;

    public List<Jugador> listarJugadores() throws BBDDException;

    public Jugador buscarPorId(int idJugador) throws BBDDException;


    // Selección
    public int insertar(Seleccion seleccion) throws BBDDException;

    public int modificar(Seleccion seleccion) throws BBDDException;

    public int eliminarSeleccion(int idSeleccion) throws BBDDException;

    public List<Seleccion> listarSelecciones() throws BBDDException;


    // Equipo
    public int insertar(Equipo equipo) throws BBDDException;

    public int modificar(Equipo equipo) throws BBDDException;

    public int eliminarEquipo(int idEquipo) throws BBDDException;

    public List<Equipo> listarEquipos() throws BBDDException;


    // Competición
    public int insertar(Competicion competicion) throws BBDDException;

    public int modificar(Competicion competicion) throws BBDDException;

    public int eliminarCompeticion(int idCompeticion) throws BBDDException;

    public List<Competicion> listarCompeticiones() throws BBDDException;


    // EquipoCompeticion
    public int insertar(EquipoCompeticion equipoCompeticion) throws BBDDException;

    public int modificar(EquipoCompeticion equipoCompeticion) throws BBDDException;

    public int eliminarEquipoCompeticion(int equipoId, int competicionId, int temporadaId) throws BBDDException;

    public List<EquipoCompeticion> listarEquiposCompeticion() throws BBDDException;


    // Contrato
    public int insertar(Contrato contrato) throws BBDDException;

    public int modificar(Contrato contrato) throws BBDDException;

    public int eliminarContrato(int idContrato) throws BBDDException;

    public List<Contrato> listarContratos() throws BBDDException;


    // EstadisticasTemporada
    public int insertar(EstadisticasTemporada estadisticasTemporada) throws BBDDException;

    public int modificar(EstadisticasTemporada estadisticasTemporada) throws BBDDException;

    public int eliminarEstadisticasTemporada(int jugadorId, int temporadaId, int competicionId, int equipoId) throws BBDDException;

    public List<EstadisticasTemporada> listarEstadisticasTemporada() throws BBDDException;

    public EstadisticasTemporada buscarEstadisticasTotalesPorJugador(int jugadorId) throws BBDDException;

    public EstadisticasTemporada buscarEstadisticasPorTemporada(int jugadorId, int temporadaId) throws BBDDException;


    // Traspaso
    public int insertar(Traspaso traspaso) throws BBDDException;

    public int modificar(Traspaso traspaso) throws BBDDException;

    public int eliminarTraspaso(int idTraspaso) throws BBDDException;

    public List<Traspaso> listarTraspasos() throws BBDDException;

    // ValorMercadoHistorial
    public int insertar(ValorMercadoHistorial valorMercadoHistorial) throws BBDDException;

    public int modificar(ValorMercadoHistorial valorMercadoHistorial) throws BBDDException;

    public int eliminarValorMercadoHistorial(int idHistorial) throws BBDDException;

    public List<ValorMercadoHistorial> listarValorMercadoHistorial() throws BBDDException;


    // Usuario
    public int insertar(Usuario usuario) throws BBDDException;

    public int modificar(Usuario usuario) throws BBDDException;

    public int eliminarUsuario(int idUsuario) throws BBDDException;

    public List<Usuario> listarUsuarios() throws BBDDException;

}
