package ru.kao.kaogateway.util;

import org.slf4j.Logger;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import ru.kao.kaogateway.controller.MessagingController;
import ru.kao.kaogateway.dto.Message;

import java.util.HashMap;
import java.util.Map;


public class JSONUtil {
    private static Logger logger = LoggerUtil.getLogger(JSONUtil.class);

    public static JSONObject messageToJSON(Message message) throws JSONException {
        JSONObject json = new JSONObject();
        if (message.body.startsWith("{"))
            if (message.body.startsWith("{\"message\""))
                json = new JSONObject(message.body);
            else
                json.put("message", new JSONObject(message.body));
         else
            json.put("message", message.body);
        json.put("headers", new JSONObject(message.headers));
        return json;
    }

    public static Message JSONToMessage(JSONObject jsonObject) throws JSONException {
        Map<String, String> headers = new HashMap<>();
        if (jsonObject.get("headers") instanceof JSONObject headersFromJSON) {
            logger.debug("Filling message headers");
            headersFromJSON.keys().forEachRemaining(key -> {
                if (key instanceof String stringKey) {
                    String stringValue = null;
                    try {
                        stringValue = (String) headersFromJSON.get(stringKey);
                    } catch (JSONException e) {
                        logger.error("JSON parse error", e);
                    }
                    logger.trace("Filling header k/v {}/{}", key, stringValue);
                    headers.put(stringKey, stringValue);
                }
            });
        } else {
            logger.error("Incorrect header type. Header type is {}", jsonObject.get("headers").getClass().getName());
        }
        try {
            Object message = jsonObject.get("message");
            return new Message(headers, (String) message);
        } catch (JSONException ignore) {}
        return new Message(headers, "");
    }
}
