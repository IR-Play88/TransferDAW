package es.tierno.daw.trasnferdaw.model.entities;

import java.time.LocalDate;

public class Contrato {
    private int idContrato;
    private int jugadorId;
    private String nombreJugador;
    private Integer equipoId;
    private String nombreEquipo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private float salario;
    private String tipoContrato;

    public Contrato() {
    }

    public Contrato(int idContrato, int jugadorId, Integer equipoId, LocalDate fechaInicio, LocalDate fechaFin, float salario, String tipoContrato) {
        this.idContrato = idContrato;
        this.jugadorId = jugadorId;
        this.equipoId = equipoId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.salario = salario;
        this.tipoContrato = tipoContrato;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }
    

    @Override
    public String toString() {
        return "Contrato{" +
                "idContrato=" + idContrato +
                ", tipoContrato='" + tipoContrato + '\'' +
                '}';
    }

    

}
