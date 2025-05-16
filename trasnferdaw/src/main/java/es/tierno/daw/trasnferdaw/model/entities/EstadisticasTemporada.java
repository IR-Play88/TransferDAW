package es.tierno.daw.trasnferdaw.model.entities;

public class EstadisticasTemporada {
    private int jugadorId;
    private String nombreJugador;
    private int temporadaId;
    private String nombreTemporada;
    private int competicionId;
    private String nombreCompeticion;
    private int equipoId;
    private String nombreEquipo;
    private int partidosJugados;
    private int goles;
    private int asistencias;

    public EstadisticasTemporada() {
    }

    public EstadisticasTemporada(int jugadorId, int temporadaId, int competicionId, int equipoId, int partidosJugados,
            int goles, int asistencias) {
        this.jugadorId = jugadorId;
        this.temporadaId = temporadaId;
        this.competicionId = competicionId;
        this.partidosJugados = partidosJugados;
        this.equipoId = equipoId;
        this.goles = goles;
        this.asistencias = asistencias;
    }

    public int getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(int jugadorId) {
        this.jugadorId = jugadorId;
    }

    public int getTemporadaId() {
        return temporadaId;
    }

    public void setTemporadaId(int temporadaId) {
        this.temporadaId = temporadaId;
    }

    public int getCompeticionId() {
        return competicionId;
    }

    public void setCompeticionId(int competicionId) {
        this.competicionId = competicionId;
    }

    public int getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(int equipoId) {
        this.equipoId = equipoId;
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

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public String getNombreTemporada() {
        return nombreTemporada;
    }

    public void setNombreTemporada(String nombreTemporada) {
        this.nombreTemporada = nombreTemporada;
    }

    public String getNombreCompeticion() {
        return nombreCompeticion;
    }

    public void setNombreCompeticion(String nombreCompeticion) {
        this.nombreCompeticion = nombreCompeticion;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    @Override
    public String toString() {
        return "EstadisticasTemporada{" +
                "jugadorId=" + jugadorId +
                ", temporadaId=" + temporadaId +
                ", competicionId=" + competicionId +
                ", equipoId=" + equipoId +
                ", goles=" + goles +
                ", asistencias=" + asistencias
                + '}';
    }

}
