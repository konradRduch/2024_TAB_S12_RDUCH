package org.skistation.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.skistation.models.DTO.ReportDTO;
import org.skistation.services.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing reports.
 * It is responsible for handling requests related to reports.
 */
@RestController
@CrossOrigin
@RequestMapping("/reports")
public class ReportController
{

    /**
     * Service for managing reports.
     */
    private final ReportService reportService;

    /**
     * Constructs a new ReportController with the specified report service.
     *
     * @param reportService the service for managing reports.
     */
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Gets all reports.
     *
     * @return a list of all reports.
     */
    @GetMapping("")
    public List<ReportDTO> getAllReports() {
        List<Object[]> rawReports = reportService.getAllReports();
        return getReportDTOS(rawReports);
    }

    /**
     * Gets reports by date range.
     *
     * @param startDate the start date of the range.
     * @param endDate   the end date of the range.
     * @return a list of reports within the specified date range.
     */
    @GetMapping("/byDate")
    public List<ReportDTO> getReportsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        List<Object[]> rawReports = reportService.getReportsByDateRange(startDate, endDate);
        return getReportDTOS(rawReports);
    }

    /**
     * Converts a list of raw reports to a list of report DTOs.
     *
     * @param rawReports the raw reports.
     * @return a list of report DTOs.
     */
    private List<ReportDTO> getReportDTOS(List<Object[]> rawReports) {
        Map<Integer, ReportDTO> reportMap = new HashMap<>();

        for (Object[] row : rawReports) {
            Integer orderId = (Integer) row[0];
            String email = (String) row[1];
            Integer phone = (Integer) row[2];
            Float orderTotal = (Float) row[3];
            Float passTotal = (Float) row[4];
            Float ticketTotal = (Float) row[5];
            LocalDateTime orderDate = (LocalDateTime) row[6];

            ReportDTO report = reportMap.get(orderId);
            if (report == null) {
                report = new ReportDTO(orderId, email, phone, orderTotal, new ArrayList<>(), new ArrayList<>(), orderDate);
                reportMap.put(orderId, report);
            }
            if (passTotal != null) {
                report.totalPass().add(passTotal);
            }
            if (ticketTotal != null) {
                report.totalTicket().add(ticketTotal);
            }
        }

        return new ArrayList<>(reportMap.values());
    }
}