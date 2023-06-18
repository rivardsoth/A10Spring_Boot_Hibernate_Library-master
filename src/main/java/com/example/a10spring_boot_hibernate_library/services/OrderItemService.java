package com.example.a10spring_boot_hibernate_library.services;

import com.example.a10spring_boot_hibernate_library.entities.ClientOrder;
import com.example.a10spring_boot_hibernate_library.entities.OrderItem;
import com.example.a10spring_boot_hibernate_library.repository.OrderItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private OrderItemRepository orderItemRepository;
    private LibraryService libraryService;

    @Autowired //pas besoin de fair un new
    public OrderItemService(OrderItemRepository orderItemRepository, LibraryService libraryService) {
        this.orderItemRepository = orderItemRepository;
        this.libraryService = libraryService;
    }

    public List<OrderItem> findall() {
        return orderItemRepository.findAll();
    }


    public boolean deleteOrderItemById(int id) {
        Optional<OrderItem> tempOrderItemO = this.findOrderItemById(id);
        if (tempOrderItemO.isPresent()) {
            OrderItem tempOrderItem = tempOrderItemO.get();
            tempOrderItem.setClientOrderByOrderId(null);
            tempOrderItem.setLibraryByEanIsbn13(null);
            orderItemRepository.save(tempOrderItem);
            orderItemRepository.flush();
            orderItemRepository.delete(tempOrderItem);
            orderItemRepository.flush();

            return true;
        }
        return false;
    }

    public Optional<OrderItem> findOrderItemById(int id) {
        return orderItemRepository.findById(id);
    }
}
