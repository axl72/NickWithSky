package general;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class GenerarReporteTxT {

    String contenido;
    
       public void Generarreporte(){
         
        try {
            File arhivo = new File(Sistema.ruta.replace(".csv", " REPORTE GENERAL.txt"));
            FileWriter Registrogeneral = new FileWriter(arhivo);
            Registrogeneral.write("REPORTE GENERAL \n\n");
            Registrogeneral.write("Ultima Cantidad de ingresantes procesados:"+" "+ Sistema.NumeroContador+"\n\n");
            Registrogeneral.write("Numero de Reportes en Excel generados:"+" "+Sistema.NumeroReporteExcel+"\n\n");
            Registrogeneral.write("Numero de Reportes en .txt generados:"+" "+Sistema.NumeroReporteTXT+"\n\n");
            Registrogeneral.write("Ultima cantidad de alumnos registrados:"+" "+Sistema.numeroRegistro+"\n\n");
            Registrogeneral.write("Ultima cantidad de facultades procesadas:"+" "+Sistema.facultades.getCantidadFacultadesUsadas()+"\n\n");
            Registrogeneral.write("Cantidad de errores del ultimo proceso:"+" "+Sistema.cantidadErrores+"  alumnos");
            Registrogeneral.close();
           
        } catch (IOException e) {
            System.out.println("Se ha producido un error al leer el número de reporte: "+e);
        } 
    }
           
           public void leerreportegeneral(){    
        try {
            BufferedReader leertexto = new BufferedReader(new FileReader(Sistema.ruta.replace(".csv", " REPORTE GENERAL.txt")));
             leertexto.skip(18);
             while((contenido=leertexto.readLine())!=null){

                System.out.println(contenido);
               if(contenido.indexOf("ingresantes")>0){
               Sistema.NumeroContador=Obtenernumero(contenido);
               leertexto.skip(1);}
               
                else if(contenido.indexOf("Excel")>0){
                Sistema.NumeroReporteExcel=Obtenernumero(contenido);
                leertexto.skip(1);}
                else if (contenido.indexOf(".txt")>0){  
                Sistema.NumeroReporteTXT=Obtenernumero(contenido);
                leertexto.skip(1);  }
             }
            leertexto.close(); 
        }catch (Exception e) {
            System.out.println("No se encontró el Archivo de Reportes txt: "+e+"\n Se va a generar un archivo nuevo...");
            Sistema.NumeroContador = 0;
            Sistema.NumeroReporteExcel=1;
            Sistema.NumeroReporteTXT=1; 
              }
  }
          
     public  int Obtenernumero(String cadena){
           char[] cadenaenarray=cadena.toCharArray();
           String cadenacumulada="";
           int numero=0;
           for(int i=0;i<cadenaenarray.length;i++){
           if(Character.isDigit(cadenaenarray[i])){cadenacumulada+=cadenaenarray[i];}
           }
           if(!cadenacumulada.isEmpty()){
           numero=Integer.parseInt(cadenacumulada);}
           return numero;
           }
     
     
     
          public  void abrirreportegeneral() {
      String file = new String( Sistema.ruta.replace(".csv", " REPORTE GENERAL.txt")); 
     try{ 
     Runtime.getRuntime().exec(new String[] { "notepad.exe", file });  
   }catch(IOException w){
      w.printStackTrace();
   } 
  }
}
