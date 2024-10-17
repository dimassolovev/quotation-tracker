package com.dimas.moexdataservice.mapper;

public interface DtoMappable<E, D> {
    D toDto(E entity);
}
