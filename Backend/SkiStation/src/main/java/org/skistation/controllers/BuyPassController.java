package org.skistation.controllers;


import org.skistation.models.*;
import org.skistation.models.DTO.BuyPassRequest;
import org.skistation.models.DTO.BuyTicketRequest;
import org.skistation.models.DTO.PassDTO;
import org.skistation.models.DTO.TicketDTO;
import org.skistation.services.ClientService;
import org.skistation.services.OrderService;
import org.skistation.services.PassService;
import org.skistation.services.PriceListService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/buyPasses")
public class BuyPassController {


    private final ClientService clientService;
    private final OrderService orderService;
    private final PassService passService;
    private final PriceListService priceListService;

    public BuyPassController(ClientService clientService, OrderService orderService, PassService passService, PriceListService priceListService) {
        this.clientService = clientService;
        this.orderService = orderService;
        this.passService = passService;
        this.priceListService = priceListService;
    }

    @PostMapping("")
    public String buyPass(@RequestBody BuyPassRequest request){
        Client client = request.getClient();
        PassDTO passDTO = request.getPassDTO();
        Float total = request.getTotal();
        PriceList priceList = request.getPriceList();
        priceListService.savePriceList(priceList);
        clientService.saveClient(client);
        Order newOrder = new Order(total, client);
        orderService.saveOrder(newOrder);
        Pass newPass = new Pass(passDTO.active(),passDTO.passTypeName(),passDTO.price(),
                newOrder,priceList,passDTO.timeStart(),passDTO.timeEnd(),passDTO.discount());
        passService.savePass(newPass);
        return "redirect:/buyPasses";
    }
}
