package com.gabriel.bcimapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gabriel.bcimapi.model.UnidadeFederacao;
import com.gabriel.bcimapi.repository.UnidadeFederacaoRepository;

@Service
public class UnidadeFederacaoService {
    
    @Autowired
    private UnidadeFederacaoRepository repository;

    public Optional<UnidadeFederacao> findById(Long id) {
        Optional<UnidadeFederacao> ufResult = this.repository.findById(id);
        return ufResult;
    }

    public List<UnidadeFederacao> findAll() {
        return this.repository.findAll();
    }
}
