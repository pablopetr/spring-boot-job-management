package br.com.pablopetr.spring_boot_job_management.modules.company.useCases;

import br.com.pablopetr.spring_boot_job_management.exceptions.CompanyAlreadyExists;
import br.com.pablopetr.spring_boot_job_management.modules.company.entities.CompanyEntity;
import br.com.pablopetr.spring_boot_job_management.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        if(
                companyRepository.existsByUsername(companyEntity.getUsername()) ||
                companyRepository.existsByEmail(companyEntity.getEmail())
        ) {
            throw new CompanyAlreadyExists();
        }

        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);

        return this.companyRepository.save(companyEntity);
    }
}
