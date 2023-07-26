package BaseDatos;
import Logs.Log;
import Pacientes.PacienteImpl;
import org.apache.log4j.Logger;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static Ventanas.Login.user;

public class PacienteBD {
    private static final Logger LOG = Log.getLogger(PacienteBD.class);

    public PacienteBD() {
    }


    public void agregarPacienteBD(PacienteImpl paciente){
        String obra_soc = paciente.getObraSocial().toString();
        try {
            LOG.debug("INFO:: agregar a base datos pacientes");

            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select dni_paciente from pacientes where dni_paciente = '" + paciente.getDni() + "'");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                LOG.debug("WARN:: paciente ya registrado");

                JOptionPane.showMessageDialog(null, "Paciente ya registrado");
                cn.close();
            } else {
                cn.close();
                try {
                    Connection cn2 = Conexion.conectar();
                    PreparedStatement pst2 = cn2.prepareStatement("" +
                            "insert into pacientes values (?,?,?,?,?,?,?,?,?,?)");
                    pst2.setInt(1, 0);
                    pst2.setString(2, paciente.getNombre() + " " + paciente.getApellido());
                    pst2.setString(3, paciente.getEdad());
                    pst2.setString(4, paciente.getMail());
                    pst2.setString(5, paciente.getTelefono());
                    pst2.setString(6, paciente.getDni());
                    pst2.setString(7, obra_soc);
                    pst2.setString(8, paciente.getSexo());
                    pst2.setString(9, paciente.getFechaNac());
                    pst2.setString(10, user);

                    pst2.executeUpdate();
                    cn2.close();
                    LOG.debug("INFO:: paciente cargado");
                    JOptionPane.showMessageDialog(null, "Paciente cargado");
                } catch (SQLException e) {
                    LOG.debug("ERROR:: Error en registrar paciente " + e);;
                    System.err.println("Error en registrar paciente " + e);
                    JOptionPane.showMessageDialog(null, "Error al registrar...");
                }
            }
        }catch (SQLException e) {
            LOG.debug("ERROR:: Error en validar paciente " + e);;
            System.err.println("Error en validad paciente " + e);
            JOptionPane.showMessageDialog(null, "No se pudo contactar la base de datos");
        }
    }

    public void actualizarPacienteBD(PacienteImpl paciente){
        try{

            LOG.debug("INFO:: actualizar base datos pacientes ");;

            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select dni_paciente from pacientes where dni_paciente = '"+ paciente.getDni() + "' and not hc = '"+paciente.getHC()+"'");

            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                LOG.debug("WARN:: paciente no disponible ");;
                JOptionPane.showMessageDialog(null,"Paciente no disponible");

            }else {
                Connection cn2 = Conexion.conectar();
                PreparedStatement pst2 = cn2.prepareStatement("update pacientes set nombre_paciente=?, edad_paciente=?, mail_paciente=?, telefono_paciente=?, dni_paciente=?, obra_soc_paciente=?, sexo_paciente=?, fec_nac_paciente=? where hc = '" + paciente.getHC() +"'");
                pst2.setString(1,paciente.getNombre() +" "+ paciente.getApellido());
                pst2.setString(2, paciente.getEdad());
                pst2.setString(3, paciente.getMail());
                pst2.setString(4, paciente.getTelefono());
                pst2.setString(5, paciente.getDni());
                pst2.setString(6, paciente.getObraSocial().toString());
                pst2.setString(7, paciente.getSexo());
                pst2.setString(8, paciente.getFechaNac());

                pst2.executeUpdate();
                cn2.close();
                LOG.debug("INFO:: actualizado");;
                JOptionPane.showMessageDialog(null,"Actulizacion correcta");

            }
        }catch (SQLException e){
            LOG.debug("INFO:: Error al actualizar informacion " + e);;
            System.err.println("Error al actualizar informacion " + e);
        }
    }

    public PacienteImpl buscarPacienteBD(String dni){
        PacienteImpl nuevoPac = new PacienteImpl();
        try {
            LOG.debug("INFO:: buscar base datos pacientes ");;
            Connection cn3 = Conexion.conectar();
            PreparedStatement pst3 = cn3.prepareStatement("" +
                    "select * from pacientes where dni_paciente = '" + dni + "'");
            ResultSet rs = pst3.executeQuery();
            if (rs.next()) {
                String cadena = rs.getString("nombre_paciente");
                String[] fragemento = cadena.split(" ");
                nuevoPac.setNombre(fragemento[0]);
                nuevoPac.setApellido(fragemento[1]);
                nuevoPac.setMail(rs.getString("mail_paciente"));
                nuevoPac.setEdad(rs.getString("edad_paciente"));
                nuevoPac.setTelefono(rs.getString("telefono_paciente"));
                nuevoPac.setDni(dni);
                String obraSocial = (rs.getString("obra_soc_paciente"));
                if (obraSocial.toUpperCase().equals("OSDE")){
                    nuevoPac.setObraSocial(Pacientes.ObraSocial.OSDE);
                }else if (obraSocial.toUpperCase().equals("DOSEM")){
                    nuevoPac.setObraSocial(Pacientes.ObraSocial.DOSEM);
                }else if (obraSocial.toUpperCase().equals("MEDIFE")){
                    nuevoPac.setObraSocial(Pacientes.ObraSocial.MEDIFE);
                }else if (obraSocial.toUpperCase().equals("SWISMEDICAL")){
                    nuevoPac.setObraSocial(Pacientes.ObraSocial.SWISMEDICAL);
                }else if (obraSocial.toUpperCase().equals("IOMA")){
                    nuevoPac.setObraSocial(Pacientes.ObraSocial.IOMA);
                }else {
                    nuevoPac.setObraSocial(Pacientes.ObraSocial.NONE);
                }
                nuevoPac.setSexo(rs.getString("sexo_paciente"));
                nuevoPac.setFechaNac(rs.getString("fec_nac_paciente"));
                nuevoPac.setMotivoConsulta("");
                nuevoPac.setAntecedentes("");
                nuevoPac.setHC(rs.getInt("hc"));
                nuevoPac.setUltima_modificacion(user);


            } else {
                LOG.debug("WARN:: paciente sin hc ");;
                JOptionPane.showMessageDialog(null, "Paciente sin HC");
                return null;
            }
            cn3.close();
            return nuevoPac;

        } catch (SQLException e) {
            LOG.debug("ERROR:: Error en cargar paciente " + e);;
            System.err.println("Error en cargar paciente " + e);
            JOptionPane.showMessageDialog(null, "Error al cargar paciente");
            return null;

        }
    }
}




