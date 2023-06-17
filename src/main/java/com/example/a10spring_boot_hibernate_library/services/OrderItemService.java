package com.example.a10spring_boot_hibernate_library.services;

import com.example.a10spring_boot_hibernate_library.entities.OrderItem;
import com.example.a10spring_boot_hibernate_library.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderItemService {

    private OrderItemRepository orderItemRepository;

    @Autowired //pas besoin de fair un new
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> findall() {
        return orderItemRepository.findAll();
    }


    public boolean deleteOrderItemById(int id) {
        OrderItem tempOrderItem = this.findOrderItemById(id);
        if (tempOrderItem != null) {
            tempOrderItem.setLibraryByEanIsbn13(null);
            tempOrderItem.setClientOrderByOrderId(null);
            orderItemRepository.save(tempOrderItem);
            orderItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public OrderItem findOrderItemById(int id) {
        OrderItem tempOrderItem = orderItemRepository.findById(id).get();
        return tempOrderItem;
    }
}
