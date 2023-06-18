package com.example.a10spring_boot_hibernate_library.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ShoppingCart {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "clientId", nullable = true)
    private Integer clientId;
    @Basic
    @Column(name = "ean_isbn13", nullable = true)
    private Long eanIsbn13;
    @Basic
    @Column(name = "quantity", nullable = true)
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "clientId", referencedColumnName = "clientId", insertable = false, updatable = false)
    private Client clientByClientId;
    @ManyToOne
    @JoinColumn(name = "ean_isbn13", referencedColumnName = "ean_isbn13", insertable = false, updatable = false)
    private Library libraryByEanIsbn13;

    public ShoppingCart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingCart that = (ShoppingCart) o;

        if (id != that.id) return false;
        if (clientId != null ? !clientId.equals(that.clientId) : that.clientId != null) return false;
        if (eanIsbn13 != null ? !eanIsbn13.equals(that.eanIsbn13) : that.eanIsbn13 != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        result = 31 * result + (eanIsbn13 != null ? eanIsbn13.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }

    public Client getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(Client clientByClientId) {
        this.clientByClientId = clientByClientId;
    }

    public Library getLibraryByEanIsbn13() {
        return libraryByEanIsbn13;
    }

    public void setLibraryByEanIsbn13(Library libraryByEanIsbn13) {
        this.libraryByEanIsbn13 = libraryByEanIsbn13;
    }
}
