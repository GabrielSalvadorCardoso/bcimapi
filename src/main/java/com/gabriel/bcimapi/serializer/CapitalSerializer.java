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
import com.gabriel.bcimapi.model.Capital;

public class CapitalSerializer {
    public static String toJson(List<Capital> capitais) throws JsonMappingException, JsonProcessingException {
        Map<String, Object> featureCollection = new HashMap<>();
        featureCollection.put("type", "FeatureCollection");
        List<Map<String, Object>> features = new ArrayList<>();

        for(Capital capital : capitais) {
            Map<String, Object> featureMapping = CapitalSerializer.toFeatureMapping(capital);
            features.add(featureMapping);
        }

        featureCollection.put("features", features);
        ObjectMapper objectMapperSerializer = new ObjectMapper();
        String jacksonData = objectMapperSerializer.writeValueAsString(featureCollection);

        return jacksonData.toString();
    }

    public static String toJson(Capital capital) throws JsonMappingException, JsonProcessingException {
        // https://www.baeldung.com/jackson-custom-serialization
        // https://github.com/bjornharrtell/jts2geojson
        // https://www.baeldung.com/java-convert-hashmap-to-json-object

		ObjectMapper objectMapper = new ObjectMapper();
		GeoJSONWriter writer = new GeoJSONWriter();

        GeoJSON json = writer.write(capital.getGeom());
        String jsonData = json.toString();

        Map<String, Object> geometry = objectMapper.readValue(jsonData, Map.class);
        Map<String, Object> geoJson = new HashMap<>();
        
        geoJson.put("type", "Feature");
        geoJson.put("geometry", geometry);

        Map<String, Object> props = new HashMap<>();
        props.put("nome", capital.getNome());
        geoJson.put("properties", props);

        ObjectMapper objectMapperSerializer = new ObjectMapper();
        String jacksonData = objectMapperSerializer.writeValueAsString(geoJson);

        return jacksonData.toString();
		//convert json string to object
		//Employee emp = objectMapper.readValue(jsonData, Capital.class);
        //Map<String, String> map = objectMapper.readValue(json, Map.class);
        //objectMapper.readValue
    }

    private static Map<String, Object> toFeatureMapping(Capital capital)
            throws JsonProcessingException, JsonMappingException {
        ObjectMapper objectMapper = new ObjectMapper();
		GeoJSONWriter writer = new GeoJSONWriter();

        GeoJSON json = writer.write(capital.getGeom());
        String jsonData = json.toString();

        Map<String, Object> geometry = objectMapper.readValue(jsonData, Map.class);
        Map<String, Object> geoJson = new HashMap<>();
        
        geoJson.put("type", "Feature");
        geoJson.put("geometry", geometry);

        Map<String, Object> props = new HashMap<>();
        props.put("id", capital.getIdObjeto());
        props.put("nome", capital.getNome());
        props.put("tipocapital", capital.getTipocapital());
        geoJson.put("properties", props);
        return geoJson;
    }
}
