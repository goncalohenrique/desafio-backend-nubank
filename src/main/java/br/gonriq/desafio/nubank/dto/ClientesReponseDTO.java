package br.gonriq.desafio.nubank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientesReponseDTO {

    private Long idcliente;
    private String nome;
    private List<ContatoResponseDTO> contatos;
}
