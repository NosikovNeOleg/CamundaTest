package org.example.delegates.end;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.service.SimpleLogService;
import org.springframework.stereotype.Component;

@Component
public class ErrorDelegate implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String bk = delegateExecution.getProcessBusinessKey();

        SimpleLogService.logWarn(bk, delegateExecution.getVariable("client").toString() + " ошибка");

        delegateExecution.getProcessEngineServices().getRuntimeService()
                .createMessageCorrelation("errorReport")
                .setVariable("client", delegateExecution.getVariable("client"))
                .setVariable("error", "unknownError")
                .processInstanceBusinessKey(delegateExecution.getProcessBusinessKey())
                .correlate();
    }
}
