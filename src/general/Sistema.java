package general;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import modelo.*;
import util.EmailArreglo;

public class Sistema implements Serializable{
    public static Trabajador usuarioConectado;
    public static FacultadArreglo facultades = new FacultadArreglo();
    public static TrabajadorArreglo trabajadores = new TrabajadorArreglo();
    public static Ingresante[] VectorIngresantes;
    public static Alumno[] vectorAlumno;
    public static String ruta="";
    public static IngresanteArreglo ingresantes = new IngresanteArreglo(10000);
    public static AlumnoArreglo alumnos = new AlumnoArreglo(10000);
    public static int cantidadTrabajadores = trabajadores.length();
    public static String AdminPass = "6969";
    public static int numerodeAlumno=0;//ME AYUDA A SABER CUANTOS ALUMNO HAY
    public static int numeroRegistro=0;//ME AYUDA A SABER CUANTOS ALUMNO HAY QUE REGISTRAR SE HAN LEIDO DEL ARCHIVO
    public static int homonimo=1;
    public static int cantidadAlumnos = alumnos.getLength();
    public static  LocalDate fechab=LocalDate.now();
    public static DateTimeFormatter f=DateTimeFormatter.ofPattern("dd"+"/"+"M"+"/"+"yyyy");
    public static String fechaactual=fechab.format(f);
    public static int NumeroIngresantes=0;
    public static int NumeroContador;
    public static int NumeroReporteExcel;
    public static int NumeroReporteTXT;
    public static int cantidadFacultades=0;
    public static int cantidadErrores=0;
    public static boolean verificadorCorreo;
    public static boolean recordar = false;
    public static String userRecordado;
    public static String passwordRecordado;
    public static int i=-1;
    public static EmailArreglo correosIngresantes = new EmailArreglo();
    
    public static String[] header = new String[] {"  NOMBRE   ", "   APELLIDO PATERNO  " ,"APELLIDO MATERNO" ,"  TELEFONO       ", "  CODIGO    ","CORREO INSTITUCIONAL                ","CONTRASEÑA", "     AÑO DE INGRESO   ","     DNI  "};
    public static int tamañoCabeceraReportes=header.length;
    
    public static String errores = "";
    
    public static Departamento [] departamentos = new Departamento [0];
    public static int cantDepartamentos = departamentos.length;
    
    public static void addDepartamento(Departamento departamento){
        Departamento [] aux = departamentos;
        departamentos = new Departamento [cantDepartamentos + 1];
        
        for(int i=0; i<cantDepartamentos; i++)
            departamentos[i] = aux[i];
        
        departamentos[cantDepartamentos] = departamento;
        cantDepartamentos++;
    }
    
    public static String[][] getInfoGeneral(){
        String [][] result= new String [1][3];
        for(int i=0;i<3;i++){
            result[0][0]=facultades.getCantidadFacultadesUsadas();
            result[0][1]=ingresantes.getInfoIngresantesActuales();
            result[0][2]=Integer.toString(cantidadErrores);
        }
        return result;
    } 
    
    public static String[][] getDatosTabla(){
        String[][] result = new String[Sistema.numerodeAlumno][Sistema.tamañoCabeceraReportes];
        int j=0;
        for(int i =0; i<Sistema.numerodeAlumno;i++){
            if (j<Sistema.numerodeAlumno){
                if(Sistema.vectorAlumno[j].isRepetido()){
                    j++;
                    if(j<Sistema.numerodeAlumno){
                        if(Sistema.vectorAlumno[j].isRepetido()){
                            j--;
                        }else{
                            result[i][0] = Sistema.vectorAlumno[j].getPrimerNombre();
                            result[i][1] = Sistema.vectorAlumno[j].getApellidoPaterno();
                            result[i][2] = Sistema.vectorAlumno[j].getApellidoMaterno();
                            result[i][3] = Sistema.vectorAlumno[j].getTelefonoCelular();
                            result[i][4] = Sistema.vectorAlumno[j].getCodigo();
                            result[i][5] = Sistema.vectorAlumno[j].getCorreoInstitucional();
                            result[i][6] = Sistema.vectorAlumno[j].getContraseña();
                            result[i][7] = Sistema.vectorAlumno[j].getAñoDeIngreso();
                            result[i][8] = Sistema.vectorAlumno[j].getDni();
                        }
                        
                    }
                }else{
                    result[i][0] = Sistema.vectorAlumno[j].getPrimerNombre();
                    result[i][1] = Sistema.vectorAlumno[j].getApellidoPaterno();
                    result[i][2] = Sistema.vectorAlumno[j].getApellidoMaterno();
                    result[i][3] = Sistema.vectorAlumno[j].getTelefonoCelular();
                    result[i][4] = Sistema.vectorAlumno[j].getCodigo();
                    result[i][5] = Sistema.vectorAlumno[j].getCorreoInstitucional();
                    result[i][6] = Sistema.vectorAlumno[j].getContraseña();
                    result[i][7] = Sistema.vectorAlumno[j].getAñoDeIngreso();
                    result[i][8] = Sistema.vectorAlumno[j].getDni();
                }
                j++;
            }
            
        }
        return result;
    }
    
