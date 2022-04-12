package controladores;


import general.Sistema;
import vista.FrmRegistrarTrabajador;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import javax.swing.*;
import modelo.Trabajador;
import util.Email;
import vista.FrmRecuperarDatos;
import vista.TextPrompt;

public class ControladorFrmRegistrar {
    private FrmRegistrarTrabajador formulario;
    private int intentos = 0;
    
    
    public ControladorFrmRegistrar(FrmRegistrarTrabajador formulario){
        this.formulario = formulario;
     
        formulario.run();
        Dimension p = Toolkit.getDefaultToolkit().getScreenSize();
        
       
        
        
        formulario.setBounds(500, 100, 800, 800);
       
        
        formulario.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                FrmRegistrarTrabajador.setDisponible(true);
                FrmRecuperarDatos.setDisponible(true);
            }
        }
        );
     
        
        ButtonGroup grupobtn1 = new ButtonGroup();
        
        grupobtn1.add(formulario.radioBtnM);
        grupobtn1.add(formulario.radioBtnF);
        funcionalidadBotones();
        funcionalidadComboBox();
        formulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        TextPrompt prueba = new TextPrompt("ADMINPASS", formulario.txtAdminpass);
        TextPrompt prueba1 = new TextPrompt("NUEVA CONTRASEÑA", formulario.txtPassword);
        TextPrompt prueba2 = new TextPrompt("CONFIRMACIÓN NUEVA CONTRASEÑA", formulario.txtPassword2);
        TextPrompt prueba3 = new TextPrompt("USUARIO", formulario.txtUser);
        TextPrompt prueba4 = new TextPrompt("CORREO ELECTRÓNICO", formulario.txtEmail);
       
        
    }
    
    class FondoPanel extends JPanel{
            private Image imagen;
            private String direccion;
            
            public FondoPanel(String direccion){
                this.direccion = direccion;
                this.imagen = new ImageIcon(getClass().getResource(direccion)).getImage();
            }

            public void paint(Graphics g){
                
                g.drawImage(this.imagen, 0, 0 , getWidth(), getHeight(), this);
                setOpaque(false);
                super.paint(g);
            }
            
            
    }
    
    public void funcionalidadComboBox(){
        
        formulario.boxProvincia1.setEnabled(false);
        formulario.boxProvincia2.setEnabled(false);
        formulario.boxDepartamento1.addItem("Departamento");
        formulario.boxDepartamento2.addItem("Departamento");
        for(int i=0; i<Sistema.cantDepartamentos; i++)
            formulario.boxDepartamento1.addItem(Sistema.departamentos[i].getNombre());
        
        for(int i=0; i<Sistema.cantDepartamentos; i++)
            formulario.boxDepartamento2.addItem(Sistema.departamentos[i].getNombre());
        
        formulario.boxDepartamento1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(!formulario.boxDepartamento1.getSelectedItem().equals("Departamento")){
                    if(formulario.boxProvincia1.getItemCount() == 0){
                        formulario.boxProvincia1.setEnabled(true);
                    }
                    else
                        formulario.boxProvincia1.removeAllItems();
                    
                    formulario.boxProvincia1.addItem("Provincia");
                    
                    for(int i=0; i<Sistema.departamentos.length; i++)
                            if(Sistema.departamentos[i].getNombre().equals(formulario.boxDepartamento1.getSelectedItem()))
                                for(int j=0; j<Sistema.departamentos[i].getProvincias().length; j++)
                                    formulario.boxProvincia1.addItem(Sistema.departamentos[i].getProvincias()[j].getNombre());
                        
                }
                else{
                    formulario.boxProvincia1.removeAllItems();
                    formulario.boxProvincia1.setEnabled(false);
                }
                    
                    
            }
            
        });
        
        formulario.boxDepartamento2.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                if(!formulario.boxDepartamento2.getSelectedItem().equals("Departamento")){
                    if(formulario.boxProvincia2.getItemCount() == 0){
                        formulario.boxProvincia2.setEnabled(true);   
                    }
                    else
                        formulario.boxProvincia2.removeAllItems();
                    
                    formulario.boxProvincia2.addItem("Provincia");
                    
                    for(int i=0; i<Sistema.departamentos.length; i++)
                            if(Sistema.departamentos[i].getNombre().equals(formulario.boxDepartamento2.getSelectedItem()))
                                for(int j=0; j<Sistema.departamentos[i].getProvincias().length; j++)
                                    formulario.boxProvincia2.addItem(Sistema.departamentos[i].getProvincias()[j].getNombre());       
                }  
                else{
                    formulario.boxProvincia2.removeAllItems();
                    formulario.boxProvincia2.setEnabled(false);
                }
                
            }
        });
    }
    
    public void funcionalidadBotones(){
        
        formulario.btnRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String genero;
                boolean validarTelef = Sistema.validarTextoNumerico(formulario.txtTelefono.getText(), 9);
                boolean validarDNI = Sistema.validarTextoNumerico(formulario.txtDNI.getText(), 8);
                boolean validarCorreo = Sistema.validarCorreo(formulario.txtEmail.getText());
                boolean validarCorreoRepetido = Sistema.validarCorreoRepetido(formulario.txtEmail.getText());
                boolean validarUser = Sistema.validarUserName(formulario.txtUser.getText());
                boolean validarPasswords = formulario.txtPassword.getText().equals(formulario.txtPassword2.getText());
                

                if(formulario.radioBtnM.isSelected())
                        genero = "Masculino";
                    else
                        genero = "Femenino";
                
       
                
                
                if (validarCorreoRepetido && !formulario.txtNombre1.getText().equals("") && !formulario.txtNombre2.getText().equals("") && !formulario.txtApellido1.getText().equals("") && !formulario.txtApellido2.getText().equals("") && 
                        formulario.fecha.getCalendar() != null && !formulario.txtDNI.getText().equals("") && !formulario.boxDepartamento1.getSelectedItem().equals("Departamento") && 
                        !formulario.boxDepartamento2.getSelectedItem().equals("Departamento") && !formulario.boxProvincia1.getSelectedItem().equals("Provincia") && 
                        !formulario.boxProvincia2.getSelectedItem().equals("Provincia") && !formulario.txtTelefono.getText().equals("") && validarTelef && !formulario.txtEmail.getText().equals("") && validarCorreo && 
                        !formulario.txtUser.getText().equals("") && !formulario.txtPassword.getText().equals("") && formulario.txtAdminpass.getText().equals(Sistema.AdminPass) && !formulario.txtEmail.getText().equals("") && 
                        validarUser && validarDNI && validarPasswords && (genero.equals("Masculino") || genero.equals("Femenino"))){ 
                    
                    
                    
                    Trabajador w = new Trabajador(formulario.txtNombre1.getText().toUpperCase(), formulario.txtNombre2.getText().toUpperCase(), formulario.txtApellido1.getText().toUpperCase(), 
                            formulario.txtApellido2.getText().toUpperCase(), new SimpleDateFormat("dd/MM/yyyy").format(formulario.fecha.getDate()), formulario.txtDNI.getText(), formulario.txtTelefono.getText().toUpperCase(),
                            genero, (String)formulario.boxDepartamento1.getSelectedItem(), (String)formulario.boxDepartamento2.getSelectedItem(), (String)formulario.boxProvincia1.getSelectedItem(),
                            (String)formulario.boxProvincia2.getSelectedItem(), formulario.txtUser.getText(), formulario.txtPassword.getText(), formulario.txtEmail.getText());
                    
                    if(Sistema.trabajadores.validarNuevoTrabajador(w)){
                        Sistema.trabajadores.addTrabajador(w);
                        
                        //Mensaje más sonido
                        Toolkit.getDefaultToolkit().beep();
                        
                        JOptionPane.showMessageDialog(null, "Sus datos han sido enviados a su correo", "REGISTRO EXITOSO", 1);
                        Sistema.trabajadores.serializar();
                        FrmRegistrarTrabajador.setDisponible(true);
                        
                        String mensaje = "<b>Bienvenido al equipo OCA!</b><br>";
                        
                        mensaje += "<i><font color=gray>TRABAJADOR: <i>" + Sistema.trabajadores.getTrabajadores()[Sistema.trabajadores.length()-1].getPrimerNombre() + " " + 
                        Sistema.trabajadores.getTrabajadores()[Sistema.trabajadores.length()-1].getSegundoNombre() + Sistema.trabajadores.getTrabajadores()[Sistema.trabajadores.length()-1].getApellidoPaterno() + " "+ 
                        Sistema.trabajadores.getTrabajadores()[Sistema.trabajadores.length()-1].getApellidoMaterno() +"<br>Usuario: " +Sistema.trabajadores.getTrabajadores()[Sistema.trabajadores.length()-1].getLogin() + "<br>Contraseña: " +
                        Sistema.trabajadores.getTrabajadores()[Sistema.trabajadores.length()-1].getPassword() + "</font>";
                        
                        System.out.println(mensaje);
                        
                        Email email =  new Email(formulario.txtEmail.getText(), "Plataforma UNMSM - Registro Exitoso", mensaje);
                        Thread enviar = new Thread(email);
                        enviar.start();
                        
                            
                        FrmRecuperarDatos.setDisponible(true);
                        formulario.dispose();
                        

                    }
                    else{
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "El DNI ingresado ya está asociado a un trabajador", "ADVERTENCIAD DEL SISTEMA", 1);
                        formulario.txtEmail.setForeground(Color.RED);
                    }
 
                    
                }
                
                else{
                    if(!validarCorreoRepetido)
                        formulario.labelBoolCorreo.setText("ESTE CORREO NO ESTA DISPONIBLE");
                    
                    else
                        formulario.labelBoolCorreo.setText("");
                    
                    
                    
                    if(formulario.txtNombre1.getText().equals(null) || formulario.txtNombre2.getText().equals(null) || formulario.txtApellido1.getText().equals(null) || formulario.txtApellido2.getText().equals(null) || formulario.fecha.getCalendar() == null || formulario.txtEmail.equals(null)){
                        formulario.labelRellenarIP.setText("FALTAN RELLENAR CAMPOS");
                    }
                    
                    else
                        formulario.labelRellenarIP.setText(null);
                    
                    if(formulario.boxDepartamento1.getSelectedItem().equals("Departamento") || formulario.boxDepartamento2.getSelectedItem().equals("Departamento") || 
                       formulario.boxProvincia1.getSelectedItem().equals("Provincia") || formulario.boxProvincia2.getSelectedItem().equals("Provincia") || formulario.txtTelefono.getText().equals(""))
                        formulario.labelRellenarIS.setText("FALTAN RELLENAR CAMPOS");

                    else
                        formulario.labelRellenarIS.setText(null);
                    
                    if(formulario.txtAdminpass.getText().equals("") || formulario.txtUser.getText().equals("") || formulario.txtPassword.getText().equals("") || 
                            formulario.txtEmail.getText().equals("")){
                   
                        formulario.labelRellenarIL.setText("FALTAN RELLENAR CAMPOS");
    
                        
                    }
                    
                    else
                        formulario.labelRellenarIL.setText(null);
                    
                    if(formulario.txtAdminpass.getText().equals(""))
                        JOptionPane.showMessageDialog(null, "INGRESE ADMIN PASS", "ERROR DE VALIDACION", 2);
                    
                    else if(!formulario.txtAdminpass.getText().equals(Sistema.AdminPass)){
                        intentos++;
                        if(intentos <3){
                            JOptionPane.showMessageDialog(null, "ADMIN PASS INCORRECTO", "ERROR DE VALIDACION", 2);
                            
                        }    
                        else{
                            JOptionPane.showMessageDialog(null, "EL FORMULARIO SE CERRARÁ", "EXCEDIÓ EL NÚMERO DE INTENTOS", 0);
                            FrmRegistrarTrabajador.setDisponible(true);
                            formulario.dispose();
                            
                        }
                            
                    }
                    
                    
                }
            
            }
       
        });
        
        
    }    
     
    
    
    
    
}
