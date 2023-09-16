package com.gabriel.bcimapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gabriel.bcimapi.model.Capital;
import com.gabriel.bcimapi.repository.CapitalRepository;

@Service
public class CapitalService {
    
    @Autowired
    private CapitalRepository repository;

    //private GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326);

    public Optional<Capital> findById(Long id) {
        Optional<Capital> capitalResult = this.repository.findById(id);
        return capitalResult;
    }

    public List<Capital> findAll() {
        List<Capital> capitais = this.repository.findAll();

        

        //GeoJSONReader reader = new GeoJSONReader();
        //Geometry geometry = reader.read(json);

        return capitais;
    }

    /*
    public Page<Capital> findAll(Pageable page){
		return repository.findAll(page);
	}
     */
}
