package org.example.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.delegates.dto.Client;
import org.springframework.stereotype.Component;

@Component
public class CheckClientDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Client client = (Client) delegateExecution.getVariable("client");
        boolean approveCredit =
                checkAge(client.getAge()) &
                checkIncome(client.getIncome()) &
                checkHistory(
                        client.getAge(),
                        client.isHasCreditStory(),
                        client.getYears()
                );
        delegateExecution.setVariable("approveCredit",approveCredit);
    }

    private boolean checkAge(int clientAge){
        return clientAge > 17;
    }

    private boolean checkIncome(int clientIncome){
        return clientIncome > 30000;
    }

    private boolean checkHistory(int clientAge, boolean clientHistory, int years){
        return clientAge < 25 | clientHistory | (years > 0);
    }

}
