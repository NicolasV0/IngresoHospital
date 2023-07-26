package Ventanas.Tecnico;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import BaseDatos.Conexion;
import Ventanas.Login;
import Ventanas.Tecnico.VentanaEliminarUsuarioPermanente;

public class VentanaEliminarUsuarioTec extends JFrame {
    String user;
    public static String usuario_update = "";
    DefaultTableModel model = new DefaultTableModel();

    private JLabel lfondo,ltitulo;
    private JScrollPane spTabla;
    private JTable tablaUsuarios;

    public VentanaEliminarUsuarioTec(){
        user = Login.user;

        setSize(800,400);
        setTitle("Eliminar usuario - Sesion de "+user);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        ltitulo = new JLabel("Usuarios registrados");
        ltitulo.setBounds(320,10,250,30);
        ltitulo.setFont(new Font("Arial",1,20));
        ltitulo.setForeground(Color.white);
        add(ltitulo);

        tablaUsuarios = new JTable();
        spTabla = new JScrollPane(tablaUsuarios);
        spTabla.setBounds(80,70,630,300);
        add(spTabla);

        try{
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("" +
                    "select id_usuario, nombre_usuario, username, tipo_nivel from usuarios");
            ResultSet rs = pst.executeQuery();
            tablaUsuarios = new JTable(model);
            spTabla.setViewportView(tablaUsuarios);

            model.addColumn("ID");
            model.addColumn("Nombre y Apellido");
            model.addColumn("Usuario");
            model.addColumn("Tipo");

            while (rs.next()){
                Object[] fila = new Object[4];
                for (int i=0; i<4;i++){
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);
            }
            cn.close();

        }catch (SQLException e){
            System.err.println("Error al llenar tabla!");
            JOptionPane.showMessageDialog(null,"Error al mostrar informacion!");
        }
        tablaUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event){
                int fila_point = tablaUsuarios.rowAtPoint(event.getPoint());
                int columna_point = 2;

                if (fila_point >-1){
                    usuario_update = (String) model.getValueAt(fila_point,columna_point);
                    new VentanaEliminarUsuarioPermanente().setVisible(true);
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
