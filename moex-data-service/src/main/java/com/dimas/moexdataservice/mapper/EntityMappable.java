package com.dimas.moexdataservice.mapper;

public interface EntityMappable<E, D> {
    E toEntity(D dto);
}
