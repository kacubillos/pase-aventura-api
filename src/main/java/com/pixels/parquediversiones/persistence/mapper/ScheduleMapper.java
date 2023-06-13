package com.pixels.parquediversiones.persistence.mapper;

import com.pixels.parquediversiones.domain.Schedule;
import com.pixels.parquediversiones.persistence.entity.Horario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GameMapper.class})
public interface ScheduleMapper {
    @Mappings({
            @Mapping(source = "idHorario", target = "scheduleId"),
            @Mapping(source = "horaInicio", target = "startTime"),
            @Mapping(source = "horaFin", target = "endTime"),
            @Mapping(source = "diaSemana", target = "weekDay"),
            @Mapping(source = "idJuego", target = "gameId"),
            @Mapping(source = "juego",target = "game")
    })
    Schedule toSchedule(Horario horario);
    List<Schedule> toSchedules(List<Horario> horarios);
    @InheritInverseConfiguration
    Horario toHorario(Schedule schedule);

}
