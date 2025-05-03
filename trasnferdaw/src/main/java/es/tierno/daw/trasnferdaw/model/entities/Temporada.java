package es.tierno.daw.trasnferdaw.model.entities;

import java.time.LocalDate;

public class Temporada {
    private int id_temporada;
    private String nombre;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private String descripcion;

    public Temporada() {
    }

    public Temporada(int id_temporada, String nombre, LocalDate fecha_inicio, LocalDate fecha_fin, String descripcion) {
        this.id_temporada = id_temporada;
        this.nombre = nombre;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.descripcion = descripcion;
    }

    public int getId_temporada() {
        return id_temporada;
    }

    public void setId_temporada(int id_temporada) {
        this.id_temporada = id_temporada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Temporada{" +
                "id_temporada=" + id_temporada +
                ", nombre=" + nombre +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                ", descripcion=" + descripcion +
                '}';
    }

}
