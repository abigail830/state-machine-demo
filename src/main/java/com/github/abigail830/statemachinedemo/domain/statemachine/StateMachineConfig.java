package com.github.abigail830.statemachinedemo.domain.statemachine;

import com.github.abigail830.statemachinedemo.infrastructure.InMemoryPersistImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.EnumSet;
import java.util.UUID;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<ContractStates, Events> {


    @Override
    public void configure(StateMachineConfigurationConfigurer<ContractStates, Events> config) throws Exception {
        config.withConfiguration()
                .listener(new ContractStateMachineListener())
                .autoStartup(true);
    }

    @Override
    public void configure(StateMachineStateConfigurer<ContractStates, Events> states)
            throws Exception {

        states.withStates()
                .initial(ContractStates.SIGNING)
                .states(EnumSet.allOf(ContractStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<ContractStates, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(ContractStates.SIGNING).target(ContractStates.SIGNED).event(Events.CONFIRM_CONSTRACT)
                .and()
                .withExternal()
                .source(ContractStates.SIGNING).target(ContractStates.CANCELLED).event(Events.CANCEL_CONTRACT)
                .and()
                .withExternal()
                .source(ContractStates.SIGNING).target(ContractStates.SIGNING).event(Events.UPDATE_CONTRACT)
                .and()
                .withExternal()
                .source(ContractStates.SIGNED).target(ContractStates.UNSIGNING).event(Events.PROVIDER_UNSIGN_CONTRACT)
                .and()
                .withExternal()
                .source(ContractStates.SIGNED).target(ContractStates.UNSIGNED).event(Events.CONSUMER_UNSIGN_CONTRACT)
                .and()
                .withExternal()
                .source(ContractStates.UNSIGNING).target(ContractStates.UNSIGNED).event(Events.CONSUMER_UNSIGN_CONTRACT);
    }

    @Bean
    public StateMachinePersist<ContractStates, Events, UUID> inMemoryPersist() {
        return new InMemoryPersistImpl();
    }

    @Bean
    public StateMachinePersister<ContractStates, Events, UUID> persister(StateMachinePersist<ContractStates, Events, UUID> defaultPersist) {
        return new DefaultStateMachinePersister<>(defaultPersist);
    }


}
