package es.tierno.daw.trasnferdaw.model.entities;

public class Jugador_Posicion {
    private int jugadorId;
    private int posicionId;
    private int temporadaId;
    private boolean esPrincipal;

    public Jugador_Posicion() {
    }

    public Jugador_Posicion(int jugadorId, int posicionId, int temporadaId, boolean esPrincipal) {
        this.jugadorId = jugadorId;
        this.posicionId = posicionId;
        this.temporadaId = temporadaId;
        this.esPrincipal = esPrincipal;
    }

    public int getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(int jugadorId) {
        this.jugadorId = jugadorId;
    }

    public int getPosicionId() {
        return posicionId;
    }

    public void setPosicionId(int posicionId) {
        this.posicionId = posicionId;
    }

    public int getTemporadaId() {
        return temporadaId;
    }

    public void setTemporadaId(int temporadaId) {
        this.temporadaId = temporadaId;
    }

    public boolean isEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }

    @Override
    public String toString() {
        return "JugadorPosicion{" +
                "jugadorId=" + jugadorId +
                ", posicionId=" + posicionId +
                ", temporadaId=" + temporadaId +
                ", esPrincipal=" + esPrincipal +
                '}';
    }

}
