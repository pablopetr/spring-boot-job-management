package br.com.pablopetr.spring_boot_job_management.exceptions;

public class UsernameOrPasswordIncorrect extends RuntimeException{
    public UsernameOrPasswordIncorrect() {
        super("Username or password incorrect");
    }
}
