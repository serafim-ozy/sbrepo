package com.fatecpg.exemplopers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.fatecpg.exemplopers.model.Pessoa;
import com.fatecpg.exemplopers.repository.PessoaRepository;

@Service
public class PessoaService {
    
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa salvarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> getAllPessoas() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> updatePessoa(Long id, Pessoa newPessoaData) {
        return pessoaRepository.findById(id).map(pessoa -> {
            pessoa.setNome(newPessoaData.getNome());
            return pessoaRepository.save(pessoa);
        });
    }

    public boolean deletePessoa(Long id) {
        if (pessoaRepository.existsById(id)) {
            pessoaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
