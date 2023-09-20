package br.com.fiap.parking.meter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

}
