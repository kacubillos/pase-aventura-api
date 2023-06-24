package com.pixels.parquediversiones.persistence.mapper;

import com.pixels.parquediversiones.domain.Sale;
import com.pixels.parquediversiones.persistence.entity.Venta;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    @Mappings({
            @Mapping(source = "idVenta", target = "saleId"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "idComprador", target = "customerId")
    })
    Sale toSale(Venta venta);
    List<Sale> toSales(List<Venta> ventas);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "entradas", ignore = true),
            @Mapping(target = "comprador", ignore = true)
    })
    Venta toVenta(Sale sale);
}
