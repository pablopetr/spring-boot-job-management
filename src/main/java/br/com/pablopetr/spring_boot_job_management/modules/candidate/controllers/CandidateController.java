package br.com.pablopetr.spring_boot_job_management.modules.candidate.controllers;

import br.com.pablopetr.spring_boot_job_management.modules.candidate.CandidateEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CandidateController {
    @PostMapping("/candidate")
    public void create(@Valid @RequestBody CandidateEntity candidateEntity) {
        System.out.print(candidateEntity);
    }
}
