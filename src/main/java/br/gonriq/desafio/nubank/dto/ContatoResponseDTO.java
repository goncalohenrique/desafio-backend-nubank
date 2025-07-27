package br.gonriq.desafio.nubank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContatoResponseDTO {
    private Long idcontato;
    private String telefone;
    private String email;
    private Long idcliente;
}
