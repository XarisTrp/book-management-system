package com.nyc.bookmanagement.repos;

import com.nyc.bookmanagement.model.AppUser;
import com.nyc.bookmanagement.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByReceiver(AppUser receiver);
}