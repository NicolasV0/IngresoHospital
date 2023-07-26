package Ventanas.Administrativo;

import BaseDatos.Conexion;
import Logs.Log;
import Pacientes.PacienteImpl;
import Ventanas.Login;
import Pacientes.Consulta;
import org.apache.log4j.Logger;

import java.util.Calendar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaIngresoPaciente extends JFrame implements ActionListener {

    private JLabel lregistro_paciente, lnombre_paciente, lapellido_paciente, ledad_paciente, ldni_paciente,
    lfecha_paciente, lobrasoc_paciente, lsexo_paciente, lmail,lfondo,ltelfono, lbuscar;
    private JTextField txnombre_paciente, txapellido_paciente, txedad_paciente, txdni_paciente,
            txfecha_paciente, txobrasoc_paciente, txsexo_paciente, txmail,txtelefono;

    private JButton btnCrear, btnCancelar,btnConsulta, btnBuscar;
    private static final Logger LOG = Log.getLogger(VentanaIngresoPaciente.class);


    String user;
    int hc;

    public VentanaIngresoPaciente(){
        user = Login.user;
        LOG.debug("DEBUG:: ventana ingreso paciente");


        setSize(600,400);
        setTitle("Ingreso de paciente - Sesion de "+user);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);



        lregistro_paciente = new JLabel("Registro de pacientes");
        lregistro_paciente.setBounds(220,10,250,30);
        lregistro_paciente.setFont(new Font("Arial",1,20));
        lregistro_paciente.setForeground(Color.white);
        add(lregistro_paciente);

        lnombre_paciente = new JLabel("Nombre");
        lnombre_paciente.setBounds(10,60,120,30);
        lnombre_paciente.setFont(new Font("Arial",1,12));
        lnombre_paciente.setForeground(Color.white);
        add(lnombre_paciente);

        txnombre_paciente = new JTextField();
        txnombre_paciente.setBounds(140,60,120,30);
        txnombre_paciente.setFont(new Font("Arial",1,12));
        txnombre_paciente.setBackground(Color.DARK_GRAY);
        txnombre_paciente.setForeground(Color.white);
        add(txnombre_paciente);


        lapellido_paciente = new JLabel("Apellido");
        lapellido_paciente.setBounds(10,100,120,30);
        lapellido_paciente.setFont(new Font("Arial",1,12));
        lapellido_paciente.setForeground(Color.white);
        add(lapellido_paciente);

        txapellido_paciente = new JTextField();
        txapellido_paciente.setBounds(140,100,120,30);
        txapellido_paciente.setFont(new Font("Arial",1,12));
        txapellido_paciente.setBackground(Color.DARK_GRAY);
        txapellido_paciente.setForeground(Color.white);
        add(txapellido_paciente);


        ldni_paciente = new JLabel("Dni");
        ldni_paciente.setBounds(10,140,120,30);
        ldni_paciente.setFont(new Font("Arial",1,12));
        ldni_paciente.setForeground(Color.white);
        add(ldni_paciente);

        txdni_paciente = new JTextField();
        txdni_paciente.setBounds(140,140,120,30);
        txdni_paciente.setFont(new Font("Arial",1,12));
        txdni_paciente.setBackground(Color.DARK_GRAY);
        txdni_paciente.setForeground(Color.white);
        add(txdni_paciente);


        lfecha_paciente = new JLabel("Fecha Nac.");
        lfecha_paciente.setBounds(10,180,120,30);
        lfecha_paciente.setFont(new Font("Arial",1,12));
        lfecha_paciente.setForeground(Color.white);
        add(lfecha_paciente);

        txfecha_paciente = new JTextField();
        txfecha_paciente.setBounds(140,180,120,30);
        txfecha_paciente.setFont(new Font("Arial",1,12));
        txfecha_paciente.setBackground(Color.DARK_GRAY);
        txfecha_paciente.setForeground(Color.white);
        add(txfecha_paciente);

        lbuscar = new JLabel("Ingrese DNI para buscar paciente");
        lbuscar.setBounds(10,220,250,30);
        lbuscar.setFont(new Font("Arial",1,12));
        lbuscar.setForeground(Color.white);
        add(lbuscar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(10,260,100,30);
        btnBuscar.setBackground(Color.DARK_GRAY);
        btnBuscar.setForeground(Color.white);
        btnBuscar.addActionListener(this);
        add(btnBuscar);


        ledad_paciente = new JLabel("Edad ");
        ledad_paciente.setBounds(300,60,120,30);
        ledad_paciente.setFont(new Font("Arial",1,12));
        ledad_paciente.setForeground(Color.white);
        add(ledad_paciente);

        txedad_paciente = new JTextField();
        txedad_paciente.setBounds(440,60,120,30);
        txedad_paciente.setFont(new Font("Arial",1,12));
        txedad_paciente.setBackground(Color.DARK_GRAY);
        txedad_paciente.setForeground(Color.white);
        add(txedad_paciente);


        lobrasoc_paciente = new JLabel("Obra Social ");
        lobrasoc_paciente.setBounds(300,100,120,30);
        lobrasoc_paciente.setFont(new Font("Arial",1,12));
        lobrasoc_paciente.setForeground(Color.white);
        add(lobrasoc_paciente);

        txobrasoc_paciente = new JTextField();
        txobrasoc_paciente.setBounds(440,100,120,30);
        txobrasoc_paciente.setFont(new Font("Arial",1,12));
        txobrasoc_paciente.setBackground(Color.DARK_GRAY);
        txobrasoc_paciente.setForeground(Color.white);
        add(txobrasoc_paciente);


        lmail = new JLabel("Mail ");
        lmail.setBounds(300,140,120,30);
        lmail.setFont(new Font("Arial",1,12));
        lmail.setForeground(Color.white);
        add(lmail);

        txmail = new JTextField();
        txmail.setBounds(440,140,120,30);
        txmail.setFont(new Font("Arial",1,12));
        txmail.setBackground(Color.DARK_GRAY);
        txmail.setForeground(Color.white);
        add(txmail);

        lsexo_paciente = new JLabel("Sexo ");
        lsexo_paciente.setBounds(300,180,120,30);
        lsexo_paciente.setFont(new Font("Arial",1,12));
        lsexo_paciente.setForeground(Color.white);
        add(lsexo_paciente);

        txsexo_paciente = new JTextField();
        txsexo_paciente.setBounds(440,180,120,30);
        txsexo_paciente.setFont(new Font("Arial",1,12));
        txsexo_paciente.setBackground(Color.DARK_GRAY);
        txsexo_paciente.setForeground(Color.white);
        add(txsexo_paciente);

        ltelfono = new JLabel("Telefono ");
        ltelfono.setBounds(300,220,120,30);
        ltelfono.setFont(new Font("Arial",1,12));
        ltelfono.setForeground(Color.white);
        add(ltelfono);

        txtelefono = new JTextField();
        txtelefono.setBounds(440,220,120,30);
        txtelefono.setFont(new Font("Arial",1,12));
        txtelefono.setBackground(Color.DARK_GRAY);
        txtelefono.setForeground(Color.white);
        add(txtelefono);

        btnConsulta = new JButton("Agregar consulta");
        btnConsulta.setBounds(150,300,130,30);
        btnConsulta.setBackground(Color.DARK_GRAY);
        btnConsulta.setForeground(Color.white);
        btnConsulta.addActionListener(this);
        add(btnConsulta);


        btnCrear = new JButton("Aceptar");
        btnCrear.setBounds(300,300,100,30);
        btnCrear.setBackground(Color.DARK_GRAY);
        btnCrear.setForeground(Color.white);
        btnCrear.addActionListener(this);
        add(btnCrear);

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

    public void actionPerformed(ActionEvent event) {

        int validacion = 0;
        String nombre_pac, apellido_pac, dni_pac, mail_pac, edad_pac, sexo_pac, obraSoc_pac, fecNac_pac, tel_pac;

        nombre_pac = txnombre_paciente.getText().trim();
        apellido_pac = txapellido_paciente.getText().trim();
        dni_pac = txdni_paciente.getText().trim();
        mail_pac = txmail.getText().trim();
        edad_pac = txedad_paciente.getText().trim();
        sexo_pac = txsexo_paciente.getText().trim();
        obraSoc_pac = txobrasoc_paciente.getText().trim();
        fecNac_pac = txfecha_paciente.getText().trim();
        tel_pac = txtelefono.getText().trim();

        if (event.getSource().equals(btnCrear)) {

            validacion = validarCampos(nombre_pac,apellido_pac,dni_pac,mail_pac,edad_pac,sexo_pac,obraSoc_pac
                    ,fecNac_pac,tel_pac);

            if (validacion == 0) {
                PacienteImpl nuevoPaciente = new PacienteImpl();
                nuevoPaciente.crearPaciente(nombre_pac, apellido_pac, dni_pac, edad_pac, fecNac_pac, obraSoc_pac,
                        tel_pac, sexo_pac, "none", "none", mail_pac);
                this.dispose();
            }else {
                LOG.debug("WARN:: complete todos los datos");

                txnombre_paciente.setBackground(Color.red);
                txapellido_paciente.setBackground(Color.red);
                JOptionPane.showMessageDialog(null,"Debe llenar todos los campos");
            }

        }
        if (event.getSource().equals(btnCancelar)) {
            this.dispose();
        }
        if (event.getSource().equals(btnBuscar)) {
            if (dni_pac.isEmpty()) {
                txdni_paciente.setBackground(Color.red);
                validacion++;
            }
            PacienteImpl nuevoPaciente = new PacienteImpl();
            nuevoPaciente = (PacienteImpl) nuevoPaciente.buscarPaciente(dni_pac);
            if (nuevoPaciente  != null) {
                txnombre_paciente.setText(nuevoPaciente.getNombre());
                txapellido_paciente.setText(nuevoPaciente.getApellido());
                txdni_paciente.setText(nuevoPaciente.getDni());
                txmail.setText(nuevoPaciente.getMail());
                txfecha_paciente.setText(nuevoPaciente.getFechaNac());
                txedad_paciente.setText(nuevoPaciente.getEdad());
                txsexo_paciente.setText(nuevoPaciente.getSexo());
                txtelefono.setText(nuevoPaciente.getTelefono());
                txobrasoc_paciente.setText(nuevoPaciente.getObraSocial().toString());
                hc = nuevoPaciente.getHC();
            }
        }
        if (event.getSource().equals(btnConsulta)) {
            String hora_ingreso, minutos_ingreso, dia_ingreso, mes_ingreso, año_ingreso;

            Calendar calendar = Calendar.getInstance();

            dia_ingreso = Integer.toString(calendar.get(Calendar.DATE));
            mes_ingreso = Integer.toString(calendar.get(Calendar.MONTH));
            año_ingreso = Integer.toString(calendar.get(Calendar.YEAR) + 1);
            hora_ingreso = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
            minutos_ingreso = Integer.toString(calendar.get(Calendar.MINUTE));
            validacion = validarCampos(nombre_pac,apellido_pac,dni_pac,mail_pac,edad_pac,sexo_pac,obraSoc_pac
                    ,fecNac_pac,tel_pac);
            if (validacion == 0) {
                Consulta nuevaConsulta = new Consulta();
                nuevaConsulta.crearNuevaConsulta(dni_pac,hc,hora_ingreso,mes_ingreso,año_ingreso
                        ,minutos_ingreso,dia_ingreso, nombre_pac,apellido_pac);
                this.dispose();
            }else {
                LOG.debug("WARN:: complete todos los datos");
                txnombre_paciente.setBackground(Color.red);
                txapellido_paciente.setBackground(Color.red);
                JOptionPane.showMessageDialog(null,"Debe llenar todos los campos");
            }

            }
        }

    public int validarCampos(String nombre_pac,String apellido_pac,String dni_pac,String mail_pac,
                             String edad_pac,String sexo_pac,String obraSoc_pac,String fecNac_pac,String tel_pac){
        int resultado=0;

        if (nombre_pac.isEmpty()) {
            txnombre_paciente.setBackground(Color.red);
            resultado++;
        }

        if (apellido_pac.isEmpty()) {
            txapellido_paciente.setBackground(Color.red);
            resultado++;
        }

        if (dni_pac.isEmpty()) {
            txdni_paciente.setBackground(Color.red);
            resultado++;
        }

        if (mail_pac.isEmpty()) {
            txmail.setBackground(Color.red);
            resultado++;
        }

        if (sexo_pac.isEmpty()) {
            txsexo_paciente.setBackground(Color.red);
            resultado++;
        }

        if (fecNac_pac.isEmpty()) {
            txfecha_paciente.setBackground(Color.red);
            resultado++;
        }

        if (obraSoc_pac.isEmpty()) {
            txobrasoc_paciente.setBackground(Color.red);
            resultado++;
        }

        if (edad_pac.isEmpty()) {
            txedad_paciente.setBackground(Color.red);
            resultado++;
        }
        if (tel_pac.isEmpty()) {
            txtelefono.setBackground(Color.red);
            resultado++;
        }
        return resultado;
    }

    public void limpiar(){
        txtelefono.setText("");
        txedad_paciente.setText("");
        txnombre_paciente.setText("");
        txdni_paciente.setText("");
        txmail.setText("");
        txapellido_paciente.setText("");
        txobrasoc_paciente.setText("");
        txfecha_paciente.setText("");
        txsexo_paciente.setText("");
    }
}
