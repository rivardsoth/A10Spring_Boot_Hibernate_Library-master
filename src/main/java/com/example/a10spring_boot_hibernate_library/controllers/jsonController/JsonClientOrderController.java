package com.example.a10spring_boot_hibernate_library.controllers.jsonController;

import com.example.a10spring_boot_hibernate_library.entities.ClientOrder;
import com.example.a10spring_boot_hibernate_library.services.ClientOrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/jsonClientOrders/{id}")//http://localhost:8080/jsonClientsOrders/1
    public ResponseEntity<?> getClientOrderById(@PathVariable("id") int id) {
        Optional<ClientOrder> optionalClientOrder = clientOrderService.findClientOrderById(id);
        if (optionalClientOrder.isPresent()) {
            ClientOrder clientOrder = optionalClientOrder.get();
            return ResponseEntity.ok(clientOrder);
        } else {
            String errorMessage = "ClientOrder with ID " + id + " does not exist.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @DeleteMapping("/jsonClientOrders/{id}")
    //PathVariable sert a extraire un parametre du URL
    public ResponseEntity<String> deleteClientOrderById(@PathVariable int id) {
        boolean deleted = clientOrderService.deleteClientOrderById(id);
        if (deleted) {
            String message = "ClientOrder avec ID " + id + " supprimé.";
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "ClientOrder avec ID " + id + " n'existe pas.";
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }
}
