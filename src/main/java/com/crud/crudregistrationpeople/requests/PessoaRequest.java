package com.crud.crudregistrationpeople.requests;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import java.time.LocalDate;
import java.util.List;


public class PessoaRequest {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @CPF(message = "CPF inválido")
    @NotEmpty(message = "O CPF é obrigatório")
    private String cpf;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Past
    private LocalDate dataNascimento;

    @Valid
    @NotEmpty(message = "A lista de contatos não pode estar vazia")
    private List<ContatoRequest> contatos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<ContatoRequest> getContatos() {
        return contatos;
    }

    public void setContatos(List<ContatoRequest> contatos) {
        this.contatos = contatos;
    }
}