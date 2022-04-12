
package modelo;

/**
 *
 * @author axell
 */
public class Ubigeo {
    private String ubigeo;
    private String nombre;

    public Ubigeo(String ubigeo, String nombre) {
        this.ubigeo = ubigeo;
        this.nombre = nombre;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

}
