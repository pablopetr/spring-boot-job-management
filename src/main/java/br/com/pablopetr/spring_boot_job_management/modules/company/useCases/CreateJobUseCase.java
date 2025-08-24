package br.com.pablopetr.spring_boot_job_management.modules.company.useCases;

import br.com.pablopetr.spring_boot_job_management.exceptions.CompanyNotFound;
import br.com.pablopetr.spring_boot_job_management.modules.company.entities.JobEntity;
import br.com.pablopetr.spring_boot_job_management.modules.company.repositories.CompanyRepository;
import br.com.pablopetr.spring_boot_job_management.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {
        if (!companyRepository.existsById(jobEntity.getCompanyId())) {
            throw new CompanyNotFound();
        }

        return this.jobRepository.save(jobEntity);
    }
}
