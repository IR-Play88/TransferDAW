package es.tierno.daw.trasnferdaw.model.entities;

public class Estadisticas_Temporada {
    private int jugadorId;
    private int temporadaId;
    private int competicionId;
    private int equipoId;
    private int goles;
    private int asistencias;
    private int minutos;
    private int partidosJugados;
    private int amarillas;
    private int rojas;
    private int promedioGoles;

    public Estadisticas_Temporada() {
    }

    public Estadisticas_Temporada(int jugadorId, int temporadaId, int competicionId, int equipoId, int goles,
            int asistencias, int minutos, int partidosJugados, int amarillas, int rojas, int promedioGoles) {
        this.jugadorId = jugadorId;
        this.temporadaId = temporadaId;
        this.competicionId = competicionId;
        this.equipoId = equipoId;
        this.goles = goles;
        this.asistencias = asistencias;
        this.minutos = minutos;
        this.partidosJugados = partidosJugados;
        this.amarillas = amarillas;
        this.rojas = rojas;
        this.promedioGoles = promedioGoles;
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

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public int getAmarillas() {
        return amarillas;
    }

    public void setAmarillas(int amarillas) {
        this.amarillas = amarillas;
    }

    public int getRojas() {
        return rojas;
    }

    public void setRojas(int rojas) {
        this.rojas = rojas;
    }

    public int getPromedioGoles() {
        return promedioGoles;
    }

    public void setPromedioGoles(int promedioGoles) {
        this.promedioGoles = promedioGoles;
    }

    @Override
    public String toString() {
        return "EstadisticasTemporada{" +
                "jugadorId=" + jugadorId +
                ", temporadaId=" + temporadaId +
                ", competicionId=" + competicionId +
                ", equipoId=" + equipoId +
                ", goles=" + goles +
                ", asistencias=" + asistencias +
                ", minutos=" + minutos +
                ", partidosJugados=" + partidosJugados +
                ", amarillas=" + amarillas +
                ", rojas=" + rojas +
                ", promedioGoles=" + promedioGoles +
                '}';
    }

}
