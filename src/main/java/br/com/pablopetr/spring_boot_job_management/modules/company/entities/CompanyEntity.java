package br.com.pablopetr.spring_boot_job_management.modules.company.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "companies")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @NotBlank
    @Pattern(regexp = "^\\S+$", message = "Username must not contain spaces")
    @Schema(example = "company_user", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @NotBlank
    @Schema(example = "company@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Length(min = 6, message = "Password should have at least 6 characters")
    @Schema(example = "strongpassword123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @NotBlank
    @URL(message = "Website should be a valid URL")
    @Schema(example = "http://www.example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String website;

    @NotBlank
    @Schema(example = "Example Company", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @CreationTimestamp
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;
}
