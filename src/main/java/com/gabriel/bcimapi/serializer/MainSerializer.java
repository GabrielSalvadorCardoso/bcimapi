package com.gabriel.bcimapi.serializer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.bcimapi.controller.CapitalController;
import com.gabriel.bcimapi.controller.UnidadeFederacaoController;
// https://github.com/opengeospatial/ogcapi-features/blob/master/core/examples/json/LandingPage.json
public class MainSerializer {
    public final static String URL_PATH_SEP = "/";
    public final static String URL_PROTOCOL_SEP = "://";
    public final static String URL_HOST_SEP = ":";
    public final static int DEFAULT_HTTP_PORT = 80;
    public final static int DEFAULT_HTTPS_PORT = 443;

    public final static String API_TITLE = "Base Cartográfica Contínua do Brasil, ao milionésimo (BCIM)";    
    public final static String API_DESCRIPTION = "Base Cartográfica Contínua do Brasil na escala de 1:1 000 000 constituída a partir das folhas da Carta Internacional do Mundo ao milionésimo, atualizada a partir de compilação do mapeamento topográfico em escalas maiores, interpretação de imagens de satélite, de nomes geográficos e dados fornecidos por órgãos setoriais parceiros.";

    public static String toHTML(ServerProperties serverProperties) throws MalformedURLException {
        StringBuilder html = new StringBuilder();
        String headerBody = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\" /><title>API BCIM</title><style>\n" +
                "table, th, td {\n" +
                "  border: 1px solid black;\n" +
                "}\n" +
                "</style></head><body>";
        String bodyFooter = "</body></html>";
        html.append(headerBody);
        // html.append("<table>");
        html.append("<table style=\"width:100%\">");
            html.append("<tr>");
                html.append("<th>title</th>");
                html.append("<td>"+API_TITLE+"</td>");
            html.append("</tr>");
            html.append("<tr>");
                html.append("<th>description</th>");
                html.append("<td>"+API_DESCRIPTION+"</td>");
            html.append("</tr>");
            html.append("<tr>");
                html.append("<th rowspan=\"2\">links</th>");
                html.append("<td><a href=\""+getSelfPath()+"/?f=html\" type=\"text/html\" title=\"Este documento\" rel=\"self\">Este documento</a></td>");
            html.append("</tr>");
            html.append("<tr>");
                html.append("<td><a href=\""+getSelfPath()+"\" type=\"application/json\" title=\"Este documento em JSON\" rel=\"alternate\">Este documento em JSON</a></td>");
            html.append("</tr>");
        html.append("</table>");
        html.append(bodyFooter);

        return html.toString();
    }

    public static String collections() throws MalformedURLException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> content = new HashMap<>();
        List<Object> links = new ArrayList<>();
        List<Object> collections = new ArrayList<>();

        Map<String, Object> selfLink = new HashMap<>();
        selfLink.put("href", getSelfPath()+"/collections");
        selfLink.put("rel", "self");
        selfLink.put("type", MediaType.APPLICATION_JSON.toString());
        selfLink.put("title", "Esse documento");

        Map<String, Object> alternateLink = new HashMap<>();
        alternateLink.put("href", getSelfPath()+"/collections.html");
        alternateLink.put("rel", "alternate");
        alternateLink.put("type", MediaType.TEXT_HTML.toString());
        alternateLink.put("title", "Esse documento em HTML");

        // TODO: HARDCODED
        Map<String, Object> capitalCollection = new HashMap<>();
        capitalCollection.put("id", "capital");
        capitalCollection.put("title", "Capitais");
        capitalCollection.put("description", "Capitais da América do Sul");

        Map<String, Object> capitalSpatialExtent = new HashMap<>();
        Map<String, Object> capitalSpatialBBoxExtent = new HashMap<>();
        List<Double> firstBbox = new ArrayList<>();
        firstBbox.add(-78.50);
        firstBbox.add(-34.86);
        firstBbox.add(-34.86);
        firstBbox.add(10.49);
        List<List<Double>> bboxes = new ArrayList<>();
        bboxes.add(firstBbox);
        capitalSpatialBBoxExtent.put("bbox", bboxes);
        capitalSpatialExtent.put("spatial", capitalSpatialBBoxExtent);
        capitalCollection.put("extent", capitalSpatialExtent);
        
        capitalCollection.put("itemType", "feature");

