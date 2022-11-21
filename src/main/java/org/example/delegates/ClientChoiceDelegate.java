package org.example.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.Variables;
import org.example.delegates.dto.CreditCondition;
import org.example.service.ClientChooseService;
import org.example.service.SimpleLogService;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ClientChoiceDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String bk = delegateExecution.getProcessBusinessKey();
        List<CreditCondition> listWithConditions = (List<CreditCondition>) delegateExecution.getVariable(Variables.CREDIT_VARIATIONS.getVariable());
        delegateExecution.setVariable(
                Variables.CLIENT_CHOICE.getVariable(),
                listWithConditions.get(ClientChooseService.makeAChoice(listWithConditions.size()))
        );

        SimpleLogService.logInfo(bk,
                String.format("Клиент выбрал кредит:\n%s", delegateExecution.getVariable(Variables.CLIENT_CHOICE.getVariable()))
        );


    }
}