    public static void instanciarDepartamentos(){
        
        //Instancia los departamentos y los añade al array [] departamentos
        
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
        
        //Instancia de las provincias y añadidas a sus respectivos departamentos
        
        departamentos[0].addProvincia(new Provincia("Chachapoyas", new Distrito [0]));
        departamentos[0].addProvincia(new Provincia("Bagua", new Distrito [0]));
        departamentos[0].addProvincia(new Provincia("Bongora", new Distrito [0]));
        departamentos[0].addProvincia(new Provincia("Condorcanqui", new Distrito [0]));
        departamentos[0].addProvincia(new Provincia("Luya", new Distrito [0]));
        departamentos[0].addProvincia(new Provincia("Rodrigues de Mendoza", new Distrito [0]));
        departamentos[0].addProvincia(new Provincia("Utcubamba", new Distrito [0]));
        
        departamentos[1].addProvincia(new Provincia("Chachapoyas", new Distrito [0]));
        departamentos[1].addProvincia(new Provincia("Huaraz", new Distrito [0]));
        departamentos[1].addProvincia(new Provincia("Aija", new Distrito [0]));
        departamentos[1].addProvincia(new Provincia("Antonio Raimondi", new Distrito [0]));
        departamentos[1].addProvincia(new Provincia("Asuncion", new Distrito [0]));
        departamentos[1].addProvincia(new Provincia("Bolognesi", new Distrito [0]));
        departamentos[1].addProvincia(new Provincia("Carhuaz", new Distrito [0]));
        departamentos[1].addProvincia(new Provincia("Carlor Fermin Firzcarrald", new Distrito [0]));
        departamentos[1].addProvincia(new Provincia("Casma", new Distrito [0]));
        departamentos[1].addProvincia(new Provincia("Corongo", new Distrito [0]));
        departamentos[1].addProvincia(new Provincia("Huari", new Distrito [0]));
        departamentos[1].addProvincia(new Provincia("Huarmey", new Distrito [0]));
        departamentos[1].addProvincia(new Provincia("Huaylas", new Distrito [0]));
        departamentos[1].addProvincia(new Provincia("Mariscal Luzuriaga", new Distrito [0]));
        departamentos[1].addProvincia(new Provincia("Ocros", new Distrito [0]));
        departamentos[1].addProvincia(new Provincia("Pallasca", new Distrito [0]));
        departamentos[1].addProvincia(new Provincia("Pomabamba", new Distrito [0]));
        departamentos[1].addProvincia(new Provincia("Recuay", new Distrito [0]));
        departamentos[1].addProvincia(new Provincia("Santa", new Distrito [0]));
        departamentos[1].addProvincia(new Provincia("Sihuas", new Distrito [0]));
        departamentos[1].addProvincia(new Provincia("Yungay", new Distrito [0]));
        
        departamentos[2].addProvincia(new Provincia("Abancay", new Distrito [0]));
        departamentos[2].addProvincia(new Provincia("Andahuaylas", new Distrito [0]));
        departamentos[2].addProvincia(new Provincia("Antabamba", new Distrito [0]));
        departamentos[2].addProvincia(new Provincia("Aymaraes", new Distrito [0]));
        departamentos[2].addProvincia(new Provincia("Cotabambas", new Distrito [0]));
        departamentos[2].addProvincia(new Provincia("Chincheros", new Distrito [0]));
        departamentos[2].addProvincia(new Provincia("Grau", new Distrito [0]));
        
        departamentos[3].addProvincia(new Provincia("Arequipa", new Distrito [0]));
        departamentos[3].addProvincia(new Provincia("Camana", new Distrito [0]));
        departamentos[3].addProvincia(new Provincia("Caraveli", new Distrito [0]));
        departamentos[3].addProvincia(new Provincia("Castilla", new Distrito [0]));
        departamentos[3].addProvincia(new Provincia("Caylloma", new Distrito [0]));
        departamentos[3].addProvincia(new Provincia("Condesuyos", new Distrito [0]));
        departamentos[3].addProvincia(new Provincia("Islay", new Distrito [0]));
        departamentos[3].addProvincia(new Provincia("La Union", new Distrito [0]));
        
        departamentos[4].addProvincia(new Provincia("Huamanga", new Distrito [0]));
        departamentos[4].addProvincia(new Provincia("Cangallo", new Distrito [0]));
        departamentos[4].addProvincia(new Provincia("Huanca Sancos", new Distrito [0]));
        departamentos[4].addProvincia(new Provincia("Huanta", new Distrito [0]));
        departamentos[4].addProvincia(new Provincia("La Mar", new Distrito [0]));
        departamentos[4].addProvincia(new Provincia("Lucanas", new Distrito [0]));
        departamentos[4].addProvincia(new Provincia("Parinacochas", new Distrito [0]));
        departamentos[4].addProvincia(new Provincia("Paucar del Sara Sara", new Distrito [0]));
        departamentos[4].addProvincia(new Provincia("Sucre", new Distrito [0]));
        departamentos[4].addProvincia(new Provincia("Victor Fajardo", new Distrito [0]));
        departamentos[4].addProvincia(new Provincia("Vilcashuaman", new Distrito [0]));
        
        departamentos[5].addProvincia(new Provincia("Cajamarca", new Distrito [0]));
        departamentos[5].addProvincia(new Provincia("Cajabamba", new Distrito [0]));
        departamentos[5].addProvincia(new Provincia("Celedin", new Distrito [0]));
        departamentos[5].addProvincia(new Provincia("Chota", new Distrito [0]));
        departamentos[5].addProvincia(new Provincia("Contumaza", new Distrito [0]));
        departamentos[5].addProvincia(new Provincia("Cutervo", new Distrito [0]));
        departamentos[5].addProvincia(new Provincia("Hualgayoc", new Distrito [0]));
        departamentos[5].addProvincia(new Provincia("Jaen", new Distrito [0]));
        departamentos[5].addProvincia(new Provincia("San Ignacio", new Distrito [0]));
        departamentos[5].addProvincia(new Provincia("San Marcos", new Distrito [0]));
        departamentos[5].addProvincia(new Provincia("San Miguel", new Distrito [0]));
        departamentos[5].addProvincia(new Provincia("San Pablo", new Distrito [0]));
        departamentos[5].addProvincia(new Provincia("Santa Cruz", new Distrito [0]));
        
        departamentos[6].addProvincia(new Provincia("Callao", new Distrito [0]));
        
        departamentos[7].addProvincia(new Provincia("Cuzco", new Distrito [0]));
        departamentos[7].addProvincia(new Provincia("Acomayo", new Distrito [0]));
        departamentos[7].addProvincia(new Provincia("Anta", new Distrito [0]));
        departamentos[7].addProvincia(new Provincia("Calca", new Distrito [0]));
        departamentos[7].addProvincia(new Provincia("Canas", new Distrito [0]));
        departamentos[7].addProvincia(new Provincia("Canchis", new Distrito [0]));
        departamentos[7].addProvincia(new Provincia("Chumbivilcas", new Distrito [0]));
        departamentos[7].addProvincia(new Provincia("Espinar", new Distrito [0]));
        departamentos[7].addProvincia(new Provincia("La Convencion", new Distrito [0]));
        departamentos[7].addProvincia(new Provincia("Paruro", new Distrito [0]));
        departamentos[7].addProvincia(new Provincia("Paucartambo", new Distrito [0]));
        departamentos[7].addProvincia(new Provincia("Quispicanchi", new Distrito [0]));
        departamentos[7].addProvincia(new Provincia("Urubamba", new Distrito [0]));
        
        departamentos[8].addProvincia(new Provincia("Huancavelica", new Distrito [0]));
        departamentos[8].addProvincia(new Provincia("Acobamba", new Distrito [0]));
        departamentos[8].addProvincia(new Provincia("Angaraes", new Distrito [0]));
        departamentos[8].addProvincia(new Provincia("Castrovirreyna", new Distrito [0]));
        departamentos[8].addProvincia(new Provincia("Churcampa", new Distrito [0]));
        departamentos[8].addProvincia(new Provincia("Huaytara", new Distrito [0]));
        departamentos[8].addProvincia(new Provincia("Tayacaja", new Distrito [0]));
        
        departamentos[9].addProvincia(new Provincia("Huanuco", new Distrito [0]));
        departamentos[9].addProvincia(new Provincia("Ambo", new Distrito [0]));
        departamentos[9].addProvincia(new Provincia("Dos de Mayo", new Distrito [0]));
        departamentos[9].addProvincia(new Provincia("Huacaybamba", new Distrito [0]));
        departamentos[9].addProvincia(new Provincia("Huamalies", new Distrito [0]));
        departamentos[9].addProvincia(new Provincia("Leoncio Prado", new Distrito [0]));
        departamentos[9].addProvincia(new Provincia("Marañon", new Distrito [0]));
        departamentos[9].addProvincia(new Provincia("Pachitea", new Distrito [0]));
        departamentos[9].addProvincia(new Provincia("Puerto Inca", new Distrito [0]));
        departamentos[9].addProvincia(new Provincia("Lauricocha", new Distrito [0]));
        departamentos[9].addProvincia(new Provincia("Yarowilca", new Distrito [0]));
        
        departamentos[10].addProvincia(new Provincia("Ica", new Distrito [0]));
        departamentos[10].addProvincia(new Provincia("Chincha", new Distrito [0]));
        departamentos[10].addProvincia(new Provincia("Nazca", new Distrito [0]));
        departamentos[10].addProvincia(new Provincia("Palpa", new Distrito [0]));
        departamentos[10].addProvincia(new Provincia("Pisco", new Distrito [0]));
        
        departamentos[11].addProvincia(new Provincia("Huancayo", new Distrito [0]));
        departamentos[11].addProvincia(new Provincia("Chanchamayo", new Distrito [0]));
        departamentos[11].addProvincia(new Provincia("Chupaca", new Distrito [0]));
        departamentos[11].addProvincia(new Provincia("Concepcion", new Distrito [0]));
        departamentos[11].addProvincia(new Provincia("Jauja", new Distrito [0]));
        departamentos[11].addProvincia(new Provincia("Junin", new Distrito [0]));
        departamentos[11].addProvincia(new Provincia("Satipo", new Distrito [0]));
        departamentos[11].addProvincia(new Provincia("Tarma", new Distrito [0]));
        departamentos[11].addProvincia(new Provincia("Yauli", new Distrito [0]));
        
        departamentos[12].addProvincia(new Provincia("Trujillo", new Distrito [0]));
        departamentos[12].addProvincia(new Provincia("Ascope", new Distrito [0]));
        departamentos[12].addProvincia(new Provincia("Bolivar", new Distrito [0]));
        departamentos[12].addProvincia(new Provincia("Chepen", new Distrito [0]));
        departamentos[12].addProvincia(new Provincia("Gran Chimu", new Distrito [0]));
        departamentos[12].addProvincia(new Provincia("Julcan", new Distrito [0]));
        departamentos[12].addProvincia(new Provincia("Otuzco", new Distrito [0]));
        departamentos[12].addProvincia(new Provincia("Pacasmayo", new Distrito [0]));
        departamentos[12].addProvincia(new Provincia("Pataz", new Distrito [0]));
        departamentos[12].addProvincia(new Provincia("Sanchez Carrion", new Distrito [0]));
        departamentos[12].addProvincia(new Provincia("Santiago de Chuco", new Distrito [0]));
        departamentos[12].addProvincia(new Provincia("Viru", new Distrito [0]));
        
        departamentos[13].addProvincia(new Provincia("Sanchez Chiclayo", new Distrito [0]));
        departamentos[13].addProvincia(new Provincia("Ferreñafe", new Distrito [0]));
        departamentos[13].addProvincia(new Provincia("Lambayeque", new Distrito [0]));
        
        departamentos[14].addProvincia(new Provincia("Lima", new Distrito [0]));
        departamentos[14].addProvincia(new Provincia("Barranca", new Distrito [0]));
        departamentos[14].addProvincia(new Provincia("Cajatambo", new Distrito [0]));
        departamentos[14].addProvincia(new Provincia("Canta", new Distrito [0]));
        departamentos[14].addProvincia(new Provincia("Cañete", new Distrito [0]));
        departamentos[14].addProvincia(new Provincia("Huaral", new Distrito [0]));
        departamentos[14].addProvincia(new Provincia("Huarochiri", new Distrito [0]));
        departamentos[14].addProvincia(new Provincia("Huaura", new Distrito [0]));
        departamentos[14].addProvincia(new Provincia("Oyon", new Distrito [0]));
        departamentos[14].addProvincia(new Provincia("Yauyos", new Distrito [0]));
        
        departamentos[15].addProvincia(new Provincia("Maynas", new Distrito[0]));
        departamentos[15].addProvincia(new Provincia("Alto Amazonas", new Distrito[0]));
        departamentos[15].addProvincia(new Provincia("Datem del Marañon", new Distrito[0]));
        departamentos[15].addProvincia(new Provincia("Loreto", new Distrito[0]));
        departamentos[15].addProvincia(new Provincia("Mariscal Ramon Castilla", new Distrito[0]));
        departamentos[15].addProvincia(new Provincia("Putumayo", new Distrito[0]));
        departamentos[15].addProvincia(new Provincia("Requena", new Distrito[0]));
        departamentos[15].addProvincia(new Provincia("Ucayali", new Distrito[0]));
        
        departamentos[16].addProvincia(new Provincia("Tambopata", new Distrito[0]));
        departamentos[16].addProvincia(new Provincia("Manu", new Distrito[0]));
        departamentos[16].addProvincia(new Provincia("Tahuamanu", new Distrito[0]));
        
        departamentos[17].addProvincia(new Provincia("Mariscal Nieto", new Distrito[0]));
        departamentos[17].addProvincia(new Provincia("General Sanchez Cerro", new Distrito[0]));
        departamentos[17].addProvincia(new Provincia("Ilo", new Distrito[0]));
        
        departamentos[18].addProvincia(new Provincia("Pasco", new Distrito[0]));
        departamentos[18].addProvincia(new Provincia("Daniel Alcides Carrion", new Distrito[0]));
        departamentos[18].addProvincia(new Provincia("Oxapampa", new Distrito[0]));
        
        departamentos[19].addProvincia(new Provincia("Piura", new Distrito[0]));
        departamentos[19].addProvincia(new Provincia("Ayabaca", new Distrito[0]));
        departamentos[19].addProvincia(new Provincia("Huancabamba", new Distrito[0]));
        departamentos[19].addProvincia(new Provincia("Morropón", new Distrito[0]));
        departamentos[19].addProvincia(new Provincia("Paita", new Distrito[0]));
        departamentos[19].addProvincia(new Provincia("Sechura", new Distrito[0]));
        departamentos[19].addProvincia(new Provincia("Sullana", new Distrito[0]));
        departamentos[19].addProvincia(new Provincia("Talara", new Distrito[0]));
        
        departamentos[20].addProvincia(new Provincia("Puno", new Distrito[0]));
        departamentos[20].addProvincia(new Provincia("Azangaro", new Distrito[0]));
        departamentos[20].addProvincia(new Provincia("Carabaya", new Distrito[0]));
        departamentos[20].addProvincia(new Provincia("Chucuito", new Distrito[0]));
        departamentos[20].addProvincia(new Provincia("El Collao", new Distrito[0]));
        departamentos[20].addProvincia(new Provincia("Huancane", new Distrito[0]));
        departamentos[20].addProvincia(new Provincia("Lampa", new Distrito[0]));
        departamentos[20].addProvincia(new Provincia("Melgar", new Distrito[0]));
        departamentos[20].addProvincia(new Provincia("Moho", new Distrito[0]));
        departamentos[20].addProvincia(new Provincia("San Antonio de Putina", new Distrito[0]));
        departamentos[20].addProvincia(new Provincia("San Roman", new Distrito[0]));
        departamentos[20].addProvincia(new Provincia("Sandia", new Distrito[0]));
        departamentos[20].addProvincia(new Provincia("Yunguyo", new Distrito[0]));
        
        departamentos[21].addProvincia(new Provincia("Moyobamba", new Distrito[0]));
        departamentos[21].addProvincia(new Provincia("Bellavista", new Distrito[0]));
        departamentos[21].addProvincia(new Provincia("El Dorado", new Distrito[0]));
        departamentos[21].addProvincia(new Provincia("Huallga", new Distrito[0]));
        departamentos[21].addProvincia(new Provincia("Lamas", new Distrito[0]));
        departamentos[21].addProvincia(new Provincia("Mariscal Caceres", new Distrito[0]));
        departamentos[21].addProvincia(new Provincia("Picota", new Distrito[0]));
        departamentos[21].addProvincia(new Provincia("Rioja", new Distrito[0]));
        departamentos[21].addProvincia(new Provincia("San Martin", new Distrito[0]));
        departamentos[21].addProvincia(new Provincia("Tocache", new Distrito[0]));
        
        departamentos[22].addProvincia(new Provincia("Tacna", new Distrito[0]));
        departamentos[22].addProvincia(new Provincia("Candarave", new Distrito[0]));
        departamentos[22].addProvincia(new Provincia("Jorge Basadre", new Distrito[0]));
        departamentos[22].addProvincia(new Provincia("Tarata", new Distrito[0]));
        
        departamentos[23].addProvincia(new Provincia("Tumbes", new Distrito[0]));
        departamentos[23].addProvincia(new Provincia("Contralmirante Villar", new Distrito[0]));
        departamentos[23].addProvincia(new Provincia("Zarumilla", new Distrito[0]));
        
        departamentos[24].addProvincia(new Provincia("Coronel Portillo", new Distrito[0]));
        departamentos[24].addProvincia(new Provincia("Atalaya", new Distrito[0]));
        departamentos[24].addProvincia(new Provincia("Padre Abad", new Distrito[0]));
        departamentos[24].addProvincia(new Provincia("Purus", new Distrito[0]));
        

    }
    
