package BaseDatos;

import Logs.Log;
import Pacientes.Consulta;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class historiaClinicaBD {
    private static final Logger LOG = Log.getLogger(historiaClinicaBD.class);

    public historiaClinicaBD() {
    }

    public void ingresarHCBD(int hc, String nombre, String dni, int id_consulta, String motivo_consulta,
                           String fecha, String hora, String status, int prioridad, String enfermeria,
                           String medicina, String observaciones_med, String observaciones_enf,
                           String epicrisis, String antecedentes, String controles){
        try {
            LOG.debug("INFO:: conectando base datos historia clinica");
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("insert into historiaclinica values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, hc);
            pst.setString(2, nombre);
            pst.setString(3, dni);
            pst.setInt(4, id_consulta);
            pst.setString(5, motivo_consulta);
            pst.setString(6, fecha);
            pst.setString(7, hora);
            pst.setString(8, status);
            pst.setInt(9, prioridad);
            pst.setString(10, enfermeria);
            pst.setString(11, medicina);
            pst.setString(12, observaciones_med);
            pst.setString(13, observaciones_enf);
            pst.setString(14, epicrisis);
            pst.setString(15, antecedentes);
            pst.setString(16, controles);

            pst.executeUpdate();
            cn.close();



        } catch (SQLException e) {
            LOG.debug("ERROR:: error base datos historia clinica");
            System.err.println("Error en registrar historia clinica " + e);
            JOptionPane.showMessageDialog(null, "Error al registrar...");
        }
    }


    public List<Consulta> buscarConsBD(String dni){
        int hc;
        int prioridad;
        String nombre, motivo_consulta,fecha, hora, status, enfermeria, medicina;
        String observaciones_med,  observaciones_enf, epicrisis, antecedentes, controles;
        List<Consulta> listaHC = new ArrayList<>();
        try{
            LOG.debug("INFO:: buscar en base datos historia clinica");

            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("" +
                    "select * from historiaclinica where dni = '" + dni + "'");
            ResultSet rs = pst.executeQuery();

            if (rs.next()){
                do {
                    Consulta nuevaConsulta = new Consulta();
                    hc = rs.getInt("hc");
                    nombre = rs.getString("nombre_paciente");
                    motivo_consulta = rs.getString("motivo_consulta");
                    fecha = rs.getString("fecha_consulta");
                    hora = rs.getString("hora_consulta");
                    status = rs.getString("estatus");
                    prioridad = rs.getInt("prioridad");
                    enfermeria = rs.getString("enfermeria");
                    medicina = rs.getString("medicina");
                    observaciones_med = rs.getString("observaciones_med");
                    observaciones_enf = rs.getString("observaciones_enf");
                    epicrisis = rs.getString("epicrisis");
                    antecedentes = rs.getString("antecedentes");
                    controles = rs.getString("controles");

                    nuevaConsulta.setHc(hc);
                    nuevaConsulta.setNombre_paciente(nombre);
                    nuevaConsulta.setMotivo_consulta(motivo_consulta);
                    nuevaConsulta.setFecha_consulta(fecha);
                    nuevaConsulta.setHora(hora);
                    nuevaConsulta.setEstatus(status);
                    nuevaConsulta.setPrioridad(prioridad);
                    nuevaConsulta.setEnfermeria(enfermeria);
                    nuevaConsulta.setMedicina(medicina);
                    nuevaConsulta.setObservaciones_med(observaciones_med);
                    nuevaConsulta.setEpicrisis(epicrisis);
                    nuevaConsulta.setAntecedentes(antecedentes);
                    nuevaConsulta.setObservaciones_enf(controles);
                    listaHC.add(nuevaConsulta);

                }while (rs.next());
                cn.close();
            }

        }catch (SQLException e){
            LOG.debug("ERROR:: error en cargar paciente "+e);

            System.err.println("Error en cargar paciente " + e);
            JOptionPane.showMessageDialog(null, "Error al cargar paciente");

        }
        return listaHC;


    }
}
