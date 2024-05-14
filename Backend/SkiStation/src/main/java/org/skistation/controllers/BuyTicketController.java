package org.skistation.controllers;


import org.skistation.models.Client;
import org.skistation.models.DTO.BuyTicketRequest;
import org.skistation.models.DTO.TicketDTO;
import org.skistation.models.Order;
import org.skistation.models.PriceList;
import org.skistation.models.Ticket;
import org.skistation.services.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/buyTickets")
@CrossOrigin("http://localhost:4321")
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
    public String buyTicket(@RequestBody BuyTicketRequest request) {
        Client client = request.getClient();
        TicketDTO ticketDTO = request.getTicketDTO();
        Float total = request.getTotal();
        PriceList priceList = request.getPriceList();
        priceListService.savePriceList(priceList);
        clientService.saveClient(client);
        Order newOrder = new Order(total, client);
        orderService.saveOrder(newOrder);
        Ticket newTicket=new Ticket(ticketDTO.amountOfRides(),ticketDTO.pricePerRide(),ticketDTO.ticketTypeName(),
                newOrder,priceList,ticketDTO.timeStart(),ticketDTO.timeEnd(),ticketDTO.discount());
        ticketService.saveTicket(newTicket);
        return "redirect:/buyTickets";
    }


}
