package org.skistation.services;

import org.skistation.repositories.PriceListRepository;
import org.springframework.stereotype.Service;

@Service
public class PriceListService
{
    private final PriceListRepository priceListRepository;

    public PriceListService(PriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }
}
