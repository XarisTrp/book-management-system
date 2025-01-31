package com.nyc.bookmanagement.controllers;

import com.nyc.bookmanagement.model.AppUser;
import com.nyc.bookmanagement.model.Order;
import com.nyc.bookmanagement.model.OrderItem;
import com.nyc.bookmanagement.model.dto.AppUserDto;
import com.nyc.bookmanagement.model.dto.OrderItemDto;
import com.nyc.bookmanagement.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    private final AppUserService appUserService;
    private final LibraryService LibraryService;
    private final BookService BookService;
    private final OrderService orderService;
    private final StockService StockService;

    public OrderController(AppUserService appUserService, LibraryService LibraryService, BookService BookService, OrderService orderService, StockService StockService) {
        this.appUserService = appUserService;
        this.LibraryService = LibraryService;
        this.BookService = BookService;
        this.orderService = orderService;
        this.StockService = StockService;
    }

    @GetMapping("/insertorder")
    public String pharmorder(ModelMap modelMap, HttpSession session) {
        List<OrderItemDto> tempItemsList = (List<OrderItemDto>) session.getAttribute("tempItemsList");
        if (tempItemsList == null) {
            //This the first time i am calling the method
            tempItemsList = new ArrayList<>();
        }
        session.setAttribute("tempItemsList", tempItemsList);
        // Make one OrderItemDto and put it in the model
        OrderItemDto tempitem = new OrderItemDto();
        modelMap.addAttribute("tempitem", tempitem);
        // I need all the medicines for the dropdown
        modelMap.addAttribute("allmedicines", BookService.getAllNotDisabledMedicine());
        // Put the partial submitted items in the model
        return "pharmacyorder";
    }

    @PostMapping(value = "dopartialorder")
    public String doPartialOrder(@ModelAttribute OrderItemDto orderItem, HttpSession session) {
        // take the order item, put it in the session of the user and return the same page
        List<OrderItemDto> partialorder = (List<OrderItemDto>) session.getAttribute("tempItemsList");
        if (partialorder.contains(orderItem)) {//User already picked the book. Add thw new quantity
            partialorder.remove(orderItem);
        }
        partialorder.add(orderItem);
        session.setAttribute("tempItemsList", partialorder);
        return "redirect:/insertorder";
    }

    @GetMapping("/orders")
    public String showAllorders(ModelMap modelMap, HttpSession session) {
        AppUser user = appUserService.mapToEntity((AppUserDto) session.getAttribute("loggedinuser"));
        if (user.getRole().name().equals("Admin")) {
            modelMap.addAttribute("allorders", orderService.getAllOrders());
        } else if (user.getRole().name().equals("librarian")) {

            modelMap.addAttribute("allorders", orderService.getAllOrderForPharmacy(user.getPharmacy()));
        }
        //  modelMap.addAttribute("allorders", orderService.getAllOrders());
        return "allorders.html";
    }

    // Returns the current inentory for a specific book and library
    @GetMapping("/getinvformed")
    @ResponseBody
    public int getInventoryForMedicineAndPharmacy(@RequestParam int medId, HttpSession session) {
        AppUser user = appUserService.mapToEntity((AppUserDto) session.getAttribute("loggedinuser"));
        int inv = StockService.fetchInventoryforMedicine(user.getPharmacy(), BookService.getMedicineById(medId));
        return inv;
    }

    @GetMapping(value = "/doinsertorder")
    public String doInserOrder(HttpSession session) {
        // take all the the order items,from the session
        List<OrderItemDto> orderitems = (List<OrderItemDto>) session.getAttribute("tempItemsList");

        Order myorder = new Order();
        //convert OrderItemDtos to Entity
        List<OrderItem> tempitems = orderitems.stream()
                .map(o -> orderService.mapToEntity(o))
                .toList();

        // fix both sides of the relationship
        tempitems.forEach(i -> i.setOrder(myorder));
        myorder.setItems(tempitems);

        AppUser user = appUserService.mapToEntity((AppUserDto) session.getAttribute("loggedinuser"));
        myorder.setPharmacy(LibraryService.getPharmacyFromUser(user));
        myorder.setOrderdate(LocalDate.now());
        orderService.createOrder(myorder);
        session.setAttribute("tempItemsList", null); // clear the session
        return "redirect:/showdashboard";
    }

    @GetMapping("/deleteorderitem/{id}")
    public String deleteOrderItem(@PathVariable int id, HttpSession session) {
        List<OrderItemDto> orderitems = (List<OrderItemDto>) session.getAttribute("tempItemsList");
        List<OrderItemDto> newlist = orderService.removeFromOrderByMedicineId(orderitems, id);
        session.removeAttribute("tempItemsList");
        session.setAttribute("tempItemsList", newlist);
        return "redirect:/insertorder";
    }

    @GetMapping("/completeorderitem")
    public String completeOrderItem(@RequestParam int order) {
        orderService.completeOrderItem(order);
        return "redirect:/orders";
    }

}
