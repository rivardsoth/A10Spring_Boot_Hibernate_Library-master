package com.example.a10spring_boot_hibernate_library.services;

import com.example.a10spring_boot_hibernate_library.entities.Client;
import com.example.a10spring_boot_hibernate_library.entities.ClientOrder;
import com.example.a10spring_boot_hibernate_library.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    private ClientOrderService clientOrderService;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientOrderService clientOrderService) {
        this.clientRepository = clientRepository;
        this.clientOrderService = clientOrderService;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Integer clientId) {
        return clientRepository.findById(clientId);
    }

    public Client createClient(Client client) { return clientRepository.save(client); }

    public void updateClient(Client client) {
        clientRepository.save(client);
    }

    public boolean deleteClientById(Integer id) {
        Optional<Client> tempclientQ = this.getClientById(id);
        if (tempclientQ.isPresent()) {
            Client tempclient = tempclientQ.get();
            //verifier l'existence de clientOrder
            List<ClientOrder> clientOrders = (List<ClientOrder>) tempclient.getClientOrdersByClientId();
            if (clientOrders != null) {
                for (ClientOrder temp: clientOrders) {
                    clientOrderService.deleteClientOrderById(temp.getOrderId());
                }
                tempclient.setClientOrdersByClientId(null);
                clientRepository.save(tempclient);
            }
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
