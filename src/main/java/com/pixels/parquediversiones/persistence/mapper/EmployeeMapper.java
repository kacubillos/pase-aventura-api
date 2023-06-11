package com.pixels.parquediversiones.persistence.mapper;

import com.pixels.parquediversiones.domain.Employee;
import com.pixels.parquediversiones.persistence.entity.Empleado;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = { RoleMapper.class })
public interface EmployeeMapper {
    @Mappings({
            @Mapping(source = "idEmpleado", target = "employeeId"),
            @Mapping(source = "tipoDocumento", target = "documentType"),
            @Mapping(source = "numeroDocumento", target = "documentNum"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "apellidos", target = "lastname"),
            @Mapping(source = "fechaNacimiento", target = "birthDate"),
            @Mapping(source = "idRol", target = "roleId"),
            @Mapping(source = "rol", target = "role"),
    })
    Employee toEmployee(Empleado empleado);
    List<Employee> toEmployees(List<Empleado> empleados);

    @InheritInverseConfiguration
    @Mapping(target = "usuario", ignore = true)
    Empleado toEmpleado(Employee employee);
}
