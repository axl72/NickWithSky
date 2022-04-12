/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import vista.FrmLogin;
import controladores.*;
import general.Sistema;
import modelo.*;

/**
 *
 * @author axell
 */
public class TesteoFormularios {
    public static void main(String [] args){
        FrmLogin frmLogin = new FrmLogin();
        ControladorFrmLogin controladorFrmLogin = new ControladorFrmLogin(frmLogin);
        FrmRegistrarTrabajador formulario2 = new FrmRegistrarTrabajador();
        ControladorFrmRegistrar controlador2 = new ControladorFrmRegistrar(formulario2);
        FrmSistema frmSistema = new FrmSistema();
        ControladorFrmSistema controladorFrmSistema = new ControladorFrmSistema(frmSistema);
    }
    
}
