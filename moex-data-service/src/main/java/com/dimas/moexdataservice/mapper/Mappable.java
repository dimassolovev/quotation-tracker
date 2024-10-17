package com.dimas.moexdataservice.mapper;

public interface Mappable<E, D> extends EntityMappable<E, D> {
    D toDto(E entity);
}
