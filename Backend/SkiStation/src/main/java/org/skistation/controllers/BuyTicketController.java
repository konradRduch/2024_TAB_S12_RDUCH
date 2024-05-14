package org.skistation.controllers;


import org.skistation.models.Client;
import org.skistation.models.DTO.TicketDTO;
import org.skistation.models.Order;
import org.skistation.models.PriceList;
import org.skistation.models.Ticket;
import org.skistation.services.ClientService;
import org.skistation.services.OrderService;
import org.skistation.services.PassService;
import org.skistation.services.TicketService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/buyTickets")
public class BuyTicketController {

    private final OrderService orderService;
    private final ClientService clientService;
    private final TicketService ticketService;


    public BuyTicketController(OrderService orderService, ClientService clientService, TicketService ticketService) {
        this.orderService = orderService;
        this.clientService = clientService;
        this.ticketService = ticketService;
    }


    @PostMapping("")
    public String buyTicket(@RequestBody Client client, @RequestBody TicketDTO ticketDTO, @RequestBody Float total,@RequestBody PriceList priceList){
        clientService.saveClient(client);
        Order newOrder = new Order(total, client);
        orderService.saveOrder(newOrder);
        Ticket newTicket=new Ticket(ticketDTO.amountOfRides(),ticketDTO.pricePerRide(),ticketDTO.ticketTypeName(),
                newOrder,priceList,ticketDTO.timeStart(),ticketDTO.timeEnd(),ticketDTO.discount());
        ticketService.saveTicket(newTicket);
        return "redirect:/buyTickets";
    }
}
