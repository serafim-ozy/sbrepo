package com.fatecpg.exemplopers.controller;

import org.springframework.web.bind.annotation.RestController;
import com.fatecpg.exemplopers.model.Pessoa;
import com.fatecpg.exemplopers.service.PessoaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/meusite")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }
    
    @PostMapping("/pessoas")
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED) // status 201 = Created
    public ResponseEntity<Pessoa> postMethodName(@RequestBody Pessoa pessoa) {
        Pessoa pessoaCriada = pessoaService.salvarPessoa(pessoa);
        return ResponseEntity.ok(pessoaCriada);
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> allPessoas() {
        List<Pessoa> pessoas = pessoaService.getAllPessoas();
        return ResponseEntity.ok(pessoas);
    }
    
    @PutMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> updateUser(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        return pessoaService.updatePessoa(id, pessoa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/pessoas/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        boolean deleted = pessoaService.deletePessoa(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
