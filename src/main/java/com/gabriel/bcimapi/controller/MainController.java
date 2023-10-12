package com.gabriel.bcimapi.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gabriel.bcimapi.serializer.MainSerializer;

// The landing page of an OGC Web API serves as the root node of the API resource tree and provides the information needed to navigate all the resources exposed through the API. 
@RestController
@RequestMapping("/") // implements: https://docs.ogc.org/is/17-069r4/17-069r4.html#_api_landing_page
public class MainController {
    @Autowired
    private ServerProperties serverProperties;

    private final ResourceLoader resourceLoader;

    public MainController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping
    public ResponseEntity<String> landingPage(@RequestParam(required = false, name="f") String f) throws JsonProcessingException, MalformedURLException {
        if(f != null && f.equals("html")) {
            return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(MainSerializer.toHTML(this.serverProperties));
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(MainSerializer.toJson(this.serverProperties));
    }

    @GetMapping(value="conformance") // implements: https://docs.ogc.org/is/17-069r4/17-069r4.html#_declaration_of_conformance_classes
    public ResponseEntity<String> conformance() throws JsonProcessingException, MalformedURLException {   
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(MainSerializer.conformance());
    }

    @GetMapping(value="collections")
    public ResponseEntity<String> collections() throws JsonProcessingException, MalformedURLException {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(MainSerializer.collections());
    }

    @GetMapping(value="api") // implements: https://docs.ogc.org/is/17-069r4/17-069r4.html#_api_definition_2
    public ResponseEntity<String> api() throws IOException {
        Resource resource = this.resourceLoader.getResource("classpath:/api-description.yaml");
        if (resource.exists()) {
            return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType("application/yaml"))
                .header("Content-Disposition", "inline; filename=api.yml")
                .body(new InputStreamResource(resource.getInputStream()).getContentAsString(Charset.forName("UTF8")));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="api.html")
    public ResponseEntity<String> apiHTML() {
        //Resource resource = this.resourceLoader.getResource("classpath:/api-description.yaml");
        //String content = new InputStreamResource(resource.getInputStream()).getContentAsString(Charset.forName("UTF8"));

        String markdownValue = "<!DOCTYPE html><html><body><code>---\n" +
                "key: value\n" +
                "list:\n" +
                "  - value 1\n" +
                "  - value 2\n" +
                "literal: |\n" +
                "  this is literal value.\n" +
                "\n" +
                "  literal values 2\n" +
                "---\n" +
                "\n" +
                "# Java Tutorial</code></body></html>";
        //String htmlValue = MainSerializer.convertMarkdownToHTML(markdownValue);

        
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(markdownValue);
    }
}
