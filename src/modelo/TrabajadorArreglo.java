package modelo;

import general.Sistema;
import java.io.*;

public class TrabajadorArreglo {
    private Trabajador [] trabajadores;
    private int cantidadTrabajadores;
    private String [][] matriz ;
    private String [] cabecera = {"N°", "Nombre 1", "Nombre 2", "Apellido 1", "Apellido 2", "DNI", "Telefono", "Genero", "Fecha Nac.", "Dpt. Nac.", "Prov . Res.", "Dpt. Res.", "Prov. Res."};

    public TrabajadorArreglo() {
        this.trabajadores = new Trabajador[0];
        this.cantidadTrabajadores = 0;
        this.matriz = new String [0][14];
    }
    
    public TrabajadorArreglo(Trabajador [] trabajadores){
        this.trabajadores = trabajadores;
        this.cantidadTrabajadores = trabajadores.length;
        this.matriz = new String [0][13];
    }
    
    public Trabajador ingresar(String login, String contraseña){
        Trabajador trabajador = null;
        for(int i=0; i<cantidadTrabajadores ;i++){
            if(trabajadores[i].getLogin().equalsIgnoreCase(login) && trabajadores[i].getPassword().equals(contraseña)){    
                trabajador = this.trabajadores[i];
                this.trabajadores[i].setConectado(true);
                break;
            }
        }
        return trabajador;
    }
    
    public void addTrabajador(Trabajador t){
        
        Trabajador [] aux = trabajadores;
        trabajadores = new Trabajador [cantidadTrabajadores+1];
        for(int i=0; i<aux.length; i++){
            trabajadores[i] = aux[i];
        }
        trabajadores[cantidadTrabajadores] = t;
        cantidadTrabajadores++;
        addMatriz(t);
    }
    
    public void removeTrabajador(String login){
        
    }

    public void setTrabajadores(Trabajador[] trabajadores) {
        this.trabajadores = trabajadores;
        this.cantidadTrabajadores = trabajadores.length;
    }
    
    public boolean validarNuevoTrabajador(Trabajador t){
        boolean resultado = true;
        
        for(int i=0; i<trabajadores.length; i++)
            if(t.getDni().equals(trabajadores[i].getDni())){
                resultado = false;
                break;
            }
                
        
        return resultado;
    }

    public Trabajador[] getTrabajadores() {
        return trabajadores;
    }
    
    public void addMatriz(Trabajador t){
        String [][] aux = this.matriz;
        
        this.matriz = new String [matriz.length+1][13];
        
        for(int i=0; i<aux.length; i++){
            matriz[i][0] = String.valueOf(i+1);
            matriz[i][1] = trabajadores[i].getPrimerNombre();
            matriz[i][2] = trabajadores[i].getSegundoNombre();
            matriz[i][3] = trabajadores[i].getApellidoPaterno();
            matriz[i][4] = trabajadores[i].getApellidoMaterno();
            matriz[i][5] = trabajadores[i].getDni();
            matriz[i][6] = trabajadores[i].getTelefonoCelular();
            matriz[i][7] = trabajadores[i].getSexoDescripcion();
            matriz[i][8] = trabajadores[i].getFehcaNacimiento();
            matriz[i][9] = trabajadores[i].getDepartamento();
            matriz[i][10] = trabajadores[i].getProvinciaNac();
            matriz[i][11] = trabajadores[i].getResidenciaDepartamento();
            matriz[i][12] = trabajadores[i].getProvinciaRes();  
        }
        
        matriz[matriz.length-1][0] = String.valueOf(matriz.length);
            matriz[matriz.length-1][1] = t.getPrimerNombre();
            matriz[matriz.length-1][2] = t.getSegundoNombre();
            matriz[matriz.length-1][3] = t.getApellidoPaterno();
            matriz[matriz.length-1][4] = t.getApellidoMaterno();
            matriz[matriz.length-1][5] = t.getDni();
            matriz[matriz.length-1][6] = t.getTelefonoCelular();
            matriz[matriz.length-1][7] = t.getSexoDescripcion();
            matriz[matriz.length-1][8] = t.getFehcaNacimiento();
            matriz[matriz.length-1][9] = t.getDepartamento();
            matriz[matriz.length-1][10] = t.getProvinciaNac();
            matriz[matriz.length-1][11] = t.getResidenciaDepartamento();
            matriz[matriz.length-1][12] = t.getProvinciaRes();  
        
        
    }
    
    
    
    
    /*private int posTrabajador(String login){
        for(int i=0, i<cantidadTrabajadores; i++)
            if(login.equals(trabajadores[i].getLogin()))
    }*/
    
    public int length(){
        return trabajadores.length;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i=0; i<this.cantidadTrabajadores;i++){
                result += this.trabajadores[i] + "\n";
        }
        return result;
    }
    
    public void serializar(){
        try{
            ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream("serialTrabajadores"));
            fichero.writeObject(trabajadores);
            fichero.close(); 
            System.out.println("se creo serialTrabajadores");
        }
        
        catch(Exception e){
            System.out.println("No se pudo serializar ArregloTrabajadores");
        }
        
        try{
            ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream("serialMatrizTrabajadores"));
            fichero.writeObject(matriz);
            fichero.close();
            System.out.println("se creo serialMatrizTrabajadores");
        }
        catch(Exception e){
            System.out.println("No se pudo serializar matriz trabajadores");
        }
    }
    
    public void recuperarSerial(){
        try{
            ObjectInputStream fichero = new ObjectInputStream(new FileInputStream("serialTrabajadores"));
            setTrabajadores((Trabajador[]) fichero.readObject());
            fichero.close();
        }
        catch(Exception e){
            System.out.println("No se pudo recuperar el serial ArregloTrabajadores");
        }
        
        try{
            ObjectInputStream fichero = new ObjectInputStream(new FileInputStream("serialMatrizTrabajadores"));
            setMatriz((String [][]) fichero.readObject());
            fichero.close();
        }
        catch(Exception e){
            System.out.println("No se pudo recuperar el serial matrizTrabajadores");
        }
    }

    public String[][] getMatriz() {
        return matriz;
    }

    public String[] getCabecera() {
        return cabecera;
    }

    public void setMatriz(String[][] matriz) {
        this.matriz = matriz;
    }
    
}