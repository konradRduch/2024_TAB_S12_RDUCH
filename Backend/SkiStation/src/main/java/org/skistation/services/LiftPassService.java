package org.skistation.services;

import org.skistation.models.*;
import org.skistation.repositories.LiftPassRepository;
import org.skistation.repositories.LiftTicketRepository;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.Optional;

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
        List<LiftPass> liftPasses = getLiftPassesByPassId(passId);

        double totalDistance = liftPasses.stream()
                .mapToDouble(pass -> pass.getLift().getDistance()).sum();

        return (float) totalDistance;
    }



    public LocalDateTime getPassTimeStart(Integer passId){
        return  this.passService.getPassById(passId).get().getTimeStart();
    }

    public LocalDateTime getPassTimeEnd(Integer passId){
      return  this.passService.getPassById(passId).get().getTimeEnd();
    }


    public boolean isPassActive(Integer passId) {
        Optional<Pass> passOpt = passService.getPassById(passId);
        if (passOpt.isPresent()) {
            Pass pass = passOpt.get();
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime timeStart = pass.getTimeStart();
            LocalDateTime timeEnd = pass.getTimeEnd();
            return now.isAfter(timeStart) && now.isBefore(timeEnd) && pass.isActive();
        }
        return false;
    }

    public Integer getPassDescentsNumber(int passId) {
        List<LiftPass> liftPasses = getLiftPassesByPassId(passId);
        return liftPasses.size();
    }
}
