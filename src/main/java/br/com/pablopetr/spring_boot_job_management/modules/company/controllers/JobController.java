package br.com.pablopetr.spring_boot_job_management.modules.company.controllers;

import br.com.pablopetr.spring_boot_job_management.modules.company.dto.CreateJobDTO;
import br.com.pablopetr.spring_boot_job_management.modules.company.entities.JobEntity;
import br.com.pablopetr.spring_boot_job_management.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("")
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
