package com.crud.crudregistrationpeople.repositories;

import com.crud.crudregistrationpeople.domain.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
