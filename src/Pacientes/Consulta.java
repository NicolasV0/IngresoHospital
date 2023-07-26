package Pacientes;

import BaseDatos.ConsultaBD;

public class Consulta {
    private int hc;
    private String nombre_paciente;
    private String dni;
    private int id_consulta;
    private String motivo_consulta;
    private String hora;
    private String fecha_consulta;
    private String estatus;
    private int prioridad;
    private String enfermeria;
    private String medicina;
    private String observaciones_enf;
    private String observaciones_med;
    private String epicrisis;
    private String antecedentes;
    private int tas;
    private int tad;
    private int fr;
    private int fc;
    private int sat;
    private float temperatura;


    public Consulta() {

    }

    public int getHc() {
        return hc;
    }

    public void setHc(int hc) {
        this.hc = hc;
    }

    public String getNombre_paciente() {
        return nombre_paciente;
    }

    public void setNombre_paciente(String nombre_paciente) {
        this.nombre_paciente = nombre_paciente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public String getMotivo_consulta() {
        return motivo_consulta;
    }

    public void setMotivo_consulta(String motivo_consulta) {
        this.motivo_consulta = motivo_consulta;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha_consulta() {
        return fecha_consulta;
    }

    public void setFecha_consulta(String fecha_consulta) {
        this.fecha_consulta = fecha_consulta;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getEnfermeria() {
        return enfermeria;
    }

    public void setEnfermeria(String enfermeria) {
        this.enfermeria = enfermeria;
    }

    public String getMedicina() {
        return medicina;
    }

    public void setMedicina(String medicina) {
        this.medicina = medicina;
    }

    public String getObservaciones_enf() {
        return observaciones_enf;
    }

    public void setObservaciones_enf(String observaciones_enf) {
        this.observaciones_enf = observaciones_enf;
    }

    public String getObservaciones_med() {
        return observaciones_med;
    }

    public void setObservaciones_med(String observaciones_med) {
        this.observaciones_med = observaciones_med;
    }

    public String getEpicrisis() {
        return epicrisis;
    }

    public void setEpicrisis(String epicrisis) {
        this.epicrisis = epicrisis;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public int getTas() {
        return tas;
    }

    public void setTas(int tas) {
        this.tas = tas;
    }

    public int getTad() {
        return tad;
    }

    public void setTad(int tad) {
        this.tad = tad;
    }

    public int getFr() {
        return fr;
    }

    public void setFr(int fr) {
        this.fr = fr;
    }

    public int getFc() {
        return fc;
    }

    public void setFc(int fc) {
        this.fc = fc;
    }

    public int getSat() {
        return sat;
    }

    public void setSat(int sat) {
        this.sat = sat;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public Consulta buscarConsulta(String nombre_paciente, String paciente_dni_update){
        ConsultaBD nuevabusq = new ConsultaBD();
        return nuevabusq.buscarConsBD(nombre_paciente,paciente_dni_update);


    }

    public void crearNuevaConsulta(String dni, int hc, String hora_ingreso,String mes_ingreso,String año_ingreso,
                                   String minutos_ingreso,String dia_ingreso,String nombre_pac,String apellido_pac){
        ConsultaBD consultaBD = new ConsultaBD();
        consultaBD.crearNuevaConsulta(dni, hc, hora_ingreso, mes_ingreso, año_ingreso,
         minutos_ingreso, dia_ingreso, nombre_pac,apellido_pac);
        return;

    }

    public void triaggeEnfermeria(String dni_pac,int hc,String nombre_apellido,String motivo_consulta,
                                  String prioridad,String observaciones_pac,String antecedentes,
                                  String tas,String tad,String sat_pac,String temperatura_pac,
                                  String fr_pac,String fc_pac){
        ConsultaBD consultaBD = new ConsultaBD();
        consultaBD.triageEnfermeriaBD(dni_pac, hc,nombre_apellido, motivo_consulta, prioridad,
                observaciones_pac, antecedentes, tas, tad, sat_pac, temperatura_pac,
                fr_pac, fc_pac);

    }

    public void consultaMed(int hc, String paciente_dni_update, String nombre_apellido,
                            String epicrisis, String observacion_med, String antecedentes){
        ConsultaBD consultaBD = new ConsultaBD();
        consultaBD.consultaMedicaBD(hc, paciente_dni_update, nombre_apellido,
                 epicrisis, observacion_med, antecedentes);

    }

    public void eliminarConsulta(String dni){
        ConsultaBD nuevaConsulta = new ConsultaBD();
        nuevaConsulta.eliminarConsultaBD(dni);

    }



}
