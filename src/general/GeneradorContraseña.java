package general;

import java.util.Arrays;
import java.util.Collections;

public class GeneradorContraseña {
    String contraseña;
    
    public GeneradorContraseña() {
    }

    
    public String aleatorio(String resultado, String palabras, int numero){
        String[] arrayPalabra = palabras.split("");
        int numeroAleatorio;
        for(int i=0;i<numero;i++){
            numeroAleatorio=(int)(Math.random()*(arrayPalabra.length-1)+0);
            resultado=resultado+arrayPalabra[numeroAleatorio];
        }
        this.contraseña=resultado;
        String arrayLetras[] =resultado.split("");
        Collections.shuffle(Arrays.asList(arrayLetras));
        resultado="";
        for(int i=0;i<arrayLetras.length;i++){
            resultado+=arrayLetras[i];
        }
        return resultado;
    }

    public String getResultado() {
        
        return contraseña;
    }
    
    
}
