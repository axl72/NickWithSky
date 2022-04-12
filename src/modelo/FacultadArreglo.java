package modelo;

import general.Sistema;
import java.util.Arrays;

public class FacultadArreglo {
    private Facultad[] facultades;
    private int cantidadFacultades;
    
    public FacultadArreglo(){
        facultades = new Facultad[0];
        cantidadFacultades=0;
    }
    
    public String getNombre(int numero){
        String nombreF = facultades[numero].getNombre();
        return nombreF;
    }
    
    public String getCódigo(int numero){
        String CódigoF = facultades[numero].getCodigodeFacultad();
        return CódigoF;
    }
    
    public void addFacultad(Facultad f){
        Facultad [] aux = facultades;
        facultades = new Facultad[cantidadFacultades+1];
                
        for(int i=0; i<aux.length; i++){
            facultades[i] = aux[i];
        }
        facultades[cantidadFacultades] = f;
        cantidadFacultades++;
        Sistema.cantidadFacultades++;
    }
    
    public void setUsed(int i, boolean used){
        facultades[i].setUsed(used);
    }
    
    public boolean isUsed(int i){
        boolean result=facultades[i].isUsed();
        return result;
    }
    
    public String getCantidadFacultadesUsadas(){
        int cantidadFacultadesUsadas=0;
        for(int i=0;i<Sistema.cantidadFacultades;i++){
            if(Sistema.facultades.isUsed(i)){
                cantidadFacultadesUsadas++;
            }
        }
        String result=Integer.toString(cantidadFacultadesUsadas);

        return result;
    }
    
    public String[][] getInfo(){
        String[][] result = new String[1][2];
        
        result [0][0]=Sistema.facultades.getCantidadFacultadesUsadas();
        result [0][1]="XD";

        return result;
    }
    
    public void instanciarFacultades(){
        addFacultad(new Facultad( "FACULTAD DE MEDICINA", "01"));
        addFacultad(new Facultad( "FACULTAD DE DERECHO Y CIENCIAS POLÍTICAS", "02"));
        addFacultad(new Facultad( "FACULTAD DE LETRAS Y CIENCIAS HUMANAS", "03"));
        addFacultad(new Facultad( "FACULTAD DE FARMACIA Y BIOQUÍMICA", "04"));
        addFacultad(new Facultad( "FACULTAD DE ODONTOLOGÍA", "05"));
        addFacultad(new Facultad( "FACULTAD DE EDUCACIÓN", "06"));
        addFacultad(new Facultad( "FACULTAD DE QUÍMICA E INGENIERÍA QUÍMICA", "07"));
        addFacultad(new Facultad( "FACULTAD DE MEDICINA VETERINARIA", "08"));
        addFacultad(new Facultad( "FACULTAD DE CIENCIAS ADMINISTRATIVAS", "09"));
        addFacultad(new Facultad( "FACULTAD DE CIENCIAS BIOLÓGICAS", "10"));
        addFacultad(new Facultad( "FACULTAD DE CIENCIAS CONTABLES", "11"));
        addFacultad(new Facultad( "FACULTAD DE CIENCIAS ECONÓMICAS", "12"));
        addFacultad(new Facultad( "FACULTAD DE CIENCIAS FÍSICAS", "13"));
        addFacultad(new Facultad( "FACULTAD DE CIENCIAS MATEMÁTICAS", "14"));
        addFacultad(new Facultad( "FACULTAD DE CIENCIAS SOCIALES", "15"));
        addFacultad(new Facultad( "FACULTAD DE INGENIERÍA GEOLÓGICA, MINERA, METALÚRGICA Y GEOGRÁFICA", "16"));
        addFacultad(new Facultad( "FACULTAD DE INGENIERIA INDUSTRIAL", "17"));
        addFacultad(new Facultad( "FACULTAD DE PSICOLOGÍA", "18"));
        addFacultad(new Facultad( "FACULTAD DE INGENIERÍA ELECTRÓNICA Y ELÉCTRICA", "19"));
        addFacultad(new Facultad( "FACULTAD DE INGENIERÍA DE SISTEMAS E INFORMÁTICA", "20"));
    }
}