        List<Object> capitalCollectionLinks = new ArrayList<>();
        Map<String, Object> capitalCollectionSelfLink = new HashMap<>();
        capitalCollectionSelfLink.put("href", getSelfPath()+"/collections/capital");
        capitalCollectionSelfLink.put("rel", "self");
        capitalCollectionSelfLink.put("title", "Essa coleção");
        capitalCollectionLinks.add(capitalCollectionSelfLink);
        Map<String, Object> capitalCollectionItemsLink = new HashMap<>();
        capitalCollectionItemsLink.put("href", getSelfPath()+"/collections/capital/items");
        capitalCollectionItemsLink.put("rel", "items");
        capitalCollectionItemsLink.put("title", "Capitais");
        capitalCollectionLinks.add(capitalCollectionItemsLink);
        Map<String, Object> capitalCollectionItemLink = new HashMap<>();
        capitalCollectionItemLink.put("href", getSelfPath()+"/collections/capital/items/{featureId}");
        capitalCollectionItemLink.put("rel", "item");
        capitalCollectionItemLink.put("title", "Template de Link para feições de capitais");
        capitalCollectionItemLink.put("templated", true);
        capitalCollectionLinks.add(capitalCollectionItemLink);

        capitalCollection.put("links", capitalCollectionLinks);

        collections.add(capitalCollection);

         // TODO: HARDCODED
        Map<String, Object> ufCollection = new HashMap<>();
        ufCollection.put("id", "unidade-federacao");
        ufCollection.put("title", "Unidades Federativas");
        ufCollection.put("description", "Unidades Federativas do Brasil");

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
        ufSpatialExtent.put("spatial", capitalSpatialBBoxExtent);
        ufCollection.put("extent", ufSpatialExtent);
        
        ufCollection.put("itemType", "feature");

        List<Object> ufCollectionLinks = new ArrayList<>();
        Map<String, Object> ufCollectionSelfLink = new HashMap<>();
        ufCollectionSelfLink.put("href", getSelfPath()+"/collections/unidade-federacao");
        ufCollectionSelfLink.put("rel", "self");
        ufCollectionSelfLink.put("title", "Essa coleção");
        ufCollectionLinks.add(ufCollectionSelfLink);
        Map<String, Object> ufCollectionItemsLink = new HashMap<>();
        ufCollectionItemsLink.put("href", getSelfPath()+"/collections/unidade-federacao/items");
        ufCollectionItemsLink.put("rel", "items");
        ufCollectionItemsLink.put("title", "Unidades Federativas");
        ufCollectionLinks.add(ufCollectionItemsLink);
        Map<String, Object> ufCollectionItemLink = new HashMap<>();
        ufCollectionItemLink.put("href", getSelfPath()+"/collections/unidade-federacao/items/{featureId}");
        ufCollectionItemLink.put("rel", "item");
        ufCollectionItemLink.put("title", "Template de Link para feições de unidades federativas");
        ufCollectionItemLink.put("templated", true);
        ufCollectionLinks.add(ufCollectionItemLink);

        ufCollection.put("links", ufCollectionLinks);

        collections.add(ufCollection);
        
        links.add(selfLink);
        links.add(alternateLink);

