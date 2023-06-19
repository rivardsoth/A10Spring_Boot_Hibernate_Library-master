package com.example.a10spring_boot_hibernate_library.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderId")
public class ClientOrder {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "orderId", nullable = false)
    private int orderId;
    /*@Basic
    @Column(name = "clientId", nullable = true)
    private Integer clientId;*/
    @Basic
    @Column(name = "orderDate", nullable = true)
    private Date orderDate;
    @Basic
    @Column(name = "totalAmount", nullable = true)
    private double totalAmount;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "clientId"/*, referencedColumnName = "clientId", insertable = false, updatable = false*/)
    @JsonIgnore
    private Client clientByClientId;
    @OneToMany(mappedBy = "clientOrderByOrderId",
            fetch = FetchType.EAGER,//va chercher directement les orderitems
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private Collection<OrderItem> orderItemsByOrderId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="paymentId")//colonne de jointure (clé étrangère)
    private Payment payment;

    public ClientOrder() {
    }

    public ClientOrder(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Client getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(Client clientByClientId) {
        this.clientByClientId = clientByClientId;
    }

    public Collection<OrderItem> getOrderItemsByOrderId() {
        return orderItemsByOrderId;
    }

    public void setOrderItemsByOrderId(Collection<OrderItem> orderItemsByOrderId) {
        this.orderItemsByOrderId = orderItemsByOrderId;
    }

    public void ajouterOrderItem(OrderItem tempOrderItem) {
        if (this.orderItemsByOrderId == null) {
            orderItemsByOrderId = new ArrayList<>();
            this.totalAmount = 0;
        }
        //faire le lien avec le client
        tempOrderItem.setClientOrderByOrderId(this);
        //on ajoute a la liste
        this.orderItemsByOrderId.add(tempOrderItem);
        //mettre a jour le total de la commande
        this.totalAmount += (tempOrderItem.getPrice() * tempOrderItem.getQuantity());
    }

    public void enleverOrderItem(OrderItem tempOrderItem) {
        //mettre a jour le total de la commande
        this.totalAmount -= (tempOrderItem.getPrice() * tempOrderItem.getQuantity());
        //on retire de la liste
        this.orderItemsByOrderId.remove(tempOrderItem);

    }
}
