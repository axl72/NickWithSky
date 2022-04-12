package util;

import general.Sistema;

public class EmailArreglo {
    private Email[] emails;
    private int cantidadEmails;
    private boolean validado=false;
    
    public EmailArreglo() {
        this.emails=new Email[0];
        this.cantidadEmails=0;
                
    }
       
    public void addEmailIngresantes(Email e){
        Email[] aux = this.emails;
        this.emails = new Email[cantidadEmails+1];
        for(int i=0;i<cantidadEmails;i++){
            this.emails[i]=aux[i];
        }
        this.emails[cantidadEmails]=e;
        cantidadEmails++;
    }
    
    public void validarEnvioCorreoIngresantes(){
        int j=0;
        for(int i=0;i<Sistema.numeroRegistro;i++){
            if(!Sistema.VectorIngresantes[i].isError()){
                System.out.println("VALIDANDO2: "+this.emails[j].isVerificador());
                Sistema.VectorIngresantes[i].setCorreoEnviado(this.emails[j].isVerificador());
                j++;
            }
            
        }
        this.validado=true;
    }

    public boolean isValidado() {
        return validado;
    }
    
}
