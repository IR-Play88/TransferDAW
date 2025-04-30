package es.tierno.daw.trasnferdaw.model.entities;

import java.sql.Date;

public class Seleccion {
    private int idSeleccion;
    private String logoUrl;
    private String nombre;
    private String pais;
    private String federacion;
    private Date fechaFundacion;
    private Integer ranking;
    private Integer entrenadorId;
    private Integer capitanId;

    public Seleccion() {
    }

    public Seleccion(int idSeleccion, String logoUrl, String nombre, String pais, String federacion,
            Date fechaFundacion, Integer ranking, Integer entrenadorId, Integer capitanId) {
        this.idSeleccion = idSeleccion;
        this.logoUrl = logoUrl;
        this.nombre = nombre;
        this.pais = pais;
        this.federacion = federacion;
        this.fechaFundacion = fechaFundacion;
        this.ranking = ranking;
        this.entrenadorId = entrenadorId;
        this.capitanId = capitanId;
    }

    public int getIdSeleccion() {
        return idSeleccion;
    }

    public void setIdSeleccion(int idSeleccion) {
        this.idSeleccion = idSeleccion;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
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

    public java.sql.Date getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(java.sql.Date fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getEntrenadorId() {
        return entrenadorId;
    }

    public void setEntrenadorId(Integer entrenadorId) {
        this.entrenadorId = entrenadorId;
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
                ", logoUrl='" + logoUrl + '\'' +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", federacion='" + federacion + '\'' +
                ", fechaFundacion=" + fechaFundacion +
                ", ranking=" + ranking +
                ", entrenadorId=" + entrenadorId +
                ", capitanId=" + capitanId +
                '}';
    }

}
