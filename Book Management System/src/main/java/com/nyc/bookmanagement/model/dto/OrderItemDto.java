package com.nyc.bookmanagement.model.dto;

import com.nyc.bookmanagement.model.Book;

import java.util.Objects;


public class OrderItemDto {
    private Book book;

    private Integer quantity;

    public OrderItemDto() {
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

    @Override
    public String toString() {
        return "OrderItemDto{" +
                "book=" + book +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemDto that = (OrderItemDto) o;
        return Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(book);
    }
}
