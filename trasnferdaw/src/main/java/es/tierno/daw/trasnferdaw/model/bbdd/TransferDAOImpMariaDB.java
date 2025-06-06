package es.tierno.daw.trasnferdaw.model.bbdd;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import es.tierno.daw.trasnferdaw.model.entities.Competicion;
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
 * La clase TransferDAOImpMariaDB implementa el comportamiento con la base de datos.
 * \author Iván Rafael Redondo.
 */
public class TransferDAOImpMariaDB extends TransferDAWDAOImp {
    // jugador
    public static final String ID_JUGADOR = "id_jugador";
    public static final String NOMBRE_JUGADOR = "nombre";
    public static final String ALIAS_JUGADOR = "alias";
    public static final String FECHA_NACIMIENTO_JUGADOR = "fecha_nacimiento";
    public static final String NACIONALIDAD_JUGADOR = "nacionalidad";
    public static final String ALTURA_JUGADOR = "altura";
    public static final String PESO_JUGADOR = "peso";
    public static final String PIE_JUGADOR = "pie_dominante";
    public static final String VALOR_JUGADOR = "valor_mercado";
    public static final String POSICION_JUGADOR = "posicion";
    public static final String REPRESENTANTE_NOMBRE_JUGADOR = "representante_nombre";
    public static final String SELECCION_NOMBRE_JUGADOR = "seleccion_nombre";

    // temporada
    public static final String ID_TEMPORADA = "id_temporada";
    public static final String NOMBRE_TEMPORADA = "nombre";
    public static final String FECHA_INICIO_TEMPORADA = "fecha_inicio";
    public static final String FECHA_FIN_TEMPORADA = "fecha_fin";

    // equipo
    public static final String ID_EQUIPO = "id_equipo";
    public static final String NOMBRE_EQUIPO = "nombre";
    public static final String CIUDAD_EQUIPO = "ciudad";
    public static final String PAIS_EQUIPO = "pais";
    public static final String ANIO_FUNDACION_EQUIPO = "anio_fundacion";
    public static final String PRESUPUESTO_EQUIPO = "presupuesto";
    public static final String PROPIETARIO_EQUIPO = "propietario";
    public static final String ESTADIO_NOMBRE_EQUIPO = "estadio_nombre";
    public static final String ENTRENADOR_NOMBRE_EQUIPO = "entrenador_nombre";

    // competicion
    public static final String ID_COMPETICION = "id_competicion";
    public static final String NOMBRE_COMPETICION = "nombre";
    public static final String PAIS_COMPETICION = "pais";
    public static final String TIPO_COMPETICION = "tipo";
    public static final String NUM_EQUIPOS_COMPETICION = "numero_equipos";
    public static final String ANIO_CREACION_COMPETICION = "anio_creacion";

    // equipo_competicion
    public static final String EQUIPO_ID_EQUIPO_COMPETICION = "equipo_id";
    public static final String COMPETICION_ID_EQUIPO_COMPETICION = "competicion_id";
    public static final String TEMPORADA_ID_EQUIPO_COMPETICION = "temporada_id";
    public static final String POSICION_EQUIPO_COMPETICION = "posicion";
    public static final String NOMBRE_EQUIPO_COMPETICION = "nombre_equipo";
    public static final String NOMBRE_COMPETICION_EQUIPO_COMPETICION = "nombre_competicion";
    public static final String NOMBRE_TEMPORADA_EQUIPO_COMPETICION = "nombre_temporada";

    // estadisticas
    public static final String JUGADOR_ID_ESTADISTICAS = "jugador_id";
    public static final String TEMPORADA_ID_ESTADISTICAS = "temporada_id";
    public static final String COMPETICION_ID_ESTADISTICAS = "competicion_id";
    public static final String EQUIPO_ID_ESTADISTICAS = "equipo_id";
    public static final String PARTIDOS_JUGADOS_ESTADISTICAS = "partidos_jugados";
    public static final String GOLES_ESTADISTICAS = "goles";
    public static final String ASISTENCIAS_ESTADISTICAS = "asistencias";
    public static final String NOMBRE_JUGADOR_ESTADISTICAS = "nombre_jugador";
    public static final String NOMBRE_TEMPORADA_ESTADISTICAS = "nombre_temporada";
    public static final String NOMBRE_COMPETICION_ESTADISTICAS = "nombre_competicion";
    public static final String NOMBRE_EQUIPO_ESTADISTICAS = "nombre_equipo";

    // traspaso
    public static final String ID_TRASPASO = "id_traspaso";
    public static final String JUGADOR_ID_TRASPASO = "jugador_id";
    public static final String EQUIPO_ORIGEN_ID_TRASPASO = "equipo_origen_id";
    public static final String EQUIPO_DESTINO_ID_TRASPASO = "equipo_destino_id";
    public static final String TEMPORADA_ID_TRASPASO = "temporada_id";
    public static final String FECHA_TRASPASO = "fecha_traspaso";
    public static final String CANTIDAD_TRASPASO = "cantidad";
    public static final String TIPO_TRASPASO = "tipo";
    public static final String NOMBRE_EQUIPO_ORIGEN_TRASPASO = "nombre_equipo_origen";
    public static final String NOMBRE_EQUIPO_DESTINO_TRASPASO = "nombre_equipo_destino";
    public static final String NOMBRE_JUGADOR_TRASPASO = "nombre_jugador";
    public static final String EQUIPO_ORIGEN_TRASPASO = "equipo_origen";
    public static final String EQUIPO_DESTINO_TRASPASO = "equipo_destino";
    public static final String NOMBRE_TEMPORADA_TRASPASO = "nombre_temporada";

    // historial
    public static final String ID_HISTORIAL = "id_historial";
    public static final String JUGADOR_ID_HISTORIAL = "jugador_id";
    public static final String FECHA_HISTORIAL = "fecha";
    public static final String VALOR_MERCADO_HISTORIAL = "valor_mercado";
    public static final String MOTIVO_HISTORIAL = "motivo";
    public static final String NOMBRE_JUGADOR_HISTORIAL = "nombre_jugador";

    // usuario
    public static final String ID_USUARIO = "id_usuario";
    public static final String NOMBRE_USUARIO = "nombre";
    public static final String EMAIL_USUARIO = "email";
    public static final String CONTRASENA_USUARIO = "contraseña";
    public static final String ROL_USUARIO = "rol";

    //Errores
    public static final String ERROR_INSERTAR_JUGADOR = "Error al insertar jugador en la base de datos";
    public static final String ERROR_MODIFICAR_JUGADOR = "Error al modificar jugador en la base de datos";
    public static final String ERROR_ELIMINAR_JUGADOR = "Error al eliminar jugador en la base de datos";
    public static final String ERROR_LISTAR_JUGADOR = "Error al listar jugadores en la base de datos";
    public static final String ERROR_BUSCAR_JUGADOR = "Error al buscar jugador en la base de datos";

    public static final String ERROR_INSERTAR_EQUIPO = "Error al insertar equipo en la base de datos";
    public static final String ERROR_MODIFICAR_EQUIPO = "Error al modificar equipo en la base de datos";
    public static final String ERROR_ELIMINAR_EQUIPO = "Error al eliminar equipo en la base de datos";
    public static final String ERROR_LISTAR_EQUIPO = "Error al listar equipos en la base de datos";
    public static final String ERROR_BUSCAR_EQUIPO = "Error al buscar equipo en la base de datos";

    public static final String ERROR_LISTAR_TEMPORADA = "Error al listar temporadas en la base de datos";

    public static final String ERROR_INSERTAR_COMPETICION = "Error al insertar competicion en la base de datos";
    public static final String ERROR_MODIFICAR_COMPETICION = "Error al modificar competicion en la base de datos";
    public static final String ERROR_ELIMINAR_COMPETICION = "Error al eliminar competicion en la base de datos";
    public static final String ERROR_LISTAR_COMPETICION = "Error al listar competiciones en la base de datos";
    public static final String ERROR_BUSCAR_COMPETICION = "Error al buscar competicion en la base de datos";

