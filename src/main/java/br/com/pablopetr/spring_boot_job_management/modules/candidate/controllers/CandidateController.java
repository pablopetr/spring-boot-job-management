package br.com.pablopetr.spring_boot_job_management.modules.candidate.controllers;

import br.com.pablopetr.spring_boot_job_management.modules.candidate.CandidateEntity;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = this.createCandidateUseCase.execute(candidateEntity);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id");

        try {
            var profileCandidate = this.profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));

            return ResponseEntity.ok().body(profileCandidate);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
