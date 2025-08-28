package br.com.pablopetr.spring_boot_job_management.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateJobDTO {
    @Schema(example = "Job position to work with Java and Spring Boot", requiredMode = Schema.RequiredMode.REQUIRED)
    public String description;

    @Schema(example = "5000.00", requiredMode = Schema.RequiredMode.REQUIRED)
    public String benefits;

    @Schema(example = "Mid-level", requiredMode = Schema.RequiredMode.REQUIRED)
    public String level;
}
