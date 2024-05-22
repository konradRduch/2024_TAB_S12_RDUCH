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

        LocalDate now = LocalDate.now();
        Client client = clientService.getClientByEmailAndPhone(request.getClient().getEmail(), request.getClient().getPhone());

        TicketDTO ticketDTO = request.getTicketDTO();
        Float total = request.getTotal();
        PriceList priceList = priceListService.getPriceListWithinTimeRange(now).get(0);

        List<String> id = new ArrayList<>();

        if( client == null  ){
            client = request.getClient();
            if(clientService.getClientsByPhoneNumber(request.getClient().getPhone()) == null && clientService.getClientsByEmailAddress(request.getClient().getEmail()) == null){
                clientService.saveClient(client);
            }else{
                id.add("Error");
                return id;
            }
        }
        Order newOrder = new Order(total, client);
        orderService.saveOrder(newOrder);

        //normal
        for(int i=0;i<request.getNumberOfNormalPasses();i++) {
            Ticket newNormalTicket = new Ticket(ticketDTO.amountOfRides(), ticketDTO.pricePerRide(), "normal",
                    newOrder, priceList, ticketDTO.timeStart(), ticketDTO.timeEnd(), false, ticketDTO.pricePerRide()*ticketDTO.amountOfRides());
            ticketService.saveTicket(newNormalTicket);
            id.add(newNormalTicket.getId().toString());
        }
        //discount
        for(int i=0;i<request.getNumberOfDiscountPasses();i++) {
            Ticket newDiscountTicket = new Ticket(ticketDTO.amountOfRides(), ticketDTO.pricePerRide()/2, "normal",
                    newOrder, priceList, ticketDTO.timeStart(), ticketDTO.timeEnd(), true,(ticketDTO.pricePerRide()/2)*ticketDTO.amountOfRides());
            ticketService.saveTicket(newDiscountTicket);
            id.add(newDiscountTicket.getId().toString());
        }
        return id;
    }
}
