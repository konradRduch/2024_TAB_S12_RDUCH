package org.skistation.controllers;

import java.util.List;
import org.skistation.models.Lift;
import org.skistation.services.LiftService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lifts")
public class LiftController {


    private final LiftService liftService;


    public LiftController(LiftService liftService) {
        this.liftService = liftService;
    }

    @GetMapping("/{id}")
    public Lift getLiftById(@PathVariable Integer id){
        return liftService.getLiftById(id).get();
    }

    @GetMapping("")
    public List<Lift> getAllLifts(){
        return liftService.getAllLifts();
    }

    @PostMapping("/addLift")
    public String addLift(@RequestBody Lift lift){
        Lift lift1 = new Lift("Name1",true,123.3f);
        liftService.saveLift(lift);
        return "redirect:/lifts";
    }

    @PutMapping("/updateLift/{id}")
    public ResponseEntity<?> updateLift(@RequestBody Lift updateLift, @PathVariable("id") Integer id){
        if (liftService.getLiftById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        updateLift.setId(id);
        liftService.saveLift(updateLift);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteLift/{id}")
    public String deleteLift(@PathVariable("id") Integer id){
        liftService.deleteLift(id);
        return "redirect:/lifts";
    }
}
