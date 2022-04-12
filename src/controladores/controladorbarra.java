package controladores;

import general.Sistema;
import java.applet.AudioClip;
import java.awt.Color;
import javax.naming.spi.DirStateFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalProgressBarUI;
import vista.ProgressBar;

public class controladorbarra {
    private ProgressBar framebarra;
    
           AudioClip Sound;
    
   //////////////////////////////////////////////
    public controladorbarra(ProgressBar framebarra) {
        this.framebarra=framebarra;
        framebarra.setDefaultCloseOperation(framebarra.DO_NOTHING_ON_CLOSE);
        framebarra.setVisible(true);
        framebarra.setLocationRelativeTo(null); 
    }
    
      //////////////////////////////////////////////
    public void inciarbexcel(String RutaTemp){
  
     Thread hilo =new Thread(){
        @Override
        public void run(){
              Sound =java.applet.Applet.newAudioClip(getClass().getResource("/controladores/Nyan.wav"));
            Musicainiciar(Sound);
        try{
            for(int i=0; i<=100; i++){
                Thread.sleep(38);
                framebarra.procentaje.setText(Integer.toString(i)+"%");
                framebarra.barra.setValue(i);
                if(i==100){
                    MusicaStop(Sound);
                    framebarra.procentaje.setText("");
                    framebarra.procentaje.setText("ARCHIVO EXCEL GENERADO");
                    Thread.sleep(1000);
                    framebarra.dispose();  
                           JOptionPane.showMessageDialog(null, "Ruta del archivo excel : \n"+RutaTemp.replace("\\\\", "\\"), "Generado", 
                              JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/Imagenes/ruta.gif")));
                     
                }
            }
        }catch (Exception f){
            
        }}};
        hilo.start();
    }
    
      //////////////////////////////////////////////
    public void iniciarbtxt(String Rutatemp){
    
     Thread hilo =new Thread(){
        @Override
        public void run(){
            Sound =java.applet.Applet.newAudioClip(getClass().getResource("/controladores/Nyan.wav"));
            Musicainiciar(Sound);
        try{
            for(int i=0; i<=100; i++){
                Thread.sleep(20);
                framebarra.procentaje.setText(Integer.toString(i)+"%");
                framebarra.barra.setValue(i);
                if(i==100){
                    MusicaStop(Sound);
                    framebarra.procentaje.setText("");
                    framebarra.procentaje.setText("ARCHIVO TEXTO GENERADO");
                    Thread.sleep(1000);
                    framebarra.dispose();  
                   JOptionPane.showMessageDialog(null, "Ruta del archivo Txt : \n"+Rutatemp.replace("\\\\", "\\"), "Generado", 
                              JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/Imagenes/ruta.gif")));
                }
            }
        }catch (Exception f){
            
        }}};
        hilo.start(); 
    }
      //////////////////////////////////////////////
   public void Musicainiciar(AudioClip Sound){
      
    Sound.play();    }
   
   //////////////////////////////////////////////
   public void MusicaStop(AudioClip Sound){
          
    Sound.stop();}
}
