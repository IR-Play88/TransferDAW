package es.tierno.daw.trasnferdaw.model.bbdd;

import java.util.List;

import es.tierno.daw.trasnferdaw.model.entities.CategoriaPosicion;
import es.tierno.daw.trasnferdaw.model.entities.Competicion;
import es.tierno.daw.trasnferdaw.model.entities.Contrato;
import es.tierno.daw.trasnferdaw.model.entities.Entrenador;
import es.tierno.daw.trasnferdaw.model.entities.Equipo;
import es.tierno.daw.trasnferdaw.model.entities.EquipoCompeticion;
import es.tierno.daw.trasnferdaw.model.entities.EquipoEntrenador;
import es.tierno.daw.trasnferdaw.model.entities.EquipoEstadio;
import es.tierno.daw.trasnferdaw.model.entities.Estadio;
import es.tierno.daw.trasnferdaw.model.entities.EstadisticasTemporada;
import es.tierno.daw.trasnferdaw.model.entities.Jugador;
import es.tierno.daw.trasnferdaw.model.entities.JugadorPosicion;
import es.tierno.daw.trasnferdaw.model.entities.Noticia;
import es.tierno.daw.trasnferdaw.model.entities.Posicion;
import es.tierno.daw.trasnferdaw.model.entities.Representante;
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

    // Representante
    public int insertar(Representante representante) throws BBDDException;

    public int modificar(Representante representante) throws BBDDException;

    public int eliminarRepresentante(int idRepresentante) throws BBDDException;

    public List<Representante> listarRepresentantes() throws BBDDException;

    // Entrenador
    public int insertar(Entrenador entrenador) throws BBDDException;

    public int modificar(Entrenador entrenador) throws BBDDException;

    public int eliminarEntrenador(int idEntrenador) throws BBDDException;

    public List<Entrenador> listarEntrenadores() throws BBDDException;

    // CategoriaPosicion
    public List<CategoriaPosicion> listarCategoriasPosicion() throws BBDDException;

    // Posicion
    public List<Posicion> listarPosiciones() throws BBDDException;

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

    // Estadio
    public int insertar(Estadio estadio) throws BBDDException;

    public int modificar(Estadio estadio) throws BBDDException;

    public int eliminarEstadio(int idEstadio) throws BBDDException;

    public List<Estadio> listarEstadios() throws BBDDException;

    // EquipoEstadio
    public int insertar(EquipoEstadio equipoEstadio) throws BBDDException;

    public int modificar(EquipoEstadio equipoEstadio) throws BBDDException;

    public int eliminarEquipoEstadio(int equipoId, int estadioId) throws BBDDException;

    public List<EquipoEstadio> listarEquiposEstadio() throws BBDDException;

    // JugadorPosicion
    public int insertar(JugadorPosicion jugadorPosicion) throws BBDDException;

    public int modificar(JugadorPosicion jugadorPosicion) throws BBDDException;

    public int eliminarJugadorPosicion(int jugadorId, int posicionId, int temporadaId) throws BBDDException;

    public List<JugadorPosicion> listarJugadoresPosicion() throws BBDDException;

    // EstadisticasTemporada
    public int insertar(EstadisticasTemporada estadisticasTemporada) throws BBDDException;

    public int modificar(EstadisticasTemporada estadisticasTemporada) throws BBDDException;

    public int eliminarEstadisticasTemporada(int jugadorId, int temporadaId, int competicionId, int equipoId) throws BBDDException;

    public List<EstadisticasTemporada> listarEstadisticasTemporada() throws BBDDException;

    public List<EstadisticasTemporada> buscarPorJugador(int jugadorId) throws BBDDException;

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

    // Noticia
    public int insertar(Noticia noticia) throws BBDDException;

    public int modificar(Noticia noticia) throws BBDDException;

    public int eliminarNoticia(int idNoticia) throws BBDDException;

    public List<Noticia> listarNoticias() throws BBDDException;

    // Usuario
    public int insertar(Usuario usuario) throws BBDDException;

    public int modificar(Usuario usuario) throws BBDDException;

    public int eliminarUsuario(int idUsuario) throws BBDDException;

    public List<Usuario> listarUsuarios() throws BBDDException;

    // EquipoEntrenador
    public int insertar(EquipoEntrenador equipoEntrenador) throws BBDDException;

    public int modificar(EquipoEntrenador equipoEntrenador) throws BBDDException;

    public int eliminarEquipoEntrenador(int entrenadorId, int equipoId) throws BBDDException;

    public List<EquipoEntrenador> listarEquiposEntrenador() throws BBDDException;
}
