package br.com.pablopetr.spring_boot_job_management.modules.company.repositories;

import br.com.pablopetr.spring_boot_job_management.modules.company.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Optional<CompanyEntity> findByUsername(String username);
}
