package com.github.abigail830.statemachinedemo.domain.statemachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.Optional;

@Slf4j
public class ContractStateMachineListener extends StateMachineListenerAdapter<ContractStates, Events> {

    @Override
    public void transition(Transition<ContractStates, Events> transition){
        log.info("ContractStates moved from:{} to:{}",
                ofNullableState(transition.getSource()),
                ofNullableState(transition.getTarget()));
    }

    @Override
    public void eventNotAccepted(Message<Events> event) {
        log.error("Event not accepted: {}", event);
    }

    private Object ofNullableState(State s) {
        return Optional.ofNullable(s)
                .map(State::getId)
                .orElse(null);
    }

}
