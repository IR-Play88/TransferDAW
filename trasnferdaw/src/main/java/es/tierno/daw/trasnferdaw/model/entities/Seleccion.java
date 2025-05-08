package es.tierno.daw.trasnferdaw.model.entities;

public class Seleccion {
    private int idSeleccion;
    private String nombre;
    private String pais;
    private String federacion;
    private int anioFundacion;
    private int ranking;
    private String entrenadorNombre;
    private Integer capitanId;

    public Seleccion() {
    }

    public Seleccion(int idSeleccion, String nombre, String pais, String federacion, int anioFundacion, int ranking,
            String entrenadorNombre, Integer capitanId) {
        this.idSeleccion = idSeleccion;
        this.nombre = nombre;
        this.pais = pais;
        this.federacion = federacion;
        this.anioFundacion = anioFundacion;
        this.ranking = ranking;
        this.entrenadorNombre = entrenadorNombre;
        this.capitanId = capitanId;
    }

    public int getIdSeleccion() {
        return idSeleccion;
    }

    public void setIdSeleccion(int idSeleccion) {
        this.idSeleccion = idSeleccion;
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

    public String getFederacion() {
        return federacion;
    }

    public void setFederacion(String federacion) {
        this.federacion = federacion;
    }

    public int getAnioFundacion() {
        return anioFundacion;
    }

    public void setAnioFundacion(int anioFundacion) {
        this.anioFundacion = anioFundacion;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getEntrenadorNombre() {
        return entrenadorNombre;
    }

    public void setEntrenadorNombre(String entrenadorNombre) {
        this.entrenadorNombre = entrenadorNombre;
    }

    public Integer getCapitanId() {
        return capitanId;
    }

    public void setCapitanId(Integer capitanId) {
        this.capitanId = capitanId;
    }

    @Override
    public String toString() {
        return "Seleccion{" +
                "idSeleccion=" + idSeleccion +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", federacion='" + federacion + '\'' +
                ", fechaFundacion=" + anioFundacion +
                ", ranking=" + ranking +
                ", entrenadorId=" + entrenadorNombre +
                ", capitanId=" + capitanId +
                '}';
    }

}
