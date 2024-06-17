package com.cmc.demo.oauth.mapper;

import java.util.List;

public interface LcMapper<E, D> {

    E toEntity(D domain);

    D toDomain(E entity);

    List<D> toDomain(List<E> listEntity);

    List<E> toEntity(List<D> listDomain);
}