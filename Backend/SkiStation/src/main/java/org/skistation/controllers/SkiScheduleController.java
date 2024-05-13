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

@RestController
@CrossOrigin
@RequestMapping("/skiSchedules")
public class SkiScheduleController
{
    private final SkiScheduleService skiScheduleService;
    private final LiftService liftService;

    public SkiScheduleController(SkiScheduleService skiScheduleService, LiftService liftService) {
        this.skiScheduleService = skiScheduleService;
        this.liftService = liftService;
    }


    @GetMapping("/dto")
    public List<SkiScheduleDTO> getAllSkiScheduleDTO() {
        List<SkiSchedule> skiSchedules = skiScheduleService.getAllSkiSchedules();
        return mapSkiSchedulesToDTOs(skiSchedules);
    }

    @GetMapping("")
    public List<SkiSchedule> getAllSkiSchedule() {
        return skiScheduleService.getAllSkiSchedules();
    }

    @GetMapping("/name")
    public List<SkiSchedule> getAllSkiScheduleWithName() {
        return skiScheduleService.getAllSkiSchedules();
    }

    @GetMapping("{id}")
    public SkiSchedule getSkiScheduleById(@PathVariable("id") Integer id) {
        return skiScheduleService.getSkiScheduleById(id).get();
    }

    @GetMapping("/{liftId}")
    public List<SkiSchedule> getSkiSchedulesByLiftId(@PathVariable("liftId") Integer liftId) {
        return skiScheduleService.getSkiSchedulesByLiftId(liftId);
    }

    @GetMapping("/{liftId}/details")
    public List<SkiScheduleDTO> getSkiSchedulesByLiftIdWithLiftDetails(@PathVariable("liftId") Integer liftId) {
        List<SkiSchedule> skiSchedules = skiScheduleService.getSkiSchedulesByLiftId(liftId);
        return mapSkiSchedulesToDTOs(skiSchedules);
    }

    private List<SkiScheduleDTO> mapSkiSchedulesToDTOs(List<SkiSchedule> skiSchedules) {
        return skiSchedules.stream()
                .map(this::mapSkiScheduleDTO)
                .collect(Collectors.toList());
    }

    private SkiScheduleDTO mapSkiScheduleDTO(SkiSchedule skiSchedule) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        String openTime = skiSchedule.getOpen().format(formatter);
        String closeTime = skiSchedule.getClose().format(formatter);

        return new SkiScheduleDTO(
                openTime,
                closeTime,
                skiSchedule.getLift().getId(),
                skiSchedule.getLift().getName(),
                skiSchedule.getLift().getActive(),
                skiSchedule.getLift().getDistance()
        );
    }

    @PostMapping("/addSkiSchedule")
    public String addSkiSchedule(@RequestBody SkiSchedule skiSchedule) {
        skiScheduleService.saveSkiSchedule(skiSchedule);

        return "redirect:/skiSchedules";
    }

    @PostMapping("/addSkiScheduleRequest")
    public String addSkiScheduleRequest(@RequestBody SkiScheduleRequest skiScheduleRequest){
        Lift liftToAdd = liftService.getLiftById(skiScheduleRequest.getLiftId()).get();
        SkiSchedule skiScheduleToAdd = new SkiSchedule(skiScheduleRequest.getOpen(),skiScheduleRequest.getClose(),liftToAdd);
        skiScheduleService.saveSkiSchedule(skiScheduleToAdd);

        return "redirect:/skiSchedules";
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateSkiSchedule(@RequestBody SkiSchedule toUpdate, @PathVariable("id") Integer id) {
        if (skiScheduleService.getSkiScheduleById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        toUpdate.setId(id);
        skiScheduleService.saveSkiSchedule(toUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public String deleteSkiSchedule(@PathVariable("id") Integer id) {
        skiScheduleService.deleteSkiSchedule(id);
        return "redirect:/skiSchedules";
    }

}
