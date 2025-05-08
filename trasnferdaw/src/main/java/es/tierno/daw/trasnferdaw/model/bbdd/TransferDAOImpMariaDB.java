package es.tierno.daw.trasnferdaw.model.bbdd;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
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

        final String sql = "INSERT INTO jugador (nombre, alias,  fecha_nacimiento, nacionalidad, altura, peso, pie_dominante, valor_mercado, posicion, representante_nombre, seleccion_id) "
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
            ps.setObject(11, jugador.getSeleccionNombre(), java.sql.Types.INTEGER);

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

        final String sql = "UPDATE jugador SET nombre = ?, alias = ?, fecha_nacimiento = ?, nacionalidad = ?, altura = ?, peso = ?, pie_dominante = ?, valor_mercado = ?, posicion = ?,  representante_nombre = ?, seleccion_id = ? WHERE id_jugador = ?";

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
            ps.setObject(11, jugador.getSeleccionNombre(), java.sql.Types.INTEGER);
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
                int seleccionNombre = rs.getInt("seleccion_id");

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
                LocalDate fechaNacimiento = rs.getDate("fecha_nacimiento").toLocalDate();
                String nacionalidad = rs.getString("nacionalidad");
                float altura = rs.getFloat("altura");
                float peso = rs.getFloat("peso");
                String pieDominante = rs.getString("pie_dominante");
                float valorMercado = rs.getFloat("valor_mercado");
                String posicion = rs.getString("posicion");
                String representanteNombre = rs.getString("representante_nombre");
                int seleccionNombre = rs.getInt("seleccion_id");

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
    public int insertar(Seleccion seleccion) throws BBDDException {
        int resultado = 0;
        final String sql = "INSERT INTO seleccion (nombre, pais, federacion, anio_fundacion, ranking, entrenador_nombre, capitan_id) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, seleccion.getNombre());
            ps.setString(2, seleccion.getPais());
            ps.setString(3, seleccion.getFederacion());
            ps.setInt(4, seleccion.getAnioFundacion());
            ps.setInt(5, seleccion.getRanking());
            ps.setString(6, seleccion.getEntrenadorNombre());
            ps.setObject(7, seleccion.getCapitanId(), java.sql.Types.INTEGER);

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
        final String sql = "UPDATE seleccion SET nombre = ?, pais = ?, federacion = ?, anio_fundacion = ?, ranking = ?, entrenador_nombre = ?, capitan_id = ? WHERE id_seleccion = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, seleccion.getNombre());
            ps.setString(2, seleccion.getPais());
            ps.setString(3, seleccion.getFederacion());
            ps.setInt(4,seleccion.getAnioFundacion());
            ps.setInt(5, seleccion.getRanking());
            ps.setString(6, seleccion.getEntrenadorNombre());
            ps.setObject(7, seleccion.getCapitanId(), java.sql.Types.INTEGER);
            ps.setInt(8, seleccion.getIdSeleccion());

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
        final String sql = "DELETE FROM seleccion WHERE id_seleccion = ?";

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
        final String query = "SELECT * FROM vista_seleccion";
        List<Seleccion> selecciones = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_seleccion");
                String nombre = rs.getString("nombre");
                String pais = rs.getString("pais");
                String federacion = rs.getString("federacion");
                int anioFundacion = rs.getInt("anio_fundacion");
                int ranking = rs.getInt("ranking");
                String entrenadorNombre = rs.getString("entrenador_nombre");
                Integer capitanId = rs.getObject("capitan_id") != null ? rs.getInt("capitan_id") : null;

                Seleccion seleccion = new Seleccion(id, nombre, pais, federacion,
                        anioFundacion, ranking, entrenadorNombre, capitanId);

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
    public int insertar(EquipoCompeticion ec) throws BBDDException {
        int resultado = 0;
        final String sql = "INSERT INTO equipo_competicion (equipo_id, competicion_id, temporada_id, rango) VALUES (?, ?, ?, ?)";

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
        final String sql = "SELECT * FROM vista_equipo_competicion";

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
        final String query = "SELECT * FROM vista_contrato";
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
                String tipoContrato = rs.getString("tipo_contrato");

                Contrato contrato = new Contrato(idContrato, jugadorId, equipoId, fechaInicio, fechaFin, salario, tipoContrato);
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

    @Override
    public List<EstadisticasTemporada> listarEstadisticasTemporada() throws BBDDException {
        final String query = "SELECT * FROM vista_estadisticas_temporada";
        List<EstadisticasTemporada> lista = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int jugadorId = rs.getInt("jugador_id");
                int temporadaId = rs.getInt("temporada_id");
                int competicionId = rs.getInt("competicion_id");
                int equipoId = rs.getInt("equipo_id");
                int partidos = rs.getInt("partidos_jugados");
                int goles = rs.getInt("goles");
                int asistencias = rs.getInt("asistencias");
                

                EstadisticasTemporada est = new EstadisticasTemporada(jugadorId, temporadaId, competicionId, equipoId,
                partidos, goles, asistencias);
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
        final String query = "SELECT * FROM vista_traspaso";
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
                String tipo = rs.getString("tipo");

                Traspaso traspaso = new Traspaso(id, jugadorId, equipoOrigenId, equipoDestinoId, temporadaId,
                        fechaTraspaso,
                        cantidad, tipo);
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
        final String query = "SELECT * FROM vista_valor_mercado_historial";
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
    public int insertar(Usuario usuario) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO usuario (nombre, email, contraseña, rol) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getContrasena());
            ps.setString(4, usuario.getRol());

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

        final String sql = "UPDATE usuario SET nombre = ?, email = ?, contraseña = ?, rol = ? WHERE id_usuario = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getContrasena());
            ps.setString(4, usuario.getRol());
            ps.setInt(5, usuario.getIdUsuario());

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

        final String sql = "DELETE FROM usuario WHERE id_usuario = ?";

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
        final String query = "SELECT * FROM vista_usuario";
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

                Usuario usuario = new Usuario(id, nombre, email, contrasena, rol);
                lista.add(usuario);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(e.getMessage());
        }

        return lista;
    }
}
