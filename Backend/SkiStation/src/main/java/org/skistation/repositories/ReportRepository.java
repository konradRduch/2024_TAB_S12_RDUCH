package org.skistation.repositories;


import java.util.List;
import org.skistation.models.Client;
import org.skistation.models.DTO.ReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Client,Integer> {

    @Query("SELECT (o.id,c.email, c.phone, o.total, p.price, t.total) " +
            "FROM Order o " +
            "LEFT JOIN Client c ON c.id = o.client.id " +
            "LEFT JOIN Pass p ON o.id = p.order.id " +
            "LEFT JOIN Ticket t ON o.id = t.order.id "
           )
    List<Object[]> findClientReports();

}
