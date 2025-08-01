package br.gonriq.desafio.nubank.dto;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Email é obrigatório!")
    private String email;
    @NotBlank(message = "Telefone é obrigatório!")
    private String telefone;

}
