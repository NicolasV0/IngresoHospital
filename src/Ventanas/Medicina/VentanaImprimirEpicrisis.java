package Ventanas.Medicina;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import HistoriaClinica.historiaClinica;
import Impresiones.ImprimirPDF;
import Pacientes.Consulta;
import Ventanas.Login;


public class VentanaImprimirEpicrisis extends JFrame implements ActionListener {

    private JLabel ltitle, lobservacion_med, lepicrisis, lantecedentes, lmotivo_consulta, ltension_arterial,
            lfr, lfc,lfondo, ltemperatura, lobservacion_enf,lsaturacion,lprioridad;
    private JTextField  txmotivo_consulta, txtension_arterial, txfr, txfc, txtemperatura, txsaturacion,txprioridad;
    private JScrollPane pane_observacion_enf, pane_antecedentes, pane_observacion_med, pane_epicrisis;
    private JTextArea  txobservacion_enf,txantecedentes,txobservacion_med, txepicrisis;

    private JButton btnRegistrar, btnCancelar;

    String user = "";
    String paciente_update = "";
    String paciente_dni_update = "";
    int hc;
    int id_consulta;
    int prioridad;
    String fecha = "";
    String enfermeria = "";
    String medicina = "";
    String status ="";
    String controles="";
    String observaciones_enf = "";
    String hora = "";




    public VentanaImprimirEpicrisis(){
        user = Login.user;
        paciente_update = VentanaRegistrarConsultaMed.paciente_update;
        paciente_dni_update = VentanaRegistrarConsultaMed.paciente_dni;

        setSize(600,700);
        setTitle("Consulta medica - Sesion de "+user);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);



        ltitle = new JLabel("Consulta de "+ paciente_update);
        ltitle.setBounds(100,10,400,30);
        ltitle.setFont(new Font("Arial",1,20));
        ltitle.setForeground(Color.white);
        add(ltitle);



        lmotivo_consulta = new JLabel("Motivo consulta");
        lmotivo_consulta.setBounds(10,60,120,30);
        lmotivo_consulta.setFont(new Font("Arial",1,12));
        lmotivo_consulta.setForeground(Color.white);
        add(lmotivo_consulta);

        txmotivo_consulta = new JTextField();
        txmotivo_consulta.setBounds(10,100,120,30);
        txmotivo_consulta.setFont(new Font("Arial",1,12));
        txmotivo_consulta.setBackground(Color.DARK_GRAY);
        txmotivo_consulta.setForeground(Color.white);
        add(txmotivo_consulta);

        lobservacion_enf = new JLabel("Observaciones Enf.");
        lobservacion_enf.setBounds(10,140,120,30);
        lobservacion_enf.setFont(new Font("Arial",1,12));
        lobservacion_enf.setForeground(Color.white);
        add(lobservacion_enf);

        txobservacion_enf = new JTextArea();
        pane_observacion_enf = new JScrollPane(txobservacion_enf);
        pane_observacion_enf.setBounds(10,180,200,80);
        pane_observacion_enf.setFont(new Font("Arial",1,12));
        pane_observacion_enf.setBackground(Color.DARK_GRAY);
        pane_observacion_enf.setForeground(Color.white);
        add(pane_observacion_enf);

        lobservacion_med = new JLabel("Observaciones");
        lobservacion_med.setBounds(10,270,120,30);
        lobservacion_med.setFont(new Font("Arial",1,12));
        lobservacion_med.setForeground(Color.white);
        add(lobservacion_med);

        txobservacion_med = new JTextArea();
        pane_observacion_med = new JScrollPane(txobservacion_med);
        pane_observacion_med.setBounds(10,310,200,80);
        pane_observacion_med.setFont(new Font("Arial",1,12));
        pane_observacion_med.setBackground(Color.DARK_GRAY);
        pane_observacion_med.setForeground(Color.white);
        add(pane_observacion_med);

        lepicrisis = new JLabel("Epicrisis");
        lepicrisis.setBounds(10,400,120,30);
        lepicrisis.setFont(new Font("Arial",1,12));
        lepicrisis.setForeground(Color.white);
        add(lepicrisis);

        txepicrisis = new JTextArea();
        pane_epicrisis = new JScrollPane(txepicrisis);
        pane_epicrisis.setBounds(10,440,550,80);
        pane_epicrisis.setFont(new Font("Arial",1,12));
        pane_epicrisis.setBackground(Color.DARK_GRAY);
        pane_epicrisis.setForeground(Color.white);
        add(pane_epicrisis);


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
        pane_antecedentes.setBounds(300,300,260,80);
        pane_antecedentes.setFont(new Font("Arial",1,12));
        pane_antecedentes.setBackground(Color.DARK_GRAY);
        pane_antecedentes.setForeground(Color.white);
        add(pane_antecedentes);



        lprioridad = new JLabel("Prioridad");
        lprioridad.setBounds(10,590,120,30);
        lprioridad.setFont(new Font("Arial",1,12));
        lprioridad.setForeground(Color.white);
        add(lprioridad);

        txprioridad = new JTextField();
        txprioridad.setBounds(10,620,120,30);
        txprioridad.setFont(new Font("Arial",1,12));
        txprioridad.setBackground(Color.DARK_GRAY);
        txprioridad.setForeground(Color.white);
        add(txprioridad);


