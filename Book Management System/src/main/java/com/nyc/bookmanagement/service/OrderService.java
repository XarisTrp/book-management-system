package com.nyc.bookmanagement.service;

import com.nyc.bookmanagement.model.Book;
import com.nyc.bookmanagement.model.Order;
import com.nyc.bookmanagement.model.OrderItem;
import com.nyc.bookmanagement.model.Library;
import com.nyc.bookmanagement.model.dto.OrderItemDto;
import com.nyc.bookmanagement.repos.BookRepo;
import com.nyc.bookmanagement.repos.OrderItemRepo;
import com.nyc.bookmanagement.repos.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepo orderRepo;

    private final OrderItemRepo orderItemRepo;

    private final BookRepo BookRepo;
    private final StockService StockService;

    public OrderService(OrderRepo orderRepo, OrderItemRepo orderItemRepo, BookRepo BookRepo, StockService StockService) {
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.BookRepo = BookRepo;
        this.StockService = StockService;
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public List<Order> getAllOrdersByLibrary(Library library) {
        return orderRepo.findBylibrary(library);
    }

    public Book getBookById(Integer id) {
        return BookRepo.getReferenceById(id);
    }

    public List<Order> getAllOrderForLibrary(Library library) {
        return orderRepo.findAll();
    }

    public Order createOrder(Order order) {
        return orderRepo.save(order);
    }

    public int getInventoryForBookAndLibrary(Integer medicineid, Library library) {
        Book book = BookRepo.getReferenceById(bookid);
        return StockService.fetchInventoryforBook(library, book);
    }

    public float getOrdersCostTotalByLibrary(Library library) {
        return orderRepo.costTotal(library);

    }


    public OrderItem mapToEntity(OrderItemDto dto) {
        OrderItem item = new OrderItem();
        item.setMedicine(dto.getMedicine());
        item.setQuantity(dto.getQuantity());
        return item;
    }

//    public OrderItemDto mapToDto(OrderItem orderItem){
//        OrderItemDto item = new OrderItemDto();
//        item.setMedicine(orderItem.getMedicine());
//        item.setQuantity(orderItem.getQuantity());
//        return item;
//    }

    public List<OrderItemDto> removeFromOrderByMedicineId(List<OrderItemDto> orders, Integer medicineid) {
        OrderItemDto tempdto = new OrderItemDto();
        for (OrderItemDto o : orders) {
            if (o.getMedicine().getMedicineid().equals(medicineid)) {
                tempdto = o;
            }
        }
        orders.remove(tempdto);
        return orders;
    }

    public OrderItem getOrderItemById(Integer id) {
        return orderItemRepo.findById(id).get();
    }

    public void completeOrderItem(Integer id) {
        OrderItem temp = getOrderItemById(id);
        //TODO Update stock of library?
        temp.setDone(true);
        orderItemRepo.save(temp);
    }

}
