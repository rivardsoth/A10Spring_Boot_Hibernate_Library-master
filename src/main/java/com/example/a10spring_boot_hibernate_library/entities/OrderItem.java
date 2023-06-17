package com.example.a10spring_boot_hibernate_library.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrderItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "orderId", nullable = true)
    private Integer orderId;
    @Basic
    @Column(name = "ean_isbn13", nullable = true)
    private Long eanIsbn13;
    @Basic
    @Column(name = "quantity", nullable = true)
    private Integer quantity;
    @Basic
    @Column(name = "price", nullable = true, precision = 2)
    private BigDecimal price;
    @Basic
    @Column(name = "clientId", nullable = true)
    private Integer clientId;
    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId", insertable = false, updatable = false)
    private ClientOrder clientOrderByOrderId;
    @ManyToOne
    @JoinColumn(name = "ean_isbn13", referencedColumnName = "ean_isbn13", insertable = false, updatable = false)
    private Library libraryByEanIsbn13;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Long getEanIsbn13() {
        return eanIsbn13;
    }

    public void setEanIsbn13(Long eanIsbn13) {
        this.eanIsbn13 = eanIsbn13;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (id != orderItem.id) return false;
        if (orderId != null ? !orderId.equals(orderItem.orderId) : orderItem.orderId != null) return false;
        if (eanIsbn13 != null ? !eanIsbn13.equals(orderItem.eanIsbn13) : orderItem.eanIsbn13 != null) return false;
        if (quantity != null ? !quantity.equals(orderItem.quantity) : orderItem.quantity != null) return false;
        if (price != null ? !price.equals(orderItem.price) : orderItem.price != null) return false;
        if (clientId != null ? !clientId.equals(orderItem.clientId) : orderItem.clientId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (eanIsbn13 != null ? eanIsbn13.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        return result;
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
