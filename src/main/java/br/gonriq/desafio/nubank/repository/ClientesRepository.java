package br.gonriq.desafio.nubank.repository;

import br.gonriq.desafio.nubank.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {
}
