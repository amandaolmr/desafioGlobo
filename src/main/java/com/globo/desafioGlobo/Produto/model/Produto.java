package com.globo.desafioGlobo.Produto.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //  Anotação onde tem várias funcionalidades do Lombok
@Builder // permite a criação de objetos de maneira mais fluida
@AllArgsConstructor // Compativel com instancias completas
@NoArgsConstructor // Compativel com instancias vazias
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)  // Define o campo como somente leitura para a serialização JSON
    private Long id;
    private String nome;
    private String descricao;
    private double preco;
}
