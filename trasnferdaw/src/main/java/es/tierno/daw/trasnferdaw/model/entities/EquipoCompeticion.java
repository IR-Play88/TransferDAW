package es.tierno.daw.trasnferdaw.model.entities;

import es.tierno.daw.trasnferdaw.model.exception.BBDDException;

/**
 * La clase EquipoCompeticion representa un equipo en una competición de
 * TransferDAW.
 * \author Iván Rafael Redondo.
 */
public class EquipoCompeticion {
    private Integer equipoId;
    private String nombreEquipo;
    private Integer competicionId;
    private String nombreCompeticion;
    private Integer temporadaId;
    private String nombreTemporada;
    private String posicion;

    /**
     * Constructor por defecto de la clase EquipoCompeticion.
     * Inicializa una nueva instancia sin establecer valores para sus atributos.
     * 
     * @throws BBDDException
     */
    public EquipoCompeticion() throws BBDDException {
    }

    /**
     * Constructor que inicializa la relación entre un equipo, una competición y una
     * temporada.
     * 
     * @param equipoId      ID del equipo
     * @param competicionId ID de la competición
     * @param temporadaId   ID de la temporada
     * @param posicion      Posición del equipo en esa competición durante la
     *                      temporada
     * @throws BBDDException
     */
    public EquipoCompeticion(Integer equipoId, Integer competicionId, Integer temporadaId, String posicion)
            throws BBDDException {
        this.equipoId = equipoId;
        this.competicionId = competicionId;
        this.temporadaId = temporadaId;
        this.posicion = posicion;
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

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return "EquipoCompeticion{" +
                "equipoId=" + equipoId +
                ", competicionId=" + competicionId +
                ", temporadaId=" + temporadaId +
                ", posicion=" + posicion +
                '}';
    }

}
