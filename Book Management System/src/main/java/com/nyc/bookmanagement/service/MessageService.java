package com.nyc.bookmanagement.service;


import com.nyc.bookmanagement.model.AppUser;
import com.nyc.bookmanagement.model.Message;
import com.nyc.bookmanagement.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public void insertMessage(Message message) {
        messageRepository.save(message);
    }

    public List<Message> getMyMessages(AppUser appUser) {
        return messageRepository.findByReceiver(appUser);
    }
}
