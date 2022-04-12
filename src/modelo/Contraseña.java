package modelo;

import general.GeneradorContraseña;

public class Contraseña {
    private int numeros;
    private String TextoNumeros, contraseña;
    private String Mayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    private int cantidadMayusculas = 4;
    private String Minusculas = "abcdefghijklmnopqrstuvwxyz";
    private int cantidadMinusculas = 2;
    private String Numeros = "1234567890";
    private int cantidadNumeros = 2;
    private int cantidadEspeciales=0;
    private String caracteresEsp ="+-,*/=%&#!?.}";
    GeneradorContraseña generador = new GeneradorContraseña();
    
    public String generarContraseña(){
        contraseña="";
        contraseña=generador.aleatorio(contraseña, Mayusculas, cantidadMayusculas);
        contraseña=generador.aleatorio(contraseña, Minusculas, cantidadMinusculas);
        contraseña=generador.aleatorio(contraseña, Numeros, cantidadNumeros);
        contraseña=generador.aleatorio(contraseña, caracteresEsp, cantidadEspeciales);
        return contraseña;
    }
    public Contraseña() {
    }
}
