package org.skistation.models.DTO;

import java.time.LocalDate;

public record  SkiScheduleDTO(LocalDate open,LocalDate close,Integer id, String liftName,Boolean active, Float distance) {





}
