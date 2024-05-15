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

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

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

        DecimalFormat df = new DecimalFormat("#.##");
        df.setMaximumFractionDigits(2); // Ustawienie maksymalnej liczby miejsc po przecinku


        Float normalPrice = calculatePassPrice(passDTO.passTypeName(), priceList.getPassPrice());
        Float roundedNormalPrice = Float.valueOf(df.format(normalPrice));
        Float discountPrice = calculatePassPrice(passDTO.passTypeName(), priceList.getPassPrice()) / 2;
        Float roundedDiscountPrice = Float.valueOf(df.format(discountPrice));
       //normal
        for(int i=0;i<request.getNumberOfNormalPasses();i++) {
            Pass newNormalPass = new Pass(passDTO.active(), passDTO.passTypeName(), roundedNormalPrice,
                    newOrder, priceList, passDTO.timeStart(), passDTO.timeEnd(), false);
            passService.savePass(newNormalPass);
        }
        //discount
        for(int i=0;i<request.getNumberOfDiscountPasses();i++) {
            Pass newDiscountPass = new Pass(passDTO.active(), passDTO.passTypeName(), roundedDiscountPrice,
                    newOrder, priceList, passDTO.timeStart(), passDTO.timeEnd(), true);
            passService.savePass(newDiscountPass);
        }

        return "redirect:/buyPasses";
    }

    private Float calculatePassPrice(String type, Float price){
        Float discountFactor = 1.0f;
        if(Objects.equals(type, "Day")){
            discountFactor =1.0f;
        }else if(Objects.equals(type, "Week")){
            discountFactor = 7.0f * 0.6f;
        } else if (Objects.equals(type, "Month")) {
            discountFactor = 30f * 0.4f;
        }
    return price * discountFactor;
    }

}
