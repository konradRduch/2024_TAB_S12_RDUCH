package org.skistation.services;

import org.skistation.models.SkiCard;
import org.skistation.repositories.SkiCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkiCardService
{
    private final SkiCardRepository skiCardRepository;

    public SkiCardService(SkiCardRepository skiCardRepository) {
        this.skiCardRepository = skiCardRepository;
    }

    Optional<SkiCard> getSkiCardById(Integer id) {
        return skiCardRepository.findById(id);
    }

    Optional<SkiCard> modifySkiCard(SkiCard skiCard) {
        Optional<SkiCard> skiCardOptional = skiCardRepository.findById(skiCard.getId());
        if (skiCardOptional.isPresent()) {
            skiCardRepository.save(skiCard);
        }
        return skiCardOptional;
    }

    SkiCard saveSkiCard(SkiCard skiCard) {
        return skiCardRepository.save(skiCard);
    }

    void deleteSkiCard(Integer id) {
        skiCardRepository.deleteById(id);
    }

    List<SkiCard> getAllSkiCards() {
        return skiCardRepository.findAll();
    }
}
