package com.crud.crudregistrationpeople.services;

import com.crud.crudregistrationpeople.domain.Contato;
import com.crud.crudregistrationpeople.domain.Pessoa;
import com.crud.crudregistrationpeople.repositories.ContatoRepository;
import com.crud.crudregistrationpeople.repositories.PessoaRepository;

import com.crud.crudregistrationpeople.requests.*;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    public Pessoa getPessoaById(Long id) {
        return pessoaRepository.findById(id).orElse(null);
    }

    public List<Pessoa> getAllPessoas() {
        return pessoaRepository.findAll();
    }

    @Transactional
    public Pessoa createPessoaComContatos(PessoaRequest pessoaRequest) {
        Pessoa novaPessoa = new Pessoa();
        novaPessoa.setNome(pessoaRequest.getNome());
        novaPessoa.setCpf(pessoaRequest.getCpf());
        novaPessoa.setDataNascimento(pessoaRequest.getDataNascimento());

        List<Contato> contatos = new ArrayList<>();
        for (ContatoRequest contatoRequest : pessoaRequest.getContatos()) {
            Contato novoContato = new Contato();
            novoContato.setEmail(contatoRequest.getEmail());
            novoContato.setTelefone(contatoRequest.getTelefone());
            novoContato.setPessoa(novaPessoa);
            contatos.add(novoContato);
        }

        novaPessoa.setContatos(contatos);

        Pessoa pessoaSalva = pessoaRepository.save(novaPessoa);

        return pessoaSalva;
    }

    public Pessoa updatePessoa(Long id, PessoaRequest pessoaRequest) {
        Pessoa pessoaExistente = pessoaRepository.findById(id).orElse(null);
        if (pessoaExistente != null) {
            pessoaExistente.setNome(pessoaRequest.getNome());
            pessoaExistente.setCpf(pessoaRequest.getCpf());
            pessoaExistente.setDataNascimento(pessoaRequest.getDataNascimento());

            List<ContatoRequest> novosContatos = pessoaRequest.getContatos();
            List<Contato> contatosExistente = pessoaExistente.getContatos();

            for (ContatoRequest novoContato : novosContatos) {
                boolean contatoAtualizado = false;
                for (Contato contato : contatosExistente) {
                    if (contato.getId() != null) {
                        contato.setEmail(novoContato.getEmail());
                        contato.setTelefone(novoContato.getTelefone());
                        contatoAtualizado = true;
                        break;
                    }
                }

                if (!contatoAtualizado) {
                    // Criar novo contato
                    Contato contato = new Contato();
                    contato.setEmail(novoContato.getEmail());
                    contato.setTelefone(novoContato.getTelefone());
                    contato.setPessoa(pessoaExistente);
                    contatosExistente.add(contato);
                }
            }

            return pessoaRepository.save(pessoaExistente);
        }
        return null;
    }




    public boolean deletePessoa(Long id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isPresent()) {
            pessoaRepository.delete(pessoaOptional.get());
            return true;
        }
        return false;
    }

}