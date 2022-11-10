package org.example.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.delegates.dto.Client;
import org.springframework.stereotype.Component;

@Component
public class CheckClientDelegate implements JavaDelegate {

    private final String VARIABLE_APPROVE_CREDIT = "approveCredit";

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
        String result = approveCredit ? "TRUE" : "FALSE";

        System.out.printf("Кредит %s одобрен\n%s\n----------\n",
                (approveCredit ? "" : "не " ),
                client);
        delegateExecution.setVariable(VARIABLE_APPROVE_CREDIT,result);

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
