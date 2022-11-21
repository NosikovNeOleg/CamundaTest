package org.example.delegates;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.Variables;
import org.example.delegates.dto.Client;
import org.example.service.SimpleLogService;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Random;

@Component
public class InitDelegate implements JavaDelegate {
    private final Random rnd = new Random();

    private final DateTimeFormatter formatTimeId = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {


        String timeId = generateBusinessKey();
        delegateExecution.getProcessInstance().setProcessBusinessKey(timeId);


        Client randomClient = generateClient();

        delegateExecution.setVariable(Variables.CLIENT.getVariable(), randomClient);
        SimpleLogService.logInfo(timeId,
                String.format("Поступила завяка от клиента %s на кредит",
                randomClient.getPassport())
        );
        SimpleLogService.logInfo(timeId,randomClient+ "| key=" + delegateExecution.getProcessBusinessKey());
    }

    private String generateBusinessKey(){
        return java.time.LocalDateTime.now().format(formatTimeId);
    }

    private Client generateClient(){
        return new Client(
                rnd.nextInt(100000,1000000),
                rnd.nextInt(14,80),
                rnd.nextInt(5000,100000),
                rnd.nextBoolean(),
                rnd.nextInt(0,5),
                rnd.nextInt(50000,1000000)
        );
    }
}
