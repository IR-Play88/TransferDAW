package es.tierno.daw.trasnferdaw.model.entities;

import java.sql.Date;

public class Equipo_Estadio {
    private int equipoId;
    private int estadioId;
    private Date fechaInicio;
    private Date fechaFin;

    public Equipo_Estadio() {
    }

    public Equipo_Estadio(int equipoId, int estadioId, Date fechaInicio, Date fechaFin) {
        this.equipoId = equipoId;
        this.estadioId = estadioId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(int equipoId) {
        this.equipoId = equipoId;
    }

    public int getEstadioId() {
        return estadioId;
    }

    public void setEstadioId(int estadioId) {
        this.estadioId = estadioId;
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
        return "EquipoEstadio{" +
                "equipoId=" + equipoId +
                ", estadioId=" + estadioId +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }

}
