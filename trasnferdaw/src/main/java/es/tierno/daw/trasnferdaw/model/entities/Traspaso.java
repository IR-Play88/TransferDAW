package es.tierno.daw.trasnferdaw.model.entities;

import java.time.LocalDate;

public class Traspaso {
    private Integer idTraspaso;
    private Integer jugadorId;
    private String nombreJugador;
    private Integer equipoOrigenId;
    private String nombreEquipoOrigen;
    private Integer equipoDestinoId;
    private String nombreEquipoDestino;
    private Integer temporadaId;
    private String nombreTemporada;
    private LocalDate fechaTraspaso;
    private double cantidad;
    private String tipo;

    public Traspaso() {
    }

    public Traspaso(Integer jugadorId, Integer equipoOrigenId, Integer equipoDestinoId, Integer temporadaId,
    LocalDate fechaTraspaso, double cantidad,String tipo) {
        this.jugadorId = jugadorId;
        this.equipoOrigenId = equipoOrigenId;
        this.equipoDestinoId = equipoDestinoId;
        this.temporadaId = temporadaId;
        this.fechaTraspaso = fechaTraspaso;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public Traspaso(Integer idTraspaso, int jugadorId, Integer equipoOrigenId, Integer equipoDestinoId, Integer temporadaId,
    LocalDate fechaTraspaso, double cantidad,String tipo) {
        this.idTraspaso = idTraspaso;
        this.jugadorId = jugadorId;
        this.equipoOrigenId = equipoOrigenId;
        this.equipoDestinoId = equipoDestinoId;
        this.temporadaId = temporadaId;
        this.fechaTraspaso = fechaTraspaso;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public Integer getIdTraspaso() {
        return idTraspaso;
    }

    public void setIdTraspaso(Integer idTraspaso) {
        this.idTraspaso = idTraspaso;
    }

    public Integer getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(Integer jugadorId) {
        this.jugadorId = jugadorId;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public Integer getEquipoOrigenId() {
        return equipoOrigenId;
    }

    public void setEquipoOrigenId(Integer equipoOrigenId) {
        this.equipoOrigenId = equipoOrigenId;
    }

    public String getNombreEquipoOrigen() {
        return nombreEquipoOrigen;
    }

    public void setNombreEquipoOrigen(String nombreEquipoOrigen) {
        this.nombreEquipoOrigen = nombreEquipoOrigen;
    }

    public Integer getEquipoDestinoId() {
        return equipoDestinoId;
    }

    public void setEquipoDestinoId(Integer equipoDestinoId) {
        this.equipoDestinoId = equipoDestinoId;
    }

    public String getNombreEquipoDestino() {
        return nombreEquipoDestino;
    }

    public void setNombreEquipoDestino(String nombreEquipoDestino) {
        this.nombreEquipoDestino = nombreEquipoDestino;
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

    public LocalDate getFechaTraspaso() {
        return fechaTraspaso;
    }

    public void setFechaTraspaso(LocalDate fechaTraspaso) {
        this.fechaTraspaso = fechaTraspaso;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
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
