package es.tierno.daw.trasnferdaw.model.entities;

public class EstadisticasTemporada {
    private int jugadorId;
    private int temporadaId;
    private int competicionId;
    private int equipoId;
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
