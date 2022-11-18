package org.example.delegates;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.Variables;
import org.example.delegates.dto.Client;
import org.example.delegates.dto.CreditCondition;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MakeCreditVariationsDelegate implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Client client = (Client) delegateExecution.getVariable("client");
        int creditMoney = client.getWantedMoney();
        int creditYears = 1;

        List<CreditCondition> listWithCredits = new ArrayList<>();

        listWithCredits.add(new CreditCondition(creditMoney,creditYears));
        if (client.isHasCreditStory()){
            creditYears += 1;
            listWithCredits.add(new CreditCondition(creditMoney, creditYears));
        }
        if (client.getYears() > 2){
            creditYears += 1;
            listWithCredits.add(new CreditCondition(creditMoney, creditYears));
        }

        delegateExecution.setVariable(Variables.CREDIT_VARIATIONS.getVariable(), listWithCredits);

    }
}
