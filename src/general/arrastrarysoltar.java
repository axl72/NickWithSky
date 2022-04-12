
package general;

import java.awt.Color;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;
import vista.FrmSistema;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import vista.FrmTable;
/**
 *
 * @author Luigi
 */

public class arrastrarysoltar {
        LeerArchivoCSV leer=new LeerArchivoCSV();
    public arrastrarysoltar(FrmSistema frame) {
    Color aceptado = new Color(113,168,120);


        frame.arrastartysoltar.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List<File>) evt
                           .getTransferable().getTransferData(
                                   DataFlavor.javaFileListFlavor);
                    for (File file : droppedFiles) {
                        frame.arrastartysoltar.setBackground(aceptado);
                        Sistema.ruta=file.getAbsolutePath().replace("\\", "\\\\");
                        if(Sistema.ruta.indexOf(".csv")>0){
                        System.out.println("Archivo leído desde: "+Sistema.ruta+"\n"); 
                        frame.arrastartysoltar.setText("");
                        frame.arrastartysoltar.setText("\tRuta: "+file.getAbsolutePath());
                            
                   
                 
                   leer.ruta(Sistema.ruta);
                    JOptionPane.showMessageDialog(null, textoennegritagood(), "Correcto", JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/Imagenes/123.gif")));
                    frame.btnCargar.setEnabled(false);
                    frame.bvalidar.setEnabled(true);
                    GenerartablaCSV();
                        }
                        else{
                            frame.arrastartysoltar.setText(""); 
                      JOptionPane.showMessageDialog(null, textoennegritabad(), "Alerta", JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/Imagenes/error.gif")));
                        
                        }
                   }
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
           }
       });
        
    }

    public arrastrarysoltar() {
       
    }
         public JLabel textoennegritagood() {
      String texto = "<html><b>Archivo Cargado</b></html>";
                   JLabel label = new JLabel(texto);
                    label.setFont(new Font("TimesRoman", Font.PLAIN, 16)); 
                    return label;
    }
    
       public JLabel textoennegritabad() {
      String texto = "<html><b>Archivo no valido,solo  .csv</b></html>";
                   JLabel label = new JLabel(texto);
                    label.setFont(new Font("TimesRoman", Font.PLAIN, 16)); 
                    return label;
    }
    
        public void  GenerartablaCSV() {
        leer.Leercsv(Sistema.ruta);
                   
                    FrmTable planilla = new FrmTable();
                    
                    planilla.table.setCellSelectionEnabled(true);
                    planilla.table.setModel(new DefaultTableModel(LeerArchivoCSV.data, LeerArchivoCSV.nombreColumnas));
                    DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
                    cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
  
        
                    for(int i=0; i<LeerArchivoCSV.nombreColumnas.length; i++)
                        planilla.table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
            
                    planilla.table.setEnabled(false);
    }
    
    
}
