package ru.kao.kaogateway.util;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeader;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class HeadersUtil {
    public static Header[] toApacheHttpHeaders(Map<String, String> nativeHeaders) {
        return nativeHeaders.entrySet().stream()
                .map(entry -> new BasicHeader(entry.getKey(), entry.getValue())).toArray(Header[]::new);
    }

    public static Map<String, String> toNativeHeaders(Header[] headers) {
        return Arrays.stream(headers)
                    .collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
    }
}
