package com.fatecpg.exemplopers.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatecpg.exemplopers.model.Funcionario;
import com.fatecpg.exemplopers.service.PessoaService;

@RestController
@RequestMapping("/meusite")
public class FuncionarioController {
    private final PessoaService pessoaService;

    public FuncionarioController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }
    
    @PostMapping("/funcionarios")
    public ResponseEntity<Funcionario> postMethodName(@RequestBody Funcionario funcionario) {
        Funcionario funcionarioCriado = (Funcionario)pessoaService.salvarPessoa(funcionario);
        return ResponseEntity.ok(funcionarioCriado);
    }

    @GetMapping("/funcionarios")
    public ResponseEntity<List<Funcionario>> allFuncionarios() {
        List<Funcionario> funcionarios = pessoaService.getAllPessoas()
            .stream().filter(p -> p instanceof Funcionario).map(p -> (Funcionario) p).toList();
        return ResponseEntity.ok(funcionarios);
    }
    
    @PutMapping("/funcionarios/{id}")
    public ResponseEntity<Funcionario> updateUser(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        return pessoaService.updatePessoa(id, funcionario)
                .map(p -> ResponseEntity.ok((Funcionario)p))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/funcionarios/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
        boolean deleted = pessoaService.deletePessoa(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }    
}
