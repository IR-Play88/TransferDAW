package es.tierno.daw.trasnferdaw.model.entities;

import java.time.LocalDate;

import es.tierno.daw.trasnferdaw.model.exception.BBDDException;

/**
 * La clase Traspaso representa un traspaso de TransferDAW.
 * \author Iván Rafael Redondo.
 */
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

    /**
     * Constructor por defecto de la clase Traspaso.
     * Inicializa una nueva instancia sin establecer valores para sus atributos.
     * 
     * @throws BBDDException
     */
    public Traspaso() throws BBDDException {
    }

    /**
     * Constructor que inicializa un traspaso con sus datos básicos, sin especificar
     * el ID.
     * 
     * @param jugadorId       ID del jugador que es traspasado
     * @param equipoOrigenId  ID del equipo de origen
     * @param equipoDestinoId ID del equipo de destino
     * @param temporadaId     ID de la temporada en la que se realiza el traspaso
     * @param fechaTraspaso   Fecha en la que se realiza el traspaso
     * @param cantidad        Cantidad económica del traspaso
     * @param tipo            Tipo de traspaso (por ejemplo, "compra", "cesión",
     *                        "libre")
     * @throws BBDDException
     */
    public Traspaso(Integer jugadorId, Integer equipoOrigenId, Integer equipoDestinoId, Integer temporadaId,
            LocalDate fechaTraspaso, double cantidad, String tipo) throws BBDDException {
        this.jugadorId = jugadorId;
        this.equipoOrigenId = equipoOrigenId;
        this.equipoDestinoId = equipoDestinoId;
        this.temporadaId = temporadaId;
        this.fechaTraspaso = fechaTraspaso;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    /**
     * Constructor que inicializa un traspaso incluyendo su identificador único.
     * 
     * @param idTraspaso      ID único del traspaso
     * @param jugadorId       ID del jugador que es traspasado
     * @param equipoOrigenId  ID del equipo de origen
     * @param equipoDestinoId ID del equipo de destino
     * @param temporadaId     ID de la temporada en la que se realiza el traspaso
     * @param fechaTraspaso   Fecha en la que se realiza el traspaso
     * @param cantidad        Cantidad económica del traspaso
     * @param tipo            Tipo de traspaso (por ejemplo, "compra", "cesión",
     *                        "libre")
     * @throws BBDDException
     */
    public Traspaso(Integer idTraspaso, int jugadorId, Integer equipoOrigenId, Integer equipoDestinoId,
            Integer temporadaId,
            LocalDate fechaTraspaso, double cantidad, String tipo) throws BBDDException {
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
