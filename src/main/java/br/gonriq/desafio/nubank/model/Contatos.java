package br.gonriq.desafio.nubank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//funções do lombok, requisitadas no desafio:
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table
public class Contatos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcontato;

    @Column(nullable = false)
    private String telefone;
    @Column(nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "clientes_idcliente")
    @JsonBackReference
    private Cliente clientes;
}
