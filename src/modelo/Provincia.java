
package modelo;

/**
 *
 * @author axell
 */
public class Provincia {
    private String nombre;
    private Distrito [] distritos;
    private static int cantDepartamentos;
    
    
    
    
    public Provincia(String nombre, Distrito [] distritos) {
        this.nombre = nombre;
        this.distritos = distritos;
    }

    public String getNombre() {
        return nombre;
    }
    
    
    
}
