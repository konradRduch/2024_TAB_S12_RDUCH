package org.skistation.services;

import org.skistation.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a report service class.
 * It provides methods to generate reports.
 */
@Service
public class ReportService
{
    /**
     * The report repository to have access to data in the database.
     */
    private final ReportRepository reportRepository;

    /**
     * Constructs a new report service with the specified report repository.
     *
     * @param reportRepository the report repository to perform operations on the report table in the database
     */
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    /**
     * Retrieves all reports from the database.
     *
     * @return a list of all reports
     */
    public List<Object[]> getAllReports() {
        return reportRepository.findClientReports();
    }

    /**
     * Retrieves all reports by a specified date range from the database.
     *
     * @param startDate the start date of the range
     * @param endDate   the end date of the range
     * @return a list of all reports within the specified date range
     */
    public List<Object[]> getReportsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return reportRepository.findClientReportsByDateRange(startDate, endDate);
    }
}