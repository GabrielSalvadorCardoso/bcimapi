package com.gabriel.bcimapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gabriel.bcimapi.model.Capital;
import com.gabriel.bcimapi.serializer.CapitalSerializer;
import com.gabriel.bcimapi.service.CapitalService;

@RestController
@RequestMapping(CapitalController.CAPITAL_COLLECTION_PATH)
public class CapitalController {
    public static final String CAPITAL_COLLECTION_PATH = "capital";

    @Autowired
    private CapitalService service;

    @GetMapping
    public ResponseEntity<String> findAll() throws JsonMappingException, JsonProcessingException {
        List<Capital> capitais = this.service.findAll();
        String capitaisStr = CapitalSerializer.toJson(capitais);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(capitaisStr);
    }

    // http://localhost:8080/capital/295765
    @GetMapping(value = "/{id}")
    public ResponseEntity<String> findById(@PathVariable Long id) throws JsonMappingException, JsonProcessingException {
        Optional<Capital> capitalResult = this.service.findById(id);
        Capital capital = capitalResult.get();
        String capitalJson = CapitalSerializer.toJson(capital);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(capitalJson);
    }

    /*
    @GetMapping
	public Page<Capital> getCityPage(Pageable pageable){
		return service.findAll(pageable);
	}
     */
    
}
