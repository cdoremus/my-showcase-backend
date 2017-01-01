package org.cdoremus.myshowcase.controller;

import org.cdoremus.myshowcase.model.User;
import org.cdoremus.myshowcase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Created by craig on 12/16/16.
 */
@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = { "*" })
public class UserRestController {

    @Autowired
    private UserService service;

    @RequestMapping(value="/new", method = RequestMethod.POST )
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = service.create(user);
        if (newUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
    }

    @RequestMapping(value="/update", method = RequestMethod.POST )
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User newUser = service.update(user);
        if (newUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/find/{userId}", method = RequestMethod.GET)
    public ResponseEntity<User> findUserById(@PathVariable("userId") long userId) {
        User user = service.findUserById(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/findByLoginId", method = RequestMethod.GET)
    public ResponseEntity<User> findUserByLoginId(@RequestParam("loginId") String loginId) {
        System.out.println("LoginID: " + loginId);
        User user = service.findUserByLoginId(loginId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

}
