package org.cdoremus.myshowcase.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Created by craig on 12/18/16.
 */
public class WebUtils {

    public static final String CLIENT_BASE_URL = "http://localhost:5000";

    private WebUtils(){}

    public static String createToken() {
        return UUID.randomUUID().toString();
    }


    public static void addToken() {
        ObjectMapper mapper = new ObjectMapper();
    }
}
