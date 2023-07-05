package com.pixels.parquediversiones.persistence.crud;

import com.pixels.parquediversiones.domain.dto.GameTicketsResponse;
import com.pixels.parquediversiones.persistence.entity.Entrada;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EntradaCrudRepository extends CrudRepository<Entrada, Integer> {
    Optional<List<Entrada>> findByIdComprador(int idComprador);

    Optional<List<Entrada>> findByIdVenta(int idVenta);

    @Query(value = "SELECT new com.pixels.parquediversiones.domain.dto.GameTicketsResponse(j.idJuego, j.nombre, COUNT(e) as entradasVendidas) " +
            "FROM Entrada e " +
            "INNER JOIN e.juego j " +
            "INNER JOIN e.venta v " +
            "WHERE v.fecha = :fecha " +
            "GROUP BY j.idJuego " +
            "ORDER BY entradasVendidas DESC")
    Optional<List<GameTicketsResponse>> findTicketsSoldByDate(@Param("fecha") LocalDateTime date);

    @Query(value = "SELECT new com.pixels.parquediversiones.domain.dto.GameTicketsResponse(j.idJuego, j.nombre, COUNT(e) as entradasVendidas) " +
            "FROM Entrada e " +
            "INNER JOIN e.juego j " +
            "INNER JOIN e.venta v " +
            "WHERE v.fecha = :fecha AND e.idJuego = :idJuego " +
            "GROUP BY j.idJuego " +
            "ORDER BY entradasVendidas DESC")
    Optional<GameTicketsResponse> findTicketsSoldByDateAndGame(@Param("fecha") LocalDateTime date, @Param("idJuego") Integer gameId);

    @Query(value = "SELECT new com.pixels.parquediversiones.domain.dto.GameTicketsResponse(j.idJuego, j.nombre, COUNT(e) as entradasVendidas) " +
            "FROM Entrada e " +
            "INNER JOIN e.juego j " +
            "GROUP BY j.idJuego " +
            "ORDER BY entradasVendidas DESC")
    Optional<List<GameTicketsResponse>> findGamesWithMostTickets();
}
