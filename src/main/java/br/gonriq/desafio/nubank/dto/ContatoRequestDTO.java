package br.gonriq.desafio.nubank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContatoRequestDTO {

    private Long idcliente;
    private Long idcontato;
    private String email;
    private String telefone;

}
