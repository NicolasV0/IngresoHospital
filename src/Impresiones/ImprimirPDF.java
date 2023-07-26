package Impresiones;

import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import javax.swing.*;
import java.io.FileOutputStream;
import java.sql.SQLException;

public class ImprimirPDF {
    public ImprimirPDF() {
    }


    public void imprimirEpicrisis(String motivo_consulta, String observaciones,
                                  String tas, String tad, String saturacion, String fr, String fc,
                                  String temperatura, String paciente_update, int hc,
                                  String antecedentes, String epicrisis, String fecha,
                                   String enfermeria, String medicina){

        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Epicrisis/" + paciente_update + ".pdf"));

            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/Images/bannerPDF.png");
            header.scaleToFit(650,1000);
            header.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Historia Clinica: " + hc +"\n \n");
            parrafo.setFont(FontFactory.getFont("Tahoma",14, Font.BOLD,BaseColor.DARK_GRAY));


            documento.open();
            documento.add(header);
            documento.add(parrafo);

            Paragraph parrafo2 = new Paragraph();
            parrafo2.setAlignment(Paragraph.ALIGN_LEFT);
            parrafo2.add("Nombre Apellido: " + paciente_update +"\n"+
                    "Fecha de consulta: "+ fecha+"\n"+
                    "Motivo de consulta: "+ motivo_consulta+"\n"+
                    "Atendido por profesional: "+ enfermeria+"\n"+
                    "Medico que otorga alta: "+ medicina+"\n"+
                    "Observaciones iniciales: "+ observaciones+"\n"+
                    "Controles al ingreso: "+"\n"+
                    " -Tension Arterial: "+ tas+"/"+tad+"\n"+
                    " -Frecuencia Cardiaca: "+ fc+"\n"+
                    " -Frecuencia Respiratoria: "+ fr+"\n"+
                    " -Temperatura: "+ temperatura+"\n"+
                    " -Saturacion: "+ saturacion+"\n"+
                    "Epicrisis: "+"\n"+
                    " "+epicrisis+".\n");
            parrafo2.setFont(FontFactory.getFont("Tahoma",14,Font.BOLD,BaseColor.DARK_GRAY));
            documento.add(parrafo2);

        }catch (Exception e){
            System.err.println("Error al cargar paciente" + e);
        }
        JOptionPane.showMessageDialog(null,"Registro correcto");
        documento.close();
    }

    public void imprimirHC(String nombre, int hc, String fecha, String motivo_consulta,
                           String enfermeria, String medicina, String observaciones_med,
                           String controles, String epicrisis, int data, Document documento){

        if (data >0){
            try {

                String ruta = System.getProperty("user.home");
                PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/HC/" + nombre + ".pdf"));

                com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/Images/logo3.png");
                header.scaleToFit(650,1000);
                header.setAlignment(Chunk.ALIGN_CENTER);

                Paragraph parrafo = new Paragraph();
                parrafo.setAlignment(Paragraph.ALIGN_CENTER);
                parrafo.add("Historia Clinica: " + hc + "\n \n");
                parrafo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY));

                documento.open();
                documento.add(header);
                documento.add(parrafo);
            } catch (Exception e){
            System.err.println("Error al cargar paciente" + e);
        }
        }
        try {

            Paragraph parrafo2 = new Paragraph();
            parrafo2.setAlignment(Paragraph.ALIGN_LEFT);
            parrafo2.add("Nombre Apellido: " + nombre +"\n"+
                                    "Fecha de consulta: "+ fecha+"\n"+
                                    "Motivo de consulta: "+ motivo_consulta+"\n"+
                                    "Atendido por profesional: "+ enfermeria+"\n"+
                                    "Medico que otorga alta: "+ medicina+"\n"+
                                    "Observaciones iniciales: "+ observaciones_med+"\n"+
                                    controles+"\n"+
                                    "Epicrisis: "+"\n"+
                                    " "+epicrisis+".\n\n\n\n\n");
            parrafo2.setFont(FontFactory.getFont("Tahoma",14,Font.BOLD,BaseColor.DARK_GRAY));
            documento.open();
            documento.add(parrafo2);

        }catch (Exception e){
            System.err.println("Error al cargar paciente" + e);
        }
            //documento.close();

        }


}
