package org.skistation.services;

import org.skistation.models.PriceList;
import org.skistation.repositories.PriceListRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Represents a price list service class.
 * It provides methods to perform operations on the price list table in the database.
 */
@Service
public class PriceListService
{
    /**
     * The price list repository to perform operations on the price list table in the database.
     */
    private final PriceListRepository priceListRepository;

    /**
     * Constructs a new price list service with the specified price list repository.
     *
     * @param priceListRepository the price list repository to perform operations on the price list table in the database
     */
    public PriceListService(PriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }

    /**
     * Retrieves a price list by their ID.
     *
     * @param id the ID of the price list to retrieve
     * @return an Optional containing the price list if found, or an empty Optional if not
     */
    public Optional<PriceList> getPriceListById(Integer id) {
        return priceListRepository.findById(id);
    }

    /**
     * Modifies a price list in the database.
     *
     * @param priceList the price list to modify
     * @return an Optional containing the modified price list if found, or an empty Optional if not
     */
    public Optional<PriceList> updatePriceList(PriceList priceList) {
        Optional<PriceList> priceListOptional = priceListRepository.findById(priceList.getId());
        if (priceListOptional.isPresent()) {
            priceListRepository.save(priceList);
        }
        return priceListOptional;
    }

    /**
     * Saves a price list to the database.
     *
     * @param priceList the price list to save
     * @return the saved price list
     */
    public PriceList savePriceList(PriceList priceList) {
        return priceListRepository.save(priceList);
    }

    /**
     * Deletes a price list from the database by their ID.
     *
     * @param id the ID of the price list to delete
     */
    public void deletePriceList(Integer id) {
        priceListRepository.deleteById(id);
    }

    /**
     * Retrieves all price lists from the database.
     *
     * @return a list of all price lists
     */
    public List<PriceList> getAllPriceLists() {
        return priceListRepository.findAll();
    }

    /**
     * Retrieves all price lists within a specified time range from the database.
     *
     * @param timeNow the current time
     * @return a list of all price lists within the specified time range
     */
    public List<PriceList> getPriceListWithinTimeRange(LocalDate timeNow) {
        List<PriceList> priceLists = priceListRepository.findAll();
        priceLists.removeIf(pL -> timeNow.isBefore(pL.getTimeStart()) || timeNow.isAfter(pL.getTimeEnd()));
        return priceLists;
    }
}