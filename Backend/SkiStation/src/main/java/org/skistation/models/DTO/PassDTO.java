package org.skistation.models.DTO;

import org.skistation.models.Order;
import org.skistation.models.PriceList;

import java.time.LocalDateTime;

public record PassDTO(Boolean active, String passTypeName, Float price, LocalDateTime timeStart, LocalDateTime timeEnd, Boolean discount) {
}
