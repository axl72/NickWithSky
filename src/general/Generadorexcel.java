
package general;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Generadorexcel {
  
    private int TamañoCabecera;
   // GeneradorRegistrosTXT registrocontador = new GeneradorRegistrosTXT();

    public  void EscribirEXCEL() {
        String nombreArchivo = "ListaUsuarios.xlsx";
        
        String hoja = "Alumnos Registrados";
        
        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja1 = libro.createSheet(hoja);
      
      
        TamañoCabecera=Sistema.header.length;
        
        CellStyle style = libro.createCellStyle();
        Font font = libro.createFont();
        font.setBold(true);
        style.setFont(font);
        
        // Generar los datos para el documento
     
        for(int i = 0 ; i <= Sistema.numerodeAlumno; i++) {
           
            XSSFRow row = hoja1.createRow(i); // Se crea la fila
            for(int j = 0 ; j < TamañoCabecera ; j++) {
                if(i==0){ // Para la TamañoCabecera
                    XSSFCell cell = row.createCell(j); // Se crean las celdas para la cabecera
                    
                    cell.setCellValue(Sistema.header[j]);
                }else{
                    XSSFCell cell = row.createCell(j); // Se crean las celdas para el contenido
                    cell.setCellValue(Sistema.getDatosTabla()[i - 1][j]); // Se añade el contenido
                }
            }
            
        }
         for (int x = 0; x < hoja1.getRow(0).getPhysicalNumberOfCells(); x++) {
           hoja1.autoSizeColumn(x);
       }
         
       
        try (OutputStream fileOut = new FileOutputStream(Sistema.ruta.replace(".csv", "RegistroExcel"+Integer.toString(Sistema.NumeroReporteExcel)+".xlsx"))){
            System.out.println("Se ha generado el archivo .xlsx\n");
            System.out.println(Sistema.ruta);
            libro.write(fileOut);
            
           
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    

 
    
  
}
