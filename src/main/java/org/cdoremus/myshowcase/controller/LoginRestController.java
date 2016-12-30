package org.cdoremus.myshowcase.controller;

import org.cdoremus.myshowcase.model.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by craig on 12/16/16.
 */
@RestController
@RequestMapping(value = "/login")
public class LoginRestController {

    @RequestMapping("/guest")
    public CsrfToken guestLogin() {
        return new CsrfToken();
    }
}
