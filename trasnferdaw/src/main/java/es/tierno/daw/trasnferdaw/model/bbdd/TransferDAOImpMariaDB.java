package es.tierno.daw.trasnferdaw.model.bbdd;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            ps.setObject(3, jugador.getFotoUrl(), java.sql.Types.VARCHAR);
            ps.setDate(4, java.sql.Date.valueOf(jugador.getFechaNacimiento()));
            ps.setString(5, jugador.getNacionalidad());
            ps.setFloat(6, jugador.getAltura());
            ps.setFloat(7, jugador.getPeso());
            ps.setString(8, jugador.getPieDominante());
            ps.setFloat(9, jugador.getValorMercado());
            ps.setObject(10, jugador.getRepresentanteId(), java.sql.Types.INTEGER);
            ps.setObject(11, jugador.getSeleccionId(), java.sql.Types.INTEGER);

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
            ps.setString(1, jugador.getNombre());
            ps.setString(2, jugador.getAlias());
            ps.setString(3, jugador.getFotoUrl());
            ps.setDate(4, java.sql.Date.valueOf(jugador.getFechaNacimiento()));
            ps.setString(5, jugador.getNacionalidad());
            ps.setFloat(6, jugador.getAltura());
            ps.setFloat(7, jugador.getPeso());
            ps.setString(8, jugador.getPieDominante());
            ps.setFloat(9, jugador.getValorMercado());
            ps.setObject(10, jugador.getRepresentanteId(), java.sql.Types.INTEGER);
            ps.setObject(11, jugador.getSeleccionId(), java.sql.Types.INTEGER);
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
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO Representante (foto_url, nombre, telefono, email, direccion) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setObject(1, representante.getFoto_url(), java.sql.Types.VARCHAR);
            ps.setString(2, representante.getNombre());
            ps.setString(3, representante.getTelefono());
            ps.setString(4, representante.getEmail());
            ps.setString(5, representante.getDireccion());

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            // Cambiar aquí para capturar el mensaje de error
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public int modificar(Representante representante) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "UPDATE Representante SET foto_url = ?, nombre = ?, telefono = ?, email = ?, direccion = ? WHERE id_representante = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setObject(1, representante.getFoto_url(), java.sql.Types.VARCHAR);
            ps.setString(2, representante.getNombre());
            ps.setString(3, representante.getTelefono());
            ps.setString(4, representante.getEmail());
            ps.setString(5, representante.getDireccion());
            ps.setInt(6, representante.getId_representante());

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public int eliminarRepresentante(int idRepresentante) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "DELETE FROM Representante WHERE id_representante = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idRepresentante);

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public List<Representante> listarRepresentantes() throws BBDDException {
        final String query = "SELECT * FROM vista_representante";
        List<Representante> representantes = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_representante");
                String foto = rs.getString("foto_url");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                String direccion = rs.getString("direccion");

                Representante representante = new Representante(id, foto, nombre, telefono, email, direccion);
                representantes.add(representante);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return representantes;
    }

    @Override
    public int insertar(Entrenador entrenador) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO Entrenador (foto_url, nombre, fecha_nacimiento, nacionalidad, experiencia) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, entrenador.getFoto_url());
            ps.setString(2, entrenador.getNombre());
            ps.setDate(3, java.sql.Date.valueOf(entrenador.getFecha_nacimiento()));
            ps.setString(4, entrenador.getNacionalidad());
            ps.setString(5, entrenador.getExperiencia());

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public int modificar(Entrenador entrenador) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "UPDATE Entrenador SET foto_url = ?, nombre = ?, fecha_nacimiento = ?, nacionalidad = ?, experiencia = ? WHERE id_entrenador = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, entrenador.getFoto_url());
            ps.setString(2, entrenador.getNombre());
            ps.setDate(3, java.sql.Date.valueOf(entrenador.getFecha_nacimiento()));
            ps.setString(4, entrenador.getNacionalidad());
            ps.setString(5, entrenador.getExperiencia());
            ps.setInt(6, entrenador.getId_entrenador());

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public int eliminarEntrenador(int idEntrenador) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "DELETE FROM Entrenador WHERE id_entrenador = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idEntrenador);

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public List<Entrenador> listarEntrenadores() throws BBDDException {
        final String query = "SELECT * FROM vista_entrenador";
        List<Entrenador> entrenadores = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_entrenador");
                String foto = rs.getString("foto_url");
                String nombre = rs.getString("nombre");
                LocalDate fechaNacimiento = rs.getDate("fecha_nacimiento") != null
                        ? rs.getDate("fecha_nacimiento").toLocalDate()
                        : null;
                String nacionalidad = rs.getString("nacionalidad");
                String experiencia = rs.getString("experiencia");

                Entrenador entrenador = new Entrenador(id, foto, nombre, fechaNacimiento, nacionalidad, experiencia);
                entrenadores.add(entrenador);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return entrenadores;
    }

    @Override
    public List<CategoriaPosicion> listarCategoriasPosicion() throws BBDDException {
        final String query = "SELECT * FROM vista_categoria_posicion";
        List<CategoriaPosicion> categorias = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_categoria");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");

                CategoriaPosicion categoria = new CategoriaPosicion(id, nombre, descripcion);
                categorias.add(categoria);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return categorias;
    }

    @Override
    public List<Posicion> listarPosiciones() throws BBDDException {
        final String query = "SELECT * FROM vista_posicion";
        List<Posicion> posiciones = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_posicion");
                String nombre = rs.getString("nombre");
                Integer idCategoria = rs.getObject("id_categoria") != null ? rs.getInt("id_categoria") : null;
                String descripcion = rs.getString("descripcion");

                Posicion posicion = new Posicion(id, nombre, idCategoria, descripcion);
                posiciones.add(posicion);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return posiciones;
    }

    @Override
    public List<Temporada> listarTemporadas() throws BBDDException {
        final String query = "SELECT * FROM vista_temporada";
        List<Temporada> temporadas = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_temporada");
                String nombre = rs.getString("nombre");
                LocalDate fechaInicio = rs.getDate("fecha_inicio") != null ? rs.getDate("fecha_inicio").toLocalDate()
                        : null;
                LocalDate fechaFin = rs.getDate("fecha_fin") != null ? rs.getDate("fecha_fin").toLocalDate() : null;
                String descripcion = rs.getString("descripcion");

                Temporada temporada = new Temporada(id, nombre, fechaInicio, fechaFin, descripcion);
                temporadas.add(temporada);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return temporadas;
    }

    @Override
    public int insertar(Seleccion seleccion) throws BBDDException {
        int resultado = 0;
        final String sql = "INSERT INTO Seleccion (logo_url, nombre, pais, federacion, fecha_fundacion, ranking, entrenador_id, capitan_id) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setObject(1, seleccion.getLogoUrl(), java.sql.Types.VARCHAR);
            ps.setString(2, seleccion.getNombre());
            ps.setString(3, seleccion.getPais());
            ps.setString(4, seleccion.getFederacion());
            ps.setDate(5, java.sql.Date.valueOf(seleccion.getFechaFundacion()));
            ps.setInt(6, seleccion.getRanking());
            ps.setObject(7, seleccion.getEntrenadorId(), java.sql.Types.INTEGER);
            ps.setObject(8, seleccion.getCapitanId(), java.sql.Types.INTEGER);

            resultado = ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return resultado;
    }

    @Override
    public int modificar(Seleccion seleccion) throws BBDDException {
        int resultado = 0;
        final String sql = "UPDATE Seleccion SET logo_url = ?, nombre = ?, pais = ?, federacion = ?, fecha_fundacion = ?, ranking = ?, entrenador_id = ?, capitan_id = ? WHERE id_seleccion = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setObject(1, seleccion.getLogoUrl(), java.sql.Types.VARCHAR);
            ps.setString(2, seleccion.getNombre());
            ps.setString(3, seleccion.getPais());
            ps.setString(4, seleccion.getFederacion());
            ps.setDate(5, java.sql.Date.valueOf(seleccion.getFechaFundacion()));
            ps.setInt(6, seleccion.getRanking());
            ps.setObject(7, seleccion.getEntrenadorId(), java.sql.Types.INTEGER);
            ps.setObject(8, seleccion.getCapitanId(), java.sql.Types.INTEGER);
            ps.setInt(9, seleccion.getIdSeleccion());

            resultado = ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return resultado;
    }

    @Override
    public int eliminarSeleccion(int idSeleccion) throws BBDDException {
        int resultado = 0;
        final String sql = "DELETE FROM Seleccion WHERE id_seleccion = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idSeleccion);

            resultado = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return resultado;
    }

    @Override
    public List<Seleccion> listarSelecciones() throws BBDDException {
        final String query = "SELECT * FROM Seleccion";
        List<Seleccion> selecciones = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_seleccion");
                String logoUrl = rs.getString("logo_url");
                String nombre = rs.getString("nombre");
                String pais = rs.getString("pais");
                String federacion = rs.getString("federacion");
                LocalDate fechaFundacion = rs.getDate("fecha_fundacion") != null
                        ? rs.getDate("fecha_fundacion").toLocalDate()
                        : null;
                Integer ranking = rs.getObject("ranking") != null ? rs.getInt("ranking") : null;
                Integer entrenadorId = rs.getObject("entrenador_id") != null ? rs.getInt("entrenador_id") : null;
                Integer capitanId = rs.getObject("capitan_id") != null ? rs.getInt("capitan_id") : null;

                Seleccion seleccion = new Seleccion(id, logoUrl, nombre, pais, federacion,
                        fechaFundacion, ranking, entrenadorId, capitanId);

                selecciones.add(seleccion);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return selecciones;
    }

    @Override
    public int insertar(Equipo equipo) throws BBDDException {
        int resultado = 0;
        final String sql = "INSERT INTO Equipo (nombre, ciudad, pais, escudo_url, descripcion, anio_fundacion, presupuesto, propietario, entrenador_id) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, equipo.getNombre());
            ps.setString(2, equipo.getCiudad());
            ps.setString(3, equipo.getPais());
            ps.setObject(4, equipo.getEscudoUrl(), java.sql.Types.VARCHAR);
            ps.setString(5, equipo.getDescripcion());
            ps.setInt(6, equipo.getAnioFundacion());
            ps.setFloat(7, equipo.getPresupuesto());
            ps.setObject(8, equipo.getPropietario(), java.sql.Types.INTEGER);
            ps.setObject(9, equipo.getEntrenadorId(), java.sql.Types.INTEGER);

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return resultado;
    }

    @Override
    public int modificar(Equipo equipo) throws BBDDException {
        int resultado = 0;
        final String sql = "UPDATE Equipo SET nombre = ?, ciudad = ?, pais = ?, escudo_url = ?, descripcion = ?, " +
                "anio_fundacion = ?, presupuesto = ?, propietario = ?, entrenador_id = ? WHERE id_equipo = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, equipo.getNombre());
            ps.setString(2, equipo.getCiudad());
            ps.setString(3, equipo.getPais());
            ps.setObject(4, equipo.getEscudoUrl(), java.sql.Types.VARCHAR);
            ps.setString(5, equipo.getDescripcion());
            ps.setInt(6, equipo.getAnioFundacion());
            ps.setFloat(7, equipo.getPresupuesto());
            ps.setObject(8, equipo.getPropietario(), java.sql.Types.INTEGER);
            ps.setObject(9, equipo.getEntrenadorId(), java.sql.Types.INTEGER);
            ps.setInt(10, equipo.getIdEquipo());

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return resultado;
    }

    @Override
    public int eliminarEquipo(int idEquipo) throws BBDDException {
        int resultado = 0;
        final String sql = "DELETE FROM Equipo WHERE id_equipo = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEquipo);
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return resultado;
    }

    @Override
    public List<Equipo> listarEquipos() throws BBDDException {
        List<Equipo> lista = new ArrayList<>();
        final String sql = "SELECT * FROM Equipo";

        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Equipo equipo = new Equipo(
                        rs.getInt("id_equipo"),
                        rs.getString("nombre"),
                        rs.getString("ciudad"),
                        rs.getString("pais"),
                        rs.getString("escudo_url"),
                        rs.getString("descripcion"),
                        rs.getInt("anio_fundacion"),
                        rs.getFloat("presupuesto"),
                        rs.getString("propietario"),
                        rs.getObject("entrenador_id") != null ? rs.getInt("entrenador_id") : null);

                lista.add(equipo);
            }

        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return lista;
    }

    @Override
    public int insertar(Competicion competicion) throws BBDDException {
        int resultado = 0;
        final String sql = "INSERT INTO Competicion (nombre, pais, tipo, foto_url, numero_equipos, anio_creacion) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, competicion.getNombre());
            ps.setString(2, competicion.getPais());
            ps.setString(3, competicion.getTipo());
            ps.setObject(4, competicion.getFotoUrl(), java.sql.Types.VARCHAR);
            ps.setInt(5, competicion.getNumeroEquipos());
            ps.setInt(6, competicion.getAnioCreacion());

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return resultado;
    }

    @Override
    public int modificar(Competicion competicion) throws BBDDException {
        int resultado = 0;
        final String sql = "UPDATE Competicion SET nombre = ?, pais = ?, tipo = ?, foto_url = ?, numero_equipos = ?, anio_creacion = ? "
                +
                "WHERE id_competicion = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, competicion.getNombre());
            ps.setString(2, competicion.getPais());
            ps.setString(3, competicion.getTipo());
            ps.setObject(4, competicion.getFotoUrl(), java.sql.Types.VARCHAR);
            ps.setInt(5, competicion.getNumeroEquipos());
            ps.setInt(6, competicion.getAnioCreacion());
            ps.setInt(7, competicion.getIdCompeticion());

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return resultado;
    }

    @Override
    public int eliminarCompeticion(int idCompeticion) throws BBDDException {
        int resultado = 0;
        final String sql = "DELETE FROM Competicion WHERE id_competicion = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCompeticion);
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return resultado;
    }

    @Override
    public List<Competicion> listarCompeticiones() throws BBDDException {
        List<Competicion> lista = new ArrayList<>();
        final String sql = "SELECT * FROM Competicion";

        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Competicion competicion = new Competicion(
                        rs.getInt("id_competicion"),
                        rs.getString("nombre"),
                        rs.getString("pais"),
                        rs.getString("tipo"),
                        rs.getString("foto_url"),
                        rs.getInt("numero_equipos"),
                        rs.getInt("anio_creacion"));

                lista.add(competicion);
            }

        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return lista;
    }

    @Override
    public int insertar(EquipoCompeticion ec) throws BBDDException {
        int resultado = 0;
        final String sql = "INSERT INTO Equipo_Competicion (equipo_id, competicion_id, temporada_id, rango) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, ec.getEquipoId(), java.sql.Types.INTEGER);
            ps.setObject(2, ec.getCompeticionId(), java.sql.Types.INTEGER);
            ps.setObject(3, ec.getTemporadaId(), java.sql.Types.INTEGER);
            ps.setInt(4, ec.getRango());

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return resultado;
    }

    @Override
    public int modificar(EquipoCompeticion ec) throws BBDDException {
        int resultado = 0;
        final String sql = "UPDATE Equipo_Competicion SET rango = ? WHERE equipo_id = ? AND competicion_id = ? AND temporada_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ec.getRango());
            ps.setInt(2, ec.getEquipoId());
            ps.setInt(3, ec.getCompeticionId());
            ps.setInt(4, ec.getTemporadaId());

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return resultado;
    }

    @Override
    public int eliminarEquipoCompeticion(int equipoId, int competicionId, int temporadaId) throws BBDDException {
        int resultado = 0;
        final String sql = "DELETE FROM Equipo_Competicion WHERE equipo_id = ? AND competicion_id = ? AND temporada_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, equipoId);
            ps.setInt(2, competicionId);
            ps.setInt(3, temporadaId);

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return resultado;
    }

    @Override
    public List<EquipoCompeticion> listarEquiposCompeticion() throws BBDDException {
        List<EquipoCompeticion> lista = new ArrayList<>();
        final String sql = "SELECT * FROM Equipo_Competicion";

        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EquipoCompeticion ec = new EquipoCompeticion(
                        rs.getInt("equipo_id"),
                        rs.getInt("competicion_id"),
                        rs.getInt("temporada_id"),
                        rs.getInt("rango"));

                lista.add(ec);
            }

        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return lista;
    }

    @Override
    public int insertar(Contrato contrato) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO Contrato (jugador_id, equipo_id, fecha_inicio, fecha_fin, salario, bonificaciones, tipo_contrato, clausula_rescision) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setObject(1, contrato.getJugadorId(), java.sql.Types.INTEGER);
            ps.setObject(2, contrato.getEquipoId(), java.sql.Types.INTEGER);
            ps.setDate(3, java.sql.Date.valueOf(contrato.getFechaInicio()));
            ps.setDate(4, java.sql.Date.valueOf(contrato.getFechaFin()));
            ps.setFloat(5, contrato.getSalario());
            ps.setFloat(6, contrato.getBonificaciones());
            ps.setString(7, contrato.getTipoContrato());
            ps.setFloat(8, contrato.getClausulaRescision());

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public int modificar(Contrato contrato) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "UPDATE Contrato SET jugador_id = ?, equipo_id = ?, fecha_inicio = ?, fecha_fin = ?, salario = ?, bonificaciones = ?, tipo_contrato = ?, clausula_rescision = ? WHERE id_contrato = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setObject(1, contrato.getJugadorId(), java.sql.Types.INTEGER);
            ps.setObject(2, contrato.getEquipoId(), java.sql.Types.INTEGER);
            ps.setDate(3, java.sql.Date.valueOf(contrato.getFechaInicio()));
            ps.setDate(4, java.sql.Date.valueOf(contrato.getFechaFin()));
            ps.setFloat(5, contrato.getSalario());
            ps.setFloat(6, contrato.getBonificaciones());
            ps.setString(7, contrato.getTipoContrato());
            ps.setFloat(8, contrato.getClausulaRescision());
            ps.setInt(9, contrato.getIdContrato());

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public int eliminarContrato(int idContrato) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "DELETE FROM Contrato WHERE id_contrato = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idContrato);

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public List<Contrato> listarContratos() throws BBDDException {
        final String query = "SELECT * FROM Contrato";
        List<Contrato> contratos = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idContrato = rs.getInt("id_contrato");
                int jugadorId = rs.getInt("jugador_id");
                int equipoId = rs.getInt("equipo_id");
                LocalDate fechaInicio = rs.getDate("fecha_inicio").toLocalDate();
                LocalDate fechaFin = rs.getDate("fecha_fin").toLocalDate();
                float salario = rs.getFloat("salario");
                float bonificaciones = rs.getFloat("bonificaciones");
                String tipoContrato = rs.getString("tipo_contrato");
                float clausulaRescision = rs.getFloat("clausula_rescision");

                Contrato contrato = new Contrato(idContrato, jugadorId, equipoId, fechaInicio, fechaFin, salario,
                        bonificaciones, tipoContrato, clausulaRescision);
                contratos.add(contrato);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return contratos;
    }

    @Override
    public int insertar(Estadio estadio) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO Estadio (foto_url, nombre, capacidad, ubicacion, anio_inauguracion) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setObject(1, estadio.getFotoUrl(), java.sql.Types.VARCHAR);
            ps.setString(2, estadio.getNombre());
            ps.setInt(3, estadio.getCapacidad());
            ps.setString(4, estadio.getUbicacion());
            ps.setInt(5, estadio.getAnioInauguracion());

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public int modificar(Estadio estadio) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "UPDATE Estadio SET foto_url = ?, nombre = ?, capacidad = ?, ubicacion = ?, anio_inauguracion = ? WHERE id_estadio = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setObject(1, estadio.getFotoUrl(), java.sql.Types.VARCHAR);
            ps.setString(2, estadio.getNombre());
            ps.setInt(3, estadio.getCapacidad());
            ps.setString(4, estadio.getUbicacion());
            ps.setInt(5, estadio.getAnioInauguracion());
            ps.setInt(6, estadio.getIdEstadio());

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public int eliminarEstadio(int idEstadio) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "DELETE FROM Estadio WHERE id_estadio = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idEstadio);

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public List<Estadio> listarEstadios() throws BBDDException {
        final String query = "SELECT * FROM Estadio";
        List<Estadio> estadios = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idEstadio = rs.getInt("id_estadio");
                String fotoUrl = rs.getString("foto_url");
                String nombre = rs.getString("nombre");
                int capacidad = rs.getInt("capacidad");
                String ubicacion = rs.getString("ubicacion");
                int anioInauguracion = rs.getInt("anio_inauguracion");

                Estadio estadio = new Estadio(idEstadio, fotoUrl, nombre, capacidad, ubicacion, anioInauguracion);
                estadios.add(estadio);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return estadios;
    }

    @Override
    public int insertar(EquipoEstadio equipoEstadio) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO Equipo_Estadio (equipo_id, estadio_id, fecha_inicio, fecha_fin) "
                + "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, equipoEstadio.getEquipoId());
            ps.setInt(2, equipoEstadio.getEstadioId());
            ps.setDate(3, java.sql.Date.valueOf(equipoEstadio.getFechaInicio()));
            ps.setObject(4, equipoEstadio.getFechaFin(), java.sql.Types.DATE);

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public int modificar(EquipoEstadio equipoEstadio) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "UPDATE Equipo_Estadio SET fecha_inicio = ?, fecha_fin = ? WHERE equipo_id = ? AND estadio_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDate(1, java.sql.Date.valueOf(equipoEstadio.getFechaInicio()));
            ps.setObject(2, equipoEstadio.getFechaFin(), java.sql.Types.DATE);
            ps.setInt(3, equipoEstadio.getEquipoId());
            ps.setInt(4, equipoEstadio.getEstadioId());

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public int eliminarEquipoEstadio(int equipoId, int estadioId) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "DELETE FROM Equipo_Estadio WHERE equipo_id = ? AND estadio_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, equipoId);
            ps.setInt(2, estadioId);

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public List<EquipoEstadio> listarEquiposEstadio() throws BBDDException {
        final String query = "SELECT * FROM Equipo_Estadio";
        List<EquipoEstadio> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int equipoId = rs.getInt("equipo_id");
                int estadioId = rs.getInt("estadio_id");
                LocalDate fechaInicio = rs.getDate("fecha_inicio").toLocalDate();
                LocalDate fechaFin = rs.getObject("fecha_fin") != null ? rs.getDate("fecha_fin").toLocalDate() : null;

                EquipoEstadio relacion = new EquipoEstadio(equipoId, estadioId, fechaInicio, fechaFin);
                lista.add(relacion);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return lista;
    }

    @Override
    public int insertar(JugadorPosicion jp) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO Jugador_Posicion (jugador_id, posicion_id, temporada_id, es_principal) "
                + "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, jp.getJugadorId());
            ps.setInt(2, jp.getPosicionId());
            ps.setInt(3, jp.getTemporadaId());
            ps.setBoolean(4, jp.isEsPrincipal());

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public int modificar(JugadorPosicion jp) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "UPDATE Jugador_Posicion SET es_principal = ? WHERE jugador_id = ? AND posicion_id = ? AND temporada_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setBoolean(1, jp.isEsPrincipal());
            ps.setInt(2, jp.getJugadorId());
            ps.setInt(3, jp.getPosicionId());
            ps.setInt(4, jp.getTemporadaId());

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public int eliminarJugadorPosicion(int jugadorId, int posicionId, int temporadaId) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "DELETE FROM Jugador_Posicion WHERE jugador_id = ? AND posicion_id = ? AND temporada_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, jugadorId);
            ps.setInt(2, posicionId);
            ps.setInt(3, temporadaId);

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public List<JugadorPosicion> listarJugadoresPosicion() throws BBDDException {
        final String query = "SELECT * FROM Jugador_Posicion";
        List<JugadorPosicion> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int jugadorId = rs.getInt("jugador_id");
                int posicionId = rs.getInt("posicion_id");
                int temporadaId = rs.getInt("temporada_id");
                boolean esPrincipal = rs.getBoolean("es_principal");

                JugadorPosicion jp = new JugadorPosicion(jugadorId, posicionId, temporadaId, esPrincipal);
                lista.add(jp);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return lista;
    }

    @Override
    public int insertar(EstadisticasTemporada est) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO Estadisticas_Temporada "
                + "(jugador_id, temporada_id, competicion_id, equipo_id, goles, asistencias, minutos, partidos_jugados, amarillas, rojas, promedio_goles) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, est.getJugadorId());
            ps.setInt(2, est.getTemporadaId());
            ps.setInt(3, est.getCompeticionId());
            ps.setInt(4, est.getEquipoId());
            ps.setInt(5, est.getGoles());
            ps.setInt(6, est.getAsistencias());
            ps.setInt(7, est.getMinutos());
            ps.setInt(8, est.getPartidosJugados());
            ps.setInt(9, est.getAmarillas());
            ps.setInt(10, est.getRojas());
            ps.setFloat(11, est.getPromedioGoles());

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return numRegistrosActualizados;
    }

    @Override
    public int modificar(EstadisticasTemporada est) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "UPDATE Estadisticas_Temporada SET goles = ?, asistencias = ?, minutos = ?, partidos_jugados = ?, "
                + "amarillas = ?, rojas = ?, promedio_goles = ? "
                + "WHERE jugador_id = ? AND temporada_id = ? AND competicion_id = ? AND equipo_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, est.getGoles());
            ps.setInt(2, est.getAsistencias());
            ps.setInt(3, est.getMinutos());
            ps.setInt(4, est.getPartidosJugados());
            ps.setInt(5, est.getAmarillas());
            ps.setInt(6, est.getRojas());
            ps.setFloat(7, est.getPromedioGoles());

            ps.setInt(8, est.getJugadorId());
            ps.setInt(9, est.getTemporadaId());
            ps.setInt(10, est.getCompeticionId());
            ps.setInt(11, est.getEquipoId());

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return numRegistrosActualizados;
    }

    @Override
    public int eliminarEstadisticasTemporada(int jugadorId, int temporadaId, int competicionId, int equipoId)
            throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "DELETE FROM Estadisticas_Temporada WHERE jugador_id = ? AND temporada_id = ? AND competicion_id = ? AND equipo_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, jugadorId);
            ps.setInt(2, temporadaId);
            ps.setInt(3, competicionId);
            ps.setInt(4, equipoId);

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public List<EstadisticasTemporada> listarEstadisticasTemporada() throws BBDDException {
        final String query = "SELECT * FROM Estadisticas_Temporada";
        List<EstadisticasTemporada> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int jugadorId = rs.getInt("jugador_id");
                int temporadaId = rs.getInt("temporada_id");
                int competicionId = rs.getInt("competicion_id");
                int equipoId = rs.getInt("equipo_id");
                int goles = rs.getInt("goles");
                int asistencias = rs.getInt("asistencias");
                int minutos = rs.getInt("minutos");
                int partidos = rs.getInt("partidos_jugados");
                int amarillas = rs.getInt("amarillas");
                int rojas = rs.getInt("rojas");
                int promedioGoles = rs.getInt("promedio_goles");

                EstadisticasTemporada est = new EstadisticasTemporada(jugadorId, temporadaId, competicionId, equipoId,
                        goles, asistencias, minutos, partidos, amarillas, rojas, promedioGoles);
                lista.add(est);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return lista;
    }

    @Override
    public EstadisticasTemporada buscarEstadisticasTotalesPorJugador(int jugadorId) throws BBDDException {
        final String query = """
            SELECT jugador_id,
                   SUM(goles) AS goles,
                   SUM(asistencias) AS asistencias,
                   SUM(minutos) AS minutos,
                   SUM(partidos_jugados) AS partidos,
                   SUM(amarillas) AS amarillas,
                   SUM(rojas) AS rojas
            FROM Estadisticas_Temporada
            WHERE jugador_id = ?
            GROUP BY jugador_id
        """;
    
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, jugadorId);
            ResultSet rs = ps.executeQuery();
    
            if (rs.next()) {
                int goles = rs.getInt("goles");
                int asistencias = rs.getInt("asistencias");
                int minutos = rs.getInt("minutos");
                int partidos = rs.getInt("partidos");
                int amarillas = rs.getInt("amarillas");
                int rojas = rs.getInt("rojas");
    
                int promedioGoles = (goles > 0) ? (minutos / goles) : 0;
    
                EstadisticasTemporada est = new EstadisticasTemporada(
                        jugadorId, 0, 0, 0, goles, asistencias, minutos, partidos, amarillas, rojas, promedioGoles);
    
                rs.close();
                ps.close();
                return est;
            }
    
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException("Error al obtener estadísticas totales del jugador: " + e.getMessage());
        }
    
        return null;
    }

    
    @Override
