package controladores;

import general.RegistroIngreso;
import general.RegistroLogin;
import general.Sistema;
import vista.TextPrompt;
import vista.FrmLogin;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import modelo.*;
import util.Email;
import vista.FrmRecuperarDatos;
import vista.FrmRegistrarTrabajador;
import vista.FrmSistema;


public class ControladorFrmLogin {
    private FrmLogin frmLogin;
    private FondoPanel fondo;
    
    
    public ControladorFrmLogin(FrmLogin frmLogin){
        
        FondoPanel fondo = new FondoPanel("/imagenes/fondoMuralSM.png");
        frmLogin.setContentPane(fondo);
        //Modificación de componentes del Formulario de Login
        this.frmLogin = frmLogin;
        frmLogin.run();
        frmLogin.setVisible(true);
        
        if(Sistema.recordar){
            frmLogin.txtUser.setText(Sistema.userRecordado);
            frmLogin.txtPassword.setText(Sistema.passwordRecordado);
            frmLogin.checkBoxRecordar.setSelected(true);
        }
        
        //Establece el tamaño del form
        
        Dimension p = Toolkit.getDefaultToolkit().getScreenSize();
        frmLogin.setBounds(p.width*1/8, p.height*1/5, p.width*3/4, p.height*3/5);
        
        TextPrompt prueba = new TextPrompt("USUARIO", frmLogin.txtUser);
        TextPrompt prueba2 = new TextPrompt("CONTRASEÑA", frmLogin.txtPassword);
        
        //Estableciendo eventos de los botones;
        funcionalidadBotones();
        
    }
    /* Esta funcion da funcionalidad a los botones a traves del manejo de eventos
        
    */
    private void funcionalidadBotones(){
        
        /*Instanciamos la interfaz de eventos para el boton enter - el boton enter
        es el boton que nos permite iniciar sesion en el sistema
        */
        
        frmLogin.btnEnter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //Si el los campos de credenciales del formulario coinciden con uno de los usuarios que maneja el sistema, automaticamentese accede al sistema

                    Sistema.usuarioConectado = Sistema.trabajadores.ingresar(frmLogin.txtUser.getText() , frmLogin.txtPassword.getText());
                    if ( Sistema.usuarioConectado != null ){                   
                        try {
                            RegistroLogin.addRegistroIngreso(new RegistroIngreso());
                        } catch (UnknownHostException ex) {
                            Logger.getLogger(ControladorFrmLogin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(frmLogin.checkBoxRecordar.isSelected()){
                            Sistema.userRecordado = frmLogin.txtUser.getText();
                            Sistema.passwordRecordado = frmLogin.txtPassword.getText();
                            Sistema.recordar = true;
                        }
                        
                        else{
                            Sistema.userRecordado = null;
                            Sistema.passwordRecordado = null;
                            Sistema.recordar = false;
                            
                        }
                        
                        String mensaje = "<b>¡Notificación de inicio de sesión en la plataforma de generador de códigos y correos de la UNMSM!</b><br>";
                        mensaje += "<i><font color=gray>Usuario: <i>" + Sistema.usuarioConectado.getLogin() + "</font>";
                        Email email =  new Email(Sistema.usuarioConectado.getEmail(), "Plataforma UNMSM - Correos y Códigos de Alumno", mensaje);
                        Thread enviar = new Thread(email);
                        enviar.start();
                        
                        frmLogin.dispose();
                        FrmSistema frmSistema = new FrmSistema();
                        
                        ControladorFrmSistema controladorSistema = new ControladorFrmSistema(frmSistema);
                        controladorSistema.iniciar();
                        //frmSistema.labelUsername.setText(Sistema.usuarioConectado.getPrimerNombre() + " " + Sistema.usuarioConectado.getApellidoPaterno());
                    }

                    
                    // Validacion cuando los campos no coinciden
                    else {
                        //Validación cuando faltan rellenar campos
                        if(frmLogin.txtUser.getText().equalsIgnoreCase("") || frmLogin.txtPassword.getText().equals(""))
                            frmLogin.labelNotificacion.setText("Faltan rellenar campos");
                            
                        
                        
                        //Validación cuando no faltan rellenar campos pero no coinciden con ningun usuari registrado en el sitema
                        else{
                            frmLogin.labelNotificacion.setText("Error de credenciales");
                            frmLogin.txtUser.setText(null);
                            frmLogin.txtPassword.setText(null);
                        }
                        
                        

                    }
                
            }
        });
        
        /*
        
        */
        frmLogin.btnSignIn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(FrmRegistrarTrabajador.isDisponible()){
                    FrmRegistrarTrabajador frmRegistrarTrabajador = new FrmRegistrarTrabajador();
                    ControladorFrmRegistrar controladorRegistrar = new ControladorFrmRegistrar(frmRegistrarTrabajador);
                    
                    FrmRegistrarTrabajador.setDisponible(false);
                    FrmRecuperarDatos.setDisponible(false);
                }
                
                else
                    JOptionPane.showMessageDialog(null, "Ya hay formulario abierto", "Mensaje del sistema", 1);
            }
        });
        
        frmLogin.btnRecuperar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(FrmRecuperarDatos.isDisponible()){
                    FrmRecuperarDatos formulario = new FrmRecuperarDatos();
                    ControladorFrmRecuperarDatos controlador = new ControladorFrmRecuperarDatos(formulario);
                    FrmRegistrarTrabajador.setDisponible(false);
                    FrmRecuperarDatos.setDisponible(false);
                    
                }
                
                else
                    JOptionPane.showMessageDialog(null, "Ya hay formulario abierto", "Mensaje del sistema", 1);
            }
        });
        
    }


    class FondoPanel extends JPanel{
            private Image imagen;
            private String direccion;
            
            public FondoPanel(String direccion){
                this.direccion = direccion;
                this.imagen = new ImageIcon(getClass().getResource(direccion)).getImage();
            }

            @Override
            public void paint(Graphics g){
                
                g.drawImage(this.imagen, 0, 0 , getWidth(), getHeight(), this);
                setOpaque(false);
                super.paint(g);
            }
    }
    
    
    
         
    
}
