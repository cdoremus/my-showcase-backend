package org.cdoremus.myshowcase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test utilities
 */
public class TestUtils {

    private TestUtils(){}


    public static String convertJavaBeanToJson(Object bean) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            json = "{}";
        }
        return json;
    }
}
