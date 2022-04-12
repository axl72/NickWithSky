
package controladores;

import general.Sistema;
import general.LeerArchivoCSV;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Luigi
 */
public class ControladorFrmFile {
 LeerArchivoCSV leer=new LeerArchivoCSV();
 private Boolean Flag=false;
  public Boolean BuscarArchivo(){
   JFileChooser Buscadordearchivo = new JFileChooser();
   
int Selecionado = Buscadordearchivo.showSaveDialog(null);

if (Selecionado == JFileChooser.APPROVE_OPTION){
   File Archivo = Buscadordearchivo.getSelectedFile();
      Sistema.ruta=Archivo.getAbsolutePath().replace("\\", "\\\\");
        if(Sistema.ruta.indexOf(".csv")>0){
               leer.ruta(Sistema.ruta);
               Flag=true;}
       else{ Flag=false;}
  }

else if (Selecionado == JFileChooser.CANCEL_OPTION){ 
Flag=true; }
 return Flag;
  }
    
}
