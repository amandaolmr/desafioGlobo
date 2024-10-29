package com.globo.desafioGlobo.Produto.controller;

import com.globo.desafioGlobo.Produto.model.Produto;
import com.globo.desafioGlobo.Produto.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/globo/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @Operation(summary = "Salvar um Produto", description = "Realiza cadastro de um produto")
    public ResponseEntity<Produto>  criarProduto(@RequestBody Produto produto) {
        return  new ResponseEntity<>( produtoService.criarProduto(produto), HttpStatus.OK);
    }


    @GetMapping
    @Operation(summary = "Consulta de Todos os Produto", description = "Realiza consulta de Todos os produto")
    public ResponseEntity<List<Produto>> obterTodosProdutos() {
        List<Produto> produtos = produtoService.obterTodosProdutos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/{idProduto}")
    @Operation(summary = "Consulta de Produto por Id", description = "Realiza consulta de produto por Id")
    public ResponseEntity<Produto> obterProdutoPorId(@PathVariable Long idProduto) {
        Produto produto = produtoService.obterProdutoPorId(idProduto).orElse(null);
        if (produto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @PutMapping("/{idProduto}")
    @Operation(summary = "Atualizar Produto", description = "Realiza atualização de produto por Id")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long idProduto, @RequestBody Produto produto) {
        Produto produtoAtualizado = produtoService.atualizarProduto(produto, idProduto);
        if (!produtoService.obterProdutoPorId(idProduto).isPresent()) { // Verificando se o Produto com o Id existe, antes de atualizar
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{idProduto}")
    @Operation(summary = "Excluir Produto", description = "Realiza Exclusão de produto por Id")
    public ResponseEntity<Produto> excluiProduto(@PathVariable Long idProduto) {
        produtoService.excluirProdutoPorId(idProduto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
