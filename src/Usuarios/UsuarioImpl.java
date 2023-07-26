package Usuarios;

import BaseDatos.UsuarioBD;

public class UsuarioImpl extends Usuario{
    public UsuarioImpl() {
    }

    @Override
    public void ingresarNuevoUsuario(String username, String nombre, String apellido,
                                     String mail, String telefono, String password,
                                     String combro_string) {
        UsuarioBD usuarioBD = new UsuarioBD();
        usuarioBD.ingresarUsuarioBD(username, nombre, apellido, mail, telefono,  password,
                combro_string);

    }

    @Override
    public void eliminarUsuario(String username) {
        UsuarioBD usuarioBD = new UsuarioBD();
        usuarioBD.eliminarUsuarioBD(username);

    }

    @Override
    public void modificarUsuario(String username, String nombre, String apellido,
                                 String mail, String telefono, String password,
                                 String combro_string,int ID) {
        UsuarioBD usuarioBD = new UsuarioBD();
        usuarioBD.modificarUsuarioBD(username, nombre, apellido, mail, telefono,  password,
                combro_string, ID);

    }

    @Override
    public void cambiarPassword(String usuario_update, String password) {
        UsuarioBD usuarioBD = new UsuarioBD();
        usuarioBD.cambiarContraseñaBD(usuario_update,password);

    }

    @Override
    public UsuarioImpl comprobarUsuario(String usuario_update) {
        UsuarioBD usuarioBD = new UsuarioBD();
        return usuarioBD.comprobarUsuario(usuario_update);

    }
}
