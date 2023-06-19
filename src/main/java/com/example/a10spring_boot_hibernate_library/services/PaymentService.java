package com.example.a10spring_boot_hibernate_library.services;

import com.example.a10spring_boot_hibernate_library.entities.ClientOrder;
import com.example.a10spring_boot_hibernate_library.entities.Payment;
import com.example.a10spring_boot_hibernate_library.repository.ClientOrderRepository;
import com.example.a10spring_boot_hibernate_library.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    private ClientOrderRepository clientOrderRepository;

    @Autowired  //pas besoin de faire un new
    public PaymentService(PaymentRepository paymentRepository, ClientOrderRepository clientOrderRepository) {
        this.paymentRepository = paymentRepository;
        this.clientOrderRepository = clientOrderRepository;
    }

    public List<Payment> findall() {
        return paymentRepository.findAll();
    }

    public boolean deletePaymentById(int id) {
        Optional<Payment> tempPaymentO = this.findPaymentById(id);
        if (tempPaymentO.isPresent()) {
            Payment tempPayment = tempPaymentO.get();
            //get ClientOrder
            ClientOrder clientOrder = tempPayment.getClientOrder();

            if (clientOrder != null) {
                clientOrder.setPayment(null);
                clientOrderRepository.save(clientOrder);
            }
            tempPayment.setClientOrder(null);
            paymentRepository.save(tempPayment);
            paymentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Payment> findPaymentById(int id) {
        return paymentRepository.findById(id);
    }
}

