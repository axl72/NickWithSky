import vista.*;
import controladores.*;
import general.RegistroLogin;
import general.Sistema;
import modelo.*;


public class App {
    public static void main(String [] args){
        
        Sistema.instanciarDepartamentos();
        Sistema.facultades.instanciarFacultades();
        
        RegistroLogin.recuperarDatosLogin();
        //Sistema.trabajadores.recuperarSerial();
        Sistema.trabajadores.addTrabajador(new Trabajador("Eliot", "Alderson", "Robot", "Puyen", "", "", "", "", "", "", "", "", "", "", ""));
        FrmLogin frmLogin = new FrmLogin();
        ControladorFrmLogin controladorFrmLogin = new ControladorFrmLogin(frmLogin);
    }
    
    public int numero(){
        return 1;
    }
    
}
