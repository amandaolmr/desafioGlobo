package com.globo.desafioGlobo.Produto.repository;

import com.globo.desafioGlobo.Produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
