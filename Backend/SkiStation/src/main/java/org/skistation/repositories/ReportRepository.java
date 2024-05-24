package org.skistation.repositories;


import java.time.LocalDateTime;
import java.util.List;
import org.skistation.models.Client;
import org.skistation.models.DTO.ReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Client,Integer> {

    @Query("SELECT (o.id,c.email, c.phone, o.total, p.price, t.total, o.orderDate) " +
            "FROM Order o " +
            "LEFT JOIN Client c ON c.id = o.client.id " +
            "LEFT JOIN Pass p ON o.id = p.order.id " +
            "LEFT JOIN Ticket t ON o.id = t.order.id "
           )
    List<Object[]> findClientReports();

    @Query("SELECT (o.id,c.email, c.phone, o.total, p.price, t.total, o.orderDate ) " +
            "FROM Order o " +
            "LEFT JOIN Client c ON c.id = o.client.id " +
            "LEFT JOIN Pass p ON o.id = p.order.id " +
            "LEFT JOIN Ticket t ON o.id = t.order.id " +
            "WHERE o.orderDate BETWEEN :startDate AND :endDate")
    List<Object[]> findClientReportsByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


}
