package modelo;

import general.Sistema;
import java.io.Serializable;

public class Trabajador extends Persona implements Serializable{
    
    private String login;
    private String password;
    private boolean conectado;
    private String email;

    public Trabajador(String nombre, String nombre2, String apellido1, String apellido2, String login, String password, String email) {
        super(nombre, nombre2, apellido1, apellido2, email);
        this.login = login;
        this.password = password;
        this.email = email;
        
    }

    public Trabajador(String primerNombre, String segundoNombre, String apellidoPaterno, String apellidoMaterno, String fehcaNacimiento, String dni, String telefono, String sexo, String departamento, String residenciaDepartamento, String provinciaNac, String provinciaRes, String login, String password,String email) {
        super(primerNombre, segundoNombre, apellidoPaterno, apellidoMaterno, fehcaNacimiento, dni, telefono, sexo, departamento, residenciaDepartamento, provinciaNac, provinciaRes, email);
        this.login = login;
        this.password = password;
        this.email = email;
    }


    public String getEmail(){
        return email;
    }
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isConectado() {
        return conectado;
    }


    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }
    
    

   

    @Override
    public String toString() {
        return login + "\t" + password + "\t" + conectado;
    }
   
    
}