        btnRegistrar = new JButton("Aceptar");
        btnRegistrar.setBounds(300,620,100,30);
        btnRegistrar.setBackground(Color.DARK_GRAY);
        btnRegistrar.setForeground(Color.white);
        btnRegistrar.addActionListener(this);
        add(btnRegistrar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(440,620,100,30);
        btnCancelar.setBackground(Color.red);
        btnCancelar.setForeground(Color.white);
        btnCancelar.addActionListener(this);
        add(btnCancelar);


        lfondo = new JLabel();
        lfondo.setBounds(0,0,600,700);
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

        hc = consultaBusq.getHc();
        txmotivo_consulta.setText(consultaBusq.getMotivo_consulta());
        txmotivo_consulta.setEnabled(false);
        txfc.setText(Integer.toString(consultaBusq.getFc()));
        txfc.setEnabled(false);
        txfr.setText((Integer.toString(consultaBusq.getFr())));
        txfr.setEnabled(false);
        txsaturacion.setText((Integer.toString(consultaBusq.getSat())));
        txsaturacion.setEnabled(false);
        txtemperatura.setText((Float.toString(consultaBusq.getTemperatura())));
        txtemperatura.setEnabled(false);
        txtension_arterial.setText(consultaBusq.getTas()+"/"+consultaBusq.getTad());
        txtension_arterial.setEnabled(false);
        txobservacion_enf.setText(consultaBusq.getObservaciones_enf());
        txobservacion_enf.setEnabled(false);
        txprioridad.setText(Integer.toString(consultaBusq.getPrioridad()));
        txprioridad.setEnabled(false);
        txantecedentes.setText(consultaBusq.getAntecedentes());
        txepicrisis.setText(consultaBusq.getEpicrisis());
        txobservacion_med.setText(consultaBusq.getObservaciones_med());
        fecha = consultaBusq.getFecha_consulta();
        enfermeria = consultaBusq.getEnfermeria();
        medicina =  consultaBusq.getMedicina();
        status = consultaBusq.getEstatus();
        hora = consultaBusq.getHora();
        observaciones_enf = consultaBusq.getObservaciones_enf();
        id_consulta = consultaBusq.getId_consulta();
        controles = "Tension arterial: "+ consultaBusq.getTas()+"/" +consultaBusq.getTad()
                +" Saturacion: " +consultaBusq.getSat()+" Temperatura: " +consultaBusq.getTemperatura()
                +" FC: " +consultaBusq.getFc()+" FR: " +consultaBusq.getFr();
        prioridad = consultaBusq.getPrioridad();


        
    }

    public void actionPerformed(ActionEvent event){
        if (event.getSource().equals(btnRegistrar)){
            int validacion =0;
            String observacion_med, antecedentes, epicrisis;
            if (event.getSource().equals(btnRegistrar)) {
                observacion_med = txobservacion_med.getText();
                antecedentes = txantecedentes.getText();
                epicrisis = txepicrisis.getText();


                if (observacion_med.isEmpty()) {
                    txobservacion_med.setBackground(Color.red);
                    validacion++;
                }

                if (epicrisis.isEmpty()) {
                    txepicrisis.setBackground(Color.red);
                    validacion++;
                }

                if (antecedentes.isEmpty()) {
                    txantecedentes.setBackground(Color.red);
                    validacion++;
                }

            }
            String motivo_consulta, observaciones,tas, tad, saturacion, fr, fc, temperatura;

            motivo_consulta = txmotivo_consulta.getText();
            observaciones = txobservacion_med.getText();
            antecedentes = txantecedentes.getText();;
            epicrisis = txepicrisis.getText();
            String cadena = txtension_arterial.getText();
            String [] fragemento = cadena.split("/");
            tas =fragemento[0];
            tad = fragemento[1];
            saturacion = txsaturacion.getText();
            temperatura = txtemperatura.getText();
            fr = txfr.getText();
            fc = txfc.getText();


            ImprimirPDF nuevaImpresion = new ImprimirPDF();
            nuevaImpresion.imprimirEpicrisis(motivo_consulta, observaciones, tas,  tad,
                    saturacion,  fr,  fc, temperatura, paciente_update, hc, antecedentes,  epicrisis,
                    fecha, enfermeria, medicina);

            Consulta consulta = new Consulta();
            consulta.eliminarConsulta(paciente_dni_update);
            historiaClinica nuevaHC = new historiaClinica();
            nuevaHC.ingresarHistoriaClinica(hc, paciente_update,paciente_dni_update, id_consulta, motivo_consulta,
                    fecha, hora, status, prioridad, enfermeria, medicina,  observaciones,
                    observaciones_enf, epicrisis, antecedentes, controles);

            this.dispose();


        }
        if (event.getSource().equals(btnCancelar)){
            this.dispose();
        }
    }

    public void limpiar(){
        txtemperatura.setText("");
        txantecedentes.setText("");
        txobservacion_med.setText("");
        txfc.setText("");
        txtension_arterial.setText("");
        txmotivo_consulta.setText("");
        txfr.setText("");
        txobservacion_enf.setText("");
    }
}