    public static boolean validarTextoNumerico(String string, int size){
        boolean resultado = true;
        
        for(int i=0; i<string.length(); i++)
            if((string.charAt(i) < 48 || string.charAt(i) > 57) || string.length() != size)
                resultado = false;
        
        return resultado;
    }
    
    public static boolean validarTextoAlfaNumerico(String string){
        boolean resultado = true;
        
        for(int i=0; i<string.length(); i++)
            if((string.charAt(i) < 48 || string.charAt(i) > 57) && (string.charAt(i) < 65 || string.charAt(i) > 90) && (string.charAt(i) < 97 || string.charAt(i) > 122) && (string.charAt(i) != 45 && string.charAt(i) != 46 && string.charAt(i) != 95))
                resultado = false;
        
        return resultado;
    }
    
    public static boolean validarCorreo(String correo){
        boolean resultado = false;
        
        int cantArrobas = 0;
        
        for(int i=0; i<correo.length(); i++)
            if(correo.charAt(i) == 64)
                cantArrobas++;
                
        if(cantArrobas == 1){
            boolean p1 = false;
            boolean p2 = false;
            String partes [] = new String [2];
        
            partes = correo.split("@");
        
            if(validarTextoAlfaNumerico(partes[0]))
                p1 = true;
        
            if(partes[1].equals("unmsm.edu.pe") || partes[1].equals("gmail.com") || partes[1].equals("hotmail.com"))
                p2 = true;
            
            if(p1 && p2)
                resultado = true;
        }

        return resultado;
    }
    
    public static boolean validarUserName(String usuario){
        boolean resultado = true;
        
        for(int i=0; i<trabajadores.length(); i++)
            if(usuario.equalsIgnoreCase(trabajadores.getTrabajadores()[i].getLogin())){
                resultado = false;
                break;
            }
        
        return resultado;
    }
    
    public static boolean validarCorreoRepetido(String correo){
        boolean resultado = true;
        for(Trabajador t: trabajadores.getTrabajadores())
            if(correo.equals(t.getEmail())){
                resultado = false;
                break;
            }
                
        
        return resultado;
    }
    
    
    public static void main(String [] args){
        System.out.println(validarCorreo("axell.bernabel@unmsm.edu.pe"));
    }
    
}

