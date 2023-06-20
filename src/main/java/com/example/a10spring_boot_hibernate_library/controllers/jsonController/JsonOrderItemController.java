package com.example.a10spring_boot_hibernate_library.controllers.jsonController;

import com.example.a10spring_boot_hibernate_library.entities.Client;
import com.example.a10spring_boot_hibernate_library.entities.OrderItem;
import com.example.a10spring_boot_hibernate_library.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JsonOrderItemController {

    private OrderItemService orderItemService;

    @Autowired
    public JsonOrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/jsonOrderItems") //http://localhost:8080/orderItems
    public List<OrderItem> getAllOrderItem() {
        return orderItemService.findall();
    }

    @GetMapping("/jsonOrderItems/{id}")//http://localhost:8080/jsonorderItems/1
    public ResponseEntity<?> getOrderItemById(@PathVariable("id") int id) {
        Optional<OrderItem> optionalOrderItem = orderItemService.findOrderItemById(id);
        if (optionalOrderItem.isPresent()) {
            OrderItem orderItem = optionalOrderItem.get();
            return ResponseEntity.ok(orderItem);
        } else {
            String errorMessage = "OrderItem with ID " + id + " does not exist.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @DeleteMapping("/jsonOrderItems/{id}")
    //PathVariable sert a extraire un parametre du URL
    public ResponseEntity<String> deleteOrderItemById(@PathVariable int id) {
        boolean deleted = orderItemService.deleteOrderItemById(id);
        if (deleted) {
            String message = "OrderItem avec ID " + id + " supprimé.";
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "OrderItem avec ID " + id + " n'existe pas.";
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }

    //modifier un orderItem avec une quantite de plus
    @PostMapping("/jsonOrderItemModifier/{id}")
    public ResponseEntity<String> mofifierItemOrderQuantite(@PathVariable int id) {
        boolean maj = orderItemService.updateOrderItem(id);
        if(maj) {
            String message = "L'id du OrderItem " + id + " a été mise à jour avec une quantité de plus!";
            return ResponseEntity.ok(message);
        }
        else {
            String errorMessage = "L'id du OrderItem " + id + " n'existe pas!";
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }
}
