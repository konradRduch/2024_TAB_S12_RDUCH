package org.skistation.models.DTO;

import java.util.List;
public record ReportDTO(String email, Integer phone, Float total, List<Float> totalPass, List<Float> totalTicket) {
}
