package es.tierno.daw.trasnferdaw.model.entities;

import java.time.LocalDate;

public class ValorMercadoHistorial {
    private int idHistorial;
    private int jugadorId;
    private LocalDate fecha;
    private float valorMercado;
    private String motivo;

    public ValorMercadoHistorial() {
    }

    public ValorMercadoHistorial(int idHistorial, int jugadorId, LocalDate fecha, float valorMercado, String motivo) {
        this.idHistorial = idHistorial;
        this.jugadorId = jugadorId;
        this.fecha = fecha;
        this.valorMercado = valorMercado;
        this.motivo = motivo;
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public int getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(int jugadorId) {
        this.jugadorId = jugadorId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public float getValorMercado() {
        return valorMercado;
    }

    public void setValorMercado(float valorMercado) {
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
