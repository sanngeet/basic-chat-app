package com.udacity.jwdnd.c1.review.service;

import com.udacity.jwdnd.c1.review.mapper.MessageMapper;
import com.udacity.jwdnd.c1.review.model.ChatForm;
import com.udacity.jwdnd.c1.review.model.ChatMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MessageService {
    private List<ChatMessage> chatMessages;
    private String bannedWords[];
    private MessageMapper messageMapper;

    public MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating MessageService bean");
        this.chatMessages = new ArrayList<>();
        this.bannedWords = new String[]{
                "stupid", "idiot", "get lost", "dumb", "fuck", "shit"
        };
    }

    public List<ChatMessage> getChatMessages() {
//        return chatMessages;
        return messageMapper.getAllMessages();
    }

    public void addMessage(ChatForm chatForm) {
        ChatMessage newMessage = new ChatMessage();
        newMessage.setUsername(chatForm.getUsername());

        if (Arrays.stream(this.bannedWords).anyMatch(chatForm.getMessageText().toLowerCase()::contains)) {
            chatForm.setMessageText("Message contains abusive words.");
            chatForm.setMessageType("Shout");
        }

        if (!chatForm.getMessageText().trim().isEmpty()) {
            switch (chatForm.getMessageType()) {
                case "Say":
                    newMessage.setMessage(chatForm.getMessageText());
                    break;
                case "Shout":
                    newMessage.setMessage(chatForm.getMessageText().toUpperCase());
                    break;
                case "Whisper":
                    newMessage.setMessage(chatForm.getMessageText().toLowerCase());
                    break;
            }
            messageMapper.addMessage(newMessage);
//            this.chatMessages.add(newMessage);
        }
    }
}
