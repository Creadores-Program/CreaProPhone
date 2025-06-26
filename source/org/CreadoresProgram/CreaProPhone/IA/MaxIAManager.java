package org.CreadoresProgram.CreaProPhone.IA;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import org.json.me.JSONObject;
import org.json.me.JSONArray;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
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
    private static String BaseDataIA = "Trollhunters501: Es un Youtuber con 8.64k de suscriptores, fundador de Creadores Program, Fundador de los servidores de minecraft bedrock llamados Creadores Survival, Creadores Games, GamerCR y el Arca Creadores (ahora desactivados indefinidamente), ha hecho colaboraciones con Piter0011, Hulk5008l, Vicmaster entre otros, Juega Minecraft, Craftsman, Roblox, CreadorCraft y Call of Duty Movile, Programador de JS, Python, Latino, EsJS, PHP y Java, sus redes sociales: TikTok: https://www.tiktok.com/@trollhunters501?_t=8nOOlYSiG7q&_r=1 Youtube: https://www.youtube.com/@Trollhunters501 Twitch: https://twitch.tv/trollhunters501 GitHub: https://github.com/Trollhunters501 Blog: https://creadoresgames.blogspot.com\n.\nCreadorCraft: Tu plataforma para crear y compartir juegos increíbles! ¡Bienvenido a CreadorCraft! Deja atrás la simple experiencia de juego y sumérgete en un universo de posibilidades donde tú eres el creador. Con CreadorCraft, no solo juegas, sino que construyes experiencias digitales únicas. Creado por Creadores Program, mas Info en https://creadorcraft.blogspot.com o juega en https://creadorcraftcp.blogspot.com/\n.\nCreadores Program es un equipo de desarrollo de software y videojuegos, fundado por Trollhunters501 en el 2017, con el objetivo de crear aplicaciones y juegos innovadores. más info en https://creadoresprogram.blogspot.com\n.\nCreaProBot(Tu hermano): un bot de chat interactivo para Discord y Guilded mas info en https://discord.gg/mrmHcwxXff\nCreaProDroid(Tu hermano): un asistente de ia para Android antiguo 4.2-8.1\nmas info en https://github.com/CreadoresProgram/CreaProDroid \n.\nCreaProPhone(Tu): un asistente de ia para J2ME, ya sabes tu info y tu version es 1.0.0";
    private static String gamesIA = "# Competencia de TV\nse trata de Fingir que estamos en un show de televisión de preguntas y respuestas, tu eres el presentador y debes preguntar cuantos participantes son, debes darles nombres aleatorios a cada participante que sean sin sentido como coco, lala, piña, etc.\ndebes hacer preguntas de cada tema como Literatura, Historia, Geografía, Matemáticas, Química, etc. Como Trivia, son 5 rondas, las preguntas deben ser diferentes para cada participante y al final se suman puntos por cada respuesta correcta y anuncias al ganador, las preguntas siempre deben ser de incisos a) a d),\nla pregunta va de 1 participante\n\n# CreadorCraft Chatventure\nun juego de Rol interactivo en chat!\n## Mecanica General:\n- Es un mundo abierto medieval\n- Sistema de Rol Simple: los jugadores podrian tener un nombre y podrian tener un inventario.\n- Puede ser multijugador en el mismo chat.\n- Acciones en el chat: Los jugadores interactuan escribiendo acciones en el chat como:\n     - [Nombre del Jugador] cava: Intentaría minar un bloque.\n     - [Nombre del Jugador] ataca al [Enemigo, entidad o jugador]: intentaria atacar una entidad o jugador.\n     - [Nombre del Jugador] tala: Tala el árbol mas cercano.\n     - [Nombre del Jugador] construye [Objeto]: puede construir un objeto (ej. mesa de crafteo, casa)\n     - [Nombre del Jugador] destruye [Objeto]: puede destruir un objeto y si aplica guarda los ítems u objetos en su inventario.\n- Eventos: El juego podría tener eventos programados o aleatorios, como la aparición de Enemigos, el descubrimiento de nuevos recursos, estructuras y mas, cambio de hora(mañana, tarde, noche) o cambio de clima(Lluvia, Soleado, Tormenta (hay una muy pequeña posibilidad que caiga un rayo a un jugador o entidad)).\n- Economia: Los jugadores pueden intercambiar cosas de su inventario entre ellos o también npc's de aldeas.\n- Sistema de Niveles: los jugadores podran subir de nivel aceptando Misiones de NPC's que esten en aldeas(si las descubren).\n- Sistema de Movimiento: los jugadores podran movense entre posiciones segun estructuras por ejemplo otra acción en el chat: [Nombre del Jugador] se va a Aldea del Desierto.\n## Ejemplo de Interacción:\nTrollhunters501 Cava\n>\nTrollhunters501 intenta cavar en la tierra... ¡Consigue 1 bloque de Tierra!\nElChinoMandarino tala\n>\nElChinoMandarino tala un árbol y Consigue 2 bloques de madera.\nSistema: Un Zombie se acerca!\nTrollhunters501 ataca al Zombie\n>\nTrollhunters501 ataca al Zombie... ¡El Zombie consigue 2 puntos de daño!\n## Extras\nCada 5 mensajes debes mostrar el estado de cada jugador Vida (barra de 0 a 10), Comida(barra de 0 a 10), Nivel\nPuedes expresarte mejor con Emojis por ejemplo Para bloques ⬜⬛🟫🟪🟦🟩🟨🟧🟥, Para Entidades 🧟‍♂️👻👿👹☠️👺☃️⛄🤖👽🎃😺🙉🦠🧌🧙🧛🥷🐯🐶🐺🐻🐻‍❄️🐨🐼🐹🐭🐰🦊🦝🐮🐷🐗🦓🐴🐉🦖🐸🐢🐊🐥🦅🦇🐬 etc.\nlos Jugadores pueden Consultar su Inventario.\n## Bloques e Items\ntu puedes añadir mas bloques e items que no esten en esta lista:\n- Tierra\n- Palo\n- Mesa de Craft\n- Hierro\n- Ruby\n- Carbon\n- Diamante\n- Oro\n- Hojas de árbol\n- Tronco de Madera\n- Arena\n- Piedra\n- Césped\n- Changesita\n- Lantano";

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
            InputStreamReader reader = new InputStreamReader(is, "UTF-8");
            int ch;
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            reader.close();
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
        long millis = System.currentTimeMillis();
        long totalSeconds = millis / 1000;
        long seconds = totalSeconds % 60;
        long totalMinutes = totalSeconds / 60;
        long minutes = totalMinutes % 60;
        long totalHours = totalMinutes / 60;
        long hours = totalHours % 24;
        long daySingleEpoch = millis / 86400000L;
        int dayOfWeek = (int) ((daySingleEpoch + 4) % 7);
        systemPart4.put("text", "**Contexto Adicional:**\n1. **Fecha y Hora Actual:** milisegundos(Convierte lo que falta con esto): "+millis+" dia de la semana: " + dayOfWeek + " (el domingo es 0, el lunes es 1, el martes es 2, el miercoles es 3, el jueves es 4, el viernes es 5, el sábado es 6) hora actual: " +hours+":"+minutes+":"+seconds+"\n2. **Idioma de Usuario:**"+((System.getProperty("microedition.locale") == null) ? "desconocido" : System.getProperty("microedition.locale"))+"\n3. **Plataforma:**"+((System.getProperty("microedition.platform") == null) ? "desconocido" : System.getProperty("microedition.platform"))+"\n4. **No uses Markdown porque no existe un parcheador de markdown para J2ME usa Texto Plano**\n5. **Juegos para Jugar con el Usuario Adicionales:** Considera estas Instucciones de Juegos como juegos que puede jugar el Usuario contigo: "+gamesIA+"\n6.  **Base de Conocimientos Adicional:** Considera estos datos como parte de tu información: "+BaseDataIA);
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
