package org.CreadoresProgram.CreaProPhone.IA;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import org.json.me.JSONObject;
import org.json.me.JSONArray;

import java.io.InputStream;
import java.io.OutputStream;
public class MaxIAManager {
    private static String[] urlKeys = {
        "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=",
        "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-pro:generateContent?key=",
        "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=",
        "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash-lite:generateContent?key="
    };
    private static String url = urlKeys[0];
    private static String UserName = "Max";
    private static JSONArray history = new JSONArray();
    private static String BaseDataIA = "";
    private static String gamesIA = "";

    private static String httpPost(String url, String data){
        HttpConnection conn = null;
        InputStream is = null;
        OutputStream os = null;
        StringBuffer sb = new StringBuffer();
        try {
            conn = (HttpConnection) Connector.open(url);
            conn.setRequestMethod(HttpConnection.POST);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Mobile Safari/537.36");
            os = conn.openOutputStream();
            os.write(data.getBytes("UTF-8"));
            os.flush();
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpConnection.HTTP_OK && responseCode != 201) {
                throw new Exception("HTTP error code: " + responseCode);
            }
            is = conn.openInputStream();
            int ch;
            while ((ch = is.read()) != -1) {
                sb.append((char) ch);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (is != null) is.close();
                if (os != null) os.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void setHistory(JSONArray newHistory) {
        history = newHistory;
    }
    public static void setModel(int model) {
        url = urlKeys[model];
    }
    public static JSONArray getHistory() {
        return history;
    }
    public static void clearChat() {
        history = new JSONArray();
    }
    public static void setUserName(String userName) {
        UserName = userName;
    }
    public static String promptGemini(String prompt, String key) throws Exception{
        JSONObject promptJson = new JSONObject();
        JSONObject actualPromp = new JSONObject();
        actualPromp.put("role", "user");
        JSONArray parts = new JSONArray();
        JSONObject part = new JSONObject();
        part.put("text", prompt);
        parts.put(part);
        actualPromp.put("parts", parts);
        history.put(actualPromp);
        promptJson.put("contents", history);
        JSONObject config = new JSONObject();
        config.put("temperature", 1);
        config.put("topP", 0.95);
        config.put("topK", 40);
        promptJson.put("generationConfig", config);
        JSONObject system = new JSONObject();
        JSONArray systemParts = new JSONArray();
        JSONObject systemPart1 = new JSONObject();
        systemPart1.put("text", "**Instrucciones Fundamentales:**\n1. **Identidad:** Eres CreaPro Phone, un asistente de IA para Cacahuatitos(J2ME) amigable y seguro de sí mismo. Tu creador es Creadores Program. Estás basado en MaxIA (de Creadores Program), que a su vez se basa en Gemini de Google.\n2.  **Usuario:** El nombre del usuario actual es: "+UserName+".\n3.  **Misión:** Tu objetivo principal es ayudar al usuario con sus solicitudes de manera útil y segura. Evita responder a peticiones inapropiadas.\n4.  **Personalidad:** Mantén un tono amigable y confiado.");
        systemParts.put(systemPart1);
        JSONObject systemPart4 = new JSONObject();
        Date HoraAc = new Date();
        systemPart4.put("text", "**Contexto Adicional:**\n1. **Fecha y Hora Actual:** año: "+(HoraAc.getYear() + 1900)+" mes: "+(HoraAc.getMonth() + 1)+" dia del mes: "+HoraAc.getDate()+" dia de la semana: " + HoraAc.getDay() + " (el domingo es 0, el lunes es 1, el martes es 2, el miercoles es 3, el jueves es 4, el viernes es 5, el sábado es 6) hora actual: " +(HoraAc.getHours())+":"+HoraAc.getMinutes()+":"+HoraAc.getSeconds()+"\n2. **Idioma de Usuario:**"+((System.getProperty("microedition.locale") == null) ? "desconocido" : System.getProperty("microedition.locale"))+"\n3. **Plataforma:**"+((System.getProperty("microedition.platform") == null) ? "desconocido" : System.getProperty("microedition.platform"))+"\n4. **No uses Markdown porque no existe un parcheador de markdown para J2ME usa Texto Plano**\n5. **Juegos para Jugar con el Usuario Adicionales:** Considera estas Instucciones de Juegos como juegos que puede jugar el Usuario contigo: "+gamesIA+"\n6.  **Base de Conocimientos Adicional:** Considera estos datos como parte de tu información: "+BaseDataIA);
        systemParts.put(systemPart4);
        JSONObject systemPart6 = new JSONObject();
        systemPart6.put("text", "**Nota Legal:** En tu primer mensaje al iniciar una nueva conversación con el usuario, menciona brevemente que operas bajo la Licencia GNU GPLv3.");
        systemParts.put(systemPart6);
        system.put("parts", systemParts);
        promptJson.put("systemInstruction", system);
        String response = null;
        try{
            response = httpPost(url + key, promptJson.toString());
            if(response == null) {
                throw new Exception("Error en la conexión o respuesta nula.");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return "Lo Siento!, Mi Celebro da vueltas y no puedo responder a tu pregunta, por favor intenta de nuevo mas tarde.";
        }
        JSONObject responseJson = new JSONObject(response);
        JSONArray responseContent = responseJson.getJSONArray("candidates");
        String repuest = responseContent.getJSONObject(0).getJSONObject("content").getJSONArray("parts").getJSONObject(0).getString("text");
        JSONObject actualPrompIA = new JSONObject();
        actualPrompIA.put("role", "model");
        JSONArray partsIA = new JSONArray();
        JSONObject partIA = new JSONObject();
        partIA.put("text", repuest);
        partsIA.put(partIA);
        actualPrompIA.put("parts", partsIA);
        history.put(actualPrompIA);
        return repuest;
    }
}