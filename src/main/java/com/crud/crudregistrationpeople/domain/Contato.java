package com.crud.crudregistrationpeople.domain;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "contato")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String telefone;

    public Contato(Long id, String email, String telefone, Pessoa pessoa) {
        this.id = id;
        this.email = email;
        this.telefone = telefone;
        this.pessoa = pessoa;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
