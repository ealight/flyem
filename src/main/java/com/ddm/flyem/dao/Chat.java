package com.ddm.flyem.dao;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String token;

    @Column
    private String title;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @ManyToMany(mappedBy = "chats")
    private List<User> users;
}
