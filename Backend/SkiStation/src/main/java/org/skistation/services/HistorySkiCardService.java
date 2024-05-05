package org.skistation.services;

import org.skistation.models.HistorySkiCard;
import org.skistation.repositories.HistorySkiCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistorySkiCardService
{
    private final HistorySkiCardRepository historySkiCardRepository;

    public HistorySkiCardService(HistorySkiCardRepository historySkiCardRepository) {
        this.historySkiCardRepository = historySkiCardRepository;
    }

    Optional<HistorySkiCard> getHistorySkiCardById(Integer id) {
        return historySkiCardRepository.findById(id);
    }

    Optional<HistorySkiCard> modifyHistorySkiCard(HistorySkiCard historySkiCard) {
        Optional<HistorySkiCard> historySkiCardOptional = historySkiCardRepository.findById(historySkiCard.getId());
        if (historySkiCardOptional.isPresent()) {
            historySkiCardRepository.save(historySkiCard);
        }
        return historySkiCardOptional;
    }

    HistorySkiCard saveHistorySkiCard(HistorySkiCard historySkiCard) {
        return historySkiCardRepository.save(historySkiCard);
    }

    void deleteHistorySkiCard(Integer id) {
        historySkiCardRepository.deleteById(id);
    }

    List<HistorySkiCard> getAllHistorySkiCards() {
        return historySkiCardRepository.findAll();
    }

    List<HistorySkiCard> getHistorySkiCardsByLiftId(Integer clientId) {
        return historySkiCardRepository.findByLiftId(clientId);
    }

    List<HistorySkiCard> getHistorySkiCardsBySkiCardId(Integer skiCardId) {
        return historySkiCardRepository.findBySkiCardId(skiCardId);
    }
}
