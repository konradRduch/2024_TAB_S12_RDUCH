package org.skistation.repositories;

import org.skistation.models.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Represents a price list repository interface extending JpaRepository.
 * It provides methods to perform queries on the price list table in the database.
 */
@Repository
public interface PriceListRepository extends JpaRepository<PriceList, Integer>
{
}