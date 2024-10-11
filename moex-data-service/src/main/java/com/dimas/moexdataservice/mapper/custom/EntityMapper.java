package com.dimas.moexdataservice.mapper.custom;

public interface EntityMapper<E, D>{
    E toEntity(D dto);
}
