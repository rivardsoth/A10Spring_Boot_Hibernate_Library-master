package com.example.a10spring_boot_hibernate_library.services;

import com.example.a10spring_boot_hibernate_library.entities.Client;
import com.example.a10spring_boot_hibernate_library.entities.ClientOrder;
import com.example.a10spring_boot_hibernate_library.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientOrderService clientOrderService;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllClients() {
        // Create a list of dummy clients
        List<Client> clients = new ArrayList<>();
        clients.add(new Client());
        clients.add(new Client());

        // Mock the behavior of the clientRepository
        when(clientRepository.findAll()).thenReturn(clients);

        // Call the method under test
        List<Client> result = clientService.getAllClients();

        // Verify the result
        assertEquals(2, result.size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void testGetAllClientsQuandBdVide() {
        // Create a list of dummy clients
        List<Client> clients = new ArrayList<>();

        // Mock the behavior of the clientRepository
        when(clientRepository.findAll()).thenReturn(clients);

        // Call the method under test
        List<Client> result = clientService.getAllClients();

        // Verify the result
        assertTrue(result.isEmpty());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void testGetClientById() {
        // Create a dummy client
        Client client = new Client();
        client.setClientId(1);

        // Mock the behavior of the clientRepository
        when(clientRepository.findById(1)).thenReturn(Optional.of(client));

        // Call the method under test
        Optional<Client> result = clientService.getClientById(1);

        // Verify the result
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getClientId());
        verify(clientRepository, times(1)).findById(1);
    }

    @Test
    void testGetClientByIdAvecIdInexistant() {
        // Create a dummy client
        Client client = new Client();
        client.setClientId(1);

        // Mock the behavior of the clientRepository
        when(clientRepository.findById(2)).thenReturn(Optional.empty());

        // Call the method under test
        Optional<Client> result = clientService.getClientById(2);

        // Verify the result
        assertFalse(result.isPresent());
        verify(clientRepository, times(1)).findById(2);
    }

    @Test
    void testCreateClient() {
        // Create a dummy client
        Client client = new Client();
        client.setClientId(1);
        client.setFirstName("John");

        // Mock the behavior of the clientRepository
        when(clientRepository.save(client)).thenReturn(client);

        // Call the method under test
        Client result = clientService.createClient(client);

        // Verify the result
        assertNotNull(result);
        assertEquals(1, result.getClientId());
        assertEquals("John", result.getFirstName());
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testUpdateClient() {
        // Create a dummy client
        Client client = new Client();
        client.setClientId(1);
        client.setFirstName("John Doe");

        // Call the method under test
        clientService.updateClient(client);

        // Verify the behavior
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testDeleteClientById() {
        // Create a dummy client
        Client client = new Client();
        client.setClientId(1);
        client.setFirstName("John Doe");

        // Mock the behavior of the clientRepository
        when(clientRepository.findById(1)).thenReturn(Optional.of(client));

        // Call the method under test
        boolean result = clientService.deleteClientById(1);

        // Verify the result
        assertTrue(result);
        //verify(clientOrderService, times(1)).deleteClientOrderById(1);
        verify(clientRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteClientByIdAvecIdNonExsitant() {
        // Mock the behavior of the clientRepository
        when(clientRepository.findById(1)).thenReturn(Optional.empty());

        // Call the method under test
        boolean result = clientService.deleteClientById(1);

        // Verify the result
        assertFalse(result);
        verify(clientRepository, times(1)).findById(1);
        verify(clientOrderService, never()).deleteClientOrderById(anyInt());
        verify(clientRepository, never()).deleteById(anyInt());
    }
}
