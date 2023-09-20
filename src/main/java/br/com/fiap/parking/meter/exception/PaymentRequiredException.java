package br.com.fiap.parking.meter.exception;

public class PaymentRequiredException extends RuntimeException {

    public PaymentRequiredException() {
    }

    public PaymentRequiredException(String message) {
        super(message);
    }

    public PaymentRequiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
