package es.tierno.daw.trasnferdaw.model.bbdd;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
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
 * Clase que gestiona los metodos para la base de datos MariaDB.
 * \author: Iván Rafael Redondo
 */
public class TransferDAOImpMariaDB extends TransferDAWDAOImp {

    private final String URL = "jdbc:mariadb://localhost:3306/%s?user=%s&password=%s";
    private final String DATABASE_NAME = "TransferDAW";
    private final String DATABASE_USER = "irr";
    private final String DATABASE_PASS = "1234";

    public TransferDAOImpMariaDB() throws Exception {
        conn = DriverManager.getConnection(String.format(URL, DATABASE_NAME, DATABASE_USER, DATABASE_PASS));
    }

    @Override
    public int insertar(Jugador jugador) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO Jugador (nombre, alias, foto_url, fecha_nacimiento, nacionalidad, altura, peso, pie_dominante, valor_mercado, representante_id, seleccion_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, jugador.getNombre());
            ps.setString(2, jugador.getAlias());

            // Manejo de foto_url: si es null, se inserta como null en la base de datos
            if (jugador.getFotoUrl() != null) {
                ps.setString(3, jugador.getFotoUrl());
            } else {
                ps.setNull(3, java.sql.Types.VARCHAR);
            }

            ps.setDate(4, java.sql.Date.valueOf(jugador.getFechaNacimiento()));
            ps.setString(5, jugador.getNacionalidad());
            ps.setFloat(6, jugador.getAltura());
            ps.setFloat(7, jugador.getPeso());
            ps.setString(8, jugador.getPieDominante());
            ps.setFloat(9, jugador.getValorMercado());

            if (jugador.getRepresentanteId() != null) {
                ps.setInt(10, jugador.getRepresentanteId());
            } else {
                ps.setNull(10, java.sql.Types.INTEGER);
            }

            if (jugador.getSeleccionId() != null) {
                ps.setInt(11, jugador.getSeleccionId());
            } else {
                ps.setNull(11, java.sql.Types.INTEGER);
            }

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            // Cambiar aquí para capturar el mensaje de error
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public int modificar(Jugador jugador) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "UPDATE Jugador SET nombre = ?, alias = ?, foto_url = ?, fecha_nacimiento = ?, nacionalidad = ?, altura = ?, peso = ?, pie_dominante = ?, valor_mercado = ?, representante_id = ?, seleccion_id = ? WHERE id_jugador = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, jugador.getIdJugador()); // Asignamos el ID manual
            ps.setString(1, jugador.getNombre());
            ps.setString(2, jugador.getAlias());
            ps.setString(3, jugador.getFotoUrl());
            ps.setDate(4, java.sql.Date.valueOf(jugador.getFechaNacimiento()));
            ps.setString(5, jugador.getNacionalidad());
            ps.setFloat(6, jugador.getAltura());
            ps.setFloat(7, jugador.getPeso());
            ps.setString(8, jugador.getPieDominante());
            ps.setFloat(9, jugador.getValorMercado());

            if (jugador.getRepresentanteId() != null) {
                ps.setInt(10, jugador.getRepresentanteId());
            } else {
                ps.setNull(10, java.sql.Types.INTEGER);
            }

            if (jugador.getSeleccionId() != null) {
                ps.setInt(11, jugador.getSeleccionId());
            } else {
                ps.setNull(11, java.sql.Types.INTEGER);
            }

