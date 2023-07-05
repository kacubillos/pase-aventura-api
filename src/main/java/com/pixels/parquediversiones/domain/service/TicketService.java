package com.pixels.parquediversiones.domain.service;

import com.pixels.parquediversiones.domain.Ticket;
import com.pixels.parquediversiones.domain.dto.GameTicketsResponse;
import com.pixels.parquediversiones.domain.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAll() {
        return ticketRepository.getAll();
    }

    public Optional<Ticket> getById(int ticketId) {
        return ticketRepository.getTicket(ticketId);
    }

    public Optional<List<Ticket>> getByCustomer(int customerId) {
        return ticketRepository.getByCustomerId(customerId);
    }

    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public boolean delete(int ticketId) {
        return ticketRepository.getTicket(ticketId).map(ticket -> {
            ticketRepository.delete(ticketId);
            return true;
        }).orElse(false);
    }

    public Optional<List<GameTicketsResponse>> getTicketsSold() {
        return ticketRepository.getTicketsSold();
    }

    public Optional<List<GameTicketsResponse>> getTicketsSoldByDate(LocalDateTime date) {
        return ticketRepository.getTicketsSoldByDate(date);
    }

    public Optional<GameTicketsResponse> getTicketsSoldByDateAndGame(LocalDateTime date, int gameId) {
        return ticketRepository.getTicketsSoldByDateAndGame(date, gameId);
    }
}
