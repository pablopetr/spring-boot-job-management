package br.com.pablopetr.spring_boot_job_management.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {
    public String field;
    public String message;
}
