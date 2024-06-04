package org.skistation.repositories;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.skistation.models.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Represents a worker repository interface extending JpaRepository.
 * It provides methods to perform queries on the worker table in the database.
 */
@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer>
{
    /**
     * Finds a worker by their email.
     *
     * @param email the email to search for
     * @return an Optional containing the worker if found, or an empty Optional if not
     */
    Optional<Worker> findByEmail(String email);

    /**
     * Finds a worker by their phone number.
     *
     * @param phone the phone number to search for
     * @return an Optional containing the worker if found, or an empty Optional if not
     */
    Optional<Worker> findByPhone(Integer phone);
}