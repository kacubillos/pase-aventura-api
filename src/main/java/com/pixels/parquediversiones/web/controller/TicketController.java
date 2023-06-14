package com.pixels.parquediversiones.web.controller;

import com.pixels.parquediversiones.domain.Ticket;
import com.pixels.parquediversiones.domain.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<Ticket>> getAll() {
        return new ResponseEntity<>(ticketService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getById(@PathVariable("id") int ticketId) {
        return ticketService.getById(ticketId)
                .map(ticket -> new ResponseEntity<>(ticket, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Ticket>> getByCustomer(@PathVariable("id") int customerId) {
        return ticketService.getByCustomer(customerId)
                .map(tickets -> new ResponseEntity<>(tickets, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Ticket> save(@RequestBody Ticket ticket) {
        return new ResponseEntity<>(ticketService.save(ticket), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Ticket> update(@RequestBody Ticket ticket) {
        return ticketService.getById(ticket.getTicketId())
                .map(ticketAux -> new ResponseEntity<>(ticket, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int ticketId) {
        if(ticketService.delete(ticketId))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
