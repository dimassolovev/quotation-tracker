package com.dimas.moexdataservice.repository;

import com.dimas.moexdataservice.model.entity.currency.ClearingType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClearingTypeRepository extends JpaRepository<ClearingType, Integer> {
    ClearingType findByClearing(String clearing);
}
