package org.cdoremus.myshowcase.model;

import org.cdoremus.myshowcase.util.WebUtils;

import java.io.Serializable;

/**
 * Created by craig on 12/18/16.
 */
public class CsrfToken  implements Serializable {
    private final String token;

    public CsrfToken() {
        this.token = WebUtils.createToken();
    }

    public CsrfToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "CsrfToken{" +
                "token='" + token + '\'' +
                '}';
    }
}
