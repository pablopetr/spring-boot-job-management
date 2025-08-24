package br.com.pablopetr.spring_boot_job_management.modules.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidates")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @NotBlank(message = "Username must not be blank")
    @Pattern(regexp = "^\\S+$", message = "Username must not contain spaces")
    public String username;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Length(min = 6, message = "Password should have at least 6 characters")
    public String password;

    @NotBlank(message = "Description must not be blank")
    public String description;

    @NotBlank(message = "Curriculum must not be blank")
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
