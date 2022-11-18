package org.example.credit2.delegates;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.service.SimpleLogService;
import org.springframework.stereotype.Component;

@Component
public class ForeignTask implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        SimpleLogService.logInfo("Я здесь побывал");
    }
}
