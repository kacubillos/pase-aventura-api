package com.pixels.parquediversiones.domain.repository;

import com.pixels.parquediversiones.domain.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    List<Schedule> getAll();
    Optional<List<Schedule>>getByGameId(int amusementId);

    Optional<List<Schedule>>getbyWeekDay(String weekDay);
    Optional<Schedule> getSchedule(int scheduleId);
    Schedule save(Schedule schedule);
    void delete(int scheduleId);
}
