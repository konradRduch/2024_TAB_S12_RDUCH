package org.skistation.controllers;

import org.skistation.models.*;
import org.skistation.models.DTO.BuyPassRequest;
import org.skistation.models.DTO.PassDTO;
import org.skistation.services.ClientService;
import org.skistation.services.OrderService;
import org.skistation.services.PassService;
import org.skistation.services.PriceListService;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Controller for buying passes.
 * It is responsible for handling requests related to buying passes.
 */
@RestController
@CrossOrigin
@RequestMapping("/buyPasses")
public class BuyPassController
{
    /**
     * Service for managing clients.
     */
    private final ClientService clientService;

    /**
     * Service for managing orders.
     */
    private final OrderService orderService;

    /**
     * Service for managing passes.
     */
    private final PassService passService;

    /**
     * Service for managing price lists.
     */
    private final PriceListService priceListService;

    /**
     * Constructs a new BuyPassController with the specified services.
     *
     * @param clientService    the service for managing clients.
     * @param orderService     the service for managing orders.
     * @param passService      the service for managing passes.
     * @param priceListService the service for managing price lists.
     */
    public BuyPassController(ClientService clientService, OrderService orderService, PassService passService, PriceListService priceListService) {
        this.clientService = clientService;
        this.orderService = orderService;
        this.passService = passService;
        this.priceListService = priceListService;
    }

    /**
     * Buys a pass.
     *
     * @param request the request to buy a pass.
     * @return a list of IDs of the bought passes.
     */
    @PostMapping("")
    public List<String> buyPass(@RequestBody BuyPassRequest request) {

        LocalDate now = LocalDate.now();
        Client client = clientService.getClientByEmailAndPhone(request.getClient().getEmail(), request.getClient().getPhone());

        PassDTO passDTO = request.getPassDTO();
        Float total = request.getTotal();
        PriceList priceList = priceListService.getPriceListWithinTimeRange(now).get(0);

        List<String> passIds = new ArrayList<>();

        if (client == null) {
            client = request.getClient();
            if (clientService.getClientsByPhoneNumber(request.getClient().getPhone()) == null
                    && clientService.getClientsByEmailAddress(request.getClient().getEmail()) == null) {
                clientService.saveClient(client);
            }
            else {
                passIds.add("Error");
                return passIds;
            }
        }


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


        // Normal passes
        for (int i = 0; i < request.getNumberOfNormalPasses(); i++) {
            Pass newNormalPass = new Pass(active, passDTO.passTypeName(), roundedNormalPrice,
                    newOrder, priceList, passDTO.timeStart(), passDTO.timeEnd(), false);
            Pass savedPass = passService.savePass(newNormalPass);
            passIds.add(savedPass.getId().toString());
        }

        // Discount passes
        for (int i = 0; i < request.getNumberOfDiscountPasses(); i++) {
            Pass newDiscountPass = new Pass(active, passDTO.passTypeName(), roundedDiscountPrice,
                    newOrder, priceList, passDTO.timeStart(), passDTO.timeEnd(), true);
            Pass savedPass = passService.savePass(newDiscountPass);
            passIds.add(savedPass.getId().toString());
        }

        // Assuming the response is a list of pass IDs
        return passIds;
    }

    /**
     * Calculates the price of a pass.
     *
     * @param type  the type of the pass.
     * @param price the price of the pass.
     * @return the calculated price.
     */
    private Float calculatePassPrice(String type, Float price) {
        Float discountFactor = 1.0f;
        if (Objects.equals(type, "Day")) {
            discountFactor = 1.0f;
        }
        else if (Objects.equals(type, "Week")) {
            discountFactor = 7.0f * 0.6f;
        }
        else if (Objects.equals(type, "Month")) {
            discountFactor = 30f * 0.4f;
        }
        return price * discountFactor;
    }

}