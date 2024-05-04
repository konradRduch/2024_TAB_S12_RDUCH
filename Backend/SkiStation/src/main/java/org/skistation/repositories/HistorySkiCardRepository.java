package org.skistation.repositories;

import org.skistation.models.HistorySkiCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorySkiCardRepository extends JpaRepository<HistorySkiCard, Integer>
{
}
