package es.tierno.daw.trasnferdaw.model.bbdd;

import java.sql.SQLException;
import java.util.List;

import es.tierno.daw.trasnferdaw.model.entities.Jugador;

/**
* Esta interface nos permite Visualizar, añadir, modificar, eliminar por cada entidad.
* \author: Iván Rafael Redondo
*/
public interface TransferDAWDAO {
    // Representante
    //void insertar(Representante representante);
    //void modificar(Representante representante);
    //void eliminar(int idRepresentante);
    //List<Representante> listarRepresentantes();

    // Entrenador
    //void insertar(Entrenador entrenador);
    //void modificar(Entrenador entrenador);
    //void eliminar(int idEntrenador);
    //List<Entrenador> listarEntrenadores();

    // CategoriaPosicion
    //void insertar(CategoriaPosicion categoriaPosicion);
    //void modificar(CategoriaPosicion categoriaPosicion);
    //void eliminar(int idCategoria);
    //List<CategoriaPosicion> listarCategoriasPosicion();

    // Posicion
    //void insertar(Posicion posicion);
    //void modificar(Posicion posicion);
    //void eliminar(int idPosicion);
    //List<Posicion> listarPosiciones();

    // Temporada
    //void insertar(Temporada temporada);
    //void modificar(Temporada temporada);
    //void eliminar(int idTemporada);
    //List<Temporada> listarTemporadas();

    // Jugador
    void insertar(Jugador jugador);
    void modificar(Jugador jugador);
    void eliminar(int idJugador);
    List<Jugador> listarJugadores();
    Jugador buscarPorId(int idJugador) throws SQLException;

    // Selección
    //void insertar(Seleccion seleccion);
    //void modificar(Seleccion seleccion);
    //void eliminar(int idSeleccion);
    //List<Seleccion> listarSelecciones();

    // Equipo
    //void insertar(Equipo equipo);
    //void modificar(Equipo equipo);
    //void eliminar(int idEquipo);
    //List<Equipo> listarEquipos();

    // Competición
    //void insertar(Competicion competicion);
    //void modificar(Competicion competicion);
    //void eliminar(int idCompeticion);
    //List<Competicion> listarCompeticiones();

    // EquipoCompeticion
    //void insertar(EquipoCompeticion equipoCompeticion);
    //void modificar(EquipoCompeticion equipoCompeticion);
    //void eliminar(int equipoId, int competicionId, int temporadaId);
    //List<EquipoCompeticion> listarEquiposCompeticion();

    // Contrato
    //void insertar(Contrato contrato);
    //void modificar(Contrato contrato);
    //void eliminar(int idContrato);
    //List<Contrato> listarContratos();

    // Estadio
    //void insertar(Estadio estadio);
    //void modificar(Estadio estadio);
    //void eliminar(int idEstadio);
    //List<Estadio> listarEstadios();

    // EquipoEstadio
    //void insertar(EquipoEstadio equipoEstadio);
    //void modificar(EquipoEstadio equipoEstadio);
    //void eliminar(int equipoId, int estadioId);
    //List<EquipoEstadio> listarEquiposEstadio();

    // JugadorPosicion
    //void insertar(JugadorPosicion jugadorPosicion);
    //void modificar(JugadorPosicion jugadorPosicion);
    //void eliminar(int jugadorId, int posicionId, int temporadaId);
    //List<JugadorPosicion> listarJugadoresPosicion();

    // EstadisticasTemporada
    //void insertar(EstadisticasTemporada estadisticasTemporada);
    //void modificar(EstadisticasTemporada estadisticasTemporada);
    //void eliminar(int jugadorId, int temporadaId, int competicionId, int equipoId);
    //List<EstadisticasTemporada> listarEstadisticasTemporada();

    // Traspaso
    //void insertar(Traspaso traspaso);
    //void modificar(Traspaso traspaso);
    //void eliminar(int idTraspaso);
    //List<Traspaso> listarTraspasos();

    // ValorMercadoHistorial
    //void insertar(ValorMercadoHistorial valorMercadoHistorial);
    //void modificar(ValorMercadoHistorial valorMercadoHistorial);
    //void eliminar(int idHistorial);
    //List<ValorMercadoHistorial> listarValorMercadoHistorial();

    // Noticia
    //void insertar(Noticia noticia);
    //void modificar(Noticia noticia);
    //void eliminar(int idNoticia);
    //List<Noticia> listarNoticias();

    // Usuario
    //void insertar(Usuario usuario);
    //void modificar(Usuario usuario);
    //void eliminar(int idUsuario);
    //List<Usuario> listarUsuarios();

    // EquipoEntrenador
    //void insertar(EquipoEntrenador equipoEntrenador);
    //void modificar(EquipoEntrenador equipoEntrenador);
    //void eliminar(int entrenadorId, int equipoId);
    //List<EquipoEntrenador> listarEquiposEntrenador();

    // Estadísticas Totales
    //List<EstadisticasTotales> listarTodos();
    //List<EstadisticasTotales> buscarPorJugador(String nombreJugador);
}
