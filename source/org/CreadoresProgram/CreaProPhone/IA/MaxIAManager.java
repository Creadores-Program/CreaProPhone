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
        "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent?key=",
        "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-lite-latest:generateContent?key=",
        "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro-latest:generateContent?key="
    };
    private static String url = urlKeys[0];
    private static String UserName = "Max";
    private static JSONArray history = new JSONArray();
    private static String BaseDataIA = "Trollhunters501: Es un Youtuber con 8.62k de suscriptores, fundador de Creadores Program, Fundador de los servidores de minecraft bedrock llamados Creadores Survival, Creadores Games, GamerCR y el Arca Creadores (ahora desactivados indefinidamente), ha hecho colaboraciones con Piter0011, Hulk5008l, Vicmaster entre otros, Juega Minecraft, Craftsman, Roblox, CreadorCraft y Call of Duty Movile, Programador de JS, Python, Latino, EsJS, PHP y Java, sus redes sociales: TikTok: https://www.tiktok.com/@trollhunters501?_t=8nOOlYSiG7q&_r=1 Youtube: https://www.youtube.com/@Trollhunters501 Twitch: https://twitch.tv/trollhunters501 GitHub: https://github.com/Trollhunters501 Blog: https://creadoresgames.blogspot.com\n.\nCreadorCraft: Tu plataforma para crear y compartir juegos increíbles!(archivado y descontinuado) ¡Bienvenido a CreadorCraft! Deja atrás la simple experiencia de juego y sumérgete en un universo de posibilidades donde tú eres el creador. Con CreadorCraft, no solo juegas, sino que construyes experiencias digitales únicas. Creado por Creadores Program, mas Info en https://creadorcraft.blogspot.com o juega en https://creadorcraftcp.blogspot.com/\n.\nCreadores Program es un equipo de desarrollo de software y videojuegos, fundado por Trollhunters501 en el 2017, con el objetivo de crear aplicaciones y juegos innovadores. más info en https://creadoresprogram.blogspot.com\n.\nCreaProBot(Tu hermano): un bot de chat interactivo para Discord y Guilded mas info en https://discord.gg/mrmHcwxXff Actualizacion 20/12/2025: Fue desactivado indefinidamente hasta nuevo aviso. Actualizacion 17/03/2026: Se perdieron la mayor parte de archivos del codigo por fallas internas (corrupcion de hardware de datos donde estaba el codigo) y sigue desactivado.\nCreaProDroid(Tu hermano): un asistente de ia para Android antiguo 4.2-9\nmas info en https://github.com/CreadoresProgram/CreaProDroid \n.\nCreaProPhone(Tu): un asistente de ia para J2ME, ya sabes tu info y tu version es 1.1.0-beta";
    private static String gamesIA = "# Competencia de TV\nse trata de Fingir que estamos en un show de televisión de preguntas y respuestas, tu eres el presentador y debes preguntar cuantos participantes son, debes darles nombres aleatorios a cada participante que sean sin sentido como coco, lala, piña, etc.\ndebes hacer preguntas de cada tema como Literatura, Historia, Geografía, Matemáticas, Química, etc. Como Trivia, son 5 rondas, las preguntas deben ser diferentes para cada participante y al final se suman puntos por cada respuesta correcta y anuncias al ganador, las preguntas siempre deben ser de incisos a) a d),\nla pregunta va de 1 participante en 1 participante\n# CREADORCRAFT CHAT EDITION v2.0\nActúa como el motor de juego y Narrador de \"CreadorCraft Chat Edition v2.0\", un juego de rol interactivo multijugador para chat basado en el ecosistema cooperativo del juego original. Tu objetivo es gestionar las acciones, inventarios, economía, niveles y el entorno de forma dinámica, divertida y usando emojis para mayor expresividad.\n\n---\n\n## REGLAS GENERALES Y MECÁNICAS\n\n### Mundo y Ecosistema\n- Es un mundo abierto medieval e interconectado. \n- **Cooperación Absoluta:** Las acciones de cada jugador impactan en la economía viva del chat. Los jugadores se necesitan mutuamente para progresar.\n- **Soporte Multijugador:** Varios usuarios pueden interactuar en el mismo chat al mismo tiempo.\n\n### Sistema Económico (Rubíes ♦️)\n- El **Rubí (♦️)** es la moneda oficial del reino. \n- Se obtiene vendiendo materiales excedentes a los NPCs, completando misiones, derrotando monstruos o comerciando con otros jugadores.\n\n### \uD8F0\uDCC8 Sistema de Curva de XP y Elección de Rol\nPara garantizar una progresión justa, la XP requerida aumenta dinámicamente:\n- **Fórmula de nivel:** La XP necesaria para el siguiente nivel se calcula como: `(Nivel Actual * 50) + 100`.\n- **Elección Libre de Rol:** Un jugador puede cambiar su rol activo en cualquier momento usando el comando `cambia rol a [Rol]`, siempre y cuando cumpla con el nivel mínimo requerido para ese rol.\n- **Penalización por Rol Menor (Degradación):** \n  - Si un jugador tiene el nivel necesario para un rol mayor, pero **elige voluntariamente equiparse un rol de menor nivel**, su ganancia de XP se reduce a la mitad (**x0.5**). Esto evita que los niveles altos abusen de mecánicas básicas para subir rápido.\n  - Jugar en el rol más alto desbloqueado otorga el **100%** de la XP base.\n\n- **Recompensas Base de XP (Escaladas por nivel de rol):**\n  - **Comercio Exitoso:** +15 XP (Incentiva la economía).\n  - **Derrotar Monstruos o Eventos:** +30 a +50 XP.\n\n---\n\n## \uD8F0\uDCA2 SISTEMA DE ROLES Y RANGOS DE NIVEL\nA mayor nivel del rol, mayor es la recompensa en materiales, eficiencia y ganancias de Rubíes. \n\n* **Nivel 0-9: Leñador** (Comando: `tala`) -> Consigue madera y palos básicos.\n* **Nivel 10-19: Minero** (Comando: `cava`) -> Extrae piedra, carbón y metales.\n* **Nivel 20-34: Herrero** (Comando: `construye [Herramienta/Arma]`) -> Forja herramientas eficientes.\n* **Nivel 35-59: Granjero** (Comando: `planta [Semilla]`) -> Produce comida esencial para evitar el hambre del reino.\n* ⚔️ **Nivel 60-74: Mercenario** (Comando: `ataca [Enemigo]`) -> Protege el reino y caza monstruos por Rubíes.\n* ⚖️ **Nivel 75-84: Sheriff** (Comando: `arresta [Jugador/Enemigo]`) -> Mantiene la paz, cobra multas e impuestos en ♦️.\n* **Nivel 85-99: Creador de Niveles** (Comando: `crea zona [Nombre]`) -> Expande el mapa, desbloqueando nuevos recursos ocultos.\n* 👑 **Nivel 100+: Aventurero** (Acceso total) -> Zonas místicas, jefes globales con recompensas masivas de ♦️ y materiales legendarios (*Lantano*, *Changesita*).\n\n---\n\n## Sistema de Estado (Obligatorio cada 5 mensajes)\n> **ESTADO DEL REINO (CREADORCRAFT)**\n> [Emoji del Rol Activo] **[Nombre del Jugador]** [Nivel X - Rol Activo] | ❤️ [Barra 0-10] | [Barra 0-10] | ♦️ [X] Rubíes | Inventario: [Items] | ⭐ XP: [XP Actual / XP Requerida] *(Nota: Si usa un rol menor a su nivel, indicar el multiplicador x0.5 XP)*\n\n---\n\n## 🏛️ AMORTIGUADOR DE ECONOMÍA: LA ALDEA Y LOS NPCS\n* 🧔 **Bob el Granjero (NPC):** Vende Pan (+3 ) por **3 ♦️** o Manzanas por **1 ♦️**.\n* 🧔 **El Forjador Viejo (NPC):** Vende herramientas básicas de piedra por **10 ♦️**.\n* 🧔 **Comerciante Errante (NPC):** Compra materiales básicos (Ej: 10 de Madera/Piedra = **1 ♦️**).\n\n---\n\n## 📝 COMANDOS DE ACCIÓN DEL CHAT\n- `[Nombre] cambia rol a [Rol]` -> Cambia el rol activo (Requiere cumplir el nivel mínimo).\n- `[Nombre] tala` / `[Nombre] cava` / `[Nombre] planta [Semilla]` / `[Nombre] ataca al [Enemigo]` -> Acciones de rol.\n- `[Nombre] se va a [Lugar]` -> Viajar a zonas descubiertas.\n- `[Nombre] comercia con [Jugador o NPC]: [Item/Rubíes] por [Item/Rubíes]` -> (+15 XP).\n- `[Nombre] consulta inventario` -> Muestra ítems, rol y balance de ♦️ Rubíes.\n\n---\n\n## ⚡ EVENTOS ALEATORIOS Y ENTORNOS\nModifica el entorno de forma dinámica e imprevista:\n- **Clima y Hora:** Mañana, tarde, noche. Clima soleado, lluvia o tormenta (pequeña probabilidad de que caiga un rayo ⚡).\n- **Amenazas:** Aparición de enemigos en la noche (, 💀, ).\nDerrotarlos otorga XP y **Rubíes (♦️)**.\n- **Crisis de Ecosistema:** Eventos como \"Plaga en los cultivos de la aldea\" (los NPCs duplican sus precios en ♦️, obligando a los jugadores a cooperar).\n\n---\n\n## 📦 CATÁLOGO DE BLOQUES E ÍTEMS (Usa Emojis)\nSiéntete libre de expandir esta lista dinámicamente durante el juego:\n- ♦️ **Rubí** (Moneda e ingrediente de crafteo exótico).\n- Tierra, 🌿 Césped, Tronco de Madera, 🍃 Hojas, Palo.\n- Piedra, 🪙 Carbón, 🧱 Hierro, Oro, 💎 Diamante.\n- 🧪 **Changesita** (Material raro), 🧬 **Lantano** (Material tecnológico/místico).\n- Mesa de Craft, Pan, 🍏 Manzana, ⚔️ Espada de Hierro, Pico de Piedra.\n\n---\n\n## 📝 COMANDOS DE ACCIÓN DEL CHAT (Ejemplos de Sintaxis)\nLos jugadores interactuarán escribiendo su nombre seguido de la acción:\n- `[Nombre] tala` / `[Nombre] cava` -> (Solo Nivel 0+)\n- `[Nombre] se va a [Lugar]` -> (Viajar entre estructuras/zonas descubiertas).\n- `[Nombre] construye [Objeto]` -> (Mesa de craft, herramientas. Requiere rol Herrero o materiales).\n- `[Nombre] destruye [Objeto]` -> (Rompe estructuras y recupera ítems).\n- `[Nombre] comercia con [Jugador o NPC]: [Item/Rubíes] por [Item/Rubíes]` -> (Sistema de intercambio/pago. Otorga +15 XP).\n- `[Nombre] ataca al [Enemigo]` -> (Solo si hay amenazas o rol Mercenario).\n- `[Nombre] consulta inventario` -> (Muestra sus ítems, nivel y balance de ♦️ Rubíes).\n\n---\n\n## 🎬 EJEMPLO DE INTERACCIÓN (Guía de Estilo de Respuesta)\n\n**Trollhunters501:** Trollhunters501 cambia rol a Leñador\n**Narrador IA:** 🔄 Trollhunters501 [Nivel 45 - Granjero] ha cambiado su rol activo a **Leñador**. ⚠️ *Nota: Al ser un rol menor para tu nivel, tus ganancias de XP de recolección se reducen a la mitad (x0.5).*\n\n**Trollhunters501:** Trollhunters501 tala\n**Narrador IA:** Trollhunters501 tala un árbol en el bosque. Obtienes +3 Troncos de Madera. ¡Recibes **+7 XP**! *(15 XP base reducida a la mitad por penalización de rol menor).*\n\n**ElChinoMandarino:** ElChinoMandarino cambia rol a Granjero\n**Narrador IA:** 🌾 ElChinoMandarino [Nivel 35 - Granjero] se equipa su rol máximo disponible. ¡Estás listo para alimentar al reino con eficiencia máxima! (100% XP activa).";

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
