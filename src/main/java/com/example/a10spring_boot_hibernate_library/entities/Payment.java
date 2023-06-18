package com.example.a10spring_boot_hibernate_library.entities;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "paymentId")
public class Payment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "paymentId", nullable = false)
    private int paymentId;
    @Basic
    @Column(name = "clientId", nullable = false)
    private int clientId;
    @Basic
    @Column(name = "cardNumber", nullable = false, length = 16)
    private String cardNumber;
    @Basic
    @Column(name = "expiration", nullable = false)
    private Date expiration;
    @ManyToOne
    @JoinColumn(name = "clientId", referencedColumnName = "clientId", nullable = false, insertable=false, updatable=false)
    private Client clientByClientId;

    public Payment() {
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (paymentId != payment.paymentId) return false;
        if (clientId != payment.clientId) return false;
        if (cardNumber != null ? !cardNumber.equals(payment.cardNumber) : payment.cardNumber != null) return false;
        if (expiration != null ? !expiration.equals(payment.expiration) : payment.expiration != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = paymentId;
        result = 31 * result + clientId;
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (expiration != null ? expiration.hashCode() : 0);
        return result;
    }

    public Client getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(Client clientByClientId) {
        this.clientByClientId = clientByClientId;
    }
}
