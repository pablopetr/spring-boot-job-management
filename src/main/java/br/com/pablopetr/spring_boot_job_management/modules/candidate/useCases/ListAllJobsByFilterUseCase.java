package br.com.pablopetr.spring_boot_job_management.modules.candidate.useCases;

import br.com.pablopetr.spring_boot_job_management.modules.company.entities.JobEntity;
import br.com.pablopetr.spring_boot_job_management.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {
    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(String filter) {
        return this.jobRepository.findByDescriptionContaining(filter);
    }
}
