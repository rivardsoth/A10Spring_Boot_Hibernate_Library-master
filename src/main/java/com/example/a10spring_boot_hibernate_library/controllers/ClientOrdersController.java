package com.example.a10spring_boot_hibernate_library.controllers;

import com.example.a10spring_boot_hibernate_library.entities.Client;
import com.example.a10spring_boot_hibernate_library.entities.ClientOrder;
import com.example.a10spring_boot_hibernate_library.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/clientorders")
public class ClientOrdersController {

    private final ClientService clientService;

    @Autowired
    public ClientOrdersController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{clientId}/orders")
    public String getClientOrders(@PathVariable Integer clientId, Model model) {
        Client client = clientService.getClientById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        List<ClientOrder> orders = (List<ClientOrder>) client.getClientOrdersByClientId();
        model.addAttribute("client", client);
        model.addAttribute("orders", orders);

        return "clientorders";
    }
}



