package com.github.abigail830.statemachinedemo.domain;

import com.github.abigail830.statemachinedemo.domain.statemachine.ContractStates;
import com.github.abigail830.statemachinedemo.domain.statemachine.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ContractService {


    private StateMachineFactory stateMachineFactory;
    private ContractInfrastructure contractInfrastructure;
    private StateMachinePersister<ContractStates, Events, UUID> stateMachinePersister;

    @Autowired
    public ContractService(StateMachineFactory stateMachineFactory,
                           ContractInfrastructure contractInfrastructure,
                           StateMachinePersister<ContractStates, Events, UUID> stateMachinePersister) {
        this.stateMachineFactory = stateMachineFactory;
        this.contractInfrastructure = contractInfrastructure;
        this.stateMachinePersister = stateMachinePersister;
    }

    public void addContract(Contract contract) throws Exception {

        final StateMachine<ContractStates, Events> stateMachine = stateMachineFactory.getStateMachine();
        contract.setStateMachineUUID(stateMachine.getUuid());
        stateMachinePersister.persist(stateMachine, stateMachine.getUuid());

        final String currentState = ofNullableState(stateMachine);
        contract.setState(currentState);

        contractInfrastructure.saveContract(contract);
    }


    public void updateContract(Contract contract) throws Exception {

        final StateMachine<ContractStates, Events> stateMachine = stateMachineFactory.getStateMachine();
        stateMachinePersister.restore(stateMachine, contract.getStateMachineUUID());

        stateMachine.sendEvent(Events.UPDATE_CONTRACT);

        final String currentState = ofNullableState(stateMachine);
        contract.setState(currentState);

        contractInfrastructure.updateContact(contract);
    }

    public void confirmContract(Contract contract) throws Exception {

        final StateMachine<ContractStates, Events> stateMachine = stateMachineFactory.getStateMachine();
        stateMachinePersister.restore(stateMachine, contract.getStateMachineUUID());

        stateMachine.sendEvent(Events.CONFIRM_CONSTRACT);

        final String currentState = ofNullableState(stateMachine);
        contract.setState(currentState);

        contractInfrastructure.updateContact(contract);
    }

    private String ofNullableState(StateMachine<ContractStates, Events> contractStatesEventsStateMachine) {
        return Optional.ofNullable(contractStatesEventsStateMachine.getState())
                .map(State::getId)
                .map(Enum::name)
                .orElse(null);
    }

}
