package Ventanas.Enfermeria;

import BaseDatos.Conexion;
import Logs.Log;
import Ventanas.Administrativo.VentanaEliminarPacienteAdm;
import Ventanas.Login;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaCantTriagados extends JFrame {

        String user;
        String nombre_apellido = "";
        public static String paciente_update = "";
        public static String paciente_dni ="";

        DefaultTableModel model = new DefaultTableModel();

        private JLabel lfondo,ltitulo;
        private JScrollPane spTabla;
        private JTable tablaPacientes;
        private static final Logger LOG = Log.getLogger(VentanaCantTriagados.class);


    public VentanaCantTriagados(){
            LOG.debug("DEBUG:: ventana eliminar paciente");
            user = Login.user;
            nombre_apellido = Login.nombre_apellido;

            setSize(800,400);
            setTitle("Cantidad de paciente Triagados "+user);
            setResizable(false);
            setLayout(null);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            ltitulo = new JLabel("Pacientes Triagados");
            ltitulo.setBounds(320,10,250,30);
            ltitulo.setFont(new Font("Arial",1,20));
            ltitulo.setForeground(Color.white);
            add(ltitulo);

            tablaPacientes = new JTable();
            spTabla = new JScrollPane(tablaPacientes);
            spTabla.setBounds(80,70,630,300);
            add(spTabla);

            try{
                LOG.debug("INFO:: conectando base datos");

                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement("" +
                        "select hora_consulta, nombre_paciente, dni, hc, fecha_consulta, prioridad, enfermeria from historiaclinica where enfermeria = '"+ nombre_apellido + "'");
                ResultSet rs = pst.executeQuery();
                tablaPacientes = new JTable(model);
                spTabla.setViewportView(tablaPacientes);

                model.addColumn("Hora");
                model.addColumn("Nombre");
                model.addColumn("DNI");
                model.addColumn("HC");
                model.addColumn("Fecha");
                model.addColumn("Prioridad");


                while (rs.next()){
                    Object[] fila = new Object[6];
                    for (int i=0; i<6;i++){
                        fila[i] = rs.getObject(i + 1);
                    }
                    model.addRow(fila);
                }
                cn.close();

            }catch (SQLException e){
                LOG.debug("ERROR:: error al llenar tabla enfermeria");
                System.err.println("Error al llenar tabla!");
                JOptionPane.showMessageDialog(null,"Error al mostrar informacion!");
            }
            tablaPacientes.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event){
                    int fila_point = tablaPacientes.rowAtPoint(event.getPoint());
                    int columna_point = 1;
                    int columna_point_dni = 2;

                    if (fila_point >-1){
                        paciente_update = (String) model.getValueAt(fila_point,columna_point);
                        paciente_dni = (String) model.getValueAt(fila_point,columna_point_dni);

                    }
                }
            });


            lfondo = new JLabel();
            lfondo.setBounds(0,0,800,400);
            add(lfondo);

            ImageIcon imagefondo = new ImageIcon("src/Images/wallpaperAD.jpg");
            Icon iconofondo = new ImageIcon(imagefondo.getImage().getScaledInstance(lfondo.getWidth(),lfondo.getHeight(), Image.SCALE_DEFAULT));
            lfondo.setIcon(iconofondo);
            this.repaint();
        }
    }

