package com.example.a10spring_boot_hibernate_library.controllers;

import com.example.a10spring_boot_hibernate_library.entities.Client;
import com.example.a10spring_boot_hibernate_library.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientEditController {

    private final ClientService clientService;

    @Autowired
    public ClientEditController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/editclient")
    public String editClient(@RequestParam("id") Integer clientId, Model model) {
        try {
            Client client = clientService.getClientById(clientId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid client ID: " + clientId));

            model.addAttribute("client", client);
            return "update-client";
        } catch (IllegalArgumentException e) {
            // Redirect to the "/error" mapping with the error message
            return "redirect:/invalid?message=" + e.getMessage();
        }
    }

    @PostMapping("/updateclient")
    public String updateClient(Client client) {
        clientService.updateClient(client);
        return "redirect:/singleclient/" + client.getClientId();
    }
}
