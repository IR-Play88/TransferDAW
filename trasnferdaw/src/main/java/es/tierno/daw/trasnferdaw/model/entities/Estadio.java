package es.tierno.daw.trasnferdaw.model.entities;

public class Estadio {
    private int idEstadio;
    private String fotoUrl;
    private String nombre;
    private int capacidad;
    private String ubicacion;
    private int anioInauguracion;

    public Estadio() {
    }

    public Estadio(int idEstadio, String fotoUrl, String nombre, int capacidad, String ubicacion,
            int anioInauguracion) {
        this.idEstadio = idEstadio;
        this.fotoUrl = fotoUrl;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.anioInauguracion = anioInauguracion;
    }

    public int getIdEstadio() {
        return idEstadio;
    }

    public void setIdEstadio(int idEstadio) {
        this.idEstadio = idEstadio;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getAnioInauguracion() {
        return anioInauguracion;
    }

    public void setAnioInauguracion(int anioInauguracion) {
        this.anioInauguracion = anioInauguracion;
    }

    @Override
    public String toString() {
        return "Estadio{" +
                "idEstadio=" + idEstadio +
                ", nombre='" + nombre + '\'' +
                '}';
    }

}
