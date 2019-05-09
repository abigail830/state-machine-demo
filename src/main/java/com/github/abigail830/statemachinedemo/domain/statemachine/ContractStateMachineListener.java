package com.github.abigail830.statemachinedemo.domain.statemachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.Optional;

@Slf4j
public class ContractStateMachineListener extends StateMachineListenerAdapter<ContractStates, Events> {

    @Override
    public void transition(Transition<ContractStates, Events> transition){
        log.warn("ContractStates moved from:{} to:{}",
                ofNullableState(transition.getSource()),
                ofNullableState(transition.getTarget()));
    }

    private Object ofNullableState(State s) {
        return Optional.ofNullable(s)
                .map(State::getId)
                .orElse(null);
    }

}
