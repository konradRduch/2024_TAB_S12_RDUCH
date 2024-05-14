package org.skistation.models.DTO;

import java.time.LocalDateTime;

public record TicketDTO(Integer amountOfRides, Float pricePerRide, String ticketTypeName, LocalDateTime timeStart,LocalDateTime timeEnd,Boolean discount) {

}
