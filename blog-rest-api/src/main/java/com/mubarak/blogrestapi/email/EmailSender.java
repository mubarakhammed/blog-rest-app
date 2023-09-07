package com.mubarak.blogrestapi.email;

public interface EmailSender {
    void send(String to, String email);
}
