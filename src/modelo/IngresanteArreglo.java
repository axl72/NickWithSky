package modelo;

import general.Sistema;

public class IngresanteArreglo {
    private Ingresante[] ingresantes;
    private int indice;
    
    public IngresanteArreglo(int tamaño){
        this.ingresantes = new Ingresante[tamaño];
        this.indice = 0;
    }
    
    public boolean add(Ingresante i){
        boolean result = false;
        if(!isLleno()){
            this.ingresantes[this.indice] = i;
            this.indice++;
            procesoderegistro();
            result = true;
        }
        return result;
    }
    private boolean isLleno() {
        if(this.indice == this.ingresantes.length){
            return true;
        } else {
            return false;
        }
    }
    
    private void procesoderegistro(){
        Sistema.VectorIngresantes=ingresantes; 
    }
    
    public void limpiarregistro(){
        for(int i=0;i<Sistema.numeroRegistro;i++){
            ingresantes [i]=new Ingresante("", "", "", "", "", "", "", "", ' ', "", "", "", "", "","","");
        }
    }
    
    public String getInfoIngresantesTotales(){
        return Integer.toString(Sistema.NumeroContador);
    }
    
    public String getInfoIngresantesActuales(){
        return Integer.toString(Sistema.numeroRegistro);
    }
    
    public int GetLenght(){
        int Lenght=ingresantes.length;
        return Lenght;
        
    }
    
    public String getDni(int i){
        String dni="";
        ingresantes[i].getDni();
        return dni;
    }
    


    @Override
    public String toString() {
        return "IngresanteArreglo{" + "ingresantes=" + ingresantes + '}';
    }

    
}