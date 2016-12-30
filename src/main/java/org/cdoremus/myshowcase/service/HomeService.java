package org.cdoremus.myshowcase.service;

import org.cdoremus.myshowcase.model.CsrfToken;
import org.springframework.stereotype.Service;

/**
 * Created by craig on 12/19/16.
 */
@Service
public class HomeService {

    public CsrfToken getToken(String strToken) {
        CsrfToken token;
        if (strToken != null && !strToken.equals("")) {
            token = new CsrfToken(strToken);
        } else {
            token = new CsrfToken();
        }
        return token;
    }
}
