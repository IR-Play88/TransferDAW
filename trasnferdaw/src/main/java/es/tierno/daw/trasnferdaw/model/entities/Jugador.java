package es.tierno.daw.trasnferdaw.model.entities;

import java.time.LocalDate;

import es.tierno.daw.trasnferdaw.model.exception.BBDDException;

/**
 * La clase Jugador representa un jugador de TransferDAW.
 * \author Iván Rafael Redondo.
 */
public class Jugador {
    private Integer idJugador;
    private String nombre;
    private String alias;
    private LocalDate fechaNacimiento;
    private String nacionalidad;
    private double altura;
    private double peso;
    private String pieDominante;
    private double valorMercado;
    private String posicion;
    private String representanteNombre;
    private String seleccionNombre;

    /**
     * Constructor por defecto de la clase Jugador.
     * Inicializa una nueva instancia sin establecer valores para sus atributos.
     * 
     * @throws BBDDException
     */
    public Jugador() throws BBDDException {
    }

    /**
     * Constructor que inicializa un jugador con sus datos básicos, sin especificar
     * el ID.
     * 
     * @param nombre              Nombre completo del jugador
     * @param alias               Apodo o nombre deportivo del jugador
     * @param fechaNacimiento     Fecha de nacimiento del jugador
     * @param nacionalidad        Nacionalidad del jugador
     * @param altura              Altura del jugador en metros
     * @param peso                Peso del jugador en kilogramos
     * @param pieDominante        Pie dominante del jugador (izquierdo, derecho,
     *                            ambos)
     * @param valorMercado        Valor de mercado estimado del jugador
     * @param posicion            Posición en la que juega el jugador (por ejemplo,
     *                            delantero, defensa, etc.)
     * @param representanteNombre Nombre del representante del jugador
     * @param seleccionNombre     Nombre de la selección nacional con la que está
     *                            asociado el jugador (si aplica)
     * @throws BBDDException
     */

    /**
     * Constructor que inicializa un jugador incluyendo su identificador único.
     * 
     * @param idJugador           ID único del jugador
     * @param nombre              Nombre completo del jugador
     * @param alias               Apodo o nombre deportivo del jugador
     * @param fechaNacimiento     Fecha de nacimiento del jugador
     * @param nacionalidad        Nacionalidad del jugador
     * @param altura              Altura del jugador en metros
     * @param peso                Peso del jugador en kilogramos
     * @param pieDominante        Pie dominante del jugador (izquierdo, derecho,
     *                            ambos)
     * @param valorMercado        Valor de mercado estimado del jugador
     * @param posicion            Posición en la que juega el jugador (por ejemplo,
     *                            delantero, defensa, etc.)
     * @param representanteNombre Nombre del representante del jugador
     * @param seleccionNombre     Nombre de la selección nacional con la que está
     *                            asociado el jugador (si aplica)
     * @throws BBDDException
     */
    public Jugador(String nombre, String alias, LocalDate fechaNacimiento, String nacionalidad, double altura,
            double peso, String pieDominante, double valorMercado, String posicion, String representanteNombre,
            String seleccionNombre) throws BBDDException {
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

    public Jugador(Integer idJugador, String nombre, String alias, LocalDate fechaNacimiento, String nacionalidad,
            double altura, double peso, String pieDominante, double valorMercado, String posicion,
            String representanteNombre, String seleccionNombre) throws BBDDException {
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

    public Integer getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Integer idJugador) {
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

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getPieDominante() {
        return pieDominante;
    }

    public void setPieDominante(String pieDominante) {
        this.pieDominante = pieDominante;
    }

    public double getValorMercado() {
        return valorMercado;
    }

    public void setValorMercado(double valorMercado) {
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
                "Nombre: " + nombre + "\n" +
                "Alias: " + alias + "\n" +
                "Fecha de Nacimiento: " + fechaNacimiento + "\n" +
                "Nacionalidad: " + nacionalidad + "\n" +
                "Altura: " + altura + " m\n" +
                "Peso: " + peso + " kg\n" +
                "Pie Dominante: " + pieDominante + "\n" +
                "Valor de Mercado: " + valorMercado + " €\n" +
                "Posicion: " + posicion + "\n" +
                "ID Representante: " + representanteNombre + "\n" +
                "ID Selección: " + seleccionNombre + "\n";
    }

}
