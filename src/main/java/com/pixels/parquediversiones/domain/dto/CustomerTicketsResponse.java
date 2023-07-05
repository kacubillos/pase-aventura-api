package com.pixels.parquediversiones.domain.dto;

public class CustomerTicketsResponse {
    private Integer customerId;
    private String name;
    private String lastname;
    private Object totalTickets;

    public CustomerTicketsResponse() {
    }

    public CustomerTicketsResponse(Integer customerId, String name, String lastname, Object totalTickets) {
        this.customerId = customerId;
        this.name = name;
        this.lastname = lastname;
        this.totalTickets = totalTickets;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Object getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(Object totalTickets) {
        this.totalTickets = totalTickets;
    }
}
