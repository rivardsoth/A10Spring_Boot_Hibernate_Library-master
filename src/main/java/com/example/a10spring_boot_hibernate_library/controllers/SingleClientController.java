package com.example.a10spring_boot_hibernate_library.controllers;


import com.example.a10spring_boot_hibernate_library.entities.Client;
import com.example.a10spring_boot_hibernate_library.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/singleclient")
public class SingleClientController {
    private final ClientService clientService;

    @Autowired
    public SingleClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public String getClientDetailsById(@PathVariable("id") Integer id, Model model) {
        Client client = clientService.getClientById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client ID: " + id));

        model.addAttribute("singleclient", client);
        return "singleclient";
    }
}
