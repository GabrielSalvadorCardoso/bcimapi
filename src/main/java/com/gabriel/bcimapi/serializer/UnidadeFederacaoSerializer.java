package com.gabriel.bcimapi.serializer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.wololo.geojson.GeoJSON;
import org.wololo.jts2geojson.GeoJSONWriter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.bcimapi.controller.UnidadeFederacaoController;
import com.gabriel.bcimapi.model.UnidadeFederacao;

public class UnidadeFederacaoSerializer {
    public static String toJson(List<UnidadeFederacao> ufs) throws JsonMappingException, JsonProcessingException {
        Map<String, Object> featureCollection = new HashMap<>();
        featureCollection.put("type", "FeatureCollection");
        List<Map<String, Object>> features = new ArrayList<>();

        for(UnidadeFederacao uf : ufs) {
            Map<String, Object> featureMapping = UnidadeFederacaoSerializer.toFeatureMapping(uf);
            features.add(featureMapping);
        }

        featureCollection.put("features", features);
        ObjectMapper objectMapperSerializer = new ObjectMapper();
        String jacksonData = objectMapperSerializer.writeValueAsString(featureCollection);

        return jacksonData.toString();
    }

    public static String toJson(UnidadeFederacao uf) throws JsonMappingException, JsonProcessingException {
		Map<String, Object> featureMapping = toFeatureMapping(uf);

        ObjectMapper objectMapperSerializer = new ObjectMapper();
        String jacksonData = objectMapperSerializer.writeValueAsString(featureMapping);

        return jacksonData.toString();
    }

    private static Map<String, Object> toFeatureMapping(UnidadeFederacao uf)
            throws JsonProcessingException, JsonMappingException {
        ObjectMapper objectMapper = new ObjectMapper();
		GeoJSONWriter writer = new GeoJSONWriter();

        GeoJSON json = writer.write(uf.getGeom());
        String jsonData = json.toString();

        Map<String, Object> geometry = objectMapper.readValue(jsonData, Map.class);
        Map<String, Object> geoJson = new HashMap<>();
        
        geoJson.put("type", "Feature");
        geoJson.put("geometry", geometry);

        Map<String, Object> props = new HashMap<>();
        props.put("id", uf.getIdObjeto());
        props.put("nome", uf.getNome());
        props.put("sigla", uf.getSigla());
        geoJson.put("properties", props);
        return geoJson;
    }

    public static Map<String, Object> generateSpatialExtent() {
        Map<String, Object> ufSpatialExtent = new HashMap<>();
        Map<String, Object> ufSpatialBBoxExtent = new HashMap<>();
        List<Double> uffirstBbox = new ArrayList<>();
        uffirstBbox.add(-73.99);
        uffirstBbox.add(-33.75);
        uffirstBbox.add(-29.29);
        uffirstBbox.add(5.27);
        List<List<Double>> ufbboxes = new ArrayList<>();
        ufbboxes.add(uffirstBbox);
        ufSpatialBBoxExtent.put("bbox", ufbboxes);
        ufSpatialExtent.put("spatial", ufSpatialBBoxExtent);
        return ufSpatialExtent;
    }

    private static boolean isNotDefaultPort(int port) {
        return port != MainSerializer.DEFAULT_HTTP_PORT && port != MainSerializer.DEFAULT_HTTPS_PORT;
    }

    private static String getSelfPath() throws MalformedURLException {
        URL selfUrl = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .encode()
        .build()
        .toUri()
        .toURL();
        StringBuilder selfSb = new StringBuilder();
        selfSb
        .append(selfUrl.getProtocol()).append(MainSerializer.URL_PROTOCOL_SEP)
        .append(selfUrl.getHost());
        if(isNotDefaultPort(selfUrl.getPort()) && selfUrl.getPort() != -1) {
            selfSb.append(MainSerializer.URL_HOST_SEP).append(selfUrl.getPort());
        }
        return selfSb.toString();
    }

    public static String collection() throws JsonProcessingException, MalformedURLException {
        Map<String, Object> contentMapper = new HashMap<>();
        contentMapper.put("id", UnidadeFederacaoController.UNIDADE_FEDERACAO_COLLECTION_ID);
        contentMapper.put("title", "BCIM - Unidade da Federação - 1:1 000 000 - 2016");
        contentMapper.put("description", "A classe Unidade da Federação faz parte da categoria Limites da Base Cartográfica Contínua do Brasil, ao milionésimo – 1: 1.000.000 (BCIM), é um conjunto de dados geoespaciais de referência, estruturados em bases de dados digitais, permitindo uma visão integrada do território nacional nesta escala. A classe foi gerada a partir da integração da vetorização das folhas da Carta Internacional do Mundo ao milionésimo – (CIM), estruturada, conforme a Especificação Técnica para a Estruturação de Dados Geoespaciais Vetoriais (ET-EDGV), correspondente aos elementos geográficos que representam o território nacional nesta escala. Possui atributos geométricos e semânticos associados a um banco de dados permitindo consultas espaciais e semânticas.");
        contentMapper.put("itemType", "feature");
        contentMapper.put("extent", generateSpatialExtent());

        List<Object> links = new ArrayList<>();

        Map<String, Object> selfLink = new HashMap<>();
        selfLink.put("type", MediaType.APPLICATION_JSON.toString());
        selfLink.put("rel", "self");
        selfLink.put("title", "Esse documento");
        selfLink.put("href", getSelfPath() + "/" + UnidadeFederacaoController.UNIDADE_FEDERACAO_COLLECTION_PATH);

        Map<String, Object> alternateLink = new HashMap<>();
        alternateLink.put("type", MediaType.APPLICATION_JSON.toString());
        alternateLink.put("rel", "alternate");
        alternateLink.put("title", "Esse documento em HTML");
        alternateLink.put("href", getSelfPath() + "/" + UnidadeFederacaoController.UNIDADE_FEDERACAO_COLLECTION_PATH + ".html");
        
        links.add(selfLink);
        links.add(alternateLink);

        contentMapper.put("links", links);
        ObjectMapper objectMapper = new ObjectMapper();
        String serialized = objectMapper.writeValueAsString(contentMapper);
        return serialized.toString();
    }


    /*
     * {
    "id": "unidade-federacao",
    "title": ,
    "description": "A classe Unidade da Federação faz parte da categoria Limites da Base Cartográfica Contínua do Brasil, ao milionésimo – 1: 1.000.000 (BCIM), é um conjunto de dados geoespaciais de referência, estruturados em bases de dados digitais, permitindo uma visão integrada do território nacional nesta escala. A classe foi gerada a partir da integração da vetorização das folhas da Carta Internacional do Mundo ao milionésimo – (CIM), estruturada, conforme a Especificação Técnica para a Estruturação de Dados Geoespaciais Vetoriais (ET-EDGV), correspondente aos elementos geográficos que representam o território nacional nesta escala. Possui atributos geométricos e semânticos associados a um banco de dados permitindo consultas espaciais e semânticas.",
    "extent": {
        "spatial": {
            "bbox": [
                [
                    3.37,
                    50.75,
                    7.21,
                    53.47
                ]
            ]
        
        }
    },
    "links": [
        {
            "type": "application/json",
            "rel": "self",
            "title": "This document",
            "href": "http://bcim-ogcapi-features/collections/unidade-federacao"
        },
        {
            "type": "text/html",
            "rel": "alternate",
            "title": "Esse documento em HTML",
            "href": "http://bcim-ogcapi-features/collections/unidade-federacao/?f=html"
        }
    ],
    "itemType": "feature"
}
     * 
     */
}
