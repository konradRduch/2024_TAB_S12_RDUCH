package org.skistation.controllers;

import java.util.List;

import org.skistation.models.Client;
import org.skistation.services.ClientService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing clients.
 * It is responsible for handling requests related to clients.
 */
@RestController
@RequestMapping("/clients")
public class ClientController
{
    /**
     * Service for managing clients.
     */
    private final ClientService clientService;

    /**
     * Constructs a new ClientController with the specified client service.
     *
     * @param clientService the service for managing clients.
     */
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Gets all clients.
     *
     * @return a list of all clients.
     */
    @GetMapping("")
    public List<Client> getClients() {
        return clientService.getAllClients();
    }

    /**
     * Finds a client by ID.
     *
     * @param id the ID of the client.
     * @return the client with the specified ID.
     */
    @GetMapping("/{id}")
    public Client findClientById(@PathVariable("id") Integer id) {
        return clientService.getClientById(id).get();
    }

    /**
     * Finds a client by email.
     *
     * @param email the email of the client.
     * @return the client with the specified email.
     */
    @GetMapping("/{email}")
    public Client findClientByEmail(@PathVariable("email") String email) {
        return clientService.getClientsByEmailAddress(email);
    }

    /**
     * Finds a client by phone number.
     *
     * @param phoneNumber the phone number of the client.
     * @return the client with the specified phone number.
     */
    @GetMapping("/{phone}")
    public Client findClientByPhoneNumber(@PathVariable("phone") Integer phoneNumber) {
        return clientService.getClientsByPhoneNumber(phoneNumber);
    }

    /**
     * Adds a new client.
     *
     * @param client the client to add.
     * @return a redirect to the clients page.
     */
    @PostMapping("/addClient")
    public String addClient(@RequestBody Client client) {
        clientService.saveClient(client);
        return "redirect:/clients";
    }

    /**
     * Shows the edit form for a client.
     *
     * @param clientId the ID of the client to edit.
     * @param model    the model to add attributes to.
     * @return the name of the view for the edit form.
     */
    @GetMapping("/editClient/{clientId}")
    public String showEditForm(@PathVariable Integer clientId, Model model) {

        Client client = clientService.getClientById(clientId).get();
        model.addAttribute("client", client);

        return "editClient"; // Nazwa widoku dla formularza edycji
    }

    /**
     * Saves an edited client.
     *
     * @param editedClient the edited client.
     * @param result       the result of the binding.
     * @param model        the model to add attributes to.
     * @return a redirect to the clients page.
     */
    @PostMapping("/editClient/save")
    public String saveEditedClient(@ModelAttribute("client") Client editedClient, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("client", editedClient);
            return "editClient";
        }
        clientService.updateClient(editedClient);

        return "redirect:/clients";
    }

    /**
     * Deletes a client.
     *
     * @param id    the ID of the client to delete.
     * @param model the model to add attributes to.
     * @return a redirect to the clients page.
     */
    @DeleteMapping("/deleteClient/{id}")
    public String deleteClient(@PathVariable("id") Integer id, Model model) {
        clientService.deleteClient(id);
        return "redirect:/clients";
    }
}