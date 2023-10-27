package br.com.fiap.parking.meter.condutor.service;

import br.com.fiap.parking.meter.condutor.domain.Condutor;
import br.com.fiap.parking.meter.condutor.dto.CondutorDto;
import br.com.fiap.parking.meter.condutor.repository.CondutorRepository;
import br.com.fiap.parking.meter.exception.ControllerNotFoundException;
import br.com.fiap.parking.meter.exception.EntityNotFoundException;
import br.com.fiap.parking.meter.exception.GenericException;
import br.com.fiap.parking.meter.veiculo.dto.VeiculoCondutorDTO;
import br.com.fiap.parking.meter.veiculo.dto.VeiculoDto;
import br.com.fiap.parking.meter.veiculo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CondutorService {

    private final CondutorRepository condutorRepository;
    private final VeiculoRepository veiculoRepository;


    @Autowired
    public CondutorService(CondutorRepository condutorRepository, VeiculoRepository veiculoRepository) {
        this.condutorRepository = condutorRepository;
        this.veiculoRepository = veiculoRepository;
    }


    @Transactional(readOnly = true)
    public Page<CondutorDto> findAll(PageRequest pageRequest) {
        var condutores = condutorRepository.findAll(pageRequest);
        return condutores.map(CondutorDto::from);
    }


    @Transactional(readOnly = true)
    public Condutor findById(Long id) {
        return this.condutorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Condutor não encontrado."));
    }


    @Transactional
    public CondutorDto insert(CondutorDto condutorDto) {
        Condutor condutor = this.condutorRepository.save(condutorDto.toDomain());
        return CondutorDto.from(condutor);
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


    @Transactional
    public void delete(Long id) {
        try {
            this.condutorRepository.deleteById(id);
        } catch (GenericException e) {
            throw new ControllerNotFoundException("Condutor/Veículo não encontrados");
        }
    }
}
