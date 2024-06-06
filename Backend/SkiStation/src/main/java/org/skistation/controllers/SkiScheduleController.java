package org.skistation.controllers;

import org.skistation.models.DTO.SkiScheduleDTO;
import org.skistation.models.DTO.SkiScheduleRequest;
import org.skistation.models.Lift;
import org.skistation.models.SkiSchedule;
import org.skistation.services.LiftService;
import org.skistation.services.SkiScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

/**
 * Controller for managing ski schedules.
 * It is responsible for handling requests related to ski schedules.
 */
@RestController
@CrossOrigin
@RequestMapping("/skiSchedules")
public class SkiScheduleController
{
    /**
     * Service for managing ski schedules.
     */
    private final SkiScheduleService skiScheduleService;

    /**
     * Service for managing lifts.
     */
    private final LiftService liftService;

    /**
     * Constructs a new SkiScheduleController with the specified ski schedule service and lift service.
     *
     * @param skiScheduleService the service for managing ski schedules.
     * @param liftService        the service for managing lifts.
     */
    public SkiScheduleController(SkiScheduleService skiScheduleService, LiftService liftService) {
        this.skiScheduleService = skiScheduleService;
        this.liftService = liftService;
    }

    /**
     * Gets all ski schedule DTOs.
     *
     * @return a list of all ski schedule DTOs.
     */
    @GetMapping("/dto")
    public List<SkiScheduleDTO> getAllSkiScheduleDTO() {
        List<SkiSchedule> skiSchedules = skiScheduleService.getAllSkiSchedules();
        return mapSkiSchedulesToDTOs(skiSchedules);
    }

    /**
     * Gets all ski schedules.
     *
     * @return a list of all ski schedules.
     */
    @GetMapping("")
    public List<SkiSchedule> getAllSkiSchedule() {
        return skiScheduleService.getAllSkiSchedules();
    }

    /**
     * Gets all ski schedules with name.
     *
     * @return a list of all ski schedules with name.
     */
    @GetMapping("/name")
    public List<SkiSchedule> getAllSkiScheduleWithName() {
        return skiScheduleService.getAllSkiSchedules();
    }

    /**
     * Gets a ski schedule by ID.
     *
     * @param id the ID of the ski schedule.
     * @return the ski schedule with the specified ID.
     */
    @GetMapping("{id}")
    public SkiSchedule getSkiScheduleById(@PathVariable("id") Integer id) {
        return skiScheduleService.getSkiScheduleById(id).get();
    }

    /**
     * Gets ski schedules by lift ID.
     *
     * @param liftId the lift ID.
     * @return a list of ski schedules with the specified lift ID.
     */
    @GetMapping("/{liftId}")
    public List<SkiSchedule> getSkiSchedulesByLiftId(@PathVariable("liftId") Integer liftId) {
        return skiScheduleService.getSkiSchedulesByLiftId(liftId);
    }

    /**
     * Gets ski schedules by lift ID with lift details.
     *
     * @param liftId the lift ID.
     * @return a list of ski schedule DTOs with the specified lift ID and lift details.
     */
    @GetMapping("/{liftId}/details")
    public List<SkiScheduleDTO> getSkiSchedulesByLiftIdWithLiftDetails(@PathVariable("liftId") Integer liftId) {
        List<SkiSchedule> skiSchedules = skiScheduleService.getSkiSchedulesByLiftId(liftId);
        return mapSkiSchedulesToDTOs(skiSchedules);
    }

    /**
     * Adds a new ski schedule.
     *
     * @param skiSchedule the ski schedule to add.
     * @return a redirect to the ski schedules page.
     */
    @PostMapping("/addSkiSchedule")
    public String addSkiSchedule(@RequestBody SkiSchedule skiSchedule) {
        skiScheduleService.saveSkiSchedule(skiSchedule);

        return "redirect:/skiSchedules";
    }

    /**
     * Adds a new ski schedule request.
     *
     * @param skiScheduleRequest the ski schedule request to add.
     * @return a redirect to the ski schedules page.
     */
    @PostMapping("/addSkiScheduleRequest")
    public String addSkiScheduleRequest(@RequestBody SkiScheduleRequest skiScheduleRequest) {
        Lift liftToAdd = liftService.getLiftById(skiScheduleRequest.getLiftId()).get();
        SkiSchedule skiScheduleToAdd = new SkiSchedule(skiScheduleRequest.getOpen(), skiScheduleRequest.getClose(), liftToAdd);
        skiScheduleService.saveSkiSchedule(skiScheduleToAdd);

        return "redirect:/skiSchedules";
    }

    /**
     * Updates a ski schedule.
     *
     * @param toUpdate the ski schedule to update.
     * @param id       the ID of the ski schedule to update.
     * @return a ResponseEntity containing the updated ski schedule, or a not found status if no such ski schedule exists.
     */
    @PutMapping("/{id}")
    ResponseEntity<?> updateSkiSchedule(@RequestBody SkiSchedule toUpdate, @PathVariable("id") Integer id) {
        if (skiScheduleService.getSkiScheduleById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        toUpdate.setId(id);
        skiScheduleService.saveSkiSchedule(toUpdate);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes a ski schedule.
     *
     * @param id the ID of the ski schedule to delete.
     * @return a redirect to the ski schedules page.
     */
    @DeleteMapping("/{id}")
    public String deleteSkiSchedule(@PathVariable("id") Integer id) {
        skiScheduleService.deleteSkiSchedule(id);
        return "redirect:/skiSchedules";
    }

    /**
     * Maps ski schedules to ski schedule DTOs.
     *
     * @param skiSchedules the ski schedules to map.
     * @return a list of ski schedule DTOs.
     */
    private List<SkiScheduleDTO> mapSkiSchedulesToDTOs(List<SkiSchedule> skiSchedules) {
        return skiSchedules.stream()
                .map(this::mapSkiScheduleDTO)
                .collect(Collectors.toList());
    }

    /**
     * Maps a ski schedule to a ski schedule DTO.
     *
     * @param skiSchedule the ski schedule to map.
     * @return the ski schedule DTO.
     */
    private SkiScheduleDTO mapSkiScheduleDTO(SkiSchedule skiSchedule) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        String openTime = skiSchedule.getOpen().format(formatter);
        String closeTime = skiSchedule.getClose().format(formatter);

        return new SkiScheduleDTO(
                skiSchedule.getId(),
                openTime,
                closeTime,
                skiSchedule.getLift().getId(),
                skiSchedule.getLift().getName(),
                skiSchedule.getLift().getActive(),
                skiSchedule.getLift().getDistance()
        );
    }
}