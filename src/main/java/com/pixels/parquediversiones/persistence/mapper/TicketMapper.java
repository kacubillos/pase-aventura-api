package com.pixels.parquediversiones.persistence.mapper;

import com.pixels.parquediversiones.domain.Ticket;
import com.pixels.parquediversiones.persistence.entity.Entrada;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = { GameMapper.class })
public interface TicketMapper {
    @Mappings({
            @Mapping(source = "idEntrada", target = "ticketId"),
            @Mapping(source = "fechaVigencia", target = "effectiveDate"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "activo", target = "active"),
            @Mapping(source = "idJuego", target = "gameId"),
            @Mapping(source = "idVenta", target = "saleId"),
            @Mapping(source = "idComprador", target = "customerId"),
            @Mapping(source = "juego", target = "game"),
    })
    Ticket toTicket(Entrada entrada);
    List<Ticket> toTickets(List<Entrada> entradas);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "comprador", ignore = true),
            @Mapping(target = "venta", ignore = true)
    })
    Entrada toEntrada(Ticket ticket);
}
