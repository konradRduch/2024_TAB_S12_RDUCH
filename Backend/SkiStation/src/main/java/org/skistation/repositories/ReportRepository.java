package org.skistation.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.skistation.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Represents a report repository interface extending JpaRepository.
 * It provides methods to perform queries on the client, pass and ticket tables in the database and generate reports.
 */
@Repository
public interface ReportRepository extends JpaRepository<Client, Integer>
{

    /**
     * Finds client reports by joining Order, Client, Pass, and Ticket tables.
     * The query selects the order ID, client email, client phone, order total, pass price, ticket total, and order date.
     *
     * @return a list of client reports with orders and their associated client, pass, and ticket
     */
    @Query("SELECT (o.id,c.email, c.phone, o.total, p.price, t.total, o.orderDate) " +
            "FROM Order o " +
            "LEFT JOIN Client c ON c.id = o.client.id " +
            "LEFT JOIN Pass p ON o.id = p.order.id " +
            "LEFT JOIN Ticket t ON o.id = t.order.id "
    )
    List<Object[]> findClientReports();

    /**
     * Finds client reports by joining Order, Client, Pass, and Ticket tables within a specified date range.
     * The query selects the order ID, client email, client phone, order total, pass price, ticket total, and order date.
     *
     * @param startDate the start date of the range
     * @param endDate   the end date of the range
     * @return a list of client reports with orders and their associated client, pass, and ticket within the specified date range
     */
    @Query("SELECT (o.id,c.email, c.phone, o.total, p.price, t.total, o.orderDate ) " +
            "FROM Order o " +
            "LEFT JOIN Client c ON c.id = o.client.id " +
            "LEFT JOIN Pass p ON o.id = p.order.id " +
            "LEFT JOIN Ticket t ON o.id = t.order.id " +
            "WHERE o.orderDate BETWEEN :startDate AND :endDate")
    List<Object[]> findClientReportsByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}