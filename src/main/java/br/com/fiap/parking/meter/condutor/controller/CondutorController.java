package br.com.fiap.parking.meter.condutor.controller;

import br.com.fiap.parking.meter.condutor.service.CondutorService;
import br.com.fiap.parking.meter.core.annotations.PaymentPath;
import br.com.fiap.parking.meter.condutor.dto.CondutorDto;
import br.com.fiap.parking.meter.veiculo.domain.Veiculo;
import br.com.fiap.parking.meter.veiculo.dto.VeiculoCondutorDTO;
import br.com.fiap.parking.meter.veiculo.dto.VeiculoDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/condutor")
public class CondutorController {

    @Autowired
    private CondutorService condutorService;

    @GetMapping
    public ResponseEntity<Page<VeiculoCondutorDTO>> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        var condutores = condutorService.findAll(pageRequest);
        return ResponseEntity.ok(condutores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondutorDto> findById(@PathVariable(name = "id") Long id) {
        CondutorDto condutorDto = CondutorDto.from(this.condutorService.findById(id));
        return ResponseEntity.ok(condutorDto);
    }

    @PostMapping
    public ResponseEntity<CondutorDto> insert(@Valid @RequestBody CondutorDto condutorDto) {
        CondutorDto condutor = this.condutorService.insert(condutorDto);
        return new ResponseEntity<CondutorDto>(condutor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CondutorDto> update(@PathVariable(name = "id") Long id, @RequestBody CondutorDto condutorDto) {
        return ResponseEntity.ok(this.condutorService.update(id, condutorDto));
    }

    @DeleteMapping
    @PaymentPath
    public ResponseEntity<Void> delete(@RequestHeader(name = "condutorId") Long id) {
        this.condutorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
