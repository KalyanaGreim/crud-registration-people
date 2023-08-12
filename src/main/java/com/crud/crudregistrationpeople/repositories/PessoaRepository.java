package com.crud.crudregistrationpeople.repositories;

import com.crud.crudregistrationpeople.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