    public static final String ERROR_INSERTAR_EQUIPO_COMPETICION = "Error al insertar un equipo en una competicion en la base de datos";
    public static final String ERROR_MODIFICAR_EQUIPO_COMPETICION  = "Error al modificar un equipo en una competicion en la base de datos";
    public static final String ERROR_ELIMINAR_EQUIPO_COMPETICION  = "Error al eliminarr un equipo en una competicion en la base de datos";
    public static final String ERROR_LISTAR_EQUIPO_COMPETICION  = "Error al listar equipos en competiciones en la base de datos";
    public static final String ERROR_BUSCAR_EQUIPO_COMPETICION  = "Error al buscar un equipo en una competicion en la base de datos";

    public static final String ERROR_INSERTAR_ESTADISTICA = "Error al insertar estadística del jugador";
    public static final String ERROR_MODIFICAR_ESTADISTICA = "Error al modificar estadística del jugador";
    public static final String ERROR_ELIMINAR_ESTADISTICA = "Error al eliminar estadística del jugador";
    public static final String ERROR_LISTAR_ESTADISTICA = "Error al listar estadísticas del jugador";
    public static final String ERROR_BUSCAR_ESTADISTICA_TOTAL = "Error al buscar estadísticas totales del jugador";
    public static final String ERROR_BUSCAR_ESTADISTICA_TEMPORADA = "Error al buscar estadística del jugador en una temporada";

    public static final String ERROR_INSERTAR_TRASPASO = "Error al insertar traspaso en la base de datos";
    public static final String ERROR_MODIFICAR_TRASPASO = "Error al modificar traspaso en la base de datos";
    public static final String ERROR_ELIMINAR_TRASPASO = "Error al eliminar traspaso en la base de datos";
    public static final String ERROR_LISTAR_TRASPASO = "Error al listar traspasos en la base de datos";
    public static final String ERROR_BUSCAR_TRASPASO = "Error al buscar traspaso en la base de datos";

    public static final String ERROR_INSERTAR_HISTORIAL = "Error al insertar valor de mercado en la base de datos";
    public static final String ERROR_MODIFICAR_HISTORIAL = "Error al modificar valor de mercado en la base de datos";
    public static final String ERROR_ELIMINAR_HISTORIAL = "Error al eliminar valor de mercado en la base de datos";
    public static final String ERROR_LISTAR_HISTORIAL = "Error al listar valores de mercado en la base de datos";
    public static final String ERROR_BUSCAR_HISTORIAL = "Error al buscar valor de mercado en la base de datos";

    public static final String ERROR_INSERTAR_USUARIO = "Error al insertar usuario en la base de datos";
    public static final String ERROR_ELIMINAR_USUARIO = "Error al eliminar usuario en la base de datos";

    public static final String ERROR_JUGADOR = "Jugador no encontrado en la base de datos";
    public static final String ERROR_EQUIPO = "Equipo no encontrado en la base de datos";
    public static final String ERROR_COMPETICION = "Competicion no encontrada en la base de datos";
    public static final String ERROR_TEMPORADA = "Temporada no encontrada en la base de datos";
    public static final String ERROR_OBTENER_ID_JUGADOR = "Error al obtener ID del jugador";
    public static final String ERROR_OBTENER_ID_EQUIPO = "Error al obtener ID del equipo";
    public static final String ERROR_OBTENER_ID_TEMPORADA = "Error al obtener ID de la temporada";
    public static final String ERROR_OBTENER_ID_COMPETICION = "Error al obtener ID de la competicion";
    public static final String ERROR_OBTENER_ID_ESTADISTICA = "Error al obtener ID de la estadistica";
    public static final String ERROR_BUSCAR_USUARIO_NOMBRE = "Error al buscar usuario por nombre";
    public static final String ERROR_BUSCAR_USUARIO_EMAIL = "Error al buscar usuario por email";


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

