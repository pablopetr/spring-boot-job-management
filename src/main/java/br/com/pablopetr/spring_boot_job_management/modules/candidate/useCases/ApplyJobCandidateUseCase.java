package br.com.pablopetr.spring_boot_job_management.modules.candidate.useCases;

import br.com.pablopetr.spring_boot_job_management.exceptions.JobNotFoundException;
import br.com.pablopetr.spring_boot_job_management.exceptions.UserNotFoundException;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.CandidateRepository;
import br.com.pablopetr.spring_boot_job_management.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    public void execute(UUID candidateId, UUID jobId) {
        this.candidateRepository.findById(candidateId)
                .orElseThrow(UserNotFoundException::new);

        System.out.println("Candidate found, checking job...");

        this.jobRepository.findById(jobId)
                .orElseThrow(JobNotFoundException::new);
    }
}
