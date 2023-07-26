package Ventanas.Enfermeria;

import Pacientes.Consulta;
import Pacientes.PacienteImpl;
import Ventanas.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VentanaTriageEnfer extends JFrame implements ActionListener {

    private JLabel ltitle, lnombre_paciente, lapellido_paciente, lantecedentes, ldni_paciente,
            lmotivo_consulta, ltension_arterial, lfr, lfc,lfondo, ltemperatura, lobservacion_enf,lsaturacion;
    private JTextField txnombre_paciente, txapellido_paciente, txdni_paciente,
            txmotivo_consulta, txtension_arterial, txfr, txfc, txtemperatura, txsaturacion;
    private JScrollPane pane_observacion_enf, pane_antecedentes;
    private JTextArea  txobservacion_enf,txantecedentes;
    private JComboBox combo_prioridad;

    private JButton btnAceptar, btnCancelar;

    String user = "";
    String paciente_update = "";
    String paciente_dni_update = "";
    String nombre_apellido = "";
    //String id_consulta="";
    int hc;




    public VentanaTriageEnfer(){
        user = Login.user;
        nombre_apellido = Login.nombre_apellido;
        paciente_update = VentanaAtenderPacEnf.paciente_update;
        paciente_dni_update = VentanaAtenderPacEnf.paciente_dni;
        //id_consulta = VentanaAtenderPacEnf.id_consulta;

        setSize(600,500);
        setTitle("Triagge Enfermeria - Sesion de "+nombre_apellido);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);



        ltitle = new JLabel("Triagge Enfermeria "+ paciente_update);
        ltitle.setBounds(100,10,400,30);
        ltitle.setFont(new Font("Arial",1,20));
        ltitle.setForeground(Color.white);
        add(ltitle);

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


        lmotivo_consulta = new JLabel("Motivo consulta");
        lmotivo_consulta.setBounds(10,180,120,30);
        lmotivo_consulta.setFont(new Font("Arial",1,12));
        lmotivo_consulta.setForeground(Color.white);
        add(lmotivo_consulta);

        txmotivo_consulta = new JTextField();
        txmotivo_consulta.setBounds(140,180,120,30);
        txmotivo_consulta.setFont(new Font("Arial",1,12));
        txmotivo_consulta.setBackground(Color.DARK_GRAY);
        txmotivo_consulta.setForeground(Color.white);
        add(txmotivo_consulta);

        lobservacion_enf = new JLabel("Observaciones");
        lobservacion_enf.setBounds(10,220,120,30);
        lobservacion_enf.setFont(new Font("Arial",1,12));
        lobservacion_enf.setForeground(Color.white);
        add(lobservacion_enf);

        txobservacion_enf = new JTextArea();
        pane_observacion_enf = new JScrollPane(txobservacion_enf);
        pane_observacion_enf.setBounds(10,260,200,80);
        pane_observacion_enf.setFont(new Font("Arial",1,12));
        pane_observacion_enf.setBackground(Color.DARK_GRAY);
        pane_observacion_enf.setForeground(Color.white);
        add(pane_observacion_enf);



        ltension_arterial = new JLabel("Tension arterial ");
        ltension_arterial.setBounds(300,100,120,30);
        ltension_arterial.setFont(new Font("Arial",1,12));
        ltension_arterial.setForeground(Color.white);
        add(ltension_arterial);

        txtension_arterial = new JTextField();
        txtension_arterial.setBounds(440,100,120,30);
        txtension_arterial.setFont(new Font("Arial",1,12));
        txtension_arterial.setBackground(Color.DARK_GRAY);
        txtension_arterial.setForeground(Color.white);
        add(txtension_arterial);


        lfc = new JLabel("FC ");
        lfc.setBounds(300,140,120,30);
        lfc.setFont(new Font("Arial",1,12));
        lfc.setForeground(Color.white);
        add(lfc);

        txfc = new JTextField();
        txfc.setBounds(440,140,120,30);
        txfc.setFont(new Font("Arial",1,12));
        txfc.setBackground(Color.DARK_GRAY);
        txfc.setForeground(Color.white);
        add(txfc);

        lfr = new JLabel("FR ");
        lfr.setBounds(300,180,120,30);
        lfr.setFont(new Font("Arial",1,12));
        lfr.setForeground(Color.white);
        add(lfr);

        txfr = new JTextField();
        txfr.setBounds(440,180,120,30);
        txfr.setFont(new Font("Arial",1,12));
        txfr.setBackground(Color.DARK_GRAY);
        txfr.setForeground(Color.white);
        add(txfr);

        ltemperatura = new JLabel("Temperatura ");
        ltemperatura.setBounds(300,220,120,30);
        ltemperatura.setFont(new Font("Arial",1,12));
        ltemperatura.setForeground(Color.white);
        add(ltemperatura);

        txtemperatura = new JTextField();
        txtemperatura.setBounds(440,220,120,30);
        txtemperatura.setFont(new Font("Arial",1,12));
        txtemperatura.setBackground(Color.DARK_GRAY);
        txtemperatura.setForeground(Color.white);
        add(txtemperatura);


        lsaturacion = new JLabel("Sat. ");
        lsaturacion.setBounds(300,60,120,30);
        lsaturacion.setFont(new Font("Arial",1,12));
        lsaturacion.setForeground(Color.white);
        add(lsaturacion);

        txsaturacion = new JTextField();
        txsaturacion.setBounds(440,60,120,30);
        txsaturacion.setFont(new Font("Arial",1,12));
        txsaturacion.setBackground(Color.DARK_GRAY);
        txsaturacion.setForeground(Color.white);
        add(txsaturacion);

        lantecedentes = new JLabel("Antecedentes");
        lantecedentes.setBounds(300,260,120,30);
        lantecedentes.setFont(new Font("Arial",1,12));
        lantecedentes.setForeground(Color.white);
        add(lantecedentes);

        txantecedentes = new JTextArea();
        pane_antecedentes = new JScrollPane(txantecedentes);
        pane_antecedentes.setBounds(300,300,200,80);
        pane_antecedentes.setFont(new Font("Arial",1,12));
        pane_antecedentes.setBackground(Color.DARK_GRAY);
        pane_antecedentes.setForeground(Color.white);
        add(pane_antecedentes);

        combo_prioridad = new JComboBox<>();
        combo_prioridad.addItem("");
        combo_prioridad.addItem("1");
        combo_prioridad.addItem("2");
        combo_prioridad.addItem("3");
        combo_prioridad.setBounds(150,400,120,30);
        combo_prioridad.setBackground(Color.DARK_GRAY);
        combo_prioridad.setForeground(Color.white);
        add(combo_prioridad);

        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(300,400,100,30);
        btnAceptar.setBackground(Color.DARK_GRAY);
        btnAceptar.setForeground(Color.white);
        btnAceptar.addActionListener(this);
        add(btnAceptar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(440,400,100,30);
        btnCancelar.setBackground(Color.red);
        btnCancelar.setForeground(Color.white);
        btnCancelar.addActionListener(this);
        add(btnCancelar);


        lfondo = new JLabel();
        lfondo.setBounds(0,0,600,500);
        add(lfondo);

        ImageIcon imagefondo = new ImageIcon("src/Images/wallpaperAD.jpg");
        Icon iconofondo = new ImageIcon(imagefondo.getImage().getScaledInstance(lfondo.getWidth(),lfondo.getHeight(), Image.SCALE_DEFAULT));
        lfondo.setIcon(iconofondo);
        this.repaint();

       Consulta consultaBusq = new Consulta();
       consultaBusq = consultaBusq.buscarConsulta(paciente_update, paciente_dni_update);
        if (consultaBusq == null) {
            JOptionPane.showMessageDialog(null,"Paciente no encontrado");
        }
        String[] cadena = paciente_update.split(" ");
        txnombre_paciente.setText(cadena[0]);
        txapellido_paciente.setText(cadena[1]);
        txdni_paciente.setText(paciente_dni_update);
        txnombre_paciente.setEnabled(false);
        txapellido_paciente.setEnabled(false);
        txdni_paciente.setEnabled(false);
        hc = consultaBusq.getHc();



    }

    public void actionPerformed(ActionEvent event){
        int validacion =0;
        String nombre_pac,apellido_pac, dni_pac, fc_pac, observaciones_pac,fr_pac,tension_pac,
                motivo_consulta, temperatura_pac, sat_pac, prioridad,antecedentes;
        String tas ="";
        String tad = "";
        if (event.getSource().equals(btnAceptar)){
            nombre_pac = txnombre_paciente.getText().trim();
            apellido_pac = txapellido_paciente.getText().trim();
            dni_pac = txdni_paciente.getText().trim();
            fc_pac = txfc.getText().trim();
            fr_pac = txfr.getText().trim();
            tension_pac = txtension_arterial.getText().trim();
            motivo_consulta = txmotivo_consulta.getText().trim();
            temperatura_pac = txtemperatura.getText().trim();
            observaciones_pac = txobservacion_enf.getText();
            antecedentes = txantecedentes.getText();
            sat_pac = txsaturacion.getText().trim();
            prioridad = combo_prioridad.getSelectedItem().toString();
            String[] fragmento = tension_pac.split("/");
            if (fragmento.length >1){
                tas = fragmento[0];
                tad = fragmento[1];
            }


            validacion = validarCampos(nombre_pac,apellido_pac, dni_pac, fc_pac, observaciones_pac,fr_pac,tension_pac,
                    motivo_consulta, temperatura_pac, sat_pac, prioridad,antecedentes);

            if (validacion==0){
                PacienteImpl paciente = new PacienteImpl();
                paciente.autoTriage(tas,tad,fr_pac,fc_pac,sat_pac);
                Consulta nuevaConsulta = new Consulta();
                nuevaConsulta.triaggeEnfermeria(dni_pac, hc,nombre_apellido, motivo_consulta, prioridad,
                        observaciones_pac, antecedentes, tas, tad, sat_pac, temperatura_pac,
                        fr_pac, fc_pac);
                this.dispose();

            }else{
                JOptionPane.showMessageDialog(null,"Debe llenar todos los campos");
            }



        }
        if (event.getSource().equals(btnCancelar)){
            this.dispose();
        }
    }

    public int validarCampos(String nombre_pac,String apellido_pac,String dni_pac,String fc_pac,
                             String observaciones_pac,String fr_pac, String tension_pac,
                             String motivo_consulta, String temperatura_pac,String sat_pac,
                             String prioridad, String antecedentes){
        int validacion = 0;
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

        if (fc_pac.isEmpty()){
            txfc.setBackground(Color.red);
            validacion++;
        }

        if (fr_pac.isEmpty()){
            txfr.setBackground(Color.red);
            validacion++;
        }

        if (motivo_consulta.isEmpty()){
            txmotivo_consulta.setBackground(Color.red);
            validacion++;
        }

        if (tension_pac.isEmpty()){
            txtension_arterial.setBackground(Color.red);
            validacion++;
        }

        if (observaciones_pac.isEmpty()){
            txantecedentes.setBackground(Color.red);
            validacion++;
        }
        if (temperatura_pac.isEmpty()){
            txtemperatura.setBackground(Color.red);
            validacion++;
        }

        if (prioridad.isEmpty()){
            combo_prioridad.setBackground(Color.red);
            validacion++;
        }
        if (sat_pac.isEmpty()){
            txsaturacion.setBackground(Color.red);
            validacion++;
        }
        if (antecedentes.isEmpty()){
            txantecedentes.setBackground(Color.red);
            validacion++;
        }
        return validacion;
    }

    public void limpiar(){
        txtemperatura.setText("");
        txantecedentes.setText("");
        txnombre_paciente.setText("");
        txdni_paciente.setText("");
        txfc.setText("");
        txapellido_paciente.setText("");
        txtension_arterial.setText("");
        txmotivo_consulta.setText("");
        txfr.setText("");
        txobservacion_enf.setText("");
    }
}
