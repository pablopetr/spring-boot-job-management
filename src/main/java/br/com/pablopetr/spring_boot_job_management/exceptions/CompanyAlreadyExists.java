package br.com.pablopetr.spring_boot_job_management.exceptions;

public class CompanyAlreadyExists extends RuntimeException {
    public CompanyAlreadyExists() {
        super("Company with given name or email already exists");
    }
}
