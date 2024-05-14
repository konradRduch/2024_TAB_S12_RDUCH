package org.skistation.models.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record  SkiScheduleDTO(Integer id,String open, String close, Integer liftId, String liftName, Boolean active, Float distance) {





}
