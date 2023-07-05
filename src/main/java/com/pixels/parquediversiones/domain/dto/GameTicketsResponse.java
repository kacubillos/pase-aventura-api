package com.pixels.parquediversiones.domain.dto;

public class GameTicketsResponse {
    private Integer gameId;
    private String name;
    private Object ticketsSold;

    public GameTicketsResponse(Integer gameId, String name, Object ticketsSold) {
        this.gameId = gameId;
        this.name = name;
        this.ticketsSold = ticketsSold;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(Object ticketsSold) {
        this.ticketsSold = ticketsSold;
    }
}
