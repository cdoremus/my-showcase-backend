package org.cdoremus.myshowcase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Created by craig on 12/27/16.
 */
public class TestUtils {

    private TestUtils(){}


    public static String convertJavaBeanToJson(Object bean) {
        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = null;
        try {
//            json = ow.writeValueAsString(bean);
            json = mapper.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            json = "{}";
        }
        return json;
    }
}
