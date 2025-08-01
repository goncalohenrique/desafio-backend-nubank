package br.gonriq.desafio.nubank.controller;

import br.gonriq.desafio.nubank.dto.ClientesRequestDTO;
import br.gonriq.desafio.nubank.dto.ContatoRequestDTO;
import br.gonriq.desafio.nubank.model.Cliente;
import br.gonriq.desafio.nubank.model.Contatos;
import br.gonriq.desafio.nubank.repository.ClientesRepository;
import br.gonriq.desafio.nubank.service.ContatosService;
import jakarta.validation.Valid;
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


    ContatosService contatosService;

    @PostMapping
    public ResponseEntity<?> cadContatos(@RequestBody @Valid ContatoRequestDTO contDto) {
        try {
            Contatos contatoSalvo = contatosService.cadastrarContato(contDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(contatoSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
