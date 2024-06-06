package org.skistation.models.DTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a report data transfer object.
 *
 * @param orderId     The ID of the order.
 * @param email       The email associated with the order.
 * @param phone       The phone number associated with the order.
 * @param total       The total amount of the order.
 * @param totalPass   The total amount for passes in the order.
 * @param totalTicket The total amount for tickets in the order.
 * @param dateTime    The date and time of the report.
 */
public record ReportDTO(
        Integer orderId,
        String email,
        Integer phone,
        Float total,
        List<Float> totalPass,
        List<Float> totalTicket,
        LocalDateTime dateTime)
{
}