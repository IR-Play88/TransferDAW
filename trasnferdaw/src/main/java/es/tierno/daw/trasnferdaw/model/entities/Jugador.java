package es.tierno.daw.trasnferdaw.model.entities;

import java.time.LocalDate;

public class Jugador {
    private int idJugador;
    private String nombre;
    private String alias;
    private String fotoUrl;
    private LocalDate fechaNacimiento;
    private String nacionalidad;
    private float altura;
    private float peso;
    private String pieDominante;
    private float valorMercado;
    private Integer representanteId;
    private Integer seleccionId;

    public Jugador() {
    }

    public Jugador(int idJugador, String nombre, String alias, String fotoUrl, LocalDate fechaNacimiento,
            String nacionalidad, float altura, float peso, String pieDominante, float valorMercado,
            Integer representanteId, Integer seleccionId) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.alias = alias;
        this.fotoUrl = fotoUrl;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.altura = altura;
        this.peso = peso;
        this.pieDominante = pieDominante;
        this.valorMercado = valorMercado;
        this.representanteId = representanteId;
        this.seleccionId = seleccionId;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getPieDominante() {
        return pieDominante;
    }

    public void setPieDominante(String pieDominante) {
        this.pieDominante = pieDominante;
    }

    public float getValorMercado() {
        return valorMercado;
    }

    public void setValorMercado(float valorMercado) {
        this.valorMercado = valorMercado;
    }

    public Integer getRepresentanteId() {
        return representanteId;
    }

    public void setRepresentanteId(Integer representanteId) {
        this.representanteId = representanteId;
    }

    public Integer getSeleccionId() {
        return seleccionId;
    }

    public void setSeleccionId(Integer seleccionId) {
        this.seleccionId = seleccionId;
    }

    @Override
    public String toString() {
        return "Ficha del Jugador:\n" +
                "ID: " + idJugador + "\n" +
                "Nombre: " + nombre + "\n" +
                "Alias: " + alias + "\n" +
                "Foto URL: " + fotoUrl + "\n" +
                "Fecha de Nacimiento: " + fechaNacimiento + "\n" +
                "Nacionalidad: " + nacionalidad + "\n" +
                "Altura: " + altura + " m\n" +
                "Peso: " + peso + " kg\n" +
                "Pie Dominante: " + pieDominante + "\n" +
                "Valor de Mercado: " + valorMercado + " €\n" +
                "ID Representante: " + representanteId + "\n" +
                "ID Selección: " + seleccionId + "\n";
    }

}
