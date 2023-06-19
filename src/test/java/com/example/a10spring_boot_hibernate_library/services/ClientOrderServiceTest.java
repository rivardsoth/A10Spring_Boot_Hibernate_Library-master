package com.example.a10spring_boot_hibernate_library.services;

import com.example.a10spring_boot_hibernate_library.entities.ClientOrder;
import com.example.a10spring_boot_hibernate_library.entities.OrderItem;
import com.example.a10spring_boot_hibernate_library.repository.ClientOrderRepository;
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

    private PaymentService paymentService;
    @Mock
    private OrderItemService orderItemService;

    @InjectMocks
    private ClientOrderService clientOrderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        clientOrderService = new ClientOrderService(clientOrderRepository, orderItemService, paymentService);
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
        ClientOrder clientOrder = new ClientOrder();
        // Set properties of the client order

        when(clientOrderRepository.findById(id)).thenReturn(Optional.of(clientOrder));
        when(clientOrderRepository.save(clientOrder)).thenReturn(clientOrder);

        boolean isDeleted = clientOrderService.deleteClientOrderById(id);

        assertTrue(isDeleted);
        verify(clientOrderRepository, times(1)).findById(id);
        verify(clientOrderRepository, times(1)).save(clientOrder);
        verify(clientOrderRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteClientOrderByIdClientOrderDoesNotExist() {
        int id = 1;

        when(clientOrderRepository.findById(id)).thenReturn(Optional.empty());

        boolean isDeleted = clientOrderService.deleteClientOrderById(id);

        assertFalse(isDeleted);
        verify(clientOrderRepository, times(1)).findById(id);
        verify(orderItemService, never()).deleteOrderItemById(anyInt());
        verify(clientOrderRepository, never()).save(any(ClientOrder.class));
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
