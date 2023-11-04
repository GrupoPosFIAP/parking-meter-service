package br.com.fiap.parking.meter.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler({Exception.class, GenericException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<GenericMessage> handleGenericException(Exception exception) {
        GenericMessage genericMessage = new GenericMessage(HttpStatus.INTERNAL_SERVER_ERROR.name(), exception.getMessage());
        return ResponseEntity.internalServerError().body(genericMessage);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<GenericMessage> handleGenericException(EntityNotFoundException exception) {
        GenericMessage genericMessage = new GenericMessage(HttpStatus.NOT_FOUND.name(), exception.getMessage());
        return ResponseEntity.badRequest().body(genericMessage);
    }

    @ExceptionHandler(PaymentRequiredException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<GenericMessage> handleGenericException(PaymentRequiredException exception) {
        GenericMessage genericMessage = new GenericMessage("PAYMENT_REQUIRED", exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(genericMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GenericMessage> handleValidationExceptions(
            ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        ConstraintViolation<?> constraintViolation = constraintViolations.stream().findFirst().orElse(null);

        GenericMessage genericMessage =
                new GenericMessage(HttpStatus.BAD_REQUEST.name(), constraintViolation != null ? constraintViolation.getMessage() : "Informações inválidas.");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericMessage);
    }

}
