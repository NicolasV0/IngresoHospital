package BaseDatos;

import Logs.Log;
import Usuarios.UsuarioImpl;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioBD {
    private static final Logger LOG = Log.getLogger(UsuarioBD.class);

    public UsuarioBD() {
    }

    public UsuarioImpl comprobarUsuario(String usuario_update){
        try{
            LOG.debug("INFO:: comprobar usuario en bd usuarios ");;
            String combo_string= "";
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("" +
                    "select * from usuarios where username = '" + usuario_update + "'");
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                UsuarioImpl usuario = new UsuarioImpl();
                usuario.setId( rs.getInt("id_usuario"));
                String cadena = rs.getString("nombre_usuario");
                String[] fragemento = cadena.split(" ");
                usuario.setNombre(fragemento[0]);
                usuario.setApellido(fragemento[1]);
                usuario.setMail( rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setTelefono((rs.getString("telefono")));
                usuario.setUsername(rs.getString("username"));
                usuario.setNivel(rs.getString("tipo_nivel"));
                return usuario;
            }
            cn.close();

        }catch (SQLException e){
            LOG.debug("ERROR:: Error en cargar usuario " + e);;
            System.err.println("Error en cargar usuario " + e);
            JOptionPane.showMessageDialog(null, "Error al cargar usuario");
            return null;

        }
        return null;

    }


    public void ingresarUsuarioBD(String username, String nombre, String apellido,
                                  String mail, String telefono, String password,
                                  String combro_string){
        try {
            LOG.debug("INFO:: ingresar usuario base datos usuarios ");

            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select username from usuarios where username = '"+ username + "'");
            ResultSet rs = pst.executeQuery();

            if (rs.next()){
                LOG.debug("WARN:: Usuario ya registrado");
                JOptionPane.showMessageDialog(null,"Usuario ya registrado");
                cn.close();
            }else {
                cn.close();
                    try {
                        Connection cn2 = Conexion.conectar();
                        PreparedStatement pst2 = cn2.prepareStatement("" +
                                "insert into usuarios values (?,?,?,?,?,?,?)");
                        pst2.setInt(1,0);
                        pst2.setString(2,nombre +" "+ apellido);
                        pst2.setString(3,mail);
                        pst2.setString(4,telefono);
                        pst2.setString(5,username);
                        pst2.setString(6,password);
                        pst2.setString(7,combro_string);

                        pst2.executeUpdate();
                        cn2.close();

                        JOptionPane.showMessageDialog(null,"Usuario cargado");


                    }catch (SQLException e){
                        LOG.debug("ERROR:: Error en registrar usuario "+e);
                        System.err.println("Error en registrar usuario "+e);
                        JOptionPane.showMessageDialog(null,"Error al registrar...");
                    }

            }

        }catch (SQLException e){
            LOG.debug("ERROR:: Error en validar usuario "+e);
            System.err.println("Error en validar usuario " + e);
            JOptionPane.showMessageDialog(null,"No se pudo contactar la base de datos");
        }
    }


    public void modificarUsuarioBD(String username, String nombre, String apellido,
                                   String mail, String telefono, String password,
                                   String combro_string, int ID){
        try{
            LOG.debug("INFO:: modificar usuario base datos usuarios ");
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select username from usuarios where username = '"+ username + "' and not id_usuario = '"+ ID +"'");

            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                LOG.debug("WARN:: Usuario no disponible ");
                JOptionPane.showMessageDialog(null,"Usuario no disponible");

            }else {
                Connection cn2 = Conexion.conectar();
                PreparedStatement pst2 = cn2.prepareStatement("update usuarios set nombre_usuario=?, email=?, telefono=?, username=?, password=?, tipo_nivel=? where id_usuario = '" + ID +"'");
                pst2.setString(1,nombre +" "+ apellido);
                pst2.setString(2,mail);
                pst2.setString(3,telefono);
                pst2.setString(4,username);
                pst2.setString(5,password);
                pst2.setString(6,combro_string);
                pst2.executeUpdate();
                cn2.close();
                LOG.debug("INFO:: actualizacion correcta ");
                JOptionPane.showMessageDialog(null,"Actulizacion correcta");

            }
        }catch (SQLException e){
            LOG.debug("ERROR:: iError al actualizar informacion " + e);
            System.err.println("Error al actualizar informacion " + e);
        }
    }


    public void cambiarContraseñaBD(String usuario_update, String password){
        try {
            LOG.debug("INFO:: cambiar contraseña bd usuarios ");
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("update usuarios set password=? where username = '" + usuario_update + "'");
            pst.setString(1,password);
            pst.executeUpdate();
            cn.close();
            LOG.debug("INFO:: actualizacion correcta ");
            JOptionPane.showMessageDialog(null,"Restauracion exitosa");

        }catch (SQLException e){
            LOG.debug("ERROR:: Error en restaurar password" +e);
            System.err.println("Error en restaurar password" +e);
            JOptionPane.showMessageDialog(null,"Error" + e);
        }
    }

    public void eliminarUsuarioBD(String username){
        try{
            LOG.debug("INFO:: eliminar usuario bd usuarios ");
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "delete from usuarios where username = ?");
            pst.setString(1,username);
            pst.executeUpdate();
            LOG.debug("INFO:: eliminado correctament ");
            JOptionPane.showMessageDialog(null,"Consulta eliminada");

        }catch (SQLException e){
            LOG.debug("ERROR:: Error al borrar informacion " + e);
            System.err.println("Error al borrar informacion " + e);
        }
    }


}
