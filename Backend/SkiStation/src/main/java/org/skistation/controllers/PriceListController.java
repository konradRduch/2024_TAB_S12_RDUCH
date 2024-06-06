package org.skistation.controllers;

import java.time.LocalDate;
import java.util.List;

import org.skistation.models.PriceList;
import org.skistation.services.PriceListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing price lists.
 * It is responsible for handling requests related to price lists.
 */
@RestController
@CrossOrigin
@RequestMapping("/priceLists")
public class PriceListController
{

    /**
     * Service for managing price lists.
     */
    private final PriceListService priceListService;

    /**
     * Constructs a new PriceListController with the specified price list service.
     *
     * @param priceListService the service for managing price lists.
     */
    public PriceListController(PriceListService priceListService) {
        this.priceListService = priceListService;
    }

    /**
     * Gets a price list by ID.
     *
     * @param id the ID of the price list.
     * @return the price list with the specified ID.
     */
    @GetMapping("/{id}")
    public PriceList getPriceListById(@PathVariable("id") Integer id) {
        return priceListService.getPriceListById(id).get();
    }

    /**
     * Gets all price lists.
     *
     * @return a list of all price lists.
     */
    @GetMapping("")
    public List<PriceList> getAllPriceLists() {
        return priceListService.getAllPriceLists();
    }

    /**
     * Gets actual price lists.
     *
     * @return a list of actual price lists.
     */
    @GetMapping("/actual")
    public List<PriceList> getActualPriceLists() {
        return priceListService.getPriceListWithinTimeRange(LocalDate.now());
    }

    /**
     * Adds a new price list.
     *
     * @param priceList the price list to add.
     * @return a redirect to the price lists page.
     */
    @PostMapping("/addPriceList")
    public String addPriceList(@RequestBody PriceList priceList) {
        priceListService.savePriceList(priceList);

        return "redirect:/priceLists?success";
    }

    /**
     * Updates a price list.
     *
     * @param toUpdate the price list to update.
     * @param id       the ID of the price list to update.
     * @return a ResponseEntity containing the updated price list, or a not found status if no such price list exists.
     */
    @PutMapping("/{id}")
    ResponseEntity<?> updatePriceList(@RequestBody PriceList toUpdate, @PathVariable("id") Integer id) {
        if (priceListService.getPriceListById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        toUpdate.setId(id);
        priceListService.savePriceList(toUpdate);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a price list.
     *
     * @param id the ID of the price list to delete.
     * @return a redirect to the price lists page.
     */
    @DeleteMapping("/{id}")
    public String deletePriceList(@PathVariable("id") Integer id) {
        priceListService.deletePriceList(id);
        return "redirect:/priceLists";
    }
}