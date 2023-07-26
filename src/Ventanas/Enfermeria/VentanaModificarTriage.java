package Ventanas.Enfermeria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import BaseDatos.Conexion;
import Ventanas.Login;

public class VentanaModificarTriage extends JFrame {
    String user;
    public static String paciente_update = "";
    public static String paciente_dni ="";

    DefaultTableModel model = new DefaultTableModel();

    private JLabel lfondo,ltitulo;
    private JScrollPane spTabla;
    private JTable tablaPacientes;
    String estatus = "Triagge";


    public VentanaModificarTriage(){
        user = Login.user;

        setSize(800,400);
        setTitle("Triagge - Sesion de "+user);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        ltitulo = new JLabel("Pool Triagge");
        ltitulo.setBounds(320,10,250,30);
        ltitulo.setFont(new Font("Arial",1,20));
        ltitulo.setForeground(Color.white);
        add(ltitulo);

        tablaPacientes = new JTable();
        spTabla = new JScrollPane(tablaPacientes);
        spTabla.setBounds(80,70,630,300);
        add(spTabla);

        try{
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("" +
                    "select hora, nombre_paciente, dni, hc, fecha_consulta, estatus from consulta where estatus = '"+ estatus + "'");
            ResultSet rs = pst.executeQuery();
            tablaPacientes = new JTable(model);
            spTabla.setViewportView(tablaPacientes);

            model.addColumn("Hora");
            model.addColumn("Nombre");
            model.addColumn("DNI");
            model.addColumn("HC");
            model.addColumn("Fecha");
            model.addColumn("Estatus");


            while (rs.next()){
                Object[] fila = new Object[6];
                for (int i=0; i<6;i++){
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);
            }
            cn.close();

        }catch (SQLException e){
            System.err.println("Error al llenar tabla!");
            JOptionPane.showMessageDialog(null,"Error al mostrar informacion!");
        }
        tablaPacientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event){
                int fila_point = tablaPacientes.rowAtPoint(event.getPoint());
                int columna_id=3;
                int columna_point = 1;
                int columna_point_dni = 2;

                if (fila_point >-1){
                    //id_paciente = (String) model.getValueAt(fila_point,columna_id);
                    paciente_update = (String) model.getValueAt(fila_point,columna_point);
                    paciente_dni = (String) model.getValueAt(fila_point,columna_point_dni);

                    new VentanaModificarTriageAtrib().setVisible(true);
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
