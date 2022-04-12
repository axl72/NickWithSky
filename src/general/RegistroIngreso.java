package general;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.Trabajador;

public class RegistroIngreso implements Serializable{
    private boolean completado;
    private String sistemaOperativo;
    private Trabajador usuario;
    private String fechaIngreso;
    private String fechaSalida;
    private String horaSalida;
    private String horaIngreso;
    private Date horariosIngreso;
    private Date horariosSalida;

    public RegistroIngreso() {
        
        this.completado = false;
        this.sistemaOperativo = System.getProperty("os.name");
        this.usuario = Sistema.usuarioConectado;
        this.horariosIngreso = new Date();
        this.fechaIngreso = new SimpleDateFormat("dd/MM/yyyy").format(horariosIngreso);
        this.horaIngreso = new SimpleDateFormat("HH:mm:ss").format(horariosIngreso);
        this.fechaSalida = "Sin registrar";
        this.horaSalida = "Sin registrar";
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public Trabajador getUsuario() {
        return usuario;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public Date getHorariosIngreso() {
        return horariosIngreso;
    }

    public Date getHorariosSalida() {
        return horariosSalida;
    }

    
    public void setHorariosSalida(){
        this.horariosSalida = new Date();
    }
    
    public void setFechaSalida(){
        this.fechaSalida = new SimpleDateFormat("dd/MM/yyyy").format(horariosSalida);
    }
    
    public void setHoraSalida(){
        this.horaSalida = new SimpleDateFormat("HH:mm:ss").format(horariosSalida);
    }
    
    public void completarRegistro(){
        setHorariosSalida();
        setFechaSalida();
        setHoraSalida();
        this.completado = true;
    }
    
    @Override
    public String toString(){
        return "Usuario: " + usuario.getLogin() + "\tNombre: " + usuario.getPrimerNombre() + usuario.getApellidoPaterno() + "\tSistema operativo: " + this.sistemaOperativo + "\tFecha de ingreso: " + this.fechaIngreso + "\tHora ingreso: " + this.horaIngreso + "\tFechaSalida: " + this.fechaSalida + "\tHora salida: " + this.horaSalida; 
    }
    

    
}
