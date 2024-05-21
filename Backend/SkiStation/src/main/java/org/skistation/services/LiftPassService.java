package org.skistation.services;

import org.skistation.models.Lift;
import org.skistation.models.LiftPass;
import org.skistation.models.LiftTicket;
import org.skistation.models.Pass;
import org.skistation.repositories.LiftPassRepository;
import org.skistation.repositories.LiftTicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LiftPassService
{
    private final LiftPassRepository liftPassRepository;
    private final LiftService liftService;
    private final PassService passService;

    public LiftPassService(LiftPassRepository liftPassRepository, LiftService liftService, PassService passService, LiftTicketRepository liftTicketRepository) {
        this.liftPassRepository = liftPassRepository;
        this.liftService = liftService;
        this.passService = passService;
    }

    public void addLiftPass(int liftId, int passId) {
        Lift lift = liftService.getLiftById(liftId).orElseGet(null);
        Pass pass = passService.getPassById(passId).orElseGet(null);
        if (lift == null || pass == null) {
            return;
        }
        addLiftPass(lift, pass);
    }

    public void addLiftPass(Lift lift, Pass pass) {
        liftPassRepository.save(new LiftPass(lift, pass));
    }

    public List<LiftPass> getLiftPasses() {
        return liftPassRepository.findAll();
    }

    public List<LiftPass> getLiftPassesByLiftId(int liftId) {
        return liftPassRepository.findByLiftId(liftId);
    }

    public List<LiftPass> getLiftPassesByPassId(int passId) {
        return liftPassRepository.findByPassId(passId);
    }

    public Float getTotalTrackDistance(Integer passId) {

        List<LiftPass> liftPasses = getLiftPassesByLiftId(passId);

        double totalDistance = liftPasses.stream()
                .mapToDouble(pass -> pass.getLift().getDistance()).sum();

        return (float) totalDistance;
    }

    public boolean isPassActive(Integer passId) {
        return passService.getPassById(passId).map(Pass::getActive).orElse(false);
    }
}
