package br.com.pablopetr.spring_boot_job_management.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {
    public MessageSource messageSource;
    public ExceptionHandlerController(MessageSource message) {
        this.messageSource = message;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException
        (
            MethodArgumentNotValidException exception
    ) {
        List<ErrorMessageDTO> dto = new ArrayList<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> {
                    String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

                    ErrorMessageDTO error = new ErrorMessageDTO(fieldError.getField(), message);
                    dto.add(error);
                });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
