package org.skistation.services;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.skistation.models.DTO.ReportDTO;
import org.skistation.repositories.ReportRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportService  {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Object[]>getAllReports(){
       return reportRepository.findClientReports();
    }


    public List<Object[]> getReportsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return reportRepository.findClientReportsByDateRange(startDate, endDate);
    }

}
