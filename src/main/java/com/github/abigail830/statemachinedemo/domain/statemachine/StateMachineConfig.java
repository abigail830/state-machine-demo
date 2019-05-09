package com.github.abigail830.statemachinedemo.domain.statemachine;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigBuilder;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<ContractStates, Events> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<ContractStates, Events> config) throws Exception {
        config.withConfiguration().listener(new ContractStateMachineListener());
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




}
