package com.example.a10spring_boot_hibernate_library.controllers;

import com.example.a10spring_boot_hibernate_library.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.a10spring_boot_hibernate_library.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


import org.springframework.ui.Model;



@Controller
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping
    public String getAllClients(Model model) {
        List<Client> clients = clientService.getAllClients();
        List<Client> firstClients = clients.subList(0, Math.min(clients.size(), 100));
        model.addAttribute("clients", firstClients);
        return "clients";
    }
}
