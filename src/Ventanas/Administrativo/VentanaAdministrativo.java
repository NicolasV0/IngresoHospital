package Ventanas.Administrativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import BaseDatos.Conexion;
import Logs.Log;
import Ventanas.Login;
import Ventanas.Tecnico.VentanaTecnico;
import org.apache.log4j.Logger;

public class VentanaAdministrativo extends JFrame implements ActionListener {

    String user, nombre_usuario;
    int sesion_usuario;

    private JLabel lfondo,lnombre_usuario,lnombre_paciente, lmodificar_paciente, leliminar_paciente;
    private JButton btn_registrar_paciente, btn_modificar_paciente, btn_eliminar_paciente, bnt_exit;

    private static final Logger LOG = Log.getLogger(VentanaAdministrativo.class);


    public VentanaAdministrativo(){
        LOG.debug("DEBUG:: ventana administrativo");

        user = Login.user;
        sesion_usuario = VentanaTecnico.sesion_usuario;

        setTitle("Administrativo - Sesion de "+ user);
        setSize(600,400);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        lnombre_paciente = new JLabel("Registrar paciente");
        lnombre_paciente.setForeground(Color.white);
        lnombre_paciente.setFont(new Font("Arial",3,16));
        lnombre_paciente.setBounds(25,220,140,30);
        add(lnombre_paciente);

        btn_registrar_paciente = new JButton();
        btn_registrar_paciente.setBounds(40,100,100,100);
        ImageIcon image = new ImageIcon("src/Images/usuario.png");
        Icon icono = new ImageIcon(image.getImage().getScaledInstance(btn_registrar_paciente.getWidth(),btn_registrar_paciente.getHeight(), Image.SCALE_DEFAULT));
        btn_registrar_paciente.setIcon(icono);
        btn_registrar_paciente.addActionListener(this);
        add(btn_registrar_paciente);

        lnombre_paciente = new JLabel("Modificar paciente");
        lnombre_paciente.setForeground(Color.white);
        lnombre_paciente.setFont(new Font("Arial",3,16));
        lnombre_paciente.setBounds(225,220,140,30);
        add(lnombre_paciente);

        btn_modificar_paciente = new JButton();
        btn_modificar_paciente.setBounds(240,100,100,100);
        ImageIcon image_reg = new ImageIcon("src/Images/modficar-usuario.png");
        Icon icono_reg = new ImageIcon(image_reg.getImage().getScaledInstance(btn_registrar_paciente.getWidth(),btn_registrar_paciente.getHeight(), Image.SCALE_DEFAULT));
        btn_modificar_paciente.setIcon(icono_reg);
        btn_modificar_paciente.addActionListener(this);
        add(btn_modificar_paciente);

        lnombre_paciente = new JLabel("Eliminar paciente");
        lnombre_paciente.setForeground(Color.white);
        lnombre_paciente.setFont(new Font("Arial",3,16));
        lnombre_paciente.setBounds(425,220,140,30);
        add(lnombre_paciente);

        btn_eliminar_paciente = new JButton();
        btn_eliminar_paciente.setBounds(440,100,100,100);
        ImageIcon image_el = new ImageIcon("src/Images/eliminar-usuario.png");
        Icon icono_el = new ImageIcon(image_el.getImage().getScaledInstance(btn_registrar_paciente.getWidth(),btn_registrar_paciente.getHeight(), Image.SCALE_DEFAULT));
        btn_eliminar_paciente.setIcon(icono_el);
        btn_eliminar_paciente.addActionListener(this);
        add(btn_eliminar_paciente);

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
            LOG.debug("DEBUG:: conectando base de datos");

            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select nombre_usuario from usuarios where username = '" + user + "'");
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                nombre_usuario = rs.getString("nombre_usuario");
                lnombre_usuario.setText(nombre_usuario);
            }
        }catch (Exception e){
            LOG.debug("ERROR:: Error en la interfaz de administrador");
            System.err.println("Error en la interfaz de administrador");

        }

    }


    public void actionPerformed(ActionEvent event){
        if (event.getSource().equals(btn_registrar_paciente)){
            new VentanaIngresoPaciente().setVisible(true);
        }
        if (event.getSource().equals(btn_modificar_paciente)){
            new VentanaModificarPacientes().setVisible(true);
        }
        if (event.getSource().equals(btn_eliminar_paciente)){
            new VentanaEliminarPacienteAdm().setVisible(true);
        }
        if (event.getSource().equals(bnt_exit)){
            new Login().setVisible(true);
            this.dispose();
        }


    }
}
