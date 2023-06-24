package com.pixels.parquediversiones.persistence.mapper;

import com.pixels.parquediversiones.domain.UserAccount;
import com.pixels.parquediversiones.persistence.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = { EmployeeMapper.class })
public interface UserMapper {
    @Mappings({
            @Mapping(source = "idUsuario", target = "userId"),
            @Mapping(source = "idEmpleado", target = "employeeId"),
            @Mapping(source = "correoElectronico", target = "email"),
            @Mapping(source = "clave", target = "password"),
            @Mapping(source = "fechaRegistro", target = "registrationDate"),
            @Mapping(source = "empleado", target = "employee")
    })
    UserAccount toUser(Usuario usuario);
    List<UserAccount> toUsers(List<Usuario> usuarios);

    @InheritInverseConfiguration
    Usuario toUsuario(UserAccount userAccount);
}
