package com.nyc.bookmanagement.service;

import com.nyc.bookmanagement.controllers.MainController;
import com.nyc.bookmanagement.model.AppUser;
import com.nyc.bookmanagement.model.Role;
import com.nyc.bookmanagement.model.dto.AppUserDto;
import com.nyc.bookmanagement.repos.AppUserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserService {

    private final AppUserRepo appUserRepo;

    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUserDto dologin(String username, String password) {
       // AppUser u = appUserRepo.findByUsernameAndUserpassword(username, password);
        AppUser u = appUserRepo.findByUsername(username);
      // Check if username is correct and passwords match
        if (u != null && passwordEncoder.matches(password, u.getUserpassword())) {
            return mapToDto(u);
        } else
            return null;

    }

    public AppUserDto mapToDto(AppUser entity) {
        AppUserDto dto = new AppUserDto();
        dto.setUsername(entity.getUsername());
        dto.setFirstname(entity.getFirstname());
        dto.setLastname(entity.getLastname());
        dto.setRole(entity.getRole().name());
        dto.setPharmacy(entity.getPharmacy());
        return dto;
    }

    public AppUser mapToEntity(AppUserDto dto) {
        return appUserRepo.findById(dto.getUsername()).get();
    }

    public List<AppUserDto> getAllUsersDto() {
        return appUserRepo.findAll().
                stream().
                map(this::mapToDto).
                collect(Collectors.toList());
    }

    public AppUser convertDtoToEntity(MainController.RegisterDto dto) {
        AppUser user = new AppUser();
        user.setFirstname(dto.firstname());
        user.setRole(Role.librarian);
        user.setUsername(dto.username());
        user.setLastname(dto.lastname());
        return user;
    }

    public void createUser(AppUser user) {
        appUserRepo.save(user);
    }
}
