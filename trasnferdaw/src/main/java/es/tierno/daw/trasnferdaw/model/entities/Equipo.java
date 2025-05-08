package es.tierno.daw.trasnferdaw.model.entities;

public class Equipo {
    private int idEquipo;
    private String nombre;
    private String ciudad;
    private String pais;
    private int anioFundacion;
    private float presupuesto;
    private String propietario;
    private String estadioNombre;
    private String entrenadorNombre;

    public Equipo() {
    }

    public Equipo(int idEquipo, String nombre, String ciudad, String pais, int anioFundacion, float presupuesto,
            String propietario, String estadioNombre, String entrenadorNombre) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.anioFundacion = anioFundacion;
        this.presupuesto = presupuesto;
        this.propietario = propietario;
        this.estadioNombre = estadioNombre;
        this.entrenadorNombre = entrenadorNombre;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getAnioFundacion() {
        return anioFundacion;
    }

    public void setAnioFundacion(int anioFundacion) {
        this.anioFundacion = anioFundacion;
    }

    public float getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(float presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getEstadioNombre() {
        return estadioNombre;
    }

    public void setEstadioNombre(String estadioNombre) {
        this.estadioNombre = estadioNombre;
    }

    public String getEntrenadorNombre() {
        return entrenadorNombre;
    }

    public void setEntrenadorNombre(String entrenadorNombre) {
        this.entrenadorNombre = entrenadorNombre;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "idEquipo=" + idEquipo +
                ", nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", pais='" + pais + '\'' +
                ", anioFundacion=" + anioFundacion +
                ", presupuesto=" + presupuesto +
                ", propietario='" + propietario + '\'' +
                ", estadio='" + estadioNombre + '\'' +
                ", entrenador=" + entrenadorNombre +
                '}';
    }

}
