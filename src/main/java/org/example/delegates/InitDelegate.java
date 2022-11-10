package org.example.delegates;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.delegates.dto.Client;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class InitDelegate implements JavaDelegate {

    Random rnd = new Random();
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Client randomClient = new Client(
                rnd.nextInt(14,80),
                rnd.nextInt(5000,100000),
                rnd.nextBoolean(),
                rnd.nextInt(0,5)
        );

        delegateExecution.setVariable("client", randomClient);

    }
}
