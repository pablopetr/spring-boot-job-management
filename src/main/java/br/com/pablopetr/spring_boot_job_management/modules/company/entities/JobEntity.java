package br.com.pablopetr.spring_boot_job_management.modules.company.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "jobs") // garanta o nome da tabela
@Data
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String description;

    @NotBlank
    private String benefits;

    @NotBlank
    private String level; // evite 'public' aqui

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
