package com.j7arsen.data.model.mapper;

public interface IObjectModelMapper<ENTITY, DOMAIN> {

    DOMAIN mapEntityToDomain(ENTITY entity);

    ENTITY mapDomainToEntity(DOMAIN domain);
}
