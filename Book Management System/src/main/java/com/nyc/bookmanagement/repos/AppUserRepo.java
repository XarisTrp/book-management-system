package com.nyc.bookmanagement.repos;

import com.nyc.bookmanagement.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, String> {

    AppUser findByUsernameAndUserpassword(String username, String userpassword);

    AppUser findByUsername(String username);
}
