package modelo;


import general.Sistema;
import java.util.*;

public class Escuela {

    public Escuela() {
    }
    
    
    public String CodigoEscuela(int q,String Escuela){
        
        String codigoE="";
        
        switch (q){
            case 200: 
                if (Escuela.equalsIgnoreCase("sistemas")){
                      codigoE="300" ;break;
                }else if(Escuela.equalsIgnoreCase("software")){
                  
                  codigoE="500";break;}
                      
            
    case 100 : if (Escuela.equalsIgnoreCase("meidicna humana")){
                   codigoE="150"; break; }  
               
                else if(Sistema.VectorIngresantes[Sistema.numerodeAlumno].getEscuela().equalsIgnoreCase("odontologia")){
                  codigoE="160";break;
                }
                
    
    
}return codigoE;}
  
}
 