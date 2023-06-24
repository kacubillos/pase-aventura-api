package com.pixels.parquediversiones.persistence.mapper;

import com.pixels.parquediversiones.domain.Role;
import com.pixels.parquediversiones.persistence.entity.Rol;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mappings({
            @Mapping(source = "idRol",  target = "roleId"),
            @Mapping(source = "nombre", target = "name")
    })
    Role toRole(Rol rol);

    @InheritInverseConfiguration
    @Mapping(target = "empleados", ignore = true)
    Rol toRol(Role role);
}
