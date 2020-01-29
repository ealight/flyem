package com.ddm.flyem.dao;

import com.ddm.flyem.domain.Message;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name="chat_data")
public class ChatData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;

    @Embedded
    private Message message;
}
