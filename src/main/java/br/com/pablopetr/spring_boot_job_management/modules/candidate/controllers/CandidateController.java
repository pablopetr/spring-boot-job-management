package br.com.pablopetr.spring_boot_job_management.modules.candidate.controllers;

import br.com.pablopetr.spring_boot_job_management.modules.candidate.entities.CandidateEntity;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.dto.CandidateProfileResponseDTO;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.useCases.ApplyJobCandidateUseCase;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.useCases.ProfileCandidateUseCase;
import br.com.pablopetr.spring_boot_job_management.modules.company.entities.JobEntity;
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
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidate", description = "Endpoints for Candidate")
public class CandidateController {
    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @Autowired
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @PostMapping("")
    @Operation(summary = "Create a candidate", description = "Endpoint to create a new candidate")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = CandidateEntity.class))
        }),
        @ApiResponse(responseCode = "400")
    })
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = this.createCandidateUseCase.execute(candidateEntity);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    @Operation(summary = "Candidate profile", description = "Show candidate profile information")
    @SecurityRequirement(name = "jwt_auth")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = CandidateProfileResponseDTO.class))
        }),
        @ApiResponse(responseCode = "400")
    })
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id");

        try {
            var profileCandidate = this.profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));

            return ResponseEntity.ok().body(profileCandidate);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "List all available jobs by filter", description = "List all available jobs by filter")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
        })
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> findJobByFilter(@RequestParam String filter) {
        var jobs = this.listAllJobsByFilterUseCase.execute(filter);

        return ResponseEntity.ok().body(jobs);
    }


    @PostMapping("job/apply")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Apply for a job", description = "Apply for a job by passing the job ID")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> applyJob(HttpServletRequest request, @RequestBody UUID jobId) {
        var candidateId = request.getAttribute("candidate_id");

        try {
            var result = this.applyJobCandidateUseCase.execute(UUID.fromString(candidateId.toString()), jobId);

            return ResponseEntity.ok().body(result);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
