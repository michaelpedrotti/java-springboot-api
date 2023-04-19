package xyz.api.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
// import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import xyz.api.requests.LoginRequest;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class AuthControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<LoginRequest> body;

    // @MockBean
    

    @Test
    void testLogin() throws Exception {

        var content = this.body.write(new LoginRequest("admin@xyz.io", "admin")).getJson();

        var request = MockMvcRequestBuilders.post("/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content);

        var response = this.mvc.perform(request).andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());

        //response.getContentAsString();
    }
}
