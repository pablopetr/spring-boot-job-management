package br.com.pablopetr.spring_boot_job_management.modules.candidate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
public class CandidateEntity {
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
}
