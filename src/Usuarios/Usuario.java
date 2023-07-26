package Usuarios;

import Pacientes.ObraSocial;

public abstract class Usuario {
    private String nombre;
    private String apellido;
    private String username;
    private int id;
    private String telefono;
    private String mail;
    private String password;
    private String nivel;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public abstract void ingresarNuevoUsuario(String username, String nombre, String apellido,
                                              String mail, String telefono, String password,
                                              String combro_string);
    public abstract void eliminarUsuario(String username);
    public abstract void modificarUsuario(String username, String nombre, String apellido,
                                          String mail, String telefono, String password,
                                          String combro_string,int ID);
    public abstract void cambiarPassword(String usuario_update, String password);

    public abstract UsuarioImpl comprobarUsuario(String usuario_update);

}
