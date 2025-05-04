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
            ps.setString(4, equipo.getEscudoUrl());
            ps.setString(5, equipo.getDescripcion());
            ps.setInt(6, equipo.getAnioFundacion());
            ps.setFloat(7, equipo.getPresupuesto());
            ps.setString(8, equipo.getPropietario());

            if (equipo.getEntrenadorId() != null) {
                ps.setInt(9, equipo.getEntrenadorId());
            } else {
                ps.setNull(9, java.sql.Types.INTEGER);
            }

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
            ps.setString(4, competicion.getFotoUrl());
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
            ps.setString(4, competicion.getFotoUrl());
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
            ps.setInt(1, ec.getEquipoId());
            ps.setInt(2, ec.getCompeticionId());
            ps.setInt(3, ec.getTemporadaId());
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
    public List<EstadisticasTemporada> buscarPorJugador(int jugadorId) throws BBDDException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorJugador'");
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

}
