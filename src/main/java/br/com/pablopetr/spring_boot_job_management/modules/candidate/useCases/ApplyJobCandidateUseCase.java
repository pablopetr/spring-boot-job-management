package br.com.pablopetr.spring_boot_job_management.modules.candidate.useCases;

import br.com.pablopetr.spring_boot_job_management.exceptions.JobNotFoundException;
import br.com.pablopetr.spring_boot_job_management.exceptions.UserNotFoundException;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.CandidateRepository;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.entities.ApplyJobEntity;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.repositories.ApplyJobRepository;
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

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID candidateId, UUID jobId) {
        this.candidateRepository.findById(candidateId)
                .orElseThrow(UserNotFoundException::new);

        System.out.println("Candidate found, checking job...");

        this.jobRepository.findById(jobId)
                .orElseThrow(JobNotFoundException::new);

        var applyJob = ApplyJobEntity.builder()
                .candidateId(candidateId)
                .jobId(jobId)
                .build();

        applyJob = (ApplyJobEntity) applyJobRepository.save(applyJob);

        return applyJob;
    }
}
