package br.com.pablopetr.spring_boot_job_management.modules.candidate.useCases;

import br.com.pablopetr.spring_boot_job_management.modules.candidate.CandidateEntity;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.CandidateRepository;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.dto.CandidateProfileResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateProfileResponseDTO execute(UUID candidateId) {
        var candidate = this.candidateRepository.findById(candidateId)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });

        var candidateDTO = CandidateProfileResponseDTO.builder()
                .id(candidate.getId())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .build();

        return candidateDTO;
    }
}
