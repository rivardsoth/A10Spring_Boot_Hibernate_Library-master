package com.example.a10spring_boot_hibernate_library.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

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


    @OneToMany(mappedBy = "clientByClientId")
    private Collection<ClientOrder> clientOrdersByClientId;
    @OneToMany(mappedBy = "clientByClientId")
    private Collection<Payment> paymentsByClientId;
    @OneToMany(mappedBy = "clientByClientId")
    private Collection<ShoppingCart> shoppingCartsByClientId;
    @OneToOne(mappedBy = "clientByClientId")
    private UserAuthentication userAuthenticationByClientId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (clientId != client.clientId) return false;
        if (firstName != null ? !firstName.equals(client.firstName) : client.firstName != null) return false;
        if (lastName != null ? !lastName.equals(client.lastName) : client.lastName != null) return false;
        if (email != null ? !email.equals(client.email) : client.email != null) return false;
        if (address != null ? !address.equals(client.address) : client.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clientId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public Collection<ClientOrder> getClientOrdersByClientId() {
        return clientOrdersByClientId;
    }

    public void setClientOrdersByClientId(Collection<ClientOrder> clientOrdersByClientId) {
        this.clientOrdersByClientId = clientOrdersByClientId;
    }

    public Collection<Payment> getPaymentsByClientId() {
        return paymentsByClientId;
    }

    public void setPaymentsByClientId(Collection<Payment> paymentsByClientId) {
        this.paymentsByClientId = paymentsByClientId;
    }

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
}
