package org.skistation.controllers;


import org.skistation.models.SkiSchedule;
import org.skistation.services.SkiScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/skiSchedules")
public class SkiScheduleController
{
    private final SkiScheduleService skiScheduleService;

    public SkiScheduleController(SkiScheduleService skiScheduleService) {
        this.skiScheduleService = skiScheduleService;
    }


    @GetMapping("")
    public List<SkiSchedule> getAllSkiSchedule() {
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

    @PostMapping("/addSkiSchedule")
    public String addSkiSchedule(@RequestBody SkiSchedule skiSchedule) {
        skiScheduleService.saveSkiSchedule(skiSchedule);

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
