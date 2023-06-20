package com.example.a10spring_boot_hibernate_library.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrderItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    /*@Basic
    @Column(name = "orderId", nullable = true)
    private Integer orderId;
    @Basic
    @Column(name = "ean_isbn13", nullable = true)
    private Long eanIsbn13;*/
    @Basic
    @Column(name = "quantity", nullable = true)
    private Integer quantity;
    @Basic
    @Column(name = "price", nullable = true)
    private double price;
    /*@Basic
    @Column(name = "clientId", nullable = true)
    private Integer clientId;*/
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "orderId")
    @JsonIgnore
    private ClientOrder clientOrderByOrderId;
    @ManyToOne
    @JoinColumn(name = "ean_isbn13")
    private Library libraryByEanIsbn13;

    public OrderItem() {

    }

    public OrderItem(Integer quantity, Library libraryByEanIsbn13) {
        this.quantity = quantity;
        this.libraryByEanIsbn13 = libraryByEanIsbn13;
        this.price = libraryByEanIsbn13.getPrice();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        //quand la quantite change, le total du clientOrder change aussi
        this.quantity = quantity;
        this.clientOrderByOrderId.majPrixTotal();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ClientOrder getClientOrderByOrderId() {
        return clientOrderByOrderId;
    }

    public void setClientOrderByOrderId(ClientOrder clientOrderByOrderId) {
        this.clientOrderByOrderId = clientOrderByOrderId;
    }

    public Library getLibraryByEanIsbn13() {
        return libraryByEanIsbn13;
    }

    public void setLibraryByEanIsbn13(Library libraryByEanIsbn13) {
        this.libraryByEanIsbn13 = libraryByEanIsbn13;
    }
}
