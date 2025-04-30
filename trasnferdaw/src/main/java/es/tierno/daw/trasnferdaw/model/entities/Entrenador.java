package es.tierno.daw.trasnferdaw.model.entities;

import java.sql.Date;

public class Entrenador {
    private int id_entrenador;
    private String foto_url;
    private String nombre;
    private Date fecha_nacimiento;
    private String nacionalidad;
    private String experiencia;

    public Entrenador() {
    }

    public Entrenador(int id_entrenador, String foto_url, String nombre, Date fecha_nacimiento, String nacionalidad,
            String experiencia) {
        this.id_entrenador = id_entrenador;
        this.foto_url = foto_url;
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nacionalidad = nacionalidad;
        this.experiencia = experiencia;
    }

    public int getId_entrenador() {
        return id_entrenador;
    }

    public void setId_entrenador(int id_entrenador) {
        this.id_entrenador = id_entrenador;
    }

    public String getFoto_url() {
        return foto_url;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "id_entrenador=" + id_entrenador +
                ", foto_url=" + foto_url +
                ", nombre=" + nombre +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", nacionalidad=" + nacionalidad +
                ", experiencia=" + experiencia +
                '}';
    }

}
