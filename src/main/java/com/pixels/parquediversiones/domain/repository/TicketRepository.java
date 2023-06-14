package com.pixels.parquediversiones.domain.repository;

import com.pixels.parquediversiones.domain.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    List<Ticket> getAll();
    Optional<List<Ticket>> getByCustomerId(int customerId);
    Optional<Ticket> getTicket(int ticketId);
    Ticket save(Ticket ticket);
    void delete(int ticketId);
}
