package org.skistation.repositories;

import org.skistation.models.Pass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassRepository extends JpaRepository<Pass, Integer>
{
    List<Pass> findByOrderId(Integer orderId);

    List<Pass> findByPriceListId(Integer priceListId);
}
