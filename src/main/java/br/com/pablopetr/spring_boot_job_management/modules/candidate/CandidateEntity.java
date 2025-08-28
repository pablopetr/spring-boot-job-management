package br.com.pablopetr.spring_boot_job_management.modules.candidate;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @NotBlank(message = "Name must not be blank")
    @Schema(example = "John Doe", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "Username must not be blank")
    @Pattern(regexp = "^\\S+$", message = "Username must not contain spaces")
    @Schema(example = "johndoe", requiredMode = Schema.RequiredMode.REQUIRED)
    public String username;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email should be valid")
    @Schema(example = "johndoe@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Length(min = 6, message = "Password should have at least 6 characters")
    @Schema(example = "strongpassword123", requiredMode = Schema.RequiredMode.REQUIRED)
    public String password;

    @NotBlank(message = "Description must not be blank")
    @Schema(example = "Candidate profile description", requiredMode = Schema.RequiredMode.REQUIRED)
    public String description;

    @NotBlank(message = "Curriculum must not be blank")
    @Schema(example = "http://example.com/curriculum/johndoe.pdf", requiredMode = Schema.RequiredMode.REQUIRED)
    private String curriculum;

    @CreationTimestamp
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;
}
