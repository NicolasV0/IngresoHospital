package BaseDatos;
import Logs.Log;
import Pacientes.Consulta;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaBD {
    private static final Logger LOG = Log.getLogger(ConsultaBD.class);

    public ConsultaBD() {
    }


    public Consulta buscarConsBD(String nombre_paciente, String paciente_dni_update){
        Consulta consulta1 = new Consulta();
        try{
            LOG.debug("INFO:: buscando en base datos consulta");

            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("" +
                    "select * from consulta where dni = '" + paciente_dni_update + "'");
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                consulta1.setHc(rs.getInt("hc"));
                consulta1.setNombre_paciente(rs.getString("nombre_paciente"));
                consulta1.setMotivo_consulta(rs.getString("motivo_consulta"));
                consulta1.setHora(rs.getString("hora"));
                consulta1.setFecha_consulta(rs.getString("fecha_consulta"));
                consulta1.setEstatus(rs.getString("estatus"));
                consulta1.setPrioridad(rs.getInt("prioridad"));
                consulta1.setEnfermeria(rs.getString("enfermeria"));
                consulta1.setMedicina(rs.getString("medicina"));
                consulta1.setObservaciones_enf(rs.getString("observacion_enf"));
                consulta1.setObservaciones_med(rs.getString("observacion_med"));
                consulta1.setEpicrisis(rs.getString("epicrisis"));
                consulta1.setAntecedentes(rs.getString("antecedentes"));
                consulta1.setTas(rs.getInt("tas"));
                consulta1.setTad(rs.getInt("tad"));
                consulta1.setSat(rs.getInt("sat"));
                consulta1.setTemperatura(rs.getFloat("temperatura"));
                consulta1.setFr(rs.getInt("fr"));
                consulta1.setFc(rs.getInt("fc"));
                consulta1.setDni(rs.getString("dni"));
                consulta1.setId_consulta(rs.getInt("id_consulta"));
                return consulta1;

            }
            cn.close();

        }catch (SQLException e){
            LOG.debug("ERROR:: al contactar base datos consulta "+ e);

            System.err.println("Error en cargar paciente " + e);
            JOptionPane.showMessageDialog(null, "Error al cargar paciente");
            return null;

        }
        return null;
    }

    public void crearNuevaConsulta(String dni, int hc, String hora_ingreso, String mes_ingreso, String año_ingreso,
                                   String minutos_ingreso, String dia_ingreso, String nombre_pac, String apellido_pac) {
        try {
            LOG.debug("INFO:: insertando en base datos consulta");

            Connection cn4 = Conexion.conectar();
            PreparedStatement pst4 = cn4.prepareStatement("select dni from consulta where dni = '" + dni + "'");
            ResultSet rs = pst4.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Paciente ya en espera");
                cn4.close();
            } else {
                cn4.close();
                try {

                    Connection cn5 = Conexion.conectar();
                    PreparedStatement pst5 = cn5.prepareStatement("" +
                            "insert into consulta values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    pst5.setInt(1, hc);
                    pst5.setString(2, "");
                    pst5.setString(3, hora_ingreso + ":" + minutos_ingreso);
                    pst5.setString(4, dia_ingreso + "/" + mes_ingreso + "/" + año_ingreso);
                    pst5.setString(5, "Nuevo Ingreso");
                    pst5.setString(6, "");
                    pst5.setString(7, "");
                    pst5.setString(8, "");
                    pst5.setString(9, "");
                    pst5.setString(10, "");
                    pst5.setString(11, "");
                    pst5.setString(12, "");
                    pst5.setInt(13, 1);
                    pst5.setInt(14, 1);
                    pst5.setInt(15, 1);
                    pst5.setDouble(16, 1f);
                    pst5.setInt(17, 1);
                    pst5.setInt(18, 1);
                    pst5.setString(19, nombre_pac + " " + apellido_pac);
                    pst5.setString(20, dni);
                    pst5.setInt(21, 0);


                    pst5.executeUpdate();
                    cn5.close();

                    JOptionPane.showMessageDialog(null, "Consulta cargada");


                } catch (SQLException e) {
                    LOG.debug("ERROR:: en registrar en base datos consulta "+e);

                    System.err.println("Error en registrar paciente " + e);
                    JOptionPane.showMessageDialog(null, "Error al registrar...");
                }

            }

        } catch (SQLException e) {
            LOG.debug("INFO:: error en validad paciente "+ e);
            System.err.println("Error en validad paciente " + e);
            JOptionPane.showMessageDialog(null, "No se pudo contactar la base de datos");
        }
    }

    public void triageEnfermeriaBD(String dni_pac, int hc, String nombre_apellido, String motivo_consulta,
                                   String prioridad, String observaciones_pac, String antecedentes,
                                   String tas, String tad, String sat_pac, String temperatura_pac,
                                   String fr_pac, String fc_pac) {
        try {
            LOG.debug("INFO:: conectando triagge enfermeria base datos consulta");

            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select id_consulta from consulta where dni = '" + dni_pac + "' and not hc = '" + hc + "'");

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                LOG.debug("WARN:: paciente no disponibel bd consulta");
                JOptionPane.showMessageDialog(null, "Paciente no disponible");

            } else {
                Connection cn2 = Conexion.conectar();
                PreparedStatement pst2 = cn2.prepareStatement("update consulta set motivo_consulta=?, prioridad=?, enfermeria=?, observacion_enf=?, antecedentes=?, tas=?, tad=?, sat=?, temperatura=?, fr=?, fc=?, estatus=? where hc = '" + hc + "'");
                pst2.setString(1, motivo_consulta);
                pst2.setString(2, prioridad);
                pst2.setString(3, nombre_apellido);
                pst2.setString(4, observaciones_pac);
                pst2.setString(5, antecedentes);
                pst2.setString(6, tas);
                pst2.setString(7, tad);
                pst2.setString(8, sat_pac);
                pst2.setString(9, temperatura_pac);
                pst2.setString(10, fr_pac);
                pst2.setString(11, fc_pac);
                pst2.setString(12, "Triagge");

                pst2.executeUpdate();
                cn2.close();
                LOG.debug("INFO:: acutalizacion correcta");

                JOptionPane.showMessageDialog(null, "Actulizacion correcta");

            }
        } catch (SQLException e) {
            LOG.debug("ERROR:: error al actualizar informacion " + e);

            System.err.println("Error al actualizar informacion " + e);
        }

    }


    public void consultaMedicaBD(int hc, String paciente_dni_update, String nombre_apellido,
                                 String epicrisis, String observacion_med, String antecedentes){
        try{
            LOG.debug("INFO:: conectando consulta medica a base datos consulta");

            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select dni from consulta where dni = '"+ paciente_dni_update + "' and not hc = '"+hc+"'");

            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                LOG.debug("WARN:: Paciente no disponible");

                JOptionPane.showMessageDialog(null,"Paciente no disponible");

            }else {
                Connection cn2 = Conexion.conectar();
                PreparedStatement pst2 = cn2.prepareStatement("update consulta set antecedentes=?, estatus=?, medicina=?, epicrisis=?, observacion_med=? where hc = '" + hc +"'");
                pst2.setString(1,antecedentes);
                pst2.setString(2,"Atendido");
                pst2.setString(3,nombre_apellido);
                pst2.setString(4,epicrisis);
                pst2.setString(5,observacion_med);


                pst2.executeUpdate();
                cn2.close();
                LOG.debug("INFO:: actulizacion correcta");

                JOptionPane.showMessageDialog(null,"Actulizacion correcta");

            }
        }catch (SQLException e){
            LOG.debug("ERROR:: error al actualizar informacion " + e);

            System.err.println("Error al actualizar informacion " + e);
        }
    }


    public void eliminarConsultaBD(String dni){

        try{
            LOG.debug("INFO:: eliminando consulta en base datos consulta");

            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "delete from consulta where dni = ?");

            pst.setString(1,dni);
            pst.executeUpdate();
            LOG.debug("INFO:: consulta eliminada");

            JOptionPane.showMessageDialog(null,"Consulta eliminada");
        }catch (SQLException e){
            LOG.debug("ERROR:: error al actualizar informacion " + e);
            System.err.println("Error al actualizar informacion " + e);
        }
    }
}
