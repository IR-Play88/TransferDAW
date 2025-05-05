package es.tierno.daw.trasnferdaw.model.entities;

import java.time.LocalDate;

public class EquipoEstadio {
    private int equipoId;
    private int estadioId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public EquipoEstadio() {
    }

    public EquipoEstadio(int equipoId, int estadioId, LocalDate fechaInicio, LocalDate fechaFin) {
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
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
