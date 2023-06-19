package com.example.a10spring_boot_hibernate_library.services;

import com.example.a10spring_boot_hibernate_library.entities.ClientOrder;
import com.example.a10spring_boot_hibernate_library.entities.Payment;
import com.example.a10spring_boot_hibernate_library.repository.ClientOrderRepository;
import com.example.a10spring_boot_hibernate_library.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private ClientOrderRepository clientOrderRepository;

    private PaymentService paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentService = new PaymentService(paymentRepository, clientOrderRepository);
    }

    @Test
    public void testDeletePaymentByIdPaymentExists() {
        int id = 1;

        // Create a sample payment
        Payment payment = new Payment();
        payment.setPaymentId(id);

        // Create a sample client order
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setPayment(payment);

        when(paymentRepository.findById(id)).thenReturn(Optional.of(payment));
        when(clientOrderRepository.save(clientOrder)).thenReturn(clientOrder);

        boolean isDeleted = paymentService.deletePaymentById(id);

        assertTrue(isDeleted);
        verify(paymentRepository, times(1)).findById(id);
        verify(paymentRepository, times(1)).save(payment);
        verify(paymentRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeletePaymentByIdPaymentDoesNotExist() {
        int id = 1;

        when(paymentRepository.findById(id)).thenReturn(Optional.empty());

        boolean isDeleted = paymentService.deletePaymentById(id);

        assertFalse(isDeleted);
        verify(paymentRepository, times(1)).findById(id);
        verify(clientOrderRepository, never()).save(any(ClientOrder.class));
        verify(paymentRepository, never()).save(any(Payment.class));
        verify(paymentRepository, never()).deleteById(anyInt());
    }

    @Test
    public void testFindPaymentByIdPaymentExists() {
        int id = 1;

        // Create a sample payment
        Payment payment = new Payment();
        payment.setPaymentId(id);

        when(paymentRepository.findById(id)).thenReturn(Optional.of(payment));

        Optional<Payment> foundPayment = paymentService.findPaymentById(id);

        assertTrue(foundPayment.isPresent());
        assertEquals(payment, foundPayment.get());
        verify(paymentRepository, times(1)).findById(id);
    }

    @Test
    public void testFindPaymentByIdPaymentDoesNotExist() {
        int id = 1;

        when(paymentRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Payment> foundPayment = paymentService.findPaymentById(id);

        assertFalse(foundPayment.isPresent());
        verify(paymentRepository, times(1)).findById(id);
    }
}

