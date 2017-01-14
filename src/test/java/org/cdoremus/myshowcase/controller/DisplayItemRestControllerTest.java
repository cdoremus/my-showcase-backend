package org.cdoremus.myshowcase.controller;

import org.cdoremus.myshowcase.TestUtils;
import org.cdoremus.myshowcase.model.DisplayItem;
import org.cdoremus.myshowcase.model.User;
import org.cdoremus.myshowcase.repository.UserRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for DisplayItemRestController.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DisplayItemRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveDisplayItem() throws Exception {
        String username = "craig123";
        DisplayItem item = new DisplayItem("file99", "file99 desc", 200, 400, "file99.png", true, null);
        item.setCreateDate(LocalDateTime.now());
        item.setItemId(null);
        // Convert to JSON
        String json = TestUtils.convertJavaBeanToJson(item);

        mockMvc.perform(post("/displayItem/save")
//                .contentType(MediaType.APPLICATION_JSON)
                .param("itemId", "1")
                .param("title", "new title")
                .param("description", "new desc")
                .param("loginId", username))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void findById() throws Exception {
        long id = 1;
        mockMvc.perform(get("/displayItem/find/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"firstName\":\"Craig\"")));

    }

    @Test
    public void findItemsByLoginId() throws Exception {
        String id = "craig123";
        mockMvc.perform(get("/displayItem/findByLoginId")
                .param("loginId", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"itemId\":1")))
                .andExpect(content().string(containsString("\"itemId\":2")));
    }

    @Test
    public void findItemsByUserId() throws Exception {
        String id = Long.toString(1L);
        mockMvc.perform(get("/displayItem/findByUserId")
                .param("userId", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"itemId\":1")))
                .andExpect(content().string(containsString("\"itemId\":2")));

    }

    @Test
    @Ignore // This needs to be correctly implemented
    public void testHandleUpload() throws Exception {
        MockMultipartFile file = new MockMultipartFile("data", "testfile.txt", "text/plain", "some file content".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/displayItem/upload")
                .file(file)
//                .accept(MediaType.MULTIPART_FORM_DATA)
                .param("uploadedFile", file.getBytes().toString())
                .param("username", "craig"))
                .andDo(print());
//                .andExpect(status().isOk());
    }

}