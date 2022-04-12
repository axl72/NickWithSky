package modelo;

import general.Generadorexcel;
import general.Sistema;

public class AlumnoArreglo {
    private Alumno[] alumnos;
    private int indice;
    private boolean repetido;
    private Generadorexcel generador = new Generadorexcel();
    
    public AlumnoArreglo(int tamño){
        this.alumnos = new Alumno[tamño];
        this.indice=0;
    }
    
    public void addAlumno(Alumno a){
        boolean result = false;
        if(!isLleno()){
            this.alumnos[this.indice] = a;
            this.indice++;
            alumnox();
            result = true;
        }
       
    }

    private boolean isLleno() {
        if(this.indice == this.alumnos.length){
            return true;
        } else {
            return false;
        }}
    public int getLength(){
        return this.alumnos.length;
       
    }
    
    public void alumnox(){
        Sistema.vectorAlumno=alumnos; 
     }

    public void limpiaralumnos(){
            for(int l=0;l<Sistema.numeroRegistro;l++){
                alumnos [l]=new Alumno("", "", "", "", "", "", "", "", "", ' ', "", "", "", "", "","","");
            }
        }
    
    @Override
    public String toString() {
        String result="";    
                   
        for (int i=0; i<this.indice;i++){
            if(!this.alumnos[i].isRepetido())
                result += this.alumnos[i] + "\n";
            }
        return result;
    }
        
        
    }
    

    