package org.skistation.repositories;

import org.skistation.models.Lift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Represents a lift repository interface extending JpaRepository.
 * It provides methods to perform queries on the lift table in the database.
 */
@Repository
public interface LiftRepository extends JpaRepository<Lift, Integer>
{
}