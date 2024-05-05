package org.skistation.repositories;

import org.skistation.models.HistorySkiCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorySkiCardRepository extends JpaRepository<HistorySkiCard, Integer>
{
    List<HistorySkiCard> findByLiftId(Integer liftId);

    List<HistorySkiCard> findBySkiCardId(Integer skiCardId);
}
