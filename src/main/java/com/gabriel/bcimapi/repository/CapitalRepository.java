package com.gabriel.bcimapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabriel.bcimapi.model.Capital;

@Repository
public interface CapitalRepository extends JpaRepository<Capital, Long> {
    List<Capital> findAll();
}
