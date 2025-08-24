package br.com.pablopetr.spring_boot_job_management.modules.company.controllers;

import br.com.pablopetr.spring_boot_job_management.modules.company.entities.CompanyEntity;
import br.com.pablopetr.spring_boot_job_management.modules.company.useCases.CreateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping("")
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {
        try {
            var result = this.createCompanyUseCase.execute(companyEntity);

            return ResponseEntity.ok().body(result);
        } catch(Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
