package com.example.a10spring_boot_hibernate_library.entities;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "paymentId")
public class Payment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "paymentId", nullable = false)
    private int paymentId;
    /*@Basic
    @Column(name = "clientId", nullable = false)
    private int clientId;*/
    @Basic
    @Column(name = "cardNumber", nullable = false, length = 16)
    private String cardNumber;
    @Basic
    @Column(name = "expiration", nullable = false)
    private Date expiration;
    /*@ManyToOne
    @JoinColumn(name = "clientId", referencedColumnName = "clientId", nullable = false, insertable=false, updatable=false)
    private Client clientByClientId;*/
    @OneToOne(mappedBy = "payment"/*,//l'attribut dans la classe ClientOrder
            //pas de cascade pour le delete enlever la suppresion en cascade
            /*cascade = CascadeType.PERSIST*/)
    @JsonIgnore
    private ClientOrder clientOrder;

    public Payment() {
    }

    public Payment(String cardNumber, Date expiration) {
        this.cardNumber = cardNumber;
        this.expiration = expiration;
    }

    public ClientOrder getClientOrder() {
        return clientOrder;
    }

    public void setClientOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    /*public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }*/

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    /*public Client getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(Client clientByClientId) {
        this.clientByClientId = clientByClientId;
    }*/
}
