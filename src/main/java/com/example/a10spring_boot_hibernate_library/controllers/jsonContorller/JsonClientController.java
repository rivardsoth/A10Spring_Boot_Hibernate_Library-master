package com.example.a10spring_boot_hibernate_library.controllers.jsonContorller;

import com.example.a10spring_boot_hibernate_library.entities.Client;
import com.example.a10spring_boot_hibernate_library.entities.Library;
import com.example.a10spring_boot_hibernate_library.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JsonClientController {

    private ClientService clientService;

    @Autowired
    public JsonClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/jsonclients") //http://localhost:8080/jsonclients
    public List<Client> getAllClient() {
        return clientService.getAllClients();
    }

    @GetMapping("/jsonclients/{id}") //http://localhost:8080/jsonclients/id
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
    @PostMapping("/jsonclients")
    public ResponseEntity<Client> ajouterNouveauClient(@RequestBody Client nouveauClient) {
        Client savedClient = clientService.createClient(nouveauClient);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    //modifier un client de la bd
    @PostMapping("/jsonclientsModifie")
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
}
