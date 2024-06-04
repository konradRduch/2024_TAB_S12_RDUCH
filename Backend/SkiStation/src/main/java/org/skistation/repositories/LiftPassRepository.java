package org.skistation.repositories;

import org.skistation.models.LiftPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Represents a lift pass repository interface extending JpaRepository.
 * It provides methods to perform queries on the lift pass table in the database.
 * Lift pass table is the join table between the client and ticket tables.
 */
@Repository
public interface LiftPassRepository extends JpaRepository<LiftPass, Integer>
{
    /**
     * Finds lift passes by their lift ID.
     *
     * @param liftId the lift ID to search for
     * @return a list of lift passes with the specified lift ID
     */
    List<LiftPass> findByLiftId(Integer liftId);

    /**
     * Finds lift passes by their pass ID.
     *
     * @param passId the pass ID to search for
     * @return a list of lift passes with the specified pass ID
     */
    List<LiftPass> findByPassId(Integer passId);
}