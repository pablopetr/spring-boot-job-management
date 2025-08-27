package br.com.pablopetr.spring_boot_job_management;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Job Management API",
                version = "1.0",
                description = "API for managing job postings and applications"
        )
)
public class SpringBootJobManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJobManagementApplication.class, args);
	}
}
