package br.com.pablopetr.spring_boot_job_management.exceptions;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException() {
        super("Job not found");
    }
}
