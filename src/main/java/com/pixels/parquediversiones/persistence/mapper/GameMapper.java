package com.pixels.parquediversiones.persistence.mapper;

import com.pixels.parquediversiones.domain.Game;
import com.pixels.parquediversiones.persistence.entity.Juego;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ScheduleMapper.class})
public interface GameMapper {
    @Mappings({
            @Mapping(source = "idJuego", target = "gameId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "capacidadPersonas", target = "capacityPersons"),
            @Mapping(source = "duracion", target = "duration"),
            @Mapping(source = "precioVenta", target = "salesPrice"),
            @Mapping(source = "fechaRegistro", target = "registrationDate"),

    })
    Game toGame(Juego juego);
    List<Game> toGames(List<Juego> juegos);
    @InheritInverseConfiguration
    @Mapping(target = "horarios", ignore = true)
    Juego toJuego(Game game);
}
