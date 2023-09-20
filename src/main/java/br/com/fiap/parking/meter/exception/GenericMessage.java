package br.com.fiap.parking.meter.exception;

import java.time.LocalDateTime;

public record GenericMessage(String errorCode, String errorMessage) {

}
