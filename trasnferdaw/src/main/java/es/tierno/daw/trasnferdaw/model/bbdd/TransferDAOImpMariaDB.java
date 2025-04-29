package es.tierno.daw.trasnferdaw.model.bbdd;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import es.tierno.daw.trasnferdaw.model.entities.Jugador;

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
}
