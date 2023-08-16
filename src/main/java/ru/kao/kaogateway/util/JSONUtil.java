package ru.kao.kaogateway.util;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import ru.kao.kaogateway.dto.Message;


public class JSONUtil {
    public static JSONObject messageToJSON(Message message) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("message", new JSONObject(message.body));
        json.put("headers", new JSONObject(message.headers));
        return json;
    }
}