            ps.setInt(12, jugador.getIdJugador());

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            // Cambiar aquí para capturar el mensaje de error
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public int eliminarJugador(int idJugador) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "DELETE FROM Jugador WHERE id_jugador = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idJugador);

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            // Cambiar aquí para capturar el mensaje de error
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public List<Jugador> listarJugadores() throws BBDDException {
        final String query = "SELECT * FROM vista_jugador";
        List<Jugador> jugadores = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_jugador");
                String nombre = rs.getString("nombre");
                String alias = rs.getString("alias");
                String foto = rs.getString("foto_url");
                LocalDate fechaNacimiento = rs.getDate("fecha_nacimiento").toLocalDate();
                String nacionalidad = rs.getString("nacionalidad");
                float altura = rs.getFloat("altura");
                float peso = rs.getFloat("peso");
                String pieDominante = rs.getString("pie_dominante");
                float valorMercado = rs.getFloat("valor_mercado");
                Integer representanteId = rs.getObject("representante_id") != null ? rs.getInt("representante_id")
                        : null;
                Integer seleccionId = rs.getObject("seleccion_id") != null ? rs.getInt("seleccion_id") : null;

                Jugador jugador = new Jugador(id, nombre, alias, foto, fechaNacimiento, nacionalidad, altura, peso,
                        pieDominante, valorMercado, representanteId, seleccionId);
                jugadores.add(jugador);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            // Cambiar aquí para capturar el mensaje de error
            throw new BBDDException(e.getMessage());
        }
        return jugadores;
    }

    @Override
    public Jugador buscarPorId(int idJugador) throws BBDDException {
        final String query = "SELECT * FROM vista_jugador WHERE id_jugador = ?";
        Jugador jugador = null;

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idJugador);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_jugador");
                String nombre = rs.getString("nombre");
                String alias = rs.getString("alias");
                String foto = rs.getString("foto_url");
                LocalDate fechaNacimiento = rs.getDate("fecha_nacimiento").toLocalDate();
                String nacionalidad = rs.getString("nacionalidad");
                float altura = rs.getFloat("altura");
                float peso = rs.getFloat("peso");
                String pieDominante = rs.getString("pie_dominante");
                float valorMercado = rs.getFloat("valor_mercado");
                Integer representanteId = rs.getObject("representante_id") != null ? rs.getInt("representante_id")
                        : null;
                Integer seleccionId = rs.getObject("seleccion_id") != null ? rs.getInt("seleccion_id") : null;

                // Crear un objeto Jugador con los datos obtenidos
                jugador = new Jugador(id, nombre, alias, foto, fechaNacimiento, nacionalidad, altura, peso,
                        pieDominante, valorMercado, representanteId, seleccionId);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            // Cambiar aquí para capturar el mensaje de error
            throw new BBDDException(e.getMessage());
        }

        return jugador; // Puede devolver null si no se encuentra el jugador
    }

    @Override
    public int insertar(Representante representante) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int modificar(Representante representante) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public int eliminarRepresentante(int idRepresentante) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarRepresentante'");
    }

    @Override
    public List<Representante> listarRepresentantes() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarRepresentantes'");
    }

    @Override
    public int insertar(Entrenador entrenador) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int modificar(Entrenador entrenador) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public int eliminarEntrenador(int idEntrenador) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarEntrenador'");
    }

    @Override
    public List<Entrenador> listarEntrenadores() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarEntrenadores'");
    }

    @Override
    public int insertar(CategoriaPosicion categoriaPosicion) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int modificar(CategoriaPosicion categoriaPosicion) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public int eliminarCategoria(int idCategoria) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarCategoria'");
    }

    @Override
    public List<CategoriaPosicion> listarCategoriasPosicion() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarCategoriasPosicion'");
    }

    @Override
    public int insertar(Posicion posicion) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int modificar(Posicion posicion) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public int eliminarPosicion(int idPosicion) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarPosicion'");
    }

    @Override
    public List<Posicion> listarPosiciones() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarPosiciones'");
    }

    @Override
    public int insertar(Temporada temporada) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int modificar(Temporada temporada) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public int eliminarTemporada(int idTemporada) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarTemporada'");
    }

    @Override
    public List<Temporada> listarTemporadas() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTemporadas'");
    }

    @Override
    public int insertar(Seleccion seleccion) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int modificar(Seleccion seleccion) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public int eliminarSeleccion(int idSeleccion) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarSeleccion'");
    }

    @Override
    public List<Seleccion> listarSelecciones() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarSelecciones'");
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
    public int insertar(Estadio estadio) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int modificar(Estadio estadio) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public int eliminarEstadio(int idEstadio) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarEstadio'");
    }

    @Override
    public List<Estadio> listarEstadios() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarEstadios'");
    }

    @Override
    public int insertar(EquipoEstadio equipoEstadio) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int modificar(EquipoEstadio equipoEstadio) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public int eliminarEquipoEstadio(int equipoId, int estadioId) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarEquipoEstadio'");
    }

    @Override
    public List<EquipoEstadio> listarEquiposEstadio() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarEquiposEstadio'");
    }

    @Override
    public int insertar(JugadorPosicion jugadorPosicion) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int modificar(JugadorPosicion jugadorPosicion) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public int eliminarJugadorPosicion(int jugadorId, int posicionId, int temporadaId) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarJugadorPosicion'");
    }

    @Override
    public List<JugadorPosicion> listarJugadoresPosicion() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarJugadoresPosicion'");
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
    public int insertar(Noticia noticia) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int modificar(Noticia noticia) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public int eliminarNoticia(int idNoticia) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarNoticia'");
    }

    @Override
    public List<Noticia> listarNoticias() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarNoticias'");
    }

    @Override
    public int insertar(Usuario usuario) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int modificar(Usuario usuario) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public int eliminarUsuario(int idUsuario) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarUsuario'");
    }

    @Override
    public List<Usuario> listarUsuarios() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarUsuarios'");
    }

    @Override
    public int insertar(EquipoEntrenador equipoEntrenador) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int modificar(EquipoEntrenador equipoEntrenador) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public int eliminarEquipoEntrenador(int entrenadorId, int equipoId) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarEquipoEntrenador'");
    }

    @Override
    public List<EquipoEntrenador> listarEquiposEntrenador() throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarEquiposEntrenador'");
    }

    /*
     * @Override
     * public List<EstadisticasTemporada> listarTodos() throws BBDDException {
     * List<EstadisticasTemporada> estadisticas = new ArrayList<>();
     * String sql = "SELECT * FROM Estadisticas_Temporada";
     * 
     * try (PreparedStatement stmt = conn.prepareStatement(sql);
     * ResultSet rs = stmt.executeQuery()) {
     * 
     * while (rs.next()) {
     * estadisticas.add(new EstadisticasTemporada(
     * rs.getInt("jugador_id"),
     * rs.getInt("temporada_id"),
     * rs.getInt("competicion_id"),
     * rs.getInt("equipo_id"),
     * rs.getInt("goles"),
     * rs.getInt("asistencias"),
     * rs.getInt("minutos"),
     * rs.getInt("partidos_jugados"),
     * rs.getInt("amarillas"),
     * rs.getInt("rojas"),
     * rs.getInt("promedio_goles")));
     * }
     * }
     * 
     * return estadisticas;
     * }
     * 
     * @Override
     * public List<EstadisticasTemporada> buscarPorJugador(int jugadorId) throws
     * BBDDException {
     * List<EstadisticasTemporada> lista = new ArrayList<>();
     * String sql = "SELECT * FROM Estadisticas_Temporada WHERE jugador_id = ?";
     * 
     * try (PreparedStatement stmt = conn.prepareStatement(sql)) {
     * stmt.setInt(1, jugadorId);
     * try (ResultSet rs = stmt.executeQuery()) {
     * while (rs.next()) {
     * EstadisticasTemporada est = new EstadisticasTemporada(
     * rs.getInt("jugador_id"),
     * rs.getInt("temporada_id"),
     * rs.getInt("competicion_id"),
     * rs.getInt("equipo_id"),
     * rs.getInt("goles"),
     * rs.getInt("asistencias"),
     * rs.getInt("minutos"),
     * rs.getInt("partidos_jugados"),
     * rs.getInt("amarillas"),
     * rs.getInt("rojas"),
     * rs.getInt("promedio_goles"));
     * lista.add(est);
     * }
     * }
     * }
     * 
     * return lista;
     * }
     */
}
