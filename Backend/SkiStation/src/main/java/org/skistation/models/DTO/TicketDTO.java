package org.skistation.models.DTO;

import java.time.LocalDateTime;

public record TicketDTO(Integer amountOfRides, Float pricePerRide, LocalDateTime timeStart,LocalDateTime timeEnd) {

}
