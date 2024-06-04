package org.skistation.services;

import org.skistation.models.*;
import org.skistation.repositories.LiftPassRepository;
import org.skistation.repositories.LiftTicketRepository;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.Optional;

/**
 * Represents a lift pass service class.
 * It provides methods to perform operations on the lift pass table in the database.
 */
@Service
public class LiftPassService
{
    /**
     * The lift pass repository to perform operations on the lift pass table in the database.
     */
    private final LiftPassRepository liftPassRepository;

    /**
     * The lift service to perform operations on the lift table in the database.
     */
    private final LiftService liftService;

    /**
     * The pass service to perform operations on the pass table in the database.
     */
    private final PassService passService;

    /**
     * Constructs a new lift pass service with the specified lift pass repository, lift service, and pass service.
     *
     * @param liftPassRepository the lift pass repository to perform operations on the lift pass table in the database
     * @param liftService        the lift service to perform operations on the lift table in the database
     * @param passService        the pass service to perform operations on the pass table in the database
     */
    public LiftPassService(LiftPassRepository liftPassRepository, LiftService liftService, PassService passService, LiftTicketRepository liftTicketRepository) {
        this.liftPassRepository = liftPassRepository;
        this.liftService = liftService;
        this.passService = passService;
    }

    /**
     * Adds a lift pass with the specified lift ID and pass ID.
     *
     * @param liftId the ID of the lift
     * @param passId the ID of the pass
     */
    public void addLiftPass(int liftId, int passId) {
        Lift lift = liftService.getLiftById(liftId).orElseGet(null);
        Pass pass = passService.getPassById(passId).orElseGet(null);
        if (lift == null || pass == null) {
            return;
        }
        addLiftPass(lift, pass);
    }

    /**
     * Adds a lift pass with the specified lift and pass objects.
     *
     * @param lift the lift
     * @param pass the pass
     */
    public void addLiftPass(Lift lift, Pass pass) {
        liftPassRepository.save(new LiftPass(lift, pass));
    }

    /**
     * Retrieves all lift passes from the database.
     *
     * @return a list of all lift passes
     */
    public List<LiftPass> getLiftPasses() {
        return liftPassRepository.findAll();
    }

    /**
     * Retrieves all lift passes by the specified lift ID from the database.
     *
     * @param liftId the ID of the lift
     * @return a list of all lift passes by the specified lift ID
     */
    public List<LiftPass> getLiftPassesByLiftId(int liftId) {
        return liftPassRepository.findByLiftId(liftId);
    }

    /**
     * Retrieves all lift passes by the specified pass ID from the database.
     *
     * @param passId the ID of the pass
     * @return a list of all lift passes by the specified pass ID
     */
    public List<LiftPass> getLiftPassesByPassId(int passId) {
        return liftPassRepository.findByPassId(passId);
    }

    /**
     * Retrieves the total track distance by the specified pass ID.
     *
     * @param passId the ID of the pass
     * @return the total track distance
     */
    public Float getTotalTrackDistance(Integer passId) {
        List<LiftPass> liftPasses = getLiftPassesByPassId(passId);

        double totalDistance = liftPasses.stream()
                .mapToDouble(pass -> pass.getLift().getDistance()).sum();

        return (float) totalDistance;
    }

    /**
     * Retrieves the pass time start by the specified pass ID.
     *
     * @param passId the ID of the pass
     * @return the pass time start
     */
    public LocalDateTime getPassTimeStart(Integer passId) {
        return this.passService.getPassById(passId).get().getTimeStart();
    }

    /**
     * Retrieves the pass end date and time by the specified pass ID.
     *
     * @param passId the ID of the pass
     * @return the pass end date and time
     */
    public LocalDateTime getPassTimeEnd(Integer passId) {
        return this.passService.getPassById(passId).get().getTimeEnd();
    }

    /**
     * Checks if the pass with the specified pass ID is active.
     *
     * @param passId the ID of the pass
     * @return true if the pass is active, false otherwise
     */
    public boolean isPassActive(Integer passId) {
        Optional<Pass> passOpt = passService.getPassById(passId);
        if (passOpt.isPresent()) {
            Pass pass = passOpt.get();
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime timeStart = pass.getTimeStart();
            LocalDateTime timeEnd = pass.getTimeEnd();
            if (now.isAfter(timeEnd)) {
                pass.setActive(false);
                passService.modifyPass(pass);
            }
            return now.isAfter(timeStart) && now.isBefore(timeEnd) && pass.isActive();
        }
        return false;
    }

    /**
     * Retrieves the pass descents number by the specified pass ID.
     *
     * @param passId the ID of the pass
     * @return the pass descents number
     */
    public Integer getPassDescentsNumber(int passId) {
        List<LiftPass> liftPasses = getLiftPassesByPassId(passId);
        return liftPasses.size();
    }
}