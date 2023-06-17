package com.example.a10spring_boot_hibernate_library.controllers;

import com.example.a10spring_boot_hibernate_library.entities.Client;
import com.example.a10spring_boot_hibernate_library.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ClientCreateController {

    private final ClientService clientService;

    @Autowired
    public ClientCreateController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/createclient")
    public String createClient(Client client, RedirectAttributes redirectAttributes) {
        Client createdClient = clientService.createClient(client);
        redirectAttributes.addAttribute("clientId", createdClient.getClientId());
        return "redirect:/singleclient/{clientId}";
    }

    /*
    @GetMapping("/clients/{clientId}")
    public String getClientById(@PathVariable("clientId") Integer clientId, Model model) {
        Client client = clientService.getClientById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client Id: " + clientId));

        model.addAttribute("client", client);
        return "client-details";
    }*/
}
