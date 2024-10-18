package com.dimas.moexdataservice.repository;

import com.dimas.moexdataservice.model.entity.currency.Security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRepository extends JpaRepository<Security, Integer> {}
