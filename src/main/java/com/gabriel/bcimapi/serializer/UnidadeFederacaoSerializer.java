package com.gabriel.bcimapi.serializer;

import java.util.HashMap;
import java.util.Map;

import org.wololo.geojson.GeoJSON;
import org.wololo.jts2geojson.GeoJSONWriter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.bcimapi.model.UnidadeFederacao;

public class UnidadeFederacaoSerializer {
     public static String toJson(UnidadeFederacao uf) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		GeoJSONWriter writer = new GeoJSONWriter();

        GeoJSON json = writer.write(uf.getGeom());
        String jsonData = json.toString();

        Map<String, Object> geometry = objectMapper.readValue(jsonData, Map.class);
        Map<String, Object> geoJson = new HashMap<>();
        
        geoJson.put("type", "Feature");
        geoJson.put("geometry", geometry);

        Map<String, Object> props = new HashMap<>();
        props.put("nome", uf.getNome());
        props.put("sigla", uf.getSigla());
        geoJson.put("properties", props);

        ObjectMapper objectMapperSerializer = new ObjectMapper();
        String jacksonData = objectMapperSerializer.writeValueAsString(geoJson);

        return jacksonData.toString();
    }
}
