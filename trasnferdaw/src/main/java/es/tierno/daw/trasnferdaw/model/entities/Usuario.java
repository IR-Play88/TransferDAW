package es.tierno.daw.trasnferdaw.model.entities;

import org.mindrot.jbcrypt.BCrypt;

import es.tierno.daw.trasnferdaw.model.exception.BBDDException;

/**
 * La clase Usuario representa un usuario de TransferDAW.
 * \author Iván Rafael Redondo.
 */
public class Usuario {
    private Integer idUsuario;
    private String nombre;
    private String email;
    private String contrasena;
    private String rol;

    /**
     * Constructor por defecto de la clase Usuario.
     * Inicializa una nueva instancia sin establecer valores para sus atributos.
     * 
     * @throws BBDDException
     */
    public Usuario() throws BBDDException {
    }

    /**
     * Constructor que inicializa un usuario con sus datos básicos, sin especificar
     * el ID.
     * 
     * @param nombre     Nombre del usuario
     * @param email      Correo electrónico del usuario
     * @param contrasena Contraseña del usuario
     * @param rol        Rol asignado al usuario (por ejemplo, "admin", "usuario")
     * @throws BBDDException
     */
    public Usuario(String nombre, String email, String contrasena, String rol) throws BBDDException {
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    /**
     * Constructor que inicializa un usuario incluyendo su identificador único.
     * 
     * @param idUsuario  ID único del usuario
     * @param nombre     Nombre del usuario
     * @param email      Correo electrónico del usuario
     * @param contrasena Contraseña del usuario
     * @param rol        Rol asignado al usuario (por ejemplo, "admin", "usuario")
     * @throws BBDDException
     */

    public Usuario(Integer idUsuario, String nombre, String email, String contrasena, String rol) throws BBDDException {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = BCrypt.hashpw(contrasena, BCrypt.gensalt());
    }

    public void setContrasenaHash(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
