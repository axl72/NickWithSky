package modelo;

public class ProcesoAdmisión {
    String códigoDeProceso;
    String AñoDeIngreso;
    
    public String getCódigoProcesoAdmisión(String año){
        this.códigoDeProceso="";
        int k=0;
        switch(k){
            case 0:
            if(año.equals("2020")){
                this.códigoDeProceso="20";
                break;
            }
            k++;
            case 1:
            if(año.equals("2019")){
                this.códigoDeProceso="19";
                break;
            }
            k++;
            default:
                System.out.println("¡No se reconoce el año de admisión!");;
            
            
        }
        return códigoDeProceso;
    }
    
    public String getAñoDeIngreso(){
        return this.AñoDeIngreso;
    }
    
    
 

    public void setAñoDeIngreso(String AñoDeIngreso) {
        this.AñoDeIngreso = AñoDeIngreso;
    }
}
