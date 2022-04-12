package modelo;

import general.Sistema;
import java.io.Serializable;
import vista.FrmSistema;

public class Persona implements Serializable{
    private String primerNombre;
    private String segundoNombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String fehcaNacimiento;
    private String email;
    private char sexo;
    private String sexoDescripcion;
    private String dni;
    private String departamento;
    private String residenciaDepartamento;
    private String provinciaNac;
    private String provinciaRes;
    private String distrito;
    private String direccion;
    private String TelefonoCelular;
    private boolean repetido=false;
    FrmSistema vista = new FrmSistema();

    public Persona(String primerNombre, String segundoNombre, String apellidoPaterno, String apellidoMaterno, String fehcaNacimiento, String email, char sexo, String departamento, String residenciaDepartamento, String distrito, String direccion, String TelefonoCelular, String dni) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fehcaNacimiento = fehcaNacimiento;
        this.email = email;
        this.sexo = sexo;
        this.departamento = departamento;
        this.residenciaDepartamento = residenciaDepartamento;
        this.distrito = distrito;
        this.direccion = direccion;
        this.TelefonoCelular = TelefonoCelular;
        this.dni=dni;
    }

    public Persona(String primerNombre, String segundoNombre, String apellidoPaterno, String apellidoMaterno, String fehcaNacimiento, String dni, String telefono, String sexo, String departamento, String residenciaDepartamento, String provinciaNac, String provinciaRes, String email) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fehcaNacimiento = fehcaNacimiento;
        this.email = email;
        this.TelefonoCelular = telefono;
        this.sexoDescripcion = sexo;
        this.dni = dni;
        this.departamento = departamento;
        this.residenciaDepartamento = residenciaDepartamento;
        this.provinciaNac = provinciaNac;
        this.provinciaRes = provinciaRes;
       
    }
    
    

    public String getDni() {
        return dni;
    }

   
    
    public Persona(String nombre, String nombre2, String apellido1, String apellido2, String correo){
        this.primerNombre = nombre;
        this.segundoNombre = nombre2;
        this.apellidoPaterno = apellido1;
        this.apellidoMaterno = apellido2;
        this.email = correo;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }
    
    public String getSegundoNombre(){
        return segundoNombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getFehcaNacimiento() {
        return fehcaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public char getSexo() {
        return sexo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getResidenciaDepartamento() {
        return residenciaDepartamento;
    }

 
    public String getDistrito() {
        return distrito;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefonoCelular() {
        return TelefonoCelular;
    }
    
    public void verificarPersona(){
        String result="";
        for(int i=0; i<Sistema.numerodeAlumno;i++){
            if(Sistema.vectorAlumno[Sistema.numerodeAlumno].getDni().equalsIgnoreCase(Sistema.vectorAlumno[i].getDni())){
                System.out.println("Repetido");
                Sistema.vectorAlumno[Sistema.numerodeAlumno].setRepetido(true);
                Sistema.VectorIngresantes[Sistema.numerodeAlumno].setRepetido(true);
                if(Sistema.cantidadErrores==0){
                    Sistema.errores+="Se han detectado los siguientes ingresantes repetidos: "+Sistema.vectorAlumno[Sistema.numerodeAlumno].toString()+"\n";
                }else{
                    Sistema.errores+=Sistema.vectorAlumno[Sistema.numerodeAlumno].toString()+"\n";
                }
                
                Sistema.cantidadErrores++;
                
                
          
            }
        }
    }

    public void setRepetido(boolean repetido) {
        this.repetido = repetido;
    }

    public boolean isRepetido() {
        return repetido;
    }

    public String getSexoDescripcion() {
        return sexoDescripcion;
    }

    public String getProvinciaNac() {
        return provinciaNac;
    }

    public String getProvinciaRes() {
        return provinciaRes;
    }
    
    

}
