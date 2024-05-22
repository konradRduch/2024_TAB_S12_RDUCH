package org.skistation.controllers;

import java.util.ArrayList;
import java.util.List;

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
        return  reportService.getAllReports();
    }
}
