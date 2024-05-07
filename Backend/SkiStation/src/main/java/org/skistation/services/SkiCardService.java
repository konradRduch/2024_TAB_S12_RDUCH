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

    public Optional<SkiCard> getSkiCardById(Integer id) {
        return skiCardRepository.findById(id);
    }

    public Optional<SkiCard> updateSkiCard(SkiCard skiCard) {
        Optional<SkiCard> skiCardOptional = skiCardRepository.findById(skiCard.getId());
        if (skiCardOptional.isPresent()) {
            skiCardRepository.save(skiCard);
        }
        return skiCardOptional;
    }

    public SkiCard saveSkiCard(SkiCard skiCard) {
        return skiCardRepository.save(skiCard);
    }

    public void deleteSkiCard(Integer id) {
        skiCardRepository.deleteById(id);
    }

    public List<SkiCard> getAllSkiCards() {
        return skiCardRepository.findAll();
    }
}
