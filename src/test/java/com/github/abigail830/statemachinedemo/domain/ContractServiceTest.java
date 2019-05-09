package com.github.abigail830.statemachinedemo.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContractServiceTest {

    @Autowired
    ContractService contractService;

    @Before
    public void setUp() throws Exception {
//        final ContractInfrastructureImpl contractInfrastructure = new ContractInfrastructureImpl();
//        final StateMachineFactory stateMachineFactory = new StateMachineFactory();
//        contractService = new ContractService(stateMachineFactory, contractInfrastructure);
    }

    @Test
    public void addContract() throws Exception {

        final Contract contract = new Contract("Consumer", "Provider","Content");
        contractService.addContract(contract);

        assertNotNull(contract.getContractStatesEventsStateMachine());
        assertEquals("SIGNING", contract.getState());
    }
}