package org.skistation.repositories;


import java.util.List;
import org.skistation.models.Client;
import org.skistation.models.DTO.ReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Client,Integer> {

    @Query("SELECT new org.skistation.models.DTO.ReportDTO (c.email, c.phone, o.total, p.price) " +
            "FROM Client c " +
            "JOIN Order o ON c.id = o.client.id " +
            "JOIN Pass p ON o.id = p.order.id "
           )
    List<ReportDTO> findClientReports();

}

// "JOIN Ticket t ON o.orderId = t.orderId"