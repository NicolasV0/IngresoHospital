package Ventanas.Medicina;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import BaseDatos.Conexion;
import Ventanas.Login;


public class VentanaMedicina extends JFrame implements ActionListener {

    String user, nombre_usuario;
    private JLabel lfondo,ltitle,lnombre_usuario, latender_paciente, lmodificar_paciente, lregistrar_consulta, lhistoriaClinica;
    private JButton btn_atender_paciente, btn_modificar_paciente, btn_registrar_consulta,btn_historiaClinica ,bnt_exit;

    public VentanaMedicina(){
        user = Login.user;

        setTitle("Pool de espera - Sesion de "+ user);
        setSize(800,400);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ltitle = new JLabel("Pool de espera");
        ltitle.setForeground(Color.white);
        ltitle.setFont(new Font("Arial",3,16));
        ltitle.setBounds(350,10,200,30);
        add(ltitle);


        latender_paciente = new JLabel("Atender paciente");
        latender_paciente.setForeground(Color.white);
        latender_paciente.setFont(new Font("Arial",3,16));
        latender_paciente.setBounds(25,220,140,30);
        add(latender_paciente);

        btn_atender_paciente = new JButton();
        btn_atender_paciente.setBounds(40,100,100,100);
        ImageIcon image = new ImageIcon("src/Images/paciente.png");
        Icon icono = new ImageIcon(image.getImage().getScaledInstance(btn_atender_paciente.getWidth(), btn_atender_paciente.getHeight(), Image.SCALE_DEFAULT));
        btn_atender_paciente.setIcon(icono);
        btn_atender_paciente.addActionListener(this);
        add(btn_atender_paciente);

        lmodificar_paciente = new JLabel("Modificar paciente");
        lmodificar_paciente.setForeground(Color.white);
        lmodificar_paciente.setFont(new Font("Arial",3,16));
        lmodificar_paciente.setBounds(225,220,140,30);
        add(lmodificar_paciente);

        btn_modificar_paciente = new JButton();
        btn_modificar_paciente.setBounds(240,100,100,100);
        ImageIcon image_reg = new ImageIcon("src/Images/modificar-pac-med.png");
        Icon icono_reg = new ImageIcon(image_reg.getImage().getScaledInstance(btn_atender_paciente.getWidth(), btn_atender_paciente.getHeight(), Image.SCALE_DEFAULT));
        btn_modificar_paciente.setIcon(icono_reg);
        btn_modificar_paciente.addActionListener(this);
        add(btn_modificar_paciente);

        lregistrar_consulta = new JLabel("Registrar consulta");
        lregistrar_consulta.setForeground(Color.white);
        lregistrar_consulta.setFont(new Font("Arial",3,16));
        lregistrar_consulta.setBounds(425,220,140,30);
        add(lregistrar_consulta);

        btn_registrar_consulta = new JButton();
        btn_registrar_consulta.setBounds(440,100,100,100);
        ImageIcon image_el = new ImageIcon("src/Images/registrar-consulta.png");
        Icon icono_el = new ImageIcon(image_el.getImage().getScaledInstance(btn_atender_paciente.getWidth(), btn_atender_paciente.getHeight(), Image.SCALE_DEFAULT));
        btn_registrar_consulta.setIcon(icono_el);
        btn_registrar_consulta.addActionListener(this);
        add(btn_registrar_consulta);

        lhistoriaClinica = new JLabel("Historia clinica");
        lhistoriaClinica.setForeground(Color.white);
        lhistoriaClinica.setFont(new Font("Arial",3,16));
        lhistoriaClinica.setBounds(625,220,140,30);
        add(lhistoriaClinica);

        btn_historiaClinica = new JButton();
        btn_historiaClinica.setBounds(640,100,100,100);
        ImageIcon image_hc = new ImageIcon("src/Images/hc.png");
        Icon icono_hc = new ImageIcon(image_hc.getImage().getScaledInstance(btn_historiaClinica.getWidth(), btn_historiaClinica.getHeight(), Image.SCALE_DEFAULT));
        btn_historiaClinica.setIcon(icono_hc);
        btn_historiaClinica.addActionListener(this);
        add(btn_historiaClinica);

        bnt_exit = new JButton("Exit");
        bnt_exit.setBounds(350,300,100,30);
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
        lfondo.setBounds(0,0,800,400);
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
            System.err.println("Error en la interfaz de medicina");

        }

    }


    public void actionPerformed(ActionEvent event){
        if (event.getSource().equals(btn_atender_paciente)){
            new VentanaAtenderPacMed().setVisible(true);
        }
        if (event.getSource().equals(btn_modificar_paciente)){
            new VentanaModificarPacAtMed().setVisible(true);
        }
        if (event.getSource().equals(btn_registrar_consulta)){
            new VentanaRegistrarConsultaMed().setVisible(true);
        }
        if (event.getSource().equals(btn_historiaClinica)){
            new VentanaHCMedicina().setVisible(true);
        }
        if (event.getSource().equals(bnt_exit)){
            new Login().setVisible(true);
            this.dispose();
        }

    }
}