package org.example.delegates;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.delegates.dto.Client;
import org.example.service.SimpleLogService;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class InitDelegate implements JavaDelegate {
    private final String VARIABLE_CLIENT = "client";
    private final Random rnd = new Random();
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Client randomClient = new Client(
                rnd.nextInt(100000,1000000),
                rnd.nextInt(14,80),
                rnd.nextInt(5000,100000),
                rnd.nextBoolean(),
                rnd.nextInt(0,5),
                rnd.nextInt(50000,1000000)
        );

        delegateExecution.setVariable(VARIABLE_CLIENT, randomClient);
        SimpleLogService.logInfo(
                String.format("Поступила завяка от клиента %s на кредит",
                randomClient.getPassport())
        );
        SimpleLogService.logInfo(randomClient.toString());

    }
}
