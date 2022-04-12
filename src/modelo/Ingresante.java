package modelo;

public class Ingresante extends Persona{
    private String escuela;
    private ProcesoAdmisión ProcesoAdmisión = new ProcesoAdmisión();
    private Facultad facultadIngresante = new Facultad();
    private String CódigoProcesoAdmisión;
    private boolean repetido=false;
    private boolean correoEnviado=false;
    private String EstadoGeneral;
    private boolean error=false;
    
    
    public boolean isCorreoEnviado() {
        return correoEnviado;
    }

    public void setCorreoEnviado(boolean correoEnviado) {
        this.correoEnviado = correoEnviado;
    }

    public Ingresante(String facultad, String escuela, String primerNombre, String segundoNombre, String apellidoPaterno, String apellidoMaterno, String fehcaNacimiento, String email, char sexo, String departamento, String residenciaDepartamento, String distrito, String direccion, String TelefonoCelular, String ProcesoAdmisión, String dni) {
        super(primerNombre, segundoNombre, apellidoPaterno, apellidoMaterno, fehcaNacimiento, email, sexo, departamento, residenciaDepartamento, distrito, direccion, TelefonoCelular, dni);

        this.facultadIngresante.identificarFacultad(facultad);
        this.escuela = escuela;
        this.ProcesoAdmisión.setAñoDeIngreso(ProcesoAdmisión);
        this.CódigoProcesoAdmisión=this.ProcesoAdmisión.getCódigoProcesoAdmisión(ProcesoAdmisión);
    }
    
    
    
    public boolean isRepetido() {
        return repetido;
    }

    public void setRepetido(boolean repetido) {
        this.repetido = repetido;
    }

    public boolean isError() {
        //System.out.println(this.facultadIngresante.getRegistroError());
        if(repetido || !this.facultadIngresante.isCorrecto()){
            this.error=true;
        }
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getCódigoFacultad() {
        return facultadIngresante.getCodigodeFacultad();
    }

    public String getEscuela() {
        return escuela;
    }

    public String getCódigoProcesoAdmisión() {
        return CódigoProcesoAdmisión;
    }

    public String getAñoDeIngreso(){
        return this.ProcesoAdmisión.getAñoDeIngreso();
    }
    
    public void setEstado(String estado){
        this.EstadoGeneral=estado;
    }
    
    public String getEstado(){
        return this.EstadoGeneral;
    }
    
    public void genEstado(){
        String result="";
        String Estado2="";
        String Estado3="";
        String Estado4="";
        
        if(repetido){
            Estado2="El ingresante es persona repetida";
        }
        if(!correoEnviado){
            Estado3="No se envió el correo electrónico";
        }
        
        if(!facultadIngresante.isCorrecto()){
            Estado4="No se pudo procesar la facultad del alumno";
        }
        result=Estado2+Estado3+Estado4;
        if(result.equals("")){
            result="No se presentaron errores al registrar ingresante";
        }else{
            result=Estado2+" | "+Estado3+" | "+Estado4;
        }
        this.EstadoGeneral=result;
    }
    
    public String getNombreFacultad(){
        return this.facultadIngresante.getNombre();
    }
}
