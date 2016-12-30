package org.cdoremus.myshowcase.repository;

import org.cdoremus.myshowcase.TestConfig;
import org.cdoremus.myshowcase.model.DisplayItem;
import org.cdoremus.myshowcase.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by craig on 12/23/16.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes=TestConfig.class)
public class DisplayItemRepositoryTest {

    @Autowired
    DisplayItemRepository itemRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void findItemsByLoginId() throws Exception {
        String loginId = "craig123";
        List<DisplayItem> items = itemRepository.findByLoginId(loginId);

        assertNotNull(items);
        assertFalse(items.isEmpty());
        assertEquals(2, items.size());
    }

    @Test
    public void findItemsByLoginId_BadLoginId() throws Exception {
        String loginId = "asdfsdfa";
        List<DisplayItem> items = itemRepository.findByLoginId(loginId);

        assertNotNull(items);
        assertTrue(items.isEmpty());
    }

    @Test
    public void findOne_BadId() throws Exception {
        long id = -99L;
        DisplayItem item = itemRepository.findOne(id);

        assertNull(item);
    }

    @Test
    public void createDisplayItem() {
        String loginId = "diana123";
        User user = userRepository.findByLoginId(loginId);
//        System.out.println("Found user: " + user);
        DisplayItem item = new DisplayItem("file3", "file3 desc", 200, 400, "file3.jpg", true, user);

        itemRepository.save(item);
        List<DisplayItem> found = itemRepository.findByLoginId(loginId);

        assertEquals(1, found.size());
        assertEquals(item.getDescription(), found.get(0).getDescription());
//        System.out.println("Found: " + found);

    }

    @Test
    public void updateDisplayItem() {
        String loginId = "craig123";
        User user = userRepository.findByLoginId(loginId);
//        System.out.println("Found user: " + user);
        DisplayItem item = itemRepository.findByLoginId(loginId).get(0);
        String newDesc = "New desc";
        item.setDescription(newDesc);

        itemRepository.save(item);
        List<DisplayItem> found = itemRepository.findByLoginId(loginId);

        assertEquals(newDesc, found.get(0).getDescription());
//        System.out.println("Found: " + found);

    }

}