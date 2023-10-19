package br.com.fiap.parking.meter.carro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @GetMapping
    public ResponseEntity<String> nothing() {
        return ResponseEntity.ok("Sucesso");
    }

}
