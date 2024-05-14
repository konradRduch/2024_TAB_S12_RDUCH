package org.skistation.controllers;


import org.skistation.models.Client;
import org.skistation.models.DTO.PassDTO;
import org.skistation.models.Order;
import org.skistation.models.Pass;
import org.skistation.services.ClientService;
import org.skistation.services.OrderService;
import org.skistation.services.PassService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/buyPasses")
public class BuyPassController {


    private final ClientService clientService;
    private final OrderService orderService;
    private final PassService passService;


    public BuyPassController(ClientService clientService, OrderService orderService, PassService passService) {
        this.clientService = clientService;
        this.orderService = orderService;
        this.passService = passService;
    }

    @PostMapping("")
    public String buyPass(@RequestBody Client client, @RequestBody PassDTO passDTO, @RequestBody Float total){
        clientService.saveClient(client);
        Order newOrder= new Order(total,client);
        orderService.saveOrder(newOrder);
        Pass newPass = new Pass(passDTO.active(),passDTO.passTypeName(),passDTO.price(),
                newOrder,passDTO.priceList(),passDTO.timeStart(),passDTO.timeEnd(),passDTO.discount());

        passService.savePass(newPass);

        return "redirect:/buyPasses";

    }
}
