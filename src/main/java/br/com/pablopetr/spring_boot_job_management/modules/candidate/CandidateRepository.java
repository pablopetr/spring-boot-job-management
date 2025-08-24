package br.com.pablopetr.spring_boot_job_management.modules.candidate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
