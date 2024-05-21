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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    public List<String> buyPass(@RequestBody BuyPassRequest request) {
        LocalDate now = LocalDate.now();
        Client client = request.getClient();
        PassDTO passDTO = request.getPassDTO();
        Float total = request.getTotal();
        PriceList priceList = priceListService.getPriceListWithinTimeRange(now).get(0);
        clientService.saveClient(client);
        Order newOrder = new Order(total, client);
        orderService.saveOrder(newOrder);

        LocalDateTime dateTime = LocalDateTime.now();
        boolean active = dateTime.isAfter(passDTO.timeStart()) && dateTime.isBefore(passDTO.timeEnd());

        DecimalFormat df = new DecimalFormat("#.##");
        df.setMaximumFractionDigits(2);

        Float normalPrice = calculatePassPrice(passDTO.passTypeName(), priceList.getPassPrice());
        Float roundedNormalPrice = Float.valueOf(df.format(normalPrice));
        Float discountPrice = calculatePassPrice(passDTO.passTypeName(), priceList.getPassPrice()) / 2;
        Float roundedDiscountPrice = Float.valueOf(df.format(discountPrice));

        List<String> passIds = new ArrayList<>();

        // Normal passes
        for(int i = 0; i < request.getNumberOfNormalPasses(); i++) {
            Pass newNormalPass = new Pass(active, passDTO.passTypeName(), roundedNormalPrice,
                    newOrder, priceList, passDTO.timeStart(), passDTO.timeEnd(), false);
            Pass savedPass = passService.savePass(newNormalPass);
            passIds.add(savedPass.getId().toString());
        }

        // Discount passes
        for(int i = 0; i < request.getNumberOfDiscountPasses(); i++) {
            Pass newDiscountPass = new Pass(active, passDTO.passTypeName(), roundedDiscountPrice,
                    newOrder, priceList, passDTO.timeStart(), passDTO.timeEnd(), true);
            Pass savedPass = passService.savePass(newDiscountPass);
            passIds.add(savedPass.getId().toString());
        }

        // Assuming the response is a list of pass IDs
        return passIds;
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
