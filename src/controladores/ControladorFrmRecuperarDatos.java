
package controladores;

import general.Sistema;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import util.Email;
import vista.FrmRecuperarDatos;
import vista.FrmRegistrarTrabajador;
import vista.TextPrompt;

/**
 *
 * @author axell
 */
public class ControladorFrmRecuperarDatos {
    private FrmRecuperarDatos formulario;
    

    public ControladorFrmRecuperarDatos(FrmRecuperarDatos formulario) {
        
        this.formulario = formulario;
        formulario.run();
        propiedadesFormulario();
        funcionalidadBotones();
        formulario.setVisible(true);
    }
    
    public void propiedadesFormulario(){
        Dimension p = Toolkit.getDefaultToolkit().getScreenSize();
        formulario.setBounds(p.width*60/200, p.height*60/200, p.width*30/100, p.height*40/100);
        formulario.setResizable(false);
        formulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        TextPrompt prueba2 = new TextPrompt("Dirección de correo electrónico de recuperación", formulario.txtCorreo);
        
        formulario.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                FrmRegistrarTrabajador.setDisponible(true);
                FrmRecuperarDatos.setDisponible(true);
            }
        });
        
    }
    
    public void funcionalidadBotones(){
        formulario.btnSiguiente.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String correo = formulario.txtCorreo.getText();
                boolean validado = Sistema.validarCorreo(correo);
                if(validado){
                    boolean encontrado = false;
                    for(int i=0; i<Sistema.trabajadores.length(); i++){
                        if(correo.equals(Sistema.trabajadores.getTrabajadores()[i].getEmail())){
                            String mensaje = "<b>Adjuntamos sus credenciales</b><br>";
                            mensaje += "<i><font color=gray>TRABAJADOR: <i>" + Sistema.trabajadores.getTrabajadores()[i].getPrimerNombre() + " " + 
                            Sistema.trabajadores.getTrabajadores()[i].getSegundoNombre() + Sistema.trabajadores.getTrabajadores()[i].getApellidoPaterno() + " "+ 
                            Sistema.trabajadores.getTrabajadores()[i].getApellidoMaterno() +"<br>Usuario: " +Sistema.trabajadores.getTrabajadores()[i].getLogin() + "<br>Contraseña: " +
                            Sistema.trabajadores.getTrabajadores()[i].getPassword() + "</font>";
                            
                            Email email =  new Email(correo, "Envio de credenciales", mensaje);
                            Thread enviar = new Thread(email);
                            enviar.start();
                            encontrado = true;
                            
                            FrmRecuperarDatos.setDisponible(true);
                            formulario.dispose();
                            
                            
                            
                        }

                    }
                    
                    if(encontrado)
                        JOptionPane.showMessageDialog(null, "Sus credenciales se enviaron a su correo", "Correo enviado", 1);
                            
                    else
                        JOptionPane.showMessageDialog(null, "El correo no está asociado a ningún trabajador", "No encontrado", 2);
                    
                    
                }
                
                else
                    JOptionPane.showMessageDialog(null, "Introduzca un correo valido", "Error", 2);
                
            }
            
        });
    }


    
}
