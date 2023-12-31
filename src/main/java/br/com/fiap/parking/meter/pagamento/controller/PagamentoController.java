package br.com.fiap.parking.meter.pagamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.parking.meter.estacionamento.dto.EstacionamentoDTO;
import br.com.fiap.parking.meter.pagamento.dto.PagamentoDto;
import br.com.fiap.parking.meter.pagamento.service.PagamentoService;

@RestController
@RequestMapping("/estacionamento")
public class PagamentoController {
    
    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    private ResponseEntity<PagamentoDto> pagamento(@RequestBody EstacionamentoDTO pagamentoDTO) {
        var pagamento = pagamentoService.payment(pagamentoDTO);
        return ResponseEntity.ok().body(pagamento);
    }
}
