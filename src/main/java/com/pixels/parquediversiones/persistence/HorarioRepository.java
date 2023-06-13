package com.pixels.parquediversiones.persistence;

import com.pixels.parquediversiones.domain.Schedule;
import com.pixels.parquediversiones.domain.repository.ScheduleRepository;

import com.pixels.parquediversiones.persistence.crud.HorarioCrudRepository;
import com.pixels.parquediversiones.persistence.entity.Horario;
import com.pixels.parquediversiones.persistence.mapper.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class HorarioRepository implements ScheduleRepository {
    @Autowired
    private HorarioCrudRepository horarioCrudRepository;
    @Autowired
    private ScheduleMapper mapper;
    @Override
    public List<Schedule> getAll(){
       List<Horario> horarios = (List<Horario>) horarioCrudRepository.findAll();
       return mapper.toSchedules(horarios);
    }

    @Override
    public Optional<List<Schedule>> getByGameId(int gameId) {
        Optional<List<Horario>> horarios = horarioCrudRepository.findByIdJuego(gameId);
        return horarios.map(horarios1 -> mapper.toSchedules(horarios1));
    }

    @Override
    public Optional<List<Schedule>> getbyWeekDay(String weekDay) {
        Optional<List<Horario>> horarios =  horarioCrudRepository.findByDiaSemana(weekDay);
        return horarios.map(horarios1 -> mapper.toSchedules(horarios1));
    }

    @Override
    public Optional<Schedule> getSchedule(int scheduleId) {
        return  horarioCrudRepository.findById(scheduleId).map(horario -> mapper.toSchedule(horario));
    }

    @Override
    public Schedule save(Schedule schedule) {
        Horario horario = mapper.toHorario(schedule);
        return mapper.toSchedule(horarioCrudRepository.save(horario));
    }



  @Override
    public void delete(int idHorario){
        horarioCrudRepository.deleteById(idHorario);
    }
}
