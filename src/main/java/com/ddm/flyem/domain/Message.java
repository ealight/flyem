package com.ddm.flyem.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.sql.Timestamp;


@Data
@Embeddable
public class Message {
    private String text;

    private String author;

    @Column(name = "send_date")
    private Timestamp sendDate;
}
