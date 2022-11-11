package org.example.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.delegates.dto.Client;
import org.example.service.SimpleLogService;
import org.springframework.stereotype.Component;

@Component
public class CheckClientDelegate implements JavaDelegate {

    private final String VARIABLE_APPROVE_CREDIT = "approveCredit";


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Client client = (Client) delegateExecution.getVariable("client");
        boolean approveCredit =
                checkAge(client.getAge()) &
                checkIncome(client.getIncome(), client.getWantedMoney()) &
                checkHistory(
                        client.getAge(),
                        client.isHasCreditStory(),
                        client.getYears()
                );
        String message = String.format("Кредит%s одобрен", (approveCredit ? "" : " не" ));
        if (approveCredit) {
            SimpleLogService.logInfo(message);
        } else {
            SimpleLogService.logWarn(message);
        }
        delegateExecution.setVariable(VARIABLE_APPROVE_CREDIT, approveCredit ? "TRUE" : "FALSE");

    }

    private boolean checkAge(int clientAge){
        return clientAge > 17;
    }

    private boolean checkIncome(int clientIncome, int clientWantedMoney){
        return (clientWantedMoney / 10) < clientIncome;
    }

    private boolean checkHistory(int clientAge, boolean clientHistory, int years){
        return clientAge < 25 | clientHistory | (years > 0);
    }

}
