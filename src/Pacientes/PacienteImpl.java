package Pacientes;

import BaseDatos.PacienteBD;

import javax.swing.*;


public class PacienteImpl extends Paciente{


    public PacienteImpl() {

    }

    @Override
    public Paciente crearPaciente(String nombre, String apellido, String dni, String edad, String fechaNac,
                                  String obraSocial, String telefono, String sexo, String motivoConsulta,
                                  String antecedentes, String mail) {

        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setDni(dni);
        this.setEdad(edad);
        this.setFechaNac(fechaNac);
        this.setTelefono(telefono);
        this.setSexo(sexo);
        this.setMail(mail);

        if (obraSocial.toUpperCase().equals("OSDE")){
            this.setObraSocial(ObraSocial.OSDE);
        }else if (obraSocial.toUpperCase().equals("DOSEM")){
            this.setObraSocial(ObraSocial.DOSEM);
        }else if (obraSocial.toUpperCase().equals("MEDIFE")){
            this.setObraSocial(ObraSocial.MEDIFE);
        }else if (obraSocial.toUpperCase().equals("SWISMEDICAL")){
            this.setObraSocial(ObraSocial.SWISMEDICAL);
        }else if (obraSocial.toUpperCase().equals("IOMA")){
            this.setObraSocial(ObraSocial.IOMA);
        }else {
            this.setObraSocial(ObraSocial.NONE);
        }
        this.setMotivoConsulta("none");
        this.setAntecedentes("none");
        PacienteBD pacienteBD = new PacienteBD();
        pacienteBD.agregarPacienteBD(this);
        return this;
    }

    public Paciente actualizarPaciente(String nombre, String apellido, String dni, String edad, String fechaNac,
                                  String obraSocial, String telefono, String sexo, String motivoConsulta,
                                  String antecedentes, String mail, int HC) {

        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setDni(dni);
        this.setEdad(edad);
        this.setFechaNac(fechaNac);
        this.setTelefono(telefono);
        this.setSexo(sexo);
        this.setMail(mail);
        this.setHC(HC);

        if (obraSocial.toUpperCase().equals("OSDE")){
            this.setObraSocial(ObraSocial.OSDE);
        }else if (obraSocial.toUpperCase().equals("DOSEM")){
            this.setObraSocial(ObraSocial.DOSEM);
        }else if (obraSocial.toUpperCase().equals("MEDIFE")){
            this.setObraSocial(ObraSocial.MEDIFE);
        }else if (obraSocial.toUpperCase().equals("SWISMEDICAL")){
            this.setObraSocial(ObraSocial.SWISMEDICAL);
        }else if (obraSocial.toUpperCase().equals("IOMA")){
            this.setObraSocial(ObraSocial.IOMA);
        }else {
            this.setObraSocial(ObraSocial.NONE);
        }
        this.setMotivoConsulta("none");
        this.setAntecedentes("none");
        PacienteBD pacienteBD = new PacienteBD();
        pacienteBD.actualizarPacienteBD(this);
        return this;
    }
    public void autoTriage(String tasP, String tadP, String frP, String fcP, String satP){
        int tas, tad, fr, fc, sat;
        int res = 0;
        tas = Integer.parseInt(tasP);
        tad = Integer.parseInt(tadP);
        fr = Integer.parseInt(frP);
        fc = Integer.parseInt(fcP);
        sat = Integer.parseInt(satP);
        if (tas >= 180 && tas <210 || tad >= 100){
            res += 1;
        }
        if (tas >=210){
            res+=2;
        }
        if (fc >= 120 && fc < 150){
            res += 1;
        }
        if (fc >= 150 && fc < 200){
            res += 2;
        }
        if (fc >=200){
            JOptionPane.showMessageDialog(null,"Categoria recomendada 1");
            return;
        }
        if (fr >= 24 && fr < 30){
            res+= 1;
        }
        if (fr >= 30){
            res +=2;
        }
        if (sat >= 89 && fr < 95){
            res+= 1;
        }
        if (sat >=85 && sat <89 ){
            res+= 2;
        }
        if (sat <85){
            JOptionPane.showMessageDialog(null,"Categoria recomendada 1");
            return;
        }
        if (res <4){
            JOptionPane.showMessageDialog(null,"Categoria recomendada 3");
        }

        if (res >=4 && res <8){
            JOptionPane.showMessageDialog(null,"Categoria recomendada 2");
        }

        if (res >8){
            JOptionPane.showMessageDialog(null,"Categoria recomendada 1");
        }
    }







    public Paciente buscarPaciente(String dni){
        PacienteBD pacienteBD = new PacienteBD();
        PacienteImpl paciente = new PacienteImpl();
        paciente = pacienteBD.buscarPacienteBD(dni);
        return paciente ;
    }

}
