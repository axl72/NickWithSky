
package general;

import controladores.ControladorFrmSistema;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import modelo.Ingresante;
import vista.FrmSistema;

public class LeerArchivoCSV {
    private String h="";
    private char sexo;
    private String [] part;
    public static String [] nombreColumnas = new String [] {"N°", "Primer nombre", "Segundo nombre", "Apellido paterno", "Apellido materno", "Fecha nacimiento", "Correo", "Genero", "Departamento", "Provincia", "Distrito", "Direccion", "Telefono", "Facultad", "Escuela", "Año", "DNI"};
    public static String [][] data = new String[0][];
    public static String [] valores = new String [0];
  
    GenerarReporteTxT verificarreporte= new GenerarReporteTxT();
    private FrmSistema frmSistema;
    
    public void ruta(String F){ 
        try{
            BufferedReader reader = new BufferedReader(new FileReader(F));           
            String line =null;
            while ((line= reader.readLine())!=null){
                part=line.split(";");
                int totalpartes=part.length;
               
               
              
               Sistema.ingresantes.add(new Ingresante(part[12],part[13],part[0], part[1], part[2], part[3], part[4], part[5],'M' ,part[7] , part[8], part[9], part[10], part[11], part[14], part[15]));
               
               
               Sistema.numeroRegistro++;
            }
            
            verificarreporte.leerreportegeneral();
           
        }catch (IOException e) {
            
              System.out.println("Se produjo un error al leer el archivo .csv");
        }
        
    }
    
    public  void Leercsv(String path){
        try{
            //Creación de buffer
            BufferedReader reader = new BufferedReader(new FileReader(path));           
            String line =null;
            int i=0;
            
            while ((line= reader.readLine())!=null){
                String [] atributos = line.split(";");
                
                //Guarda la informacion
                String [][] aux = data;
                
                //Redimensiona la matriz
                data = new String [data.length+1][];
                String [] copiaValores = valores;
                valores = new String[valores.length+1];
                
                for(int j=0; j<aux.length; j++){
                    data[j] = aux[j];    
                }
                
                        
                data[data.length-1] = atributos;
                
                for(int k=0; k<copiaValores.length; k++)
                    valores[k] = copiaValores[k];
                
                valores[valores.length-1] = String.valueOf(i+1);    
                
                i++;
            }
            
      
            
          
            
            
            String [][] copiaData = data;
            data = new String[copiaData.length][copiaData[0].length+1];
            for(int j=0; j<data.length; j++){
                for(int k=0; k<data[0].length; k++){
                    if(k==0){
                        data[j][k] = valores[j];
                    }
                
                    else if(k>0){
                        data[j][k] = copiaData[j][k-1];
                    }
                    
                    
                }
            }
            
       
 
        }
        catch (IOException e) {
              System.out.println("Se produjo un error al leer el archivo .csv");
        }
        
    }
 
          
     
} 

