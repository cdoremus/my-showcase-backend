package org.cdoremus.myshowcase.controller;

import org.cdoremus.myshowcase.model.DisplayItem;
import org.cdoremus.myshowcase.service.DisplayItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by craig on 12/27/16.
 */
@RestController
@RequestMapping(value = "/displayItem")
@CrossOrigin(origins = { "*" })
public class DisplayItemRestController {

    @Autowired
    private DisplayItemService service;


    @RequestMapping(value="/save", method = { RequestMethod.POST })
    public ResponseEntity<DisplayItem> saveDisplayItem(
            @RequestParam("itemId") Long itemId,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("loginId") String username) {
        DisplayItem item = service.findItemById(itemId);
        item.setTitle(title);
        item.setDescription(description);
        System.out.println("Item data: " + item);
        DisplayItem newItem = service.save(item, username);
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
            return new ResponseEntity<>(item, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/findByLoginId", method = { RequestMethod.GET })
    public ResponseEntity<List<DisplayItem>> findItemsByLoginId(@RequestParam("loginId") String loginId) {
        List<DisplayItem> items = service.findItemsByLoginId(loginId);
        if (items == null || items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(items, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/findByUserId", method = { RequestMethod.GET })
    public ResponseEntity<List<DisplayItem>> findItemsByUserId(@RequestParam("userId") long userId) {
        List<DisplayItem> items = service.findItemsByUserId(userId);
        if (items == null || items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(items, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/upload", method = { RequestMethod.POST, RequestMethod.OPTIONS })
    public ResponseEntity<DisplayItem> handleUpload(
            @RequestParam("uploadedFile") MultipartFile uploadedFile,
            @RequestParam("loginId") String username) {

        System.out.println("uploadedFile: " + uploadedFile);
        System.out.println("username: " + username);
        DisplayItem item = service.uploadFile(uploadedFile, username);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
