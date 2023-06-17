package com.example.a10spring_boot_hibernate_library.controllers.jsonController;

import com.example.a10spring_boot_hibernate_library.entities.Client;
import com.example.a10spring_boot_hibernate_library.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JsonClientController {

    private ClientService clientService;

    @Autowired
    public JsonClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/jsonClients") //http://localhost:8080/jsonclients
    public List<Client> getAllClient() {
        return clientService.getAllClients();
    }

    @GetMapping("/jsonClients/{id}") //http://localhost:8080/jsonclients/id
    public Client getClientById(@PathVariable("id") int id) {
        //Pour ne pas changer la methode deja existant
        List<Client> clientstrouves = clientService.getClientById(id).stream().toList();
        Client cli = null;
        for (Client temp: clientstrouves) {
            cli = temp;
        }
        //prendre le 1er client trouvee parce que id est unique
        return cli;
    }

    //ajouter un client dans la bd
    @PostMapping("/jsonClients")
    public ResponseEntity<Client> ajouterNouveauClient(@RequestBody Client nouveauClient) {
        Client savedClient = clientService.createClient(nouveauClient);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    //modifier un client de la bd
    @PostMapping("/jsonClientsModifie")
    public ResponseEntity<String> majClient(@RequestBody Client majClient) {
        if(clientService.getClientById(majClient.getClientId()).isEmpty()) {
            String errorMessage = "L'id client " + majClient.getClientId() + " n'existe pas!";
            return ResponseEntity.badRequest().body(errorMessage);
        }
        else {
            clientService.updateClient(majClient);
            String message = "Mise à jour effectué pour l'id client " + majClient.getClientId() + "!";
            return ResponseEntity.ok(message);
        }
    }

    //effacer un client de la bd
    @DeleteMapping("/jsonClients/{id}")
    //PathVariable sert a extraire un parametre du URL
    public ResponseEntity<String> deleteClientById(@PathVariable int id) {
        boolean deleted = clientService.deleteClientById(id);
        if (deleted) {
            String message = "Client avec ID " + id + " supprimé.";
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "Client avec ID " + id + " n'existe pas.";
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }
}
