package br.gonriq.desafio.nubank.controller;

import br.gonriq.desafio.nubank.dto.ClientesDTO;
import br.gonriq.desafio.nubank.dto.ClientesReponseDTO;
import br.gonriq.desafio.nubank.dto.ContatoResponseDTO;
import br.gonriq.desafio.nubank.model.Clientes;
import br.gonriq.desafio.nubank.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class CliienteController {

    @Autowired
    private ClientesService clientesService;

    @PostMapping
    public ResponseEntity<Clientes> CadCliente(@RequestBody ClientesDTO clidto)
    {
        Clientes clienteSalvo = clientesService.CadastrarCliente(clidto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<ClientesReponseDTO>> listarTodos()
    {
        return ResponseEntity.ok(clientesService.ListarClientes());
    }

    @GetMapping("/{id}/contatos")
    public ResponseEntity<List<ContatoResponseDTO>> ListarContatos(@PathVariable Long id)
    {
        return ResponseEntity.ok(clientesService.listarContatoPorCliente(id));
    }


}
