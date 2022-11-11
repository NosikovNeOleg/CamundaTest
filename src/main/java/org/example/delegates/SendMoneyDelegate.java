package org.example.delegates;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.delegates.dto.Client;
import org.example.service.SendMoneyService;
import org.example.service.sendMoneyImpls.SendMoneyRusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMoneyDelegate implements JavaDelegate {

    private final SendMoneyService sendMoneyService;

    @Autowired
    public SendMoneyDelegate(SendMoneyRusServiceImpl sendMoneyService) {
        this.sendMoneyService = sendMoneyService;
    }
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Client client = (Client) delegateExecution.getVariable("client");
        sendMoneyService.sendMoney(client.getPassport() ,client.getWantedMoney());
    }
}
