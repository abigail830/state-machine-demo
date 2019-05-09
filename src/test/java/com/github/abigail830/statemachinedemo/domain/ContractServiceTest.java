package com.github.abigail830.statemachinedemo.domain;

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

    @Test
    public void addContract() throws Exception {

        final Contract contract = new Contract("Consumer", "Provider","Content");
        contractService.addContract(contract);

        assertNotNull(contract.getStateMachineUUID());
        assertEquals("SIGNING", contract.getState());
    }

    @Test
    public void updateContract() throws Exception {
        final Contract contract = new Contract("Consumer2", "Provider2", "Content2");
        contractService.addContract(contract);

        final Contract newContract = new Contract("Consumer2", "Provider2", "NewContent2");
        contractService.updateContract(newContract);

        assertNotNull(contract.getStateMachineUUID());
        assertEquals("SIGNING", newContract.getState());
    }

    @Test
    public void confirmContract() throws Exception {
        final Contract contract = new Contract("Consumer3", "Provider3", "Content3");
        contractService.addContract(contract);

        final Contract newContract = new Contract("Consumer3", "Provider3", "NewContent3");
        contractService.updateContract(newContract);
        contractService.confirmContract(newContract);

        assertNotNull(contract.getStateMachineUUID());
        assertEquals("SIGNED", newContract.getState());
    }
}