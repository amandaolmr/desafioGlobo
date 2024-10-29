package com.globo.desafioGlobo.service;

import com.globo.desafioGlobo.Produto.model.Produto;
import com.globo.desafioGlobo.Produto.repository.ProdutoRepository;
import com.globo.desafioGlobo.Produto.service.ProdutoService;
import com.globo.desafioGlobo.stubs.ProdutoStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarProduto() {
        Produto produto = ProdutoStub.buildProduto(1l, "GloboPlay", "teste Globoplay", 20);
        when(produtoRepository.save(produto)).thenReturn(produto);

        Produto novoProduto = produtoService.criarProduto(produto);
        assertNotNull(novoProduto);
        assertEquals("GloboPlay", novoProduto.getNome());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void obterTodosProdutos() {
        Produto produto1 = ProdutoStub.buildProduto(1l, "Premiere", "teste Premiere", 25);
        Produto produto2 = ProdutoStub.buildProduto(1l, "GloboPlay", "teste Globoplay", 20);
        when(produtoRepository.findAll()).thenReturn(Arrays.asList(produto1, produto2));

        List<Produto> produtos = produtoService.obterTodosProdutos();
        assertEquals(2, produtos.size());
        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    void obterProdutoPorId() {
        Produto produto = ProdutoStub.buildProduto(1l, "Premiere", "teste Premiere", 25);
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        Optional<Produto> produtoEncontrado = produtoService.obterProdutoPorId(1L);
        assertTrue(produtoEncontrado.isPresent());
        assertEquals("Premiere", produtoEncontrado.get().getNome());
        verify(produtoRepository, times(1)).findById(1L);
    }

    @Test
    void atualizarProduto() {
        Produto produtoExistente = ProdutoStub.buildProduto(1l, "Premiere", "teste Premiere", 25);
        Produto produtoAtualizado = ProdutoStub.buildProduto(1L, "Produto Teste Atualizado", "Descrição Atualizada", 15.0);
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produtoExistente));
        when(produtoRepository.save(produtoAtualizado)).thenReturn(produtoAtualizado);

        Produto resultado = produtoService.atualizarProduto(produtoAtualizado, 1L);
        assertEquals("Produto Teste Atualizado", resultado.getNome());
        verify(produtoRepository, times(1)).save(produtoAtualizado);
    }

    @Test
    void excluirProduto() {
        Produto produto = ProdutoStub.buildProduto(1l, "GloboPlay", "teste Globoplay", 10);
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        doNothing().when(produtoRepository).deleteById(1L);

        produtoService.excluirProdutoPorId(1L);
        verify(produtoRepository, times(1)).deleteById(1L);
    }
}
