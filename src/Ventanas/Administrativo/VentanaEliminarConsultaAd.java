package Ventanas.Administrativo;


import BaseDatos.Conexion;
import Logs.Log;
import Ventanas.Login;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaEliminarConsultaAd extends JFrame implements ActionListener {

    private JLabel lregistro_paciente, lnombre_paciente, lapellido_paciente, ledad_paciente, ldni_paciente,
            lfecha_paciente, lobrasoc_paciente, lsexo_paciente, lmail,lfondo,ltelfono,lultima_modificacion;
    private JTextField txnombre_paciente, txapellido_paciente, txedad_paciente, txdni_paciente,
            txfecha_paciente, txobrasoc_paciente, txsexo_paciente, txmail,txtelefono,txultima_modificacion;

    private JButton btnBorrar, btnCancelar;

    String user = "";
    String paciente_update = "";
    String paciente_dni_update = "";
    int hc;

    private static final Logger LOG = Log.getLogger(VentanaEliminarConsultaAd.class);





    public VentanaEliminarConsultaAd(){
        LOG.debug("DEBUG:: ventana eliminar consulta");

        user = Login.user;
        paciente_update = VentanaEliminarPacienteAdm.paciente_update;
        paciente_dni_update = VentanaEliminarPacienteAdm.paciente_dni;

        setSize(600,400);
        setTitle("Eliminar paciente "+paciente_update+" - Sesion de "+user);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);



        lregistro_paciente = new JLabel("Eliminar paciente "+ paciente_update);
        lregistro_paciente.setBounds(100,10,400,30);
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

        lultima_modificacion = new JLabel("Ultima modificacion");
        lultima_modificacion.setBounds(10,220,120,30);
        lultima_modificacion.setFont(new Font("Arial",1,12));
        lultima_modificacion.setForeground(Color.white);
        add(lultima_modificacion);

        txultima_modificacion = new JTextField();
        txultima_modificacion.setBounds(140,220,120,30);
        txultima_modificacion.setFont(new Font("Arial",1,12));
        txultima_modificacion.setBackground(Color.DARK_GRAY);
        txultima_modificacion.setForeground(Color.white);
        add(txultima_modificacion);


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

        btnBorrar = new JButton("Borrar");
        btnBorrar.setBounds(300,300,100,30);
        btnBorrar.setBackground(Color.DARK_GRAY);
        btnBorrar.setForeground(Color.white);
        btnBorrar.addActionListener(this);
        add(btnBorrar);

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

        try{
            LOG.debug("INFO:: conectando base de datos");

            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("" +
                    "select * from pacientes where dni_paciente = '" + paciente_dni_update + "'");
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                hc = rs.getInt("hc");
                String cadena = rs.getString("nombre_paciente");
                String[] fragemento = cadena.split(" ");
                txnombre_paciente.setText(fragemento[0]);
                txapellido_paciente.setText(fragemento[1]);
                txmail.setText(rs.getString("mail_paciente"));
                txedad_paciente.setText(rs.getString("edad_paciente"));
                txtelefono.setText(rs.getString("telefono_paciente"));
                txdni_paciente.setText(paciente_dni_update);
                txobrasoc_paciente.setText(rs.getString("obra_soc_paciente"));
                txsexo_paciente.setText(rs.getString("sexo_paciente"));
                txfecha_paciente.setText(rs.getString("fec_nac_paciente"));
                txultima_modificacion.setText(rs.getString("ultima_modificacion"));

            }
            cn.close();

        }catch (SQLException e){

            LOG.debug("ERROR:: conectando base de datos " + e);
            System.err.println("Error en cargar paciente " + e);
            JOptionPane.showMessageDialog(null, "Error al cargar paciente");

        }





    }

    public void actionPerformed(ActionEvent event){
        int validacion =0;
        String nombre_pac,apellido_pac, dni_pac, mail_pac, edad_pac,sexo_pac,obraSoc_pac,
                fecNac_pac, tel_pac, ultima_modificacion;
        if (event.getSource().equals(btnBorrar)){
            nombre_pac = txnombre_paciente.getText().trim();
            apellido_pac = txapellido_paciente.getText().trim();
            dni_pac = txdni_paciente.getText().trim();
            mail_pac = txmail.getText().trim();
            edad_pac = txedad_paciente.getText().trim();
            sexo_pac = txsexo_paciente.getText().trim();
            obraSoc_pac = txobrasoc_paciente.getText().trim();
            fecNac_pac = txfecha_paciente.getText().trim();
            tel_pac = txtelefono.getText().trim();
            ultima_modificacion = txultima_modificacion.getText().trim();


            if (nombre_pac.isEmpty()){
                txnombre_paciente.setBackground(Color.red);
                validacion++;
            }

            if (apellido_pac.isEmpty()){
                txapellido_paciente.setBackground(Color.red);
                validacion++;
            }

            if (dni_pac.isEmpty()){
                txdni_paciente.setBackground(Color.red);
                validacion++;
            }

            if (mail_pac.isEmpty()){
                txmail.setBackground(Color.red);
                validacion++;
            }

            if (sexo_pac.isEmpty()){
                txsexo_paciente.setBackground(Color.red);
                validacion++;
            }

            if (fecNac_pac.isEmpty()){
                txfecha_paciente.setBackground(Color.red);
                validacion++;
            }

            if (obraSoc_pac.isEmpty()){
                txobrasoc_paciente.setBackground(Color.red);
                validacion++;
            }

            if (edad_pac.isEmpty()){
                txedad_paciente.setBackground(Color.red);
                validacion++;
            }
            if (tel_pac.isEmpty()){
                txtelefono.setBackground(Color.red);
                validacion++;
            }
            if (ultima_modificacion.isEmpty()){
                txultima_modificacion.setBackground(Color.red);
                validacion++;
            }
            if (validacion==0){
                try{
                    Connection cn = Conexion.conectar();
                    PreparedStatement pst = cn.prepareStatement(
                            "delete from consulta where dni = ?");

                    pst.setString(1,dni_pac);
                    pst.executeUpdate();
                    limpiar();
                    JOptionPane.showMessageDialog(null,"Pacientes.Consulta eliminada");
                    }catch (SQLException e){
                    System.err.println("Error al actualizar informacion " + e);
                }

            }else{
                JOptionPane.showMessageDialog(null,"Debe llenar todos los campos");
            }



        }
        if (event.getSource().equals(btnCancelar)){
            this.dispose();
        }
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
        txultima_modificacion.setText("");
    }
}
