package br.com.pablopetr.spring_boot_job_management.modules.company.useCases;

import br.com.pablopetr.spring_boot_job_management.exceptions.CompanyAlreadyExists;
import br.com.pablopetr.spring_boot_job_management.modules.company.entities.CompanyEntity;
import br.com.pablopetr.spring_boot_job_management.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    private void execute(CompanyEntity companyEntity) {
        if(
                companyRepository.existsByUsername(companyEntity.getUsername()) ||
                companyRepository.existsByEmail(companyEntity.getEmail())
        ) {
            throw new CompanyAlreadyExists();
        }

        this.companyRepository.save(companyEntity);
    }
}
