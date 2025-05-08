package es.tierno.daw.trasnferdaw.model.entities;

public class Competicion {
    private int idCompeticion;
    private String nombre;
    private String pais;
    private String tipo;
    private int numeroEquipos;
    private int anioCreacion;

    public Competicion() {
    }

    public Competicion(int idCompeticion, String nombre, String pais, String tipo, int numeroEquipos,
            int anioCreacion) {
        this.idCompeticion = idCompeticion;
        this.nombre = nombre;
        this.pais = pais;
        this.tipo = tipo;
        this.numeroEquipos = numeroEquipos;
        this.anioCreacion = anioCreacion;
    }

    public int getIdCompeticion() {
        return idCompeticion;
    }

    public void setIdCompeticion(int idCompeticion) {
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
