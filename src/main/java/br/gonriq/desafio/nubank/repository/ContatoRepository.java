package br.gonriq.desafio.nubank.repository;

import br.gonriq.desafio.nubank.model.Contatos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contatos, Long> {
}
