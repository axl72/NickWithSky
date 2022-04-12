
package controladores;

import general.GeneradorTXT;
import general.LeerArchivoCSV;
import general.RegistroLogin;
import general.Sistema;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class ControladorFrmRegistroLogin {
    private FrmRegistroLogin formulario;

    public ControladorFrmRegistroLogin(FrmRegistroLogin formulario) {
        Dimension p = Toolkit.getDefaultToolkit().getScreenSize();
        this.formulario = formulario;
        this.formulario.setBounds(p.width*50/200, p.height*60/200 ,p.width*65/100 , p.height*40/100);
        
        formulario.btnImprimir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo="REGISTRO DE INGRESOS AL SISTEMA";
                GeneradorTXT archivologin= new GeneradorTXT();
                
                String rutatemporal= System.getProperty("user.home") + "\\desktop"+ "\\Reporte de ingresos al sistema.txt";


                System.out.println(rutatemporal);
                archivologin.generarTxt(rutatemporal,RegistroLogin.imprimirRegistroLogin(),titulo);
            }
            
        });
          formulario.btncerrarregistrologin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            formulario.setVisible(false);
            }
            
        });
        this.formulario.table.setCellSelectionEnabled(true);
         
        this.formulario.table.setModel(new DefaultTableModel(RegistroLogin.matrizIngresos, RegistroLogin.cabecera));  
        
      
          
        this.formulario.table.setEnabled(false);
        this.formulario.setVisible(true);
        TableColumn columna;
        columna=this.formulario.table.getColumnModel().getColumn(2);
        columna.setPreferredWidth(290);
        columna.setMaxWidth(290);
        columna.setMinWidth(290);
        columna=this.formulario.table.getColumnModel().getColumn(0);
        columna.setPreferredWidth(80);
        columna.setMaxWidth(80);
        columna.setMinWidth(80);
        
        columna=this.formulario.table.getColumnModel().getColumn(1);
        columna.setPreferredWidth(150);
        columna.setMaxWidth(150);
        columna.setMinWidth(150);
        
        columna=this.formulario.table.getColumnModel().getColumn(3);
        columna.setPreferredWidth(120);
        columna.setMaxWidth(120);
        columna.setMinWidth(120);
        
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
  
        
        for(int i=0; i<RegistroLogin.cabecera.length; i++)
            formulario.table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        
        this.formulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        formulario.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                
                FrmRegistroLogin.setDisponible(true);
 
            }
        });
    }
          public void iniciar(){
            formulario.setDisponible(true);
    }
    
}
