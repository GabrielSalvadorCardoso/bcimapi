package com.gabriel.bcimapi.controller;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gabriel.bcimapi.model.UnidadeFederacao;
import com.gabriel.bcimapi.serializer.UnidadeFederacaoSerializer;
import com.gabriel.bcimapi.service.UnidadeFederacaoService;

@RestController
@RequestMapping(UnidadeFederacaoController.UNIDADE_FEDERACAO_COLLECTION_PATH)
public class UnidadeFederacaoController {
    public static final String UNIDADE_FEDERACAO_COLLECTION_ID = "unidade-federacao";
    public static final String UNIDADE_FEDERACAO_COLLECTION_PATH = "collections/"+UNIDADE_FEDERACAO_COLLECTION_ID;
    @Autowired
    private UnidadeFederacaoService service;

    @GetMapping(value = "/items")
    public ResponseEntity<String> findAll(@RequestParam Map<String, String> param) throws JsonMappingException, JsonProcessingException {
        // if(param.size() == 2) {

        // } else if(param.get())
        String sigla = param.get("sigla");
        String nome = param.get("nome");

        List<UnidadeFederacao> ufs;

        if(sigla != null && nome != null) {
            ufs = this.service.findBySiglaOrNome(sigla, nome);
        } else if(sigla != null) {
            ufs = this.service.findBySigla(sigla);
        } else if(nome != null) {
            ufs = this.service.findByNome(nome);
        } else {
            ufs = this.service.findAll();
        }
        String ufJson = UnidadeFederacaoSerializer.toJson(ufs);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ufJson);
    }

    // http://localhost:8980/unidade-federacao/55615
    @GetMapping(value = "/items/{id}")
    public ResponseEntity<String> findById(@PathVariable Long id) throws JsonMappingException, JsonProcessingException {
        Optional<UnidadeFederacao> ufResult = this.service.findById(id);
        UnidadeFederacao uf = ufResult.get();
        String ufJson = UnidadeFederacaoSerializer.toJson(uf);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ufJson);
    }

    @GetMapping(value = "")
    public ResponseEntity<String> collection() throws JsonProcessingException, MalformedURLException {
        String content = UnidadeFederacaoSerializer.collection();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(content);
    }
    
}
