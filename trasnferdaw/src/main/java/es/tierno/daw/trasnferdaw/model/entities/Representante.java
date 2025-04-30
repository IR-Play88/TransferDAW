package es.tierno.daw.trasnferdaw.model.entities;

public class Representante {
    private int id_representante;
    private String foto_url;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;

    public Representante() {
    }

    public Representante(int id_representante, String foto_url, String nombre, String telefono, String email,
            String direccion) {
        this.id_representante = id_representante;
        this.foto_url = foto_url;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    public int getId_representante() {
        return id_representante;
    }

    public void setId_representante(int id_representante) {
        this.id_representante = id_representante;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Representante{" +
                "id_representante=" + id_representante +
                ", foto_url=" + foto_url +
                ", nombre=" + nombre +
                ", telefono=" + telefono +
                ", email=" + email +
                ", direccion=" + direccion +
                '}';
    }
}
