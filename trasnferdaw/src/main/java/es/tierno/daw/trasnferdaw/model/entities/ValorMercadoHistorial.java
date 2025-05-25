package es.tierno.daw.trasnferdaw.model.entities;

import java.time.LocalDate;

public class ValorMercadoHistorial {
    private Integer idHistorial;
    private Integer jugadorId;
    private String nombreJugador;
    private LocalDate fecha;
    private double valorMercado;
    private String motivo;

    public ValorMercadoHistorial() {
    }

    public ValorMercadoHistorial(Integer jugadorId, LocalDate fecha, double valorMercado, String motivo) {
        this.jugadorId = jugadorId;
        this.fecha = fecha;
        this.valorMercado = valorMercado;
        this.motivo = motivo;
    }

    public ValorMercadoHistorial(Integer idHistorial, Integer jugadorId, LocalDate fecha, double valorMercado, String motivo) {
        this.idHistorial = idHistorial;
        this.jugadorId = jugadorId;
        this.fecha = fecha;
        this.valorMercado = valorMercado;
        this.motivo = motivo;
    }

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getValorMercado() {
        return valorMercado;
    }

    public void setValorMercado(double valorMercado) {
        this.valorMercado = valorMercado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "ValorMercadoHistorial{" +
                "idHistorial=" + idHistorial +
                ", valorMercado=" + valorMercado +
                '}';
    }

}
