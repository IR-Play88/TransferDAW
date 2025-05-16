package es.tierno.daw.trasnferdaw.model.entities;

import java.time.LocalDate;

public class Jugador {
    private int idJugador;
    private String nombre;
    private String alias;
    private LocalDate fechaNacimiento;
    private String nacionalidad;
    private float altura;
    private float peso;
    private String pieDominante;
    private float valorMercado;
    private String posicion;
    private String representanteNombre;
    private String seleccionNombre;

    public Jugador() {
    }

    public Jugador(int idJugador, String nombre, String alias, LocalDate fechaNacimiento, String nacionalidad,
            float altura, float peso, String pieDominante, float valorMercado, String posicion,
            String representanteNombre,  String seleccionNombre) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.alias = alias;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.altura = altura;
        this.peso = peso;
        this.pieDominante = pieDominante;
        this.valorMercado = valorMercado;
        this.posicion = posicion;
        this.representanteNombre = representanteNombre;
        this.seleccionNombre = seleccionNombre;
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

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getRepresentanteNombre() {
        return representanteNombre;
    }

    public void setRepresentanteNombre(String representanteNombre) {
        this.representanteNombre = representanteNombre;
    }

    public String getSeleccionNombre() {
        return seleccionNombre;
    }

    public void setSeleccionNombre(String seleccionNombre) {
        this.seleccionNombre = seleccionNombre;
    }

    @Override
    public String toString() {
        return "Ficha del Jugador:\n" +
                "ID: " + idJugador + "\n" +
                "Nombre: " + nombre + "\n" +
                "Alias: " + alias + "\n" +
                "Fecha de Nacimiento: " + fechaNacimiento + "\n" +
                "Nacionalidad: " + nacionalidad + "\n" +
                "Altura: " + altura + " m\n" +
                "Peso: " + peso + " kg\n" +
                "Pie Dominante: " + pieDominante + "\n" +
                "Valor de Mercado: " + valorMercado + " €\n" +
                "Posicion: " + posicion  + "\n" +
                "ID Representante: " + representanteNombre + "\n" +
                "ID Selección: " + seleccionNombre + "\n";
    }

}
