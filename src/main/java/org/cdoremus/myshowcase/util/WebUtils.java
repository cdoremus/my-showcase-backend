package org.cdoremus.myshowcase.util;

import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Utility functions and constants
 */
public class WebUtils {

    public static final String CLIENT_BASE_URL = "http://localhost:7000";

    private WebUtils(){}

    public static String createToken() {
        return UUID.randomUUID().toString();
    }


    public static void addToken() {
        ObjectMapper mapper = new ObjectMapper();
    }
}
