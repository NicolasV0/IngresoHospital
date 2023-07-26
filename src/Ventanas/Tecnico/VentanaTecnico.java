package Ventanas.Tecnico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import BaseDatos.Conexion;
import Ventanas.Login;

public class VentanaTecnico extends JFrame implements ActionListener {

    String user, nombre_usuario;
    public static int sesion_usuario;

    private JLabel lfondo, lnombre_tecnico, lnombre_usuario, lmodificar_usuario, leliminar_usuario;
    private JButton btn_registrar_usuario, btn_modificar_usuario, btn_eliminar_usuario, bnt_exit;

    public VentanaTecnico(){
        user = Login.user;
        sesion_usuario = 1;

        setTitle("Tecnico - Sesion de "+ user);
        setSize(600,400);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        lnombre_usuario = new JLabel("Registrar usuario");
        lnombre_usuario.setForeground(Color.white);
        lnombre_usuario.setFont(new Font("Arial",3,16));
        lnombre_usuario.setBounds(25,220,140,30);
        add(lnombre_usuario);

        btn_registrar_usuario = new JButton();
        btn_registrar_usuario.setBounds(40,100,100,100);
        ImageIcon image = new ImageIcon("src/Images/logo-paciente.png");
        Icon icono = new ImageIcon(image.getImage().getScaledInstance(btn_registrar_usuario.getWidth(), btn_registrar_usuario.getHeight(), Image.SCALE_DEFAULT));
        btn_registrar_usuario.setIcon(icono);
        btn_registrar_usuario.addActionListener(this);
        add(btn_registrar_usuario);

        lnombre_usuario = new JLabel("Modificar usuario");
        lnombre_usuario.setForeground(Color.white);
        lnombre_usuario.setFont(new Font("Arial",3,16));
        lnombre_usuario.setBounds(225,220,140,30);
        add(lnombre_usuario);

        btn_modificar_usuario = new JButton();
        btn_modificar_usuario.setBounds(240,100,100,100);
        ImageIcon image_reg = new ImageIcon("src/Images/modificar-paciente.png");
        Icon icono_reg = new ImageIcon(image_reg.getImage().getScaledInstance(btn_registrar_usuario.getWidth(), btn_registrar_usuario.getHeight(), Image.SCALE_DEFAULT));
        btn_modificar_usuario.setIcon(icono_reg);
        btn_modificar_usuario.addActionListener(this);
        add(btn_modificar_usuario);

        lnombre_usuario = new JLabel("Eliminar usuario");
        lnombre_usuario.setForeground(Color.white);
        lnombre_usuario.setFont(new Font("Arial",3,16));
        lnombre_usuario.setBounds(425,220,140,30);
        add(lnombre_usuario);

        btn_eliminar_usuario = new JButton();
        btn_eliminar_usuario.setBounds(440,100,100,100);
        ImageIcon image_el = new ImageIcon("src/Images/eliminar_paciente.png");
        Icon icono_el = new ImageIcon(image_el.getImage().getScaledInstance(btn_registrar_usuario.getWidth(), btn_registrar_usuario.getHeight(), Image.SCALE_DEFAULT));
        btn_eliminar_usuario.setIcon(icono_el);
        btn_eliminar_usuario.addActionListener(this);
        add(btn_eliminar_usuario);

        bnt_exit = new JButton("Exit");
        bnt_exit.setBounds(250,300,100,30);
        bnt_exit.setBackground(Color.red);
        bnt_exit.setForeground(Color.white);
        bnt_exit.addActionListener(this);
        add(bnt_exit);

        lnombre_tecnico = new JLabel();
        lnombre_tecnico.setBounds(10,10,140,30);
        lnombre_tecnico.setFont(new Font("Arial",3,12));
        lnombre_tecnico.setForeground(Color.white);
        add(lnombre_tecnico);


        lfondo = new JLabel();
        lfondo.setBounds(0,0,600,400);
        add(lfondo);

        ImageIcon imagefondo = new ImageIcon("src/Images/wallpaperAD.jpg");
        Icon iconofondo = new ImageIcon(imagefondo.getImage().getScaledInstance(lfondo.getWidth(),lfondo.getHeight(), Image.SCALE_DEFAULT));
        lfondo.setIcon(iconofondo);
        this.repaint();

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select nombre_usuario from usuarios where username = '" + user + "'");
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                nombre_usuario = rs.getString("nombre_usuario");
                lnombre_tecnico.setText(nombre_usuario);
            }
        }catch (Exception e){
            System.err.println("Error en la interfaz de administrador");

        }

    }


    public void actionPerformed(ActionEvent event){
        if (event.getSource().equals(btn_registrar_usuario)){
            new VentanaIngresoUsuario().setVisible(true);
        }
        if (event.getSource().equals(btn_modificar_usuario)){
            new VentanaModificarUsuario().setVisible(true);
        }
        if (event.getSource().equals(btn_eliminar_usuario)){
            new VentanaEliminarUsuarioTec().setVisible(true);
        }
        if (event.getSource().equals(bnt_exit)){
            new Login().setVisible(true);
            this.dispose();
        }
    }
}
