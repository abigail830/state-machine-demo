package com.github.abigail830.statemachinedemo.domain;

import com.github.abigail830.statemachinedemo.domain.statemachine.ContractStates;
import com.github.abigail830.statemachinedemo.domain.statemachine.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

@Service
public class ContractService {


    StateMachineFactory stateMachineFactory;

    ContractInfrastructure contractInfrastructure;

    @Autowired
    public ContractService(StateMachineFactory stateMachineFactory, ContractInfrastructure contractInfrastructure) {
        this.stateMachineFactory = stateMachineFactory;
        this.contractInfrastructure = contractInfrastructure;
    }

    public void addContract(Contract contract) throws Exception {

        final StateMachine<ContractStates, Events> contractStatesEventsStateMachine = stateMachineFactory.getStateMachine();
        contractStatesEventsStateMachine.start();
        contract.setContractStatesEventsStateMachine(contractStatesEventsStateMachine);

        final String currentState = contractStatesEventsStateMachine.getState().getId().name();
        contract.setState(currentState);

        contractInfrastructure.saveContract(contract);
    }


    public void updateContract(Contract contract){
        contract.getContractStatesEventsStateMachine().sendEvent(Events.UPDATE_CONTRACT);

        contractInfrastructure.updateContact(contract);
    }

}
