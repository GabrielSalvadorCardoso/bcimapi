package com.gabriel.bcimapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.bcimapi.model.UnidadeFederacao;

@Repository
public interface UnidadeFederacaoRepository extends JpaRepository<UnidadeFederacao, Long> {
    List<UnidadeFederacao> findAll();
    List<UnidadeFederacao> findBySiglaOrNome(String sigla, String nome);
    List<UnidadeFederacao> findBySigla(String sigla);
    List<UnidadeFederacao> findByNome(String nome);
}
