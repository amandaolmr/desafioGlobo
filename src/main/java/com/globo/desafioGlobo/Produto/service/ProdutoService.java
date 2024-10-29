package com.globo.desafioGlobo.Produto.service;

import com.globo.desafioGlobo.Produto.model.Produto;
import com.globo.desafioGlobo.Produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository; // Injetando o produtoRepository

    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> obterTodosProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> obterProdutoPorId(Long idProduto){
        return produtoRepository.findById(idProduto);
    }

    public Produto atualizarProduto(Produto produto, Long idProduto) {
        produto.setId(idProduto);
        return produtoRepository.save(produto);
    }

    public void excluirProdutoPorId(Long idProduto) {
        produtoRepository.deleteById(idProduto);
    }
}
