package com.nyc.bookmanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libraryorderitem")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderitemid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookid")
    private Book book;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderid")
    private Order order;

    private boolean iscompleted = false;

    public OrderItem() {
    }

    public Integer getOrderitemid() {
        return orderitemid;
    }

    public void setOrderitemid(Integer orderid) {
        this.orderitemid = orderid;
    }


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean isDone() {
        return iscompleted;
    }

    public void setDone(boolean done) {
        iscompleted = done;
    }
}
