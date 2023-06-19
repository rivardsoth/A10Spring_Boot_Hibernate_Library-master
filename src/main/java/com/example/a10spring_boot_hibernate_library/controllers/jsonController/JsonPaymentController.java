package com.example.a10spring_boot_hibernate_library.controllers.jsonController;

import com.example.a10spring_boot_hibernate_library.entities.OrderItem;
import com.example.a10spring_boot_hibernate_library.entities.Payment;
import com.example.a10spring_boot_hibernate_library.services.PaymentService;
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
public class JsonPaymentController {

    private PaymentService paymentService;

    @Autowired
    public JsonPaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/jsonPayment") //http://localhost:8080/jsonPayment
    public List<Payment> getAllOrderItem() {
        return paymentService.findall();
    }

    @GetMapping("/jsonPayment/{id}")//http://localhost:8080/jsonPayment/1
    public ResponseEntity<?> getClientById(@PathVariable("id") int id) {
        Optional<Payment> optionalPayment = paymentService.findPaymentById(id);
        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            return ResponseEntity.ok(payment);
        } else {
            String errorMessage = "Payment with ID " + id + " does not exist.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @DeleteMapping("/jsonPayment/{id}")
    //PathVariable sert a extraire un parametre du URL
    public ResponseEntity<String> deletePaymentById(@PathVariable int id) {
        boolean deleted = paymentService.deletePaymentById(id);
        if (deleted) {
            String message = "Payment avec ID " + id + " supprimé.";
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "Payment avec ID " + id + " n'existe pas.";
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }
}
