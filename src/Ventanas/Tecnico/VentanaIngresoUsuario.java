package Ventanas.Tecnico;
import BaseDatos.Conexion;
import Usuarios.UsuarioImpl;
import Ventanas.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaIngresoUsuario extends JFrame implements ActionListener {

    private JLabel lregistro_usuario, lnombre_usuario, lapellido_usuario, lpassword, lusername,
            ltipo_nivel, lmail,lfondo,ltelfono;
    private JTextField txnombre_usuario, txapellido_usuario, txpassword, txusername, txmail,txtelefono;
    private JComboBox comboTipo_Nivel;

    private JButton btnAgregar, btnCancelar;

    String user;



    public VentanaIngresoUsuario(){
        user = Login.user;

        setSize(600,400);
        setTitle("Ingreso de usuario - Sesion de "+user);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);



        lregistro_usuario = new JLabel("Registro de usuario");
        lregistro_usuario.setBounds(220,10,250,30);
        lregistro_usuario.setFont(new Font("Arial",1,20));
        lregistro_usuario.setForeground(Color.white);
        add(lregistro_usuario);

        lnombre_usuario = new JLabel("Nombre");
        lnombre_usuario.setBounds(10,60,120,30);
        lnombre_usuario.setFont(new Font("Arial",1,12));
        lnombre_usuario.setForeground(Color.white);
        add(lnombre_usuario);

        txnombre_usuario = new JTextField();
        txnombre_usuario.setBounds(140,60,120,30);
        txnombre_usuario.setFont(new Font("Arial",1,12));
        txnombre_usuario.setBackground(Color.DARK_GRAY);
        txnombre_usuario.setForeground(Color.white);
        add(txnombre_usuario);


        lapellido_usuario = new JLabel("Apellido");
        lapellido_usuario.setBounds(10,100,120,30);
        lapellido_usuario.setFont(new Font("Arial",1,12));
        lapellido_usuario.setForeground(Color.white);
        add(lapellido_usuario);

        txapellido_usuario = new JTextField();
        txapellido_usuario.setBounds(140,100,120,30);
        txapellido_usuario.setFont(new Font("Arial",1,12));
        txapellido_usuario.setBackground(Color.DARK_GRAY);
        txapellido_usuario.setForeground(Color.white);
        add(txapellido_usuario);


        lusername = new JLabel("Username");
        lusername.setBounds(10,140,120,30);
        lusername.setFont(new Font("Arial",1,12));
        lusername.setForeground(Color.white);
        add(lusername);

        txusername = new JTextField();
        txusername.setBounds(140,140,120,30);
        txusername.setFont(new Font("Arial",1,12));
        txusername.setBackground(Color.DARK_GRAY);
        txusername.setForeground(Color.white);
        add(txusername);


        ltipo_nivel = new JLabel("Tipo Nivel");
        ltipo_nivel.setBounds(10,180,120,30);
        ltipo_nivel.setFont(new Font("Arial",1,12));
        ltipo_nivel.setForeground(Color.white);
        add(ltipo_nivel);

        comboTipo_Nivel = new JComboBox();
        comboTipo_Nivel.setBounds(140,180,120,30);
        comboTipo_Nivel.setFont(new Font("Arial",1,12));
        comboTipo_Nivel.setBackground(Color.DARK_GRAY);
        comboTipo_Nivel.setForeground(Color.white);
        comboTipo_Nivel.addItem("Tecnico");
        comboTipo_Nivel.addItem("Administrativo");
        comboTipo_Nivel.addItem("Enfermeria");
        comboTipo_Nivel.addItem("Medicina");
        add(comboTipo_Nivel);


        lpassword = new JLabel("Password ");
        lpassword.setBounds(300,60,120,30);
        lpassword.setFont(new Font("Arial",1,12));
        lpassword.setForeground(Color.white);
        add(lpassword);

        txpassword = new JTextField();
        txpassword.setBounds(440,60,120,30);
        txpassword.setFont(new Font("Arial",1,12));
        txpassword.setBackground(Color.DARK_GRAY);
        txpassword.setForeground(Color.white);
        add(txpassword);


        lmail = new JLabel("Mail ");
        lmail.setBounds(300,100,120,30);
        lmail.setFont(new Font("Arial",1,12));
        lmail.setForeground(Color.white);
        add(lmail);

        txmail = new JTextField();
        txmail.setBounds(440,100,120,30);
        txmail.setFont(new Font("Arial",1,12));
        txmail.setBackground(Color.DARK_GRAY);
        txmail.setForeground(Color.white);
        add(txmail);


        ltelfono = new JLabel("Telefono ");
        ltelfono.setBounds(300,140,120,30);
        ltelfono.setFont(new Font("Arial",1,12));
        ltelfono.setForeground(Color.white);
        add(ltelfono);

        txtelefono = new JTextField();
        txtelefono.setBounds(440,140,120,30);
        txtelefono.setFont(new Font("Arial",1,12));
        txtelefono.setBackground(Color.DARK_GRAY);
        txtelefono.setForeground(Color.white);
        add(txtelefono);

        btnAgregar = new JButton("Aceptar");
        btnAgregar.setBounds(300,300,100,30);
        btnAgregar.setBackground(Color.DARK_GRAY);
        btnAgregar.setForeground(Color.white);
        btnAgregar.addActionListener(this);
        add(btnAgregar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(440,300,100,30);
        btnCancelar.setBackground(Color.red);
        btnCancelar.setForeground(Color.white);
        btnCancelar.addActionListener(this);
        add(btnCancelar);


        lfondo = new JLabel();
        lfondo.setBounds(0,0,600,400);
        add(lfondo);

        ImageIcon imagefondo = new ImageIcon("src/Images/wallpaperAD.jpg");
        Icon iconofondo = new ImageIcon(imagefondo.getImage().getScaledInstance(lfondo.getWidth(),lfondo.getHeight(), Image.SCALE_DEFAULT));
        lfondo.setIcon(iconofondo);
        this.repaint();

    }

    public void actionPerformed(ActionEvent event){
        int combo_nivel, validacion =0;
        String nombre_us,apellido_us, username_us, mail_us, password_us, tel_us, combro_string="";
        if (event.getSource().equals(btnAgregar)){
            nombre_us = txnombre_usuario.getText().trim();
            apellido_us = txapellido_usuario.getText().trim();
            username_us = txusername.getText().trim();
            mail_us = txmail.getText().trim();
            password_us = txpassword.getText().trim();
            tel_us = txtelefono.getText().trim();
            combo_nivel = comboTipo_Nivel.getSelectedIndex() +1;

            validacion = validarCampos();
            if (combo_nivel == 1){
                combro_string = "Tecnico";
            } else if (combo_nivel == 2){
                combro_string = "Administrativo";
            } else if (combo_nivel == 3){
                combro_string = "Enfermeria";
            } else if (combo_nivel == 4){
                combro_string = "Medicina";
            }


            if (validacion == 0){
                UsuarioImpl nuevoUsuario = new UsuarioImpl();
                nuevoUsuario.ingresarNuevoUsuario(username_us, nombre_us, apellido_us, mail_us, tel_us,
                        password_us,
                        combro_string);
                this.dispose();
            }
            else {
                JOptionPane.showMessageDialog(null,"Debe llenar todos los campos");
            }

            }
        if (event.getSource().equals(btnCancelar)){
            this.dispose();
        }
    }

    public int validarCampos(){
        String nombre_us,apellido_us, username_us, mail_us, password_us, tel_us, combro_string="";
        int combo_nivel;
        nombre_us = txnombre_usuario.getText().trim();
        apellido_us = txapellido_usuario.getText().trim();
        username_us = txusername.getText().trim();
        mail_us = txmail.getText().trim();
        password_us = txpassword.getText().trim();
        tel_us = txtelefono.getText().trim();
        combo_nivel = comboTipo_Nivel.getSelectedIndex() +1;
        int validacion =0;
        if (nombre_us.isEmpty()){
            txnombre_usuario.setBackground(Color.red);
            validacion++;
        }

        if (apellido_us.isEmpty()){
            txapellido_usuario.setBackground(Color.red);
            validacion++;
        }

        if (username_us.isEmpty()){
            txusername.setBackground(Color.red);
            validacion++;
        }

        if (mail_us.isEmpty()){
            txmail.setBackground(Color.red);
            validacion++;
        }


        if (password_us.isEmpty()){
            txpassword.setBackground(Color.red);
            validacion++;
        }
        if (tel_us.isEmpty()){
            txtelefono.setBackground(Color.red);
            validacion++;
        }
        return validacion;
    }

    public void limpiar(){
        txtelefono.setText("");
        txpassword.setText("");
        txnombre_usuario.setText("");
        txusername.setText("");
        txmail.setText("");
        txapellido_usuario.setText("");

    }
}