package br.com.pablopetr.spring_boot_job_management.modules.candidate.repositories;

import br.com.pablopetr.spring_boot_job_management.modules.candidate.entities.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {
}
