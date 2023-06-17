package com.example.a10spring_boot_hibernate_library.controllers.jsonController;

import com.example.a10spring_boot_hibernate_library.entities.OrderItem;
import com.example.a10spring_boot_hibernate_library.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JsonOrderItemController {

    private OrderItemService orderItemService;

    @Autowired
    public JsonOrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/jsonorderItems") //http://localhost:8080/orderItems
    public List<OrderItem> getAllOrderItem() {
        return orderItemService.findall();
    }

    @GetMapping("/jsonorderItems/{id}")//http://localhost:8080/instructors/1
    public OrderItem getOrderItemById(@PathVariable("id") int id) {
        //va chercher le orderItem
        return orderItemService.findOrderItemById(id);

    }
    @DeleteMapping("/jsonorderItems/{id}")
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
}
