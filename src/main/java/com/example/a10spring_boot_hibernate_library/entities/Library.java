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
    private BigDecimal price;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Library library = (Library) o;

        if (eanIsbn13 != library.eanIsbn13) return false;
        if (length != library.length) return false;
        if (title != null ? !title.equals(library.title) : library.title != null) return false;
        if (creators != null ? !creators.equals(library.creators) : library.creators != null) return false;
        if (firstName != null ? !firstName.equals(library.firstName) : library.firstName != null) return false;
        if (lastName != null ? !lastName.equals(library.lastName) : library.lastName != null) return false;
        if (description != null ? !description.equals(library.description) : library.description != null) return false;
        if (publisher != null ? !publisher.equals(library.publisher) : library.publisher != null) return false;
        if (publishDate != null ? !publishDate.equals(library.publishDate) : library.publishDate != null) return false;
        if (price != null ? !price.equals(library.price) : library.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (eanIsbn13 ^ (eanIsbn13 >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (creators != null ? creators.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + length;
        return result;
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
