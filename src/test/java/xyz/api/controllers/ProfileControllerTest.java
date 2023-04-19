package xyz.api.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfileControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Return 404 when resource is not found")
    // @WithMockUser - not found
    void testShow() throws Exception {

        var requestBuilder = MockMvcRequestBuilders.get("/profile/1");

        var response = this.mvc.perform(requestBuilder).andReturn().getResponse();

        assertEquals(response.getStatus(), 403);
    }
}
