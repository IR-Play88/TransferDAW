package es.tierno.daw.trasnferdaw.model.bbdd;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import es.tierno.daw.trasnferdaw.model.entities.Competicion;
import es.tierno.daw.trasnferdaw.model.entities.Contrato;
import es.tierno.daw.trasnferdaw.model.entities.Equipo;
import es.tierno.daw.trasnferdaw.model.entities.EquipoCompeticion;
import es.tierno.daw.trasnferdaw.model.entities.EstadisticasTemporada;
import es.tierno.daw.trasnferdaw.model.entities.Jugador;
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
          // Registro manual del driver
    Class.forName("org.mariadb.jdbc.Driver");
        conn = DriverManager.getConnection(String.format(URL, DATABASE_NAME, DATABASE_USER, DATABASE_PASS));
    }

    @Override
    public int insertar(Jugador jugador) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO jugador (nombre, alias,  fecha_nacimiento, nacionalidad, altura, peso, pie_dominante, valor_mercado, posicion, representante_nombre, seleccion_nombre) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, jugador.getNombre());
            ps.setString(2, jugador.getAlias());
            ps.setDate(3, java.sql.Date.valueOf(jugador.getFechaNacimiento()));
            ps.setString(4, jugador.getNacionalidad());
            ps.setFloat(5, jugador.getAltura());
            ps.setFloat(6, jugador.getPeso());
            ps.setString(7, jugador.getPieDominante());
            ps.setFloat(8, jugador.getValorMercado());
            ps.setString(9, jugador.getPosicion());
            ps.setString(10, jugador.getRepresentanteNombre());
            ps.setString(11, jugador.getSeleccionNombre());

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

        final String sql = "UPDATE jugador SET nombre = ?, alias = ?, fecha_nacimiento = ?, nacionalidad = ?, altura = ?, peso = ?, pie_dominante = ?, valor_mercado = ?, posicion = ?,  representante_nombre = ?, seleccion_nombre = ? WHERE id_jugador = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, jugador.getNombre());
            ps.setString(2, jugador.getAlias());
            ps.setDate(3, java.sql.Date.valueOf(jugador.getFechaNacimiento()));
            ps.setString(4, jugador.getNacionalidad());
            ps.setFloat(5, jugador.getAltura());
            ps.setFloat(6, jugador.getPeso());
            ps.setString(7, jugador.getPieDominante());
            ps.setFloat(8, jugador.getValorMercado());
            ps.setString(9, jugador.getPosicion());
            ps.setString(10, jugador.getRepresentanteNombre());
            ps.setString(11, jugador.getSeleccionNombre());
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

        final String sql = "DELETE FROM jugador WHERE id_jugador = ?";

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
                LocalDate fechaNacimiento = rs.getDate("fecha_nacimiento").toLocalDate();
                String nacionalidad = rs.getString("nacionalidad");
                float altura = rs.getFloat("altura");
                float peso = rs.getFloat("peso");
                String pieDominante = rs.getString("pie_dominante");
                float valorMercado = rs.getFloat("valor_mercado");
                String posicion = rs.getString("posicion");
                String representanteNombre = rs.getString("representante_nombre");
                String seleccionNombre = rs.getString("seleccion_nombre");

                Jugador jugador = new Jugador(id, nombre, alias, fechaNacimiento, nacionalidad, altura, peso,
                        pieDominante, valorMercado, posicion, representanteNombre, seleccionNombre);
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
    public Jugador visualizarJugador(int idJugador) throws BBDDException {
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
                LocalDate fechaNacimiento = rs.getDate("fecha_nacimiento").toLocalDate();
                String nacionalidad = rs.getString("nacionalidad");
                float altura = rs.getFloat("altura");
                float peso = rs.getFloat("peso");
                String pieDominante = rs.getString("pie_dominante");
                float valorMercado = rs.getFloat("valor_mercado");
                String posicion = rs.getString("posicion");
                String representanteNombre = rs.getString("representante_nombre");
                String seleccionNombre = rs.getString("seleccion_nombre");

                // Crear un objeto Jugador con los datos obtenidos
                jugador = new Jugador(id, nombre, alias, fechaNacimiento, nacionalidad, altura, peso,
                        pieDominante, valorMercado, posicion, representanteNombre, seleccionNombre);
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

                Temporada temporada = new Temporada(id, nombre, fechaInicio, fechaFin);
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
    public Temporada visualizarTemporada(int idTemporada) throws BBDDException {
        final String query = "SELECT * FROM vista_temporada WHERE id_temporada = ?";
        Temporada temporada = null;

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idTemporada);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_temporada");
                String nombre = rs.getString("nombre");
                LocalDate fechaInicio = rs.getDate("fecha_inicio").toLocalDate();
                LocalDate fechaFin = rs.getDate("fecha_fin").toLocalDate();

                temporada = new Temporada(id, nombre, fechaInicio, fechaFin);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return temporada; // Devuelve null si no se encuentra
    }

    @Override
    public int insertar(Equipo equipo) throws BBDDException {
        int resultado = 0;
        final String sql = "INSERT INTO equipo (nombre, ciudad, pais, anio_fundacion, presupuesto, propietario, estadio_nombre, entrenador_nombre) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, equipo.getNombre());
            ps.setString(2, equipo.getCiudad());
            ps.setString(3, equipo.getPais());
            ps.setInt(4, equipo.getAnioFundacion());
            ps.setFloat(5, equipo.getPresupuesto());
            ps.setString(6, equipo.getPropietario());
            ps.setString(7, equipo.getEstadioNombre());
            ps.setString(8, equipo.getEntrenadorNombre());

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return resultado;
    }

    @Override
    public int modificar(Equipo equipo) throws BBDDException {
        int resultado = 0;
        final String sql = "UPDATE equipo SET nombre = ?, ciudad = ?, pais = ?, anio_fundacion = ?, presupuesto = ?, propietario = ?, estadio_nombre = ?, entrenador_nombre = ? WHERE id_equipo = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, equipo.getNombre());
            ps.setString(2, equipo.getCiudad());
            ps.setString(3, equipo.getPais());
            ps.setInt(4, equipo.getAnioFundacion());
            ps.setFloat(5, equipo.getPresupuesto());
            ps.setString(6, equipo.getPropietario());
            ps.setString(7, equipo.getEstadioNombre());
            ps.setString(8, equipo.getEntrenadorNombre());
            ps.setInt(9, equipo.getIdEquipo());

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return resultado;
    }

    @Override
    public int eliminarEquipo(int idEquipo) throws BBDDException {
        int resultado = 0;
        final String sql = "DELETE FROM equipo WHERE id_equipo = ?";

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
        final String sql = "SELECT * FROM vista_equipo";

        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Equipo equipo = new Equipo(
                        rs.getInt("id_equipo"),
                        rs.getString("nombre"),
                        rs.getString("ciudad"),
                        rs.getString("pais"),
                        rs.getInt("anio_fundacion"),
                        rs.getFloat("presupuesto"),
                        rs.getString("propietario"),
                        rs.getString("estadio_nombre"),
                        rs.getString("entrenador_nombre"));

                lista.add(equipo);
            }

        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return lista;
    }

    @Override
    public Equipo visualizarEquipo(int idEquipo) throws BBDDException {
        final String query = "SELECT * FROM vista_equipo WHERE id_equipo = ?";
        Equipo equipo = null;

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idEquipo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_equipo");
                String nombre = rs.getString("nombre");
                String ciudad = rs.getString("ciudad");
                String pais = rs.getString("pais");
                int anioFundacion = rs.getInt("anio_fundacion");
                float presupuesto = rs.getFloat("presupuesto");
                String propietario = rs.getString("propietario");
                String estadioNombre = rs.getString("estadio_nombre");
                String entrenadorNombre = rs.getString("entrenador_nombre");

                // Crear un objeto Equipo con los datos obtenidos
                equipo = new Equipo(id, nombre, ciudad, pais, anioFundacion, presupuesto, propietario, estadioNombre,
                        entrenadorNombre);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            // Capturar el mensaje de error
            throw new BBDDException(e.getMessage());
        }

        return equipo; // Puede devolver null si no se encuentra el equipo
    }

    @Override
    public int insertar(Competicion competicion) throws BBDDException {
        int resultado = 0;
        final String sql = "INSERT INTO competicion (nombre, pais, tipo, numero_equipos, anio_creacion) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, competicion.getNombre());
            ps.setString(2, competicion.getPais());
            ps.setString(3, competicion.getTipo());
            ps.setInt(4, competicion.getNumeroEquipos());
            ps.setInt(5, competicion.getAnioCreacion());

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return resultado;
    }

    @Override
    public int modificar(Competicion competicion) throws BBDDException {
        int resultado = 0;
        final String sql = "UPDATE competicion SET nombre = ?, pais = ?, tipo = ?,  numero_equipos = ?, anio_creacion = ? "
                +
                "WHERE id_competicion = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, competicion.getNombre());
            ps.setString(2, competicion.getPais());
            ps.setString(3, competicion.getTipo());
            ps.setInt(4, competicion.getNumeroEquipos());
            ps.setInt(5, competicion.getAnioCreacion());
            ps.setInt(6, competicion.getIdCompeticion());

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return resultado;
    }

    @Override
    public int eliminarCompeticion(int idCompeticion) throws BBDDException {
        int resultado = 0;
        final String sql = "DELETE FROM competicion WHERE id_competicion = ?";

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
        final String sql = "SELECT * FROM vista_competicion";

        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Competicion competicion = new Competicion(
                        rs.getInt("id_competicion"),
                        rs.getString("nombre"),
                        rs.getString("pais"),
                        rs.getString("tipo"),
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
    public Competicion visualizarCompeticion(int idCompeticion) throws BBDDException {
        final String query = "SELECT * FROM vista_competicion WHERE id_competicion = ?";
        Competicion competicion = null;

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idCompeticion);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_competicion");
                String nombre = rs.getString("nombre");
                String pais = rs.getString("pais");
                String tipo = rs.getString("tipo");
                int numeroEquipos = rs.getInt("numero_equipos");
                int anioCreacion = rs.getInt("anio_creacion");

                // Crear un objeto Competicion con los datos obtenidos
                competicion = new Competicion(id, nombre, pais, tipo, numeroEquipos, anioCreacion);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            // Capturar el mensaje de error
            throw new BBDDException(e.getMessage());
        }

        return competicion; // Puede devolver null si no se encuentra la competición
    }

    @Override
    public int insertar(EquipoCompeticion ec) throws BBDDException {
        int resultado = 0;
        final String sql = "INSERT INTO equipo_competicion (equipo_id, competicion_id, temporada_id, rango) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, ec.getEquipoId(), java.sql.Types.INTEGER);
            ps.setObject(2, ec.getCompeticionId(), java.sql.Types.INTEGER);
            ps.setObject(3, ec.getTemporadaId(), java.sql.Types.INTEGER);
            ps.setObject(4, ec.getRango(), java.sql.Types.INTEGER);

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return resultado;
    }

    @Override
    public int modificar(EquipoCompeticion ec) throws BBDDException {
        int resultado = 0;
        final String sql = "UPDATE equipo_competicion SET rango = ? WHERE equipo_id = ? AND competicion_id = ? AND temporada_id = ?";

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
        final String sql = "DELETE FROM equipo_competicion WHERE equipo_id = ? AND competicion_id = ? AND temporada_id = ?";

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
        final String sql = "SELECT * FROM vista_equipo_competicion_temporada";

        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EquipoCompeticion ec = new EquipoCompeticion();
                ec.setEquipoId(rs.getInt("equipo_id"));
                ec.setNombreEquipo(rs.getString("nombre_equipo"));
                ec.setCompeticionId(rs.getInt("competicion_id"));
                ec.setNombreCompeticion(rs.getString("nombre_competicion"));
                ec.setTemporadaId(rs.getInt("temporada_id"));
                ec.setNombreTemporada(rs.getString("nombre_temporada"));
                ec.setRango(rs.getInt("rango"));
                lista.add(ec);
            }
    
        } catch (Exception e) {
            throw new BBDDException("Error al obtener datos de la vista: " + e.getMessage());
        }
    
        return lista;
    }
    

    @Override
    public EquipoCompeticion visualizarEquipoCompeticion(int equipoId, int competicionId, int temporadaId)
            throws BBDDException {
        final String query = "SELECT * FROM vista_equipo_competicion WHERE equipo_id = ? AND competicion_id = ? AND temporada_id = ?";
        EquipoCompeticion equipoCompeticion = null;
    
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, equipoId);
            ps.setInt(2, competicionId);
            ps.setInt(3, temporadaId);
    
            ResultSet rs = ps.executeQuery();
    
            if (rs.next()) {
                int idEquipo = rs.getInt("equipo_id");
                int idCompeticion = rs.getInt("competicion_id");
                int idTemporada = rs.getInt("temporada_id");
                int rango = rs.getObject("rango") != null ? rs.getInt("rango") : 0;
    
                String nombreEquipo = rs.getString("nombre_equipo");
                String nombreCompeticion = rs.getString("nombre_competicion");
                String nombreTemporada = rs.getString("nombre_temporada");
    
                equipoCompeticion = new EquipoCompeticion(idEquipo, idCompeticion, idTemporada, rango);
                equipoCompeticion.setNombreEquipo(nombreEquipo);
                equipoCompeticion.setNombreCompeticion(nombreCompeticion);
                equipoCompeticion.setNombreTemporada(nombreTemporada);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
    
        return equipoCompeticion;
    }
    

    @Override
    public int insertar(Contrato contrato) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO contrato (jugador_id, equipo_id, fecha_inicio, fecha_fin, salario, tipo_contrato) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setObject(1, contrato.getJugadorId(), java.sql.Types.INTEGER);
            ps.setObject(2, contrato.getEquipoId(), java.sql.Types.INTEGER);
            ps.setDate(3, java.sql.Date.valueOf(contrato.getFechaInicio()));
            ps.setDate(4, java.sql.Date.valueOf(contrato.getFechaFin()));
            ps.setFloat(5, contrato.getSalario());
            ps.setString(6, contrato.getTipoContrato());

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

        final String sql = "UPDATE contrato SET jugador_id = ?, equipo_id = ?, fecha_inicio = ?, fecha_fin = ?, salario = ?, tipo_contrato = ? WHERE id_contrato = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setObject(1, contrato.getJugadorId(), java.sql.Types.INTEGER);
            ps.setObject(2, contrato.getEquipoId(), java.sql.Types.INTEGER);
            ps.setDate(3, java.sql.Date.valueOf(contrato.getFechaInicio()));
            ps.setDate(4, java.sql.Date.valueOf(contrato.getFechaFin()));
            ps.setFloat(5, contrato.getSalario());
            ps.setString(6, contrato.getTipoContrato());
            ps.setInt(7, contrato.getIdContrato());

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

        final String sql = "DELETE FROM contrato WHERE id_contrato = ?";

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
        final String query = "SELECT * FROM vista_contrato_jugador_equipo";
        List<Contrato> contratos = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Contrato c = new Contrato();
            c.setIdContrato(rs.getInt("id_contrato"));
            c.setJugadorId(rs.getInt("jugador_id"));
            c.setNombreJugador(rs.getString("nombre_jugador"));
            c.setEquipoId(rs.getInt("equipo_id"));
            c.setNombreEquipo(rs.getString("nombre_equipo"));
            c.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
            c.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
            c.setSalario(rs.getFloat("salario"));
            c.setTipoContrato(rs.getString("tipo"));

            contratos.add(c);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
        return contratos;
    }

    @Override
    public Contrato visualizarContrato(int idContrato) throws BBDDException {
        final String query = "SELECT * FROM vista_contrato WHERE id_contrato = ?";
        Contrato contrato = null;
    
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idContrato);
    
            ResultSet rs = ps.executeQuery();
    
            if (rs.next()) {
                int id = rs.getInt("id_contrato");
                int jugadorId = rs.getInt("jugador_id");
                Integer equipoId = rs.getObject("equipo_id") != null ? rs.getInt("equipo_id") : null;
                LocalDate fechaInicio = rs.getDate("fecha_inicio").toLocalDate();
                LocalDate fechaFin = rs.getDate("fecha_fin").toLocalDate();
                float salario = rs.getFloat("salario");
                String tipoContrato = rs.getString("tipo_contrato");
                String nombreJugador = rs.getString("nombre_jugador");
                String nombreEquipo = rs.getString("nombre_equipo");
    
                contrato = new Contrato(id, jugadorId, equipoId, fechaInicio, fechaFin, salario, tipoContrato);
                contrato.setNombreJugador(nombreJugador);
                contrato.setNombreEquipo(nombreEquipo);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
    
        return contrato;
    }
    

    @Override
    public int insertar(EstadisticasTemporada est) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO estadisticas_temporada "
                + "(jugador_id, temporada_id, competicion_id, equipo_id,  partidos_jugados, goles, asistencias) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, est.getJugadorId());
            ps.setInt(2, est.getTemporadaId());
            ps.setInt(3, est.getCompeticionId());
            ps.setInt(4, est.getEquipoId());
            ps.setInt(5, est.getPartidosJugados());
            ps.setInt(6, est.getGoles());
            ps.setInt(7, est.getAsistencias());

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

        final String sql = "UPDATE estadisticas_temporada SET partidos_jugados = ?,  goles = ?, asistencias = ? "
                + "WHERE jugador_id = ? AND temporada_id = ? AND competicion_id = ? AND equipo_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, est.getPartidosJugados());
            ps.setInt(2, est.getGoles());
            ps.setInt(3, est.getAsistencias());
            ps.setInt(4, est.getJugadorId());
            ps.setInt(5, est.getTemporadaId());
            ps.setInt(6, est.getCompeticionId());
            ps.setInt(7, est.getEquipoId());

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

        final String sql = "DELETE FROM estadisticas_temporada WHERE jugador_id = ? AND temporada_id = ? AND competicion_id = ? AND equipo_id = ?";

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

    public EstadisticasTemporada obtenerEstadisticaPorId(int jugadorId, int temporadaId, int competicionId, int equipoId) throws BBDDException {
        final String query = "SELECT * FROM vista_estadisticas_temporada_jugador_equipo WHERE jugador_id = ? AND temporada_id = ? AND competicion_id = ? AND equipo_id = ?";
    
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, jugadorId);
            ps.setInt(2, temporadaId);
            ps.setInt(3, competicionId);
            ps.setInt(4, equipoId);
            ResultSet rs = ps.executeQuery();
    
            if (rs.next()) {
                EstadisticasTemporada est = new EstadisticasTemporada();
    
                est.setJugadorId(jugadorId);
                est.setTemporadaId(temporadaId);
                est.setCompeticionId(competicionId);
                est.setEquipoId(equipoId);
    
                est.setPartidosJugados(rs.getInt("partidos_jugados"));
                est.setGoles(rs.getInt("goles"));
                est.setAsistencias(rs.getInt("asistencias"));
    
                est.setNombreJugador(rs.getString("nombre_jugador"));
                est.setNombreTemporada(rs.getString("nombre_temporada"));
                est.setNombreCompeticion(rs.getString("nombre_competicion"));
                est.setNombreEquipo(rs.getString("nombre_equipo"));
    
                rs.close();
                ps.close();
                return est;
            }
    
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException("Error al obtener estadística por ID compuesto: " + e.getMessage());
        }
    
        return null;
    }

    
    @Override
