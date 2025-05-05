package es.tierno.daw.trasnferdaw.model.entities;

import java.time.LocalDate;

public class Noticia {
    private int idNoticia;
    private Integer jugadorId;
    private Integer equipoId;
    private Integer usuarioId;
    private String titulo;
    private String contenido;
    private String fotoUrl;
    private String categoria;
    private LocalDate fecha;

    public Noticia() {
    }

    public Noticia(int idNoticia, Integer jugadorId, Integer equipoId, Integer usuarioId, String titulo,
            String contenido, String fotoUrl, String categoria, LocalDate fecha) {
        this.idNoticia = idNoticia;
        this.jugadorId = jugadorId;
        this.equipoId = equipoId;
        this.usuarioId = usuarioId;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fotoUrl = fotoUrl;
        this.categoria = categoria;
        this.fecha = fecha;
    }

    public int getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(int idNoticia) {
        this.idNoticia = idNoticia;
    }

    public Integer getJugadorId() {
        return jugadorId;
    }

    public void setJugadorId(Integer jugadorId) {
        this.jugadorId = jugadorId;
    }

    public Integer getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Integer equipoId) {
        this.equipoId = equipoId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "idNoticia=" + idNoticia +
                ", titulo='" + titulo + '\'' +
                '}';
    }

}
