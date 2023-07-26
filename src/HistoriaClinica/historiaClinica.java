package HistoriaClinica;

import BaseDatos.historiaClinicaBD;
import Impresiones.ImprimirPDF;
import Pacientes.Consulta;
import com.itextpdf.text.Document;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class historiaClinica {
    private Consulta consulta;





    public historiaClinica() {
    }

    public void ingresarHistoriaClinica(int hc, String nombre, String dni, int id_consulta, String motivo_consulta,
                                        String fecha, String hora, String status, int prioridad, String enfermeria,
                                        String medicina, String observaciones_med, String observaciones_enf,
                                        String epicrisis, String antecedentes, String controles){
        historiaClinicaBD historiaClinica = new historiaClinicaBD();
        historiaClinica.ingresarHCBD (hc, nombre, dni, id_consulta, motivo_consulta,
                fecha, hora, status, prioridad, enfermeria, medicina,  observaciones_med,
                observaciones_enf, epicrisis, antecedentes, controles);

    }

    public void buscarHC(String dni){
        List<Consulta> listHC = new ArrayList<>();
        historiaClinicaBD historiaClinicaBD = new historiaClinicaBD();
        listHC = historiaClinicaBD.buscarConsBD(dni);
        if (listHC.isEmpty()){
            JOptionPane.showMessageDialog(null,"Paciente no encontrado");
            return;
        }
        ImprimirPDF imprimir = new ImprimirPDF();
        String nombre,fecha, motivo_consulta, enfermeria ,medicina, observaciones_med, controles, epicrisis;
        int hc;
        int data =1;
        Document documento = new Document();


        for (int i = 0; i< listHC.size();i++){
            nombre = listHC.get(i).getNombre_paciente();
            hc =  listHC.get(i).getHc();
            fecha =  listHC.get(i).getFecha_consulta();
            motivo_consulta =  listHC.get(i).getMotivo_consulta();
            enfermeria =  listHC.get(i).getEnfermeria();
            medicina =  listHC.get(i).getMedicina();
            observaciones_med =  listHC.get(i).getObservaciones_med();
            controles =  listHC.get(i).getObservaciones_enf();
            epicrisis =  listHC.get(i).getEpicrisis();
            imprimir.imprimirHC(nombre,hc,fecha,motivo_consulta,enfermeria,medicina,observaciones_med,controles,epicrisis,data,documento);
            data = 0;
        }
        documento.close();
        JOptionPane.showMessageDialog(null,"Historia clinica descargada");
        JOptionPane.showMessageDialog(null,"Escritorio/ HC");

    }

}
