package org.skistation.models.DTO;

import org.skistation.models.Order;
import org.skistation.models.PriceList;

import java.time.LocalDateTime;

public record PassDTO(Boolean active, String passTypeName, LocalDateTime timeStart, LocalDateTime timeEnd) {
}
