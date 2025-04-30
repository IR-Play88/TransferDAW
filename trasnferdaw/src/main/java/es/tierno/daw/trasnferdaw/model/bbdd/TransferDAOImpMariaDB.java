package es.tierno.daw.trasnferdaw.model.bbdd;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.tierno.daw.trasnferdaw.model.entities.EstadisticasTemporada;
import es.tierno.daw.trasnferdaw.model.entities.Jugador;

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
    public void insertar(Jugador jugador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public void modificar(Jugador jugador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificar'");
    }

    @Override
    public void eliminar(int idJugador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    @Override
    public List<Jugador> listarJugadores() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarJugadores'");
    }

    @Override
    public Jugador buscarPorId(int idJugador) throws SQLException {
        // Sentencia SQL para obtener los datos del jugador
        String sql = "SELECT * FROM Jugador WHERE id_jugador = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idJugador); // Establecer el valor del parámetro en la consulta

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Mapear los resultados del ResultSet a un objeto Jugador
                    return new Jugador(
                            rs.getInt("id_jugador"),
                            rs.getString("nombre"),
                            rs.getString("alias"),
                            rs.getString("foto_url"),
                            rs.getDate("fecha_nacimiento").toLocalDate(),
                            rs.getString("nacionalidad"),
                            rs.getFloat("altura"),
                            rs.getFloat("peso"),
                            rs.getString("pie_dominante"),
                            rs.getFloat("valor_mercado"),
                            rs.getInt("representante_id"),
                            rs.getInt("seleccion_id"));
                } else {
                    throw new SQLException("Jugador no encontrado con ID: " + idJugador);
                }
            }
        }
    }

    @Override
    public List<EstadisticasTemporada> listarTodos() throws SQLException {
        List<EstadisticasTemporada> estadisticas = new ArrayList<>();
        String sql = "SELECT * FROM Estadisticas_Temporada";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                estadisticas.add(new EstadisticasTemporada(
                        rs.getInt("jugador_id"),
                        rs.getInt("temporada_id"),
                        rs.getInt("competicion_id"),
                        rs.getInt("equipo_id"),
                        rs.getInt("goles"),
                        rs.getInt("asistencias"),
                        rs.getInt("minutos"),
                        rs.getInt("partidos_jugados"),
                        rs.getInt("amarillas"),
                        rs.getInt("rojas"),
                        rs.getInt("promedio_goles")));
            }
        }

        return estadisticas;
    }

    @Override
    public List<EstadisticasTemporada> buscarPorJugador(int jugadorId) throws SQLException {
        List<EstadisticasTemporada> lista = new ArrayList<>();
        String sql = "SELECT * FROM Estadisticas_Temporada WHERE jugador_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, jugadorId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    EstadisticasTemporada est = new EstadisticasTemporada(
                            rs.getInt("jugador_id"),
                            rs.getInt("temporada_id"),
                            rs.getInt("competicion_id"),
                            rs.getInt("equipo_id"),
                            rs.getInt("goles"),
                            rs.getInt("asistencias"),
                            rs.getInt("minutos"),
                            rs.getInt("partidos_jugados"),
                            rs.getInt("amarillas"),
                            rs.getInt("rojas"),
                            rs.getInt("promedio_goles"));
                    lista.add(est);
                }
            }
        }

        return lista;
    }

}
