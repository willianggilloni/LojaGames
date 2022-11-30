package com.generation.games.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.games.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    public List<Produto> findAllByDescricaoContainsIgnoreCase(@Param("descricao") String descricao);

    public List<Produto> findAllByValorGreaterThanOrderByValor(Double valor);

    public List<Produto> findAllByValorIsLessThanOrderByValor(Double valor);



}