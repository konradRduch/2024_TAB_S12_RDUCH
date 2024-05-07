package org.skistation.repositories;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.skistation.models.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer>
{
    Optional<Worker> findByEmail(String email);

    Optional<Worker> findByPhone(Integer phone);
}
