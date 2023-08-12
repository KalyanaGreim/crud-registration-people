package com.crud.crudregistrationpeople.services;

import com.crud.crudregistrationpeople.domain.Contato;
import com.crud.crudregistrationpeople.domain.Pessoa;
import com.crud.crudregistrationpeople.repositories.ContatoRepository;
import com.crud.crudregistrationpeople.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    // Método para buscar uma Pessoa por ID
    public Pessoa getPessoaById(Long id) {
        return pessoaRepository.findById(id).orElse(null);
    }

    // Método para criar uma nova Pessoa
    public Pessoa createPessoa(Pessoa pessoa) {
        // Implementar a lógica de validação e criação aqui
        return pessoaRepository.save(pessoa);
    }

    // Método para atualizar uma Pessoa existente
    public Pessoa updatePessoa(Long id, Pessoa pessoa) {
        // Implementar a lógica de validação e atualização aqui
        return pessoaRepository.save(pessoa);
    }

    // Método para excluir uma Pessoa
    public boolean deletePessoa(Long id) {
        // Implementar a lógica de exclusão aqui
        return false;
    }

    public Pessoa createPessoaComContatos(Pessoa pessoa) {
        Pessoa novaPessoa = pessoaRepository.save(pessoa); // Salva a pessoa para obter o ID gerado

        for (Contato contato : novaPessoa.getContatos()) {
            contato.setPessoa(novaPessoa); // Associa a pessoa ao contato
            contatoRepository.save(contato); // Salva o contato com a associação correta
        }

        return novaPessoa;
    }
}
