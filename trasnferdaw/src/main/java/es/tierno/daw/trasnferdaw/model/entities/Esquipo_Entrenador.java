package es.tierno.daw.trasnferdaw.model.entities;

import java.sql.Date;

public class Esquipo_Entrenador {
    private int entrenadorId;
    private int equipoId;
    private Date fechaInicio;
    private Date fechaFin;

    public Esquipo_Entrenador() {
    }

    public Esquipo_Entrenador(int entrenadorId, int equipoId, Date fechaInicio, Date fechaFin) {
        this.entrenadorId = entrenadorId;
        this.equipoId = equipoId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getEntrenadorId() {
        return entrenadorId;
    }

    public void setEntrenadorId(int entrenadorId) {
        this.entrenadorId = entrenadorId;
    }

    public int getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(int equipoId) {
        this.equipoId = equipoId;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "EquipoEntrenador{" +
                "entrenadorId=" + entrenadorId +
                ", equipoId=" + equipoId +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }
}
