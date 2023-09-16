package com.gabriel.bcimapi.controller;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gabriel.bcimapi.serializer.MainSerializer;

// implements: https://docs.ogc.org/is/17-069r4/17-069r4.html#landing_page
// The landing page of an OGC Web API serves as the root node of the API resource tree and provides the information needed to navigate all the resources exposed through the API. 
@RestController
@RequestMapping("/")
public class MainController {
    @Autowired
    private ServerProperties serverProperties;

    @GetMapping
    public ResponseEntity<String> landingPage() throws JsonProcessingException, MalformedURLException {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(MainSerializer.toJson(this.serverProperties));
    }
}
