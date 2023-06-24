package com.pixels.parquediversiones.web.controller;

import com.pixels.parquediversiones.domain.Game;
import com.pixels.parquediversiones.domain.Schedule;
import com.pixels.parquediversiones.domain.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @GetMapping
    public ResponseEntity<List<Schedule>> getAll(){
        return new ResponseEntity<>(scheduleService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable("id") int scheduleId){
        return scheduleService.getSchedule(scheduleId)
                .map(schedule -> new ResponseEntity<>(schedule,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/week-day/{string}")
    public ResponseEntity<List<Schedule>> getByWeekDay(@PathVariable("string") String weekDay){
        return scheduleService.getbyWeekDay(weekDay)
                .map(schedule -> new ResponseEntity<>(schedule,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/game/{id}")
    public ResponseEntity<List<Schedule>> getByGameId(@PathVariable("id") int gameId) {
        return scheduleService.getByGameId(gameId)
                .map(Schedule -> new ResponseEntity<>(Schedule, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public ResponseEntity<Schedule> save(@RequestBody Schedule schedule){
        return new ResponseEntity<>(scheduleService.save(schedule),HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Schedule> update(@RequestBody Schedule schedule) {
        return scheduleService.getSchedule(schedule.getScheduleId())
                .map(emp -> new ResponseEntity<>(scheduleService.save(schedule), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int scheduleId){
        if(scheduleService.delete(scheduleId)){
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
