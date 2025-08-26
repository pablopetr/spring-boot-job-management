package br.com.pablopetr.spring_boot_job_management.modules.company.dto;

import lombok.Data;

@Data
public class CreateJobDTO {
    public String description;
    public String benefits;
    public String level;
}
