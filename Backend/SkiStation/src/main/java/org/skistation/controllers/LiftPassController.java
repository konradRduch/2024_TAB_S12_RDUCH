package org.skistation.controllers;

import org.skistation.models.DTO.BouncePassRequest;
import org.skistation.models.DTO.PassSummary;
import org.skistation.services.LiftPassService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Controller for managing lift passes.
 * It is responsible for handling requests related to lifts and passes history.
 */
@RestController
@RequestMapping("/liftPass")
public class LiftPassController
{
    /**
     * Service for managing lift passes.
     */
    private final LiftPassService liftPassService;

    /**
     * Constructs a new LiftPassController with the specified lift pass service.
     *
     * @param liftPassService the service for managing lift passes.
     */
    public LiftPassController(LiftPassService liftPassService) {
        this.liftPassService = liftPassService;
    }

    /**
     * Adds a new lift pass.
     *
     * @param request the request to add a lift pass.
     * @return a redirect to the lift passes page.
     */
    @PostMapping("/bounceliftPass")
    public String addLiftPass(@RequestBody BouncePassRequest request) {
        boolean active = liftPassService.isPassActive(request.getPassId());
        if (active) {
            liftPassService.addLiftPass(request.getLiftId(), request.getPassId());
        }
        return "redirect:/listPass";
    }

    /**
     * Gets a summary of a pass.
     *
     * @param liftId the ID of the lift.
     * @param passId the ID of the pass.
     * @return a summary of the pass.
     */
    @GetMapping("/summary")
    public PassSummary getSummary(@RequestParam int liftId, @RequestParam int passId) {
        Boolean active = liftPassService.isPassActive(passId);
        Float distance = liftPassService.getTotalTrackDistance(passId);
        LocalDateTime timeStart = liftPassService.getPassTimeStart(passId);
        LocalDateTime timeEnd = liftPassService.getPassTimeEnd(passId);
        Integer descentsNumber = liftPassService.getPassDescentsNumber(passId);
        return new PassSummary(active, distance, timeStart, timeEnd, descentsNumber);
    }
}