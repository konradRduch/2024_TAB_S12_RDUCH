package org.skistation.controllers;

import org.skistation.models.Client;
import org.skistation.models.DTO.BuyTicketRequest;
import org.skistation.models.DTO.TicketDTO;
import org.skistation.models.Order;
import org.skistation.models.PriceList;
import org.skistation.models.Ticket;
import org.skistation.services.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for buying tickets.
 * It is responsible for handling requests related to buying tickets.
 */
@RestController
@RequestMapping("/buyTickets")
public class BuyTicketController
{

    /**
     * Service for managing orders.
     */
    private final OrderService orderService;

    /**
     * Service for managing clients.
     */
    private final ClientService clientService;

    /**
     * Service for managing tickets.
     */
    private final TicketService ticketService;

    /**
     * Service for managing price lists.
     */
    private final PriceListService priceListService;

    /**
     * Constructs a new BuyTicketController with the specified services.
     *
     * @param orderService     the service for managing orders.
     * @param clientService    the service for managing clients.
     * @param ticketService    the service for managing tickets.
     * @param priceListService the service for managing price lists.
     */
    public BuyTicketController(OrderService orderService, ClientService clientService, TicketService ticketService, PriceListService priceListService) {
        this.orderService = orderService;
        this.clientService = clientService;
        this.ticketService = ticketService;
        this.priceListService = priceListService;
    }

    /**
     * Buys a ticket.
     *
     * @param request the request to buy a ticket.
     * @return a list of IDs of the bought tickets.
     */
    @PostMapping("")
    public List<String> buyTicket(@RequestBody BuyTicketRequest request) {

        LocalDate now = LocalDate.now();
        Client client = clientService.getClientByEmailAndPhone(request.getClient().getEmail(), request.getClient().getPhone());

        TicketDTO ticketDTO = request.getTicketDTO();
        Float total = request.getTotal();
        PriceList priceList = priceListService.getPriceListWithinTimeRange(now).get(0);

        List<String> id = new ArrayList<>();

        if (client == null) {
            client = request.getClient();
            if (clientService.getClientsByPhoneNumber(request.getClient().getPhone()) == null && clientService.getClientsByEmailAddress(request.getClient().getEmail()) == null) {
                clientService.saveClient(client);
            }
            else {
                id.add("Error");
                return id;
            }
        }
        Order newOrder = new Order(total, client);
        orderService.saveOrder(newOrder);

        //normal
        for (int i = 0; i < request.getNumberOfNormalPasses(); i++) {
            Ticket newNormalTicket = new Ticket(ticketDTO.amountOfRides(), ticketDTO.pricePerRide(), "normal",
                    newOrder, priceList, ticketDTO.timeStart(), ticketDTO.timeEnd(), false, ticketDTO.pricePerRide() * ticketDTO.amountOfRides());
            ticketService.saveTicket(newNormalTicket);
            id.add(newNormalTicket.getId().toString());
        }
        //discount
        for (int i = 0; i < request.getNumberOfDiscountPasses(); i++) {
            Ticket newDiscountTicket = new Ticket(ticketDTO.amountOfRides(), ticketDTO.pricePerRide() / 2, "discount",
                    newOrder, priceList, ticketDTO.timeStart(), ticketDTO.timeEnd(), true, (ticketDTO.pricePerRide() / 2) * ticketDTO.amountOfRides());
            ticketService.saveTicket(newDiscountTicket);
            id.add(newDiscountTicket.getId().toString());
        }
        return id;
    }
}