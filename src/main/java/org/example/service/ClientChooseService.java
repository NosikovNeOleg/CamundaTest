package org.example.service;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ClientChooseService {


    public static int makeAChoice(int choices){
        return new Random().nextInt(choices);
    }
}
