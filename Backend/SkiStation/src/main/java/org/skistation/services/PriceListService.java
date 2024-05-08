package org.skistation.services;

import org.skistation.models.PriceList;
import org.skistation.repositories.PriceListRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PriceListService
{
    private final PriceListRepository priceListRepository;

    public PriceListService(PriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }

    public Optional<PriceList> getPriceListById(Integer id) {
        return priceListRepository.findById(id);
    }

    public Optional<PriceList> updatePriceList(PriceList priceList) {
        Optional<PriceList> priceListOptional = priceListRepository.findById(priceList.getId());
        if (priceListOptional.isPresent()) {
            priceListRepository.save(priceList);
        }
        return priceListOptional;
    }

    public PriceList savePriceList(PriceList priceList) {
        return priceListRepository.save(priceList);
    }

    public void deletePriceList(Integer id) {
        priceListRepository.deleteById(id);
    }

    public List<PriceList> getAllPriceLists() {
        return priceListRepository.findAll();
    }

    public List<PriceList> getPriceListWithinTimeRange(LocalDateTime timeStart, LocalDateTime timeEnd) {
        List<PriceList> priceLists = priceListRepository.findAll();
        priceLists.removeIf(priceList -> priceList.getTimeStart().isBefore(timeStart) || priceList.getTimeEnd().isAfter(timeEnd));
        return priceLists;
    }

    public List<PriceList> getPriceListWithinTimeRange(LocalDateTime timeNow) {
        List<PriceList> priceLists = priceListRepository.findAll();
        priceLists.removeIf(pL->timeNow.isBefore(pL.getTimeStart()) || timeNow.isAfter(pL.getTimeEnd()));
        return priceLists;
    }

}
