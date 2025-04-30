package es.tierno.daw.trasnferdaw.model.entities;

public class EquipoCompeticion {
    private int equipoId;
    private int competicionId;
    private int temporadaId;
    private int rango;

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

    public int getCompeticionId() {
        return competicionId;
    }

    public void setCompeticionId(int competicionId) {
        this.competicionId = competicionId;
    }

    public int getTemporadaId() {
        return temporadaId;
    }

    public void setTemporadaId(int temporadaId) {
        this.temporadaId = temporadaId;
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
