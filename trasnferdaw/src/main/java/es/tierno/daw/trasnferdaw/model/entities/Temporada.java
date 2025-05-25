package es.tierno.daw.trasnferdaw.model.entities;

import java.time.LocalDate;

public class Temporada {
    private Integer idTemporada;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Temporada() {
    }

    public Temporada(String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Temporada(Integer idTemporada, String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        this.idTemporada = idTemporada;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Integer getIdTemporada() {
        return idTemporada;
    }

    public void setIdTemporada(Integer idTemporada) {
        this.idTemporada = idTemporada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        return "Temporada{" +
                "id_temporada=" + idTemporada +
                ", nombre=" + nombre +
                ", fecha_inicio=" + fechaInicio +
                ", fecha_fin=" + fechaFin +
                '}';
    }

}
