package com.pixels.parquediversiones.persistence;

import com.pixels.parquediversiones.domain.Ticket;
import com.pixels.parquediversiones.domain.dto.GameTicketsResponse;
import com.pixels.parquediversiones.domain.repository.TicketRepository;
import com.pixels.parquediversiones.persistence.crud.EntradaCrudRepository;
import com.pixels.parquediversiones.persistence.entity.Entrada;
import com.pixels.parquediversiones.persistence.mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class EntradaRepository implements TicketRepository {
    @Autowired
    private EntradaCrudRepository entradaCrudRepository;
    @Autowired
    private TicketMapper mapper;

    @Override
    public List<Ticket> getAll() {
        List<Entrada> entradas = (List<Entrada>) entradaCrudRepository.findAll();
        return mapper.toTickets(entradas);
    }

    @Override
    public Optional<List<Ticket>> getByCustomerId(int customerId) {
        Optional<List<Entrada>> entradas = entradaCrudRepository.findByIdComprador(customerId);
        return entradas.map(ents -> mapper.toTickets(ents));
    }

    @Override
    public Optional<List<Ticket>> getBySaleId(int saleId) {
        Optional<List<Entrada>> entradas = entradaCrudRepository.findByIdVenta(saleId);
        return entradas.map(ents -> mapper.toTickets(ents));
    }

    @Override
    public Optional<Ticket> getTicket(int ticketId) {
        return entradaCrudRepository.findById(ticketId).map(entrada -> mapper.toTicket(entrada));
    }

    @Override
    public Ticket save(Ticket ticket) {
        Entrada entrada = mapper.toEntrada(ticket);
        return mapper.toTicket(entradaCrudRepository.save(entrada));
    }

    @Override
    public void delete(int ticketId) {
        entradaCrudRepository.deleteById(ticketId);
    }

    @Override
    public Optional<List<GameTicketsResponse>> getTicketsSoldByDate(LocalDateTime date) {
        return entradaCrudRepository.findTicketsSoldByDate(date);
    }

    @Override
    public Optional<GameTicketsResponse> getTicketsSoldByDateAndGame(LocalDateTime date, int gameId) {
        return entradaCrudRepository.findTicketsSoldByDateAndGame(date, gameId);
    }

    @Override
    public Optional<List<GameTicketsResponse>> getTicketsSold() {
        return entradaCrudRepository.findGamesWithMostTickets();
    }
}
