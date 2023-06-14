package com.pixels.parquediversiones.persistence.mapper;

import com.pixels.parquediversiones.domain.Customer;
import com.pixels.parquediversiones.persistence.entity.Comprador;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel="spring")
public interface CustomerMapper {

    @Mappings({
            @Mapping(source = "idComprador",target = "customerId"),
            @Mapping(source = "nombre",target = "name"),
            @Mapping(source = "apellido",target = "lastname"),
            @Mapping(source = "fechaNacimiento",target = "dateBirth"),
            @Mapping(source = "fechaRegistro",target = "dateRegistration")
    })
    Customer toCustomer(Comprador comprador);
    List<Customer> toCustomers(List<Comprador> compradores);

    @InheritInverseConfiguration
    Comprador toComprador(Customer customer);
}
