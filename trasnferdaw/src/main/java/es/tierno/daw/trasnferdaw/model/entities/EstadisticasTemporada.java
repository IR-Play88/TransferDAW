package es.tierno.daw.trasnferdaw.model.entities;

import es.tierno.daw.trasnferdaw.model.exception.BBDDException;

/**
 * La clase EstadisticasTemporada representa las estadisticas de un jugador de
 * TransferDAW.
 * \author Iván Rafael Redondo.
 */
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

    /**
     * Constructor por defecto de la clase EstadisticasTemporada.
     * Inicializa una nueva instancia sin establecer valores para sus atributos.
     * 
     * @throws BBDDException
     */
    public EstadisticasTemporada() throws BBDDException {
    }

    /**
     * Constructor que inicializa las estadísticas de un jugador en una temporada
     * concreta, dentro de una competición y equipo.
     * 
     * @param jugadorId       ID del jugador
     * @param temporadaId     ID de la temporada
     * @param competicionId   ID de la competición
     * @param equipoId        ID del equipo en el que jugó el jugador durante la
     *                        temporada
     * @param partidosJugados Número de partidos que disputó el jugador
     * @param goles           Número de goles marcados por el jugador
     * @param asistencias     Número de asistencias realizadas por el jugador
     * @throws BBDDException
     */
    public EstadisticasTemporada(Integer jugadorId, Integer temporadaId, Integer competicionId, Integer equipoId,
            int partidosJugados,
            int goles, int asistencias) throws BBDDException {
        this.jugadorId = jugadorId;
        this.temporadaId = temporadaId;
        this.competicionId = competicionId;
        this.partidosJugados = partidosJugados;
        this.equipoId = equipoId;
        this.goles = goles;
        this.asistencias = asistencias;
    }

    public Integer getJugadorId() throws BBDDException {
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
