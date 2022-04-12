
package controladores;

import general.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Alumno;
import modelo.Ingresante;
import util.Email;
import vista.FrmInformacionIngresante;

public class ControladorFrmInformaciónIngresante {
    FrmInformacionIngresante vista = new FrmInformacionIngresante();
    Ingresante ingresanteEncontrado = null;
    Alumno alumnoEncontrado = null;
    private int posicion;
    
    public ControladorFrmInformaciónIngresante(FrmInformacionIngresante vista){
        this.vista=vista;
        this.vista.btnRC.setEnabled(false);
        funcionalidadBotones();
    }
    
    public void funcionalidadBotones(){
        this.vista.btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int j=0;j<Sistema.numerodeAlumno;j++){
                    Sistema.VectorIngresantes[j].genEstado();
                }
                if(vista.cmbBuscarAlumnoPor.getSelectedItem().equals("DNI")){
                    for(int i=0;i<Sistema.numerodeAlumno;i++){
                        if(vista.txtBuscarAlumno.getText().equals(Sistema.VectorIngresantes[i].getDni())){
                            System.out.println("ITERADOR: "+i);
                            ingresanteEncontrado=Sistema.VectorIngresantes[i];
                            alumnoEncontrado=Sistema.vectorAlumno[i];
                            posicion=i;
                            i=Sistema.numerodeAlumno;
                        }
                        
                    }
                }
                if (ingresanteEncontrado!= null && alumnoEncontrado != null){
                    String info="";
                    info+="Nombre: "+ingresanteEncontrado.getPrimerNombre()+" "+ingresanteEncontrado.getApellidoPaterno()+"\n";
                    info+="DNI: "+ingresanteEncontrado.getDni()+"\n\n";
                    if(ingresanteEncontrado.isCorreoEnviado()){
                        info+="Correo electrónico: Enviado\n"; 
                    }else if(!ingresanteEncontrado.isCorreoEnviado()){
                        info+="Correo electrónico: No Enviado\n"; 
                    }
                    info+="Facultad: "+ingresanteEncontrado.getNombreFacultad()+"\n";
                    info+="Escuela: "+ingresanteEncontrado.getEscuela()+"\n\n";
                    info+="Estado de ingresante: \n"+ingresanteEncontrado.getEstado();

                    vista.txtInfoIngresante.setText(info);
                    vista.btnRC.setEnabled(true);
                }else{
                    vista.txtInfoIngresante.setText("El alumno no ha sido encontrado, ingrese otro DNI");
                }
                
            }
        });
        
        this.vista.btnRC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensaje="";
                mensaje += "¡Su correo electrónico y código de alumno universitario de la UNMSM se ha generado!<br><br>";
                mensaje += "<i><b>Código de alumno: <i>" + alumnoEncontrado.getCodigo()+ "</font></b><br><br>";
                mensaje += "<b><i>Correo Institucional: <i>" + alumnoEncontrado.getCorreoInstitucional()+ "</font></b><br>";
                mensaje += "<b><i>CONTRASEÑA: <i>" + alumnoEncontrado.getContraseña()+ "</b></font><br>";
                mensaje +="<br><br><b>Correo electrónico reenviado</b>";
                
                if(!ingresanteEncontrado.isError()){
                    Email email =  new Email(ingresanteEncontrado.getEmail(), "Plataforma UNMSM - Correos y Códigos de Alumno", mensaje);
                    Thread enviar = new Thread(email);

                    enviar.start();
                    Sistema.VectorIngresantes[posicion].setCorreoEnviado(true);
                    JOptionPane.showMessageDialog(null, "¡Correo electrónico reenviado!");
                }else{
                    JOptionPane.showMessageDialog(null, "¡Error al enviar correo electrónico!");
                }

                
                
            }
        });
           this.vista.btnSalir1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           vista.dispose();

                
                
            }
        });
        
    }
    
    public void IniciarFrm(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.txtInfoIngresante.setEditable(false);
    }
}
