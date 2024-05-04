package org.skistation.services;

import org.skistation.models.PriceList;
import org.skistation.repositories.PriceListRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PriceListService
{
    private final PriceListRepository priceListRepository;

    public PriceListService(PriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }

    Optional<PriceList> getPriceListById(Integer id) {
        return priceListRepository.findById(id);
    }

    Optional<PriceList> modifyPriceList(PriceList priceList) {
        Optional<PriceList> priceListOptional = priceListRepository.findById(priceList.getId());
        if (priceListOptional.isPresent()) {
            priceListRepository.save(priceList);
        }
        return priceListOptional;
    }

    PriceList savePriceList(PriceList priceList) {
        return priceListRepository.save(priceList);
    }

    void deletePriceList(Integer id) {
        priceListRepository.deleteById(id);
    }

    List<PriceList> getAllPriceLists() {
        return priceListRepository.findAll();
    }

    List<PriceList> getPriceListWithinTimeRange(LocalDate timeStart, LocalDate timeEnd) {
        List<PriceList> priceLists = priceListRepository.findAll();
        priceLists.removeIf(priceList -> priceList.getTimeStart().isBefore(timeStart) || priceList.getTimeEnd().isAfter(timeEnd));
        return priceLists;
    }
}