public List<EstadisticasTemporada> listarEstadisticasTemporada() throws BBDDException {
    final String query = "SELECT * FROM vista_estadisticas_temporada_jugador_equipo";
    List<EstadisticasTemporada> lista = new ArrayList<>();

    try {
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            EstadisticasTemporada est = new EstadisticasTemporada();

            est.setJugadorId(rs.getInt("jugador_id"));
            est.setTemporadaId(rs.getInt("temporada_id"));
            est.setCompeticionId(rs.getInt("competicion_id"));
            est.setEquipoId(rs.getInt("equipo_id"));

            est.setPartidosJugados(rs.getInt("partidos_jugados"));
            est.setGoles(rs.getInt("goles"));
            est.setAsistencias(rs.getInt("asistencias"));

            est.setNombreJugador(rs.getString("nombre_jugador"));
            est.setNombreTemporada(rs.getString("nombre_temporada"));
            est.setNombreCompeticion(rs.getString("nombre_competicion"));
            est.setNombreEquipo(rs.getString("nombre_equipo"));

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
        final String query = "SELECT * FROM vista_estadisticas_por_jugador WHERE jugador_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, jugadorId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int partidos = rs.getInt("partidos_jugados");
                int goles = rs.getInt("goles");
                int asistencias = rs.getInt("asistencias");

                EstadisticasTemporada est = new EstadisticasTemporada(
                        jugadorId, 0, 0, 0, partidos, goles, asistencias);

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
        final String query = "SELECT * FROM vista_estadisticas_de_jugador_temporada WHERE jugador_id = ? AND temporada_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, jugadorId);
            ps.setInt(2, temporadaId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int partidos = rs.getInt("partidos_jugados");
                int goles = rs.getInt("goles");
                int asistencias = rs.getInt("asistencias");

                EstadisticasTemporada est = new EstadisticasTemporada(
                        jugadorId, temporadaId, 0, 0, partidos, goles, asistencias);

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

        final String sql = "INSERT INTO traspaso (jugador_id, equipo_origen_id, equipo_destino_id, temporada_id, fecha_traspaso, cantidad, tipo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, traspaso.getJugadorId());
            ps.setInt(2, traspaso.getEquipoOrigenId());
            ps.setInt(3, traspaso.getEquipoDestinoId());
            ps.setInt(4, traspaso.getTemporadaId());
            ps.setDate(5, java.sql.Date.valueOf(traspaso.getFechaTraspaso()));
            ps.setFloat(6, traspaso.getCantidad());
            ps.setString(7, traspaso.getTipo());

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

        final String sql = "UPDATE traspaso SET jugador_id = ?, equipo_origen_id = ?, equipo_destino_id = ?, temporada_id = ?, fecha_traspaso = ?, cantidad = ?, tipo = ? "
                + "WHERE id_traspaso = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, traspaso.getJugadorId());
            ps.setInt(2, traspaso.getEquipoOrigenId());
            ps.setInt(3, traspaso.getEquipoDestinoId());
            ps.setInt(4, traspaso.getTemporadaId());
            ps.setDate(5, java.sql.Date.valueOf(traspaso.getFechaTraspaso()));
            ps.setFloat(6, traspaso.getCantidad());
            ps.setString(7, traspaso.getTipo());
            ps.setInt(8, traspaso.getIdTraspaso());

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

        final String sql = "DELETE FROM traspaso WHERE id_traspaso = ?";

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
        final String query = "SELECT * FROM vista_traspaso_equipo_jugador_temporada";
        List<Traspaso> lista = new ArrayList<>();
    
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
    
            while (rs.next()) {
                Traspaso t = new Traspaso();
    
                t.setIdTraspaso(rs.getInt("id_traspaso"));
                t.setJugadorId(rs.getInt("jugador_id"));
                t.setEquipoOrigenId(rs.getInt("equipo_origen_id"));
                t.setEquipoDestinoId(rs.getInt("equipo_destino_id"));
                t.setTemporadaId(rs.getInt("temporada_id"));
                t.setFechaTraspaso(rs.getDate("fecha_traspaso").toLocalDate());
                t.setCantidad(rs.getFloat("cantidad"));
                t.setTipo(rs.getString("tipo"));
    
                // Campos de nombres
                t.setNombreJugador(rs.getString("nombre_jugador"));
                t.setNombreEquipoOrigen(rs.getString("nombre_equipo_origen"));
                t.setNombreEquipoDestino(rs.getString("nombre_equipo_destino"));
                t.setNombreTemporada(rs.getString("nombre_temporada"));
    
                lista.add(t);
            }
    
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
    
        return lista;
    }
    
    @Override
public Traspaso visualizarTraspaso(int idTraspaso) throws BBDDException {
    final String query = "SELECT * FROM vista_traspaso WHERE id_traspaso = ?";
    Traspaso traspaso = null;

    try {
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, idTraspaso);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id_traspaso");
            int jugadorId = rs.getInt("jugador_id");
            Integer equipoOrigenId = rs.getObject("equipo_origen_id") != null ? rs.getInt("equipo_origen_id") : null;
            Integer equipoDestinoId = rs.getObject("equipo_destino_id") != null ? rs.getInt("equipo_destino_id") : null;
            Integer temporadaId = rs.getObject("temporada_id") != null ? rs.getInt("temporada_id") : null;
            LocalDate fecha = rs.getDate("fecha_traspaso").toLocalDate();
            float cantidad = rs.getFloat("cantidad");
            String tipo = rs.getString("tipo");

            traspaso = new Traspaso(id, jugadorId, equipoOrigenId, equipoDestinoId, temporadaId, fecha, cantidad, tipo);

            // Ahora seteo los nombres
            traspaso.setNombreJugador(rs.getString("nombre_jugador"));
            traspaso.setNombreEquipoOrigen(rs.getString("equipo_origen"));
            traspaso.setNombreEquipoDestino(rs.getString("equipo_destino"));
            traspaso.setNombreTemporada(rs.getString("nombre_temporada"));
        }

        rs.close();
        ps.close();
    } catch (Exception e) {
        throw new BBDDException(e.getMessage());
    }

    return traspaso;
}


    @Override
    public int insertar(ValorMercadoHistorial historial) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO valor_mercado_historial (jugador_id, fecha, valor_mercado, motivo) VALUES (?, ?, ?, ?)";

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

        final String sql = "UPDATE valor_mercado_historial SET jugador_id = ?, fecha = ?, valor_mercado = ?, motivo = ? WHERE id_historial = ?";

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

        final String sql = "DELETE FROM valor_mercado_historial WHERE id_historial = ?";

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
        final String query = "SELECT * FROM vista_valor_mercado_historial_jugador";
        List<ValorMercadoHistorial> lista = new ArrayList<>();
    
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
    
            while (rs.next()) {
                ValorMercadoHistorial vm = new ValorMercadoHistorial();
    
                vm.setIdHistorial(rs.getInt("id_historial"));
                vm.setJugadorId(rs.getInt("jugador_id"));
                vm.setNombreJugador(rs.getString("nombre_jugador")); // desde la vista
                vm.setFecha(rs.getDate("fecha").toLocalDate());
                vm.setValorMercado(rs.getFloat("valor_mercado"));
                vm.setMotivo(rs.getString("motivo"));
    
                lista.add(vm);
            }
    
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException("Error al listar historial de valor de mercado: " + e.getMessage());
        }
    
        return lista;
    }
    

    @Override
    public ValorMercadoHistorial visualizarValorMercadoHistorial(int idHistorial) throws BBDDException {
        final String query = "SELECT * FROM vista_valor_mercado_historial WHERE id_historial = ?";
        ValorMercadoHistorial historial = null;
    
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idHistorial);
    
            ResultSet rs = ps.executeQuery();
    
            if (rs.next()) {
                int id = rs.getInt("id_historial");
                int jugadorId = rs.getInt("jugador_id");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                float valor = rs.getFloat("valor_mercado");
                String motivo = rs.getString("motivo");
                String nombreJugador = rs.getString("nombre_jugador");  // Aquí lees el nombre del jugador
    
                historial = new ValorMercadoHistorial(id, jugadorId, fecha, valor, motivo);
                historial.setNombreJugador(nombreJugador);  // Seteas el nombre en el objeto
            }
    
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }
    
        return historial;
    }
    

    public int obtenerIdPorNombreJugador(String nombre) throws BBDDException {
        final String sql = "SELECT id_jugador FROM vista_jugador WHERE nombre = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_jugador");
                } else {
                    throw new BBDDException("Jugador no encontrado: " + nombre);
                }
            }
        } catch (Exception e) {
            throw new BBDDException("Error al obtener ID del jugador: " + e.getMessage());
        }
    }

    public int obtenerIdPorNombreEquipo(String nombre) throws BBDDException {
        final String sql = "SELECT id_equipo FROM vista_equipo WHERE nombre = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_equipo");
                } else {
                    throw new BBDDException("Equipo no encontrado: " + nombre);
                }
            }
        } catch (Exception e) {
            throw new BBDDException("Error al obtener ID del equipo: " + e.getMessage());
        }
    }

    
    public int obtenerIdPorNombreCompeticion(String nombre) throws BBDDException {
        final String sql = "SELECT id_competicion FROM vista_competicion WHERE nombre = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_competicion");
                } else {
                    throw new BBDDException("Competición no encontrada: " + nombre);
                }
            }
        } catch (Exception e) {
            throw new BBDDException("Error al obtener ID de la competición: " + e.getMessage());
        }
    }

    public int obtenerIdPorNombreTemporada(String nombre) throws BBDDException {
        final String sql = "SELECT id_temporada FROM vista_temporada WHERE nombre = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_temporada");
                } else {
                    throw new BBDDException("Temporada no encontrada: " + nombre);
                }
            }
        } catch (Exception e) {
            throw new BBDDException("Error al obtener ID de la temporada: " + e.getMessage());
        }
    }
    
public Usuario buscarUsuarioPorNombreYPassword(String nombre, String contraseña) {
    Usuario usuario = null;
    String sql = "SELECT * FROM usuario WHERE nombre = ? AND contraseña = ?";

    try (
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, nombre);
        ps.setString(2, contraseña);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                usuario.setContrasena(rs.getString("contraseña"));
                usuario.setRol(rs.getString("rol"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return usuario;
}

}
