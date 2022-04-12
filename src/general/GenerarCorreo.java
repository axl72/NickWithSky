
package general;

import general.*;
import java.text.Normalizer;
import javax.swing.SwingConstants;
import modelo.Contraseña;

public class GenerarCorreo {
    
    private String correo;
    private String dominio="@unmsm.edu.pe";
    private String contraseñafinal="";
    private Contraseña generadorcontraseña = new Contraseña();
    private int HomonimoIngresante;
     
    public void Correo(String Apaterno , String Pnombre){
        correo="";
       
        try {
            this.HomonimoIngresante=0;
            for (int k=0;k<Sistema.numerodeAlumno;k++){
                if(Sistema.VectorIngresantes[k].getPrimerNombre().equalsIgnoreCase(Sistema.VectorIngresantes[Sistema.numerodeAlumno].getPrimerNombre())&&Sistema.VectorIngresantes[k].getApellidoPaterno().equalsIgnoreCase(Sistema.VectorIngresantes[Sistema.numerodeAlumno].getApellidoPaterno())){
                    if(!Sistema.VectorIngresantes[k].isError()){
                        this.HomonimoIngresante++;
                    }
                    
                }
            }
            if(this.HomonimoIngresante==0){
                    correo=Sistema.VectorIngresantes[Sistema.numerodeAlumno].getPrimerNombre()+"."+Sistema.VectorIngresantes[Sistema.numerodeAlumno].getApellidoPaterno()+this.dominio;
                }else{
                    correo=Sistema.VectorIngresantes[Sistema.numerodeAlumno].getPrimerNombre()+"."+Sistema.VectorIngresantes[Sistema.numerodeAlumno].getApellidoPaterno()+Integer.toString(this.HomonimoIngresante)+this.dominio;
                }
        }catch (Exception e) {
            System.out.println(e);
        }
        correo=correo.toLowerCase();
        String cadenaLimpia = Normalizer.normalize(correo, Normalizer.Form.NFD);
        String correoLimpio = cadenaLimpia.replaceAll("[^\\p{ASCII}]", "");
        correo = correoLimpio;
        contraseñafinal=generadorcontraseña.generarContraseña();
    }
    
    public String getCorreo() {
        return correo;
    }

    public String getContraseñafinal() {
        return contraseñafinal;
    }
    public void limpiarcorreo(){
        correo="";
    }

    
}
