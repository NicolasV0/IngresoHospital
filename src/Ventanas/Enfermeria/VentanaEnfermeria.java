package Ventanas.Enfermeria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import BaseDatos.Conexion;
import Ventanas.Login;

public class VentanaEnfermeria extends JFrame implements ActionListener {

    static String user;
    String nombre_usuario;
    public static int sesion_usuario;

    private JLabel lfondo,ltitle,lnombre_usuario, latender_paciente, lmodificar_triage, lcant_triagados;
    private JButton btn_registrar_paciente, btn_modificar_triage, btn_cant_triagados, bnt_exit;

    public VentanaEnfermeria(){
        user = Login.user;
        sesion_usuario = 1;

        setTitle("Triage Enfermeria - Sesion de "+ user);
        setSize(600,400);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ltitle = new JLabel("Triagge de Enfermeria");
        ltitle.setForeground(Color.white);
        ltitle.setFont(new Font("Arial",3,16));
        ltitle.setBounds(250,10,200,30);
        add(ltitle);


        latender_paciente = new JLabel("Atender paciente");
        latender_paciente.setForeground(Color.white);
        latender_paciente.setFont(new Font("Arial",3,16));
        latender_paciente.setBounds(25,220,140,30);
        add(latender_paciente);

        btn_registrar_paciente = new JButton();
        btn_registrar_paciente.setBounds(40,100,100,100);
        ImageIcon image = new ImageIcon("src/Images/paciente.png");
        Icon icono = new ImageIcon(image.getImage().getScaledInstance(btn_registrar_paciente.getWidth(),btn_registrar_paciente.getHeight(), Image.SCALE_DEFAULT));
        btn_registrar_paciente.setIcon(icono);
        btn_registrar_paciente.addActionListener(this);
        add(btn_registrar_paciente);

        latender_paciente = new JLabel("Modificar Triagge");
        latender_paciente.setForeground(Color.white);
        latender_paciente.setFont(new Font("Arial",3,16));
        latender_paciente.setBounds(225,220,140,30);
        add(latender_paciente);

        btn_modificar_triage = new JButton();
        btn_modificar_triage.setBounds(240,100,100,100);
        ImageIcon image_reg = new ImageIcon("src/Images/modificar-paciente.png");
        Icon icono_reg = new ImageIcon(image_reg.getImage().getScaledInstance(btn_registrar_paciente.getWidth(),btn_registrar_paciente.getHeight(), Image.SCALE_DEFAULT));
        btn_modificar_triage.setIcon(icono_reg);
        btn_modificar_triage.addActionListener(this);
        add(btn_modificar_triage);

        lcant_triagados = new JLabel("Historial triagge");
        lcant_triagados.setForeground(Color.white);
        lcant_triagados.setFont(new Font("Arial",3,16));
        lcant_triagados.setBounds(425,220,140,30);
        add(lcant_triagados);

        btn_cant_triagados = new JButton();
        btn_cant_triagados.setBounds(440,100,100,100);
        ImageIcon image_el = new ImageIcon("src/Images/historial.png");
        Icon icono_el = new ImageIcon(image_el.getImage().getScaledInstance(btn_registrar_paciente.getWidth(),btn_registrar_paciente.getHeight(), Image.SCALE_DEFAULT));
        btn_cant_triagados.setIcon(icono_el);
        btn_cant_triagados.addActionListener(this);
        add(btn_cant_triagados);

        bnt_exit = new JButton("Exit");
        bnt_exit.setBounds(250,300,100,30);
        bnt_exit.setBackground(Color.red);
        bnt_exit.setForeground(Color.white);
        bnt_exit.addActionListener(this);
        add(bnt_exit);

        lnombre_usuario = new JLabel();
        lnombre_usuario.setBounds(10,10,140,30);
        lnombre_usuario.setFont(new Font("Arial",3,12));
        lnombre_usuario.setForeground(Color.white);
        add(lnombre_usuario);


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
                lnombre_usuario.setText(nombre_usuario);
            }
        }catch (Exception e){
            System.err.println("Error en la interfaz de enfermeria");

        }

    }


    public void actionPerformed(ActionEvent event){
        if (event.getSource().equals(btn_registrar_paciente)){
            new VentanaAtenderPacEnf().setVisible(true);
        }
        if (event.getSource().equals(btn_modificar_triage)){
            new VentanaModificarTriage().setVisible(true);
        }
        if (event.getSource().equals(bnt_exit)){
            new Login().setVisible(true);
            this.dispose();
        }
        if (event.getSource().equals(btn_cant_triagados)){
            new VentanaCantTriagados().setVisible(true);
            this.dispose();
        }
    }
}
