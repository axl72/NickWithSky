
package controladores;

import general.GeneradorTXT;
import general.RegistroLogin;
import general.Sistema;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import vista.FrmRegistroLogin;
import vista.FrmRegistroTrabajadores;

/**
 *
 * @author axell
 */
public class ControladorFrmRegistroTrabajadores {
    private FrmRegistroTrabajadores formulario;

    public ControladorFrmRegistroTrabajadores(FrmRegistroTrabajadores formulario) {
        Dimension p = Toolkit.getDefaultToolkit().getScreenSize();
        formulario.setBounds(p.width*35/200, p.height*40/200, p.width*65/100, p.height*60/100);
        this.formulario = formulario;
        this.formulario.table.setModel(new DefaultTableModel(Sistema.trabajadores.getMatriz(), Sistema.trabajadores.getCabecera()));  
        formulario.setVisible(true);
        funcionalidadBotones();
        
        
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        for(int i=0; i<Sistema.trabajadores.getCabecera().length; i++)
            formulario.table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        
        formulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        TableColumn columna;
        columna = this.formulario.table.getColumnModel().getColumn(3);
        columna.setPreferredWidth(100);
        columna.setMaxWidth(100);
        columna.setMinWidth(100);
    }
    
    public void funcionalidadBotones(){
        formulario.btnImprimir.addActionListener(new ActionListener(){
            
            
            String [][] matriz = Sistema.trabajadores.getMatriz();
            String [] cabecera = Sistema.trabajadores.getCabecera();
            GeneradorTXT archivologin= new GeneradorTXT();
            String texto="";
            String titulo="LISTA DE TRABAJADORES REGISTRADOS";
            
            public void actionPerformed(ActionEvent e) {
                String rutatemporal=System.getProperty("user.home") + "\\desktop"+ "\\Reporte de Trabajadores Registrados.txt";

             
                for(int i=0; i<matriz.length; i++){
                    for(int j=0; j<matriz[0].length; j++){
                       texto+=matriz[i][j]+" ";
  
                    }
                    texto+="\n\n"; 
                  
                }  
                archivologin.generarTxt(rutatemporal,texto,titulo);
                
            }
            
        });
        
        formulario.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                
                FrmRegistroTrabajadores.setDisponible(true);
                
            }
        });
            formulario.btncerrartrabajadores.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
           formulario.setVisible(false);
            }
            
        });
        
    }
 
       public void iniciar(){
            FrmRegistroTrabajadores.setDisponible(true);
    }
    
    
}
