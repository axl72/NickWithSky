
package general;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.Trabajador;


/**
 *
 * @author axell
 */
public class RegistroLogin implements Serializable{
    public static RegistroIngreso [] listaRegistros = new RegistroIngreso[0];
    public static String [][] matrizIngresos = new String[0][];
   
    public static String [] cabecera = {"Usuario", "Trabajador", "Dirección IP", "Sistema operativo", "Fecha ingreso", "Hora ingreso", "Fecha salida", "Hora salida"};
    
    public static void addRegistroIngreso(RegistroIngreso r) throws UnknownHostException{
        RegistroIngreso [] aux = listaRegistros;
        
        listaRegistros = new RegistroIngreso[listaRegistros.length+1];
        
        for(int i=0; i<aux.length; i++)
            listaRegistros[i] = aux[i];
        
        listaRegistros[listaRegistros.length-1] = r;
        addMatriz(r);
    }
    
    
    public static String imprimirRegistroLogin(){
      String x="";
        for( RegistroIngreso e : RegistroLogin.listaRegistros){
            x+=e+"\n";
            System.out.println(x);
            
            
        }
       
        return x;
    }
    
    public static void addMatriz(RegistroIngreso r) throws UnknownHostException{
        String [][] aux = matrizIngresos;
        
        matrizIngresos = new String[matrizIngresos.length+1][8];
        
        for(int i=0; i<aux.length; i++)
            matrizIngresos[i] = aux[i];
        
        matrizIngresos[matrizIngresos.length-1][0] = r.getUsuario().getLogin();
        matrizIngresos[matrizIngresos.length-1][1] = r.getUsuario().getPrimerNombre() + " " + r.getUsuario().getApellidoPaterno();
        matrizIngresos[matrizIngresos.length-1][2] = obtenerip() + "";
        matrizIngresos[matrizIngresos.length-1][3] = r.getSistemaOperativo();
        matrizIngresos[matrizIngresos.length-1][4] = r.getFechaIngreso() ;
        matrizIngresos[matrizIngresos.length-1][5] = r.getHoraIngreso();
        matrizIngresos[matrizIngresos.length-1][6] = r.getFechaSalida();
        matrizIngresos[matrizIngresos.length-1][7] = r.getHoraSalida();
    }
    
    public static void actualizarMatrizRegistros(){
        /*for(int i=0; i<matrizIngresos.length; i++){
            matrizIngresos[i][6] = listaRegistros[i].getFechaSalida();
            matrizIngresos[i][7] = listaRegistros[i].getHoraSalida();
        }*/
        
        matrizIngresos[matrizIngresos.length-1][6] = listaRegistros[listaRegistros.length-1].getFechaSalida();
        matrizIngresos[matrizIngresos.length-1][7] = listaRegistros[listaRegistros.length-1].getHoraSalida();
    }
    
    public static void serializarDatosLogin(){
        
        
        try{
            ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream("datosRegistroLogin"));
            fichero.writeObject(RegistroLogin.listaRegistros);
            fichero.close();
            System.out.println("Se creo datosRegistroLogin");
        }
        
        catch(Exception e){
            System.out.println("No se pudo serializar RegistroLogin");
        }
        
        try{
            ObjectOutputStream fichero2 = new ObjectOutputStream(new FileOutputStream("datosMatrizLogin"));
            fichero2.writeObject(RegistroLogin.matrizIngresos);
            fichero2.close();
            System.out.println("Se creo datosMatrizLogin");
        }
        
        catch(Exception e){
            System.out.println("No se pudo serializar la matriz RegistroLogin");
        }
    
    }
    
    public static void recuperarDatosLogin(){
        try{
            ObjectInputStream fichero1 = new ObjectInputStream(new FileInputStream("datosRegistroLogin"));
            RegistroLogin.listaRegistros = (RegistroIngreso []) fichero1.readObject();
            fichero1.close();
  
        }
        catch(Exception e){
            System.out.println("No se pudo recuperar RegistroLogin");
        }
        
        try{
            ObjectInputStream fichero2 = new ObjectInputStream(new FileInputStream("datosMatrizLogin"));
            RegistroLogin.matrizIngresos = (String [][]) fichero2.readObject();
            fichero2.close(); 
        }
        catch(Exception e){
            System.out.println("No se pudo recuperar matriz de RegistroLogin");
        }
    }
    
    //Prueba unitaria
    public static void main(String [] args) throws UnknownHostException{
        Sistema.usuarioConectado = new Trabajador("Axell", "Hernan", "Bernabel", "Mancilla", "axl", "12345", "axell.bernabel@unmsm.edu.pe");
        addRegistroIngreso(new RegistroIngreso());
        addRegistroIngreso(new RegistroIngreso());
        addRegistroIngreso(new RegistroIngreso());
        
        
        try{
            ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream("datosLogin"));
            fichero.writeObject(RegistroLogin.listaRegistros);
            fichero.close();
            

            ObjectInputStream fichero3 = new ObjectInputStream(new FileInputStream("datosLogin"));
            RegistroIngreso [] recuperado = (RegistroIngreso []) fichero3.readObject();
            fichero3.close();
            
            /*ObjectInputStream fichero4 = new ObjectInputStream(new FileInputStream("datosMatriz"));
            String [][] recuperado2 = (String [][]) fichero3.readObject();
            fichero4.close();*/
            
            for(int i=0; i<recuperado.length; i++)
                System.out.println(recuperado[i]);
            
            /*for(int j=0; j<recuperado2.length; j++){
                for(int k=0; k<recuperado2[0].length; k++){
                    System.out.print(recuperado[k] + "\t");
                }
                System.out.println();
            } */   
        }
        
        catch(Exception e){
            System.out.println("No se pudo serializar el objeto");
        }
        
        
        try{
            ObjectOutputStream fichero2 = new ObjectOutputStream(new FileOutputStream("datosMatriz"));
            fichero2.writeObject(RegistroLogin.matrizIngresos);
            fichero2.close();
            ObjectInputStream fichero3 = new ObjectInputStream(new FileInputStream("datosMatriz"));
            String [][] recuperado = (String [][]) fichero3.readObject();
            fichero3.close();
            
            for(int j=0; j<recuperado.length; j++){
                for(int k=0; k<recuperado[0].length; k++){
                    System.out.print(recuperado[j][k] + "\t");
                }
                System.out.println();
            }    
        }
        catch(Exception e){
            System.out.println("Ocurrio un error"); }}
    
    
    public static InetAddress obtenerip() throws UnknownHostException{
        InetAddress address = InetAddress.getLocalHost();
        return address;
    }
     
}




