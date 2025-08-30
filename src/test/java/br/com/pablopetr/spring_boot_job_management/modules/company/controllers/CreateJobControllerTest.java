package br.com.pablopetr.spring_boot_job_management.modules.company.controllers;

import br.com.pablopetr.spring_boot_job_management.modules.company.dto.CreateJobDTO;
import br.com.pablopetr.spring_boot_job_management.modules.company.entities.CompanyEntity;
import br.com.pablopetr.spring_boot_job_management.modules.company.repositories.CompanyRepository;
import br.com.pablopetr.spring_boot_job_management.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "security.token.secret=mysecretkey"
)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @org.junit.Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @org.junit.Test
    public void should_be_able_to_create_a_new_job() throws Exception {
        var company = CompanyEntity.builder()
                .email("email@company.com")
                .username("company")
                .password("12345678")
                .website("https://www.google.com")
                .name("company")
                .build();

        company = companyRepository.saveAndFlush(company);

        var dto = CreateJobDTO.builder()
                .description("Job position to work with Java and Spring Boot")
                .benefits("5000")
                .level("Mid-level")
                .build();

        var token = TestUtils.generateToken(company.getId(), "mysecretkey");

        mvc.perform(MockMvcRequestBuilders.post("/company/job")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectToJSON(dto))
                        .header("Authorization", "Bearer " + token))
                .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void should_not_be_able_to_create_a_new_job_if_company_not_found() throws Exception {
        var createJobDTO = CreateJobDTO.builder()
                .benefits("5000")
                .description("Job position to work with Java and Spring Boot")
                .level("Mid-level")
                .build();

        var token = TestUtils.generateToken(UUID.randomUUID(), "mysecretkey");

        mvc.perform(
                MockMvcRequestBuilders.post("/company/job")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + token)
                    .content(TestUtils.objectToJSON(createJobDTO))
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}