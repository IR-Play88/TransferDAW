package es.tierno.daw.trasnferdaw.model.entities;

public class EquipoCompeticion {
    private int equipoId;
    private String nombreEquipo;
    private int competicionId;
    private String nombreCompeticion;
    private int temporadaId;
    private String nombreTemporada;
    private Integer rango;


    public EquipoCompeticion() {
    }

    public EquipoCompeticion(int equipoId, int competicionId, int temporadaId, int rango) {
        this.equipoId = equipoId;
        this.competicionId = competicionId;
        this.temporadaId = temporadaId;
        this.rango = rango;
    }

    public int getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(int equipoId) {
        this.equipoId = equipoId;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public int getCompeticionId() {
        return competicionId;
    }

    public void setCompeticionId(int competicionId) {
        this.competicionId = competicionId;
    }

    public String getNombreCompeticion() {
        return nombreCompeticion;
    }

    public void setNombreCompeticion(String nombreCompeticion) {
        this.nombreCompeticion = nombreCompeticion;
    }

    public int getTemporadaId() {
        return temporadaId;
    }

    public void setTemporadaId(int temporadaId) {
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
