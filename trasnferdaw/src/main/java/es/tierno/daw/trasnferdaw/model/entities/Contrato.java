package es.tierno.daw.trasnferdaw.model.entities;

import java.time.LocalDate;

public class Contrato {
    private Integer idContrato;
    private Integer jugadorId;
    private String nombreJugador;
    private Integer equipoId;
    private String nombreEquipo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double salario;
    private String tipoContrato;

    public Contrato() {
    }

    public Contrato(Integer jugadorId, Integer equipoId, LocalDate fechaInicio, LocalDate fechaFin, double salario, String tipoContrato) {
        this.jugadorId = jugadorId;
        this.equipoId = equipoId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.salario = salario;
        this.tipoContrato = tipoContrato;
    }
    

    public Contrato(Integer idContrato, Integer jugadorId, Integer equipoId, LocalDate fechaInicio, LocalDate fechaFin, double salario, String tipoContrato) {
        this.idContrato = idContrato;
        this.jugadorId = jugadorId;
        this.equipoId = equipoId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.salario = salario;
        this.tipoContrato = tipoContrato;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(Integer jugadorId) {
        this.jugadorId = jugadorId;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public Integer getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Integer equipoId) {
        this.equipoId = equipoId;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
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

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String toString() {
        return "Contrato{" +
                "idContrato=" + idContrato +
                ", tipoContrato='" + tipoContrato + '\'' +
                '}';
    }

}
