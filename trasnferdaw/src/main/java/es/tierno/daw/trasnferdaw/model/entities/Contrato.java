package es.tierno.daw.trasnferdaw.model.entities;

import java.sql.Date;

public class Contrato {
    private int idContrato;
    private int jugadorId;
    private Integer equipoId;
    private Date fechaInicio;
    private Date fechaFin;
    private float salario;
    private float bonificaciones;
    private String tipoContrato;
    private float clausulaRescision;

    public Contrato() {
    }

    public Contrato(int idContrato, int jugadorId, Integer equipoId, Date fechaInicio, Date fechaFin, float salario,
            float bonificaciones, String tipoContrato, float clausulaRescision) {
        this.idContrato = idContrato;
        this.jugadorId = jugadorId;
        this.equipoId = equipoId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.salario = salario;
        this.bonificaciones = bonificaciones;
        this.tipoContrato = tipoContrato;
        this.clausulaRescision = clausulaRescision;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public int getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(int jugadorId) {
        this.jugadorId = jugadorId;
    }

    public Integer getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Integer equipoId) {
        this.equipoId = equipoId;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public float getBonificaciones() {
        return bonificaciones;
    }

    public void setBonificaciones(float bonificaciones) {
        this.bonificaciones = bonificaciones;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public float getClausulaRescision() {
        return clausulaRescision;
    }

    public void setClausulaRescision(float clausulaRescision) {
        this.clausulaRescision = clausulaRescision;
    }

    @Override
    public String toString() {
        return "Contrato{" +
                "idContrato=" + idContrato +
                ", tipoContrato='" + tipoContrato + '\'' +
                '}';
    }

}
