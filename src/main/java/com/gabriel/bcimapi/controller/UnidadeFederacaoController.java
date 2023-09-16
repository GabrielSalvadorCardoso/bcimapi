package com.gabriel.bcimapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gabriel.bcimapi.model.UnidadeFederacao;
import com.gabriel.bcimapi.serializer.UnidadeFederacaoSerializer;
import com.gabriel.bcimapi.service.UnidadeFederacaoService;

@RestController
@RequestMapping(UnidadeFederacaoController.UNIDADE_FEDERACAO_COLLECTION_PATH)
public class UnidadeFederacaoController {
    public static final String UNIDADE_FEDERACAO_COLLECTION_PATH = "unidade-federacao";
    @Autowired
    private UnidadeFederacaoService service;

    @GetMapping
    public ResponseEntity<String> findAll() throws JsonMappingException, JsonProcessingException {
        List<UnidadeFederacao> ufs = this.service.findAll();
        String ufJson = UnidadeFederacaoSerializer.toJson(ufs);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ufJson);
    }

    // http://localhost:8980/unidade-federacao/55615
    @GetMapping(value = "/{id}")
    public ResponseEntity<String> findById(@PathVariable Long id) throws JsonMappingException, JsonProcessingException {
        Optional<UnidadeFederacao> ufResult = this.service.findById(id);
        UnidadeFederacao uf = ufResult.get();
        String ufJson = UnidadeFederacaoSerializer.toJson(uf);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ufJson);
    }
}
