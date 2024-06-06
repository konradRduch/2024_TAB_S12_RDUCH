package org.skistation.models.DTO;

import org.skistation.models.Client;
import org.skistation.models.PriceList;

/**
 * Represents a request to buy a ticket.
 * It contains the client, ticket details, total price, number of normal and discount tickets, and the price list.
 */
public class BuyTicketRequest
{
    /**
     * The client who wants to buy the ticket.
     */
    private Client client;

    /**
     * The details of the ticket to buy.
     */
    private TicketDTO ticketDTO;

    /**
     * The total price of the ticket.
     */
    private Float total;

    /**
     * The number of normal tickets to buy.
     */
    private Integer numberOfNormalPasses;

    /**
     * The number of discount tickets to buy.
     */
    private Integer numberOfDiscountPasses;

    /**
     * The price list for the ticket.
     */
    private PriceList priceList;

    /**
     * Constructs a new BuyTicketRequest with no details.
     */
    public BuyTicketRequest() {
    }

    /**
     * Constructs a new BuyTicketRequest with the specified client, ticket details, total price, number of normal and discount tickets, and price list.
     *
     * @param client                 the client who wants to buy the ticket
     * @param ticketDTO              the details of the ticket to buy
     * @param total                  the total price of the ticket
     * @param numberOfNormalPasses   the number of normal tickets to buy
     * @param numberOfDiscountPasses the number of discount tickets to buy
     * @param priceList              the price list for the ticket
     */
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