package es.tierno.daw.trasnferdaw.model.entities;

import java.time.LocalDate;

public class Traspaso {
    private int idTraspaso;
    private int jugadorId;
    private Integer equipoOrigenId;
    private Integer equipoDestinoId;
    private Integer temporadaId;
    private LocalDate fechaTraspaso;
    private float cantidad;
    private String tipo;

    public Traspaso() {
    }

    public Traspaso(int idTraspaso, int jugadorId, Integer equipoOrigenId, Integer equipoDestinoId, Integer temporadaId,
    LocalDate fechaTraspaso, float cantidad,String tipo) {
        this.idTraspaso = idTraspaso;
        this.jugadorId = jugadorId;
        this.equipoOrigenId = equipoOrigenId;
        this.equipoDestinoId = equipoDestinoId;
        this.temporadaId = temporadaId;
        this.fechaTraspaso = fechaTraspaso;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public int getIdTraspaso() {
        return idTraspaso;
    }

    public void setIdTraspaso(int idTraspaso) {
        this.idTraspaso = idTraspaso;
    }

    public int getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(int jugadorId) {
        this.jugadorId = jugadorId;
    }

    public Integer getEquipoOrigenId() {
        return equipoOrigenId;
    }

    public void setEquipoOrigenId(Integer equipoOrigenId) {
        this.equipoOrigenId = equipoOrigenId;
    }

    public Integer getEquipoDestinoId() {
        return equipoDestinoId;
    }

    public void setEquipoDestinoId(Integer equipoDestinoId) {
        this.equipoDestinoId = equipoDestinoId;
    }

    public Integer getTemporadaId() {
        return temporadaId;
    }

    public void setTemporadaId(Integer temporadaId) {
        this.temporadaId = temporadaId;
    }

    public LocalDate getFechaTraspaso() {
        return fechaTraspaso;
    }

    public void setFechaTraspaso(LocalDate fechaTraspaso) {
        this.fechaTraspaso = fechaTraspaso;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Traspaso{" +
                "idTraspaso=" + idTraspaso +
                ", tipo='" + tipo + '\'' +
                '}';
    }

}
