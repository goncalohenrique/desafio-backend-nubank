package br.gonriq.desafio.nubank.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientesRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    private List<ContatoRequestDTO> contatos;
}
