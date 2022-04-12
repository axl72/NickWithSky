package modelo;

import general.*;

public class Facultad {
    private String Nombre="";
    private String codigoF;
    private boolean correcto; 
    private boolean used;
    private String RegistroError="";
    
    public Facultad(){
    }
    
    public void identificarFacultad(String nombre){
        this.correcto=true;
        for(int i=0; i<Sistema.cantidadFacultades; i++){
            if(nombre.equalsIgnoreCase(Sistema.facultades.getNombre(i))){
                this.Nombre=Sistema.facultades.getNombre(i);
                this.codigoF=Sistema.facultades.getCódigo(i);
                Sistema.facultades.setUsed(i, true);
            }
            
        }
        if(Nombre.equals("") || this.codigoF.equals("")){
                this.correcto=false;
                Nombre="NO IDENTIFICADA";
        }
    }

    public boolean isCorrecto() {
        return correcto;
    }

    public void setCorrecto(boolean correcto) {
        this.correcto = correcto;
    }

    public String getRegistroError() {
        return RegistroError;
    }

    public boolean isUsed() {
        return used;
    }
    
    
    
    public void setUsed(boolean used) {
        this.used = used;
    }
    
    public void setNombre(String nombre){
        this.Nombre=nombre;
    }
    
    public Facultad(String nombre, String CódigoF){
        this.Nombre=nombre;
        this.codigoF=CódigoF;
    }

    
    public String getCodigodeFacultad(){
        return this.codigoF;
    }

    public String getNombre() {
        return Nombre;
    }
    
    
}
