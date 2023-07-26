package Ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import BaseDatos.Conexion;
import Logs.Log;
import Ventanas.Administrativo.VentanaAdministrativo;
import Ventanas.Enfermeria.VentanaEnfermeria;
import Ventanas.Medicina.VentanaMedicina;
import Ventanas.Tecnico.VentanaTecnico;
import org.apache.log4j.Logger;


public class Login extends JFrame implements ActionListener {
    private JLabel lfondo,licono;
    private JTextField txusuario;
    private JPasswordField txcontraseña;
    private JButton btnAcceder;

    public static String user="";
    public static String nombre_apellido = "";
    String pass = "";


    private static final Logger LOG = Log.getLogger(Login.class);

    public Login(){
        LOG.debug("DEBUG:: iniciando Login");
        setLayout(null);

        setSize(400,550);
        setTitle("Login");
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        btnAcceder = new JButton("Acceder");
        btnAcceder.setBackground(Color.DARK_GRAY);
        btnAcceder.setForeground(Color.white);
        btnAcceder.setFont(new Font("Arial Narrow",3,18));
        btnAcceder.setBounds(95,420,210,35);
        btnAcceder.addActionListener(this);
        add(btnAcceder);


        txusuario = new JTextField();
        txusuario.setBounds(95,330,210,30);
        txusuario.setForeground(Color.white);
        txusuario.setBackground(Color.DARK_GRAY);
        txusuario.setFont(new Font("Arial",2,18));
        txusuario.setHorizontalAlignment(JLabel.CENTER);
        add(txusuario);

        txcontraseña = new JPasswordField();
        txcontraseña.setForeground(Color.white);
        txcontraseña.setBackground(Color.DARK_GRAY);
        txcontraseña.setFont(new Font("Arial",2,18));
        txcontraseña.setHorizontalAlignment(JPasswordField.CENTER);
        txcontraseña.setBounds(95,370,210,30);
        add(txcontraseña);



        licono = new JLabel();
        licono.setBounds(100,60,200,200);
        add(licono);

        lfondo = new JLabel();
        lfondo.setBounds(0,0,400,550);
        add(lfondo);

        LOG.debug("DEBUG:: cargando imagenes login");
        ImageIcon image = new ImageIcon("src/Images/fondo2.jpg");
        Icon icono = new ImageIcon(image.getImage().getScaledInstance(lfondo.getWidth(),lfondo.getHeight(), Image.SCALE_DEFAULT));
        lfondo.setIcon(icono);
        this.repaint();



        ImageIcon logo = new ImageIcon("src/Images/logo3.png");
        Icon icono_logo = new ImageIcon(logo.getImage().getScaledInstance(licono.getWidth(),licono.getHeight(),Image.SCALE_DEFAULT));
        licono.setIcon(icono_logo);
        this.repaint();

    }


    @Override
    public void actionPerformed(ActionEvent event) {
        user = txusuario.getText().trim();
        pass = txcontraseña.getText().trim();
        if (event.getSource().equals(btnAcceder)){
            if (!user.isEmpty() || !pass.isEmpty()){
                try {
                    LOG.debug("DEBUG:: conectando base de datos");
                    Connection cn = Conexion.conectar();
                    PreparedStatement pst = cn.prepareStatement(
                            "select tipo_nivel, nombre_usuario from usuarios where username = '" + user
                            + "' and password = '" + pass + "'");

                    ResultSet rs = pst.executeQuery();
                    if (rs.next()){
                        nombre_apellido = rs.getString("nombre_usuario");

                        String tipo_nivel = rs.getString("tipo_nivel");
                        if (tipo_nivel.equals("Administrativo")){
                            dispose();
                            new VentanaAdministrativo().setVisible(true);
                        }
                        else if (tipo_nivel.equals("Enfermeria")){
                            dispose();
                            new VentanaEnfermeria().setVisible(true);
                        }
                        else if (tipo_nivel.equals("Medicina")){
                            dispose();
                            new VentanaMedicina().setVisible(true);
                        }

                        else if (tipo_nivel.equals("Tecnico")){
                            dispose();
                            new VentanaTecnico().setVisible(true);
                        }



                    }else {
                        LOG.debug("INFO:: datos incorrectos");
                        JOptionPane.showMessageDialog(null,"Datos de acceso incorrectos");
                        txusuario.setText("");
                        txcontraseña.setText("");

                    }


                }catch (SQLException e){
                    LOG.debug("ERROR:: error al acceder a la base de datos");
                    System.err.println("Error en el boton acceder"+e);
                    JOptionPane.showMessageDialog(null,"Error al iniciar sesion! Contacte al adiminstrador");

                }


            }else {
                LOG.debug("warn:: complete campos");
                JOptionPane.showMessageDialog(null,"Debe llenar todos los campos");

            }
        }


    }
}
