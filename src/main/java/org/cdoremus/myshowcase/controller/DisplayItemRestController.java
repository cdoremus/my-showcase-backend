package org.cdoremus.myshowcase.controller;

import org.cdoremus.myshowcase.model.DisplayItem;
import org.cdoremus.myshowcase.service.DisplayItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by craig on 12/27/16.
 */
@RestController
@RequestMapping(value = "/displayItem")
public class DisplayItemRestController {

    @Autowired
    private DisplayItemService service;


    @RequestMapping(value="/new", method = { RequestMethod.POST })
    public ResponseEntity<DisplayItem> createDisplayItem(@RequestBody DisplayItem item) {
        DisplayItem newItem = service.create(item);
        System.out.println("newItem: " + newItem);
        if (newItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(newItem, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/find/{itemId}")
    public ResponseEntity<DisplayItem> findById(@PathVariable("itemId") long itemId) {
        DisplayItem item = service.findItemById(itemId);
        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(item, HttpStatus.NOT_FOUND.OK);
        }
    }

    @RequestMapping(value = "/findByLoginId", method = { RequestMethod.GET })
    public ResponseEntity<List<DisplayItem>> findItemsByLoginId(@RequestParam("loginId") String loginId) {
        List<DisplayItem> items = service.findItemsByLoginId(loginId);
        if (items == null || items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(items, HttpStatus.NOT_FOUND.OK);
        }
    }

    @RequestMapping(value = "/findByUserId", method = { RequestMethod.GET })
    public ResponseEntity<List<DisplayItem>> findItemsByUserId(@RequestParam("userId") long userId) {
        List<DisplayItem> items = service.findItemsByUserId(userId);
        if (items == null || items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(items, HttpStatus.NOT_FOUND.OK);
        }
    }
}
