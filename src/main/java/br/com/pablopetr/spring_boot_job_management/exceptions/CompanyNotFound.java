package br.com.pablopetr.spring_boot_job_management.exceptions;

public class CompanyNotFound extends RuntimeException{
    public CompanyNotFound() {
        super("Company with given id not found");
    }
}
