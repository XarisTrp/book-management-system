package com.nyc.bookmanagement.controllers;

import com.nyc.bookmanagement.model.AppUser;
import com.nyc.bookmanagement.model.dto.AppUserDto;
import com.nyc.bookmanagement.service.AppUserService;
import com.nyc.bookmanagement.service.StockService;
import com.nyc.bookmanagement.service.LibraryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StockController {

    @Autowired
    private StockService StockService;
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private LibraryService LibraryService;


    @GetMapping("/getinventory")
    public String getAllInventory(ModelMap modelmap, HttpSession session) {
        AppUser user = appUserService.mapToEntity((AppUserDto) session.getAttribute("loggedinuser"));
        modelmap.addAttribute("stock",
                StockService.getPharmacyInventory(LibraryService.getPharmacyFromUser(user)));
        return "inventorytable";
    }

    @GetMapping("/warehouseinventory")
    public String getWarehouseInventory(ModelMap modelmap, HttpSession session, Model model) {
        modelmap.addAttribute("stock", StockService.getInventoryWarehouses());
        return "inventorytable";
    }
}
