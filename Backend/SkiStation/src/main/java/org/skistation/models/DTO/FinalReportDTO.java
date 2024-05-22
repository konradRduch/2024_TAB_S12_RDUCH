package org.skistation.models.DTO;

import java.util.List;
public record FinalReportDTO (String email,Integer phone, Float total, List<Float>totalPass){
}
