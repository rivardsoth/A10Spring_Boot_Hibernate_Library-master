package com.example.a10spring_boot_hibernate_library.configuration;

import com.example.a10spring_boot_hibernate_library.entities.Client;
import com.example.a10spring_boot_hibernate_library.entities.ClientOrder;
import com.example.a10spring_boot_hibernate_library.entities.OrderItem;
import com.example.a10spring_boot_hibernate_library.entities.Payment;
import com.example.a10spring_boot_hibernate_library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component //tiens compte de cela
public class Tp2Config implements CommandLineRunner {

    private ClientRepository clientRepository;
    private LibraryRepository libraryRepository;
    private ClientOrderRepository clientOrderRepository;
    private OrderItemRepository orderItemRepository;
    private PaymentRepository paymentRepository;


    @Autowired
    public Tp2Config(ClientRepository clientRepository, LibraryRepository libraryRepository, ClientOrderRepository clientOrderRepository, OrderItemRepository orderItemRepository, PaymentRepository paymentRepositor) {
        this.clientRepository = clientRepository;
        this.libraryRepository = libraryRepository;
        this.clientOrderRepository = clientOrderRepository;
        this.orderItemRepository = orderItemRepository;
        this.paymentRepository = paymentRepositor;

    }

    @Override
    public void run(String... args) throws Exception {

        initialiserAvecDelete(clientOrderRepository, orderItemRepository, paymentRepository);
        createDonneesPourBd(clientRepository, libraryRepository, clientOrderRepository, orderItemRepository, paymentRepository);
    }

    private void createDonneesPourBd(ClientRepository clientRepository, LibraryRepository libraryRepository, ClientOrderRepository clientOrderRepository, OrderItemRepository orderItemRepository, PaymentRepository paymentRepository) {

        //Insertion des OrderItem de 2 clients
        //orderItems du client1
        OrderItem orderItem1 = new OrderItem(3, libraryRepository.findById(9780123745149L).get());
        OrderItem orderItem2 = new OrderItem(5, libraryRepository.findById(9780131471399L).get());
        OrderItem orderItem3 = new OrderItem(1, libraryRepository.findById(9780133976892L).get());
        //orderItems du client2
        OrderItem orderItem4 = new OrderItem(1, libraryRepository.findById(9780134757599L).get());
        OrderItem orderItem5 = new OrderItem(1, libraryRepository.findById(9780123745149L).get());

        //Insertion des ClientOrder de 2 clients
        //ClientOrder du client1
        ClientOrder clientOrder1 = new ClientOrder(Date.valueOf("2023-06-11"));
        clientOrder1.ajouterOrderItem(orderItem1);
        clientOrder1.ajouterOrderItem(orderItem2);
        clientOrder1.ajouterOrderItem(orderItem3);
        //ClientOrder du client2
        ClientOrder clientOrder2 = new ClientOrder(Date.valueOf("2023-06-14"));
        clientOrder2.ajouterOrderItem(orderItem4);
        clientOrder2.ajouterOrderItem(orderItem5);

        //Insertion des payment de 2 client
        //Payment du client 1 et ajouter au clientOrder1
        Payment payment1 = new Payment("numeroCarte", Date.valueOf("2023-07-12"));
        clientOrder1.setPayment(payment1);
        //Payment du client 2 et ajouter au clientOrder2
        Payment payment2 = new Payment("mastercard", Date.valueOf("2028-12-15"));
        clientOrder2.setPayment(payment2);

        //Sauvegarde les orderItems et les cientOrders dans la Bd
        //sauvegarde des orderItem
        orderItemRepository.save(orderItem1);
        orderItemRepository.save(orderItem2);
        orderItemRepository.save(orderItem3);
        orderItemRepository.save(orderItem4);
        orderItemRepository.save(orderItem5);

        //sauvegarde des payments
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        //sauvegarde des clientOrder
        clientOrderRepository.save(clientOrder1);
        clientOrderRepository.save(clientOrder2);

        //Ajouter les clientOrder au client
        Client client1 = clientRepository.findById(1).get();
        Client client2 = clientRepository.findById(5).get();
        client1.ajouterClientOrder(clientOrder1);
        client2.ajouterClientOrder(clientOrder2);

        //sauvegarde des clients
        clientRepository.save(client1);
        clientRepository.save(client2);


    }

    private void initialiserAvecDelete(ClientOrderRepository clientOrderRepository, OrderItemRepository orderItemRepository, PaymentRepository paymentRepository) {

       /* //effacer tous les orderItems existant
        if (!orderItemRepository.findAll().isEmpty()) {*/
            orderItemRepository.deleteAllInBatch();

        /*}
        //effacer tous les clientOrders existant
        if (!clientOrderRepository.findAll().isEmpty()) {*/
            clientOrderRepository.deleteAllInBatch();
        paymentRepository.deleteAllInBatch();
        /*}*/

    }

}
