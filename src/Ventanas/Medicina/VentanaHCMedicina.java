package Ventanas.Medicina;

import BaseDatos.Conexion;
import HistoriaClinica.historiaClinica;
import Ventanas.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VentanaHCMedicina extends JFrame implements ActionListener {
    String user, nombre_usuario;
    private JLabel lfondo,ltitle,lnombre_usuario, lbuscar_paciente;
    private JButton btn_buscar_paciente, bnt_exit;
    private JTextField txbuscar_paciente;




    public VentanaHCMedicina(){
        user = Login.user;

        setTitle("Historia Clinica- Sesion de "+ user);
        setSize(600,400);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ltitle = new JLabel("Buscar HC.");
        ltitle.setForeground(Color.white);
        ltitle.setFont(new Font("Arial",3,16));
        ltitle.setBounds(250,10,200,30);
        add(ltitle);


        lbuscar_paciente = new JLabel("Ingrese DNI de paciente");
        lbuscar_paciente.setForeground(Color.white);
        lbuscar_paciente.setFont(new Font("Arial",3,16));
        lbuscar_paciente.setBounds(25,120,200,30);
        add(lbuscar_paciente);

        txbuscar_paciente = new JTextField();
        txbuscar_paciente.setBounds(25,200,200,30);
        txbuscar_paciente.setFont(new Font("Arial",1,12));
        txbuscar_paciente.setBackground(Color.DARK_GRAY);
        txbuscar_paciente.setForeground(Color.white);
        add(txbuscar_paciente);

        btn_buscar_paciente = new JButton("Buscar");
        btn_buscar_paciente.setBounds(150,300,100,30);
        btn_buscar_paciente.setBackground(Color.MAGENTA);
        btn_buscar_paciente.setForeground(Color.white);
        btn_buscar_paciente.addActionListener(this);
        add(btn_buscar_paciente);


        bnt_exit = new JButton("Exit");
        bnt_exit.setBounds(275,300,100,30);
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
        if (event.getSource().equals(btn_buscar_paciente)){
            String dni = txbuscar_paciente.getText().trim();

            historiaClinica historiaClinica = new historiaClinica();
            historiaClinica.buscarHC(dni);
        }
        if (event.getSource().equals(bnt_exit)){
            this.dispose();
        }

    }

}
