package es.tierno.daw.trasnferdaw.model.entities;

import es.tierno.daw.trasnferdaw.model.exception.BBDDException;

/**
 * La clase Competicion representa una competición de TransferDAW.
 * \author Iván Rafael Redondo.
 */
public class Competicion {
    private Integer idCompeticion;
    private String nombre;
    private String pais;
    private String tipo;
    private int numeroEquipos;
    private int anioCreacion;

    /**
     * Constructor por defecto de la clase Competicion.
     * Inicializa una nueva instancia sin establecer valores para sus atributos.
     * 
     * @throws BBDDException
     */
    public Competicion() throws BBDDException {
    }

    /**
     * Constructor que inicializa una competición con sus datos básicos, sin
     * especificar el ID.
     * 
     * @param nombre        Nombre de la competición
     * @param pais          País donde se celebra la competición
     * @param tipo          Tipo de competición (por ejemplo, liga, copa, etc.)
     * @param numeroEquipos Número de equipos que participan en la competición
     * @param anioCreacion  Año en el que se creó la competición
     * @throws BBDDException
     */

    public Competicion(String nombre, String pais, String tipo, int numeroEquipos, int anioCreacion)
            throws BBDDException {
        this.nombre = nombre;
        this.pais = pais;
        this.tipo = tipo;
        this.numeroEquipos = numeroEquipos;
        this.anioCreacion = anioCreacion;
    }

    /**
     * Constructor que inicializa una competición incluyendo su identificador único.
     * 
     * @param idCompeticion ID único de la competición
     * @param nombre        Nombre de la competición
     * @param pais          País donde se celebra la competición
     * @param tipo          Tipo de competición (por ejemplo, liga, copa, etc.)
     * @param numeroEquipos Número de equipos que participan en la competición
     * @param anioCreacion  Año en el que se creó la competición
     * @throws BBDDException
     */
    public Competicion(Integer idCompeticion, String nombre, String pais, String tipo, int numeroEquipos,
            int anioCreacion) throws BBDDException {
        this.idCompeticion = idCompeticion;
        this.nombre = nombre;
        this.pais = pais;
        this.tipo = tipo;
        this.numeroEquipos = numeroEquipos;
        this.anioCreacion = anioCreacion;
    }

    public Integer getIdCompeticion() {
        return idCompeticion;
    }

    public void setIdCompeticion(Integer idCompeticion) {
        this.idCompeticion = idCompeticion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumeroEquipos() {
        return numeroEquipos;
    }

    public void setNumeroEquipos(int numeroEquipos) {
        this.numeroEquipos = numeroEquipos;
    }

    public int getAnioCreacion() {
        return anioCreacion;
    }

    public void setAnioCreacion(int anioCreacion) {
        this.anioCreacion = anioCreacion;
    }

    @Override
    public String toString() {
        return "Competicion{" +
                "idCompeticion=" + idCompeticion +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
