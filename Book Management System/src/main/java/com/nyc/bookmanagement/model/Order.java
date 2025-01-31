package com.nyc.bookmanagement.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "libraryorder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libraryid")
    private Library library;

    @Temporal(TemporalType.DATE)
    private LocalDate orderdate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderItem> items;

    public Order() {
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long id) {
        this.orderid = id;
    }

    public Library getPharmacy() {
        return library;
    }

    public void setPharmacy(Library library) {
        this.library = library;
    }

    public LocalDate getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(LocalDate orderdate) {
        this.orderdate = orderdate;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
