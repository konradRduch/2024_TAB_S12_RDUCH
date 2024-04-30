package org.skistation.repositories;

import org.skistation.models.SkiCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkiCardRepository extends JpaRepository<SkiCard, Long>
{
}
