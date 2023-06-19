package com.example.a10spring_boot_hibernate_library.services;

import com.example.a10spring_boot_hibernate_library.entities.Client;
import com.example.a10spring_boot_hibernate_library.entities.ClientOrder;
import com.example.a10spring_boot_hibernate_library.entities.OrderItem;
import com.example.a10spring_boot_hibernate_library.entities.Payment;
import com.example.a10spring_boot_hibernate_library.repository.ClientOrderRepository;
import com.example.a10spring_boot_hibernate_library.repository.ClientRepository;
import com.example.a10spring_boot_hibernate_library.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientOrderService {

    private ClientOrderRepository clientOrderRepository;

    private OrderItemRepository orderItemRepository;

    private PaymentService paymentService;
    private ClientRepository clientRepository;

    @Autowired //pas besoin de faire un new
    public ClientOrderService(ClientOrderRepository clientOrderRepository, OrderItemRepository orderItemRepository, PaymentService paymentService, ClientRepository clientRepository) {
        this.clientOrderRepository = clientOrderRepository;
        this.orderItemRepository = orderItemRepository;
        this.paymentService = paymentService;
        this.clientRepository = clientRepository;
    }

    public List<ClientOrder> findall() {
        return clientOrderRepository.findAll();
    }


    @Transactional
    public boolean deleteClientOrderById(int id) {
        Optional<ClientOrder> tempClientOrderQ = this.findClientOrderById(id);

        if (tempClientOrderQ.isPresent()) {
            ClientOrder tempClientOrder = tempClientOrderQ.get();
            Client client = tempClientOrder.getClientByClientId();
            //enlever de la liste du client
            if (client.getClientOrdersByClientId().contains(tempClientOrder)) {
                client.enleverClientOrder(tempClientOrder);
                clientRepository.save(client);
            }

            //effacer tous la liste de itemOrders du client
            List<OrderItem> liste = (List<OrderItem>) tempClientOrder.getOrderItemsByOrderId();
            if (!liste.isEmpty()) {
                for (int i=0;i<liste.size();i++){
                    orderItemRepository.deleteById(liste.get(i).getId());
                    tempClientOrder.enleverOrderItem(liste.get(i));
                }
                if (!liste.isEmpty()) {
                    orderItemRepository.deleteById(liste.get(0).getId());
                    tempClientOrder.enleverOrderItem(liste.get(0));
                }
                tempClientOrder.setOrderItemsByOrderId(null);
                clientOrderRepository.save(tempClientOrder);

            }
            //effacer le payment du client
            Payment payment = tempClientOrder.getPayment();
            if (payment != null) {
                paymentService.deletePaymentById(payment.getPaymentId());
                tempClientOrder.setPayment(null);
            }
            //mettre le client a null
            tempClientOrder.setClientByClientId(null);
            clientOrderRepository.save(tempClientOrder);
            clientOrderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<ClientOrder> findClientOrderById(int id) {
        return clientOrderRepository.findById(id);
    }

    public void majTotal(ClientOrder clientOrder) {
        clientOrderRepository.save(clientOrder);
    }
}
