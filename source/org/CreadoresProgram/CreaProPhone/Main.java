package org.CreadoresProgram.CreaProPhone;
import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.*;

import org.CreadoresProgram.CreaProPhone.update.GithubUpdate;
public class Main extends MIDlet implements CommandListener {
    private Form mainMenu;
    private Command cmdmainConfig;
    private Command cmdmainEnviarChat;
    private Command cmdmainBorrarChat;
    private Command cmdmainHistorial;
    private Command cmdmainSalir;
    private Form configForm;
    private Command cmdconfigSalir;
    private Command cmdconfigActuaKey;
    private Command cmdconfigActuaName;
    private Command cmdconfigmodelSelectCom;
    private List cmdconfigmodelSelect;
    private Command cmdconfigBuscarAc;
    private List cmdHistorialchat;
    public void startApp() {
        //Archivos guardados
        //RecordStore
        new Thread(){
            public void run() {
                GithubUpdate.checkForUpdates(Main.this, true);
            }
        }.start();
        mainMenu = new Form("CreaProPhone");
        cmdmainConfig = new Command("Configuración", Command.SCREEN, 1);
        cmdmainEnviarChat = new Command("Enviar Chat", Command.SCREEN, 2);
        cmdmainBorrarChat = new Command("Borrar Chat", Command.SCREEN, 3);
        cmdmainHistorial = new Command("Historial", Command.SCREEN, 4);
        cmdmainSalir = new Command("Salir", Command.EXIT, 5);
        mainMenu.addCommand(cmdmainConfig);
        mainMenu.addCommand(cmdmainEnviarChat);
        mainMenu.addCommand(cmdmainBorrarChat);
        mainMenu.addCommand(cmdmainHistorial);
        mainMenu.addCommand(cmdmainSalir);
        mainMenu.setCommandListener(this);
        configForm = new Form("Configuración");
        cmdconfigSalir = new Command("Salir", Command.BACK, 1);
        cmdconfigActuaKey = new Command("Actualizar API Key de Gemini", Command.SCREEN, 2);
        cmdconfigActuaName = new Command("Actualizar Nombre", Command.SCREEN, 3);
        cmdconfigmodelSelectCom = new Command("Seleccionar Modelo de IA", Command.SCREEN, 4);
        cmdconfigmodelSelect = new List("Seleccionar Modelo de IA", List.IMPLICIT);
        cmdconfigmodelSelect.append("Gemini Flash 2.5", null);
        cmdconfigmodelSelect.append("Gemini Pro 2.5", null);
        cmdconfigmodelSelect.append("Gemini Flash 2.0", null);
        cmdconfigmodelSelect.append("Gemini Flash Lite 2.0", null);
        cmdconfigBuscarAc = new Command("Buscar Actualizaciones", Command.SCREEN, 5);
        configForm.addCommand(cmdconfigSalir);
        configForm.addCommand(cmdconfigActuaKey);
        configForm.addCommand(cmdconfigActuaName);
        configForm.addCommand(cmdconfigmodelSelectCom);
        configForm.addCommand(cmdconfigBuscarAc);
        configForm.setCommandListener(this);
        cmdconfigmodelSelect.setCommandListener(this);
        Display.getDisplay(this).setCurrent(mainMenu);
    }
    public void commandAction(Command c, Displayable d) {
    }
    public Form mainMenu(){
        return mainMenu;
    }
    public void pauseApp() {}
    public void destroyApp(boolean unconditional) {}
}