        final String sql = "INSERT INTO jugador (" + NOMBRE_JUGADOR + ", " + ALIAS_JUGADOR + ", "
                + FECHA_NACIMIENTO_JUGADOR + ", " + NACIONALIDAD_JUGADOR + ", " + ALTURA_JUGADOR + ", " + PESO_JUGADOR
                + ", " + PIE_JUGADOR + ", "
                + VALOR_JUGADOR + ", " + POSICION_JUGADOR + ", " + REPRESENTANTE_NOMBRE_JUGADOR
                + ", " + SELECCION_NOMBRE_JUGADOR + ") " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, jugador.getNombre());
            ps.setString(2, jugador.getAlias());
            ps.setDate(3, java.sql.Date.valueOf(jugador.getFechaNacimiento()));
            ps.setString(4, jugador.getNacionalidad());
            ps.setDouble(5, jugador.getAltura());
            ps.setDouble(6, jugador.getPeso());
            ps.setString(7, jugador.getPieDominante());
            ps.setDouble(8, jugador.getValorMercado());
            ps.setString(9, jugador.getPosicion());
            ps.setString(10, jugador.getRepresentanteNombre());
            ps.setString(11, jugador.getSeleccionNombre());

            numRegistrosActualizados = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_INSERTAR_JUGADOR, e);
        }
        return numRegistrosActualizados;
    }

    @Override
    public int modificar(Jugador jugador) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "UPDATE jugador SET " + NOMBRE_JUGADOR + " = ?, " + ALIAS_JUGADOR + " = ?, "
                + FECHA_NACIMIENTO_JUGADOR + " = ?, " + NACIONALIDAD_JUGADOR + " = ?, " + ALTURA_JUGADOR + " = ?, "
                + PESO_JUGADOR + " = ?, "
                + PIE_JUGADOR + " = ?, " + VALOR_JUGADOR + " = ?, " + POSICION_JUGADOR + " = ?, "
                + REPRESENTANTE_NOMBRE_JUGADOR + " = ?, " + SELECCION_NOMBRE_JUGADOR
                + " = ? WHERE " + ID_JUGADOR + " = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, jugador.getNombre());
            ps.setString(2, jugador.getAlias());
            ps.setDate(3, java.sql.Date.valueOf(jugador.getFechaNacimiento()));
            ps.setString(4, jugador.getNacionalidad());
            ps.setDouble(5, jugador.getAltura());
            ps.setDouble(6, jugador.getPeso());
            ps.setString(7, jugador.getPieDominante());
            ps.setDouble(8, jugador.getValorMercado());
            ps.setString(9, jugador.getPosicion());
            ps.setString(10, jugador.getRepresentanteNombre());
            ps.setString(11, jugador.getSeleccionNombre());
            ps.setInt(12, jugador.getIdJugador());

            numRegistrosActualizados = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_MODIFICAR_JUGADOR, e);
        }
        return numRegistrosActualizados;
    }

    @Override
    public int eliminarJugador(int idJugador) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "DELETE FROM jugador WHERE " + ID_JUGADOR + " = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, idJugador);

            numRegistrosActualizados = ps.executeUpdate();

        } catch (Exception e) {
            throw new BBDDException(ERROR_ELIMINAR_JUGADOR, e);
        }
        return numRegistrosActualizados;
    }

    @Override
    public List<Jugador> listarJugadores() throws BBDDException {
        final String query = "SELECT " + ID_JUGADOR + ", " + NOMBRE_JUGADOR + ", "
                + ALIAS_JUGADOR + ", " + FECHA_NACIMIENTO_JUGADOR + ", " + NACIONALIDAD_JUGADOR + ", "
                + ALTURA_JUGADOR + ", " + PESO_JUGADOR + ", " + PIE_JUGADOR + ", "
                + VALOR_JUGADOR + ", " + POSICION_JUGADOR + ", " + REPRESENTANTE_NOMBRE_JUGADOR + ", "
                + SELECCION_NOMBRE_JUGADOR
                + " FROM vista_jugador";

        List<Jugador> jugadores = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(query)){
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(ID_JUGADOR);
                String nombre = rs.getString(NOMBRE_JUGADOR);
                String alias = rs.getString(ALIAS_JUGADOR);
                LocalDate fechaNacimiento = rs.getDate(FECHA_NACIMIENTO_JUGADOR).toLocalDate();
                String nacionalidad = rs.getString(NACIONALIDAD_JUGADOR);
                double altura = rs.getDouble(ALTURA_JUGADOR);
                double peso = rs.getDouble(PESO_JUGADOR);
                String pieDominante = rs.getString(PIE_JUGADOR);
                double valorMercado = rs.getDouble(VALOR_JUGADOR);
                String posicion = rs.getString(POSICION_JUGADOR);
                String representanteNombre = rs.getString(REPRESENTANTE_NOMBRE_JUGADOR);
                String seleccionNombre = rs.getString(SELECCION_NOMBRE_JUGADOR);

                Jugador jugador = new Jugador(id, nombre, alias, fechaNacimiento, nacionalidad, altura, peso,
                        pieDominante, valorMercado, posicion, representanteNombre, seleccionNombre);
                jugadores.add(jugador);
            }
        } catch (Exception e) {
            throw new BBDDException(ERROR_LISTAR_JUGADOR, e);
        }
        return jugadores;
    }

    @Override
    public Jugador visualizarJugador(int idJugador) throws BBDDException {
        final String query = "SELECT " + ID_JUGADOR + ", "
                + NOMBRE_JUGADOR + ", " + ALIAS_JUGADOR + ", " + FECHA_NACIMIENTO_JUGADOR + ", " + NACIONALIDAD_JUGADOR
                + ", "
                + ALTURA_JUGADOR + ", " + PESO_JUGADOR + ", " + PIE_JUGADOR + ", " + VALOR_JUGADOR + ", "
                + POSICION_JUGADOR + ", "
                + REPRESENTANTE_NOMBRE_JUGADOR + ", " + SELECCION_NOMBRE_JUGADOR + " FROM vista_jugador WHERE "
                + ID_JUGADOR + " = ?";

        Jugador jugador = null;

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, idJugador);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(ID_JUGADOR);
                String nombre = rs.getString(NOMBRE_JUGADOR);
                String alias = rs.getString(ALIAS_JUGADOR);
                LocalDate fechaNacimiento = rs.getDate(FECHA_NACIMIENTO_JUGADOR).toLocalDate();
                String nacionalidad = rs.getString(NACIONALIDAD_JUGADOR);
                double altura = rs.getDouble(ALTURA_JUGADOR);
                double peso = rs.getDouble(PESO_JUGADOR);
                String pieDominante = rs.getString(PIE_JUGADOR);
                double valorMercado = rs.getDouble(VALOR_JUGADOR);
                String posicion = rs.getString(POSICION_JUGADOR);
                String representanteNombre = rs.getString(REPRESENTANTE_NOMBRE_JUGADOR);
                String seleccionNombre = rs.getString(SELECCION_NOMBRE_JUGADOR);

                jugador = new Jugador(id, nombre, alias, fechaNacimiento, nacionalidad, altura, peso,
                        pieDominante, valorMercado, posicion, representanteNombre, seleccionNombre);
            }
        } catch (Exception e) {
            throw new BBDDException(ERROR_BUSCAR_JUGADOR, e);
        }

        return jugador;
    }

    @Override
    public List<Temporada> listarTemporadas() throws BBDDException {
      final String query = "SELECT " 
        + ID_TEMPORADA + ", " 
        + NOMBRE_TEMPORADA + ", " 
        + FECHA_INICIO_TEMPORADA + ", " 
        + FECHA_FIN_TEMPORADA 
        + " FROM vista_temporada";
        List<Temporada> temporadas = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(ID_TEMPORADA);
                String nombre = rs.getString(NOMBRE_TEMPORADA);
                LocalDate fechaInicio = rs.getDate(FECHA_INICIO_TEMPORADA) != null
                        ? rs.getDate(FECHA_INICIO_TEMPORADA).toLocalDate()
                        : null;
                LocalDate fechaFin = rs.getDate(FECHA_FIN_TEMPORADA) != null
                        ? rs.getDate(FECHA_FIN_TEMPORADA).toLocalDate()
                        : null;

                Temporada temporada = new Temporada(id, nombre, fechaInicio, fechaFin);
                temporadas.add(temporada);
            }

        } catch (Exception e) {
            throw new BBDDException(ERROR_LISTAR_TEMPORADA, e);
        }

        return temporadas;
    }

    @Override
    public int insertar(Equipo equipo) throws BBDDException {
        int resultado = 0;
        final String sql = "INSERT INTO equipo (" +
                NOMBRE_EQUIPO + ", " +
                CIUDAD_EQUIPO + ", " +
                PAIS_EQUIPO + ", " +
                ANIO_FUNDACION_EQUIPO + ", " +
                PRESUPUESTO_EQUIPO + ", " +
                PROPIETARIO_EQUIPO + ", " +
                ESTADIO_NOMBRE_EQUIPO + ", " +
                ENTRENADOR_NOMBRE_EQUIPO + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, equipo.getNombre());
            ps.setString(2, equipo.getCiudad());
            ps.setString(3, equipo.getPais());
            ps.setInt(4, equipo.getAnioFundacion());
            ps.setDouble(5, equipo.getPresupuesto());
            ps.setString(6, equipo.getPropietario());
            ps.setString(7, equipo.getEstadioNombre());
            ps.setString(8, equipo.getEntrenadorNombre());

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_INSERTAR_EQUIPO, e);
        }

        return resultado;
    }

    @Override
    public int modificar(Equipo equipo) throws BBDDException {
        int resultado = 0;
        final String sql = "UPDATE equipo SET " +
                NOMBRE_EQUIPO + " = ?, " +
                CIUDAD_EQUIPO + " = ?, " +
                PAIS_EQUIPO + " = ?, " +
                ANIO_FUNDACION_EQUIPO + " = ?, " +
                PRESUPUESTO_EQUIPO + " = ?, " +
                PROPIETARIO_EQUIPO + " = ?, " +
                ESTADIO_NOMBRE_EQUIPO + " = ?, " +
                ENTRENADOR_NOMBRE_EQUIPO + " = ? WHERE " +
                ID_EQUIPO + " = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, equipo.getNombre());
            ps.setString(2, equipo.getCiudad());
            ps.setString(3, equipo.getPais());
            ps.setInt(4, equipo.getAnioFundacion());
            ps.setDouble(5, equipo.getPresupuesto());
            ps.setString(6, equipo.getPropietario());
            ps.setString(7, equipo.getEstadioNombre());
            ps.setString(8, equipo.getEntrenadorNombre());
            ps.setInt(9, equipo.getIdEquipo());

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_MODIFICAR_EQUIPO, e);
        }

        return resultado;
    }

    @Override
    public int eliminarEquipo(int idEquipo) throws BBDDException {
        int resultado = 0;
        final String sql = "DELETE FROM equipo WHERE " + ID_EQUIPO + " = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEquipo);
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_ELIMINAR_EQUIPO, e);
        }

        return resultado;
    }

    @Override
    public List<Equipo> listarEquipos() throws BBDDException {
        List<Equipo> lista = new ArrayList<>();
        final String sql = "SELECT "
        + ID_EQUIPO + ", " 
        + NOMBRE_EQUIPO + ", "
        + CIUDAD_EQUIPO + ", "
        + PAIS_EQUIPO + ", "
        + ANIO_FUNDACION_EQUIPO + ", "
        + PRESUPUESTO_EQUIPO + ", "
        + PROPIETARIO_EQUIPO + ", "
        + ESTADIO_NOMBRE_EQUIPO + ", "
        + ENTRENADOR_NOMBRE_EQUIPO
        + " FROM vista_equipo";
    

        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Equipo equipo = new Equipo(
                        rs.getInt(ID_EQUIPO),
                        rs.getString(NOMBRE_EQUIPO),
                        rs.getString(CIUDAD_EQUIPO),
                        rs.getString(PAIS_EQUIPO),
                        rs.getInt(ANIO_FUNDACION_EQUIPO),
                        rs.getDouble(PRESUPUESTO_EQUIPO),
                        rs.getString(PROPIETARIO_EQUIPO),
                        rs.getString(ESTADIO_NOMBRE_EQUIPO),
                        rs.getString(ENTRENADOR_NOMBRE_EQUIPO));

                lista.add(equipo);
            }

        } catch (Exception e) {
            throw new BBDDException(ERROR_LISTAR_EQUIPO, e);
        }

        return lista;
    }

    @Override
    public Equipo visualizarEquipo(int idEquipo) throws BBDDException {
        final String query = "SELECT "
        + ID_EQUIPO + ", "
        + NOMBRE_EQUIPO + ", "
        + CIUDAD_EQUIPO + ", "
        + PAIS_EQUIPO + ", "
        + ANIO_FUNDACION_EQUIPO + ", "
        + PRESUPUESTO_EQUIPO + ", "
        + PROPIETARIO_EQUIPO + ", "
        + ESTADIO_NOMBRE_EQUIPO + ", "
        + ENTRENADOR_NOMBRE_EQUIPO
        + " FROM vista_equipo WHERE " + ID_EQUIPO + " = ?";
    
        Equipo equipo = null;

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, idEquipo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(ID_EQUIPO);
                String nombre = rs.getString(NOMBRE_EQUIPO);
                String ciudad = rs.getString(CIUDAD_EQUIPO);
                String pais = rs.getString(PAIS_EQUIPO);
                int anioFundacion = rs.getInt(ANIO_FUNDACION_EQUIPO);
                double presupuesto = rs.getDouble(PRESUPUESTO_EQUIPO);
                String propietario = rs.getString(PROPIETARIO_EQUIPO);
                String estadioNombre = rs.getString(ESTADIO_NOMBRE_EQUIPO);
                String entrenadorNombre = rs.getString(ENTRENADOR_NOMBRE_EQUIPO);

                equipo = new Equipo(id, nombre, ciudad, pais, anioFundacion, presupuesto, propietario, estadioNombre,
                        entrenadorNombre);
            }

        } catch (Exception e) {
            throw new BBDDException(ERROR_BUSCAR_EQUIPO, e);
        }

        return equipo;
    }

    @Override
    public int insertar(Competicion competicion) throws BBDDException {
        int resultado = 0;
        final String sql = "INSERT INTO competicion (" +
                NOMBRE_COMPETICION + ", " +
                PAIS_COMPETICION + ", " +
                TIPO_COMPETICION + ", " +
                NUM_EQUIPOS_COMPETICION + ", " +
                ANIO_CREACION_COMPETICION + ") VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, competicion.getNombre());
            ps.setString(2, competicion.getPais());
            ps.setString(3, competicion.getTipo());
            ps.setInt(4, competicion.getNumeroEquipos());
            ps.setInt(5, competicion.getAnioCreacion());

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_INSERTAR_COMPETICION, e);
        }

        return resultado;
    }

    @Override
    public int modificar(Competicion competicion) throws BBDDException {
        int resultado = 0;
        final String sql = "UPDATE competicion SET " +
                NOMBRE_COMPETICION + " = ?, " +
                PAIS_COMPETICION + " = ?, " +
                TIPO_COMPETICION + " = ?, " +
                NUM_EQUIPOS_COMPETICION + " = ?, " +
                ANIO_CREACION_COMPETICION + " = ? WHERE " +
                ID_COMPETICION + " = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, competicion.getNombre());
            ps.setString(2, competicion.getPais());
            ps.setString(3, competicion.getTipo());
            ps.setInt(4, competicion.getNumeroEquipos());
            ps.setInt(5, competicion.getAnioCreacion());
            ps.setInt(6, competicion.getIdCompeticion());

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_MODIFICAR_COMPETICION, e);
        }

        return resultado;
    }

    @Override
    public int eliminarCompeticion(int idCompeticion) throws BBDDException {
        int resultado = 0;
        final String sql = "DELETE FROM competicion WHERE " + ID_COMPETICION + " = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCompeticion);
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_ELIMINAR_COMPETICION, e);
        }

        return resultado;
    }

    @Override
    public List<Competicion> listarCompeticiones() throws BBDDException {
        List<Competicion> lista = new ArrayList<>();
        final String sql = "SELECT "
        + ID_COMPETICION + ", "
        + NOMBRE_COMPETICION + ", "
        + PAIS_COMPETICION + ", "
        + TIPO_COMPETICION + ", "
        + NUM_EQUIPOS_COMPETICION + ", "
        + ANIO_CREACION_COMPETICION
        + " FROM vista_competicion";
    
        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Competicion competicion = new Competicion(
                        rs.getInt(ID_COMPETICION),
                        rs.getString(NOMBRE_COMPETICION),
                        rs.getString(PAIS_COMPETICION),
                        rs.getString(TIPO_COMPETICION),
                        rs.getInt(NUM_EQUIPOS_COMPETICION),
                        rs.getInt(ANIO_CREACION_COMPETICION));

                lista.add(competicion);
            }

        } catch (Exception e) {
            throw new BBDDException(ERROR_LISTAR_COMPETICION, e);
        }

        return lista;
    }

    @Override
    public Competicion visualizarCompeticion(int idCompeticion) throws BBDDException {
        final String query = "SELECT "
        + ID_COMPETICION + ", "
        + NOMBRE_COMPETICION + ", "
        + PAIS_COMPETICION + ", "
        + TIPO_COMPETICION + ", "
        + NUM_EQUIPOS_COMPETICION + ", "
        + ANIO_CREACION_COMPETICION
        + " FROM vista_competicion WHERE " + ID_COMPETICION + " = ?";
    
        Competicion competicion = null;

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, idCompeticion);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(ID_COMPETICION);
                String nombre = rs.getString(NOMBRE_COMPETICION);
                String pais = rs.getString(PAIS_COMPETICION);
                String tipo = rs.getString(TIPO_COMPETICION);
                int numeroEquipos = rs.getInt(NUM_EQUIPOS_COMPETICION);
                int anioCreacion = rs.getInt(ANIO_CREACION_COMPETICION);

                competicion = new Competicion(id, nombre, pais, tipo, numeroEquipos, anioCreacion);
            }
        } catch (Exception e) {
            throw new BBDDException(ERROR_BUSCAR_COMPETICION, e);
        }

        return competicion;
    }

    @Override
    public int insertar(EquipoCompeticion ec) throws BBDDException {
        int resultado = 0;
        final String sql = "INSERT INTO equipo_competicion (" +
                EQUIPO_ID_EQUIPO_COMPETICION + ", " +
                COMPETICION_ID_EQUIPO_COMPETICION + ", " +
                TEMPORADA_ID_EQUIPO_COMPETICION + ", " +
                POSICION_EQUIPO_COMPETICION + ") VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, ec.getEquipoId(), java.sql.Types.INTEGER);
            ps.setObject(2, ec.getCompeticionId(), java.sql.Types.INTEGER);
            ps.setObject(3, ec.getTemporadaId(), java.sql.Types.INTEGER);
            ps.setObject(4, ec.getPosicion());

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_INSERTAR_EQUIPO_COMPETICION, e);
        }

        return resultado;
    }

    @Override
    public int modificar(EquipoCompeticion ec) throws BBDDException {
        int resultado = 0;
        final String sql = "UPDATE equipo_competicion SET " + POSICION_EQUIPO_COMPETICION +
                " = ? WHERE " + EQUIPO_ID_EQUIPO_COMPETICION + " = ? AND " + COMPETICION_ID_EQUIPO_COMPETICION
                + " = ? AND " + TEMPORADA_ID_EQUIPO_COMPETICION + " = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ec.getPosicion());
            ps.setInt(2, ec.getEquipoId());
            ps.setInt(3, ec.getCompeticionId());
            ps.setInt(4, ec.getTemporadaId());

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_MODIFICAR_EQUIPO_COMPETICION, e);
        }

        return resultado;
    }

    @Override
    public int eliminarEquipoCompeticion(int equipoId, int competicionId, int temporadaId) throws BBDDException {
        int resultado = 0;
        final String sql = "DELETE FROM equipo_competicion WHERE " +
                EQUIPO_ID_EQUIPO_COMPETICION + " = ? AND " +
                COMPETICION_ID_EQUIPO_COMPETICION + " = ? AND " +
                TEMPORADA_ID_EQUIPO_COMPETICION + " = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, equipoId);
            ps.setInt(2, competicionId);
            ps.setInt(3, temporadaId);

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_ELIMINAR_EQUIPO_COMPETICION, e);
        }

        return resultado;
    }

    @Override
    public List<EquipoCompeticion> listarEquiposCompeticion() throws BBDDException {
        List<EquipoCompeticion> lista = new ArrayList<>();
        final String sql = "SELECT "
        + EQUIPO_ID_EQUIPO_COMPETICION + ", "
        + NOMBRE_EQUIPO_COMPETICION + ", "
        + COMPETICION_ID_EQUIPO_COMPETICION + ", "
        + NOMBRE_COMPETICION_EQUIPO_COMPETICION + ", "
        + TEMPORADA_ID_EQUIPO_COMPETICION + ", "
        + NOMBRE_TEMPORADA_EQUIPO_COMPETICION + ", "
        + POSICION_EQUIPO_COMPETICION
        + " FROM vista_equipo_competicion_temporada";
    

        try (PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EquipoCompeticion ec = new EquipoCompeticion();
                ec.setEquipoId(rs.getInt(EQUIPO_ID_EQUIPO_COMPETICION));
                ec.setNombreEquipo(rs.getString(NOMBRE_EQUIPO_COMPETICION));
                ec.setCompeticionId(rs.getInt(COMPETICION_ID_EQUIPO_COMPETICION));
                ec.setNombreCompeticion(rs.getString(NOMBRE_COMPETICION_EQUIPO_COMPETICION));
                ec.setTemporadaId(rs.getInt(TEMPORADA_ID_EQUIPO_COMPETICION));
                ec.setNombreTemporada(rs.getString(NOMBRE_TEMPORADA_EQUIPO_COMPETICION));
                ec.setPosicion(rs.getString(POSICION_EQUIPO_COMPETICION));
                lista.add(ec);
            }

        } catch (Exception e) {
            throw new BBDDException(ERROR_LISTAR_EQUIPO_COMPETICION,  e);
        }

        return lista;
    }

    @Override
    public EquipoCompeticion visualizarEquipoCompeticion(int equipoId, int competicionId, int temporadaId)
            throws BBDDException {
                final String query = "SELECT "
                + EQUIPO_ID_EQUIPO_COMPETICION + ", "
                + COMPETICION_ID_EQUIPO_COMPETICION + ", "
                + TEMPORADA_ID_EQUIPO_COMPETICION + ", "
                + POSICION_EQUIPO_COMPETICION + ", "
                + NOMBRE_EQUIPO_COMPETICION + ", "
                + NOMBRE_COMPETICION_EQUIPO_COMPETICION + ", "
                + NOMBRE_TEMPORADA_EQUIPO_COMPETICION
                + " FROM vista_equipo_competicion WHERE "
                + EQUIPO_ID_EQUIPO_COMPETICION + " = ? AND "
                + COMPETICION_ID_EQUIPO_COMPETICION + " = ? AND "
                + TEMPORADA_ID_EQUIPO_COMPETICION + " = ?";
            
        EquipoCompeticion equipoCompeticion = null;

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, equipoId);
            ps.setInt(2, competicionId);
            ps.setInt(3, temporadaId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idEquipo = rs.getInt(EQUIPO_ID_EQUIPO_COMPETICION);
                int idCompeticion = rs.getInt(COMPETICION_ID_EQUIPO_COMPETICION);
                int idTemporada = rs.getInt(TEMPORADA_ID_EQUIPO_COMPETICION);
                String posicion = rs.getString(POSICION_EQUIPO_COMPETICION);

                String nombreEquipo = rs.getString(NOMBRE_EQUIPO_COMPETICION);
                String nombreCompeticion = rs.getString(NOMBRE_COMPETICION_EQUIPO_COMPETICION);
                String nombreTemporada = rs.getString(NOMBRE_TEMPORADA_EQUIPO_COMPETICION);

                equipoCompeticion = new EquipoCompeticion(idEquipo, idCompeticion, idTemporada, posicion);
                equipoCompeticion.setNombreEquipo(nombreEquipo);
                equipoCompeticion.setNombreCompeticion(nombreCompeticion);
                equipoCompeticion.setNombreTemporada(nombreTemporada);
            }

        } catch (Exception e) {
            throw new BBDDException(ERROR_BUSCAR_EQUIPO_COMPETICION, e);
        }

        return equipoCompeticion;
    }

    @Override
    public int insertar(EstadisticasTemporada est) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO estadisticas_temporada (" +
                JUGADOR_ID_ESTADISTICAS + ", " +
                TEMPORADA_ID_ESTADISTICAS + ", " +
                COMPETICION_ID_ESTADISTICAS + ", " +
                EQUIPO_ID_ESTADISTICAS + ", " +
                PARTIDOS_JUGADOS_ESTADISTICAS + ", " +
                GOLES_ESTADISTICAS + ", " +
                ASISTENCIAS_ESTADISTICAS + ") VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, est.getJugadorId());
            ps.setInt(2, est.getTemporadaId());
            ps.setInt(3, est.getCompeticionId());
            ps.setInt(4, est.getEquipoId());
            ps.setInt(5, est.getPartidosJugados());
            ps.setInt(6, est.getGoles());
            ps.setInt(7, est.getAsistencias());

            numRegistrosActualizados = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_INSERTAR_ESTADISTICA, e);
        }

        return numRegistrosActualizados;
    }

    @Override
    public int modificar(EstadisticasTemporada est) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "UPDATE estadisticas_temporada SET " +
                PARTIDOS_JUGADOS_ESTADISTICAS + " = ?, " +
                GOLES_ESTADISTICAS + " = ?, " +
                ASISTENCIAS_ESTADISTICAS + " = ? WHERE " +
                JUGADOR_ID_ESTADISTICAS + " = ? AND " +
                TEMPORADA_ID_ESTADISTICAS + " = ? AND " +
                COMPETICION_ID_ESTADISTICAS + " = ? AND " +
                EQUIPO_ID_ESTADISTICAS + " = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, est.getPartidosJugados());
            ps.setInt(2, est.getGoles());
            ps.setInt(3, est.getAsistencias());
            ps.setInt(4, est.getJugadorId());
            ps.setInt(5, est.getTemporadaId());
            ps.setInt(6, est.getCompeticionId());
            ps.setInt(7, est.getEquipoId());

            numRegistrosActualizados = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_MODIFICAR_ESTADISTICA, e);
        }

        return numRegistrosActualizados;
    }

    @Override
    public int eliminarEstadisticasTemporada(int jugadorId, int temporadaId, int competicionId, int equipoId)
            throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "DELETE FROM estadisticas_temporada WHERE " +
                JUGADOR_ID_ESTADISTICAS + " = ? AND " +
                TEMPORADA_ID_ESTADISTICAS + " = ? AND " +
                COMPETICION_ID_ESTADISTICAS + " = ? AND " +
                EQUIPO_ID_ESTADISTICAS + " = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, jugadorId);
            ps.setInt(2, temporadaId);
            ps.setInt(3, competicionId);
            ps.setInt(4, equipoId);

            numRegistrosActualizados = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_ELIMINAR_ESTADISTICA, e);
        }

        return numRegistrosActualizados;
    }

    @Override
    public List<EstadisticasTemporada> listarEstadisticasTemporada() throws BBDDException {
        final String query = "SELECT "
        + JUGADOR_ID_ESTADISTICAS + ", "
        + NOMBRE_JUGADOR_ESTADISTICAS +", "
        + TEMPORADA_ID_ESTADISTICAS + ", "
        + NOMBRE_TEMPORADA_ESTADISTICAS +", "
        + COMPETICION_ID_ESTADISTICAS + ", "
        + NOMBRE_COMPETICION_ESTADISTICAS +", "
        + EQUIPO_ID_ESTADISTICAS + ", "
        + NOMBRE_EQUIPO_ESTADISTICAS +", "
        + PARTIDOS_JUGADOS_ESTADISTICAS + ", "
        + GOLES_ESTADISTICAS + ", "
        + ASISTENCIAS_ESTADISTICAS
        + " FROM vista_estadisticas_temporada_jugador_equipo";
    
        List<EstadisticasTemporada> lista = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EstadisticasTemporada est = new EstadisticasTemporada();

                est.setJugadorId(rs.getInt(JUGADOR_ID_ESTADISTICAS));
                est.setTemporadaId(rs.getInt(TEMPORADA_ID_ESTADISTICAS));
                est.setCompeticionId(rs.getInt(COMPETICION_ID_ESTADISTICAS));
                est.setEquipoId(rs.getInt(EQUIPO_ID_ESTADISTICAS));
                est.setPartidosJugados(rs.getInt(PARTIDOS_JUGADOS_ESTADISTICAS));
                est.setGoles(rs.getInt(GOLES_ESTADISTICAS));
                est.setAsistencias(rs.getInt(ASISTENCIAS_ESTADISTICAS));

                est.setNombreJugador(rs.getString(NOMBRE_JUGADOR_ESTADISTICAS));
                est.setNombreTemporada(rs.getString(NOMBRE_TEMPORADA_ESTADISTICAS));
                est.setNombreCompeticion(rs.getString(NOMBRE_COMPETICION_ESTADISTICAS));
                est.setNombreEquipo(rs.getString(NOMBRE_EQUIPO_ESTADISTICAS));

                lista.add(est);
            }
        } catch (Exception e) {
            throw new BBDDException(ERROR_LISTAR_ESTADISTICA, e);
        }

        return lista;
    }

    @Override
    public EstadisticasTemporada buscarEstadisticasTotalesPorJugador(int jugadorId) throws BBDDException {
        final String query = "SELECT "
        + JUGADOR_ID_ESTADISTICAS + ", "
        + NOMBRE_JUGADOR_ESTADISTICAS + ", "
        + PARTIDOS_JUGADOS_ESTADISTICAS + ", "
        + GOLES_ESTADISTICAS + ", "
        + ASISTENCIAS_ESTADISTICAS
        + " FROM vista_estadisticas_totales_por_jugador WHERE " + JUGADOR_ID_ESTADISTICAS + " = ?";
    

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, jugadorId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int partidos = rs.getInt(PARTIDOS_JUGADOS_ESTADISTICAS);
                    int goles = rs.getInt(GOLES_ESTADISTICAS);
                    int asistencias = rs.getInt(ASISTENCIAS_ESTADISTICAS);
                    String nombreJugador = rs.getString(NOMBRE_JUGADOR_ESTADISTICAS);

                    EstadisticasTemporada est = new EstadisticasTemporada(jugadorId, 0, 0, 0, partidos, goles,
                            asistencias);
                    est.setNombreJugador(nombreJugador);

                    return est;
                }
            }
        } catch (Exception e) {
            throw new BBDDException(ERROR_BUSCAR_ESTADISTICA_TOTAL , e);
        }

        return null;
    }

    @Override
    public EstadisticasTemporada buscarEstadisticasPorTemporada(int jugadorId, int temporadaId) throws BBDDException {
        final String query = "SELECT "
        + ID_JUGADOR + ", "
        + NOMBRE_JUGADOR_ESTADISTICAS + ", "
        + ID_TEMPORADA + ", "
        + NOMBRE_TEMPORADA_ESTADISTICAS + ", "
        + PARTIDOS_JUGADOS_ESTADISTICAS + ", "
        + GOLES_ESTADISTICAS + ", "
        + ASISTENCIAS_ESTADISTICAS
        + " FROM vista_estadisticas_jugador_temporada WHERE "
        + ID_JUGADOR + " = ? AND "
        + ID_TEMPORADA + " = ?";
    

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, jugadorId);
            ps.setInt(2, temporadaId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int partidos = rs.getInt(PARTIDOS_JUGADOS_ESTADISTICAS);
                    int goles = rs.getInt(GOLES_ESTADISTICAS);
                    int asistencias = rs.getInt(ASISTENCIAS_ESTADISTICAS);
                    String nombreJugador = rs.getString(NOMBRE_JUGADOR_ESTADISTICAS);
                    String nombreTemporada = rs.getString(NOMBRE_TEMPORADA_ESTADISTICAS);

                    EstadisticasTemporada est = new EstadisticasTemporada(jugadorId, temporadaId, 0, 0, partidos, goles,
                            asistencias);
                    est.setNombreJugador(nombreJugador);
                    est.setNombreTemporada(nombreTemporada);

                    return est;
                }
            }
        } catch (Exception e) {
            throw new BBDDException(ERROR_BUSCAR_ESTADISTICA_TEMPORADA , e);
        }

        return null;
    }

    @Override
    public int insertar(Traspaso traspaso) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO traspaso (" +
                JUGADOR_ID_TRASPASO + ", " +
                EQUIPO_ORIGEN_ID_TRASPASO + ", " +
                EQUIPO_DESTINO_ID_TRASPASO + ", " +
                TEMPORADA_ID_TRASPASO + ", " +
                FECHA_TRASPASO + ", " +
                CANTIDAD_TRASPASO + ", " +
                TIPO_TRASPASO +
                ") VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, traspaso.getJugadorId());
            ps.setInt(2, traspaso.getEquipoOrigenId());
            ps.setInt(3, traspaso.getEquipoDestinoId());
            ps.setInt(4, traspaso.getTemporadaId());
            ps.setDate(5, java.sql.Date.valueOf(traspaso.getFechaTraspaso()));
            ps.setDouble(6, traspaso.getCantidad());
            ps.setString(7, traspaso.getTipo());

            numRegistrosActualizados = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_INSERTAR_TRASPASO, e);
        }
        return numRegistrosActualizados;
    }

    @Override
    public int modificar(Traspaso traspaso) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "UPDATE traspaso SET " +
                JUGADOR_ID_TRASPASO + " = ?, " +
                EQUIPO_ORIGEN_ID_TRASPASO + " = ?, " +
                EQUIPO_DESTINO_ID_TRASPASO + " = ?, " +
                TEMPORADA_ID_TRASPASO + " = ?, " +
                FECHA_TRASPASO + " = ?, " +
                CANTIDAD_TRASPASO + " = ?, " +
                TIPO_TRASPASO + " = ? " +
                "WHERE " + ID_TRASPASO + " = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, traspaso.getJugadorId());
            ps.setInt(2, traspaso.getEquipoOrigenId());
            ps.setInt(3, traspaso.getEquipoDestinoId());
            ps.setInt(4, traspaso.getTemporadaId());
            ps.setDate(5, java.sql.Date.valueOf(traspaso.getFechaTraspaso()));
            ps.setDouble(6, traspaso.getCantidad());
            ps.setString(7, traspaso.getTipo());
            ps.setInt(8, traspaso.getIdTraspaso());

            numRegistrosActualizados = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_MODIFICAR_TRASPASO, e);
        }
        return numRegistrosActualizados;
    }

    @Override
    public int eliminarTraspaso(int idTraspaso) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "DELETE FROM traspaso WHERE " + ID_TRASPASO + " = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, idTraspaso);

            numRegistrosActualizados = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new BBDDException(ERROR_ELIMINAR_TRASPASO, e);
        }
        return numRegistrosActualizados;
    }

    @Override
    public List<Traspaso> listarTraspasos() throws BBDDException {
        final String query = "SELECT "
        + ID_TRASPASO + ", "  
        + JUGADOR_ID_TRASPASO + ", "
        + NOMBRE_JUGADOR_TRASPASO + ", "
        + EQUIPO_ORIGEN_ID_TRASPASO + ", "
        + NOMBRE_EQUIPO_ORIGEN_TRASPASO + ", "
        + EQUIPO_DESTINO_ID_TRASPASO + ", "
        + NOMBRE_EQUIPO_DESTINO_TRASPASO + ", "
        + TEMPORADA_ID_TRASPASO + ", "
        + NOMBRE_TEMPORADA_TRASPASO + ", "
        + FECHA_TRASPASO + ", "
        + CANTIDAD_TRASPASO + ", "
        + TIPO_TRASPASO
        + " FROM vista_traspaso_equipo_jugador_temporada";
    
        List<Traspaso> lista = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Traspaso t = new Traspaso();

                t.setIdTraspaso(rs.getInt(ID_TRASPASO));
                t.setJugadorId(rs.getInt(JUGADOR_ID_TRASPASO));
                t.setEquipoOrigenId(rs.getInt(EQUIPO_ORIGEN_ID_TRASPASO));
                t.setEquipoDestinoId(rs.getInt(EQUIPO_DESTINO_ID_TRASPASO));
                t.setTemporadaId(rs.getInt(TEMPORADA_ID_TRASPASO));
                t.setFechaTraspaso(rs.getDate(FECHA_TRASPASO).toLocalDate());
                t.setCantidad(rs.getDouble(CANTIDAD_TRASPASO));
                t.setTipo(rs.getString(TIPO_TRASPASO));

                // Campos de nombres (se mantienen literales porque no hay constantes para
                // ellos)
                t.setNombreJugador(rs.getString(NOMBRE_JUGADOR_TRASPASO));
                t.setNombreEquipoOrigen(rs.getString(NOMBRE_EQUIPO_ORIGEN_TRASPASO));
                t.setNombreEquipoDestino(rs.getString(NOMBRE_EQUIPO_DESTINO_TRASPASO));
                t.setNombreTemporada(rs.getString((NOMBRE_TEMPORADA_TRASPASO)));

                lista.add(t);
            }
        } catch (Exception e) {
            throw new BBDDException(ERROR_LISTAR_TRASPASO, e);
        }

        return lista;
    }

    @Override
    public Traspaso visualizarTraspaso(int idTraspaso) throws BBDDException {
        final String query = "SELECT "
        + ID_TRASPASO + ", "
        + JUGADOR_ID_TRASPASO + ", "
         + NOMBRE_JUGADOR_TRASPASO + ", "
        + EQUIPO_ORIGEN_ID_TRASPASO + ", "
        + EQUIPO_ORIGEN_TRASPASO + ", "
        + EQUIPO_DESTINO_ID_TRASPASO + ", "
        + EQUIPO_DESTINO_TRASPASO + ", "
        + TEMPORADA_ID_TRASPASO + ", "
        + NOMBRE_TEMPORADA_TRASPASO+ ", "
        + FECHA_TRASPASO + ", "
        + CANTIDAD_TRASPASO + ", "
        + TIPO_TRASPASO 
        + " FROM vista_traspaso WHERE " + ID_TRASPASO + " = ?";
    
        Traspaso traspaso = null;

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, idTraspaso);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(ID_TRASPASO);
                int jugadorId = rs.getInt(JUGADOR_ID_TRASPASO);
                Integer equipoOrigenId = rs.getObject(EQUIPO_ORIGEN_ID_TRASPASO) != null
                        ? rs.getInt(EQUIPO_ORIGEN_ID_TRASPASO)
                        : null;
                Integer equipoDestinoId = rs.getObject(EQUIPO_DESTINO_ID_TRASPASO) != null
                        ? rs.getInt(EQUIPO_DESTINO_ID_TRASPASO)
                        : null;
                Integer temporadaId = rs.getObject(TEMPORADA_ID_TRASPASO) != null
                        ? rs.getInt(TEMPORADA_ID_TRASPASO)
                        : null;
                LocalDate fecha = rs.getDate(FECHA_TRASPASO).toLocalDate();
                double cantidad = rs.getDouble(CANTIDAD_TRASPASO);
                String tipo = rs.getString(TIPO_TRASPASO);

                traspaso = new Traspaso(id, jugadorId, equipoOrigenId, equipoDestinoId, temporadaId, fecha, cantidad,
                        tipo);

                // Campos de nombres (se mantienen literales porque no hay constantes)
                traspaso.setNombreJugador(rs.getString(NOMBRE_JUGADOR_TRASPASO));
                traspaso.setNombreEquipoOrigen(rs.getString(EQUIPO_ORIGEN_TRASPASO));
                traspaso.setNombreEquipoDestino(rs.getString(EQUIPO_DESTINO_TRASPASO));
                traspaso.setNombreTemporada(rs.getString(NOMBRE_TEMPORADA_TRASPASO));

            }
        } catch (Exception e) {
            throw new BBDDException(ERROR_BUSCAR_TRASPASO, e);
        }

        return traspaso;
    }

    @Override
    public int insertar(ValorMercadoHistorial historial) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "INSERT INTO valor_mercado_historial (" +
                JUGADOR_ID_HISTORIAL + ", " + FECHA_HISTORIAL + ", " + VALOR_MERCADO_HISTORIAL + ", " + MOTIVO_HISTORIAL
                + ") VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, historial.getJugadorId());
            ps.setDate(2, java.sql.Date.valueOf(historial.getFecha()));
            ps.setDouble(3, historial.getValorMercado());
            ps.setString(4, historial.getMotivo());

            numRegistrosActualizados = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_INSERTAR_HISTORIAL, e);
        }

        return numRegistrosActualizados;
    }

    @Override
    public int modificar(ValorMercadoHistorial historial) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "UPDATE valor_mercado_historial SET " +
                JUGADOR_ID_HISTORIAL + " = ?, " +
                FECHA_HISTORIAL + " = ?, " +
                VALOR_MERCADO_HISTORIAL + " = ?, " +
                MOTIVO_HISTORIAL + " = ? WHERE " + ID_HISTORIAL + " = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, historial.getJugadorId());
            ps.setDate(2, java.sql.Date.valueOf(historial.getFecha()));
            ps.setDouble(3, historial.getValorMercado());
            ps.setString(4, historial.getMotivo());
            ps.setInt(5, historial.getIdHistorial());

            numRegistrosActualizados = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_MODIFICAR_HISTORIAL, e);
        }

        return numRegistrosActualizados;
    }

    @Override
    public int eliminarValorMercadoHistorial(int idHistorial) throws BBDDException {
        int numRegistrosActualizados = 0;

        final String sql = "DELETE FROM valor_mercado_historial WHERE " + ID_HISTORIAL + " = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, idHistorial);
            numRegistrosActualizados = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_ELIMINAR_HISTORIAL, e);
        }

        return numRegistrosActualizados;
    }

    @Override
    public List<ValorMercadoHistorial> listarValorMercadoHistorial() throws BBDDException {
        final String query = "SELECT "
        + ID_HISTORIAL + ", "
        + JUGADOR_ID_HISTORIAL + ", "
        + NOMBRE_JUGADOR_HISTORIAL + ", "
        + FECHA_HISTORIAL + ", "
        + VALOR_MERCADO_HISTORIAL + ", "
        + MOTIVO_HISTORIAL
        + " FROM vista_valor_mercado_historial_jugador";
    
        List<ValorMercadoHistorial> lista = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ValorMercadoHistorial vm = new ValorMercadoHistorial();

                vm.setIdHistorial(rs.getInt(ID_HISTORIAL));
                vm.setJugadorId(rs.getInt(JUGADOR_ID_HISTORIAL));
                vm.setNombreJugador(rs.getString(NOMBRE_JUGADOR_HISTORIAL));
                vm.setFecha(rs.getDate(FECHA_HISTORIAL).toLocalDate());
                vm.setValorMercado(rs.getLong(VALOR_MERCADO_HISTORIAL));
                vm.setMotivo(rs.getString(MOTIVO_HISTORIAL));

                lista.add(vm);
            }
        } catch (Exception e) {
            throw new BBDDException(ERROR_LISTAR_HISTORIAL, e);
        }

        return lista;
    }

    @Override
    public ValorMercadoHistorial visualizarValorMercadoHistorial(int idHistorial) throws BBDDException {
        final String query = "SELECT "
        + ID_HISTORIAL + ", "
        + JUGADOR_ID_HISTORIAL + ", "
        + NOMBRE_JUGADOR_HISTORIAL + ", "
        + FECHA_HISTORIAL + ", "
        + VALOR_MERCADO_HISTORIAL + ", "
        + MOTIVO_HISTORIAL
        + " FROM vista_valor_mercado_historial_jugador WHERE " + ID_HISTORIAL + " = ?";
    
        ValorMercadoHistorial historial = null;

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, idHistorial);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(ID_HISTORIAL);
                int jugadorId = rs.getInt(JUGADOR_ID_HISTORIAL);
                LocalDate fecha = rs.getDate(FECHA_HISTORIAL).toLocalDate();
                long valor = rs.getLong(VALOR_MERCADO_HISTORIAL);
                String motivo = rs.getString(MOTIVO_HISTORIAL);
                String nombreJugador = rs.getString(NOMBRE_JUGADOR_HISTORIAL);

                historial = new ValorMercadoHistorial(id, jugadorId, fecha, valor, motivo);
                historial.setNombreJugador(nombreJugador);
            }
        } catch (Exception e) {
            throw new BBDDException(ERROR_BUSCAR_HISTORIAL, e);
        }

        return historial;
    }

    @Override
    public int insertar(Usuario usuario) throws BBDDException {
        int filas = 0;

        final String sql = "INSERT INTO usuario (" +
                NOMBRE_USUARIO + ", " +
                EMAIL_USUARIO + ", " +
                CONTRASENA_USUARIO + ", " +
                ROL_USUARIO + ") VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getContrasena());
            ps.setString(4, usuario.getRol());

            filas = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_INSERTAR_USUARIO, e);
        }

        return filas;
    }

    @Override
    public int eliminar(Usuario usuario) throws BBDDException {
        int filas = 0;

        final String sql = "DELETE FROM usuario WHERE " + NOMBRE_USUARIO + " = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, usuario.getNombre());
            filas = ps.executeUpdate();
        } catch (Exception e) {
            throw new BBDDException(ERROR_ELIMINAR_USUARIO, e);
        }

        return filas;
    }

    @Override
    public int obtenerIdPorNombreJugador(String nombre) throws BBDDException {
        final String sql = "SELECT " + ID_JUGADOR + " FROM vista_jugador WHERE " + NOMBRE_JUGADOR + " = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(ID_JUGADOR);
                } else {
                    throw new BBDDException(ERROR_JUGADOR + nombre);
                }
            }
        } catch (Exception e) {
            throw new BBDDException(ERROR_OBTENER_ID_JUGADOR, e);
        }
    }

    @Override
    public int obtenerIdPorNombreEquipo(String nombre) throws BBDDException {
        final String sql = "SELECT " + ID_EQUIPO + " FROM vista_equipo WHERE " + NOMBRE_EQUIPO + " = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(ID_EQUIPO);
                } else {
                    throw new BBDDException(ERROR_EQUIPO + nombre);
                }
            }
        } catch (Exception e) {
            throw new BBDDException(ERROR_OBTENER_ID_EQUIPO , e);
        }
    }

    @Override
    public int obtenerIdPorNombreCompeticion(String nombre) throws BBDDException {
        final String sql = "SELECT " + ID_COMPETICION + " FROM vista_competicion WHERE " + NOMBRE_COMPETICION + " = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(ID_COMPETICION);
                } else {
                    throw new BBDDException(ERROR_COMPETICION + nombre);
                }
            }
        } catch (Exception e) {
            throw new BBDDException(ERROR_OBTENER_ID_COMPETICION ,  e);
        }
    }

    @Override
    public int obtenerIdPorNombreTemporada(String nombre) throws BBDDException {
        final String sql = "SELECT " + ID_TEMPORADA + " FROM vista_temporada WHERE " + NOMBRE_TEMPORADA + " = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(ID_TEMPORADA);
                } else {
                    throw new BBDDException(ERROR_TEMPORADA + nombre);
                }
            }
        } catch (Exception e) {
            throw new BBDDException(ERROR_OBTENER_ID_TEMPORADA , e);
        }
    }

    @Override
    public EstadisticasTemporada obtenerEstadisticaPorId(int jugadorId, int temporadaId, int competicionId,
            int equipoId) throws BBDDException {
                final String query = "SELECT "
                + JUGADOR_ID_ESTADISTICAS + ", "
                + NOMBRE_JUGADOR_ESTADISTICAS + ", "
                + TEMPORADA_ID_ESTADISTICAS + ", "
                + NOMBRE_TEMPORADA_ESTADISTICAS + ", "
                + COMPETICION_ID_ESTADISTICAS + ", "
                + NOMBRE_COMPETICION_ESTADISTICAS + ", "
                + EQUIPO_ID_ESTADISTICAS + ", "
                + NOMBRE_EQUIPO_ESTADISTICAS + ", "
                + PARTIDOS_JUGADOS_ESTADISTICAS + ", "
                + GOLES_ESTADISTICAS + ", "
                + ASISTENCIAS_ESTADISTICAS           
                + " FROM vista_estadisticas_temporada_jugador_equipo WHERE "
                + JUGADOR_ID_ESTADISTICAS + " = ? AND "
                + TEMPORADA_ID_ESTADISTICAS + " = ? AND "
                + COMPETICION_ID_ESTADISTICAS + " = ? AND "
                + EQUIPO_ID_ESTADISTICAS + " = ?";
            

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, jugadorId);
            ps.setInt(2, temporadaId);
            ps.setInt(3, competicionId);
            ps.setInt(4, equipoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    EstadisticasTemporada est = new EstadisticasTemporada();

                    est.setJugadorId(jugadorId);
                    est.setTemporadaId(temporadaId);
                    est.setCompeticionId(competicionId);
                    est.setEquipoId(equipoId);

                    est.setPartidosJugados(rs.getInt(PARTIDOS_JUGADOS_ESTADISTICAS));
                    est.setGoles(rs.getInt(GOLES_ESTADISTICAS));
                    est.setAsistencias(rs.getInt(ASISTENCIAS_ESTADISTICAS));

                    est.setNombreJugador(rs.getString(NOMBRE_JUGADOR_ESTADISTICAS));
                    est.setNombreTemporada(rs.getString(NOMBRE_TEMPORADA_ESTADISTICAS));
                    est.setNombreCompeticion(rs.getString(NOMBRE_COMPETICION_ESTADISTICAS));
                    est.setNombreEquipo(rs.getString(NOMBRE_EQUIPO_ESTADISTICAS));

                    return est;
                }
            }
        } catch (Exception e) {
            throw new BBDDException(ERROR_OBTENER_ID_ESTADISTICA , e);
        }

        return null;
    }

    @Override
    public Usuario buscarUsuarioPorNombre(String nombre) throws BBDDException {
        final String query = "SELECT "
        + ID_USUARIO  + ", "
        + NOMBRE_USUARIO + ", "
        + EMAIL_USUARIO + ", "
        + CONTRASENA_USUARIO + ", "
        + ROL_USUARIO
        + " FROM usuario WHERE " + NOMBRE_USUARIO + " = ?";
    

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario user = new Usuario();
                    user.setIdUsuario(rs.getInt(ID_USUARIO));
                    user.setNombre(rs.getString(NOMBRE_USUARIO));
                    user.setEmail(rs.getString(EMAIL_USUARIO));
                    user.setContrasenaHash(rs.getString(CONTRASENA_USUARIO));
                    user.setRol(rs.getString(ROL_USUARIO));
                    return user;
                }
            }
        } catch (Exception e) {
            throw new BBDDException(ERROR_BUSCAR_USUARIO_NOMBRE , e);
        }
        return null;
    }

    @Override
    public Usuario buscarUsuarioPorEmail(String email) throws BBDDException {
        final String query = "SELECT "
    + ID_USUARIO  + ", "
    + NOMBRE_USUARIO + ", "
    + EMAIL_USUARIO + ", "
    + CONTRASENA_USUARIO + ", "
    + ROL_USUARIO
    + " FROM usuario WHERE " + EMAIL_USUARIO + " = ?";


        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario user = new Usuario();
                    user.setIdUsuario(rs.getInt(ID_USUARIO));
                    user.setNombre(rs.getString(NOMBRE_USUARIO));
                    user.setEmail(rs.getString(EMAIL_USUARIO));
                    user.setContrasena(rs.getString(CONTRASENA_USUARIO));
                    user.setRol(rs.getString(ROL_USUARIO));
                    return user;
                }
            }
        } catch (Exception e) {
            throw new BBDDException(ERROR_BUSCAR_USUARIO_EMAIL , e);
        }
        return null;
    }
}
