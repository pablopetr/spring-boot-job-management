package br.com.pablopetr.spring_boot_job_management.modules.candidate.controllers;

import br.com.pablopetr.spring_boot_job_management.modules.candidate.CandidateEntity;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.CandidateRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("/candidate")
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity) {
        return this.candidateRepository.save(candidateEntity);
    }
}
