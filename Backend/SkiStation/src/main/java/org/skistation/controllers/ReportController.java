package org.skistation.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.skistation.models.Client;
import org.skistation.models.DTO.ReportDTO;
import org.skistation.models.Order;
import org.skistation.models.Pass;
import org.skistation.models.Ticket;
import org.skistation.services.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @GetMapping("")
    public List<ReportDTO> getAllReports(){


        List<Object[]> rawReports = reportService.getAllReports();
        Map<String, ReportDTO> reportMap = new HashMap<>();

        for (Object[] row : rawReports) {
            String email = (String) row[0];
            Integer phone = (Integer) row[1];
            Float orderTotal = (Float) row[2];
            Float passTotal = (Float) row[3];
            Float ticketTotal = (Float)row[4];

            ReportDTO report = reportMap.get(email);
            if (report == null) {
                report = new ReportDTO(email, phone, orderTotal, new ArrayList<>(), new ArrayList<>());
                reportMap.put(email, report);
            }
            if(passTotal!=null){
                report.totalPass().add(passTotal);
            }
            if(ticketTotal!=null){
                report.totalTicket().add(ticketTotal);
            }

        }

        return new ArrayList<>(reportMap.values());
    }
}

