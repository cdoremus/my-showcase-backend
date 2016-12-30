package org.cdoremus.myshowcase.controller;

import org.cdoremus.myshowcase.model.CsrfToken;
import org.cdoremus.myshowcase.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by craig on 12/18/16.
 */
@RestController
public class HomeRestController {

    @Autowired
    private HomeService service;


    @RequestMapping(value="/", method = { RequestMethod.GET })
    public ResponseEntity<CsrfToken> getToken(@RequestParam(value = "token", required = false) String strToken) {
        CsrfToken token = service.getToken(strToken);
        return new ResponseEntity<CsrfToken>(token, HttpStatus.OK);
    }
}
