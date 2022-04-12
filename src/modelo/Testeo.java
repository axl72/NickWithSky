/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import general.Sistema;

/**
 *
 * @author axell
 */
public class Testeo {
    public static void main(String [] args){
        
        Sistema.addDepartamento(new Departamento("Amazonas", 1, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Ancash", 2, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Apurimac", 3, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Arequipa", 4, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Aycucho", 5, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Cajamarca", 6, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Callao", 7, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Cusco", 8, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Huancavelica", 9, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Huanuco", 10, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Ica", 11, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Junin", 12, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("La libertad", 13, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Lambayeque", 14, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Lima", 15, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Loreto", 16, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Madre de Dios", 17, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Moquegua", 18, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Pasco", 19, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Piura", 20, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Puno", 21, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("San Martin", 22, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Tacna", 23, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Tumbes", 24, new Provincia [0]));
        Sistema.addDepartamento(new Departamento("Ucayali", 25, new Provincia [0]));
        
        
        for(int i=0; i<Sistema.departamentos.length; i++)
            System.out.println(Sistema.departamentos[i].getNombre());
    }
    
}
