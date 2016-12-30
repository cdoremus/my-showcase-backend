package org.cdoremus.myshowcase;

import org.cdoremus.myshowcase.model.DisplayItem;
import org.cdoremus.myshowcase.model.User;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

/**
 *
 */
public class TestUtilsTest {

    @Test
    public void convertObjectToJson() {
        User user = new User("login123", "First", "Last", "foo@bar.com");
        DisplayItem item = new DisplayItem("Title1", "Desc1", 400, 200, "file.jpg", true, user);

        String json = TestUtils.convertJavaBeanToJson(item);
//        System.out.println("Item: " + json);
        assertTrue(json.contains("\"title\":\"Title1\""));
        assertTrue(json.contains("\"email\":\"foo@bar.com\""));
    }


    @Test
    public void convertObjectToJson_withDate() {
        User user = new User("user123", "First", "Last", "foo@bar.com");
        String str = "2016-06-04 11:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        user.setCreateDate(dateTime);

        String json = TestUtils.convertJavaBeanToJson(user);

        System.out.println("User: " + json);
        assertTrue(json.contains("\"firstName\":\"First\""));
        assertTrue(json.contains("\"email\":\"foo@bar.com\""));
        assertTrue((json.contains("\"createDate\":\"2016-06-04T11:30\"")));
    }
}