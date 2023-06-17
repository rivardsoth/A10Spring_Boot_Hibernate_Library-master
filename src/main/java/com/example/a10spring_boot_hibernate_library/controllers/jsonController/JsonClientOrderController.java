package com.example.a10spring_boot_hibernate_library.controllers.jsonController;

import com.example.a10spring_boot_hibernate_library.entities.ClientOrder;
import com.example.a10spring_boot_hibernate_library.services.ClientOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JsonClientOrderController {

    private ClientOrderService clientOrderService;

    @Autowired
    public JsonClientOrderController(ClientOrderService clientOrderService) {
        this.clientOrderService = clientOrderService;
    }

    @GetMapping("/jsonClientOrders") //http://localhost:8080/clientOrders
    public List<ClientOrder> getAllClientOrders() {
        return clientOrderService.findall();
    }

    @GetMapping("/jsonClientOrders/{id}")//http://localhost:8080/instructors/1
    public ClientOrder getClientOrderById(@PathVariable("id") int id) {
        //va chercher le clientOrder
        return clientOrderService.findClientOrderById(id);

    }
    @DeleteMapping("/jsonClientOrders/{id}")
    //PathVariable sert a extraire un parametre du URL
    public ResponseEntity<String> deleteClientOrderById(@PathVariable int id) {
        boolean deleted = clientOrderService.deleteClientOrderById(id);
        if (deleted) {
            String message = "OrderItem avec ID " + id + " supprimé.";
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "OrderItem avec ID " + id + " n'existe pas.";
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }
}
