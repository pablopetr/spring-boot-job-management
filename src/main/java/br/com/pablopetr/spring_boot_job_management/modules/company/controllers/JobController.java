package br.com.pablopetr.spring_boot_job_management.modules.company.controllers;

import br.com.pablopetr.spring_boot_job_management.modules.company.dto.CreateJobDTO;
import br.com.pablopetr.spring_boot_job_management.modules.company.entities.JobEntity;
import br.com.pablopetr.spring_boot_job_management.modules.company.useCases.CreateJobUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("")
    @PreAuthorize("hasRole('COMPANY')")
    @Tag(name = "Jobs", description = "Job management endpoints")
    @Operation(summary = "Create a job", description = "Endpoint to create a new job")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
        })
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        try {
            var companyId = request.getAttribute("company_id");

            var jobEntity = JobEntity.builder()
                        .companyId(UUID.fromString(companyId.toString()))
                        .description(createJobDTO.getDescription())
                        .benefits(createJobDTO.getBenefits())
                        .level(createJobDTO.getLevel())
                        .build();

            jobEntity.setCompanyId(UUID.fromString(companyId.toString()));

            var result = this.createJobUseCase.execute(jobEntity);

            return ResponseEntity.ok().body(result);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
