package br.com.pablopetr.spring_boot_job_management.modules.company.controllers;

import br.com.pablopetr.spring_boot_job_management.modules.company.dto.CreateJobDTO;
import br.com.pablopetr.spring_boot_job_management.utils.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "security.token.secret=mysecretkey"
)
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @org.junit.Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @org.junit.Test
    public void should_be_able_to_create_a_new_job() throws Exception {
        var dto = CreateJobDTO.builder()
                .description("Job position to work with Java and Spring Boot")
                .benefits("5000")
                .level("Mid-level")
                .build();

        var companyId = UUID.fromString("4885a8b4-80e9-4968-a50d-1c887527ab5e");
        var token = TestUtils.generateToken(companyId, "mysecretkey");

        mvc.perform(MockMvcRequestBuilders.post("/company/job")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectToJSON(dto))
                        .header("Authorization", "Bearer " + token))
                .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}