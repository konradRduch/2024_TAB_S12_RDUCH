package org.skistation.controllers;


import org.skistation.models.DTO.BouncePassRequest;
import org.skistation.models.DTO.BounceTicketRequest;
import org.skistation.models.DTO.PassSummary;
import org.skistation.models.DTO.TicketSummary;
import org.skistation.services.LiftPassService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/liftPass")
public class LiftPassController
{
    private final LiftPassService liftPassService;

    public LiftPassController(LiftPassService liftPassService) {
        this.liftPassService = liftPassService;
    }

    @PostMapping("/bounceliftPass")
    public String addLiftPass(@RequestBody BouncePassRequest request) {
        boolean active = liftPassService.isPassActive(request.getPassId());
        if(active) {
            liftPassService.addLiftPass(request.getLiftId(), request.getPassId());
        }
        return "redirect:/listPass";
    }

    @GetMapping("/summary")
    public PassSummary getSummary(@RequestParam int liftId, @RequestParam int passId) {
        Boolean active = liftPassService.isPassActive(passId);
        Float distance = liftPassService.getTotalTrackDistance(passId);
        return new PassSummary(active, distance);
    }
}
