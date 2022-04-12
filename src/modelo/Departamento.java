package modelo;

public class Departamento {
    private String nombre;
    private int ubigeo;
    private Provincia [] provincias;
    private static int cantDepartamentos;
    private int cantProvincias;
    

    public Departamento(String nombre, int ubigeo, Provincia[] provincias) {
        this.nombre = nombre;
        this.ubigeo = ubigeo;
        this.provincias = provincias;
        
    }

    public String getNombre() {
        return nombre;
    }

    public int getUbigeo() {
        return ubigeo;
    }

    public Provincia[] getProvincias() {
        return provincias;
    }

    public static int getCantDepartamentos() {
        return cantDepartamentos;
    }
    
    public void addProvincia(Provincia provincia){
        
        if(cantProvincias == provincias.length){
            Provincia [] aux = provincias;
            provincias = new Provincia [cantProvincias + 1];
        
            for(int i=0; i<aux.length; i++)
                provincias[i] = aux[i];
        
            provincias[cantProvincias] = provincia;
            cantProvincias++;
        }
        
        else
            for(int i=0; i<provincias.length; i++)
                if(provincias[i] == null){
                    provincias[i] = provincia;
                    break;
                }
 
    }
    
    public static void main(String [] args){
        Departamento departamento = new Departamento("Lima", 3, new Provincia [0]);
        Departamento departamentos2 = new Departamento("Cusco", 1, new Provincia [10]);
        
        departamento.addProvincia(new Provincia("Lima", new Distrito [0]));
        //departamentos2.addProvincia(new Provincia());
        
        System.out.println("provincias de Lima" + departamento.provincias.length);
        System.out.println("provincias de Cusco" + departamentos2.provincias.length);
       
    }
    

    
    
    
    
}
