package general;

import modelo.Facultad;

public class Generarcodigo {
    private String nfacu;
    private String nescuela;
    private String nbase;
    private String codigo;
    
    
    
    
    public String codigo(){
        if(!Sistema.VectorIngresantes[Sistema.numerodeAlumno].isError()){
            codigo=Sistema.VectorIngresantes[Sistema.numerodeAlumno].getCódigoProcesoAdmisión()+Sistema.VectorIngresantes[Sistema.numerodeAlumno].getCódigoFacultad()+getContador();
        }else{
            Sistema.VectorIngresantes[Sistema.numerodeAlumno].setError(true);
        }
        return codigo;
    }


    public void tempcodigo(){
        String codigo="";  
    }
    
    public String getContador(){
        String result ="";
        int[] contador = new int[4];
        System.out.println("Contador de alumnos"+Sistema.NumeroContador);
        contador[3] = Sistema.NumeroContador%10;
        contador[2] = Sistema.NumeroContador/10 % 10;
        contador[1] = Sistema.NumeroContador/100 % 10;
        contador[0] = Sistema.NumeroContador/1000 % 10;
        
        for(int i=0;i<4;i++){
            result+=Integer.toString(contador[i]);
        }
        Sistema.NumeroContador++;
        return result;
    }

    public String getCodigo() {
        return codigo;
    }

   

 
    



}
