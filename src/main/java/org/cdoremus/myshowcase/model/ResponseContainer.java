package org.cdoremus.myshowcase.model;

import java.io.Serializable;

/**
 * Created by craig on 12/18/16.
 */
public class ResponseContainer<T>  implements Serializable {
    private final  T payload;
    private final CsrfToken token;

    public ResponseContainer(T payload) {
        this.payload = payload;
        this.token = new CsrfToken();
    }

    public ResponseContainer(T payload, CsrfToken token) {
        this.payload = payload;
        this.token = token;
    }

    public T getPayload() {
        return payload;
    }

    public CsrfToken getToken() {
        return token;
    }


}
