package br.gonriq.desafio.nubank.repository;

import br.gonriq.desafio.nubank.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
