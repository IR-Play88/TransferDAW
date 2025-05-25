package es.tierno.daw.trasnferdaw.model.entities;

public class EquipoCompeticion {
    private Integer equipoId;
    private String nombreEquipo;
    private Integer competicionId;
    private String nombreCompeticion;
    private Integer temporadaId;
    private String nombreTemporada;
    private int rango;

    public EquipoCompeticion() {
    }

    public EquipoCompeticion(Integer equipoId, Integer competicionId, Integer temporadaId, int rango) {
        this.equipoId = equipoId;
        this.competicionId = competicionId;
        this.temporadaId = temporadaId;
        this.rango = rango;
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

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    @Override
    public String toString() {
        return "EquipoCompeticion{" +
                "equipoId=" + equipoId +
                ", competicionId=" + competicionId +
                ", temporadaId=" + temporadaId +
                ", rango=" + rango +
                '}';
    }

}
