package org.example.delegates.end;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.delegates.dto.Client;
import org.example.service.SimpleLogService;
import org.springframework.stereotype.Component;

@Component
public class DeclineDelegate implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Client client = (Client) delegateExecution.getVariable("client");
        SimpleLogService.logWarn(String.format("Клиент %s уведомлен об отказе", client.getPassport()));
    }
}
