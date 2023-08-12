package com.crud.crudregistrationpeople.controllers;

import com.crud.crudregistrationpeople.domain.Pessoa;
import com.crud.crudregistrationpeople.repositories.PessoaRepository;
import com.crud.crudregistrationpeople.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.getPessoaById(id);
        if (pessoa != null) {
            return ResponseEntity.ok(pessoa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PostMapping
//    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
//        Pessoa novaPessoa = pessoaService.createPessoa(pessoa);
//        return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
//    }

    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
        Pessoa novaPessoa = pessoaService.createPessoaComContatos(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Pessoa pessoaAtualizada = pessoaService.updatePessoa(id, pessoa);
        if (pessoaAtualizada != null) {
            return ResponseEntity.ok(pessoaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        boolean deleted = pessoaService.deletePessoa(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
