package br.com.pablopetr.spring_boot_job_management.modules.candidate;

import lombok.Data;

import java.util.UUID;

@Data
public class CandidateEntity {
    private UUID id;
    private String name;
    public String username;
    private String email;
    public String password;
    public String description;
    private String curriculum;
}
