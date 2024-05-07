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

    public Optional<HistorySkiCard> getHistorySkiCardById(Integer id) {
        return historySkiCardRepository.findById(id);
    }

    public Optional<HistorySkiCard> updateHistorySkiCard(HistorySkiCard historySkiCard) {
        Optional<HistorySkiCard> historySkiCardOptional = historySkiCardRepository.findById(historySkiCard.getId());
        if (historySkiCardOptional.isPresent()) {
            historySkiCardRepository.save(historySkiCard);
        }
        return historySkiCardOptional;
    }

    public HistorySkiCard saveHistorySkiCard(HistorySkiCard historySkiCard) {
        return historySkiCardRepository.save(historySkiCard);
    }

    public void deleteHistorySkiCard(Integer id) {
        historySkiCardRepository.deleteById(id);
    }

    public List<HistorySkiCard> getAllHistorySkiCards() {
        return historySkiCardRepository.findAll();
    }

    public List<HistorySkiCard> getHistorySkiCardsByLiftId(Integer clientId) {
        return historySkiCardRepository.findByLiftId(clientId);
    }

    public List<HistorySkiCard> getHistorySkiCardsBySkiCardId(Integer skiCardId) {
        return historySkiCardRepository.findBySkiCardId(skiCardId);
    }
}
