package es.tierno.daw.trasnferdaw.model.entities;

public class Posicion {
    private int id_posicion;
    private String nombre;
    private int id_categoria;
    private String descripcion;

    public Posicion() {
    }

    public Posicion(int id_posicion, String nombre, int id_categoria, String descripcion) {
        this.id_posicion = id_posicion;
        this.nombre = nombre;
        this.id_categoria = id_categoria;
        this.descripcion = descripcion;
    }

    public int getId_posicion() {
        return id_posicion;
    }

    public void setId_posicion(int id_posicion) {
        this.id_posicion = id_posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Posicion{" +
                "id_posicion=" + id_posicion +
                ", nombre=" + nombre +
                ", id_categoria=" + id_categoria +
                ", descripcion=" + descripcion +
                '}';
    }
}
