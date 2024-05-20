package org.skistation.controllers;


import org.skistation.models.Client;
import org.skistation.models.DTO.BuyTicketRequest;
import org.skistation.models.DTO.TicketDTO;
import org.skistation.models.Order;
import org.skistation.models.PriceList;
import org.skistation.models.Ticket;
import org.skistation.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/buyTickets")
public class BuyTicketController {

    private final OrderService orderService;
    private final ClientService clientService;
    private final TicketService ticketService;
    private final PriceListService priceListService;


    public BuyTicketController(OrderService orderService, ClientService clientService, TicketService ticketService, PriceListService priceListService) {
        this.orderService = orderService;
        this.clientService = clientService;
        this.ticketService = ticketService;
        this.priceListService = priceListService;
    }

    @PostMapping("")
    public List<String> buyTicket(@RequestBody BuyTicketRequest request) {
        Client client = request.getClient();
        TicketDTO ticketDTO = request.getTicketDTO();
        Float total = request.getTotal();
        PriceList priceList = request.getPriceList();
        priceListService.savePriceList(priceList);
        clientService.saveClient(client);
        Order newOrder = new Order(total, client);
        orderService.saveOrder(newOrder);
        List<String> id = new ArrayList<>();
        //normal
        for(int i=0;i<request.getNumberOfNormalPasses();i++) {
            Ticket newNormalTicket = new Ticket(ticketDTO.amountOfRides(), ticketDTO.pricePerRide(), "normal",
                    newOrder, priceList, ticketDTO.timeStart(), ticketDTO.timeEnd(), false);
            ticketService.saveTicket(newNormalTicket);

            id.add(newNormalTicket.getId().toString());
        }
        //discount
        for(int i=0;i<request.getNumberOfDiscountPasses();i++) {
            Ticket newDiscountTicket = new Ticket(ticketDTO.amountOfRides(), ticketDTO.pricePerRide()/2, "normal",
                    newOrder, priceList, ticketDTO.timeStart(), ticketDTO.timeEnd(), true);
            ticketService.saveTicket(newDiscountTicket);
            id.add(newDiscountTicket.getId().toString());
        }
        return id;
    }
}
