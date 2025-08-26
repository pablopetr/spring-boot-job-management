package br.com.pablopetr.spring_boot_job_management.modules.company.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "companies")
@Data
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Pattern(regexp = "^\\S+$", message = "Username must not contain spaces")
    private String username;

    @NotBlank
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Length(min = 6, message = "Password should have at least 6 characters")
    private String password;

    @NotBlank
    @URL(message = "Website should be a valid URL")
    private String website;

    @NotBlank
    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
