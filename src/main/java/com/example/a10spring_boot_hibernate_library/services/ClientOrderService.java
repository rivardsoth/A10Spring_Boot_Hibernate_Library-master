package com.example.a10spring_boot_hibernate_library.services;

import com.example.a10spring_boot_hibernate_library.entities.Client;
import com.example.a10spring_boot_hibernate_library.entities.ClientOrder;
import com.example.a10spring_boot_hibernate_library.entities.OrderItem;
import com.example.a10spring_boot_hibernate_library.repository.ClientOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientOrderService {

    private ClientOrderRepository clientOrderRepository;

    private OrderItemService orderItemService;
    /*@Autowired
    private PaymentService paymentService;*/

    @Autowired //pas besoin de faire un new
    public ClientOrderService(ClientOrderRepository clientOrderRepository, OrderItemService orderItemService) {
        this.clientOrderRepository = clientOrderRepository;
        this.orderItemService = orderItemService;
    }

    public List<ClientOrder> findall() {
        return clientOrderRepository.findAll();
    }


    @Transactional
    public boolean deleteClientOrderById(int id) {
        Optional<ClientOrder> tempClientOrderQ = this.findClientOrderById(id);

        if (tempClientOrderQ.isPresent()) {
            ClientOrder tempClientOrder = tempClientOrderQ.get();
            //effacer tous la liste de itemOrders du client
            List<OrderItem> liste = (List<OrderItem>) tempClientOrder.getOrderItemsByOrderId();
            if (liste != null) {
                for (OrderItem orderItem: liste){
                    orderItemService.deleteOrderItemById(orderItem.getId());
                }
                tempClientOrder.setOrderItemsByOrderId(null);
                clientOrderRepository.save(tempClientOrder);
            }
            //effacer le payment du client
            /*Payment payment = tempClientOrder.getPayment();
            if (payment != null) {
                paymentService.deletePaymentById(payment.getPaymentId());
                tempClientOrder.setPayment(null);
            }*/
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
}
