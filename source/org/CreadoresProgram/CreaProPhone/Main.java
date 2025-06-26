package org.CreadoresProgram.CreaProPhone;
import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.*;
import javax.microedition.rms.*;

import java.util.Random;

import org.json.me.JSONArray;
import org.json.me.JSONObject;

import org.CreadoresProgram.CreaProPhone.update.GithubUpdate;
import org.CreadoresProgram.CreaProPhone.IA.MaxIAManager;
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
    private TextBox tbEnviarChat;
    private Command cmdEnviarChatsalir;
    private Command cmdEnviarChatEnviar;
    private Command cmdconfigmodelSelectSalir;

    private String apiKey;
    public void startApp() {
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
        cmdconfigmodelSelectSalir = new Command("Salir", Command.BACK, 1);
        cmdconfigmodelSelect.addCommand(cmdconfigmodelSelectSalir);
        configForm.addCommand(cmdconfigSalir);
        configForm.addCommand(cmdconfigActuaKey);
        configForm.addCommand(cmdconfigActuaName);
        configForm.addCommand(cmdconfigmodelSelectCom);
        configForm.addCommand(cmdconfigBuscarAc);
        configForm.setCommandListener(this);
        cmdconfigmodelSelect.setCommandListener(this);
        tbEnviarChat = new TextBox("Enviar Chat", "Escribe tu mensaje:", 9000, TextField.ANY);
        cmdEnviarChatsalir = new Command("Salir", Command.BACK, 1);
        cmdEnviarChatEnviar = new Command("Enviar", Command.OK, 2);
        tbEnviarChat.addCommand(cmdEnviarChatsalir);
        tbEnviarChat.addCommand(cmdEnviarChatEnviar);
        tbEnviarChat.setCommandListener(this);
        Display.getDisplay(this).setCurrent(mainMenu);
        verifyConfig();
    }
    public void commandAction(Command c, Displayable d) {
        if(c == cmdmainConfig){
            Display.getDisplay(this).setCurrent(configForm);
        }else if(c == cmdmainEnviarChat){
            Display.getDisplay(this).setCurrent(tbEnviarChat);
        }else if(c == cmdmainBorrarChat){
            saveChat();
            MaxIAManager.clearChat();
            mainMenu.deleteAll();
            mainMenu.addCommand(cmdmainConfig);
            mainMenu.addCommand(cmdmainEnviarChat);
            mainMenu.addCommand(cmdmainBorrarChat);
            mainMenu.addCommand(cmdmainHistorial);
            mainMenu.addCommand(cmdmainSalir);
        }else if(c == cmdmainHistorial){
            Display.getDisplay(this).setCurrent(cmdHistorialchat);
        }else if(c == cmdmainSalir){
            notifyDestroyed();
        }else if(c == cmdconfigActuaKey){
            final TextBox tb = new TextBox("API Key", "Escribe tu API key:", 100, TextField.ANY);
            final Command cmdOk = new Command("Aceptar", Command.OK, 1);
            final Command cmdCancel = new Command("Cancelar", Command.CANCEL, 2);
            tb.addCommand(cmdOk);
            tb.addCommand(cmdCancel);
            tb.setCommandListener(new CommandListener(){
                public void commandAction(Command c, Displayable d) {
                    if (c == cmdOk) {
                        apiKey = ((TextBox)d).getString();
                        if (apiKey == null || apiKey.length() == 0) {
                            Alert alert = new Alert("Error", "Debes ingresar una API Key válida.", null, AlertType.ERROR);
                            alert.setTimeout(Alert.FOREVER);
                            alert.setCommandListener(new CommandListener() {
                                public void commandAction(Command c, Displayable d) {
                                    Display.getDisplay(Main.this).setCurrent(configForm);
                                }
                            });
                            Display.getDisplay(Main.this).setCurrent(alert);
                            return;
                        }
                        setItem("apiKey", apiKey);
                        verifyName();
                    }else{
                        Display.getDisplay(Main.this).setCurrent(configForm);
                    }
                }
            });
            Display.getDisplay(this).setCurrent(tb);
        }else if(c == cmdconfigActuaName){
            final TextBox tb = new TextBox("Nombre de Usuario", "Escribe tu nombre:", 20, TextField.ANY);
            final Command cmdOk = new Command("Aceptar", Command.OK, 1);
            final Command cmdCancel = new Command("Cancelar", Command.CANCEL, 2);
            tb.addCommand(cmdOk);
            tb.addCommand(cmdCancel);
            tb.setCommandListener(new CommandListener() {
                public void commandAction(Command c, Displayable d) {
                    if (c == cmdOk) {
                        String userName = ((TextBox)d).getString();
                        if (userName == null || userName.length() == 0 || userName.length() < 5) {
                            Alert alert = new Alert("Error", "El nombre debe tener entre 5 y 20 caracteres.", null, AlertType.ERROR);
                            alert.setTimeout(Alert.FOREVER);
                            alert.setCommandListener(new CommandListener() {
                                public void commandAction(Command c, Displayable d) {
                                    Display.getDisplay(Main.this).setCurrent(tb);
                                }
                            });
                            Display.getDisplay(Main.this).setCurrent(alert);
                            return;
                        }
                        setItem("userName", userName);
                        MaxIAManager.setUserName(userName);
                        verifyHystory();
                    } else {
                        Display.getDisplay(Main.this).setCurrent(configForm);
                        return;
                    }
                }
            });
            Display.getDisplay(this).setCurrent(tb);
        }else if(c == cmdconfigBuscarAc){
            new Thread(){
                public void run() {
                    GithubUpdate.checkForUpdates(Main.this, false);
                }
            }.start();
        }else if(c == cmdconfigmodelSelectCom){
            Display.getDisplay(this).setCurrent(cmdconfigmodelSelect);
        }else if(c == List.SELECT_COMMAND){
            if(d != cmdconfigmodelSelect){
                return;
            }
            int selectedIndex = cmdconfigmodelSelect.getSelectedIndex();
            MaxIAManager.setModel(selectedIndex);
        }else if(c == cmdconfigmodelSelectSalir){
            Display.getDisplay(this).setCurrent(configForm);
        }else if(c == cmdEnviarChatEnviar){
            String message = tbEnviarChat.getString();
            if (message == null || message.length() == 0) {
                return;
            }
            mainMenu.append("Tú: " + message + "\n");
            String response;
            try{
                response = MaxIAManager.promptGemini(message, apiKey);
                if (response == null || response.length() == 0) {
                    throw new Exception("No se pudo obtener una respuesta válida.");
                }
                mainMenu.append("CreaProPhone: " + response + "\n");
            }catch(Exception e){
                mainMenu.append("CreaProPhone: Lo Siento!, Mi Celebro da vueltas y no puedo responder a tu pregunta, por favor intenta de nuevo mas tarde.\n");
            }
            tbEnviarChat.setString("");
            Display.getDisplay(this).setCurrent(mainMenu);
        }else if(c == cmdEnviarChatsalir){
            Display.getDisplay(this).setCurrent(mainMenu);
        }else if(c == cmdconfigSalir){
            Display.getDisplay(this).setCurrent(mainMenu);
        }
    }
    public Form mainMenu(){
        return mainMenu;
    }
    private void setItem(String idkey, String value){
        try {
            RecordStore rs = RecordStore.openRecordStore(idkey, true);
            rs.addRecord(value.getBytes(), 0, value.getBytes().length);
            rs.closeRecordStore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getItem(String idkey){
        try{
            RecordStore rs = RecordStore.openRecordStore(idkey, false);
            RecordEnumeration re = rs.enumerateRecords(null, null, false);
            byte[] data = null;
            if (re.hasNextElement()) {
                int recordId = re.nextRecordId();
                data = rs.getRecord(recordId);
            }
            rs.closeRecordStore();
            return data != null ? new String(data) : null;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private void verifyConfig(){
        apiKey = getItem("apiKey");
        if(apiKey == null || apiKey.length() == 0) {
            final TextBox tb = new TextBox("API Key", "Escribe tu API key:", 100, TextField.ANY);
            final Command cmdOk = new Command("Aceptar", Command.OK, 1);
            final Command cmdCancel = new Command("Cancelar", Command.CANCEL, 2);
            tb.addCommand(cmdOk);
            tb.addCommand(cmdCancel);
            tb.setCommandListener(new CommandListener(){
                public void commandAction(Command c, Displayable d) {
                    if (c == cmdOk) {
                        apiKey = ((TextBox)d).getString();
                        if (apiKey == null || apiKey.length() == 0) {
                            Alert alert = new Alert("Error", "Debes ingresar una API Key válida.", null, AlertType.ERROR);
                            alert.setTimeout(Alert.FOREVER);
                            alert.setCommandListener(new CommandListener() {
                                public void commandAction(Command c, Displayable d) {
                                    Display.getDisplay(Main.this).setCurrent(tb);
                                }
                            });
                            Display.getDisplay(Main.this).setCurrent(alert);
                            return;
                        }
                        setItem("apiKey", apiKey);
                        verifyName();
                    }else{
                        Alert alert = new Alert("Error", "Debes ingresar una API Key válida.", null, AlertType.ERROR);
                        alert.setTimeout(Alert.FOREVER);
                        alert.setCommandListener(new CommandListener() {
                            public void commandAction(Command c, Displayable d) {
                                notifyDestroyed();
                            }
                        });
                        Display.getDisplay(Main.this).setCurrent(alert);
                    }
                }
            });
            Display.getDisplay(this).setCurrent(tb);
        }
    }
    private void verifyName() {
        String name = getItem("userName");
        if (name == null || name.length() == 0) {
            final TextBox tb = new TextBox("Nombre de Usuario", "Escribe tu nombre:", 20, TextField.ANY);
            final Command cmdOk = new Command("Aceptar", Command.OK, 1);
            final Command cmdCancel = new Command("Cancelar", Command.CANCEL, 2);
            tb.addCommand(cmdOk);
            tb.addCommand(cmdCancel);
            tb.setCommandListener(new CommandListener() {
                public void commandAction(Command c, Displayable d) {
                    if (c == cmdOk) {
                        String userName = ((TextBox)d).getString();
                        if (userName == null || userName.length() == 0 || userName.length() < 5) {
                            Alert alert = new Alert("Error", "El nombre debe tener entre 5 y 20 caracteres.", null, AlertType.ERROR);
                            alert.setTimeout(Alert.FOREVER);
                            alert.setCommandListener(new CommandListener() {
                                public void commandAction(Command c, Displayable d) {
                                    Display.getDisplay(Main.this).setCurrent(tb);
                                }
                            });
                            Display.getDisplay(Main.this).setCurrent(alert);
                            return;
                        }
                        setItem("userName", userName);
                        MaxIAManager.setUserName(userName);
                        verifyHystory();
                    } else {
                        Alert alert = new Alert("Error", "No se puede continuar sin el nombre.", null, AlertType.ERROR);
                        alert.setTimeout(Alert.FOREVER);
                        alert.setCommandListener(new CommandListener() {
                            public void commandAction(Command c, Displayable d) {
                                notifyDestroyed();
                            }
                        });
                        Display.getDisplay(Main.this).setCurrent(alert);
                        return;
                    }
                }
            });
            Display.getDisplay(this).setCurrent(tb);
        }else{
            MaxIAManager.setUserName(name);
            verifyHystory();
        }
    }
    private void verifyHystory() {
        String hystory = getItem("historyChats");
        if (hystory == null || hystory.length() == 0) {
            setItem("historyChats", "[]");
        }
        JSONArray historyArray;
        cmdHistorialchat = new List("Chats Anteriores", List.IMPLICIT);
        final Command cmdHistorialchatSalir = new Command("Salir", Command.BACK, 1);
        cmdHistorialchat.addCommand(cmdHistorialchatSalir);
        try{
            historyArray = new JSONArray(hystory);
            if (historyArray.length() == 0) {
                Alert alert = new Alert("Bienvenido", "No tienes chats guardados.", null, AlertType.INFO);
                alert.setTimeout(Alert.FOREVER);
                alert.setCommandListener(new CommandListener() {
                    public void commandAction(Command c, Displayable d) {
                        Display.getDisplay(Main.this).setCurrent(mainMenu);
                    }
                });
                cmdHistorialchat.setCommandListener(new CommandListener() {
                    public void commandAction(Command c, Displayable d) {
                        if (c == cmdHistorialchatSalir) {
                            Display.getDisplay(Main.this).setCurrent(mainMenu);
                        }
                    }
                });
                Display.getDisplay(this).setCurrent(alert);
            }else{
                for (int i = 0; i < historyArray.length(); i++) {
                    JSONObject chat = historyArray.getJSONObject(i);
                    cmdHistorialchat.append(chat.getString("name"), null);
                }
                final JSONArray historyArrayFinal = historyArray;
                cmdHistorialchat.setCommandListener(new CommandListener() {
                    public void commandAction(Command c, Displayable d) {
                        if (c == cmdHistorialchatSalir) {
                            Display.getDisplay(Main.this).setCurrent(mainMenu);
                        } else {
                            int index = cmdHistorialchat.getSelectedIndex();
                            try {
                                JSONObject chat = historyArrayFinal.getJSONObject(index);
                                MaxIAManager.setHistory(chat.getJSONArray("history"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                Display.getDisplay(this).setCurrent(mainMenu);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void saveChat(){
        try {
            JSONArray historyArray = new JSONArray(getItem("historyChats"));
            JSONArray chat = MaxIAManager.getHistory();
            if (chat.length() == 0) {
                return;
            }
            if (historyArray.length() > 10){
                Object first = historyArray.get(0);
                JSONArray newChat = new JSONArray();
                for (int i = 1; i < chat.length(); i++) {
                    newChat.put(historyArray.get(i));
                }
                historyArray = newChat;
            }
            JSONObject chatObject = new JSONObject();
            chatObject.put("name", System.currentTimeMillis()+" "+chat.getJSONObject(0).getJSONArray("parts").getJSONObject(0).getString("text")+" "+(new Random().nextInt()));
            chatObject.put("history", chat);
            historyArray.put(chatObject);
            setItem("historyChats", historyArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void pauseApp() {}
    public void destroyApp(boolean unconditional) {
        saveChat();
    }
}
