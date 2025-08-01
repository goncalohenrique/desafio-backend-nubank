package br.gonriq.desafio.nubank.repository;

import br.gonriq.desafio.nubank.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Cliente, Long> {
}
