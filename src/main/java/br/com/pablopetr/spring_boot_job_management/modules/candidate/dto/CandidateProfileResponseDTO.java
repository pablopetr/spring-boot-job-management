package br.com.pablopetr.spring_boot_job_management.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateProfileResponseDTO {
    public UUID id;

    @Schema(example = "Candidate profile description")
    public String description;

    @Schema(example = "johndoe")
    public String username;

    @Schema(example = "johndoe@example.com")
    public String email;

    @Schema(example = "John Doe")
    public String name;
}
