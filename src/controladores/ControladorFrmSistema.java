package controladores;


import general.GeneradorTXT;
import general.Sistema;
import general.arrastrarysoltar;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import modelo.Alumno;
import general.GenerarCorreo;
import general.GenerarReporteTxT;
import general.Generarcodigo;
import general.LeerArchivoCSV;
import general.RegistroLogin;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import util.Email;
import vista.*;

public class ControladorFrmSistema {
    
    private FrmSistema frmSistema;
    private String RutaTemp; 
    private GenerarCorreo generarCorreo= new GenerarCorreo();
    private Generarcodigo generarCodigo=new Generarcodigo();
    private GenerarReporteTxT RepoGeneral=new GenerarReporteTxT();
    private ProgressBar vistabar=new ProgressBar();
    String Cabecera[]={"FACULTADES PROCESADAS", "ALUMNOS PROCESADOS","  ERRORES "};
    private boolean Btxt=false,Bexcel=false;
    private Email correo;
    private boolean correosEnviados=false;
    private FrmRegistroLogin registro;
    private FrmRegistroTrabajadores registro2;
    
    
    public ControladorFrmSistema(FrmSistema frmSistema){
        
        this.frmSistema = frmSistema;
        
        Dimension p = Toolkit.getDefaultToolkit().getScreenSize();
        FondoPanel fondo = new FondoPanel("/imagenes/fondoUNMSM.jpg");
        
      
        frmSistema.run();
        
        frmSistema.setBounds(400, 300, 1300, 600);
       
        this.frmSistema.txtUsuarioConectado.setText(Sistema.usuarioConectado.getPrimerNombre() + " " + Sistema.usuarioConectado.getApellidoPaterno());
        
        frmSistema.tblInformación.setEnabled(false);
        frmSistema.tblInformación.setDragEnabled(false);
        frmSistema.tblInformación.setTableHeader(null);
        this.frmSistema.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        funcionalidadBotones();
        
        funcionalidadBarraMenu();
        frmSistema.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                FrmLogin frmLogin = new FrmLogin();
                ControladorFrmLogin controladorFrmLogin = new ControladorFrmLogin(frmLogin);
                
                RegistroLogin.listaRegistros[RegistroLogin.listaRegistros.length-1].completarRegistro();
                RegistroLogin.actualizarMatrizRegistros(); 
                RegistroLogin.serializarDatosLogin();
            }
        });    
        
        this.frmSistema.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    JMenu sistema;
    
    
    private void funcionalidadBarraMenu(){
        

        //Instancia de menus de la ProgressBar
        sistema = new JMenu("SISTEMA");
        

        JMenuItem informacion = new JMenuItem("Información de sesiones");
        JMenuItem elemento2 = new JMenuItem("Registro de trabajadores");

        
        elemento2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(FrmRegistroTrabajadores.isDisponible()){
                    registro2 = new FrmRegistroTrabajadores();
                    ControladorFrmRegistroTrabajadores controlador = new ControladorFrmRegistroTrabajadores(registro2);
                    FrmRegistroTrabajadores.setDisponible(false);
                    controlador.iniciar();
                }
                
                else
                    Toolkit.getDefaultToolkit().beep();
            }
            
        });
        
        informacion.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(FrmRegistroLogin.isDisponible()){
                    registro = new FrmRegistroLogin();
                    ControladorFrmRegistroLogin controlador = new ControladorFrmRegistroLogin(registro);
                    FrmRegistroLogin.setDisponible(false);
                     controlador.iniciar();
                }
                
                else
                    Toolkit.getDefaultToolkit().beep();
                
                    
            }
        });
        
        
       
        //Agregando cada elemento;

        sistema.add(informacion);
        //sesion.add(cerrar);
        sistema.add(elemento2);
        
        frmSistema.menuBarra.add(sistema);
    }
       
    private void funcionalidadBotones(){
        frmSistema.bvalidar.setEnabled(false);
        
        
        //Boton cargar----------------------------------------------------------------------------------------------
        frmSistema.btnCargar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorFrmFile chooser =new ControladorFrmFile();
                  Boolean Flag = chooser.BuscarArchivo();
                
                if (Flag && Sistema.ruta.isEmpty()){
                frmSistema.arrastartysoltar.setBackground(Color.RED);
                frmSistema.arrastartysoltar.setText("SOLTAR ARCHIVO AQUI");
                }
               else  if(Flag){
                    JOptionPane.showMessageDialog(null, "ARCHIVO CARGADO");
                    frmSistema.btnCargar.setEnabled(false);
                    frmSistema.bvalidar.setEnabled(true);
                    frmSistema.arrastartysoltar.setText("");
                    frmSistema.arrastartysoltar.setBackground(Color.GREEN);
                    general.arrastrarysoltar ays= new arrastrarysoltar();
                    ays.GenerartablaCSV();
                    frmSistema.arrastartysoltar.setText("\tRuta: "+Sistema.ruta.replace("\\\\", "\\"));
                }
                else{
                   frmSistema.arrastartysoltar.setBackground(Color.RED);
                   frmSistema.arrastartysoltar.setText("SOLTAR ARCHIVO AQUI");
                JOptionPane.showMessageDialog(null, "ARCHIVO NO VALIDO \n \tSOLO  .CSV", "Error", 2);
                }
              

            }
        });
        
        //Boton unlogin---------------------------------------------------------------------------------------------------
        
        frmSistema.btnUnlogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Sistema.usuarioConectado = null;
                Sistema.i=-1;
                FrmLogin frmLogin = new FrmLogin();
                ControladorFrmLogin controladorFrmLogin = new ControladorFrmLogin(frmLogin);
                frmSistema.dispose();

                RegistroLogin.listaRegistros[RegistroLogin.listaRegistros.length-1].completarRegistro();
                RegistroLogin.actualizarMatrizRegistros();
                RegistroLogin.serializarDatosLogin();
                
            }
        });

        frmSistema.bvalidar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                
                for(int i=0; i<Sistema.numeroRegistro;i++){
                    //generarCorreo.Correo(Sistema.VectorIngresantes[Sistema.numerodeAlumno].getApellidoPaterno(),Sistema.VectorIngresantes[Sistema.numerodeAlumno].getPrimerNombre());
                    generarCorreo.Correo(Sistema.VectorIngresantes[Sistema.numerodeAlumno].getApellidoPaterno(),Sistema.VectorIngresantes[Sistema.numerodeAlumno].getPrimerNombre());
                    generarCodigo.codigo();
                    
                    Sistema.alumnos.addAlumno(new Alumno(generarCodigo.getCodigo(), generarCorreo.getCorreo(), generarCorreo.getContraseñafinal(), Sistema.VectorIngresantes[Sistema.numerodeAlumno].getPrimerNombre(), Sistema.VectorIngresantes[Sistema.numerodeAlumno].getSegundoNombre(), Sistema.VectorIngresantes[Sistema.numerodeAlumno].getApellidoPaterno(), Sistema.VectorIngresantes[Sistema.numerodeAlumno].getApellidoMaterno(), Sistema.VectorIngresantes[Sistema.numerodeAlumno].getFehcaNacimiento(), Sistema.VectorIngresantes[Sistema.numerodeAlumno].getEmail(), 'm', Sistema.VectorIngresantes[Sistema.numerodeAlumno].getDepartamento(), Sistema.VectorIngresantes[Sistema.numerodeAlumno].getResidenciaDepartamento(), Sistema.VectorIngresantes[Sistema.numerodeAlumno].getDistrito(), Sistema.VectorIngresantes[Sistema.numerodeAlumno].getDireccion(), Sistema.VectorIngresantes[Sistema.numerodeAlumno].getTelefonoCelular(), Sistema.VectorIngresantes[Sistema.numerodeAlumno].getAñoDeIngreso(), Sistema.VectorIngresantes[Sistema.numerodeAlumno].getDni()));
                    Sistema.vectorAlumno[Sistema.numerodeAlumno].verificarPersona();
                    
                    Sistema.numerodeAlumno++;
                    
                    
                    
                }
                
                if(Sistema.ruta.equals("")){
                    JOptionPane.showMessageDialog(null, "AGREGUE UN ARCHIVO");
                }
                else{
                    //JOptionPane.showConfirmDialog(null, "   ARCHIVO CARGADO  " + "\n"+"         "+Sistema.fechaactual,"",JOptionPane.DEFAULT_OPTION);
                    frmSistema.bgetxt.setEnabled(true);
                    frmSistema.bgeexcel.setEnabled(true);
                    frmSistema.bgreporte.setEnabled(true);
                    frmSistema.btnCargar.setEnabled(false);
                    frmSistema.bvalidar.setEnabled(false);
                    
                    frmSistema.txtError.setText("Se encontraron "+Sistema.cantidadErrores+" alumnos repetidos en el registro .csv");
                    frmSistema.txtError.setFont(new Font("Monospaced", Font.BOLD, 13));

                    frmSistema.tblInformación.setEnabled(true); 
                    DefaultTableModel modelotabla = new DefaultTableModel(Sistema.getInfoGeneral(), Cabecera);

                    frmSistema.tblInformación.setModel(modelotabla);
                    frmSistema.tblInformación.setAutoResizeMode(1);
                    frmSistema.tblInformación.setDefaultEditor(Object.class, null);
                }
                
                
                
        }       
                
        
    });
        
        frmSistema.bgetxt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!correosEnviados){
                for(int i=0;i<Sistema.numerodeAlumno;i++){
                    Email email=null;
                
                    
                        if(!Sistema.VectorIngresantes[i].isError()){
                        
                            
                            String mensaje="";
                            mensaje += "¡Su correo electrónico y código de alumno universitario de la UNMSM se ha generado!<br><br>";
                            mensaje += "<i><b>Código de alumno: <i>" + Sistema.vectorAlumno[i].getCodigo()+ "</font></b><br><br>";
                            mensaje += "<b><i>Correo Institucional: <i>" + Sistema.vectorAlumno[i].getCorreoInstitucional()+ "</font></b><br>";
                            mensaje += "<b><i>CONTRASEÑA: <i>" + Sistema.vectorAlumno[i].getContraseña()+ "</b></font><br>";

                            email =  new Email(Sistema.VectorIngresantes[i].getEmail(), "Plataforma UNMSM - Correos y Códigos de Alumno", mensaje);
                            Thread enviar = new Thread(email);
                            
                            System.out.println("Enviando correo a: "+Sistema.VectorIngresantes[i].getEmail());
                            
                            enviar.start();
                            Sistema.correosIngresantes.addEmailIngresantes(email);
                        }else{
                            System.out.println("No se pudo iniciar el envio de correo a: "+Sistema.VectorIngresantes[i].getPrimerNombre()+" "+Sistema.VectorIngresantes[i].getApellidoPaterno());
                        }
                        correosEnviados=true;
                    }
                }else{
                    System.out.println("Ya se inició el envío de correos a los ingresantes");
                }
                    
                System.out.println(Sistema.ruta);
                RutaTemp=Sistema.ruta.replace(".csv", "Registro ("+Sistema.NumeroReporteTXT+").txt");
                Btxt=true;
                System.out.println(RutaTemp);
                general.GeneradorTXT TXT = new GeneradorTXT();
                TXT.generarTxt(RutaTemp, vistabar);
                frmSistema.bgetxt.setEnabled(false);
                if(Btxt&&Bexcel){
                RepoGeneral.Generarreporte();
                }
                
            }
        });
        
        frmSistema.bgeexcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Email email = null;
                RutaTemp=Sistema.ruta.replace(".csv", "RegistroExcel ("+Sistema.NumeroReporteExcel+").xlsx");
                Bexcel=true;
                general.Generadorexcel escribir=new general.Generadorexcel();
                escribir.EscribirEXCEL();
                controladorbarra controladorb=new controladorbarra(vistabar);
                controladorb.inciarbexcel(RutaTemp);
                System.out.println(RutaTemp);
                 frmSistema.BtnOpenexcel.setEnabled(true);
                
                if(!correosEnviados){
                for(int i=0;i<Sistema.numerodeAlumno;i++){
                    
                    //System.out.println(Sistema.VectorIngresantes[i].isCorreoEnviado());
                    
                        if(!Sistema.VectorIngresantes[i].isError()){
                        
                            
                            String mensaje="";
                            mensaje += "¡Su correo electrónico y código de alumno universitario de la UNMSM se ha generado!<br><br>";
                            mensaje += "<i><b>Código de alumno: <i>" + Sistema.vectorAlumno[i].getCodigo()+ "</font></b><br><br>";
                            mensaje += "<b><i>Correo Institucional: <i>" + Sistema.vectorAlumno[i].getCorreoInstitucional()+ "</font></b><br>";
                            mensaje += "<b><i>CONTRASEÑA: <i>" + Sistema.vectorAlumno[i].getContraseña()+ "</b></font><br>";

                            email =  new Email(Sistema.VectorIngresantes[i].getEmail(), "Plataforma UNMSM - Correos y Códigos de Alumno", mensaje);
                            Thread enviar = new Thread(email);
                            
                            System.out.println("Enviando correo a: "+Sistema.VectorIngresantes[i].getEmail());
                            
                            enviar.start();
                            Sistema.correosIngresantes.addEmailIngresantes(email);
                        }else{
                            System.out.println("No se pudo iniciar el envio de correo a: "+Sistema.VectorIngresantes[i].getPrimerNombre()+" "+Sistema.VectorIngresantes[i].getApellidoPaterno());
                        }
                        correosEnviados=true;
                    }
                }else{
                    System.out.println("Ya se inició el envío de correos a los ingresantes");
                    
                }
                    
                
                
                frmSistema.bgeexcel.setEnabled(false);
                  if(Btxt&&Bexcel){
                RepoGeneral.Generarreporte();
                Sistema.NumeroReporteExcel++;//ACA ESTA EL ITERADOR DE LA CANTIDAD DE EXCEL GENERADOS123
                }
                //frmSistema.btnInformaciónIngresantes.setEnabled(true);
            }
        });
        
        frmSistema.bgreporte.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {                
                if(!Btxt&&Bexcel){
                    RepoGeneral.Generarreporte();
                  JOptionPane.showMessageDialog(null, textoennegritagood(), "Alerta", 
                              JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/Imagenes/ruta.gif")));
                    RepoGeneral.abrirreportegeneral();

                    /*if(!Sistema.correosIngresantes.isValidado()){
                        
                        Sistema.correosIngresantes.validarEnvioCorreoIngresantes();
                    }*/
                }
                else if(Btxt&&!Bexcel){
                    RepoGeneral.Generarreporte();
                      JOptionPane.showMessageDialog(null, textoennegritagood(), "Alerta", 
                              JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/Imagenes/ruta.gif")));
                    RepoGeneral.abrirreportegeneral();
                    
                    /*if(!Sistema.correosIngresantes.isValidado()){
                        Sistema.correosIngresantes.validarEnvioCorreoIngresantes();
                    }*/
            }
                 else if(Btxt && Bexcel){
                      JOptionPane.showMessageDialog(null, textoennegritabad(), "Alerta", 
                              JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/Imagenes/ruta.gif")));
                       
                        RepoGeneral.abrirreportegeneral();
                }
                
                frmSistema.btnValidarCorreos.setEnabled(true);
            }
        });
        
        frmSistema.BtnOpenexcel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            String file = new String (Sistema.ruta.replace(".csv", "RegistroExcel"+Integer.toString(Sistema.NumeroReporteExcel)+".xlsx"));
            try{ 
                Desktop.getDesktop().open(new File(file));
            }catch(IOException l){
                l.printStackTrace();
            }
                
        
            }
        });
        
        frmSistema.btnInformaciónIngresantes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmInformacionIngresante vistaI = new FrmInformacionIngresante();
                ControladorFrmInformaciónIngresante controlador2 = new ControladorFrmInformaciónIngresante(vistaI);
                controlador2.IniciarFrm();
            }
        });
       
        frmSistema.btnValidarCorreos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sistema.correosIngresantes.validarEnvioCorreoIngresantes();
                JOptionPane.showMessageDialog(null, "¡Correos electrónicos validados!");
                
                frmSistema.btnInformaciónIngresantes.setEnabled(true);
            }
        });
    }
    
    public void iniciar(){
        this.frmSistema.setTitle("UNMSM");
        general.arrastrarysoltar ays= new arrastrarysoltar(frmSistema);
        frmSistema.arrastartysoltar.setText("\t\t\tSoltar Achivo CSV aquí\t\t\t");
        frmSistema.arrastartysoltar.setEnabled(true);
        frmSistema.bgeexcel.setEnabled(false);
        frmSistema.bgetxt.setEnabled(false);
        frmSistema.bgreporte.setEnabled(false);
        frmSistema.BtnOpenexcel.setEnabled(false);
        frmSistema.btnInformaciónIngresantes.setEnabled(false);
        frmSistema.btnValidarCorreos.setEnabled(false);
        
    }
    

    
    class FondoPanel extends JPanel{
            private Image imagen;
            private String direccion;
            
            public FondoPanel(String direccion){
                this.direccion = direccion;
                this.imagen = new ImageIcon(getClass().getResource(direccion)).getImage();
            }

            public void paint(Graphics g){
                
                g.drawImage(this.imagen, 0, 0 , getWidth(), getHeight(), this);
                setOpaque(false);
                super.paint(g);
            }
    }
    public void jfile(){
        JFileChooser jf=new JFileChooser();
        jf.showOpenDialog(jf);
        File archivo=jf.getSelectedFile();
        System.out.println(archivo);
       /* System.out.println("xdddd");
    File archivoselecionado;
               JFileChooser we;
               we=new JFileChooser();
               we.showOpenDialog(null);
               archivoselecionado=we.getSelectedFile();
                System.out.println("asdasdas"+archivoselecionado);*/
    
    }
  public JLabel textoennegritagood() {
      String texto = "<html><b>Archivo Generado , Abriendo....</b></html>";
                   JLabel label = new JLabel(texto);
                    label.setFont(new Font("TimesRoman", Font.PLAIN, 16)); 
                    return label;
    }
    
       public JLabel textoennegritabad() {
      String texto = "<html><b>Ya se genero el archivo , Abriendo....</b></html>";
                   JLabel label = new JLabel(texto);
                    label.setFont(new Font("TimesRoman", Font.PLAIN, 16)); 
                    return label;
    }
     
    
}
