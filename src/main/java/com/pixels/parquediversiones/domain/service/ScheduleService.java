package com.pixels.parquediversiones.domain.service;

import com.pixels.parquediversiones.domain.Schedule;
import com.pixels.parquediversiones.domain.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    public List<Schedule> getAll(){
        return scheduleRepository.getAll();
    }
    public Optional<List<Schedule>> getByGameId(int gameId){
        return scheduleRepository.getByGameId(gameId);
    }
    public Optional<List<Schedule>>getbyWeekDay(String weekDay){
        return scheduleRepository.getbyWeekDay(weekDay);
    }
    public Optional<Schedule> getSchedule(int scheduleId){
        return scheduleRepository.getSchedule(scheduleId);
    }
    public Schedule save(Schedule schedule){
        return scheduleRepository.save(schedule);
    }
    public boolean delete(int scheduleId){
        return getSchedule(scheduleId).map(schedule -> {
            scheduleRepository.delete(scheduleId);
            return true;
        }).orElse(false);
        }
}