        content.put("links", links);
        content.put("collections", collections);
        String serializer = mapper.writeValueAsString(content);
        return serializer.toString();
    }

    public static String conformance() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> content = new HashMap<>();
        List<String> conformanceClasses = new ArrayList<>();
        conformanceClasses.add("http://www.opengis.net/spec/ogcapi-features-1/1.0/conf/core");
        conformanceClasses.add("http://www.opengis.net/spec/ogcapi-features-1/1.0/conf/oas30");
        conformanceClasses.add("http://www.opengis.net/spec/ogcapi-features-1/1.0/conf/html");
        conformanceClasses.add("http://www.opengis.net/spec/ogcapi-features-1/1.0/conf/geojson");

        content.put("conformsTo", conformanceClasses);
        String serialized = mapper.writeValueAsString(content);        
        return serialized.toString();
    }

    public static String toJson(ServerProperties serverProperties) throws JsonProcessingException, MalformedURLException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> content = new HashMap<>();

        content.put("title", API_TITLE);
        content.put("description", API_DESCRIPTION);

        List<Map<String, Object>> linkObjs = new ArrayList<>();

        Map<String, Object> selfLink = new HashMap<>();
        selfLink.put("rel", "self");
        selfLink.put("type", MediaType.APPLICATION_JSON.toString());
        selfLink.put("title", "Esse documento");
        selfLink.put("href", getSelfPath());
        linkObjs.add(selfLink);

        Map<String, Object> selfLinkHTML = new HashMap<>();
        selfLinkHTML.put("rel", "alternate");
        selfLinkHTML.put("type", MediaType.TEXT_HTML.toString());
        selfLinkHTML.put("title", "Esse documento em HTML");
        selfLinkHTML.put("href", getSelfPath()+"/?f=html");
        linkObjs.add(selfLinkHTML);

        Map<String, Object> conformanceLink = new HashMap<>();
        conformanceLink.put("rel", "conformance");
        conformanceLink.put("type", MediaType.APPLICATION_JSON.toString());
        conformanceLink.put("title", "Relação de classes de conformidade do padrão OGC API implementadas por esse servidor");
        conformanceLink.put("href", getSelfPath()+"/conformance");
        linkObjs.add(conformanceLink);

        Map<String, Object> collectionsInfoLink = new HashMap<>();
        collectionsInfoLink.put("rel", "data");
        collectionsInfoLink.put("type", MediaType.APPLICATION_JSON.toString());
        collectionsInfoLink.put("title", "Informações sobre as coleções de feições");
        collectionsInfoLink.put("href", getSelfPath()+"/collections");
        linkObjs.add(collectionsInfoLink);

        Map<String, Object> serviceDescriptionLink = new HashMap<>();
        serviceDescriptionLink.put("rel", "service-desc");
        serviceDescriptionLink.put("type", "application/vnd.oai.openapi+json;version=3.0");
        serviceDescriptionLink.put("title", "A definição da API");
        serviceDescriptionLink.put("href", getSelfPath()+"/api");
        linkObjs.add(serviceDescriptionLink);

        Map<String, Object> serviceDocumentationLink = new HashMap<>();
        serviceDocumentationLink.put("rel", "service-doc");
        serviceDocumentationLink.put("type", MediaType.TEXT_HTML.toString());
        serviceDocumentationLink.put("title", "A documentação da API");
        serviceDocumentationLink.put("href", getSelfPath()+"/api.html");
        linkObjs.add(serviceDocumentationLink);
        
        // Map<String, Object> capitalLink = new HashMap<>();
        // capitalLink.put("rel", "service");
        // capitalLink.put("type", "application/geo+json");
        // capitalLink.put("title", "BCIM - Capital - 1:1 000 000 - 2016");
        // capitalLink.put("href", getCapitalCollectionPath());
        // linkObjs.add(capitalLink);

        // Map<String, Object> ufLink = new HashMap<>();
        // ufLink.put("rel", "service");
        // ufLink.put("type", "application/geo+json");
        // ufLink.put("title", "BCIM - Unidade da Federação - 1:1 000 000 - 2016");
        // ufLink.put("href", getUnidadeFederacaoCollectionPath());
        // linkObjs.add(ufLink);
        
        content.put("links", linkObjs);
        String serialized = mapper.writeValueAsString(content);
        
        return serialized.toString();
    }

    private static boolean isNotDefaultPort(int port) {
        return port != DEFAULT_HTTP_PORT && port != DEFAULT_HTTPS_PORT;
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
        .append(selfUrl.getProtocol()).append(URL_PROTOCOL_SEP)
        .append(selfUrl.getHost());
        if(isNotDefaultPort(selfUrl.getPort())) {
            selfSb.append(URL_HOST_SEP).append(selfUrl.getPort());
        }
        return selfSb.toString();
    }


    private static String getCapitalCollectionPath() throws MalformedURLException {
        URL capitalUrl = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path(CapitalController.CAPITAL_COLLECTION_PATH)
        .encode() // To encode your url... always usefull
        .build()
        .toUri()
        .toURL();
        StringBuilder capitalSb = new StringBuilder();
        capitalSb
        .append(capitalUrl.getProtocol()).append(URL_PROTOCOL_SEP)
        .append(capitalUrl.getHost());
        
        if(isNotDefaultPort(capitalUrl.getPort())) {
            capitalSb.append(URL_HOST_SEP).append(capitalUrl.getPort());
        }
        capitalSb.append(capitalUrl.getPath());

        return capitalSb.toString();
    }

    private static String getUnidadeFederacaoCollectionPath() throws MalformedURLException {
        URL unidadeFederacaoUrl = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path(UnidadeFederacaoController.UNIDADE_FEDERACAO_COLLECTION_PATH)
        .encode() // To encode your url... always usefull
        .build()
        .toUri()
        .toURL();
        StringBuilder unidadeFederacaoSb = new StringBuilder();
        unidadeFederacaoSb
        .append(unidadeFederacaoUrl.getProtocol()).append(URL_PROTOCOL_SEP)
        .append(unidadeFederacaoUrl.getHost());
        
        if(isNotDefaultPort(unidadeFederacaoUrl.getPort())) {
            unidadeFederacaoSb.append(URL_HOST_SEP).append(unidadeFederacaoUrl.getPort());
        }
        unidadeFederacaoSb.append(unidadeFederacaoUrl.getPath());
        
        return unidadeFederacaoSb.toString();
    }
}
