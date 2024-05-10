package org.skistation.models.DTO;


import java.time.LocalDateTime;

public class SkiScheduleRequest {

    private LocalDateTime open;
    private LocalDateTime close;
    private Integer liftId;


    public LocalDateTime getOpen() {
        return open;
    }

    public void setOpen(LocalDateTime open) {
        this.open = open;
    }

    public LocalDateTime getClose() {
        return close;
    }

    public void setClose(LocalDateTime close) {
        this.close = close;
    }

    public Integer getLiftId() {
        return liftId;
    }

    public void setLiftId(Integer liftId) {
        this.liftId = liftId;
    }
}
