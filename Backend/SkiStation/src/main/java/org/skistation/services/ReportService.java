package org.skistation.services;


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

    public List<ReportDTO>getAllReports(){
       return reportRepository.findClientReports();
    }

    public List<ReportDTO>getReportsByClientEmail(String email){
        return reportRepository.findClientReports().stream().filter(reportDTO -> reportDTO.email().equals(email)).collect(Collectors.toList());
    }
}