public EstadisticasTemporada buscarEstadisticasPorTemporada(int jugadorId, int temporadaId) throws BBDDException {
    final String query = """
        SELECT jugador_id, temporada_id,
               SUM(goles) AS goles,
               SUM(asistencias) AS asistencias,
               SUM(minutos) AS minutos,
               SUM(partidos_jugados) AS partidos,
               SUM(amarillas) AS amarillas,
               SUM(rojas) AS rojas
        FROM Estadisticas_Temporada
        WHERE jugador_id = ? AND temporada_id = ?
        GROUP BY jugador_id, temporada_id
    """;

    try {
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, jugadorId);
        ps.setInt(2, temporadaId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int goles = rs.getInt("goles");
            int asistencias = rs.getInt("asistencias");
            int minutos = rs.getInt("minutos");
            int partidos = rs.getInt("partidos");
            int amarillas = rs.getInt("amarillas");
            int rojas = rs.getInt("rojas");

            int promedioGoles = (goles > 0) ? (minutos / goles) : 0;

            EstadisticasTemporada est = new EstadisticasTemporada(
                    jugadorId, temporadaId, 0, 0, goles, asistencias, minutos, partidos, amarillas, rojas, promedioGoles);

            rs.close();
            ps.close();
            return est;
        }

        rs.close();
        ps.close();
    } catch (Exception e) {
        throw new BBDDException("Error al obtener estadísticas por temporada: " + e.getMessage());
    }

    return null;
}

    

    @Override
    public int insertar(Traspaso traspaso) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO Traspaso (jugador_id, equipo_origen_id, equipo_destino_id, temporada_id, fecha_traspaso, cantidad, clausula_traspaso, bonificaciones, tipo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, traspaso.getJugadorId());
            ps.setInt(2, traspaso.getEquipoOrigenId());
            ps.setInt(3, traspaso.getEquipoDestinoId());
            ps.setInt(4, traspaso.getTemporadaId());
            ps.setDate(5, java.sql.Date.valueOf(traspaso.getFechaTraspaso()));
            ps.setFloat(6, traspaso.getCantidad());
            ps.setFloat(7, traspaso.getClausulaTraspaso());
            ps.setFloat(8, traspaso.getBonificaciones());
            ps.setString(9, traspaso.getTipo());

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public int modificar(Traspaso traspaso) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "UPDATE Traspaso SET jugador_id = ?, equipo_origen_id = ?, equipo_destino_id = ?, temporada_id = ?, fecha_traspaso = ?, cantidad = ?, clausula_traspaso = ?, bonificaciones = ?, tipo = ? "
                + "WHERE id_traspaso = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, traspaso.getJugadorId());
            ps.setInt(2, traspaso.getEquipoOrigenId());
            ps.setInt(3, traspaso.getEquipoDestinoId());
            ps.setInt(4, traspaso.getTemporadaId());
            ps.setDate(5, java.sql.Date.valueOf(traspaso.getFechaTraspaso()));
            ps.setFloat(6, traspaso.getCantidad());
            ps.setFloat(7, traspaso.getClausulaTraspaso());
            ps.setFloat(8, traspaso.getBonificaciones());
            ps.setString(9, traspaso.getTipo());
            ps.setInt(10, traspaso.getIdTraspaso());

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public int eliminarTraspaso(int idTraspaso) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "DELETE FROM Traspaso WHERE id_traspaso = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idTraspaso);

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return numRegistrosActualizados;
    }

    @Override
    public List<Traspaso> listarTraspasos() throws BBDDException {
        final String query = "SELECT * FROM Traspaso";
        List<Traspaso> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_traspaso");
                int jugadorId = rs.getInt("jugador_id");
                int equipoOrigenId = rs.getInt("equipo_origen_id");
                int equipoDestinoId = rs.getInt("equipo_destino_id");
                int temporadaId = rs.getInt("temporada_id");
                LocalDate fechaTraspaso = rs.getDate("fecha_traspaso").toLocalDate();
                float cantidad = rs.getFloat("cantidad");
                float clausula = rs.getFloat("clausula_traspaso");
                float bonificaciones = rs.getFloat("bonificaciones");
                String tipo = rs.getString("tipo");

                Traspaso traspaso = new Traspaso(id, jugadorId, equipoOrigenId, equipoDestinoId, temporadaId,
                        fechaTraspaso,
                        cantidad, clausula, bonificaciones, tipo);
                lista.add(traspaso);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return lista;
    }

    @Override
    public int insertar(ValorMercadoHistorial historial) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO Valor_Mercado_Historial (jugador_id, fecha, valor_mercado, motivo) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, historial.getJugadorId());
            ps.setDate(2, java.sql.Date.valueOf(historial.getFecha()));
            ps.setFloat(3, historial.getValorMercado());
            ps.setString(4, historial.getMotivo());

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return numRegistrosActualizados;
    }

    @Override
    public int modificar(ValorMercadoHistorial historial) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "UPDATE Valor_Mercado_Historial SET jugador_id = ?, fecha = ?, valor_mercado = ?, motivo = ? WHERE id_historial = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, historial.getJugadorId());
            ps.setDate(2, java.sql.Date.valueOf(historial.getFecha()));
            ps.setFloat(3, historial.getValorMercado());
            ps.setString(4, historial.getMotivo());
            ps.setInt(5, historial.getIdHistorial());

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return numRegistrosActualizados;
    }

    @Override
    public int eliminarValorMercadoHistorial(int idHistorial) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "DELETE FROM Valor_Mercado_Historial WHERE id_historial = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idHistorial);

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return numRegistrosActualizados;
    }

    @Override
    public List<ValorMercadoHistorial> listarValorMercadoHistorial() throws BBDDException {
        final String query = "SELECT * FROM Valor_Mercado_Historial";
        List<ValorMercadoHistorial> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idHistorial = rs.getInt("id_historial");
                int jugadorId = rs.getInt("jugador_id");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                float valorMercado = rs.getFloat("valor_mercado");
                String motivo = rs.getString("motivo");

                ValorMercadoHistorial historial = new ValorMercadoHistorial(idHistorial, jugadorId, fecha, valorMercado,
                        motivo);
                lista.add(historial);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return lista;
    }

    @Override
    public int insertar(Noticia noticia) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO Noticia (jugador_id, equipo_id, usuario_id, titulo, contenido, foto_url, categoria, fecha) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setObject(1, noticia.getJugadorId(), java.sql.Types.INTEGER);
            ps.setObject(2, noticia.getEquipoId(), java.sql.Types.INTEGER);
            ps.setInt(3, noticia.getUsuarioId());
            ps.setString(4, noticia.getTitulo());
            ps.setString(5, noticia.getContenido());
            ps.setString(6, noticia.getFotoUrl());
            ps.setString(7, noticia.getCategoria());
            ps.setDate(8, java.sql.Date.valueOf(noticia.getFecha()));

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return numRegistrosActualizados;
    }

    @Override
    public int modificar(Noticia noticia) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "UPDATE Noticia SET jugador_id = ?, equipo_id = ?, usuario_id = ?, titulo = ?, contenido = ?, foto_url = ?, categoria = ?, fecha = ? "
                + "WHERE id_noticia = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setObject(1, noticia.getJugadorId(), java.sql.Types.INTEGER);
            ps.setObject(2, noticia.getEquipoId(), java.sql.Types.INTEGER);
            ps.setInt(3, noticia.getUsuarioId());
            ps.setString(4, noticia.getTitulo());
            ps.setString(5, noticia.getContenido());
            ps.setString(6, noticia.getFotoUrl());
            ps.setString(7, noticia.getCategoria());
            ps.setDate(8, java.sql.Date.valueOf(noticia.getFecha()));
            ps.setInt(9, noticia.getIdNoticia());

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return numRegistrosActualizados;
    }

    @Override
    public int eliminarNoticia(int idNoticia) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "DELETE FROM Noticia WHERE id_noticia = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idNoticia);

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return numRegistrosActualizados;
    }

    @Override
    public List<Noticia> listarNoticias() throws BBDDException {
        final String query = "SELECT * FROM Noticia";
        List<Noticia> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_noticia");
                Integer jugadorId = rs.getObject("jugador_id") != null ? rs.getInt("jugador_id") : null;
                Integer equipoId = rs.getObject("equipo_id") != null ? rs.getInt("equipo_id") : null;
                int usuarioId = rs.getInt("usuario_id");
                String titulo = rs.getString("titulo");
                String contenido = rs.getString("contenido");
                String fotoUrl = rs.getString("foto_url");
                String categoria = rs.getString("categoria");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();

                Noticia noticia = new Noticia(id, jugadorId, equipoId, usuarioId, titulo, contenido, fotoUrl, categoria,
                        fecha);
                lista.add(noticia);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return lista;
    }

    @Override
    public int insertar(Usuario usuario) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO Usuario (nombre, email, contraseña, rol, fecha_registro) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getContrasena());
            ps.setString(4, usuario.getRol());
            ps.setDate(5, java.sql.Date.valueOf(usuario.getFechaRegistro()));

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return numRegistrosActualizados;
    }

    @Override
    public int modificar(Usuario usuario) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "UPDATE Usuario SET nombre = ?, email = ?, contraseña = ?, rol = ?, fecha_registro = ? WHERE id_usuario = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getContrasena());
            ps.setString(4, usuario.getRol());
            ps.setDate(5, java.sql.Date.valueOf(usuario.getFechaRegistro()));
            ps.setInt(6, usuario.getIdUsuario());

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return numRegistrosActualizados;
    }

    @Override
    public int eliminarUsuario(int idUsuario) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "DELETE FROM Usuario WHERE id_usuario = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return numRegistrosActualizados;
    }

    @Override
    public List<Usuario> listarUsuarios() throws BBDDException {
        final String query = "SELECT * FROM Usuario";
        List<Usuario> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String contrasena = rs.getString("contraseña");
                String rol = rs.getString("rol");
                LocalDate fechaRegistro = rs.getDate("fecha_registro").toLocalDate();

                Usuario usuario = new Usuario(id, nombre, email, contrasena, rol, fechaRegistro);
                lista.add(usuario);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return lista;
    }

    @Override
    public int insertar(EquipoEntrenador relacion) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO Equipo_Entrenador (entrenador_id, equipo_id, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, relacion.getEntrenadorId());
            ps.setInt(2, relacion.getEquipoId());
            ps.setDate(3, java.sql.Date.valueOf(relacion.getFechaInicio()));
            ps.setObject(4, relacion.getFechaFin(), java.sql.Types.DATE);

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return numRegistrosActualizados;
    }

    @Override
    public int modificar(EquipoEntrenador relacion) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "UPDATE Equipo_Entrenador SET entrenador_id = ?, equipo_id = ?, fecha_inicio = ?, fecha_fin = ? WHERE id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, relacion.getEntrenadorId());
            ps.setInt(2, relacion.getEquipoId());
            ps.setDate(3, java.sql.Date.valueOf(relacion.getFechaInicio()));
            ps.setObject(4, relacion.getFechaFin(), java.sql.Types.DATE);

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return numRegistrosActualizados;
    }

    @Override
    public int eliminarEquipoEntrenador(int entrenadorId, int equipoId) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "DELETE FROM Equipo_Entrenador WHERE id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, entrenadorId);

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return numRegistrosActualizados;
    }

    @Override
    public List<EquipoEntrenador> listarEquiposEntrenador() throws BBDDException {
        final String query = "SELECT * FROM Equipo_Entrenador";
        List<EquipoEntrenador> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int entrenadorId = rs.getInt("entrenador_id");
                int equipoId = rs.getInt("equipo_id");
                LocalDate fechaInicio = rs.getDate("fecha_inicio").toLocalDate();
                LocalDate fechaFin = rs.getObject("fecha_fin") != null ? rs.getDate("fecha_fin").toLocalDate() : null;

                EquipoEntrenador relacion = new EquipoEntrenador(entrenadorId, equipoId, fechaInicio, fechaFin);
                lista.add(relacion);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return lista;
    }

}
