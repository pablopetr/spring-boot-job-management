package br.com.pablopetr.spring_boot_job_management.modules.candidate.useCases;

import br.com.pablopetr.spring_boot_job_management.exceptions.JobNotFoundException;
import br.com.pablopetr.spring_boot_job_management.exceptions.UserNotFoundException;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.entities.ApplyJobEntity;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.entities.CandidateEntity;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.CandidateRepository;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.repositories.ApplyJobRepository;
import br.com.pablopetr.spring_boot_job_management.modules.company.entities.JobEntity;
import br.com.pablopetr.spring_boot_job_management.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {
    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void should_not_be_able_to_apply_job_with_candidate_not_found() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (UserNotFoundException exception) {
            assert(exception.getClass().equals(UserNotFoundException.class));
        }
    }

    @Test
    @DisplayName("Should not be able to apply job with job not found")
    public void should_not_be_able_to_apply_job_with_job_not_found() {
        var candidateId = java.util.UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(candidateId);

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateUseCase.execute(candidateId, null);
        } catch (JobNotFoundException exception) {
            assert(exception.getClass().equals(JobNotFoundException.class));
        }
    }

    @Test
    @DisplayName("Should be able to create a new apply job")
    public void should_be_Able_to_create_a_new_apply_job() {
        var candidateId = UUID.randomUUID();
        var jobId = UUID.randomUUID();

        var applyJob = ApplyJobEntity.builder().candidateId(candidateId)
                .jobId(jobId)
                .build();

        var applyJobCreated = ApplyJobEntity.builder().candidateId(candidateId).jobId(jobId).build();

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateUseCase.execute(candidateId, jobId);

        assert(result.getCandidateId().equals(candidateId));
        assert(result.getJobId().equals(jobId));
    }
}
