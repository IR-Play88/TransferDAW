package es.tierno.daw.trasnferdaw.model.entities;

import java.time.LocalDate;

import es.tierno.daw.trasnferdaw.model.exception.BBDDException;

/**
 * La clase ValorMercadpHistorial representa el valor de mercado de los
 * jugadores de TransferDAW.
 * \author Iván Rafael Redondo.
 */
public class ValorMercadoHistorial {
    private Integer idHistorial;
    private Integer jugadorId;
    private String nombreJugador;
    private LocalDate fecha;
    private long valorMercado;
    private String motivo;

    /**
     * Constructor por defecto de la clase ValorMercadoHistorial.
     * Inicializa una nueva instancia sin establecer valores para sus atributos.
     * 
     * @throws BBDDException
     */
    public ValorMercadoHistorial() throws BBDDException {
    }

    /**
     * Constructor que inicializa un historial de valor de mercado para un jugador,
     * sin especificar el ID.
     * 
     * @param jugadorId    ID del jugador
     * @param fecha        Fecha en la que se registra el valor de mercado
     * @param valorMercado Valor de mercado del jugador en la fecha indicada
     * @param motivo       Motivo o descripción del cambio en el valor de mercado
     * @throws BBDDException
     */
    public ValorMercadoHistorial(Integer jugadorId, LocalDate fecha, long valorMercado, String motivo)
            throws BBDDException {
        this.jugadorId = jugadorId;
        this.fecha = fecha;
        this.valorMercado = valorMercado;
        this.motivo = motivo;
    }

    /**
     * Constructor que inicializa un historial de valor de mercado para un jugador,
     * incluyendo su identificador único.
     * 
     * @param idHistorial  ID único del historial de valor de mercado
     * @param jugadorId    ID del jugador
     * @param fecha        Fecha en la que se registra el valor de mercado
     * @param valorMercado Valor de mercado del jugador en la fecha indicada
     * @param motivo       Motivo o descripción del cambio en el valor de mercado
     * @throws BBDDException
     */
    public ValorMercadoHistorial(Integer idHistorial, Integer jugadorId, LocalDate fecha, long valorMercado,
            String motivo) throws BBDDException {
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

    public long getValorMercado() {
        return valorMercado;
    }

    public void setValorMercado(long valorMercado) {
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
