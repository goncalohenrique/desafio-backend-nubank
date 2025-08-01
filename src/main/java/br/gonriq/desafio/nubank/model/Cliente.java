package br.gonriq.desafio.nubank.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
//funções do lombok, requisitadas no desafuo:
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idcliente;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "clientes", cascade =  CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Contatos> contatos = new ArrayList<Contatos>();

}
