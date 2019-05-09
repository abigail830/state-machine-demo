package com.github.abigail830.statemachinedemo.domain;


import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    Integer id;
    String consumer;
    String provider;
    String content;
    Timestamp createTime;
    Timestamp lastUpdateTime;

    String state;

    public Contract(String consumer, String provider, String content) {
        this.consumer = consumer;
        this.provider = provider;
        this.content = content;
    }

    UUID stateMachineUUID;

}
