package com.gabriel.bcimapi.serializer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.bcimapi.controller.CapitalController;
import com.gabriel.bcimapi.controller.UnidadeFederacaoController;

public class MainSerializer {
    public final static String URL_PATH_SEP = "/";
    public final static String URL_PROTOCOL_SEP = "://";
    public final static String URL_HOST_SEP = ":";
    public final static int DEFAULT_HTTP_PORT = 80;
    public final static int DEFAULT_HTTPS_PORT = 443;
    
    
    public final static String API_DESCRIPTION = "Base Cartográfica Contínua do Brasil na escala de 1:1 000 000 constituída a partir das folhas da Carta Internacional do Mundo ao milionésimo, atualizada a partir de compilação do mapeamento topográfico em escalas maiores, interpretação de imagens de satélite, de nomes geográficos e dados fornecidos por órgãos setoriais parceiros.";
    public static String toJson(ServerProperties serverProperties) throws JsonProcessingException, MalformedURLException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> content = new HashMap<>();
        content.put("description", API_DESCRIPTION);
        
        content.put("capital", getCapitalCollectionPath());
        content.put("unidade-federacao", getUnidadeFederacaoCollectionPath());
        
        String serialized = mapper.writeValueAsString(content);
        
        return serialized.toString();
    }

    private static boolean isNotDefaulPort(int port) {
        return port != DEFAULT_HTTP_PORT && port != DEFAULT_HTTPS_PORT;
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
        
        if(isNotDefaulPort(capitalUrl.getPort())) {
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
        
        if(isNotDefaulPort(unidadeFederacaoUrl.getPort())) {
            unidadeFederacaoSb.append(URL_HOST_SEP).append(unidadeFederacaoUrl.getPort());
        }
        unidadeFederacaoSb.append(unidadeFederacaoUrl.getPath());
        
        return unidadeFederacaoSb.toString();
    }
}
