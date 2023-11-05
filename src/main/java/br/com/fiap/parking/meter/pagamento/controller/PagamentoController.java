package br.com.fiap.parking.meter.pagamento.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.parking.meter.pagamento.dto.PagamentoDto;
import br.com.fiap.parking.meter.pagamento.service.PagamentoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pagamento")
public class PagamentoController {
    
    private final PagamentoService pagamentoService;

    @PostMapping("{id}")
    public ResponseEntity<PagamentoDto> reciboDePagamento(@PathVariable("id") Long id) {
        var recibo = pagamentoService.payment(id);
        return ResponseEntity.ok().body(recibo);
    }
}
