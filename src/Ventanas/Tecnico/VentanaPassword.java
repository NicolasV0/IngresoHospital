package Ventanas.Tecnico;

import BaseDatos.Conexion;
import Usuarios.UsuarioImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VentanaPassword extends JFrame implements ActionListener {
    private JLabel lnuevoPass, lconfPass,ltitle,lfondo;
    private JTextField txnuevoPass, txconfPass;
    private JButton btnRestaurar;

    String usuario_update ="";




    public VentanaPassword(){
        usuario_update = VentanaModificarUsuario.usuario_update;

        setTitle("Restaurar Password");
        setSize(400,400);

        lfondo = new JLabel("Cambio Password "+ usuario_update);
        lfondo.setBounds(100,10,400,30);
        lfondo.setFont(new Font("Arial",1,20));
        lfondo.setForeground(Color.white);
        add(lfondo);

        lnuevoPass = new JLabel("Nuevo password");
        lnuevoPass.setBounds(100,60,120,30);
        lnuevoPass.setFont(new Font("Arial",1,12));
        lnuevoPass.setForeground(Color.white);
        add(lnuevoPass);

        txnuevoPass = new JTextField();
        txnuevoPass.setBounds(100,100,120,30);
        txnuevoPass.setFont(new Font("Arial",1,12));
        txnuevoPass.setBackground(Color.DARK_GRAY);
        txnuevoPass.setForeground(Color.white);
        add(txnuevoPass);

        lconfPass = new JLabel("Confirmar password");
        lconfPass.setBounds(100,140,120,30);
        lconfPass.setFont(new Font("Arial",1,12));
        lconfPass.setForeground(Color.white);
        add(lconfPass);

        txconfPass = new JTextField();
        txconfPass.setBounds(100,180,120,30);
        txconfPass.setFont(new Font("Arial",1,12));
        txconfPass.setBackground(Color.DARK_GRAY);
        txconfPass.setForeground(Color.white);
        add(txconfPass);

        btnRestaurar = new JButton("Restaurar Password");
        btnRestaurar.setBounds(100,300,150,30);
        btnRestaurar.setBackground(Color.red);
        btnRestaurar.setForeground(Color.white);
        btnRestaurar.addActionListener(this);
        add(btnRestaurar);




        lfondo = new JLabel();
        lfondo.setBounds(0,0,600,400);
        add(lfondo);

        ImageIcon imagefondo = new ImageIcon("src/Images/wallpaperAD.jpg");
        Icon iconofondo = new ImageIcon(imagefondo.getImage().getScaledInstance(lfondo.getWidth(),lfondo.getHeight(), Image.SCALE_DEFAULT));
        lfondo.setIcon(iconofondo);
        this.repaint();
    }


    public void actionPerformed(ActionEvent event){
        if (event.getSource().equals(btnRestaurar)){
            String password, confirmacion_pass;
            password = txnuevoPass.getText().trim();
            confirmacion_pass = txconfPass.getText().trim();

            if (!password.isEmpty() && !confirmacion_pass.isEmpty()){
                if (password.equals(confirmacion_pass)){
                    UsuarioImpl usuario = new UsuarioImpl();
                    usuario.cambiarPassword(usuario_update,password);
                    this.dispose();

                }else {
                    txconfPass.setBackground(Color.red);
                    JOptionPane.showMessageDialog(null,"Las contraseñas no coinciden");
                }
            }else {
                txconfPass.setBackground(Color.red);
                txnuevoPass.setBackground(Color.red);
                JOptionPane.showMessageDialog(null,"Debe completar todos los campos");
            }
        }
    }
}
