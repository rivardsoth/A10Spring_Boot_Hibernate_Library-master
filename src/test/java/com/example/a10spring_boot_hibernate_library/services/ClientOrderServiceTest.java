package com.example.a10spring_boot_hibernate_library.services;

import com.example.a10spring_boot_hibernate_library.entities.Client;
import com.example.a10spring_boot_hibernate_library.entities.ClientOrder;
import com.example.a10spring_boot_hibernate_library.entities.OrderItem;
import com.example.a10spring_boot_hibernate_library.entities.Payment;
import com.example.a10spring_boot_hibernate_library.repository.ClientOrderRepository;
import com.example.a10spring_boot_hibernate_library.repository.ClientRepository;
import com.example.a10spring_boot_hibernate_library.repository.OrderItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClientOrderServiceTest {

    @Mock
    private ClientOrderRepository clientOrderRepository;
    @Mock
    private PaymentService paymentService;
    @Mock
    private OrderItemRepository orderItemRepository;
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientOrderService clientOrderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        clientOrderService = new ClientOrderService(clientOrderRepository, orderItemRepository, paymentService, clientRepository);
    }

    @Test
    public void testFindAll() {
        List<ClientOrder> expectedClientOrders = new ArrayList<>();
        // Add sample client orders to the expected list

        when(clientOrderRepository.findAll()).thenReturn(expectedClientOrders);

        List<ClientOrder> actualClientOrders = clientOrderService.findall();

        assertEquals(expectedClientOrders, actualClientOrders);
        verify(clientOrderRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteClientOrderByIdClientOrderExists() {
        int id = 1;

        // Create a sample client order
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setOrderId(id);

        // Create a sample client
        Client client = new Client();
        client.ajouterClientOrder(clientOrder);

        // Create a sample order item
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(0);
        clientOrder.ajouterOrderItem(orderItem);

        // Create a sample payment
        Payment payment = new Payment();
        clientOrder.setPayment(payment);

        when(clientOrderRepository.findById(id)).thenReturn(Optional.of(clientOrder));
        when(clientRepository.save(client)).thenReturn(client);

        boolean isDeleted = clientOrderService.deleteClientOrderById(id);

        assertTrue(isDeleted);
        verify(clientOrderRepository, times(1)).findById(id);
        verify(orderItemRepository, times(1)).deleteById(anyInt());
        verify(clientRepository, times(1)).save(client);
        verify(paymentService, times(1)).deletePaymentById(anyInt());
        verify(clientOrderRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteClientOrderByIdClientOrderDoesNotExist() {
        int id = 1;

        when(clientOrderRepository.findById(id)).thenReturn(Optional.empty());

        boolean isDeleted = clientOrderService.deleteClientOrderById(id);

        assertFalse(isDeleted);
        verify(clientOrderRepository, times(1)).findById(id);
        verify(orderItemRepository, never()).deleteById(anyInt());
        verify(clientRepository, never()).save(any(Client.class));
        verify(paymentService, never()).deletePaymentById(anyInt());
        verify(clientOrderRepository, never()).deleteById(anyInt());
    }

    @Test
    public void testFindClientOrderByIdClientOrderExists() {
        int id = 1;
        ClientOrder expectedClientOrder = new ClientOrder();
        // Set properties of the expected client order

        when(clientOrderRepository.findById(id)).thenReturn(Optional.of(expectedClientOrder));

        Optional<ClientOrder> actualClientOrder = clientOrderService.findClientOrderById(id);

        assertEquals(expectedClientOrder, actualClientOrder.orElse(null));
        verify(clientOrderRepository, times(1)).findById(id);
    }

    @Test
    public void testFindClientOrderByIdClientOrderDoesNotExist() {
        int id = 1;

        when(clientOrderRepository.findById(id)).thenReturn(Optional.empty());

        Optional<ClientOrder> actualClientOrder = clientOrderService.findClientOrderById(id);

        assertFalse(actualClientOrder.isPresent());
        verify(clientOrderRepository, times(1)).findById(id);
    }
}
