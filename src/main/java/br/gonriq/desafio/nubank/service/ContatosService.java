package br.gonriq.desafio.nubank.service;

import br.gonriq.desafio.nubank.dto.ContatoRequestDTO;
import br.gonriq.desafio.nubank.model.Cliente;
import br.gonriq.desafio.nubank.model.Contatos;
import br.gonriq.desafio.nubank.repository.ClientesRepository;
import br.gonriq.desafio.nubank.repository.ContatoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public class ContatosService {

    ClientesRepository clientesRepository;
    ContatoRepository contatoRepository;

    public Contatos cadastrarContato(ContatoRequestDTO contDto) {
        Optional<Cliente> clientesOpt = clientesRepository.findById(contDto.getIdcliente());

        if (clientesOpt.isEmpty()) {
            throw new IllegalArgumentException("Cliente não encontrado!");
        }

        Contatos contato = new Contatos();
        contato.setClientes(clientesOpt.get());
        contato.setTelefone(contDto.getTelefone());
        contato.setEmail(contDto.getEmail());

        return contatoRepository.save(contato);
    }


}
