package Ventanas.Administrativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import BaseDatos.Conexion;
import Logs.Log;
import Ventanas.Administrativo.VentanaEliminarConsultaAd;
import Ventanas.Login;
import org.apache.log4j.Logger;

public class VentanaEliminarPacienteAdm extends JFrame {
    String user;
    public static String paciente_update = "";
    public static String paciente_dni ="";
    DefaultTableModel model = new DefaultTableModel();

    private JLabel lfondo,ltitulo;
    private JScrollPane spTabla;
    private JTable tablaPacientes;
    private static final Logger LOG = Log.getLogger(VentanaEliminarPacienteAdm.class);


    public VentanaEliminarPacienteAdm(){
        LOG.debug("DEBUG:: ventana eliminar paciente");

        user = Login.user;

        setSize(800,400);
        setTitle("Eliminar paciente - Sesion de "+user);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        ltitulo = new JLabel("Pacientes registrados");
        ltitulo.setBounds(320,10,250,30);
        ltitulo.setFont(new Font("Arial",1,20));
        ltitulo.setForeground(Color.white);
        add(ltitulo);

        tablaPacientes = new JTable();
        spTabla = new JScrollPane(tablaPacientes);
        spTabla.setBounds(80,70,630,300);
        add(spTabla);

        try{
            LOG.debug("INFO:: conectando base de datos");

            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("" +
                    "select hc, nombre_paciente, dni from consulta");
            ResultSet rs = pst.executeQuery();
            tablaPacientes = new JTable(model);
            spTabla.setViewportView(tablaPacientes);

            model.addColumn("HC");
            model.addColumn("Nombre");
            model.addColumn("DNI");

            while (rs.next()){
                Object[] fila = new Object[3];
                for (int i=0; i<3;i++){
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);
            }
            cn.close();

        }catch (SQLException e){
            LOG.debug("ERROR:: error al llenar tabla eliminar pacientes "+ e);
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
                    new VentanaEliminarConsultaAd().setVisible(true);
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
