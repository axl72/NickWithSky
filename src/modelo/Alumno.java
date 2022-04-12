package modelo;

import vista.FrmSistema;

public class Alumno extends Persona{
    
    private String codigo;
    private String correoInstitucional;
    private String contraseña;
    private static int cantidadAlumnos = 0;
    private boolean repetido;
    ProcesoAdmisión ProcesoDeAdmisión = new ProcesoAdmisión();

    public Alumno(String codigo, String correoInstitucional, String contraseña, String primerNombre, String segundoNombre, String apellidoPaterno, String apellidoMaterno, String fehcaNacimiento, String email, char sexo, String departamento, String residenciaDepartamento, String distrito, String direccion, String TelefonoCelular, String AñoDeIngreso, String dni) {
        super(primerNombre, segundoNombre, apellidoPaterno, apellidoMaterno, fehcaNacimiento, email, sexo, departamento, residenciaDepartamento, distrito, direccion, TelefonoCelular, dni);
        this.codigo = codigo;
        this.correoInstitucional = correoInstitucional;
        this.contraseña = contraseña;
        this.ProcesoDeAdmisión.setAñoDeIngreso(AñoDeIngreso);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public String getContraseña() {
        return contraseña;
    }

    
    
    public String getAñoDeIngreso(){
        return this.ProcesoDeAdmisión.getAñoDeIngreso();
    }
    @Override
    public String toString() {
        return super.getPrimerNombre()+ " CODIGO : " + codigo + " CORREOINSTITUCIONAL: " + correoInstitucional + " CONTRASE\u00f1a: " + contraseña ;
    }

    
  

   



    

    
    
    
    
    

    
    
    
    
}
