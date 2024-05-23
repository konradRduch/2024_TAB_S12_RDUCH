package org.skistation.controllers;

import org.skistation.models.Client;
import org.skistation.models.Pass;
import org.skistation.services.PassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/passes")
public class PassController
{
    private final PassService passService;

    public PassController(PassService passService) {
        this.passService = passService;
    }


    @GetMapping("")
    public List<Pass> getAllPasss() {
        return passService.getAllPass();
    }

    @GetMapping("{id}")
    public Pass getPassById(@PathVariable("id") Integer id) {
        return passService.getPassById(id).get();
    }

    @PostMapping("/addPass")
    public String addPass(@RequestBody Pass ticket) {
        passService.savePass(ticket);

        return "redirect:/tickets";
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateTask(@RequestBody Pass toUpdate, @PathVariable("id") Integer id) {
        if (passService.getPassById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        toUpdate.setId(id);
        passService.savePass(toUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public String deletePass(@PathVariable("id") Integer id) {
        passService.deletePass(id);
        return "redirect:/tickets";
    }

    @PostMapping("/suspend/{id}")
    public ResponseEntity<Pass> suspendPass(@RequestBody Client clientData, @PathVariable("id") Integer id) {
        Optional<Pass> pass = passService.suspendPass(id, clientData.getEmail(), clientData.getPhone());
        return pass.isPresent() ? ResponseEntity.ok(pass.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping("/resume/{id}")
    public ResponseEntity<Pass> resumePass(@RequestBody Client clientData, @PathVariable("id") Integer id) {
        Optional<Pass> pass = passService.resumePass(id, clientData.getEmail(), clientData.getPhone());
        return pass.isPresent() ? ResponseEntity.ok(pass.get()) : ResponseEntity.notFound().build();
    }

}