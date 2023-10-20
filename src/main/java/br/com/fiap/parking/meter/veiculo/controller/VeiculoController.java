package br.com.fiap.parking.meter.veiculo.controller;

import br.com.fiap.parking.meter.core.annotations.PaymentPath;
import br.com.fiap.parking.meter.veiculo.dto.VeiculoDto;
import br.com.fiap.parking.meter.veiculo.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;


    @GetMapping
    public ResponseEntity<Page<VeiculoDto>> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                    @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        return ResponseEntity.ok(this.veiculoService.findAll(page, pageSize));
    }


    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDto> findById(@PathVariable(name = "id") Long id) {
        VeiculoDto veiculoDto = VeiculoDto.from(this.veiculoService.findById(id));
        return ResponseEntity.ok(veiculoDto);
    }


    @PostMapping
    public ResponseEntity<VeiculoDto> insert(@Valid @RequestBody VeiculoDto veiculoDto) {
        VeiculoDto veiculo = this.veiculoService.insert(veiculoDto);
        return new ResponseEntity<VeiculoDto>(veiculo, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDto> update(@PathVariable(name = "id") Long id, @RequestBody VeiculoDto veiculoDto) {
        return ResponseEntity.ok(this.veiculoService.update(id, veiculoDto));
    }


    @DeleteMapping("/{id}")
    @PaymentPath
    public ResponseEntity<Void> delete(@RequestHeader(name = "id") Long id) {
        this.veiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
