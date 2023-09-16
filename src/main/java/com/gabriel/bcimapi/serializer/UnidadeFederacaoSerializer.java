package com.gabriel.bcimapi.serializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.wololo.geojson.GeoJSON;
import org.wololo.jts2geojson.GeoJSONWriter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
}
