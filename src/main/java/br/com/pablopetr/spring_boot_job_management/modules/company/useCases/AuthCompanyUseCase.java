package br.com.pablopetr.spring_boot_job_management.modules.company.useCases;

import br.com.pablopetr.spring_boot_job_management.exceptions.UsernameOrPasswordIncorrect;
import br.com.pablopetr.spring_boot_job_management.modules.candidate.dto.AuthCompanyResponseDTO;
import br.com.pablopetr.spring_boot_job_management.modules.company.dto.AuthCompanyDTO;
import br.com.pablopetr.spring_boot_job_management.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyUseCase {
    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) {
        var company = companyRepository.findByUsername(authCompanyDTO.getUsername())
                .orElseThrow(UsernameOrPasswordIncorrect::new);

        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!passwordMatches) {
            throw new RuntimeException("Invalid password");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("job-management")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(company.getId().toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        return AuthCompanyResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();
    }
}
