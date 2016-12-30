package org.cdoremus.myshowcase.service;

import org.cdoremus.myshowcase.model.DisplayItem;
import org.cdoremus.myshowcase.repository.DisplayItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class DisplayItemService {

    @Autowired
    private DisplayItemRepository repository;

    public DisplayItem create(DisplayItem item) {
        return repository.save(item);
    }

    public DisplayItem findItemById(long itemId) {
        return repository.findOne(itemId);
    }

    public List<DisplayItem> findItemsByLoginId(String loginId) {
        List<DisplayItem> items = repository.findByLoginId(loginId);
        return items;
    }

    public List<DisplayItem> findItemsByUserId(long userId) {
        return repository.findByUserId(userId);
    }
}
