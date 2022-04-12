package general;

import controladores.controladorbarra;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import vista.ProgressBar;
/**
 *
 * @author Luigi
 */
public class GeneradorTXT {
    
    
    
    public void generarTxt(String RutaTemp, ProgressBar vistabar){
    try {
                       
        FileWriter fichero=new FileWriter(RutaTemp);
                     
        fichero.write(Sistema.alumnos.toString());
        controladorbarra controladorb=new controladorbarra(vistabar);
        controladorb.iniciarbtxt(RutaTemp);
        
        fichero.write("\n    Fecha de generado  "+Sistema.fechaactual);
        fichero.close();
    }catch(Exception e) {
        System.out.println("Se produjo un problema al generar el archivo .txt");
    }
    
    }
      
    public void generarTxt(String RutaTemp,String texto,String titulo){
        try{
                       
            FileWriter fichero=new FileWriter(RutaTemp);
            fichero.write(titulo+": \n\n");            
            fichero.write(texto);
        

            fichero.write("\n    Fecha de generado  "+Sistema.fechaactual);
            fichero.write("\n    Ruta del archivo  "+RutaTemp);
            fichero.close();
            ABRIRREGISTRODEINGRESO(RutaTemp);
          
    }catch(Exception e) {
        System.out.println("Se produjo un problema al generar el archivo .txt");
    }
    
    }
    public void ABRIRREGISTRODEINGRESO(String RutaTemp){
        try{ 
            Desktop.getDesktop().open(new File(RutaTemp));
        }
        catch(IOException l){
            l.printStackTrace();
        }
    }
}
