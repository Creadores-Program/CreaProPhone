package org.CreadoresProgram.CreaProPhone.update;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;

import org.CreadoresProgram.CreaProPhone.Main;

import org.json.me.JSONObject;
import org.json.me.JSONArray;

import java.io.InputStream;
public class GithubUpdate {
    private static String repoUrl = "https://api.github.com/repos/Creadores-Program/CreaProPhone/releases/latest";
    private static String latestVersion = "1.0.0";
    
    public static void checkForUpdates(Main main, boolean adNoUp) {
        HttpConnection conn = null;
        InputStream is = null;
        StringBuffer response = new StringBuffer();
        try {
            conn = (HttpConnection) Connector.open(repoUrl);
            conn.setRequestMethod(HttpConnection.GET);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Mobile Safari/537.36");
            conn.setRequestProperty("Accept", "application/vnd.github+json");
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpConnection.HTTP_OK) {
                is = conn.openInputStream();
                int ch;
                while ((ch = is.read()) != -1) {
                    response.append((char) ch);
                }
            }else{
                throw new Exception("Error al conectar con el servidor: " + responseCode);
            }
            JSONObject responsejson = new JSONObject(response.toString());
            if(responsejson.getString("tag_name").equals(latestVersion)){
                return;
            }
            JSONArray assetsGithub = responsejson.getJSONArray("assets");
            for(int i = 0; i < assetsGithub.length(); i++) {
                JSONObject asset = assetsGithub.getJSONObject(i);
                if(asset.getString("name").endsWith(".jar")){
                    Alert alert = new Alert("Actualización", "Nueva Actualizacion Disponible!\nPesa: "+(asset.getLong("size") / 1024 )+"kb\n"+responsejson.getString("name")+"\n"+responsejson.getString("body")+"Para actualizar entra a "+asset.getString("browser_download_url"), null, AlertType.INFO);
                    alert.setTimeout(Alert.FOREVER);
                    alert.setCommandListener(new CommandListener(){
                        public void commandAction(Command c, Displayable d) {
                            Display.getDisplay(main).setCurrent(main.mainMenu());
                        }
                    });
                    Display.getDisplay(main).setCurrent(alert);
                    return;
                }
            }
            if(adNoUp) return;
            Alert alert = new Alert("Actualización", "No se encontraron archivos de actualización disponibles.", null, AlertType.INFO);
            alert.setTimeout(Alert.FOREVER);
            alert.setCommandListener(new CommandListener(){
                public void commandAction(Command c, Displayable d) {
                    Display.getDisplay(main).setCurrent(main.mainMenu());
                }
            });
            Display.getDisplay(main).setCurrent(alert);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (is != null) is.close();
            if (conn != null) conn.close();
        }
    }
}