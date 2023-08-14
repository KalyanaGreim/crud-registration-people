package com.crud.crudregistrationpeople.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class ContatoRequest {
    @Email(message = "Email invalido")
    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;

    private PessoaRequest pessoa;

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

    public PessoaRequest getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaRequest pessoa) {
        this.pessoa = pessoa;
    }
}