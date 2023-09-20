package br.com.fiap.parking.meter.condutor.service;

import br.com.fiap.parking.meter.condutor.domain.Condutor;
import br.com.fiap.parking.meter.condutor.dto.CondutorDto;
import br.com.fiap.parking.meter.condutor.repository.CondutorRepository;
import br.com.fiap.parking.meter.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CondutorService {

    @Autowired
    private CondutorRepository condutorRepository;

    public Page<CondutorDto> findAll(int page, int pageSize) {

        Page<Condutor> result = this.condutorRepository.findAll(PageRequest.of(page, pageSize));
        return result.map(CondutorDto::from);
    }

    @Transactional
    public CondutorDto insert(CondutorDto condutorDto) {
        Condutor condutor = this.condutorRepository.save(condutorDto.toDomain());
        return CondutorDto.from(condutor);
    }

    @Transactional
    public void delete(Long id) {
        this.condutorRepository.deleteById(id);
    }

    public Condutor findById(Long id) {
        return this.condutorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Condutor não encontrado."));
    }

    @Transactional
    public CondutorDto update(Long id, CondutorDto condutorDto) {

        Condutor condutor = findById(id);

        condutor.setNome(condutorDto.nome());
        condutor.setEmail(condutorDto.email());
        condutor.setEndereco(condutorDto.endereco().toDomain());
        condutor.setTelefone(condutorDto.telefone());

        this.condutorRepository.save(condutor);

        return condutorDto;
    }
}
