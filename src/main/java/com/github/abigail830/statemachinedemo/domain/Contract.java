package com.github.abigail830.statemachinedemo.domain;


import com.github.abigail830.statemachinedemo.domain.statemachine.ContractStates;
import com.github.abigail830.statemachinedemo.domain.statemachine.Events;
import lombok.*;
import org.springframework.statemachine.StateMachine;

import java.sql.Timestamp;

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

    StateMachine<ContractStates, Events> contractStatesEventsStateMachine;

}
