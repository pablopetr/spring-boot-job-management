package br.com.pablopetr.spring_boot_job_management.modules.candidate.dto;

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
    public String description;
    public String username;
    public String email;
    public UUID id;
    public String name;
}
