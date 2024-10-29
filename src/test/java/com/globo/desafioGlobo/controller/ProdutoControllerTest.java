package com.globo.desafioGlobo.controller;

import com.globo.desafioGlobo.Produto.controller.ProdutoController;
import com.globo.desafioGlobo.Produto.model.Produto;
import com.globo.desafioGlobo.Produto.service.ProdutoService;
import com.globo.desafioGlobo.stubs.ProdutoStub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @Test
    void criarProduto() throws Exception {
        Produto produto =  ProdutoStub.buildProduto(1l, "GloboPlay", "descrição Globoplay", 10);
        when(produtoService.criarProduto(any(Produto.class))).thenReturn(produto);

        this.mockMvc.perform(post("/api/globo/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"GloboPlay\",\"descricao\":\"descrição Globoplay\",\"preco\":10}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void obterTodosProdutos() throws Exception {
        Produto produto1 = ProdutoStub.buildProduto(1l, "Globoplay", "descrição Globoplay", 10);
        Produto produto2 = ProdutoStub.buildProduto(2l, "Telecine", "Descrição teste B", 20.0);
        when(produtoService.obterTodosProdutos()).thenReturn(Arrays.asList(produto1, produto2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/globo/produtos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Globoplay"))
                .andExpect(jsonPath("$[1].nome").value("Telecine"));
    }

    @Test
    void obterProdutoPorId() throws Exception {
        Produto produto = ProdutoStub.buildProduto(1l, "GloboPlay", "descrição Globoplay", 15.0);
        when(produtoService.obterProdutoPorId(1L)).thenReturn(Optional.of(produto));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/globo/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("GloboPlay"));
    }

    @Test
    void atualizarProduto() throws Exception {
        Produto produtoAtualizado = ProdutoStub.buildProduto(1l, "Produto Telecine Atualizado", "Descrição Telecine Atualizada", 15.0);
        when(produtoService.atualizarProduto(any(Produto.class), eq(1L))).thenReturn(produtoAtualizado);
        when(produtoService.obterProdutoPorId(eq(1L))).thenReturn(Optional.of(produtoAtualizado)); // mockando a consulta do produto

        mockMvc.perform(MockMvcRequestBuilders.put("/api/globo/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Produto Telecine Atualizado\",\"descricao\":\"Descrição Telecine Atualizada\",\"preco\":15.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Produto Telecine Atualizado"));
    }

}
