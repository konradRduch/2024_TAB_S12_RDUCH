package org.skistation.controllers;


import java.util.List;

import org.skistation.models.PriceList;
import org.skistation.models.PriceList;
import org.skistation.services.PriceListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/priceLists")
public class PriceListController {

    private final PriceListService priceListService;

    public PriceListController(PriceListService priceListService) {
        this.priceListService = priceListService;
    }
    @GetMapping("/{id}")
    public PriceList getPriceListById(@PathVariable("id")Integer id){
        return priceListService.getPriceListById(id).get();
    }

    @GetMapping("/")
    public List<PriceList> getAllPriceLists(){
        return priceListService.getAllPriceLists();
    }

    @PostMapping("/addPriceList")
    public String addPriceList(@RequestBody PriceList priceList) {
        priceListService.savePriceList(priceList);

        return "redirect:/priceLists";
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updatePriceList(@RequestBody PriceList toUpdate, @PathVariable("id") Integer id) {
        if (priceListService.getPriceListById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        toUpdate.setId(id);
        priceListService.savePriceList(toUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public String deletePriceList(@PathVariable("id") Integer id) {
        priceListService.deletePriceList(id);
        return "redirect:/priceLists";
    }
}
