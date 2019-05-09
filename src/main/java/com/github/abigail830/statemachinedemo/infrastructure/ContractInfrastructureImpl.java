package com.github.abigail830.statemachinedemo.infrastructure;

import com.github.abigail830.statemachinedemo.domain.Contract;
import com.github.abigail830.statemachinedemo.domain.ContractInfrastructure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ContractInfrastructureImpl implements ContractInfrastructure {

    @Override
    public void saveContract(Contract contract) {
        log.info("saveContract invoked for {}", contract);
    }

    @Override
    public void updateContact(Contract contract) {
        log.info("updateContact invoked for {}", contract);
    }
}
