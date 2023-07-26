package Ventanas.Medicina;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import BaseDatos.Conexion;
import Ventanas.Login;

public class VentanaAtenderPacMed extends JFrame {
    String user;
    public static String paciente_update = "";
    public static String paciente_dni ="";
    DefaultTableModel model = new DefaultTableModel();

    private JLabel lfondo,ltitulo;
    private JScrollPane spTabla;
    private JTable tablaPacientes;
    String estatus = "Triagge";

    public VentanaAtenderPacMed(){
        user = Login.user;

        setSize(800,400);
        setTitle("Pool de espera - Sesion de "+user);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        ltitulo = new JLabel("Pacientes en espera");
        ltitulo.setBounds(320,10,250,30);
        ltitulo.setFont(new Font("Arial",1,20));
        ltitulo.setForeground(Color.white);
        add(ltitulo);

        tablaPacientes = new JTable();
        spTabla = new JScrollPane(tablaPacientes);
        spTabla.setBounds(80,70,630,300);
        add(spTabla);
        model.setRowCount(0);
        model.setColumnCount(0);

        try{
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("" +
                    "select hc, nombre_paciente, dni , hora, fecha_consulta, motivo_consulta, prioridad ,estatus from consulta where estatus = '" + estatus +"'");
            ResultSet rs = pst.executeQuery();
            tablaPacientes = new JTable(model);
            spTabla.setViewportView(tablaPacientes);

            model.addColumn("HC");
            model.addColumn("Nombre");
            model.addColumn("DNI");
            model.addColumn("Hora");
            model.addColumn("Fecha");
            model.addColumn("Motivo consulta");
            model.addColumn("Prioridad");
            model.addColumn("Estatus");


            while (rs.next()){
                Object[] fila = new Object[8];
                for (int i=0; i<8;i++){
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
                int columna_point = 1;
                int columna_point_dni = 2;

                if (fila_point >-1){
                    paciente_update = (String) model.getValueAt(fila_point,columna_point);
                    paciente_dni = (String) model.getValueAt(fila_point,columna_point_dni);
                    new VentanaConsultaMed().setVisible(true);
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