package com.example.a10spring_boot_hibernate_library.services;

import com.example.a10spring_boot_hibernate_library.entities.ClientOrder;
import com.example.a10spring_boot_hibernate_library.entities.OrderItem;
import com.example.a10spring_boot_hibernate_library.repository.ClientOrderRepository;
import com.example.a10spring_boot_hibernate_library.repository.OrderItemRepository;
import com.example.a10spring_boot_hibernate_library.services.LibraryService;
import com.example.a10spring_boot_hibernate_library.services.OrderItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderItemServiceTest {

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private LibraryService libraryService;
    @Mock
    private ClientOrderRepository clientOrderRepository;

    @InjectMocks
    private OrderItemService orderItemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        orderItemService = new OrderItemService(orderItemRepository, libraryService, clientOrderRepository);
    }

    @Test
    public void testFindAll() {
        List<OrderItem> expectedOrderItems = new ArrayList<>();
        // Add sample order items to the expected list

        when(orderItemRepository.findAll()).thenReturn(expectedOrderItems);

        List<OrderItem> actualOrderItems = orderItemService.findall();

        assertEquals(expectedOrderItems, actualOrderItems);
        verify(orderItemRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteOrderItemByIdOrderItemExists() {
        int id = 1;

        // Create a sample order item
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(0);
        orderItem.setId(id);

        // Create a sample client order
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.ajouterOrderItem(orderItem);

        when(orderItemRepository.findById(id)).thenReturn(Optional.of(orderItem));
        when(clientOrderRepository.findById(anyInt())).thenReturn(Optional.of(clientOrder));
        when(orderItemRepository.save(orderItem)).thenReturn(orderItem);

        boolean isDeleted = orderItemService.deleteOrderItemById(id);

        assertTrue(isDeleted);
        verify(orderItemRepository, times(1)).findById(id);
        verify(clientOrderRepository, times(1)).findById(anyInt());
        verify(clientOrderRepository, times(1)).save(clientOrder);
        verify(orderItemRepository, times(1)).save(orderItem);
        verify(orderItemRepository, times(1)).delete(orderItem);
    }

    @Test
    public void testDeleteOrderItemByIdOrderItemDoesNotExist() {
        int id = 1;

        when(orderItemRepository.findById(id)).thenReturn(Optional.empty());

        boolean isDeleted = orderItemService.deleteOrderItemById(id);

        assertFalse(isDeleted);
        verify(orderItemRepository, times(1)).findById(id);
        verify(clientOrderRepository, never()).findById(anyInt());
        verify(orderItemRepository, never()).save(any(OrderItem.class));
        verify(orderItemRepository, never()).delete(any(OrderItem.class));
    }

    @Test
    public void testFindOrderItemById_rderItemExists() {
        int id = 1;
        OrderItem expectedOrderItem = new OrderItem();
        // Set properties of the expected order item

        when(orderItemRepository.findById(id)).thenReturn(Optional.of(expectedOrderItem));

        Optional<OrderItem> actualOrderItem = orderItemService.findOrderItemById(id);

        assertEquals(expectedOrderItem, actualOrderItem.orElse(null));
        verify(orderItemRepository, times(1)).findById(id);
    }

    @Test
    public void testFindOrderItemByIdOrderItemDoesNotExist() {
        int id = 1;

        when(orderItemRepository.findById(id)).thenReturn(Optional.empty());

        Optional<OrderItem> actualOrderItem = orderItemService.findOrderItemById(id);

        assertFalse(actualOrderItem.isPresent());
        verify(orderItemRepository, times(1)).findById(id);
    }

}
