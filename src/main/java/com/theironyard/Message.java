package com.theironyard;

/**
 * Created by alexanderhughes on 3/7/16.
 */
public class Message {
    private String text, author;
    private int id;
    private boolean isAuthor;

    public Message(String message, String author, int id) {
        this.text = message;
        this.author = author;
        this.id = id;
    }

    public boolean isAuthor() {
        return isAuthor;
    }

    public void setAuthor(boolean author) {
        isAuthor = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
