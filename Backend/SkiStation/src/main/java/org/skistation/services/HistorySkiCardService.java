package org.skistation.services;

import org.skistation.repositories.HistorySkiCardRepository;
import org.springframework.stereotype.Service;

@Service
public class HistorySkiCardService
{
    private final HistorySkiCardRepository historySkiCardRepository;

    public HistorySkiCardService(HistorySkiCardRepository historySkiCardRepository) {
        this.historySkiCardRepository = historySkiCardRepository;
    }
}
