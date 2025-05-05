package es.tierno.daw.trasnferdaw.model.entities;

import java.time.LocalDate;

public class EquipoEntrenador {
    private int entrenadorId;
    private int equipoId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public EquipoEntrenador() {
    }

    public EquipoEntrenador(int entrenadorId, int equipoId, LocalDate fechaInicio, LocalDate fechaFin) {
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
        return "EquipoEntrenador{" +
                "entrenadorId=" + entrenadorId +
                ", equipoId=" + equipoId +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }
}
