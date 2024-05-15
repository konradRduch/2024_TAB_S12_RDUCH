package org.skistation.models.DTO;

import org.skistation.models.Client;
import org.skistation.models.PriceList;

public class BuyTicketRequest {
    private Client client;
    private TicketDTO ticketDTO;
    private Float total;
    private Integer numberOfNormalPasses;
    private Integer numberOfDiscountPasses;
    private PriceList priceList;

    public BuyTicketRequest() {
    }

    public BuyTicketRequest(Client client, TicketDTO ticketDTO, Float total, Integer numberOfNormalPasses, Integer numberOfDiscountPasses, PriceList priceList) {
        this.client = client;
        this.ticketDTO = ticketDTO;
        this.total = total;
        this.numberOfNormalPasses = numberOfNormalPasses;
        this.numberOfDiscountPasses = numberOfDiscountPasses;
        this.priceList = priceList;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public TicketDTO getTicketDTO() {
        return ticketDTO;
    }

    public void setTicketDTO(TicketDTO ticketDTO) {
        this.ticketDTO = ticketDTO;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }

    public Integer getNumberOfNormalPasses() {
        return numberOfNormalPasses;
    }

    public void setNumberOfNormalPasses(Integer numberOfNormalPasses) {
        this.numberOfNormalPasses = numberOfNormalPasses;
    }

    public Integer getNumberOfDiscountPasses() {
        return numberOfDiscountPasses;
    }

    public void setNumberOfDiscountPasses(Integer numberOfDiscountPasses) {
        this.numberOfDiscountPasses = numberOfDiscountPasses;
    }
}
