package com.globo.desafioGlobo.stubs;

import com.globo.desafioGlobo.Produto.model.Produto;

public class ProdutoStub {

    public static Produto buildProduto(Long id, String nome, String descricao, double preco){
        return Produto.builder()
                .id(id)
                .nome(nome)
                .descricao(descricao)
                .preco(preco)
                .build();
    }
}
