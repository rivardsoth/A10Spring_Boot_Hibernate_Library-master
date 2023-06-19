package com.example.a10spring_boot_hibernate_library.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "clientId")
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "clientId", nullable = false)
    private int clientId;
    @Basic
    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;
    @Basic
    @Column(name = "lastName", nullable = false, length = 50)
    private String lastName;
    @Basic
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Basic
    @Column(name = "address", nullable = false, length = 200)
    private String address;


    @OneToMany(mappedBy = "clientByClientId",//l'attribut dans la classe ClientOrder
            fetch = FetchType.EAGER,//va chercher directement les clientorder
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private Collection<ClientOrder> clientOrdersByClientId;
    /*@OneToMany(mappedBy = "clientByClientId")
    private Collection<Payment> paymentsByClientId;*/
    @OneToMany(mappedBy = "clientByClientId")
    private Collection<ShoppingCart> shoppingCartsByClientId;
    @OneToOne(mappedBy = "clientByClientId")
    private UserAuthentication userAuthenticationByClientId;

    public Client() {
    }

    public Client(String firstName, String lastName, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Collection<ClientOrder> getClientOrdersByClientId() {
        return clientOrdersByClientId;
    }

    public void setClientOrdersByClientId(Collection<ClientOrder> clientOrdersByClientId) {
        this.clientOrdersByClientId = clientOrdersByClientId;
    }

    /*public Collection<Payment> getPaymentsByClientId() {
        return paymentsByClientId;
    }

    public void setPaymentsByClientId(Collection<Payment> paymentsByClientId) {
        this.paymentsByClientId = paymentsByClientId;
    }*/

    public Collection<ShoppingCart> getShoppingCartsByClientId() {
        return shoppingCartsByClientId;
    }

    public void setShoppingCartsByClientId(Collection<ShoppingCart> shoppingCartsByClientId) {
        this.shoppingCartsByClientId = shoppingCartsByClientId;
    }

    public UserAuthentication getUserAuthenticationByClientId() {
        return userAuthenticationByClientId;
    }

    public void setUserAuthenticationByClientId(UserAuthentication userAuthenticationByClientId) {
        this.userAuthenticationByClientId = userAuthenticationByClientId;
    }

    /**
     * Ajouter un ClientOrder au client
     * @param tempClientOrder
     */
    public void ajouterClientOrder(ClientOrder tempClientOrder) {
        if (this.clientOrdersByClientId == null) {
            clientOrdersByClientId = new ArrayList<>();

        }
        //faire le lien avec le client
        tempClientOrder.setClientByClientId(this);
        //on ajoute a la liste
        this.clientOrdersByClientId.add(tempClientOrder);
    }
}
