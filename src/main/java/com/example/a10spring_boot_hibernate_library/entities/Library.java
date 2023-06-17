package com.example.a10spring_boot_hibernate_library.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "eanIsbn13")
@Entity
public class Library {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ean_isbn13", unique = true, nullable = false)
    private long eanIsbn13;
    @Basic
    @Column(name = "title", nullable = false, length = 145)
    private String title;
    @Basic
    @Column(name = "creators", nullable = false, length = 123)
    private String creators;
    @Basic
    @Column(name = "firstName", nullable = false, length = 13)
    private String firstName;
    @Basic
    @Column(name = "lastName", nullable = false, length = 14)
    private String lastName;
    @Basic
    @Column(name = "description", nullable = false, length = 4769)
    private String description;
    @Basic
    @Column(name = "publisher", nullable = true, length = 37)
    private String publisher;
    @Basic
    @Column(name = "publishDate", nullable = true)
    private Date publishDate;
    @Basic
    @Column(name = "price", nullable = false, precision = 3)
    private double price;
    @Basic
    @Column(name = "length", nullable = false)
    private int length;
    @OneToMany(mappedBy = "libraryByEanIsbn13")
    private Collection<OrderItem> orderItemsByEanIsbn13;
    @OneToMany(mappedBy = "libraryByEanIsbn13")
    private Collection<ShoppingCart> shoppingCartsByEanIsbn13;

    public long getEanIsbn13() {
        return eanIsbn13;
    }

    public void setEanIsbn13(long eanIsbn13) {
        this.eanIsbn13 = eanIsbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreators() {
        return creators;
    }

    public void setCreators(String creators) {
        this.creators = creators;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Collection<OrderItem> getOrderItemsByEanIsbn13() {
        return orderItemsByEanIsbn13;
    }

    public void setOrderItemsByEanIsbn13(Collection<OrderItem> orderItemsByEanIsbn13) {
        this.orderItemsByEanIsbn13 = orderItemsByEanIsbn13;
    }

    public Collection<ShoppingCart> getShoppingCartsByEanIsbn13() {
        return shoppingCartsByEanIsbn13;
    }

    public void setShoppingCartsByEanIsbn13(Collection<ShoppingCart> shoppingCartsByEanIsbn13) {
        this.shoppingCartsByEanIsbn13 = shoppingCartsByEanIsbn13;
    }
}
