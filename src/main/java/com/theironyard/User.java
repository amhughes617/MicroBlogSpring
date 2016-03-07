package com.theironyard;

import java.util.ArrayList;

/**
 * Created by alexanderhughes on 3/7/16.
 */
public class User {
    private String userName;
    private String password;
    private ArrayList<Message> messages;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.messages = new ArrayList<Message>();
    }

    public String getName() {
        return userName;
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
