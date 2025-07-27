package br.gonriq.desafio.nubank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

//funções do lombok, requisitadas no desafuo:
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcontato;

    private String telefone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "cliente_idcliente")
    @JsonBackReference
    private Clientes clientes;
}
