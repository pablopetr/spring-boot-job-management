package br.com.pablopetr.spring_boot_job_management.exceptions;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists() {
        super("User with given username or email already exists");
    }
}
