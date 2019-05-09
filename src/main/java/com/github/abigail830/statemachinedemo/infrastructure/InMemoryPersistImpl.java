package com.github.abigail830.statemachinedemo.infrastructure;

import com.github.abigail830.statemachinedemo.domain.statemachine.ContractStates;
import com.github.abigail830.statemachinedemo.domain.statemachine.Events;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;

import java.util.HashMap;
import java.util.UUID;


public class InMemoryPersistImpl implements StateMachinePersist<ContractStates, Events, UUID> {

    private HashMap<UUID, StateMachineContext<ContractStates, Events>> storage = new HashMap<>();

    @Override
    public void write(StateMachineContext<ContractStates, Events> context, UUID contextUUID) throws Exception {
        storage.put(contextUUID, context);
    }

    @Override
    public StateMachineContext<ContractStates, Events> read(UUID contextUUID) throws Exception {
        return storage.get(contextUUID);
    }
}
