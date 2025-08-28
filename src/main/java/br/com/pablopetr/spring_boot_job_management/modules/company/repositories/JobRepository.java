package br.com.pablopetr.spring_boot_job_management.modules.company.repositories;

import br.com.pablopetr.spring_boot_job_management.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
    List<JobEntity> findByDescriptionContaining(String filter);
}
