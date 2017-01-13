package org.cdoremus.myshowcase.controller;

import org.cdoremus.myshowcase.TestUtils;
import org.cdoremus.myshowcase.model.User;
import org.cdoremus.myshowcase.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService service;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User("jdoe", "John", "Doe", "jdoe@foobar.com");
        String str = "2016-06-04 11:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        user.setCreateDate(dateTime);
        // Convert user to JSON
        String json = TestUtils.convertJavaBeanToJson(user);

//        System.out.println("MockMvc" + mockMvc);
        mockMvc.perform(post("/user/new")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }


    @Test
    public void testUpdateUser() throws Exception {
        // insert a user
        User user = new User("jdoe1", "John", "Doe", "jdoe@foobar.com");
        String str = "2016-06-04 22:35";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        System.out.println("User: " + user);
        user.setCreateDate(dateTime);
        User newUser = service.create(user);
        // update the user
        newUser.setEmail("jdoe1@foobar.com");
        System.out.println("New User: " + newUser);
        // Convert user to JSON
        String json = TestUtils.convertJavaBeanToJson(newUser);

//        System.out.println("MockMvc" + mockMvc);
        mockMvc.perform(post("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("\"email\":\"jdoe1@foobar.com\"")));

    }

    @Test
    public void testFindById() throws Exception {
        long id = 1;
        mockMvc.perform(get("/user/find/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"firstName\":\"Craig\"")));
    }

    @Test
    public void testFindByLoginId() throws Exception {
        String loginId = "diana123";
        mockMvc.perform(get("/user/findByLoginId")
                .param("loginId", loginId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"firstName\":\"Diana\"")));
    }

}