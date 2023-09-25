package ru.kao.kaogateway.util;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HeadersUtil {
    private static Logger logger = LoggerUtil.getLogger(HeadersUtil.class);
    public static Header[] toApacheHttpHeaders(Map<String, String> nativeHeaders) {
        return nativeHeaders.entrySet().stream()
                .map(entry -> new BasicHeader(entry.getKey(), entry.getValue())).toArray(Header[]::new);
    }

    public static Map<String, String> toNativeHeaders(Header[] headers) {
        Map<String, String> map = new HashMap<>();
        for (Header header : headers) {
            if (map.put(header.getName(), header.getValue()) != null) {
                logger.error("Find duplicated key. Its = {}. I replaced it with a newer", header.getName());
            }
        }
        return map;
    }
}
