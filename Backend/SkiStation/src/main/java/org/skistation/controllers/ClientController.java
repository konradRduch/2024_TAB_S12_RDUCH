package org.skistation.controllers;

import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.skistation.models.Client;
import org.skistation.services.ClientService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("")
    public List<Client> getClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client findClientById(@PathVariable("id") Integer id){
        return clientService.getClientById(id).get();
    }

    @GetMapping("/{email}")
    public Client findClientByEmail(@PathVariable("email") String email){
        return clientService.getClientsByEmailAddress(email);
    }

    @GetMapping("/{phone}")
    public Client findClientByPhoneNumber(@PathVariable("phone") Integer phoneNumber){
        return clientService.getClientsByPhoneNumber(phoneNumber);
    }


    @PostMapping("/addClient")
    public String addClient(@RequestBody Client client){
        clientService.saveClient(client);
        return "redirect:/clients";
    }




    @GetMapping("/editClient/{clientId}")
    public String showEditForm(@PathVariable Integer clientId, Model model) {

        Client client = clientService.getClientById(clientId).get();
        model.addAttribute("client", client);

        return "editClient"; // Nazwa widoku dla formularza edycji
    }

    @PostMapping("/editClient/save")
    public String saveEditedClient( @ModelAttribute("client") Client editedClient, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("client", editedClient);
            return "editClient";
        }
        clientService.updateClient(editedClient);

        return "redirect:/clients";
    }

    @DeleteMapping("/deleteClient/{id}")
    public String deleteClient(@PathVariable("id")Integer id,Model model){
        clientService.deleteClient(id);
        return "redirect:/clients";
    }
}
