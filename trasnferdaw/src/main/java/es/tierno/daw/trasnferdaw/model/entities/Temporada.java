package es.tierno.daw.trasnferdaw.model.entities;

import java.time.LocalDate;

import es.tierno.daw.trasnferdaw.model.exception.BBDDException;

/**
 * La clase Temporada representa una temporada de TransferDAW.
 * \author Iván Rafael Redondo.
 */
public class Temporada {
    private Integer idTemporada;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    /**
     * Constructor por defecto de la clase Temporada.
     * Inicializa una nueva instancia sin establecer valores para sus atributos.
     * 
     * @throws BBDDException
     */
    public Temporada() throws BBDDException {
    }

    /**
     * Constructor que inicializa una temporada con sus datos básicos, sin
     * especificar el ID.
     * 
     * @param nombre      Nombre de la temporada (por ejemplo, "2024/2025")
     * @param fechaInicio Fecha de inicio de la temporada
     * @param fechaFin    Fecha de finalización de la temporada
     * @throws BBDDException
     */
    public Temporada(String nombre, LocalDate fechaInicio, LocalDate fechaFin) throws BBDDException {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    /**
     * Constructor que inicializa una temporada incluyendo su identificador único.
     * 
     * @param idTemporada ID único de la temporada
     * @param nombre      Nombre de la temporada (por ejemplo, "2024/2025")
     * @param fechaInicio Fecha de inicio de la temporada
     * @param fechaFin    Fecha de finalización de la temporada
     * @throws BBDDException
     */
    public Temporada(Integer idTemporada, String nombre, LocalDate fechaInicio, LocalDate fechaFin)
            throws BBDDException {
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
