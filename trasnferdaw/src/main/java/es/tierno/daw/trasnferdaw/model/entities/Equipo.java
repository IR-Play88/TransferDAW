package es.tierno.daw.trasnferdaw.model.entities;

public class Equipo {
    private int idEquipo;
    private String nombre;
    private String ciudad;
    private String pais;
    private String escudoUrl;
    private String descripcion;
    private int anioFundacion;
    private float presupuesto;
    private String propietario;
    private Integer entrenadorId;

    public Equipo() {
    }

    public Equipo(int idEquipo, String nombre, String ciudad, String pais, String escudoUrl, String descripcion,
            int anioFundacion, float presupuesto, String propietario, Integer entrenadorId) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.escudoUrl = escudoUrl;
        this.descripcion = descripcion;
        this.anioFundacion = anioFundacion;
        this.presupuesto = presupuesto;
        this.propietario = propietario;
        this.entrenadorId = entrenadorId;
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

    public String getEscudoUrl() {
        return escudoUrl;
    }

    public void setEscudoUrl(String escudoUrl) {
        this.escudoUrl = escudoUrl;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Integer getEntrenadorId() {
        return entrenadorId;
    }

    public void setEntrenadorId(Integer entrenadorId) {
        this.entrenadorId = entrenadorId;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "idEquipo=" + idEquipo +
                ", nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", pais='" + pais + '\'' +
                ", escudoUrl='" + escudoUrl + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", anioFundacion=" + anioFundacion +
                ", presupuesto=" + presupuesto +
                ", propietario='" + propietario + '\'' +
                ", entrenadorId=" + entrenadorId +
                '}';
    }

}
