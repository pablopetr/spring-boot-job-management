package br.com.pablopetr.spring_boot_job_management.modules.candidate.useCases;

import br.com.pablopetr.spring_boot_job_management.exceptions.UserAlreadyExists;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.CandidateEntity;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        if(
                candidateRepository.existsByUsername(candidateEntity.getUsername()) ||
                candidateRepository.existsByEmail(candidateEntity.getEmail())
        ) {
            throw new UserAlreadyExists();
        }

        var password = passwordEncoder.encode(candidateEntity.getPassword());

        candidateEntity.setPassword(password);

        return candidateRepository.save(candidateEntity);
    }
}
