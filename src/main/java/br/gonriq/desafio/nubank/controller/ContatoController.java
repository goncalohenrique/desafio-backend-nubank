package br.gonriq.desafio.nubank.controller;

import br.gonriq.desafio.nubank.dto.ContatoRequestDTO;
import br.gonriq.desafio.nubank.model.Clientes;
import br.gonriq.desafio.nubank.model.Contato;
import br.gonriq.desafio.nubank.repository.ClientesRepository;
import br.gonriq.desafio.nubank.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @PostMapping
    public ResponseEntity<?> cadContatos(@RequestBody ContatoRequestDTO contDto) {
        Optional<Clientes> clientesOpt = clientesRepository.findById(contDto.getIdcliente());
        if (clientesOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não encontrado!");
        }

        Contato contato = new Contato();
        contato.setClientes(clientesOpt.get());
        contato.setTelefone(contDto.getTelefone());
        contato.setEmail(contDto.getEmail());
        contatoRepository.save(contato);
        return ResponseEntity.status(HttpStatus.CREATED).body(contato);

    }

}
