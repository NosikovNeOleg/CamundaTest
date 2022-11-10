package org.example.delegates;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.delegates.dto.Client;
import org.example.delegates.dto.CreditCondition;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ChooseCreditDelegate implements JavaDelegate {

    private final String VARIABLE_CLIENT_CHOICE = "clientChoice";

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

        delegateExecution.setVariable(VARIABLE_CLIENT_CHOICE, listWithCredits.get(new Random().nextInt(listWithCredits.size())));
        System.out.println("Клиент выбрал кредит:\n" + delegateExecution.getVariable(VARIABLE_CLIENT_CHOICE) + "\n----------");
    }
}
