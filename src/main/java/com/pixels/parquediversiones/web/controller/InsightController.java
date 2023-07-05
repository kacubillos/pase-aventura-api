package com.pixels.parquediversiones.web.controller;

import com.pixels.parquediversiones.domain.dto.CustomerTicketsResponse;
import com.pixels.parquediversiones.domain.dto.GameTicketsResponse;
import com.pixels.parquediversiones.domain.dto.TotalSalesResponse;
import com.pixels.parquediversiones.domain.service.SaleService;
import com.pixels.parquediversiones.domain.service.TicketService;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/insights")
public class InsightController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private SaleService saleService;

    @GetMapping("/tickets-sold")
    public ResponseEntity<List<GameTicketsResponse>> getTicketsSold() {
        return ticketService.getTicketsSold().map(gameTicketsResponses -> new ResponseEntity<>(gameTicketsResponses, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    /**
     * Method to get a list with the tickets sold per game given a date or a gameId
     * @param strDate
     * @return
     */
    @GetMapping("/tickets-sold/filter")
    public ResponseEntity<List<GameTicketsResponse>> getTicketsSoldByDateOrId(@RequestParam("date") String strDate, @RequestParam(value = "gameid", required = false) Integer gameId) {
        try {
            LocalDate date = LocalDate.parse(strDate);
            LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.MIDNIGHT);

            if(gameId != null) {
                return ticketService.getTicketsSoldByDateAndGame(dateTime, gameId).map(gameTicketsResponse -> {
                    List<GameTicketsResponse> responseList = new ArrayList<>();
                    responseList.add(gameTicketsResponse);
                    return new ResponseEntity<>(responseList, HttpStatus.OK);
                }).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
            } else {
                return ticketService.getTicketsSoldByDate(dateTime).map(gameTicketsResponses -> new ResponseEntity<>(gameTicketsResponses, HttpStatus.OK))
                        .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to get the total sales on a year, month or day
     * @param year
     * @param month
     * @param day
     * @return
     */
    @GetMapping("/total-sales")
    public ResponseEntity<TotalSalesResponse> getTotalSales(@RequestParam("year") Integer year,
                                                            @RequestParam(value = "month", required = false) Integer month,
                                                            @RequestParam(value = "day", required = false) Integer day) {
        if(month == null && day == null) {
            return saleService.getTotalSalesByYear(year).map(totalSalesResponse -> new ResponseEntity<>(totalSalesResponse, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        } else if(month != null && day == null) {
            return saleService.getTotalSalesByMonth(year, month).map(totalSalesResponse -> new ResponseEntity<>(totalSalesResponse, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        } else if(month != null) {
            return saleService.getTotalSalesByDay(year, month, day).map(totalSalesResponse -> new ResponseEntity<>(totalSalesResponse, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to get a list with tickets purchased by customers on a year or month
     * @param year
     * @param month
     * @return
     */
    @GetMapping("/customer-tickets")
    public ResponseEntity<List<CustomerTicketsResponse>> getTicketsByCustomer(@RequestParam("year") Integer year,
                                                                              @RequestParam(value = "month", required = false) Integer month) {
        if(month == null) {
            return saleService.getTotalTicketsCustomerByYear(year).map(customerTicketsResponses -> new ResponseEntity<>(customerTicketsResponses, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        } else {
            return saleService.getTotalTicketsCustomerByMonth(year, month).map(customerTicketsResponses -> new ResponseEntity<>(customerTicketsResponses, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        }
    }
}
