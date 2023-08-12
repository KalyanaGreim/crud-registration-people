package com.crud.crudregistrationpeople.domain;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @CPF
    private String cpf;

    @Past
    private LocalDate dataNascimento;

    public Pessoa(Long id, String nome, String cpf, LocalDate dataNascimento, List<Contato> contatos) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.contatos = contatos;
    }

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contato> contatos;

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public Long getId() {
        return id;
    }

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

    public void setId(Long id) {
        this.id = id;
    }
}
