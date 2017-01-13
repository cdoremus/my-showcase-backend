package org.cdoremus.myshowcase.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class WebUtilsTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreateToken() {
        // Make sure the same
        int count = 50;
        int expectedLen = 36;
        for (int i = 0; i < count; i++) {
            String token = WebUtils.createToken();
            int actualLen = token.length();
    //        System.out.println(token + " of length " + actualLen);
            assertEquals(expectedLen, actualLen);
        }
    }


}