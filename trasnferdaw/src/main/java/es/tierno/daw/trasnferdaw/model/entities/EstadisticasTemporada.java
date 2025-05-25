package es.tierno.daw.trasnferdaw.model.entities;

public class EstadisticasTemporada {
    private Integer jugadorId;
    private String nombreJugador;
    private Integer temporadaId;
    private String nombreTemporada;
    private Integer competicionId;
    private String nombreCompeticion;
    private Integer equipoId;
    private String nombreEquipo;
    private int partidosJugados;
    private int goles;
    private int asistencias;

    public EstadisticasTemporada() {
    }

    public EstadisticasTemporada(Integer jugadorId, Integer temporadaId, Integer competicionId, Integer equipoId, int partidosJugados,
            int goles, int asistencias) {
        this.jugadorId = jugadorId;
        this.temporadaId = temporadaId;
        this.competicionId = competicionId;
        this.partidosJugados = partidosJugados;
        this.equipoId = equipoId;
        this.goles = goles;
        this.asistencias = asistencias;
    }

    public Integer getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(Integer jugadorId) {
        this.jugadorId = jugadorId;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public Integer getTemporadaId() {
        return temporadaId;
    }

    public void setTemporadaId(Integer temporadaId) {
        this.temporadaId = temporadaId;
    }

    public String getNombreTemporada() {
        return nombreTemporada;
    }

    public void setNombreTemporada(String nombreTemporada) {
        this.nombreTemporada = nombreTemporada;
    }

    public Integer getCompeticionId() {
        return competicionId;
    }

    public void setCompeticionId(Integer competicionId) {
        this.competicionId = competicionId;
    }

    public String getNombreCompeticion() {
        return nombreCompeticion;
    }

    public void setNombreCompeticion(String nombreCompeticion) {
        this.nombreCompeticion = nombreCompeticion;
    }

    public Integer getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Integer equipoId) {
        this.equipoId = equipoId;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    @Override
    public String toString() {
        return "EstadisticasTemporada{" +
                "  Jugador=" + nombreJugador +
                ", Competicion=" + nombreCompeticion +
                ", Temporada=" + nombreTemporada +
                ", Equipo=" + nombreEquipo +
                ", Partidos jugados=" + partidosJugados +
                ", Goles=" + goles +
                ", Asistencias=" + asistencias
                + '}';
    }
}
