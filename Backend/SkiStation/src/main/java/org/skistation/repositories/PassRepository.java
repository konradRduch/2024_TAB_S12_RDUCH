package org.skistation.repositories;

import org.skistation.models.Pass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Represents a pass repository interface extending JpaRepository.
 * It provides methods to perform queries on the pass table in the database.
 */
@Repository
public interface PassRepository extends JpaRepository<Pass, Integer>
{
    /**
     * Finds passes by their order ID.
     *
     * @param orderId the order ID to search for
     * @return a list of passes with the specified order ID
     */
    List<Pass> findByOrderId(Integer orderId);

    /**
     * Finds passes by their price list ID.
     *
     * @param priceListId the price list ID to search for
     * @return a list of passes with the specified price list ID
     */
    List<Pass> findByPriceListId(Integer priceListId);
}