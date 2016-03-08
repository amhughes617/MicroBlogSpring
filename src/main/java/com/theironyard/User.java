package com.theironyard;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexanderhughes on 3/7/16.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true, nullable = false)
    private String userName;
    @NotNull
    private String password;

    public User() {}

    @OneToMany(mappedBy = "user")
    private List<Message> list;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Message> getList() {
        return list;
    }

    public void setList(List<Message> list) {
        this.list = list;
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
}